package com.payu.android.front.sdk.payment_library_core.util;

import java.lang.reflect.Array;

public class ArrayUtils {

    @SuppressWarnings("unchecked")
    public <K> K[] asArray(K instance) {
        K[] resultArray = (K[]) Array.newInstance(instance.getClass(), 1);
        resultArray[0] = instance;
        return resultArray;
    }
}