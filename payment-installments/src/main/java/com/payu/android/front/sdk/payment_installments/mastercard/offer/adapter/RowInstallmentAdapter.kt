package com.payu.android.front.sdk.payment_installments.mastercard.offer.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.payu.android.front.sdk.payment_installments.R
import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.InstallmentAdapterCell
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentType
import com.payu.android.front.sdk.payment_library_core.translation.Translation
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider

class RowInstallmentAdapter(private val installmentList: List<InstallmentAdapterCell>,
                            private val listener: (InstallmentAdapterCell) -> Unit,
                            private val libraryStyle: LibraryStyleInfo,
                            private val translations: Translation,
                            private val context: Context) :
        RecyclerView.Adapter<RowInstallmentAdapter.RowInstallmentViewHolder>() {

    class RowInstallmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItem(installment: InstallmentAdapterCell,
                     libraryStyle: LibraryStyleInfo,
                     translations: Translation,
                     context: Context) {
            val imageView = itemView.findViewById<ImageView>(R.id.row_installments_image)
            val unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.baseline_date_range_black_24dp)
            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
            DrawableCompat.setTint(wrappedDrawable, libraryStyle.accentColor)

            imageView.setImageDrawable(wrappedDrawable)

            val subtitle = itemView.findViewById<TextView>(R.id.row_installments_number_text)
            subtitle.text = installment.formatSubtitle(translations.translate(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_SINGULAR),
                    translations.translate(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_PLURAL),
                    translations.translate(TranslationKey.CHOOSE_INSTALLMENTS_LIST_SUBTITLE_MANY)
            )
            subtitle.setTypeface(subtitle.typeface, Typeface.BOLD)
            createStyleFromInfo(libraryStyle.textStyleTitle, context).applyTo(subtitle)

            val additionalText = if (installment.installmentType == InstallmentType.OPTIONS) translations.translate(TranslationKey.CHOOSE_INSTALLMENTS_LIST_1ST_INSTALLMENT) else ""

            itemView.findViewById<TextView>(R.id.row_installments_one_text).text = installment.formatTopBody(additionalText)
            itemView.findViewById<TextView>(R.id.row_installments_total_text).text = installment.formatLowerBody(translations.translate(TranslationKey.CHOOSE_INSTALLMENTS_LIST_TOTAL_INSTALLMENTS))
        }

        private fun createStyleFromInfo(styleInfo: TextStyleInfo?, context: Context): TextViewStyle {
            return TextViewStyle(styleInfo, FontProvider(context))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowInstallmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflate: View = layoutInflater.inflate(R.layout.row_installment, parent, false)
        return RowInstallmentViewHolder(inflate)

    }

    override fun onBindViewHolder(holder: RowInstallmentViewHolder, position: Int) {
        holder.bindItem(installmentList[position], libraryStyle, translations, context)
        holder.itemView.setOnClickListener { listener(installmentList[position]) }
    }

    override fun getItemCount(): Int {
        return installmentList.size
    }
}