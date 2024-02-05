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

-include ../proguard/guava-android.txt

#Google Pay
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

#Entrypoints
-keep class com.payu.android.front.sdk.payment_library_google_pay_module.service.** {*;}
-keep class com.payu.android.front.sdk.payment_library_google_pay_module.model.** {*;}
-keep class com.payu.android.front.sdk.payment_library_google_pay_module.builder.** {*;}
-keep class com.payu.android.front.sdk.payment_library_google_pay_module.listener.** {*;}