package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class English extends StringMapTranslation {

    public English() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Card number should not be empty");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Card number is incorrect");
        add(TranslationKey.INVALID_CVV_ERROR, "Please enter a valid code");
        add(TranslationKey.CVV_CODE, "CVV2/CVC2 code");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/YY");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Please enter a valid date");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Expiration date");
        add(TranslationKey.CARD_NAME, "Card name");
        add(TranslationKey.CARD_NUMBER, "Card number");
        add(TranslationKey.SAVE_AND_USE, "Save and use");
        add(TranslationKey.USE, "Use");
        add(TranslationKey.ENTER_CVV2, "Provide CVV2/CVC2 code for your card");
        add(TranslationKey.NEW_CARD, "Add card");
        add(TranslationKey.CREDIT_CARD, "Credit card");
        add(TranslationKey.CANCEL, "Cancel");
        add(TranslationKey.PLEASE_WAIT, "Please wait...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Close and go back");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Yes, go back");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "No, stay on one payment page");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Your payment will be canceled. Continue?");
        add(TranslationKey.BANK_TRANSFER, "Bank transfer");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Deleting payment method");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Are you sure to remove selected payment method?");
        add(TranslationKey.REMOVE, "Remove");
        add(TranslationKey.INFORMATIONS, "About");
        add(TranslationKey.PUBLISHER, "Publisher");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Application version");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Select payment method");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "I accept <a href=\"#\">PayU Payment Terms</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Bank transfer");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "On the device there is no app to support this");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debit or credit");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bank transfer");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Choose how to pay");
        add(TranslationKey.BLIK_HINT, "Enter BLIK code");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Authorize and store BLIK payment in your bank app");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "use code from your bank app");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Enter new BLIK code");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "one-tap payment");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "saved BLIK payment");
        add(TranslationKey.SCAN_CARD, "Card Scanning");
        add(TranslationKey.SCAN_FAILED, "Unable to scan card – enter card details manually");
        add(TranslationKey.SCAN_CANCELED, "Card scanning cancelled");
        add(TranslationKey.SECURE_CHECKOUT, "SECURE CHECKOUT");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Verifying payment ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.ENGLISH;
    }
}
