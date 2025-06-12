package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Italian extends StringMapTranslation {

    public Italian() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Il campo numero della carta non può essere vuoto");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Numero della carta errato");
        add(TranslationKey.INVALID_CVV_ERROR, "Inserisci un codice valido");
        add(TranslationKey.CVV_CODE, "Codice CVV2/CVC2");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/AA");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Inserisci una data valida");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Data di scadenza");
        add(TranslationKey.CARD_NAME, "Nome della carta");
        add(TranslationKey.CARD_NUMBER, "Numero della carta");
        add(TranslationKey.SAVE_AND_USE, "Salva e usa");
        add(TranslationKey.USE, "Usa");
        add(TranslationKey.ENTER_CVV2, "Fornisci il codice CVV2/CVC2 per la tua carta");
        add(TranslationKey.NEW_CARD, "Aggiungi carta");
        add(TranslationKey.CREDIT_CARD, "Carta di credito");
        add(TranslationKey.CANCEL, "Cancella");
        add(TranslationKey.PLEASE_WAIT, "Attendi prego…");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Chiudi e torna al negozio");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Sì, torna");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "No, resta sulla pagina del pagamento");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Il tuo pagamento sarà cancellato. Vuoi continuare?");
        add(TranslationKey.BANK_TRANSFER, "Bonifico bancario");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Cancellazione del metodo di pagamento");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Vuoi davvero cancellare il metodo di pagamento selezionato?");
        add(TranslationKey.REMOVE, "Cancella");
        add(TranslationKey.INFORMATIONS, "Informazioni");
        add(TranslationKey.PUBLISHER, "Emettitore");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Versione dell’app");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Seleziona un metodo di pagamento");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Accetto i <a href=\"#\">Termini di pagamento PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Bonifico bancario");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Sul tuo dispositivo non è presente nessuna applicazione per eseguire questa azione");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "debito o credito");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Bonifico bancario");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Scegli come pagare");
        add(TranslationKey.BLIK_HINT, "Inserisci il codice BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorizza e memorizza il pagamento BLIK nell’app della tua banca");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "usa il codice dell'app della tua banca");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Inserisci un nuovo codice BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "pagamento one-tap");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "pagamento BLIK salvato");
        add(TranslationKey.SCAN_CARD, "Scansione carta");
        add(TranslationKey.SCAN_FAILED, "Impossibile scansionare la carta, inserisci i dati manualmente");
        add(TranslationKey.SCAN_CANCELED, "Scansione carta cancellata");
        add(TranslationKey.SECURE_CHECKOUT, "CHECKOUT SICURO");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Verifica pagamento…");
    }

    @Override
    public Locale getLanguage() {
        return Locale.ITALIAN;
    }
}
