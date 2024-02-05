package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core;

import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.IFrameMessage.UNKNOWN;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.StringUtilsKt.retrieveAuthenticationId;

import android.util.Log;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.model.LoggerInformation;
import com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.service.LoggerService;
import com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.service.RetrofitServiceInstance;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionDetail;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoftAcceptStateListener implements OnRetrieveStateListener {

    private final OnRedirectionCompleted onRedirectionCompleted;
    private final AuthorizationDetails authorizationDetails;
    private boolean isExecuted;

    public SoftAcceptStateListener(@NonNull OnRedirectionCompleted redirectionCompleted,
                                   @NonNull AuthorizationDetails authorizationDetails) {
        this.onRedirectionCompleted = redirectionCompleted;
        this.authorizationDetails = authorizationDetails;
        setCallExecuted(false);
    }

    @Override
    public void retrieveState(@NonNull String state, @NonNull String pageInfo, @NonNull String userAgentInfo) {

        IFrameMessage message = IFrameMessage.retrieveIFrameMessageByName(state);
        //should only be a temporary solution
        //could this be a logic problem?
      //  if (UNKNOWN.equals(message)) {
     //       return;
     //   }
        doOnce(message, pageInfo, userAgentInfo);
    }

    private SoftAcceptTransactionStatus transactionStatus(@NonNull IFrameMessage frameMessage) {
        switch (frameMessage) {
            case SUCCESS:
                return SoftAcceptTransactionStatus.AUTHENTICATION_SUCCESSFUL;
            case NOT_REQUIRED:
                return SoftAcceptTransactionStatus.AUTHENTICATION_NOT_REQUIRED;
            case CANCEL:
                return SoftAcceptTransactionStatus.AUTHENTICATION_CANCELED;
            case DISPLAY_FRAME:
                return SoftAcceptTransactionStatus.DISPLAY_FRAME;
            default:
                return SoftAcceptTransactionStatus.UNKNOWN;
        }
    }

    private synchronized void doOnce(@NonNull IFrameMessage message, @NonNull String pageInfo, @NonNull String userAgentInfo) {
        Log.v("Listener", "message from IFrame " + message.getMessage());
        if (isCallExecuted()) {
            return;
        }
        SoftAcceptTransactionDetail softAcceptTransactionDetail = new SoftAcceptTransactionDetail(transactionStatus(message), authorizationDetails);
        callLogger(message.getMessage(), retrieveAuthenticationId(pageInfo), userAgentInfo);
        onRedirectionCompleted.onDetailReceived(softAcceptTransactionDetail);
        setCallExecuted(true);
    }

    //TODO user agent info
    private synchronized void callLogger(@NonNull String message, @NonNull String authId, @NonNull String userAgentInfo) {
        LoggerInformation loggerInformation = new LoggerInformation("receivedMessage", authId, "INFO", message, "Android - SDK PayU");
        LoggerService service = RetrofitServiceInstance.createService(RetrofitServiceInstance.createRetrofitInstance(" https://secure.payu.com"));
        Call<Unit> response = service.sendInformation(loggerInformation);
        response.enqueue(new Callback<Unit>() {
            @Override
            public void onResponse(Call<Unit> call, Response<Unit> response) {
                Log.v("SoftAcceptStateListener", "Success " + response.toString());
            }

            @Override
            public void onFailure(Call<Unit> call, Throwable t) {
                Log.v("SoftAcceptStateListener", "Failure " + t.getMessage());

            }
        });
    }

    private synchronized boolean isCallExecuted() {
        return isExecuted;
    }

    private synchronized void setCallExecuted(boolean executed) {
        isExecuted = executed;
    }
}
