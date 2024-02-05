package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorView;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer.UNKNOWN;

public class CardSelectorPresenter implements CardSelector {
    private CardIssuer lastSelected;
    private SelectorView selectorView;

    public CardSelectorPresenter() {
        lastSelected = UNKNOWN;
    }

    public void selectIssuer(@NonNull CardIssuer currentIssuer) {
        checkArgument(selectorView != null, "View should be set");
        if (currentIssuer == lastSelected) {
            return;
        }
        lastSelected = currentIssuer;
        if (lastSelected != UNKNOWN) {
            deselectAll();
            select(lastSelected);
        } else {
            selectAll();
        }
    }

    private void deselect(@NonNull CardIssuer oldIssuer) {
        selectorView.deselect(oldIssuer);

    }

    private void select(@NonNull CardIssuer issuer) {
        selectorView.select(issuer);
    }

    private void selectAll() {
        for (CardIssuer cardIssuer : CardIssuer.values()) {
            select(cardIssuer);
        }
    }

    private void deselectAll() {
        for (CardIssuer cardIssuer : CardIssuer.values()) {
            deselect(cardIssuer);
        }
    }

    public void takeView(@NonNull SelectorView view) {
        this.selectorView = view;
        selectAll();
    }
}
