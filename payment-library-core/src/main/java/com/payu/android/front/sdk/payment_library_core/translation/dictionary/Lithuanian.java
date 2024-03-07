package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Lithuanian extends StringMapTranslation {

    public Lithuanian() {
        super();
        add(TranslationKey.OK, "Labi");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Kartes numura lauks nedrīkst būt tukšs");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Kartes numurs ir nepareizs");
        add(TranslationKey.INVALID_CVV_ERROR, "Lūdzu, ievadiet derīgu kodu");
        add(TranslationKey.CVV_CODE, "CVV2/CVC2 kods");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/GG");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Lūdzu, ievadiet derīgu datumu");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Derīguma termiņš");
        add(TranslationKey.CARD_NAME, "Kortelės pavadinimas");
        add(TranslationKey.CARD_NUMBER, "Kartes numurs");
        add(TranslationKey.SAVE_AND_USE, "Saglabāt un lietot");
        add(TranslationKey.USE, "Lietot");
        add(TranslationKey.ENTER_CVV2, "Norādiet savas kartes CVV2/CVC2 kodu");
        add(TranslationKey.NEW_CARD, "Pievienot karti");
        add(TranslationKey.CREDIT_CARD, "Kredītkarte");
        add(TranslationKey.CANCEL, "Atcelt");
        add(TranslationKey.PLEASE_WAIT, "Lūdzu, uzgaidiet...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Aizvērt un doties atpakaļ");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Jā, doties atpakaļ");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Ne, pasilikite puslapyje");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Jūsu maksājums tiks atcelts. Turpināt?");
        add(TranslationKey.BANK_TRANSFER, "Bankas pārskaitījums");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Maksājuma metodes dzēšana");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Vai tiešām vēlaties noņemt izvēlēto maksājuma metodi?");
        add(TranslationKey.REMOVE, "Noņemt");
        add(TranslationKey.INFORMATIONS, "Par");
        add(TranslationKey.PUBLISHER, "Izdevējs");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Lietotnes versija");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Izvēlieties maksājuma metodi");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Es piekrītu <a href=\"#\">PayU maksājumu noteikumiem</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Bankas pārskaitījums");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Ierīcē nav lietotnes, kas atbalsta šo formātu");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "kredīts vai debets");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bankas pārskaitījums");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Izvēlieties maksāšanas veidu");
        add(TranslationKey.BLIK_HINT, "Ievadiet BLIK kodu");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizēt un saglabāt BLIK maksājumu savā bankas lietotnē");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "naudokite banko kodą");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Ievadiet jaunu BLIK kodu");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "viena pieskāriena maksājums");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "BLIK maksājums saglabāts");
        add(TranslationKey.SCAN_CARD, "kartes nolasīšana");
        add(TranslationKey.SCAN_FAILED, "Neizdevās nolasīt karti – ievadiet kartes informāciju manuāli");
        add(TranslationKey.SCAN_CANCELED, "aršu nolasīšana atcelta");
        add(TranslationKey.SECURE_CHECKOUT, "DROŠI NORĒĶINI");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Verificē maksājumu ...");
        add(TranslationKey.OFFER_INSTALLMENTS_TITLE, "Darījums apstiprināts");
        add(TranslationKey.OFFER_INSTALLMENTS_HEADER, "Daļas maksājums");
        add(TranslationKey.OFFER_INSTALLMENTS_SUBTITLE, "Saņēmējs saņems kopējo pasūtījuma summu.");
        add(TranslationKey.OFFER_INSTALLMENTS_BODY, "Ar Mastercard karti šo maksājumu varat sadalīt pa daļām.");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_ACCEPT, "Sadalīt vairākos maksājumos");
        add(TranslationKey.OFFER_INSTALLMENTS_BUTTON_NEGATIVE, "Nē, paldies");
        add(TranslationKey.CHOOSE_INSTALLMENTS_SUBTITLE, "Ar Mastercard karti varat veikt samaksu pa daļām. Izvēlieties maksājumu skaitu, lai apstiprinātu.");
        add(TranslationKey.CHOOSE_INSTALLMENTS_BUTTON_NEGATIVE, "Nē, paldies");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_PLURAL, "daļu maksājumi");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_SINGULAR, "daļas maksājums");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_MANY, "daļu maksājumi");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_TOTAL_INSTALLMENTS, "kopā");
        add(TranslationKey.CHOOSE_INSTALLMENTS_LIST_1ST_INSTALLMENT, "1. daļas maksājums");
    }

    @Override
    public Locale getLanguage() {
        return Locale.LITHUANIAN;
    }
}
