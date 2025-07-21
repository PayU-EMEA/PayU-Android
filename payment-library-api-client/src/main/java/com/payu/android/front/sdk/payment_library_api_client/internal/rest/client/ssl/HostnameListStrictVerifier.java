package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.internal.tls.OkHostnameVerifier;

public final class HostnameListStrictVerifier implements HostnameVerifier {
    private final List<String> mHostUrlList;
    private final OkHostnameVerifier okHostnameVerifier = OkHostnameVerifier.INSTANCE;

    public HostnameListStrictVerifier(@NonNull String... urls) {
        checkNotNull(urls, "Url has to be provided");
        mHostUrlList = getHostUrlList(urls);
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
        if (!mHostUrlList.contains(hostname)) {
            return false;
        }

        return okHostnameVerifier.verify(hostname, session);
    }


    private String getHostUrl(String hostname) {
        return firstNonNull(Uri.parse(hostname).getHost(), hostname);
    }

    private List<String> getHostUrlList(String... urls) {
        List<String> hostnameList = new ArrayList<String>(urls.length);

        for (int i = 0; i < urls.length; i++) {

            if (!Strings.isNullOrEmpty(urls[i])) {
                hostnameList.add(getHostUrl(urls[i]));
            }
        }

        return hostnameList;
    }
}
