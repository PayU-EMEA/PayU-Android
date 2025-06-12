package com.payu.android.front.sdk.demo.repository

import android.content.Context

private const val SETTINGS_NAME = "appSettings"
private const val SETTINGS_POSID_KEY = "posid"
private const val SETTINGS_CLIENT_SECRET_KEY = "client_secret"
private const val SETTINGS_SAVE_CREDENTIALS_KEY = "save_credentials"

/**
 * Production:
 * POS - 277470
 * CLIENT_SECRET - 6bc70c6a2506de15554460f94e2ae2e9
 */
private const val POS_ID_DEFAULT_VALUE = "385627"
private const val CLIENT_SECRET_DEFAULT_VALUE = "8fe2195693e8e2d108aa2d92132a7283"
private const val CLIENT_EMAIL_DEFAULT_VALUE = "test@test.test"

private const val ORDER_ID = "order_id"

private const val SETTINGS_CLIENT_EMAIL_KEY = "email"

class PersistentRepository(private val context: Context) {

    private fun getSettings() = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
    var saveCredentials: Boolean
        get() = getSettings().getBoolean(SETTINGS_SAVE_CREDENTIALS_KEY, false)
        set(value) {
            getSettings().edit().putBoolean(SETTINGS_SAVE_CREDENTIALS_KEY, value).apply()
        }

    var posid: String
        get() = getSettings().getString(SETTINGS_POSID_KEY, POS_ID_DEFAULT_VALUE)
            ?.ifEmpty { POS_ID_DEFAULT_VALUE }
            ?: POS_ID_DEFAULT_VALUE
        set(value) {
            getSettings().edit().putString(SETTINGS_POSID_KEY, value).apply()
        }

    var clientSecret
        get() = getSettings().getString(SETTINGS_CLIENT_SECRET_KEY, CLIENT_SECRET_DEFAULT_VALUE)
            ?.ifEmpty { CLIENT_SECRET_DEFAULT_VALUE }
            ?: CLIENT_SECRET_DEFAULT_VALUE
        set(value) {
            getSettings().edit().putString(SETTINGS_CLIENT_SECRET_KEY, value).apply()
        }

    var email
        get() = getSettings().getString(SETTINGS_CLIENT_EMAIL_KEY, CLIENT_EMAIL_DEFAULT_VALUE)
                ?.ifEmpty { CLIENT_EMAIL_DEFAULT_VALUE }
                ?: CLIENT_EMAIL_DEFAULT_VALUE
        set(value) {
            getSettings().edit().putString(SETTINGS_CLIENT_EMAIL_KEY, value).apply()
        }
    var orderId
        get() = getSettings().getString(ORDER_ID, "")?.ifEmpty { "" } ?: ""
        set(value) {
            getSettings().edit().putString(ORDER_ID, value).apply()
        }
}
