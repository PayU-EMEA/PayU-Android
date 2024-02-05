package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemRemovedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.view.SwipeRevealLayout;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.view.ViewBinderHelper;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {
    @NonNull
    private final List<PaymentMethodModel> methodModels;
    @NonNull
    private final OnItemSelectedListener<PaymentMethodModel> listener;
    @NonNull
    private final OnItemRemovedListener<PaymentMethodModel> removedListener;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    PaymentMethodAdapter(
            @NonNull List<PaymentMethodModel> methodModels, @NonNull OnItemSelectedListener<PaymentMethodModel> listener,
            @NonNull OnItemRemovedListener<PaymentMethodModel> removedListener) {
        this.methodModels = methodModels;
        this.listener = listener;
        this.removedListener = removedListener;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return methodModels.get(position).getId().hashCode();
    }

    public void setMethodModels(@NonNull List<PaymentMethodModel> methodModels) {
        this.methodModels.clear();
        this.methodModels.addAll(methodModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.payu_item_payment_method, parent, false);
        return new PaymentMethodViewHolder(inflate, listener, removedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, final int position) {
        PaymentMethodModel model = methodModels.get(position);
        viewBinderHelper.bind(holder.swipeRevealLayout, model.getId());
        if (!model.canBeRemoved()) {
            viewBinderHelper.lockSwipe(model.getId());
        } else {
            viewBinderHelper.unlockSwipe(model.getId());
        }
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return methodModels.size();
    }

    class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        private final SwipeRevealLayout swipeRevealLayout;
        private final View deleteButton;
        private final PaymentMethodView itemView;
        private final OnItemSelectedListener<PaymentMethodModel> selectedListener;
        private final OnItemRemovedListener<PaymentMethodModel> removedListener;
        private PaymentMethodModel model;

        PaymentMethodViewHolder(
                @NonNull View itemView, @NonNull OnItemSelectedListener<PaymentMethodModel> selectedListener,
                @NonNull OnItemRemovedListener<PaymentMethodModel> removedListener) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.item_paymentMethodView);
            this.swipeRevealLayout = itemView.findViewById(R.id.swipe_reveal_layout);
            this.deleteButton = itemView.findViewById(R.id.payment_method_delete_view);
            this.selectedListener = selectedListener;
            this.removedListener = removedListener;
            setupListener();
        }

        void bind(@NonNull PaymentMethodModel model) {
            itemView.bindModel(model);
            this.model = model;
        }

        private void setupListener() {
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedListener.onSelected(model);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removedListener.onRemoved(model);
                }
            });

        }
    }
}
