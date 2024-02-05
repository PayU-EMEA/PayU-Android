package com.payu.android.front.sdk.payment_library_webview_module.web.formatter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;

import com.payu.android.front.sdk.payment_library_webview_module.R;

public class SslAddressFormatter {

    private static final String HTTPS_PREFIX = "https";
    private static final String HTTP_PREFIX = "http";
    @NonNull
    private final Context context;

    public SslAddressFormatter(@NonNull Context context) {
        this.context = context;
    }

    public CharSequence getFormattedAddress(@NonNull String address, boolean isAddressVerified) {
        SpannableString spannableString = SpannableString.valueOf(address);

        if (address.contains(HTTP_PREFIX)) {
            int startPosition = getPrefixStartPosition(address);
            int endPosition = startPosition + getPrefixLength(address);
            int color = isAddressVerified ? ContextCompat.getColor(context, R.color.green) : ContextCompat.getColor(context, R.color.green);
            spannableString.setSpan(new ForegroundColorSpan(color), startPosition, endPosition, 0);

            if (!isAddressVerified) {
                spannableString.setSpan(new StrikethroughSpan(), startPosition, endPosition, 0);
            }
        }

        return spannableString;
    }

    public boolean isHttps(@NonNull String url) {
        return url.startsWith(HTTPS_PREFIX);
    }

    private int getPrefixLength(String address) {
        return isHttps(address) ? HTTPS_PREFIX.length() : HTTP_PREFIX.length();
    }

    private int getPrefixStartPosition(String address) {
        return isHttps(address) ? address.indexOf(HTTPS_PREFIX) : address.indexOf(HTTP_PREFIX);
    }
}
