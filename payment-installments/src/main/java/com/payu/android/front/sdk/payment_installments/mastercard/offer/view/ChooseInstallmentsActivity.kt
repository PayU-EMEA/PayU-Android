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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer
import com.payu.android.front.sdk.payment_installments.R
import com.payu.android.front.sdk.payment_installments.mastercard.offer.adapter.RowInstallmentAdapter
import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.SelectedInstallment
import com.payu.android.front.sdk.payment_installments.mastercard.offer.view.OfferInstallmentsActivity.Companion.INSTALLMENT_KEY
import com.payu.android.front.sdk.payment_installments.mastercard.offer.view.OfferInstallmentsActivity.Companion.INSTALLMENT_REQUEST_CODE
import com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel.ChooseInstallmentsViewModel
import com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel.ChooseInstallmentsViewModelFactory
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider
import com.payu.android.front.sdk.payment_library_core_android.base.BaseActivity
import com.payu.android.front.sdk.payment_library_core_android.styles.ButtonStyle
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider
import mapDataFromApp

class ChooseInstallmentsActivity : BaseActivity() {
    private lateinit var viewModel: ChooseInstallmentsViewModel
    private lateinit var appToolbar: Toolbar
    private lateinit var toolbarTitleText: TextView
    private lateinit var subTitleText: TextView
    private lateinit var negativeButton: Button
    private lateinit var logoImageView: ImageView

    private lateinit var libraryStyleInfo: LibraryStyleInfo

    companion object {
        @JvmStatic
        fun startForResult(activity: Activity) {
            val intent = Intent(activity, ChooseInstallmentsActivity::class.java)
            activity.startActivityForResult(intent, INSTALLMENT_REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
                ChooseInstallmentsViewModelFactory(this, StaticContentUrlProvider(
                        ConfigurationEnvironmentProvider.provideEnvironment(this)),
                        CardIssuer.MASTER_CARD,
                        TranslationFactory.getInstance()))
                .get(ChooseInstallmentsViewModel::class.java)

        viewModel.onClickEvent.observe(this, {
            setResult(RESULT_CANCELED)
            finish()
        })
        libraryStyleInfo = LibraryStyleProvider.fromContext(this)
        translation = TranslationFactory.getInstance()
        Glide.with(this).load(viewModel.provideImagePath()).into(logoImageView)
        setupTranslations()
        setupStyles()
        setupActions()
    }

    override fun provideToolbar(): Toolbar {
        return appToolbar
    }

    override fun bindViews() {
        appToolbar = findViewById(R.id.payu_toolbar)
        toolbarTitleText = findViewById(R.id.title_payu_toolbar_textView)
        subTitleText = findViewById(R.id.choose_installment_subtitle_text)
        negativeButton = findViewById(R.id.choose_installment_negative_button)
        logoImageView = findViewById(R.id.choose_installment_logo_image)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_choose_installments
    }

    private fun setupTranslations() {
        subTitleText.text = viewModel.provideTranslationForSubTitle()
        negativeButton.text = viewModel.provideTranslationForButtonNegative()
        toolbarTitleText.text = viewModel.provideTranslationForHeader()

    }

    private fun setupStyles() {
        appToolbar.setBackgroundColor(libraryStyleInfo.toolbarColor)
        findViewById<View>(R.id.choose_installments_view).setBackgroundColor(libraryStyleInfo.backgroundColor)

        //buttons
        ButtonStyle(libraryStyleInfo.textStyleButtonPrimary).applyTo(negativeButton)
        createStyleFromInfo(libraryStyleInfo.textStyleButtonPrimary.textStyleInfo).applyTo(negativeButton)
        negativeButton.setOnClickListener { viewModel.onDeclineInstallments() }
        //textViews
        createStyleFromInfo(libraryStyleInfo.textStyleText).applyTo(subTitleText)
        createStyleFromInfo(libraryStyleInfo.textStyleTitle).applyTo(toolbarTitleText)


    }

    private fun setupAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.choose_installment_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel.installment.observe(this, {
            val rowInstallmentAdapter = RowInstallmentAdapter(mapDataFromApp(it), { selectedItem ->
                val intent = Intent()
                intent.putExtra(INSTALLMENT_KEY, SelectedInstallment(selectedItem.getId(),
                        selectedItem.installmentType.toString(), it.proposalId))
                setResult(RESULT_OK, intent)
                finish()
            },
                    libraryStyleInfo, translation, this)
            recyclerView.adapter = rowInstallmentAdapter
        })
    }

    private fun setupActions() {
        setupAdapter()
    }
}