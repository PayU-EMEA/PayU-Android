package com.payu.android.front.sdk.demo.ui.terms

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.payu.android.front.sdk.demo.ui.base.MainActivity
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.frontsdk.databinding.ActivityTermsConditionBinding
import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.TermsAgreementResponseListener
import com.payu.android.front.sdk.marketplace_terms_condition.marketplace.TermsMarketPlaceService
import dagger.android.AndroidInjection
import javax.inject.Inject

class TermsAndConditionActivity : MainActivity() {
    lateinit var instance: TermsMarketPlaceService

    @Inject
    lateinit var viewModel: TermsTestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       AndroidInjection.inject(this)
        val binding: ActivityTermsConditionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_terms_condition)
        binding.model = viewModel
        instance = TermsMarketPlaceService.newInstance(
            "Super Dialog do wyświeltenia",
            "Terms and Condition Dialog",
            "Opis",
            viewModel.baseUrl.get() ?: "", viewModel.verificationId.get() ?: "",
            binding.testTermsView
        )
        observeAction(binding)

    }

    private fun observeAction(binding: ActivityTermsConditionBinding) {
        binding.model?.callEvent?.observe(this, { triggered ->
            if (triggered) {
                instance.onTermsApproveClick(object : TermsAgreementResponseListener {
                    override fun onSuccess() {
                        Toast.makeText(this@TermsAndConditionActivity, "Success", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(t: Throwable) {
                        Toast.makeText(this@TermsAndConditionActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

}