package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view.PaymentMethodView;

import java.util.List;

public class BlikAmbiguityAdapter extends RecyclerView.Adapter<BlikAmbiguityAdapter.BlikAmbiguityViewHolder> {
    @NonNull
    private final OnItemSelectedListener<PaymentMethodModel> listener;
    @NonNull
    private List<PaymentMethodModel> methodModels;

    BlikAmbiguityAdapter(@NonNull List<PaymentMethodModel> methodModel, @NonNull OnItemSelectedListener<PaymentMethodModel> listener) {
        this.methodModels = methodModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BlikAmbiguityAdapter.BlikAmbiguityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.payu_item_blik, parent, false);
        return new BlikAmbiguityViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BlikAmbiguityAdapter.BlikAmbiguityViewHolder holder, int position) {
        holder.bind(methodModels.get(position));

    }

    public void setModel(@NonNull List<PaymentMethodModel> paymentMethods) {
        this.methodModels = paymentMethods;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return methodModels.size();
    }

    class BlikAmbiguityViewHolder extends RecyclerView.ViewHolder {
        private OnItemSelectedListener<PaymentMethodModel> selectedListener;
        private PaymentMethodView itemView;
        private PaymentMethodModel model;

        public BlikAmbiguityViewHolder(View itemView, OnItemSelectedListener<PaymentMethodModel> listener) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.item_paymentMethodView);
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

        void bind(@NonNull PaymentMethodModel model) {
            itemView.bindModel(model);
            this.model = model;
        }
    }
}
