# payment-library-card-scanner

This module is part of [PayU Mobile SDK for Android](../README.md). For the requirements, installation and the server side of the integration, see the [main README](../README.md).

The card scanner lets the payer add a card by scanning it with the camera in `CreateAndSelectCardActivity` (the add card view). It reads only the front of the card: the card number and the expiration date. PayU uses a third-party card scanner.

To enable it, add the `payment-library-card-scanner` module to your project (in the same version as the other modules) and set the key in `payu.xml`:

```groovy
implementation "com.payu.android.front.sdk:payment-library-card-scanner:{VERSION}"
```

```xml
<bool name="payu_card_scanner">true</bool>
```

> [!NOTE]
> The card scanner needs the camera permission:
> - You must add a link to the terms and conditions in the Google Play Store.
> - If your app is already published without this permission, on older Android versions users must update the app manually to accept the new permission.
