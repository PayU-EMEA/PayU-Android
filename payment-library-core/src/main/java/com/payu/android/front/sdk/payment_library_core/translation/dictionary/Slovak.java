package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Slovak extends StringMapTranslation {

    public Slovak() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Číslo karty musí byť uvedené");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Číslo karty nie je správne");
        add(TranslationKey.INVALID_CVV_ERROR, "Prosím zadejte správny kód");
        add(TranslationKey.INVALID_CVV_EMPTY, "Zadajte CVV kód");
        add(TranslationKey.CVV_CODE, "Kód CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/RR");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Prosím zadajte správny dátum");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Dátum platnosti");
        add(TranslationKey.CARD_NAME, "Názov karty");
        add(TranslationKey.CARD_NUMBER, "Číslo karty");
        add(TranslationKey.SAVE_AND_USE, "Uložiť a použiť");
        add(TranslationKey.USE, "Použiť");
        add(TranslationKey.ENTER_CVV2, "Zadejte CVV kód");
        add(TranslationKey.NEW_CARD, "Pridať kartu");
        add(TranslationKey.CREDIT_CARD, "Platobná karta");
        add(TranslationKey.CANCEL, "Zrušiť");
        add(TranslationKey.PLEASE_WAIT, "Čakajte prosím...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Zavrieť a späť");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Áno, späť");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Nie, zostať");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Vaša platba bude zrušená. Pokračovať?");
        add(TranslationKey.BANK_TRANSFER, "Bankový prevod");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Odstránenie platobnej metódy");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Skutočne chcete odstrániť vybranú platobnú metódu?");
        add(TranslationKey.REMOVE, "Odstraniť");
        add(TranslationKey.PUBLISHER, "Vydavateľ");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Verzia aplikácie");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Vyberte platobnú metódu");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Súhlasím s <a href=\"#\">Platobnými obchodnými podmienkami PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");

        add(TranslationKey.PBL_TITLE, "Bankový prevod");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Chýba mobilná aplikácia");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "Debetná alebo kreditná");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "online a tradičný prevod");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Vyberte, ako zaplatiť");
        add(TranslationKey.BLIK_HINT, "Zadajte BLIK kód");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizujte a uložte BLIK platbu v bankovej aplikácii");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Použite kód z bankovej aplikácie");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Zadajte nový BLIK kód");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "Platba jedným dotykom");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "Uloženú platbu BLIK");
        add(TranslationKey.SCAN_CARD, "Skenovať kartu");
        add(TranslationKey.SCAN_FAILED, "Kartu nie je možné naskenovať - zadať údaje o karte ručne");
        add(TranslationKey.SCAN_CANCELED, "Skenovanie karty bolo zrušené");
        add(TranslationKey.SECURE_CHECKOUT, "SECURE CHECKOUT");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Overenie platby ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.SLOVAK;
    }
}
