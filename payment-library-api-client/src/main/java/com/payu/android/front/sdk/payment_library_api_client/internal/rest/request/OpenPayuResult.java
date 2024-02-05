package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import com.google.gson.annotations.SerializedName;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.OpenPayuStatusCode;

public class OpenPayuResult {

    public static class OpenPayuStatus {

        @SerializedName("statusCode")
        public OpenPayuStatusCode statusCode;

        @SerializedName("code")
        public Integer code;
    }

    @SerializedName("type")
    public String type;


    @SerializedName("status")
    public OpenPayuStatus status;

    public String getType() {
        return (type != null) ? type : null;
    }

    public Integer getCode() {
        return status.code;
    }

    public OpenPayuStatusCode getOpenPayuStatusCode() {
        return (status.statusCode != null) ? status.statusCode : null;
    }
}
