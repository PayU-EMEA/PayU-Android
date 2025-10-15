package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Hungarian extends StringMapTranslation {

    public Hungarian() {
        super();
        add(TranslationKey.OK, "Oké");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Írd be a bankkártyád számát!");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Érvénytelen bankkártya-szám");
        add(TranslationKey.INVALID_CVV_ERROR, "Hibás kódot írtál be");
        add(TranslationKey.CVV_CODE, "CVV2/CVC2 kód");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "HH/ÉÉ");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Valami nem stimmel a dátummal");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Lejárati dátum");
        add(TranslationKey.CARD_NAME, "A kártya neve");
        add(TranslationKey.CARD_NUMBER, "Kártyaszám");
        add(TranslationKey.SAVE_AND_USE, "Mentem és ezt használom");
        add(TranslationKey.USE, "Ezt használom");
        add(TranslationKey.ENTER_CVV2, "Írd be a kártyád CVV2/CVC2 kódját");
        add(TranslationKey.NEW_CARD, "Bankkártya hozzáadása");
        add(TranslationKey.CREDIT_CARD, "Hitelkártya");
        add(TranslationKey.CANCEL, "Mégsem");
        add(TranslationKey.PLEASE_WAIT, "Kérlek, várj egy kicsit...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Bezárom és visszamegyek");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Igen, megszakítom");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Nem, maradok a fizetési oldalon");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "A fizetési folyamat megszakad. Biztosan megszakítod?");
        add(TranslationKey.BANK_TRANSFER, "Banki átutalás");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Törlöm ezt a fizetési módot");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Biztosan törlöd ezt a fizetési módot?");
        add(TranslationKey.REMOVE, "Törlöm");
        add(TranslationKey.PUBLISHER, "Fejlesztő");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Alkalmazás verzió");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Válassz fizetési módot!");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Elfogadom a következőt: <a href=\"#\">PayU Payment Terms</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Banki átutalás");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "A készülékeden nincs olyan app, ami meg tudná ezt jeleníteni.");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "Bankkártya vagy hitelkártya");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Banki átutalás");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Hogyan szeretnél fizetni?");
        add(TranslationKey.BLIK_HINT, "Írd be a BLIK kódot");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Érvényesítem és elmentem a BLIK kódot a banki appomba");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Használd a kódot a banki appodból");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "<a href=\"#\">Írj be új BLIK kódot</a>");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "Egytapintásos fizetés");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "A BLIK fizetés elmentve");
        add(TranslationKey.SCAN_CARD, "Kártya-beolvasás");
        add(TranslationKey.SCAN_FAILED, "Nem sikerült beolvasni – írd be a kártyaszámot");
        add(TranslationKey.SCAN_CANCELED, "Kártya-beolvasás megszakítva");
        add(TranslationKey.SECURE_CHECKOUT, "SECURE CHECKOUT");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Fizetés ellenőrzése ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.HUNGARIAN;
    }
}
