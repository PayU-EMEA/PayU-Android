package com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard;

/**
 * Additional functionality that could be responsible for adding a card dynamically
 */
public interface DynamicCardActions {
    /**
     * should add card flow be visible in payment-library-chooser-widget (library)
     */
    boolean addCardFlow();

    /**
     * Should button "saveAndUse" be visible in payment-library-chooser-widget (library) on AddCardView
     * if not user could only add card using "Use" button
     */
    boolean saveAndUseOption();

    /**
     * Should button "ScanCard" be visible in  payment-library-chooser-widget (library) on AddCardView
     * this button is responsible for triggering using camera on device
     */
    boolean scanCardOption();
}
