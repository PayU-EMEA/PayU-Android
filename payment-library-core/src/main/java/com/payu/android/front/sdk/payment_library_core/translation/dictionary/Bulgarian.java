package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Bulgarian extends StringMapTranslation {

    public Bulgarian() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Номерът на картата не трябва да е празен");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Номерът на картата е грешен");
        add(TranslationKey.INVALID_CVV_ERROR, "Моля, въведете валиден код");
        add(TranslationKey.INVALID_CVV_EMPTY, "Въведете CVV код");
        add(TranslationKey.CVV_CODE, "CVV код");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "ММ/ГГ");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Моля, въведете валидна дата");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Дата на изтичане на срока");
        add(TranslationKey.CARD_NAME, "Име на картата");
        add(TranslationKey.CARD_NUMBER, "Номер на картата");
        add(TranslationKey.SAVE_AND_USE, "Запазване и използване");
        add(TranslationKey.USE, "Използване");
        add(TranslationKey.ENTER_CVV2, "Посочете CVV код за Вашата карта");
        add(TranslationKey.NEW_CARD, "Добавяне на карта");
        add(TranslationKey.CREDIT_CARD, "Кредитна карта");
        add(TranslationKey.CANCEL, "Отказ");
        add(TranslationKey.PLEASE_WAIT, "Моля, изчакайте...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Затворете и се върнете обратно");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Да, върнете се обратно");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Не, останете на страницата за плащане");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Плащането Ви ще бъде отменено. Продължете?");
        add(TranslationKey.BANK_TRANSFER, "Банков превод");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Изтриване на метод на плащане");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Сигурни ли сте, че искате да премахнете избрания метод на плащане?");
        add(TranslationKey.REMOVE, "Премахване");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Изберете метод на плащане");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Приемам <a href=\"#\">условията за плащане на PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Банков превод");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "В устройството няма приложение, което да поддържа това");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "дебит или кредит");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Банков превод");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Изберете как да платите");
        add(TranslationKey.BLIK_HINT, "Въведете BLIK код");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Одобрете и запазете плащането на BLIK в приложението на Вашата банка");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "използвайте код от приложението на Вашата банка");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Въведете нов BLIK код");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "плащане с едно докосване");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "запазено плащане на BLIK");
        add(TranslationKey.SCAN_CARD, "Сканиране на карта");
        add(TranslationKey.SCAN_FAILED, "Сканирането на картата е неуспешно, въведете данните на картата ръчно");
        add(TranslationKey.SCAN_CANCELED, "Сканирането на картата е отменено");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Проверка на плащането...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.BULGARIAN;
    }
}
