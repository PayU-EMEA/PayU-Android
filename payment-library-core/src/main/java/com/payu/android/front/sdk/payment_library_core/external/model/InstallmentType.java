package com.payu.android.front.sdk.payment_library_core.external.model;

public enum InstallmentType {
    OPTIONS("VARYING_NUMBER_OF_OPTIONS"),
    INSTALLMENT("VARYING_NUMBER_OF_INSTALLMENTS");

    private final String installmentTypeText;

    InstallmentType(String installmentTypeText) {
        this.installmentTypeText = installmentTypeText;
    }

    public static InstallmentType withInstallmentTypeText(String installmentTypeText) {

        for (InstallmentType installmentType : values()) {

            if (installmentType.getInstallmentTypeText().equalsIgnoreCase(installmentTypeText)) {
                return installmentType;
            }
        }
        return null;
    }

    public String getInstallmentTypeText() {
        return installmentTypeText;
    }
}
