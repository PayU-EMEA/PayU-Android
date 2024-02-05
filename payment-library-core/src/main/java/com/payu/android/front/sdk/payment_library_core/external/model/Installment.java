package com.payu.android.front.sdk.payment_library_core.external.model;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;


public class Installment {
    private final String currency;
    private final List<InstallmentOption> installmentOptionList;
    private final InstallmentType type;
    private final int minNumberOfInstallments;
    private final int maxNumberOfInstallments;
    private final String proposalId;

    private Installment(String currency, InstallmentType type, List<InstallmentOption> installmentOptionList, int minNumberOfInstallments,
                        int maxNumberOfInstallments, String proposalId) {
        this.currency = currency;
        this.type = type;
        this.installmentOptionList = installmentOptionList;
        this.minNumberOfInstallments = minNumberOfInstallments;
        this.maxNumberOfInstallments = maxNumberOfInstallments;
        this.proposalId = proposalId;

    }

    public String getCurrency() {
        return currency;
    }

    public InstallmentType getType() {
        return type;
    }

    public int getMinNumberOfInstallments() {
        return minNumberOfInstallments;
    }

    public int getMaxNumberOfInstallments() {
        return maxNumberOfInstallments;
    }

    public List<InstallmentOption> getInstallmentOptionList() {
        return installmentOptionList;
    }

    public String getProposalId() {
        return proposalId;
    }

    public static class Builder {
        private String currency;
        private List<InstallmentOption> installmentOptionList;
        private InstallmentType type;
        private int minNumberOfInstallments;
        private int maxNumberOfInstallments;
        private String proposalId;

        public Installment build() {
            checkArgument(currency != null, "currency should be provided");
            checkArgument(installmentOptionList != null && !installmentOptionList.isEmpty(), "option list should be not empty");

            if (type == InstallmentType.INSTALLMENT) {
                checkArgument(minNumberOfInstallments > 0, "minNumberOfInstallments should be greater than 0");
                checkArgument(maxNumberOfInstallments > 0, "maxNumberOfInstallments should be greater than 0");
            }
            return new Installment(currency, type, installmentOptionList, minNumberOfInstallments, maxNumberOfInstallments, proposalId);
        }

        public Builder withProposalId(String proposalId) {
            this.proposalId = proposalId;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withInstallmentType(String installmentType) {
            type = InstallmentType.withInstallmentTypeText(installmentType);
            checkArgument(type != null, "installment type should be: VARYING_NUMBER_OF_OPTIONS or VARYING_NUMBER_OF_INSTALLMENTS");
            return this;
        }


        public Builder withInstallmentOptionList(List<InstallmentOption> installmentOptionList) {
            this.installmentOptionList = installmentOptionList;
            return this;
        }

        public Builder withMinNumberOfInstallments(int minNumberOfInstallments) {
            this.minNumberOfInstallments = minNumberOfInstallments;
            return this;
        }

        public Builder withMaxNumberOfInstallments(int maxNumberOfInstallments) {
            this.maxNumberOfInstallments = maxNumberOfInstallments;
            return this;
        }

    }
}
