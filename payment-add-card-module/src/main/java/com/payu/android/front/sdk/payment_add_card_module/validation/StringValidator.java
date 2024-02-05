package com.payu.android.front.sdk.payment_add_card_module.validation;

import com.google.common.base.Optional;

public interface StringValidator {

    Optional<String> getErrorString(String text);
}
