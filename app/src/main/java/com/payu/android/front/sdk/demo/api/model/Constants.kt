package com.payu.android.front.sdk.demo.api.model

object Constants {
    object Buyer {
        const val email = "email@payu.com"
        const val phone = "+48123456789"
        const val firstName = "John"
        const val lastName = "Doe"
        const val language = Constants.Locale.defaultLanguageCode
    }

    object Delivery {
        const val recipientName = "Recipient"
        const val recipientEmail = "Testowy"
        const val recipientPhone = "+48 123 456 789"
        const val addressId = "44"
        const val street = "Grunwaldzka 186"
        const val postalBox = "Poznań"
        const val postalCode = "60-166"
        const val city = "Poznań"
        const val state = "woj. wielkopolskie"
        const val countryCode = Constants.Locale.defaultCountryCode
        const val name = "PayU"
    }

    object Order {
        const val customerIP = "127.0.0.1"
        const val continueUrl = "https://www.payu.com/"
        const val description = "PayU Android SDK"
    }

    object Locale {
        const val defaultCountryCode = "PL"
        const val defaultCurrencyCode = "PLN"
        const val defaultLanguageCode = "pl"
    }
}