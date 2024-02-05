package com.payu.android.front.sdk.marketplace_terms_condition.marketplace;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.network.RetrofitService;
import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.network.TermsAndConditionService;
import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view.TermsMarketPlacePresenter;
import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view.TermsMarketPlaceView;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsMarketPlaceService {
    @NonNull
    private TermsMarketPlaceView view;
    @NonNull
    private TermsMarketPlacePresenter presenter;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String linkingText;
    @NonNull
    private String baseUrl;
    @NonNull
    private String verificationId;
    @NonNull
    private TermsAndConditionService service;

    private TermsMarketPlaceService(@NonNull String title,
                                    @NonNull String description,
                                    @NonNull String linkingText,
                                    @NonNull String baseUrl,
                                    @NonNull String verificationId,
                                    @NonNull TermsMarketPlaceView view,
                                    @NonNull TermsMarketPlacePresenter presenter,
                                    @NonNull TermsAndConditionService service
    ) {
        this.title = title;
        this.description = description;
        this.linkingText = linkingText;
        this.baseUrl = baseUrl;
        this.verificationId = verificationId;
        this.view = view;
        this.presenter = presenter;
        this.presenter.takeView(view);
        this.service = service;

        String regulationUrl = createUrl(baseUrl, verificationId);
        this.presenter.setInformation(title, description, encodedUrlString(linkingText, regulationUrl));
    }

    /**
     * Create new instance of TermsMarketPlaceService
     * to use this feature please bind
     *
     * @param title          - text for widget title
     * @param description    - checkbox text (leftSide text)
     * @param linkingText    - text that will link to regulations hyperlink
     * @param baseUrl        - needed for regulations and marketplace call
     * @param verificationId - needed for regulations and marketplace call
     * @param view           - TermsMarketPlaceView that should be added to your view:
     *                       com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view.TermsMarketPlaceView
     * @return Instance of TermsMarketPlaceService
     */
    public static TermsMarketPlaceService newInstance(@NonNull String title,
                                                      @NonNull String description,
                                                      @NonNull String linkingText,
                                                      @NonNull String baseUrl,
                                                      @NonNull String verificationId,
                                                      @NonNull TermsMarketPlaceView view) {
        RetrofitService retrofitService = new RetrofitService();
        return new TermsMarketPlaceService(title, description, linkingText, baseUrl, verificationId, view, new TermsMarketPlacePresenter(),
                retrofitService.createService(retrofitService.createRetrofitInstance(baseUrl)));
    }


    private String createUrl(String baseUrl, String verificationId) {
        return baseUrl + "/api/aml-verification/v1/public/verifications/" + verificationId + "/regulations";
    }

    //prepare UI
    private String encodedUrlString(String textToUrl, String url) {
        return "<a href=\"" + url + "\">" + textToUrl + "</a>";//&lt;
    }

    /**
     * OnClick should be bind to merchant button.
     * This action triggers a call to backend. Call will be made only if user select a checkbox
     *
     * @param termsAgreementResponseListener - will be notified after response from backend success - in case of positive
     *                                       and onError (Throwable) in case of issue
     */
    public void onTermsApproveClick(@NonNull final TermsAgreementResponseListener termsAgreementResponseListener) {
        if (presenter.isAgreementConfirmed()) {
            Call<Unit> response = service.approveRegulationService(verificationId, verificationId);
            response.enqueue(new Callback<Unit>() {
                @Override
                public void onResponse(Call<Unit> call, Response<Unit> response) {
                    termsAgreementResponseListener.onSuccess();
                }

                @Override
                public void onFailure(Call<Unit> call, Throwable t) {
                    termsAgreementResponseListener.onError(t);
                }
            });
        }
    }

    /**
     * @return current of terms and condition on marketplace
     */
    public LiveData<Boolean> isChecked() {
        return presenter.isAgreementSelected();
    }

}
