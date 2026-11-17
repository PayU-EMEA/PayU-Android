# payment-library-google-pay-module

This module is part of [PayU Mobile SDK for Android](../README.md). For the requirements, installation and the server side of the integration, see the [main README](../README.md).

Google Pay needs two modules:

- `payment-library-google-pay-module` – creates the Google Pay token.
- [`payment-library-google-pay-adapter`](../payment-library-google-pay-adapter/README.md) – adds Google Pay to the payment methods displayed by [`payment-library-chooser-module`](../payment-library-chooser-module/README.md). It cannot be used without the chooser and the Google Pay module.

Google Pay must be enabled on your POS. To enable it, contact your PayU account manager.

Google Pay requires Google Play services 11.4 or later and a Google account on the device.

`GooglePayService` is the entry point for Google Pay. The integration has three steps:

1. [Check whether Google Pay is available on the device](#checking-whether-google-pay-is-available).
2. [Request the Google Pay payment](#requesting-the-google-pay-payment).
3. [Get the Google Pay token from the result](#getting-the-google-pay-token).

To test Google Pay, sign in on the test device to a Google account with at least one real card added to Google Pay, and select that card during the payment. In the `sandbox` environment, Google Pay returns a test token without real card data, so the card is never charged.

## Checking Whether Google Pay Is Available

Display Google Pay only if it is available on the device. To check this, call `GooglePayService#isReadyToPay` with a `GooglePayVerificationListener`:

```java
public interface GooglePayVerificationListener {
    /**
     * Called when the Google Pay status has been obtained.
     * Offer Google Pay only if verificationStatus is GooglePayVerificationStatus.SUCCESS.
     */
    void onVerificationCompleted(GooglePayVerificationStatus verificationStatus);

    /**
     * Called when the connection to the Google API failed.
     */
    void onException(@NonNull Exception exception);
}
```

Possible statuses (`GooglePayVerificationStatus`):

- `SUCCESS` – Google Pay is available.
- `ERROR_API_VERSION` – the Android version is too low.
- `ERROR_GOOGLE_PLAY_SERVICES_VERSION` – the Google Play services version is too low; the buyer must update it.
- `ERROR_GOOGLE_PLAY_SERVICES_UNAVAILABLE` – Google Play services are not available; the buyer must install them.
- `ERROR_UNKNOWN` – an unknown error prevents the Google Pay payment.

For the device requirements, see the [Google Pay documentation](https://developers.google.com/pay/api/android/overview).

## Requesting the Google Pay Payment

Create the cart with `Cart.Builder`. The price is an integer in the smallest currency unit. For the supported currencies, see the `Currency` enum.

```java
private Cart createCart() {
    return new Cart.Builder()
        .withTotalPrice(1000) // 10.00
        .withCurrency(Currency.PLN)
        .build();
}
```

Call `GooglePayService#requestGooglePayCard()` with the cart and your POS ID:

```java
googlePayService.requestGooglePayCard(createCart(), "YOUR_POS_ID");
```

The method has a variant with a third parameter, `shouldCardPickerBeDisplayed` (default `true`), which specifies whether Google Pay displays the card selection UI. For details, see the Google [PaymentDataRequest.Builder](https://developers.google.com/android/reference/com/google/android/gms/wallet/PaymentDataRequest.Builder) documentation.

## Getting the Google Pay Token

The result is passed to `Activity#onActivityResult()` with the `GooglePayService.REQUEST_CODE_GOOGLE_PAY_PAYMENT` request code:

| resultCode | Description |
|---|---|
| `Activity.RESULT_OK` | The buyer selected a card. Pass the intent to `GooglePayService#handleGooglePayResultData`. |
| `Activity.RESULT_CANCELED` | The buyer canceled the Google Pay payment. |
| `GooglePayService.RESULT_ERROR` | An error occurred. Get the details with `GooglePayService#handleGooglePayErrorStatus`. |

```java
if (requestCode == GooglePayService.REQUEST_CODE_GOOGLE_PAY_PAYMENT) {
    if (resultCode == Activity.RESULT_OK) {
        GooglePayTokenResponse googlePayTokenResponse = googlePayService.handleGooglePayResultData(data);
    } else if (resultCode == Activity.RESULT_CANCELED) {
        // The buyer canceled the payment.
    } else if (resultCode == GooglePayService.RESULT_ERROR) {
        ErrorStatus status = googlePayService.handleGooglePayErrorStatus(data);
    }
}
```

`GooglePayTokenResponse` contains the encoded Google Pay token. Pass it to your server, which includes it in the [order](https://developers.payu.com/europe/docs/payment-solutions/cards/digital-wallets/google-pay/#google-pay-order). Do not encode the token from the SDK in Base64 again.

If the order response requires 3DS, handle it with [`payment-library-webview-module`](../payment-library-webview-module/README.md).

`ErrorStatus` contains the Google Pay status code. For the possible codes, see [CommonStatusCodes](https://developers.google.com/android/reference/com/google/android/gms/common/api/CommonStatusCodes).

## Going Live with Google Pay

Google Pay does not work in production without additional configuration:

- Google Pay must be enabled on your production POS. Contact your PayU account manager.
- Your app must be approved by Google. See the [Google Pay integration checklist](https://developers.google.com/pay/api/android/guides/test-and-deploy/integration-checklist).
- In the Google Pay configuration, the gateway is PayU, and the gateway merchant ID is your POS ID.

If Google Pay shows an error that the merchant does not support Google Pay (in Polish: "Realizacja żądania nie powiodła się. Ten sprzedawca nie obsługuje GooglePay"), the app uses the production environment but has not been approved by Google. Contact Google support.
