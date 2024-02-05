# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# --------------------------------------------------------------------
# REMOVE all Log messages except warnings and errors
# --------------------------------------------------------------------
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

-include ../proguard/guava-android.txt
-include ../proguard/glide.txt
-include ../proguard/android.txt



-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.service.** {*;}
-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.widget.** {*;}
-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.** {*;}
-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.** {*;}
-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.** {*;}
-keep class com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.** {*;}
