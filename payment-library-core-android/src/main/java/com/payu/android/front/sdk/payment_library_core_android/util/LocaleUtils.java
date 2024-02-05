package com.payu.android.front.sdk.payment_library_core_android.util;

import android.content.Context;

import java.util.Locale;

public final class LocaleUtils {

  private LocaleUtils() { }

  public static String getDeviceDefaultLocale(Context context) {
    Locale defaultLocale = context.getResources().getConfiguration().locale;
    String language = defaultLocale.getLanguage();
    String country = defaultLocale.getCountry();
    return language + "_" + country;
  }

}