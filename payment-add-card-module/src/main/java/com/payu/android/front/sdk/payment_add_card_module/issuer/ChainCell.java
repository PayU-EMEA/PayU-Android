package com.payu.android.front.sdk.payment_add_card_module.issuer;

public interface ChainCell<T> {

    T getNext();

    void setNext(T next);
}
