package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Romanian extends StringMapTranslation {

    public Romanian() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Numărul cardului nu trebuie să fie necompletat");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Numărul cardului este incorect");
        add(TranslationKey.INVALID_CVV_ERROR, "Vă rugăm să introduceţi un cod valid");
        add(TranslationKey.INVALID_CVV_EMPTY, "Introduceţi codul CVV");
        add(TranslationKey.CVV_CODE, "Cod CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "LL/AA");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Vă rugăm să introduceţi o dată validă");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Data expirării");
        add(TranslationKey.CARD_NAME, "Nume card");
        add(TranslationKey.CARD_NUMBER, "Număr card");
        add(TranslationKey.SAVE_AND_USE, "Salvare şi folosire");
        add(TranslationKey.USE, "Folosire");
        add(TranslationKey.ENTER_CVV2, "Indicaţi codul CVV pentru cardul dvs.");
        add(TranslationKey.NEW_CARD, "Adăugare card");
        add(TranslationKey.CREDIT_CARD, "Card de credit");
        add(TranslationKey.CANCEL, "Anulare");
        add(TranslationKey.PLEASE_WAIT, "Vă rugăm să aşteptaţi...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Închidere şi revenire");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Da, revin");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Nu, rămân pe pagina de plată");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Plata dvs. va fi anulată. Continuaţi?");
        add(TranslationKey.BANK_TRANSFER, "Transfer bancar");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Se şterge metoda de plată");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Sigur doriţi să ştergeţi metoda de plată?");
        add(TranslationKey.REMOVE, "Ştergere");
        add(TranslationKey.PUBLISHER, "Editor");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Versiunea aplicaţiei");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Selectaţi metoda de plată");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Sunt de acord cu <a href=\"#\">Termenii de plată PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Transfer bancar");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Pe dispozitiv nu există nicio aplicţie care să accepte aceasta");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debit sau credit");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Transfer bancar");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Alegeţi cum plătiţi");
        add(TranslationKey.BLIK_HINT, "Introduceţi codul BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizaţi şi stocaţi plata BLIK în aplicaţia dvs. bancară");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "utilizați codul de la bancă");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Introduceţi noul cod BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "plată cu o apăsare");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "plată BLIK salvată");
        add(TranslationKey.SCAN_CARD, "Scanare card");
        add(TranslationKey.SCAN_FAILED, "Cardul nu poate fi scanat - introduceţi manual detaliile cardului");
        add(TranslationKey.SCAN_CANCELED, "Scanare card anulată");
        add(TranslationKey.SECURE_CHECKOUT, "VERIFICARE ÎN SIGURANŢĂ");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Se verifică plata ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.ROMANIAN;
    }
}
