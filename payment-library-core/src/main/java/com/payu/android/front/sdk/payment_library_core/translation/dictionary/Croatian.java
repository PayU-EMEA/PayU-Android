package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Croatian extends StringMapTranslation {

    public Croatian() {
        super();
        add(TranslationKey.OK, "U redu");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Broj kartice ne smije biti prazan");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Broj kartice nije točan");
        add(TranslationKey.INVALID_CVV_ERROR, "Unesite valjani kod");
        add(TranslationKey.INVALID_CVV_EMPTY, "Unesite CVV kod");
        add(TranslationKey.CVV_CODE, "CVV kod");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/GG");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Unesite valjani datum");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Datum isteka roka trajanja");
        add(TranslationKey.CARD_NAME, "Naziv kartice");
        add(TranslationKey.CARD_NUMBER, "Broj kartice");
        add(TranslationKey.SAVE_AND_USE, "Spremi i koristi");
        add(TranslationKey.USE, "Koristiti");
        add(TranslationKey.ENTER_CVV2, "Navedite CVV kod za svoju karticu");
        add(TranslationKey.NEW_CARD, "Dodaj karticu");
        add(TranslationKey.CREDIT_CARD, "Kreditna kartica");
        add(TranslationKey.CANCEL, "Otkazati");
        add(TranslationKey.PLEASE_WAIT, "Pričekajte...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Zatvori i vrati se");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Da, vrati se");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ne, ostani na jednoj stranici za plaćanje");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Vaše plaćanje bit će poništeno. Nastaviti?");
        add(TranslationKey.BANK_TRANSFER, "Bankovni transfer");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Brisanje načina plaćanja");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Jeste li sigurni da želite ukloniti odabrani način plaćanja?");
        add(TranslationKey.REMOVE, "Ukloniti");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Odaberi način plaćanja");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Prihvaćam <a href=\"#\">PayU uvjete plaćanja</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Bankovni transfer");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Na uređaju ne postoji aplikacija koja to podržava");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debitno ili kreditno");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bankovni transfer");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Odaberi način plaćanja");
        add(TranslationKey.BLIK_HINT, "Unesite BLIK kod");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizirajte i pohranite BLIK plaćanje u svojoj bankovnoj aplikaciji");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "upotrijebi kod iz aplikacije svoje banke");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Unesite novi BLIK kod");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "plaćanje jednim dodirom");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "spremljeno BLIK plaćanje");
        add(TranslationKey.SCAN_CARD, "Skeniranje kartice");
        add(TranslationKey.SCAN_FAILED, "Ne mogu skenirati karticu,Äì unesite podatke o kartici ručno");
        add(TranslationKey.SCAN_CANCELED, "Skeniranje kartice otkazano");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Provjera plaćanja...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.CROATIAN;
    }
}
