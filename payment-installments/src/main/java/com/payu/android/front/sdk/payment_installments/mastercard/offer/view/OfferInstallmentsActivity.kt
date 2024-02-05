package com.payu.android.front.sdk.payment_installments.mastercard.offer.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer
import com.payu.android.front.sdk.payment_installments.R
import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.SelectedInstallment
import com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel.OfferInstallmentsViewModel
import com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel.OfferInstallmentsViewModelFactory
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider
import com.payu.android.front.sdk.payment_library_core_android.base.BaseActivity
import com.payu.android.front.sdk.payment_library_core_android.styles.ButtonStyle
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider


class OfferInstallmentsActivity : BaseActivity() {
    private lateinit var viewModel: OfferInstallmentsViewModel
    private lateinit var appToolbar: Toolbar
    private lateinit var libraryStyleInfo: LibraryStyleInfo
    private lateinit var toolbarTitleText: TextView
    private lateinit var titleText: TextView
    private lateinit var subTitleText: TextView
    private lateinit var bodyText: TextView
    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button
    private lateinit var logoImageView: ImageView

    companion object {
        const val INSTALLMENT_REQUEST_CODE = 167;
        const val INSTALLMENT_KEY = "INSTALLMENT_KEY"

        /**
         * Calling this method will trigger the installment flow of the app
         * Before please check if transaction can have an installment using call to: "/api/v2_1/orders/{orderId}/transactions"
         * on your endpoint
         *
         * REQUEST CODE:
         * @see OfferInstallmentsActivity.INSTALLMENT_REQUEST_CODE
         * BUNDLE KEY ->
         * @see OfferInstallmentsActivity.INSTALLMENT_KEY
         * Bundle object:
         * @see SelectedInstallment
         * After that please send selected installment using endpoint:
         * "/api/v2_1/card-installment-proposals/{proposalId}/decisions"
         * @see  [online documentation](https://developers.payu.com/en/mci.html)
         */
        @JvmStatic
        fun startForResult(activity: Activity) {
            val intent = Intent(activity, OfferInstallmentsActivity::class.java)
            activity.startActivityForResult(intent, INSTALLMENT_REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
                OfferInstallmentsViewModelFactory(StaticContentUrlProvider(
                        ConfigurationEnvironmentProvider.provideEnvironment(this)),
                        CardIssuer.MASTER_CARD,
                        TranslationFactory.getInstance()))
                .get(OfferInstallmentsViewModel::class.java)

        libraryStyleInfo = LibraryStyleProvider.fromContext(this)


        Glide.with(this).load(viewModel.provideImagePath()).into(logoImageView)
        setupTranslations()
        setupStyles()
        setupActions()
        observeUserAction()

    }

    override fun provideToolbar(): Toolbar {
        return appToolbar
    }

    override fun bindViews() {
        appToolbar = findViewById(R.id.payu_toolbar)
        toolbarTitleText = findViewById(R.id.title_payu_toolbar_textView)
        logoImageView = findViewById(R.id.installment_provider_logo_image)
        titleText = findViewById(R.id.installment_provider_title_text)
        subTitleText = findViewById(R.id.installment_provider_subtitle_text)
        bodyText = findViewById(R.id.installment_provider_body_text)
        positiveButton = findViewById(R.id.installment_provider_positive_button)
        negativeButton = findViewById(R.id.installment_provider_negative_button)

    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_offer_installments
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (INSTALLMENT_REQUEST_CODE == requestCode) {
            setResult(RESULT_OK, data)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
    }

    private fun setupTranslations() {
        titleText.text = viewModel.provideTranslationForTitle()
        subTitleText.text = viewModel.provideTranslationForSubTitle()
        bodyText.text = viewModel.provideTranslationForBody()
        positiveButton.text = viewModel.provideTranslationForButtonPositive()
        negativeButton.text = viewModel.provideTranslationForButtonNegative()
        toolbarTitleText.text = viewModel.provideTranslationForHeader()

    }

    private fun setupStyles() {
        appToolbar.setBackgroundColor(libraryStyleInfo.toolbarColor)
        findViewById<View>(R.id.main_view).setBackgroundColor(libraryStyleInfo.backgroundColor)

        //buttons
        ButtonStyle(libraryStyleInfo.textStyleButtonPrimary).applyTo(positiveButton)
        createStyleFromInfo(libraryStyleInfo.textStyleButtonPrimary.textStyleInfo).applyTo(positiveButton)
        ButtonStyle(libraryStyleInfo.textStyleButtonBasic).applyTo(negativeButton)
        createStyleFromInfo(libraryStyleInfo.textStyleButtonBasic.textStyleInfo).applyTo(negativeButton)

        //textViews
        val titleWithoutPadding = TextStyleInfo.Builder().withFont(libraryStyleInfo.textStyleTitle.font)
                .withFontPath(libraryStyleInfo.textStyleTitle.fontPath)
                .withPaddingBottom(libraryStyleInfo.textStyleTitle.paddingBottom)
                .withPaddingLeft(0f)
                .withPaddingRight(0f)
                .withPaddingTop(libraryStyleInfo.textStyleTitle.paddingTop)
                .withTextColor(libraryStyleInfo.textStyleTitle.textColor)
                .withTextSize(libraryStyleInfo.textStyleTitle.textSize)
                .build()

        createStyleFromInfo(titleWithoutPadding).applyTo(titleText)
        createStyleFromInfo(libraryStyleInfo.textStyleText).applyTo(subTitleText)
        createStyleFromInfo(libraryStyleInfo.textStyleText).applyTo(bodyText)
        createStyleFromInfo(libraryStyleInfo.textStyleTitle).applyTo(toolbarTitleText)


    }

    private fun setupActions() {
        positiveButton.setOnClickListener { viewModel.onAcceptInstallments() }
        negativeButton.setOnClickListener { viewModel.onDeclineInstallments() }
    }

    private fun observeUserAction() {
        viewModel.onClickEvent.observe(this, { action ->
            if (action) {
                ChooseInstallmentsActivity.startForResult(this)
            } else {
                setResult(RESULT_CANCELED)
                finish()
            }
        })
    }
}
