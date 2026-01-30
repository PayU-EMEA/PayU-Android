package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Greek extends StringMapTranslation {

    public Greek() {
        super();
        add(TranslationKey.OK, "ΟΚ");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Το πεδίο «αριθμός κάρτας» δεν μπορεί να είναι κενό");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Ο αριθμός κάρτας είναι εσφαλμένος");
        add(TranslationKey.INVALID_CVV_ERROR, "Παρακαλώ εισάγετε έναν έγκυρο κωδικό");
        add(TranslationKey.INVALID_CVV_EMPTY, "Εισαγωγή κωδικού CVV");
        add(TranslationKey.CVV_CODE, "Κωδικός CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "ΜΜ/ΕΕ");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Παρακαλώ εισάγετε μία έγκυρη ημερομηνία");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Ημερομηνία λήξης");
        add(TranslationKey.CARD_NAME, "Όνομα κάρτας");
        add(TranslationKey.CARD_NUMBER, "Αριθμός κάρτας");
        add(TranslationKey.SAVE_AND_USE, "Αποθήκευση και χρήση");
        add(TranslationKey.USE, "Χρήση");
        add(TranslationKey.ENTER_CVV2, "Παρακαλώ καταχωρίστε τον κωδικό CVV της κάρτας σας");
        add(TranslationKey.NEW_CARD, "Προσθήκη κάρτας");
        add(TranslationKey.CREDIT_CARD, "Πιστωτική κάρτα");
        add(TranslationKey.CANCEL, "Ακύρωση");
        add(TranslationKey.PLEASE_WAIT, "Παρακαλώ περιμένετε…");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Κλείσιμο και επιστροφή");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Ναι, επιστροφή τώρα");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Όχι, παραμονή στη σελίδα πληρωμής");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Η πληρωμή σας θα ακυρωθεί. Θέλετε να συνεχίσετε;");
        add(TranslationKey.BANK_TRANSFER, "Τραπεζική μεταφορά");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Διαγραφή μεθόδου πληρωμής");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Είστε σίγουροι ότι θέλετε να διαγράψετε την επιλεγμένη μέθοδο πληρωμής;");
        add(TranslationKey.REMOVE, "Διαγραφή");
        add(TranslationKey.PUBLISHER, "Εκδότης");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Έκδοση εφαρμογής");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Επιλογή μεθόδου πληρωμής");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Αποδέχομαι τους <a href=\"#\">Όρους πληρωμής PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Τραπεζική μεταφορά");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Σε αυτή τη συσκευή δεν υπάρχει εφαρμογή που να υποστηρίζει αυτή τη λειτουργία");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "χρεωστική ή πιστωτική");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Τραπεζική μεταφορά");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Επιλογή μεθόδου πληρωμής");
        add(TranslationKey.BLIK_HINT, "Εισάγετε κωδικό BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Εγκρίνετε και αποθηκεύστε την πληρωμή BLIK  στην εφαρμογή της τράπεζάς σας");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "χρήση κωδικού από την εφαρμογή της τράπεζας");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Εισάγετε έναν νέο κωδικό BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "πληρωμή με ένα πάτημα");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "αποθηκευμένη πληρωμή BLIK");
        add(TranslationKey.SCAN_CARD, "Σάρωση κάρτας");
        add(TranslationKey.SCAN_FAILED, "Δεν είναι δυνατή η σάρωση της κάρτας, παρακαλώ εισάγετε τα στοιχεία της κάρτας");
        add(TranslationKey.SCAN_CANCELED, "Η σάρωση της κάρτας ακυρώθηκε");
        add(TranslationKey.SECURE_CHECKOUT, "ΑΣΦΑΛΗΣ ΟΛΟΚΛΗΡΩΣΗ");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Επαλήθευση πληρωμής…");
    }

    @Override
    public Locale getLanguage() {
        return Locale.GREEK;
    }
}
