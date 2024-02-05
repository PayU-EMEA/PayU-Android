package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

public class SoftAcceptTransactionData implements Parcelable {
    @NonNull
    private String link;


    private SoftAcceptTransactionData(@NonNull String link) {
        this.link = link;
    }

    protected SoftAcceptTransactionData(Parcel in) {
        link = in.readString();
    }

    public static final Creator<SoftAcceptTransactionData> CREATOR = new Creator<SoftAcceptTransactionData>() {
        @Override
        public SoftAcceptTransactionData createFromParcel(Parcel in) {
            return new SoftAcceptTransactionData(in);
        }

        @Override
        public SoftAcceptTransactionData[] newArray(int size) {
            return new SoftAcceptTransactionData[size];
        }
    };

    @NonNull
    public String getLink() {
        return link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(link);
    }

    public static class Builder {
        private String link;


        public SoftAcceptTransactionData build() {
            checkArgument(notEmpty(link), "link should be provided");
            return new SoftAcceptTransactionData(link);
        }

        public Builder withLink(@NonNull String link) {
            this.link = link;
            return this;
        }
    }
}
