package com.payu.android.front.sdk.payment_add_card_module.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;

public class CardSelectorView extends LinearLayout implements SelectorView {
    private final static int DESELECT_ITEM_ALPHA = 100;
    private final static int SELECTED_ITEM_ALPHA = 255;

    private ImageView masterCardImageView;
    private ImageView maestroCardImageView;
    private ImageView visaCardImageView;

    public CardSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public void clear() {
        deselectAll();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        masterCardImageView = findViewById(R.id.masterCard_imageView);
        maestroCardImageView = findViewById(R.id.maestroCard_imageView);
        visaCardImageView = findViewById(R.id.visaCard_imageView);

        Glide.with(getContext()).load(getImageUrl(CardIssuer.MASTER_CARD.getPath())).into(masterCardImageView);
        Glide.with(getContext()).load(getImageUrl(CardIssuer.MAESTRO.getPath())).into(maestroCardImageView);
        Glide.with(getContext()).load(getImageUrl(CardIssuer.VISA.getPath())).into(visaCardImageView);
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_card_chooser, this);
    }

    protected String getImageUrl(String path) {
        return new StaticContentUrlProvider(ConfigurationEnvironmentProvider.provideEnvironment(getContext())).get(path);
    }

    public void deselect(@NonNull CardIssuer issuer) {
        ImageView imageView = getImageView(issuer);
        if (imageView != null) {
            imageView.setImageAlpha(DESELECT_ITEM_ALPHA);
        }
    }

    public void select(@NonNull CardIssuer issuer) {
        ImageView imageView = getImageView(issuer);
        if (imageView != null) {
            imageView.setImageAlpha(SELECTED_ITEM_ALPHA);
        }
    }


    @Nullable
    private ImageView getImageView(@NonNull CardIssuer issuer) {
        switch (issuer) {
            case VISA:
                return visaCardImageView;
            case MAESTRO:
                return maestroCardImageView;
            case MASTER_CARD:
                return masterCardImageView;
            case UNKNOWN:
                return null;
            default:
                return null;
        }
    }

    private void deselectAll() {
        for (CardIssuer cardIssuer : CardIssuer.values()) {
            deselect(cardIssuer);
        }
    }
}
