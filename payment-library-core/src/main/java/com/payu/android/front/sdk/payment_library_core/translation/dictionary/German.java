package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class German extends StringMapTranslation {

    public German() {
        super();
        add(TranslationKey.OK, "O.K.");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Gib die Kartennummer an");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Die Kartennummer ist ungültig");
        add(TranslationKey.INVALID_CVV_ERROR, "Geben Sie der richtige Code");
        add(TranslationKey.INVALID_CVV_EMPTY, "CVV-Code eingeben");
        add(TranslationKey.CVV_CODE, "CVV-Code");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/JJ");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Geben Sie das richtige Datum");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Gültigkeitsdatum");
        add(TranslationKey.CARD_NAME, "Bezeichnung der Karte");
        add(TranslationKey.CARD_NUMBER, "Kartennummer");
        add(TranslationKey.SAVE_AND_USE, "Speichern und verwenden");
        add(TranslationKey.USE, "Verwenden");
        add(TranslationKey.ENTER_CVV2, "Gib den CVV-Code für die Karte ein");
        add(TranslationKey.NEW_CARD, "Karte hinzufügen");
        add(TranslationKey.CREDIT_CARD, "Zahlungskarte");
        add(TranslationKey.CANCEL, "Abbrechen");
        add(TranslationKey.PLEASE_WAIT, "Bitte warten...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Schließen und zurück");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Ja, zurück");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Nein, bleiben");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Deine Zahlung wird abgebrochen. Fortfahren?");
        add(TranslationKey.BANK_TRANSFER, "Banküberweisung");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Löschen der Zahlungsart");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Möchtest du die ausgewählte Zahlungsart wirklich löschen?");
        add(TranslationKey.REMOVE, "Löschen");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Zahlungsart auswählen");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Ich stimme <a href=\"#\">den Geschäftsbedingungen PayU zu</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Banküberweisung");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Mobile App fehlend");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "Kredit- oder Debitkarte");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Banküberweisung");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Wählen Sie, wie Sie bezahlen möchten");
        add(TranslationKey.BLIK_HINT, "Geben Sie die BLIK-Code ein");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorisieren und speichern Sie BLIK-Zahlung in Ihrer Bank-App");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Verwenden Sie den Code aus Ihrer Bank-App");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "Gesparte BLIK-Zahlung");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Geben Sie den neuen BLIK-Code ein");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "One-Tap-Zahlung");
        add(TranslationKey.SCAN_CARD, "Karte scannen");
        add(TranslationKey.SCAN_FAILED, "Karte kann nicht gescannt werden - geben Sie die Kartendetails manuell ein");
        add(TranslationKey.SCAN_CANCELED, "Scannen der Karte abgebrochen");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Zahlung überprüfen ...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.GERMAN;
    }
}
