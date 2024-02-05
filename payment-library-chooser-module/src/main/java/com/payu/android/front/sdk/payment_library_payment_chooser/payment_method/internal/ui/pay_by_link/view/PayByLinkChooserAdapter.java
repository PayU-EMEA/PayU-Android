package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.payu.android.front.sdk.payment_library_core_android.util.ThemeUtils;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;

import java.util.List;

public class PayByLinkChooserAdapter extends RecyclerView.Adapter<PayByLinkChooserAdapter.PayByLinkViewHolder> {
    private final OnItemSelectedListener<PayByLinkModel> listener;
    private List<PayByLinkModel> methodModels;

    PayByLinkChooserAdapter(@NonNull List<PayByLinkModel> methodModels, @NonNull OnItemSelectedListener<PayByLinkModel> listener) {
        this.methodModels = methodModels;
        this.listener = listener;
    }

    @Override
    public PayByLinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.payu_item_pay_by_link, parent, false);
        return new PayByLinkViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(PayByLinkViewHolder holder, int position) {
        holder.bind(methodModels.get(position));
    }

    @Override
    public int getItemCount() {
        return methodModels.size();
    }

    public void setModel(@NonNull List<PayByLinkModel> payByLinkModels) {
        methodModels = payByLinkModels;
        notifyDataSetChanged();
    }

    class PayByLinkViewHolder extends RecyclerView.ViewHolder {

        private final ImageView payByLinkImage;
        private OnItemSelectedListener<PayByLinkModel> selectedListener;
        private PayByLinkModel model;

        PayByLinkViewHolder(View itemView, OnItemSelectedListener<PayByLinkModel> listener) {
            super(itemView);
            payByLinkImage = itemView.findViewById(R.id.pay_by_link_icon_imageView);
            selectedListener = listener;
            setupListener();
        }

        private void setupListener() {
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedListener.onSelected(model);
                }
            });
        }

        void bind(@NonNull PayByLinkModel model) {
            this.model = model;
            Glide.with(itemView.getContext()).load(model.getImageUrl()).into(payByLinkImage);
            itemView.setEnabled(model.isEnabled());
            if (ThemeUtils.isInNightMode(itemView.getContext())) {
                itemView.setBackgroundResource(R.drawable.payu_styles_pbl_button_selector_for_dark_theme);
            }
        }
    }
}
