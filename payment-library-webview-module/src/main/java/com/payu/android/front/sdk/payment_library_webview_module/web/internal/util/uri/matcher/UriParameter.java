package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import com.google.common.base.Objects;

public class UriParameter {

    private String mParameterName;
    private String mParameterValue;

    public UriParameter(String parameterName, String parameterValue) {
        mParameterName = parameterName;
        mParameterValue = parameterValue;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UriParameter that = (UriParameter) o;
        return Objects.equal(this.mParameterName, that.mParameterName)
                && Objects.equal(this.mParameterValue, that.mParameterValue);
    }

    public String getName() {
        return mParameterName;
    }

    public String getValue() {
        return mParameterValue;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mParameterName, mParameterValue);
    }
}
