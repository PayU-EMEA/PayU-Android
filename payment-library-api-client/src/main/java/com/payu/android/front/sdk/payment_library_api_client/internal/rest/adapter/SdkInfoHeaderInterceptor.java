package com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;


public class SdkInfoHeaderInterceptor implements Interceptor {
    private static final String MERCHANT_APP_NAME_HEADER = "X-Merchant-App-Name";
    private static final String MERCHANT_APP_VER_HEADER = "X-Merchant-App-Ver";
    private static final String OS_HEADER = "X-OS";
    private static final String SDK_NAME_HEADER = "X-SDK-Name";
    private static final String SDK_VER_HEADER = "X-SDK-Ver";

    private static final String ANDROID_OS_HEADER_VALUE = "Android";
    private static final String SDK_NAME_HEADER_VALUE = "mobile_sdk";

    private final String applicationPackageName;
    private final String applicationVersionName;
    private final String mobileSdkVersionName;

    public SdkInfoHeaderInterceptor(@NonNull Context context) {
        applicationPackageName = checkNotNull(context).getPackageName();
        applicationVersionName = retrieveApplicationVersionName(context);
        mobileSdkVersionName = BuildConfig.LIBRARY_VERSION_NAME;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(MERCHANT_APP_NAME_HEADER, applicationPackageName)
                .addHeader(MERCHANT_APP_VER_HEADER, applicationVersionName)
                .addHeader(OS_HEADER, ANDROID_OS_HEADER_VALUE)
                .addHeader(SDK_NAME_HEADER, SDK_NAME_HEADER_VALUE)
                .addHeader(SDK_VER_HEADER, mobileSdkVersionName)
                .build();

        return chain.proceed(request);
    }

    private String retrieveApplicationVersionName(Context context) {
        String versionName = "n/a";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(applicationPackageName, 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
