package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Czech extends StringMapTranslation {

    public Czech() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Číslo karty musí být uvedeno");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Číslo karty není správné");
        add(TranslationKey.INVALID_CVV_ERROR, "Prosím zadejte správný kód");
        add(TranslationKey.INVALID_CVV_EMPTY, "Zadejte CVV kód");
        add(TranslationKey.CVV_CODE, "Kód CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/RR");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Prosím zadejte správné datum");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Datum platnosti");
        add(TranslationKey.CARD_NAME, "Název karty");
        add(TranslationKey.CARD_NUMBER, "Číslo karty");
        add(TranslationKey.SAVE_AND_USE, "Uložit a použít");
        add(TranslationKey.USE, "Použít");
        add(TranslationKey.ENTER_CVV2, "Zadejte CVV kód");
        add(TranslationKey.NEW_CARD, "Přidat kartu");
        add(TranslationKey.CREDIT_CARD, "Platební karta");
        add(TranslationKey.CANCEL, "Zrušit");
        add(TranslationKey.PLEASE_WAIT, "Čekejte prosím...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Vaše platba bude zrušena. Pokračovat?");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Zavřít a zpět");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Ano, zpět");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ne, zůstat");
        add(TranslationKey.BANK_TRANSFER, "Bankovní převod");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Odstranění platební metody");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Skutečně chcete odstranit vybranou platební metodu?");
        add(TranslationKey.REMOVE, "Odstranit");
        add(TranslationKey.PUBLISHER, "Vydavatel");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Verze aplikace");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Vyberte platební metodu");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Souhlasím s <a href=\"#\">Platebnimi obchodními podmínkami PayU</a>");
        add(TranslationKey.COMPLIANCE_URL,
                "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_cs.pdf");
        add(TranslationKey.PBL_TITLE, "Bankovní převod");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Chybí mobilní aplikace");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "Debetní nebo kreditní");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bankovní převod");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Vyberte, jak zaplatit");
        add(TranslationKey.BLIK_HINT, "Zadejte BLIK kód");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizujte a uložte BLIK platbu v bankovní aplikaci");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Použijte kód z bankovní aplikace");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Zadejte nový BLIK kód");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "Platba jedním dotykem");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "Uloženou platbu BLIK");
        add(TranslationKey.SCAN_CARD, "Skenovat kartu");
        add(TranslationKey.SCAN_FAILED, "Kartu nelze naskenovat - zadat údaje o kartě ručně");
        add(TranslationKey.SCAN_CANCELED, "Skenování karty bylo zrušeno");
        add(TranslationKey.SECURE_CHECKOUT, "SECURE CHECKOUT");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Ověření platby ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.CZECH;
    }
}
