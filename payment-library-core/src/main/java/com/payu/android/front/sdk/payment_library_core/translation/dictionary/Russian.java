package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Russian extends StringMapTranslation {

    public Russian() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Поле с номером карты не должно быть пустым");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Неправильный номер карты");
        add(TranslationKey.INVALID_CVV_ERROR, "Введите действительный код");
        add(TranslationKey.INVALID_CVV_EMPTY, "Введите код CVV");
        add(TranslationKey.CVV_CODE, "Код CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "ММ/ГГ");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Введите действительную дату");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Срок действия");
        add(TranslationKey.CARD_NAME, "Имя владельца карты");
        add(TranslationKey.CARD_NUMBER, "Номер карты");
        add(TranslationKey.SAVE_AND_USE, "Сохранить и использовать");
        add(TranslationKey.USE, "Использовать");
        add(TranslationKey.ENTER_CVV2, "Укажите код CVV карты");
        add(TranslationKey.NEW_CARD, "Добавить карту");
        add(TranslationKey.CREDIT_CARD, "Кредитная карта");
        add(TranslationKey.CANCEL, "Отменить");
        add(TranslationKey.PLEASE_WAIT, "Подождите...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Закрыть и вернуться");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Да, вернуться");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Нет, остаться на странице платежа");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Ваш платеж будет отменен. Продолжить?");
        add(TranslationKey.BANK_TRANSFER, "Банковский перевод");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Удаление способа оплаты");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Действительно удалить выбранный способ оплаты?");
        add(TranslationKey.REMOVE, "Удалить");
        add(TranslationKey.PUBLISHER, "Орган, выдавший карту");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Версия приложения");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Выбрать способ оплаты");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Я принимаю <a href=\"#\">условия оплаты PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Банковский перевод");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "На устройстве не установлено приложение, поддерживающее это действие");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "дебетовая или кредитная");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Банковский перевод");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Выберите способ оплаты");
        add(TranslationKey.BLIK_HINT, "Введите код BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Авторизировать и сохранить платеж BLIK в банковском приложении");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "использовать код из банковского приложения");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Введите новый код BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "оплата одним нажатием");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "сохраненный платеж BLIK");
        add(TranslationKey.SCAN_CARD, "Сканирование карты");
        add(TranslationKey.SCAN_FAILED, "Невозможно отсканировать карту‚Äì введите данные карты вручную");
        add(TranslationKey.SCAN_CANCELED, "Сканирование карты отменено");
        add(TranslationKey.SECURE_CHECKOUT, "БЕЗОПАСНАЯ ОПЛАТА");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Проверка платежа...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.RUSSIAN;
    }
}
