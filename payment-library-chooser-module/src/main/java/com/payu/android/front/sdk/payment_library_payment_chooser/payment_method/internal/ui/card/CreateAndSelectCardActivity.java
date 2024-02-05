package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.payu.android.front.sdk.payment_add_card_module.service.Error;
import com.payu.android.front.sdk.payment_add_card_module.service.InternalCardServiceTokenizer;
import com.payu.android.front.sdk.payment_add_card_module.service.NewCardCallback;
import com.payu.android.front.sdk.payment_add_card_module.service.NewCardService;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseMenuActivity;
import com.payu.android.front.sdk.payment_library_core_android.styles.ButtonStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.ScannedCard;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.PaymentMethodPresenterProvider;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;


public class CreateAndSelectCardActivity extends BaseMenuActivity implements CreateAndSelectCardView {
    private View mainView;
    private Toolbar toolbar;
    private NewCardView view;
    private TextView textToolbarTitle;
    private InternalCardServiceTokenizer instance;
    private Button buttonSaveWithAgreement;
    private Button buttonSaveWithoutAgreement;
    private View containerScanCard;
    private ImageView imageScanCard;
    private TextView textScanCard;
    private ViewGroup contentContainer;
    private CreateAndSelectCardViewPresenter presenter;
    private ProgressBar progressBar;
    private LibraryStyleInfo libraryStyleInfo;

    public static void startForResult(Activity activity, int requestCode) {
        Intent starter = new Intent(activity, CreateAndSelectCardActivity.class);
        activity.startActivityForResult(starter, requestCode);
    }

    @Override
    public void setCardNumber(@NonNull String cardNumber) {
        view.getCardNumberView().setCardNumber(cardNumber);
    }

    @Override
    public void setExpirationDate(int month, int year) {
        view.getCardDateView().setExpirationDate(month, year);
    }

    @Override
    public void hideLoading() {
        setContentEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        setContentEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSaveAndUse() {
        setupVisibilityAndStyle(true);
    }

    @Override
    public void hideSaveAndUse() {
        setupVisibilityAndStyle(false);
    }

    @Override
    public void showError(@NonNull String error) {
        // TODO: 25.04.2018 Implement better error visualization
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addCardWithoutAgreement(@NonNull String senderId) {
        instance.addCardWithoutAgreement(senderId);
    }

    @Override
    public void addCardWithAgreement(@NonNull String senderId) {
        instance.addCardWithAgreement(senderId);
    }

    @Override
    public void closeWithSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryStyleInfo = LibraryStyleProvider.fromContext(this);
        setupPresenter();
        setupListeners();
        setupTranslations();
    }

    @Override
    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected void bindViews() {
        mainView = findViewById(R.id.main_view);
        toolbar = findViewById(R.id.payu_toolbar);
        view = findViewById(R.id.new_card_view);
        buttonSaveWithAgreement = findViewById(R.id.save_with_agreement_button);
        buttonSaveWithoutAgreement = findViewById(R.id.save_without_agreement_button);
        textToolbarTitle = findViewById(R.id.title_payu_toolbar_textView);
        contentContainer = findViewById(R.id.create_and_select_card_content_container);
        containerScanCard = findViewById(R.id.card_scanner_container);
        imageScanCard = findViewById(R.id.card_scanner_imageView);
        textScanCard = findViewById(R.id.card_scanner_textView);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_create_and_select_card;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == presenter.getScannerAPI().createCardScannerRequestCode()) {
            if (resultCode == Activity.RESULT_OK) {
                ScannedCard card = presenter.getScannerAPI().createCardModel(data);
                if (card == null) {
                    Toast.makeText(this, translation.translate(TranslationKey.SCAN_FAILED), Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.onCardScanned(card.getCardNumber(), card.getExpirationDate());
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, translation.translate(TranslationKey.SCAN_CANCELED), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, translation.translate(TranslationKey.SCAN_FAILED), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupTranslations() {
        buttonSaveWithAgreement.setText(translation.translate(TranslationKey.SAVE_AND_USE));
        buttonSaveWithoutAgreement.setText(translation.translate(TranslationKey.USE));
        textToolbarTitle.setText(translation.translate(TranslationKey.NEW_CARD));
        textScanCard.setText(translation.translate(TranslationKey.SCAN_CARD));
    }

    private void setupVisibilityAndStyle(boolean isSaveWithAgreementVisible) {
        mainView.setBackgroundColor(libraryStyleInfo.getBackgroundColor());
        if (isSaveWithAgreementVisible) {
            new ButtonStyle(libraryStyleInfo.getTextStyleButtonPrimary()).applyTo(buttonSaveWithAgreement);
            createStyleFromInfo(libraryStyleInfo.getTextStyleButtonPrimary().getTextStyleInfo()).applyTo(buttonSaveWithAgreement);
            new ButtonStyle(libraryStyleInfo.getTextStyleButtonBasic()).applyTo(buttonSaveWithoutAgreement);
            createStyleFromInfo(libraryStyleInfo.getTextStyleButtonBasic().getTextStyleInfo()).applyTo(buttonSaveWithoutAgreement);
        } else {
            buttonSaveWithAgreement.setVisibility(View.GONE);
            new ButtonStyle(libraryStyleInfo.getTextStyleButtonPrimary()).applyTo(buttonSaveWithoutAgreement);
            createStyleFromInfo(libraryStyleInfo.getTextStyleButtonPrimary().getTextStyleInfo()).applyTo(buttonSaveWithoutAgreement);
        }
        if (presenter.shouldScanCard()) {
            Drawable cameraImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_camera_android, null);
            imageScanCard.setImageDrawable(cameraImage);
            imageScanCard.setColorFilter(libraryStyleInfo.getAccentColor());
            new ButtonStyle(libraryStyleInfo.getTextStyleButtonBasic()).applyTo(containerScanCard);
            createStyleFromInfo(libraryStyleInfo.getTextStyleButtonBasic().getTextStyleInfo()).applyTo(textScanCard);
            textScanCard.setPadding(textScanCard.getPaddingLeft(), 0, 0, 0);
            int windowContentPadding = ((int) libraryStyleInfo.getWindowContentPadding());
            contentContainer.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
            contentContainer.setBackgroundColor(libraryStyleInfo.getBackgroundColor());
            containerScanCard.setVisibility(View.VISIBLE);
        } else {
            containerScanCard.setVisibility(View.GONE);
        }

        createStyleFromInfo(libraryStyleInfo.getTextStyleTitle()).applyTo(textToolbarTitle);
        toolbar.setBackgroundColor(libraryStyleInfo.getToolbarColor());
    }

    private void setupListeners() {
        instance = (InternalCardServiceTokenizer) NewCardService.newInstance(view, this, new NewCardCallback() {
            @Override
            public void onSuccess(CardPaymentMethod cardPaymentMethod) {
                presenter.onAddCardSuccess(cardPaymentMethod);
            }

            @Override
            public void onError(Error error) {
                presenter.onAddCardError(error);
            }
        });
        instance.shouldHidePayUTerms(this.getPackageName());

        buttonSaveWithAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddCard(true, instance.isCardValid());
            }
        });
        buttonSaveWithoutAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddCard(false, instance.isCardValid());
            }
        });

        containerScanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(presenter.getScannerAPI().createScannerIntent(CreateAndSelectCardActivity.this, presenter.getScanDate()), presenter.getScannerAPI().createCardScannerRequestCode());
            }
        });
    }

    private void setupPresenter() {
        presenter = PaymentMethodPresenterProvider.createCreateAndSelectCardPresenter(this);
        presenter.takeView(this);
    }

    private void setContentEnabled(boolean enabled) {
        view.setEnabled(enabled);
        buttonSaveWithAgreement.setEnabled(enabled);
        buttonSaveWithoutAgreement.setEnabled(enabled);
        containerScanCard.setEnabled(enabled);
        containerScanCard.findViewById(R.id.card_scanner_textView).setEnabled(enabled);
        containerScanCard.findViewById(R.id.card_scanner_imageView).setEnabled(enabled);
    }
}
