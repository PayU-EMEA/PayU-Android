package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class Spanish extends StringMapTranslation {

    public Spanish() {
        super();
        add(TranslationKey.OK, "OK");
        add(TranslationKey.CARD_VALIDATION_EMPTY, "El número de tarjeta no debe estar vacío");
        add(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT, "El número de tarjeta es incorrecto");
        add(TranslationKey.INVALID_CVV_ERROR, "Por favor, introduzca un codigo valido");
        add(TranslationKey.CVV_CODE, "CVV2/CVC2 código");
        add(TranslationKey.EXPIRATION_DATE_HINT_TEXT, "MM/YY");
        add(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID, "Por favor introduzca una fecha valida");
        add(TranslationKey.CARD_EXPIRATION_DATE, "Fecha de caducidado");
        add(TranslationKey.CARD_NAME, "Nombre de tarjeta");
        add(TranslationKey.CARD_NUMBER, "Número de tarjeta");
        add(TranslationKey.SAVE_AND_USE, "Guardar y usar");
        add(TranslationKey.USE, "Usar");
        add(TranslationKey.ENTER_CVV2, "Proporcione el código CVV2/CVC2 para su tarjeta");
        add(TranslationKey.NEW_CARD, "Agregar tarjeta");
        add(TranslationKey.CREDIT_CARD, "Tarjeta de crédito");
        add(TranslationKey.CANCEL, "Cancelar");
        add(TranslationKey.PLEASE_WAIT, "Espere por favor...");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE, "Cerrar y volver");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE, "Si, vuelve");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE, "No abandones la página de pago");
        add(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE, "Su pago será cancelado. Continuar?");
        add(TranslationKey.BANK_TRANSFER, "Transferencia Bancaria");
        add(TranslationKey.REMOVE_METHOD_DIALOG_TITLE, "Eliminar método de pago");
        add(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT, "¿Estás seguro de eliminar el método de pago seleccionado?");
        add(TranslationKey.REMOVE, "Eliminar");
        add(TranslationKey.INFORMATIONS, "Sobre");
        add(TranslationKey.PUBLISHER, "Editor");
        add(TranslationKey.PAYU_COMPANY_NAME, "PayU S.A.");
        add(TranslationKey.APPLICATION_VERSION, "Versión de la aplicación");
        add(TranslationKey.SELECT_PAYMENT_METHOD, "Seleccione el método de pago");
        add(TranslationKey.SUPPORT_PAYMENT_INFORMATION, "Acepto <a href=\"#\">Condiciones de pago de PayU</a>");
        add(TranslationKey.COMPLIANCE_URL, "https://docs.google.com/viewer?url=https://static.payu.com/sites/terms/files/payu_terms_of_service_single_transaction_pl_en.pdf");
        add(TranslationKey.PBL_TITLE, "Transferencia Bancaria");
        add(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT, "En el dispositivo no hay ninguna aplicación compatible con esto");
        add(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION, "débito o crédito");
        add(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION, "Transferencia Bancaria");
        add(TranslationKey.BLIK_AMBIGUITY_SELECTION, "Elige cómo pagar");
        add(TranslationKey.BLIK_HINT, "Ingrese el código BLIK");
        add(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT, "Autorice y almacene el pago BLIK en su aplicación bancaria");
        add(TranslationKey.BLIK_PAYMENT_NAME, "BLIK");
        add(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION, "código en tu aplicación bancaria");
        add(TranslationKey.BLIK_INPUT_NEW_CODE, "Introduce el nuevo código BLIK");
        add(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION, "pago con un solo toque");
        add(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION, "pago BLIK guardado");
        add(TranslationKey.SCAN_CARD, "Escaneo de tarjetas");
        add(TranslationKey.SCAN_FAILED, "No se puede escanear la tarjeta: ingrese los detalles de la tarjeta manualmente");
        add(TranslationKey.SCAN_CANCELED, "Escaneo de tarjeta cancelada");
        add(TranslationKey.SECURE_CHECKOUT, "PAGO SEGURO");
        add(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE, "Verificando el pago...");
    }

    @Override
    public Locale getLanguage() {
        return Locale.SPANISH;
    }
}
