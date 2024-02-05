package com.payu.android.front.sdk.payment_library_core.external.model;

import static com.google.common.base.Preconditions.checkArgument;

public class InstallmentOption {
    private final int numberOfInstallments;
    private final float interestRate;
    private final int installmentFeeAmount;
    private final float annualPercentageRate;
    private final int installmentAmount;
    private final float firstInstallmentValue;
    private final float totalValue;
    private final String id;

    private InstallmentOption(String id, int numberOfInstallments, float firstInstallmentValue, float totalValue, float interestRate, int installmentFeeAmount,
                             float annualPercentageRate, int installmentAmount) {
        this.id = id;
        this.numberOfInstallments = numberOfInstallments;
        this.firstInstallmentValue = firstInstallmentValue;
        this.totalValue = totalValue;
        this.interestRate = interestRate;
        this.installmentFeeAmount = installmentFeeAmount;
        this.annualPercentageRate = annualPercentageRate;
        this.installmentAmount = installmentAmount;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public float getFirstInstallmentValue() {
        return firstInstallmentValue;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public String getId() {
        return id;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public int getInstallmentFeeAmount() {
        return installmentFeeAmount;
    }

    public float getAnnualPercentageRate() {
        return annualPercentageRate;
    }

    public int getInstallmentAmount() {
        return installmentAmount;
    }


    public static class Builder {
        private int numberOfInstallments;  // only for VARYING_NUMBER_OF_OPTIONS
        private float firstInstallmentValue;  // only for VARYING_NUMBER_OF_OPTIONS
        private float totalValue;
        private String id;
        private float interestRate;
        private int installmentFeeAmount;
        private float annualPercentageRate;
        private int installmentAmount;  // only for VARYING_NUMBER_OF_OPTIONS

        public InstallmentOption build() {
            checkArgument(id != null, "id should be provided");
            checkArgument(totalValue > 0, "totalValue should be greater than 0");

            return new InstallmentOption(id, numberOfInstallments, firstInstallmentValue, totalValue,
                    interestRate, installmentFeeAmount, annualPercentageRate, installmentAmount);
        }

        public Builder withInstallmentAmount(int installmentAmount) {
            this.installmentAmount = installmentAmount;
            return this;
        }

        public Builder withAnnualPercentageRate(float annualPercentageRate) {
            this.annualPercentageRate = annualPercentageRate;
            return this;
        }

        public Builder withInstallmentFeeAmount(int installmentFeeAmount) {
            this.installmentFeeAmount = installmentFeeAmount;
            return this;
        }

        public Builder withInterestRate(float interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withNumberOfInstallments(int numberOfInstallments) {
            this.numberOfInstallments = numberOfInstallments;
            return this;
        }

        public Builder withFirstInstallments(int firstInstallmentValue) {
            this.firstInstallmentValue = firstInstallmentValue;
            return this;
        }

        public Builder withTotalValue(int totalValue) {
            this.totalValue = totalValue;
            return this;
        }

    }
}
