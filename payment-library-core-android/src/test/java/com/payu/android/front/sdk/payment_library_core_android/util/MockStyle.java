package com.payu.android.front.sdk.payment_library_core_android.util;

import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleConfiguration;

public class MockStyle implements StyleConfiguration {
    public MockStyle() {
    }

    @Override
    public int payuStyle() {

        return -1;
    }

    @Override
    public int payuLibraryIcon() {
        return 0;
    }

    @Override
    public int pathIconAddNewCard() {
        return NO_ICON;
    }

    @Override
    public int pathIconPBLPayment() {
        return NO_ICON;
    }

    public class InternalStyle implements StyleConfiguration {
        public InternalStyle() {
        }

        @Override
        public int payuStyle() {
            return -1;
        }

        @Override
        public int payuLibraryIcon() {
            return 0;
        }

        @Override
        public int pathIconAddNewCard() {
            return NO_ICON;
        }

        @Override
        public int pathIconPBLPayment() {
            return NO_ICON;
        }

    }
}

