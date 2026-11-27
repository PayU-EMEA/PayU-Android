# payment-library-google-pay-adapter

This module is part of [PayU Mobile SDK for Android](../README.md). For the requirements, installation and the server side of the integration, see the [main README](../README.md).

To display Google Pay in `payment-library-chooser-module`, follow the [chooser integration](../payment-library-chooser-module/README.md) and make sure Google Pay is included in the payment methods retrieved by your server. Then set the adapter in `Activity#onCreate`:

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...
    googlePayService = new GooglePayService(this);
    paymentChooserWidget.setPaymentMethodsAdapter(new GooglePayAdapter(googlePayService));
    // ...
}
```
