package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale ;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey ;

public class French extends StringMapTranslation {

    public French() {
        super() ;
        add(TranslationKey.OK, "OK") ;
        add(TranslationKey.CARD_VALIDATION_EMPTY, "Le numéro de la carte ne peut être vide");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "Le numéro de la carte est incorrect");
        add(TranslationKey.INVALID_CVV_ERROR, "Renseigner le code correct");
        add(TranslationKey.INVALID_CVV_EMPTY, "Saisir le code CVV");
        add(TranslationKey.CVV_CODE, "Code CVV");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/AA");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Renseigner une date valide");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Date d'expiration");
        add(TranslationKey.CARD_NAME, "Nom de la carte");
        add(TranslationKey.CARD_NUMBER, "Numéro de carte");
        add(TranslationKey.SAVE_AND_USE, "Sauvegarder et utiliser");
        add(TranslationKey.USE, "Utiliser");
        add(TranslationKey.ENTER_CVV2, "Entrer le code CVV de la carte");
        add(TranslationKey.NEW_CARD, "Ajouter une carte");
        add(TranslationKey.CREDIT_CARD, "Carte de paiement");
        add(TranslationKey.CANCEL, "Annuler");
        add(TranslationKey.PLEASE_WAIT, "Veuillez patienter...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Retour au magasin");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Oui, retour au magasin");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "Non, rester sur la page de paiement");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Vous allez retourner au magasin dans un instant. Si vous n'avez pas effectué le paiement, votre transaction sera annulée. Continuer ?");
        add(TranslationKey.BANK_TRANSFER, "Virement bancaire");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Suppression du mode de paiement");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "Êtes-vous sûr de vouloir supprimer le mode de paiement sélectionné ?");
        add(TranslationKey.REMOVE, "Supprimer");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Choisir le mode de paiement");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "J'accepte les <a href=\"#\">conditions de paiement de PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Virement bancaire");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "Nous sommes désolés, mais aucune application sur l'appareil ne prend en charge cette action.");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "débit ou crédit");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "virement en ligne et traditionnel");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Sélectionnez un paiement enregistré");
        add(TranslationKey.BLIK_HINT, "Entrez le code BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Approuver et enregistrer le paiement dans l'application bancaire");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "Code de l'application bancairee");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "paiement par touche unique");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Je veux entrer un nouveau code BLIK");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "paiement BLIK enregistré");
        add(TranslationKey.SCAN_CARD, "Numériser la carte");
        add(TranslationKey.SCAN_FAILED, "La numérisation de la carte a échoué - entrer les données via le formulaire");
        add(TranslationKey.SCAN_CANCELED, "La numérisation de la carte a été annulée");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Je vérifie le paiement...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.FRENCH;
    }
}
