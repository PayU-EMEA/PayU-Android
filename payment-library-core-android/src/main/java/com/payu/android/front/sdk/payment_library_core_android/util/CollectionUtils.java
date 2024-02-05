package com.payu.android.front.sdk.payment_library_core_android.util;

import java.util.List;

public final class CollectionUtils {

    private CollectionUtils() {
        // This is an utility class
    }

    public static boolean isOneOf(Object object, List<?> itemList) {
        return (itemList != null) && itemList.contains(object);
    }
}
