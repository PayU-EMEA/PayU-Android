package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.common.base.Strings;

import org.apache.http.conn.ssl.AbstractVerifier;
import org.apache.http.conn.ssl.StrictHostnameVerifier;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

public final class HostnameListStrictVerifier extends AbstractVerifier {

    private static StrictHostnameVerifier sStrictHostnameVerifier;
    private List<String> mHostUrlList;

    public HostnameListStrictVerifier(@NonNull String... urls) {
        checkNotNull(urls, "Url has to be provided");
        mHostUrlList = getHostUrlList(urls);

        if (sStrictHostnameVerifier == null) {
            sStrictHostnameVerifier = new StrictHostnameVerifier();
        }
    }

    @Override
    public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {

        if (!mHostUrlList.contains(host)) {
            throw new SSLPeerUnverifiedException("Host does not match expected value");
        }

        sStrictHostnameVerifier.verify(host, cns, subjectAlts);
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
