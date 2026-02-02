package com.payu.android.front.sdk.demo.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory

private const val LANG_AUTO = 0
private const val LANG_PL = 1
private const val LANG_EN = 2
private const val LANG_CS = 3

const val LANGUAGE_DIALOG_TAG = "LanguageDialog"

class ChangeLanguageDialog : DialogFragment() {

    companion object {
        fun showLanguageDialog(fragmentManager: FragmentManager) {
            ChangeLanguageDialog().apply {
                show(fragmentManager, LANGUAGE_DIALOG_TAG)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val languages = arrayOf(
            resources.getString(R.string.choose_language_auto),
            resources.getString(R.string.choose_language_pl),
            resources.getString(R.string.choose_language_en),
            resources.getString(R.string.choose_language_cs)
        )
        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.choose_language_title)
                .setItems(languages) { _, which ->
                    when (which) {
                        LANG_AUTO -> TranslationFactory.forceTranslation(Locale.AUTO)
                        LANG_PL -> TranslationFactory.forceTranslation(Locale.POLISH)
                        LANG_EN -> TranslationFactory.forceTranslation(Locale.ENGLISH)
                        LANG_CS -> TranslationFactory.forceTranslation(Locale.CZECH)
                    }
                    dismiss()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}