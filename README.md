# PayU Mobile SDK for Android

Mobile SDK for Android lets buyers pay in your app with pay-by-link, cards, BLIK and Google Pay. The SDK displays the payment methods, tokenizes cards and handles redirects, 3DS and the CVV code. Card data goes directly to PayU and never passes through your server.

The SDK does not create orders. Your server authenticates with PayU, retrieves payment methods and creates orders, and your app passes the results to the SDK. The server side is the same for Android, iOS and Flutter and is described in the PayU Developer Guide:

- [Mobile SDK](https://developers.payu.com/europe/docs/mobile-sdk/) – overview and supported features.
- [Payment Flow](https://developers.payu.com/europe/docs/mobile-sdk/payment-flow/) – how your app, your server, the SDK and PayU work together.
- [Server Integration](https://developers.payu.com/europe/docs/mobile-sdk/server-integration/) – the requests your server sends to PayU.
- [Testing](https://developers.payu.com/europe/docs/mobile-sdk/testing/) – testing in the Sandbox environment.

## Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Modules](#modules)
- [Languages](#languages)
- [Example App](#example-app)
- [For Maintainers](#for-maintainers)

## Requirements

- Android 5.0 (API level 21) or later.
- A PayU POS for production and a POS in the [Sandbox](https://developers.payu.com/europe/docs/testing/sandbox/) environment for testing.

## Installation

Mobile SDK releases are available in PayU's [JFrog Artifactory](https://payu.jfrog.io/ui/native/mobile-sdk-gradle-local/). Add the repository to your Gradle configuration:

```groovy
maven { url "https://payu.jfrog.io/payu/mobile-sdk-gradle-local" }
```

Then add the [modules](#modules) you need to the `app/build.gradle` file:

```groovy
dependencies {
    implementation "com.payu.android.front.sdk:{MODULE_NAME}:{VERSION}"
}
```

The current version and the changes in each release are listed in the [CHANGELOG](CHANGELOG.md).

### Alpha Builds

If you received an alpha build as an archive, extract it into your local Maven repository and add `mavenLocal()` to your repositories:

```groovy
allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}
```

## Modules

| Module | Description |
|---|---|
| [`payment-library-chooser-module`](payment-library-chooser-module/README.md) | Displays a widget in which the buyer selects a payment method: pay-by-link, card, Google Pay or BLIK. It includes the add card form. |
| [`payment-add-card-module`](payment-add-card-module/README.md) | Provides the card form and tokenizes the card in PayU. It also displays the CVV view when the order response requires the CVV code. |
| [`payment-library-webview-module`](payment-library-webview-module/README.md) | Handles pay-by-link, PEX (Pay By External) and 3DS payments in a WebView, so the buyer does not leave your app. Includes 3DS Soft Accept. |
| [`payment-library-card-scanner`](payment-library-card-scanner/README.md) | Lets the buyer scan the card with the device camera. |
| [`payment-library-google-pay-module`](payment-library-google-pay-module/README.md) | Creates the Google Pay token that you pass to PayU in the order. |
| [`payment-library-google-pay-adapter`](payment-library-google-pay-adapter/README.md) | Adds Google Pay to the payment methods displayed by `payment-library-chooser-module`. |

All modules share the configuration file and the visual customization described in [`payment-library-core-android`](payment-library-core-android/README.md):

- [Configuration](payment-library-core-android/README.md#configuration) – the `payu.xml` file.
- [Visual Customization](payment-library-core-android/README.md#visual-customization) – colors, fonts and icons of the SDK screens.

## Languages

The SDK is available in: Bulgarian, Croatian, Czech, English, French, German, Greek, Hungarian, Lithuanian, Polish, Romanian, Russian, Slovak, Slovenian, Spanish and Ukrainian.

## Example App

The repository contains an example app (`app`) that shows the integration.

1. In `app/src/main/res/values/payu.xml`, set the environment to `production` or `sandbox`:

   ```xml
   <string name="payu_environment">sandbox</string>
   ```

2. Add your POS ID and secret in `com.payu.android.front.sdk.demo.repository.PersistentRepository`. The example app stores the secret only to simplify testing. In your app, your server keeps the secret and gets OAuth tokens.

The app is configured for the Sandbox environment. For the public Sandbox POS, see [Sandbox](https://developers.payu.com/europe/docs/testing/sandbox/#sandbox-public-pos).

## For Maintainers

### Releasing a Version

1. Update `project.ext.versionName` in [build.gradle](build.gradle).
2. Update [CHANGELOG.md](CHANGELOG.md).
3. Create a pull request named `Release: ${project.ext.versionName}`.
4. Squash and merge. The release is triggered by a commit to `main` with a message starting with `Release: `.

If a release changes the requirements, the `payu.xml` keys or a public API described in the documentation, update this README or the README of the affected module in the same pull request.
