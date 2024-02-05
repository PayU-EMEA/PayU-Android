package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicAddCardClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicAddCardClassConfigurationProviderFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegateFactory;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.IconPathProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.CardScannerApiConfigurationStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodConfigurationProviderFactory;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.BlikAmbiguityStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodsAdapter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodExtractor;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodPersistenceStorage;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.presentation.BlikPresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card.CreateAndSelectCardViewPresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation.PayByLinkPresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation.PblModelConverter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation.GeneralContentHandler;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation.PaymentMethodConverter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation.PaymentMethodsPresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePaymentMethodsList;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePblPaymentMethods;

public class PaymentMethodPresenterProvider {

    public static PayByLinkPresenter createPayByLinkPresenter(@NonNull Context context) {
        return new PayByLinkPresenter(new RetrievePblPaymentMethods(getRepository(context)), new PblModelConverter());
    }

    private static PaymentMethodRepository getRepository(@NonNull Context context) {
        return PaymentMethodStaticHolder.getInstance(context).getPaymentMethodRepository();
    }

    @Nullable
    private static PaymentMethodsAdapter getAdapter(@NonNull Context context) {
        return PaymentMethodStaticHolder.getInstance(context).getPaymentMethodsAdapter();
    }

    @Nullable
    private static CardScannerAPI getCardScannerAPI(@NonNull Context context) {
        ConfigurationDataProvider configurationDataProvider = ConfigurationDataProviderHolder.getInstance(context);

        DynamicCardActionDelegate dynamicCardActionDelegate = createDynamicCardActionDelegate(DynamicAddCardClassConfigurationProviderFactory.createProvider(context),
                configurationDataProvider);
        return CardScannerApiConfigurationStaticHolder.getInstance(dynamicCardActionDelegate).getCardScannerAPI();
    }

    public static CreateAndSelectCardViewPresenter createCreateAndSelectCardPresenter(@NonNull Context context) {
        ConfigurationDataProvider configurationDataProvider = ConfigurationDataProviderHolder.getInstance(context);
        DynamicCardActionDelegate dynamicCardActionDelegate = createDynamicCardActionDelegate(DynamicAddCardClassConfigurationProviderFactory.createProvider(context),
                configurationDataProvider);
        PaymentMethodClassConfigurationProvider configurationProvider = PaymentMethodConfigurationProviderFactory.createProvider(context);
        return new CreateAndSelectCardViewPresenter(getRepository(context),
                dynamicCardActionDelegate,
                configurationProvider.getPaymentMethodsActions(),
                configurationDataProvider.isScanCardDatePossible(),
                getCardScannerAPI(context));
    }

    public static PaymentMethodsPresenter createPaymentMethodPresenter(@NonNull Context context) {
        Translation translation = TranslationFactory.getInstance();
        ConfigurationDataProvider configurationDataProvider = ConfigurationDataProviderHolder.getInstance(context);
        DynamicCardActionDelegate dynamicCardActionDelegate = createDynamicCardActionDelegate(DynamicAddCardClassConfigurationProviderFactory.createProvider(context),
                configurationDataProvider);
        StyleClassConfigurationProvider styleClassConfigurationProvider = StyleClassConfigurationFactory.createStyleClassProvider(context);
        return new PaymentMethodsPresenter(
                new RetrievePaymentMethodsList(getRepository(context), getAdapter(context),
                        new SelectedPaymentMethodExtractor(new SelectedPaymentMethodPersistenceStorage(context), new TokenHasher())),
                new RetrievePblPaymentMethods(getRepository(context)), new PaymentMethodConverter(translation),
                new GeneralContentHandler(translation, new IconPathProvider(styleClassConfigurationProvider),
                        dynamicCardActionDelegate, configurationDataProvider.isPBLPossible()));

    }

    public static BlikPresenter createBlikPresenter(@NonNull Context context) {
        Translation translation = TranslationFactory.getInstance();
        return new BlikPresenter(BlikAmbiguityStaticHolder.getInstance(context).getBlikAmbiguityRepository(), new PaymentMethodConverter(translation));
    }

    private static DynamicCardActionDelegate createDynamicCardActionDelegate(@NonNull DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider,
                                                                             @NonNull ConfigurationDataProvider configurationDataProvider) {
        return DynamicCardActionDelegateFactory.create(dynamicAddCardClassConfigurationProvider, configurationDataProvider);
    }
}
