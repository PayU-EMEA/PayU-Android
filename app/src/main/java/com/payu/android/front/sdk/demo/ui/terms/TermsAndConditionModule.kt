package com.payu.android.front.sdk.demo.ui.terms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.payu.android.front.sdk.demo.ui.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [TermsAndConditionModule.Providers::class])
abstract class TermsAndConditionModule {
    @ContributesAndroidInjector(modules = [TermsAndConditionModule.Injectors::class])
    abstract fun bind(): TermsAndConditionActivity

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(TermsTestViewModel::class)
        fun provideTermsTestViewModel(
        ): ViewModel = TermsTestViewModel()
    }

    @Module
    class Injectors {
        @Provides
        fun provideTermsTestViewModel(
            factory: ViewModelProvider.Factory,
            target: TermsAndConditionActivity
        ): TermsTestViewModel = ViewModelProvider(target, factory).get(TermsTestViewModel::class.java)
    }
}