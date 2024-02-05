package com.payu.android.front.sdk.payment_library_core_android.events;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

/**
 * In case when redirection to mobile app is passed as a link
 * please provide fallbackUrl for handling case when user doesn't install app on local device
 */
public class AuthorizationDetails implements Parcelable {
    private final static String CONTINUE_URL = "https://mobilesdk.secure.payu.com/continue";
    public static final Creator<AuthorizationDetails> CREATOR = new Creator<AuthorizationDetails>() {

        @Override
        public AuthorizationDetails createFromParcel(Parcel parcel) {
            return new AuthorizationDetails((PaymentAuthorization) parcel.readSerializable(),
                    parcel.readString(),
                    parcel.readString(),
                    (Map<String, String>) parcel.readSerializable(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString());
        }

        @Override
        public AuthorizationDetails[] newArray(int i) {
            return new AuthorizationDetails[0];
        }
    };
    private PaymentAuthorization authorizationType;
    @NonNull
    private String link;
    @Nullable
    private String continueUrl;
    @Nullable
    private String orderId;
    @Nullable
    private String extOrderId;
    @Nullable
    private Map<String, String> postParameterMap;

    @Nullable
    private String fallbackLink;

    private AuthorizationDetails(PaymentAuthorization authorizationType, @NonNull String link,
                                 @Nullable String continueUrl, @Nullable Map<String, String> postParameterMap,
                                 @Nullable String orderId, @Nullable String extOrderId, @Nullable String fallbackLink) {
        this.authorizationType = authorizationType;
        this.link = link;
        this.continueUrl = continueUrl;
        this.orderId = orderId;
        this.extOrderId = extOrderId;
        this.postParameterMap = postParameterMap;
        this.fallbackLink = fallbackLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public PaymentAuthorization getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(PaymentAuthorization authorizationType) {
        this.authorizationType = authorizationType;
    }

    public Optional<String> getContinueUrl() {
        return fromNullable(continueUrl);
    }

    public Optional<String> getLink() {
        return fromNullable(link);
    }

    public void setLink(@NonNull String link) {
        this.link = link;
    }

    public Optional<String> getOrderId() {
        return fromNullable(orderId);
    }

    public Optional<String> getExtOrderId() {
        return fromNullable(extOrderId);
    }

    public Optional<Map<String, String>> getPostParameterMap() {
        return fromNullable(postParameterMap);
    }


    public Optional<String> getFallbackLink() {
        return fromNullable(fallbackLink);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(authorizationType);
        parcel.writeString(link);
        parcel.writeString(continueUrl);
        parcel.writeSerializable(postParameterMap != null ? new HashMap<>(postParameterMap) : null);
        parcel.writeString(orderId);
        parcel.writeString(extOrderId);
        parcel.writeString(fallbackLink);
    }

    @Override
    public String toString() {
        return "AuthorizationDetails{" +
                "authorizationType=" + authorizationType +
                ", link=" + link +
                ", continueUrl=" + continueUrl +
                ", orderId=" + orderId +
                ", extOrderId=" + extOrderId +
                ", fallbackLink= " + fallbackLink +
                '}';
    }


    public static class Builder {

        private PaymentAuthorization authorizationType = PaymentAuthorization.PAY_BY_LINK;
        private String link;
        private String continueUrl;
        private String orderId;
        private String extOrderId;
        private String fallbackLink;

        public AuthorizationDetails build() {
            checkArgument(notEmpty(link), "Payment link should be provided");
            return new AuthorizationDetails(authorizationType, link,
                    fromNullable(continueUrl).or(CONTINUE_URL), null, orderId, extOrderId, fallbackLink);
        }


        public Builder withContinueUrl(@NonNull String continueUrl) {
            this.continueUrl = continueUrl;
            return this;
        }

        public Builder withAuthorizationType(@NonNull PaymentAuthorization authorizationType) {
            this.authorizationType = authorizationType;
            return this;
        }

        public Builder withLink(@NonNull String link) {
            this.link = link;
            return this;
        }


        public Builder withOrderId(@NonNull String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withExtOrderId(@NonNull String extOrderId) {
            this.extOrderId = extOrderId;
            return this;
        }

        public Builder withFallbackLink(@NonNull String fallbackLink) {
            this.fallbackLink = fallbackLink;
            return this;
        }
    }
}
