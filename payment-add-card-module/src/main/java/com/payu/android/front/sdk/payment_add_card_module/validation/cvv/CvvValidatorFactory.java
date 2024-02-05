package com.payu.android.front.sdk.payment_add_card_module.validation.cvv;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;

import java.util.Map;

public class CvvValidatorFactory {

    private static final Map<CardIssuer, CvvValidator> ISSUER_SPECIFIC_VALIDATOR_MAP = Maps.newEnumMap(CardIssuer.class);
    private static final GenericCvvValidator DEFAULT_VALIDATOR = new GenericCvvValidator();

    public CvvValidator getValidator(CardIssuer cardIssuer) {
        return MoreObjects.firstNonNull(ISSUER_SPECIFIC_VALIDATOR_MAP.get(cardIssuer), DEFAULT_VALIDATOR);
    }

    static {
        ISSUER_SPECIFIC_VALIDATOR_MAP.put(CardIssuer.MAESTRO, new MaestroCvvValidator());
    }
}
