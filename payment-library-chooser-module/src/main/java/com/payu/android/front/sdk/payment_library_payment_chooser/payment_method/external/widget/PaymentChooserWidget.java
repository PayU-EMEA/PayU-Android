package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicAddCardClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicAddCardClassConfigurationProviderFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegateFactory;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.CardScannerApiConfigurationStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodsAdapter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.SelectedPaymentMethodWidget;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.extension.blik.BlikPaymentMethodWidgetExtension;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class PaymentChooserWidget extends FrameLayout {
    private float minimumWidth;
    private SelectedPaymentMethodWidget selectedPaymentMethodWidget;
    private BlikPaymentMethodWidgetExtension blikContainer;

    public PaymentChooserWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaymentChooserWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private static int calculateSize(float minSize, int measureSpec) {
        return (int) Math.max(minSize, MeasureSpec.getSize(measureSpec));
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_payment_chooser, this);
        minimumWidth = getContext().getResources().getDimension(R.dimen.payu_styles_widget_minimum_width);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getBackground() == null) {
            setBackgroundResource(R.color.payu_styles_color_background);
        }
        selectedPaymentMethodWidget = findViewById(R.id.selected_payment_method_widget);
        blikContainer = findViewById(R.id.blik_container);
        selectedPaymentMethodWidget.setSelectionPaymentListener(blikContainer.getOnSelectionPaymentMethodListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(calculateSizeSpec(minimumWidth, widthMeasureSpec), heightMeasureSpec);
    }

    private int calculateSizeSpec(float minSize, int measureSpec) {
        if (MeasureSpec.getMode(measureSpec) == MeasureSpec.AT_MOST || MeasureSpec.getMode(measureSpec) == MeasureSpec.UNSPECIFIED) {
            return measureSpec;
        } else {
            return MeasureSpec.makeMeasureSpec(calculateSize(minSize, measureSpec), MeasureSpec.getMode(measureSpec));
        }
    }

    /**
     * This method set Google Pay as additional Payment method inside the widget for configuration check
     * documentation about payment-library-google-pay-adapter & payment-library-google-pay-module
     * <p>
     * This unlock Google Pay tile, for Payment Support please contact itsupport
     *
     * @param paymentMethodsAdapter add new Payment method that cope with currently used
     */
    public void setPaymentMethodsAdapter(PaymentMethodsAdapter paymentMethodsAdapter) {
        PaymentMethodStaticHolder.getInstance(getContext()).setPaymentMethodsAdapter(paymentMethodsAdapter);
    }

    /**
     * This method set API for card scanner to pas configuration check for documentation on payment-library-card-scanner
     * and add property     <bool name="payu_card_scanner">true</bool> inside xml file
     * When this is set new button will be available inside "add new card view"
     *
     * @param cardScannerAPI instance of API that handle 3rd party card scanner
     */
    public void setCardScannerAPI(@NonNull CardScannerAPI cardScannerAPI) {
        ConfigurationDataProvider configurationDataProvider = ConfigurationDataProviderHolder.getInstance(getContext());

        DynamicCardActionDelegate dynamicCardActionDelegate = createDynamicCardActionDelegate(DynamicAddCardClassConfigurationProviderFactory.createProvider(getContext()),
                configurationDataProvider);
        CardScannerApiConfigurationStaticHolder.getInstance(dynamicCardActionDelegate).setCardScannerAPI(cardScannerAPI);
    }

    /**
     * Retrieve selected by the user payment method
     *
     * @return selected LiveData with Payment Method or empty LiveData object
     */
    @NonNull
    public LiveData<PaymentMethod> getPaymentMethod() {
        return selectedPaymentMethodWidget.getPaymentMethod();
    }

    /**
     * Check if payment method is selected
     *
     * @return false if Payment Method is not selected and true when Payment Method is selected
     */
    public boolean isPaymentMethodSelected() {
        LiveData<PaymentMethod> paymentMethod = getPaymentMethod();
        return paymentMethod != null && paymentMethod.getValue() != null;
    }

    /**
     * Check if BLIK authorization code is needed
     *
     * @return true if user should input 6 Digit code
     */
    public boolean isBlikAuthorizationCodeNeeded() {
        return blikContainer.isInputDisplayed();
    }

    /***
     *Retrieved authorization code from user
     * @return null or value that was inputted by user
     */
    @Nullable
    public String getBlikAuthorizationCode() {
        return blikContainer.getAuthorizationCode();
    }

    /**
     * Clean all Payment Methods that were provided by
     * {@link PaymentMethodActions#providePaymentMethods(PaymentMethodsCallback)}
     * and selected PaymentMethod
     */
    public void cleanPaymentMethods() {
        selectedPaymentMethodWidget.cleanPaymentMethods();
    }

    public void cleanPaymentMethodsWithoutCleaningSelectedMethod() {
        selectedPaymentMethodWidget.cleanPaymentMethodsWithoutSelectedPayment();
    }

    /**
     * Check if user inputted 6 digit authorization code
     *
     * @return false if validation fails in other hand true
     */
    public boolean isBlikAuthorizationCodeProvided() {
        return blikContainer.isBlikAuthorizationCodeProvided();
    }

    private static DynamicCardActionDelegate createDynamicCardActionDelegate(@NonNull DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider,
                                                                             @NonNull ConfigurationDataProvider configurationDataProvider) {
        return DynamicCardActionDelegateFactory.create(dynamicAddCardClassConfigurationProvider, configurationDataProvider);
    }
}
