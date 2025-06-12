package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Slovenian extends StringMapTranslation {

    public Slovenian() {
        super();
        add(TranslationKey.OK, "V redu");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Št. kartice ne sme biti prazno polje");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Št. kartice ni pravilna");
        add(TranslationKey.INVALID_CVV_ERROR, "Vnesite veljavno kodo");
        add(TranslationKey.CVV_CODE, "Koda CVV2/CVC2");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/LL");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Vnesite veljavni datum");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Datum zapadlosti");
        add(TranslationKey.CARD_NAME, "Ime kartice");
        add(TranslationKey.CARD_NUMBER, "Št. kartice");
        add(TranslationKey.SAVE_AND_USE, "Shrani in uporabi");
        add(TranslationKey.USE, "Uporabi");
        add(TranslationKey.ENTER_CVV2, "Vnesite kodo CVV2/CVC2 za svojo kartico");
        add(TranslationKey.NEW_CARD, "Dodaj kartico");
        add(TranslationKey.CREDIT_CARD, "Kreditna kartica");
        add(TranslationKey.CANCEL, "Prekliči");
        add(TranslationKey.PLEASE_WAIT, "Počakaj, ...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Zapri in nazaj");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Da, pojdi nazaj");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ne, ostani na stran plačila");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Vaše pplačilo bo preklicano. Nadaljuje?");
        add(TranslationKey.BANK_TRANSFER, "Bančni transfer");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Brisanje načina plačila");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Ali res želite odstraniti izbran način plačila?");
        add(TranslationKey.REMOVE, "Odstrani");
        add(TranslationKey.INFORMATIONS, "O...");
        add(TranslationKey.PUBLISHER, "Izdajatelj");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Različica aplikacije");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Izberite način plačila");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Sprejmem plačilne pogoje <a href=\"#\">PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Bančno nakazilo");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Na napravi ni aplik., ki to podpira");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debit ali kredit");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bančni transfer");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Izberite način plačila");
        add(TranslationKey.BLIK_HINT, "Vnesite kodo BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Odobrite in shranite plačilo BLIK v bančno aplik.");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "uporabi kodo iz bančne aplik.");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Vnesite novo kodo BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "plačilo na klik");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "shranjeno plačilo BLIK");
        add(TranslationKey.SCAN_CARD, "Skeniranje kartice");
        add(TranslationKey.SCAN_FAILED, "Ni mogoče skenirati kartice – ročno vnesite podatke o kartici");
        add(TranslationKey.SCAN_CANCELED, "Skeniranje kartice preklicano");
        add(TranslationKey.SECURE_CHECKOUT, "VARNO NA BLAGAJNO");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Preverjanje plačila ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.SLOVENIAN;
    }
}
