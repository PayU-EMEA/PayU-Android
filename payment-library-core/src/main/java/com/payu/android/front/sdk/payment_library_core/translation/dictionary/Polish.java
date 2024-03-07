package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Polish extends StringMapTranslation {

    public Polish() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Numer karty nie może być pusty");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Numer karty jest niepoprawny");
        add(TranslationKey.INVALID_CVV_ERROR, "Podaj poprawny kod");//TODO translation
        add(TranslationKey.CVV_CODE, "Kod CVV2/CVC2");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/RR");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Podaj poprawną datę");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Data ważności");
        add(TranslationKey.CARD_NAME, "Nazwa karty");
        add(TranslationKey.CARD_NUMBER, "Numer karty");
        add(TranslationKey.SAVE_AND_USE, "Zapisz i użyj");
        add(TranslationKey.USE, "Użyj");
        add(TranslationKey.ENTER_CVV2, "Wprowadź kod CVV2/CVC2 dla karty");
        add(TranslationKey.NEW_CARD, "Dodaj kartę");
        add(TranslationKey.CREDIT_CARD, "Karta płatnicza");
        add(TranslationKey.CANCEL, "Anuluj");
        add(TranslationKey.PLEASE_WAIT, "Proszę czekać...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Powrót do sklepu");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Tak, wróć do sklepu");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Nie, zostań na stronie płatności");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Za chwilę przejdziesz z powrotem do sklepu. Jeśli nie dokonałeś płatności, Twoja transakcja zostanie anulowana. Kontynuować?");
        add(TranslationKey.BANK_TRANSFER, "Przelew bankowy");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Usuwanie metody płatności");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Czy na pewno chcesz usunąć wybraną metodę płatności?");
        add(TranslationKey.REMOVE, "Usuń");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Wybierz metodę płatności");
        add(TranslationKey.INFORMATIONS, "Informacje");
        add(TranslationKey.PUBLISHER, "Wydawca");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Wersja aplikacji");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Akceptuję <a href=\"#\">Zasady płatności PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_pl.pdf");
        add(TranslationKey.PBL_TITLE, "Płatność przelewem");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Przykro nam, ale na urządzeniu nie znajduje się aplikacja obsługującą tą akcję.");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debetowa lub kredytowa");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "przelew online i tradycyjny");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Wybierz zapisaną płatność");
        add(TranslationKey.BLIK_HINT, "Wpisz kod BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Zatwierdź i zapamiętaj płatność w aplikacji banku");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "płatność kodem z aplikacji banku");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "płatność jednym dotknięciem");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "<a href=\"#\">Chcę wpisać nowy kod BLIK</a>");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "zapisana płatność BLIK");
        add(TranslationKey.SCAN_CARD, "Skanuj kartę");
        add(TranslationKey.SCAN_FAILED, "Skanowanie karty nie powiodło się - wprowadź dane przez formularz");
        add(TranslationKey.SCAN_CANCELED, "Skanowanie karty zostało anulowane");
        add(TranslationKey.SECURE_CHECKOUT, "SECURE CHECKOUT");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Weryfikuję płatność...");
        add(TranslationKey.OFFER_INSTALLMENTS_TITLE, "Transakcja zaakceptowana");
        add(TranslationKey.OFFER_INSTALLMENTS_HEADER, "Spłata na raty");
        add(TranslationKey.OFFER_INSTALLMENTS_SUBTITLE, "Sprzedawca otrzyma pełną kwotę zamówienia.");
        add(TranslationKey.OFFER_INSTALLMENTS_BODY, "Możesz skorzystać z usługi Mastercard Płać w ratach i rozłożyć tę płatność na raty.");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_ACCEPT, "Rozkładam na raty");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_NEGATIVE, "Nie, dziękuję");
        add(TranslationKey.CHOOSE_INSTALLMENTS_SUBTITLE, "Swoją płatność kartą Mastercard możesz rozłożyć na raty. Potwierdź wybór rozłożenia zakupu na raty.");
        add(TranslationKey.CHOOSE_INSTALLMENTS_BUTTON_NEGATIVE, "Nie, dziękuję");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_PLURAL, "raty");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_SINGULAR, "rata");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_MANY, "rat");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_TOTAL_INSTALLMENTS, "łącznie");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_1ST_INSTALLMENT, "1. rata");
    }

    @Override
    public Locale getLanguage() {
        return Locale.POLISH;
    }
}
