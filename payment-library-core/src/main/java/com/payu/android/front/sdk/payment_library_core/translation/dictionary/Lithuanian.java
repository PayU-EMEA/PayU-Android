package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Lithuanian extends StringMapTranslation {

    public Lithuanian() {
        super();
        add(TranslationKey.OK, "Gerai");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Kortelės numeris neturi būti tuščias");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Kortelės numeris neteisingas");
        add(TranslationKey.INVALID_CVV_ERROR, "Įveskite galiojantį kodą");
        add(TranslationKey.INVALID_CVV_EMPTY, "Įveskite CVV kodą");
        add(TranslationKey.CVV_CODE, "CVV kodas");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/YY");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Įveskite galiojančią datą");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Galiojimo data");
        add(TranslationKey.CARD_NAME, "Kortelės pavadinimas");
        add(TranslationKey.CARD_NUMBER, "Kortelės numeris");
        add(TranslationKey.SAVE_AND_USE, "Išsaugoti ir naudoti");
        add(TranslationKey.USE, "Naudoti");
        add(TranslationKey.ENTER_CVV2, "Įveskite savo kortelės CVV kodą");
        add(TranslationKey.NEW_CARD, "Pridėti kortelę");
        add(TranslationKey.CREDIT_CARD, "Kredito kortelė");
        add(TranslationKey.CANCEL, "Atšaukti");
        add(TranslationKey.PLEASE_WAIT, "Prašome palaukti...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Uždaryti ir grįžti atgal");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Taip, grįžti atgal");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ne, likti viename mokėjimo puslapyje");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Jūsų mokėjimas bus atšauktas. Tęsti?");
        add(TranslationKey.BANK_TRANSFER, "Banko pavedimas");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Mokėjimo būdo ištrinimas");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Ar tikrai norite pašalinti pasirinktą mokėjimo būdą?");
        add(TranslationKey.REMOVE, "Pašalinti");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Pasirinkite mokėjimo būdą");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Sutinku su <a href=\"#\">PayU mokėjimo sąlygomis</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Banko pavedimas");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Įrenginyje nėra šią funkciją palaikančios programėlės");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debetinė arba kredito");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Banko pavedimas");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Pasirinkite mokėjimo būdą");
        add(TranslationKey.BLIK_HINT, "Įveskite BLIK kodą");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizuokite ir išsaugokite BLIK mokėjimą savo banko programoje");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "naudokite kodą iš savo banko programos");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Įveskite naują BLIK kodą");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "mokėjimas vienu paspaudimu");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "išsaugotas BLIK mokėjimas");
        add(TranslationKey.SCAN_CARD, "Kortelės nuskaitymas");
        add(TranslationKey.SCAN_FAILED, "Nepavyko nuskaityti kortelės – įveskite kortelės duomenis rankiniu būdu");
        add(TranslationKey.SCAN_CANCELED, "Kortelės nuskaitymas atšauktas");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Tikrinamas mokėjimas...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.LITHUANIAN;
    }
}
