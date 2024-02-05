package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.model;

import com.google.gson.annotations.SerializedName;

public class CvvAuthorization {

    @SerializedName("request")
    final String request = "auth.cvv";
    @SerializedName("data")
    CvvData data;

    public static final class Builder {

        private String cvv;
        private String mRefReqId;

        public CvvAuthorization build() {
            CvvData cvvData = new CvvData();
            cvvData.cvv = cvv;
            cvvData.refReqId = mRefReqId;
            CvvAuthorization cvvAuthorization = new CvvAuthorization();
            cvvAuthorization.data = cvvData;
            return cvvAuthorization;
        }

        public Builder withCvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder withRefReqId(String refReqId) {
            mRefReqId = refReqId;
            return this;
        }
    }

    public static class CvvData {

        @SerializedName("cvv")
        String cvv;
        @SerializedName("refReqId")
        String refReqId;
    }
}

