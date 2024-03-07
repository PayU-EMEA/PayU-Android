package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Ukrainian extends StringMapTranslation {

    public Ukrainian() {
        super();
        add(TranslationKey.OK, "Гаразд");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Номер картки не може бути пустим");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Номер картки неправильний");
        add(TranslationKey.INVALID_CVV_ERROR, "Введіть правильний код");
        add(TranslationKey.CVV_CODE, "CVV2/CVC2 код");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/YY");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Введіть правильну дату");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Термін дії");
        add(TranslationKey.CARD_NAME, "Назва картки");
        add(TranslationKey.CARD_NUMBER, "Номер картки");
        add(TranslationKey.SAVE_AND_USE, "Збережіть та використовуйте");
        add(TranslationKey.USE, "Використовуйте");
        add(TranslationKey.ENTER_CVV2, "Введіть CVV2/CVC2 код картки");
        add(TranslationKey.NEW_CARD, "Додати картку");
        add(TranslationKey.CREDIT_CARD, "Платіжна картка");
        add(TranslationKey.CANCEL, "Скасувати");
        add(TranslationKey.PLEASE_WAIT, "Будь ласка, зачекайте...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Закрийте та поверніться");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Так, повертайся");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ні, залишайтеся на сторінці оплати");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Якщо ви не здійснили платіж, ваша транзакція буде скасована. Продовжити?");
        add(TranslationKey.BANK_TRANSFER, "Банківський переказ");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Видалити спосіб оплати");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Ви впевнені, що хочете видалити вибраний спосіб оплати?");
        add(TranslationKey.REMOVE, "Видалити");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Виберіть спосіб оплати");
        add(TranslationKey.INFORMATIONS, "Інформація");
        add(TranslationKey.PUBLISHER, "Видавець");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Версія застосунку");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Я приймаю <a href=\"#\">умови оплати</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Банківський переказ");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "На пристрої немає програми для підтримки");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "Дебетова або кредитна картка");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Банківський переказ");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Виберіть спосіб оплати");
        add(TranslationKey.BLIK_HINT, "Введіть BLIK код");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Затвердити та запам'ятати платіж у банківському додатку");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Використовуйте код з банківського додатку");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "Оплата в один клік");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Введіть новий BLIK код");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "Збережений BLIK платіж");
        add(TranslationKey.SCAN_CARD, "Сканування картки");
        add(TranslationKey.SCAN_FAILED, "Не вдається відсканувати картку – введіть дані картки");
        add(TranslationKey.SCAN_CANCELED, "Сканування картки скасовано");
        add(TranslationKey.SECURE_CHECKOUT, "Безпечна оплата");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Перевіряю оплату...");
        add(TranslationKey.OFFER_INSTALLMENTS_TITLE, "Транзакція завершена");
        add(TranslationKey.OFFER_INSTALLMENTS_HEADER, "Розстрочка");
        add(TranslationKey.OFFER_INSTALLMENTS_SUBTITLE, "Продавець отримає повну суму замовлення.");
        add(TranslationKey.OFFER_INSTALLMENTS_BODY, "Ви можете оплатити замовлення частинами за допомогою Mastercard.");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_ACCEPT, "Оплата частинами");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_NEGATIVE, "Ні, дякую");
        add(TranslationKey.CHOOSE_INSTALLMENTS_SUBTITLE, "Ви можете оплатити замовлення частинами за допомогою Mastercard. Будь ласка, підтвердьте оплату частинами.");
        add(TranslationKey.CHOOSE_INSTALLMENTS_BUTTON_NEGATIVE, "Ні, дякую");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_PLURAL, "Pозстрочка");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_SINGULAR, "Частина");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_MANY, "Розстрочка");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_TOTAL_INSTALLMENTS, "Pазом");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_1ST_INSTALLMENT, "Перший платіж");
    }

    @Override
    public Locale getLanguage() {
        return Locale.UKRAINIAN;
    }
}
