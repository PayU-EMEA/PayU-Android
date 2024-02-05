package com.payu.android.front.sdk.payment_library_core_android.styles;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.ColorUtils;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;

public class PayUDefaultDialogBuilder {
    interface WithCustomProperties {
        void apply(MaterialAlertDialogBuilder builder);
    }

    private static final int NEGATIVE_BUTTON_ALPHA_VALUE = 175;

    private final MaterialAlertDialogBuilder dialogBuilderDelegate;
    private final LibraryStyleInfo libraryStyleInfo;
    private boolean cancelOnTouchOutside = true;
    private final DialogInterface.OnClickListener defaultOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    public PayUDefaultDialogBuilder(@NonNull Context context) {
        this(context, 0);
    }

    public PayUDefaultDialogBuilder(@NonNull Context context, int overrideThemeResId) {
        libraryStyleInfo = LibraryStyleProvider.fromContext(context);
        dialogBuilderDelegate = new MaterialAlertDialogBuilder(context, overrideThemeResId);
    }

    public PayUDefaultDialogBuilder setTitle(String title) {
        dialogBuilderDelegate.setTitle(title);
        return this;
    }

    public PayUDefaultDialogBuilder setView(int viewResId) {
        dialogBuilderDelegate.setView(viewResId);
        return this;
    }

    public PayUDefaultDialogBuilder setView(View view) {
        dialogBuilderDelegate.setView(view);
        return this;
    }

    public PayUDefaultDialogBuilder setMessage(String message) {
        dialogBuilderDelegate.setMessage(message);
        return this;
    }

    public PayUDefaultDialogBuilder setPositiveButton(CharSequence text) {
        return setPositiveButton(text, null);
    }

    public PayUDefaultDialogBuilder setPositiveButton(CharSequence text, @Nullable DialogInterface.OnClickListener listener) {
        if (listener == null) {
            listener = defaultOnClickListener;
        }
        dialogBuilderDelegate.setPositiveButton(text, listener);
        return this;
    }

    public PayUDefaultDialogBuilder setNegativeButton(CharSequence text) {
        return setNegativeButton(text, null);
    }

    public PayUDefaultDialogBuilder setNegativeButton(CharSequence text, @Nullable DialogInterface.OnClickListener listener) {
        if (listener == null) {
            listener = defaultOnClickListener;
        }
        dialogBuilderDelegate.setNegativeButton(text, listener);
        return this;
    }

    public PayUDefaultDialogBuilder setCancelable(boolean cancel) {
        cancelOnTouchOutside = cancel;
        dialogBuilderDelegate.setCancelable(cancel);
        return this;
    }

    public AlertDialog create() {
        return create(null);
    }

    public AlertDialog create(@Nullable WithCustomProperties withCustomProperties) {
        if (withCustomProperties != null) {
            withCustomProperties.apply(dialogBuilderDelegate);
        }
        final AlertDialog payUDialog = dialogBuilderDelegate.create();
        payUDialog.setCanceledOnTouchOutside(cancelOnTouchOutside);
        payUDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                applyStyleForActionButtons(payUDialog);
            }
        });
        applyStyleForBackground(payUDialog);
        return payUDialog;
    }

    public AlertDialog show() {
        return show(null);
    }

    public AlertDialog show(@Nullable WithCustomProperties withCustomProperties) {
        AlertDialog dialog = create(withCustomProperties);
        dialog.show();
        return dialog;
    }

    private void applyStyleForActionButtons(AlertDialog payUDialog) {
        Button positiveButton = payUDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = payUDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(libraryStyleInfo.getAccentColor());
            positiveButton.invalidate();
        }
        if (negativeButton != null) {
            int textDefaultColor = libraryStyleInfo.getTextStyleButtonBasic().getTextStyleInfo().getTextColor().getDefaultColor();
            negativeButton.setTextColor(ColorUtils.setAlphaComponent(textDefaultColor, NEGATIVE_BUTTON_ALPHA_VALUE));
        }
    }

    private void applyStyleForBackground(AlertDialog payUDialog) {
        if (payUDialog.getWindow() != null) {
            payUDialog.getWindow().setBackgroundDrawable(new ColorDrawable(libraryStyleInfo.getBackgroundColor()));
        }
    }
}
