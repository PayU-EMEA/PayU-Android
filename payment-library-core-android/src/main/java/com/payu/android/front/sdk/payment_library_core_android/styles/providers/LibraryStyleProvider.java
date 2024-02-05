package com.payu.android.front.sdk.payment_library_core_android.styles.providers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

import com.payu.android.front.sdk.payment_library_core_android.R;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ClassConfigurationException;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.ButtonStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;

import java.util.Arrays;

import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.ACCENT_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.BACKGROUND_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.BASIC_BUTTON_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.CONTENT_PADDING;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.DESCRIPTION_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.DISABLED_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.FONT_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.HEADER_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.HEADLINE_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.INPUT_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.PRIMARY_BUTTON_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.PRIMARY_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.SEPARATOR_COLOR;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.TEXT_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.TITLE_STYLE;
import static com.payu.android.front.sdk.payment_library_core_android.styles.providers.StyleResourceProperties.TOOLBAR_COLOR;

/**
 * REMINDER: When adding new Attribute to style please provide it to attr[] for its style, if it will not be provided,
 * There will be hard to tell issues with retrieving resources
 */
public class LibraryStyleProvider {
    private static final int NO_VALUE = Integer.MAX_VALUE;
    private static final int DEFAULT_DIMENSION = 0;
    private static final String STYLE_CONFIGURATION_EXCEPTION_MESSAGE =
            "Cannot resolve style information for property: %s. Please check if style configuration which you have provided has all the required "
                    + "fields.";

    /**
     *
     *     <attr name="payu_styles_accentColor" format="color|reference" />
     *         <attr name="payu_styles_backgroundColor" format="color|reference" />
     *         <attr name="payu_styles_toolbarColor" format="color|reference" />
     *         <attr name="payu_styles_primaryColor" format="color|reference" />
     *         <attr name="payu_styles_fontColor" format="color|reference" />
     *         <attr name="payu_styles_disabledColor" format="color|reference" />
     *         <attr name="payu_styles_separatorColor" format="color|reference" />
     *         <attr name="payu_styles_windowContentPadding" format="dimension|reference" />
     *         <attr name="payu_styles_textStyleButtonBasic" format="reference" />
     *         <attr name="payu_styles_textStyleButtonPrimary" format="reference" />
     *         <attr name="payu_styles_textStyleTitle" format="reference" />
     *         <attr name="payu_styles_textStyleHeader" format="reference" />
     *         <attr name="payu_styles_textStyleHeadline" format="reference" />
     *         <attr name="payu_styles_textStyleText" format="reference" />
     *         <attr name="payu_styles_textStyleInput" format="reference" />
     *         <attr name="payu_styles_textStyleDescription" format="reference" />
     *         <attr name="payu_styles_3ds" format="reference" />
     *
     * @param context
     * @return
     */

    public static LibraryStyleInfo fromContext(Context context) {
        StyleClassConfigurationProvider provider = StyleClassConfigurationFactory.createStyleClassProvider(context);
        int style = provider.getStyleFromConfiguration().payuStyle();
        int[] attrs = {
                R.attr.payu_styles_accentColor,R.attr.payu_styles_backgroundColor, R.attr.payu_styles_toolbarColor, R.attr.payu_styles_primaryColor,
                R.attr.payu_styles_fontColor, R.attr.payu_styles_disabledColor, R.attr.payu_styles_separatorColor, R.attr.payu_styles_windowContentPadding,
                R.attr.payu_styles_textStyleButtonBasic,  R.attr.payu_styles_textStyleButtonPrimary, R.attr.payu_styles_textStyleTitle,
                R.attr.payu_styles_textStyleHeader, R.attr.payu_styles_textStyleHeadline,
                R.attr.payu_styles_textStyleText, R.attr.payu_styles_textStyleInput,
                R.attr.payu_styles_textStyleDescription, R.attr.payu_styles_3ds};
        Arrays.sort(attrs);
        TypedArray resources = context.obtainStyledAttributes(style, attrs);
        int accentColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_accentColor, ACCENT_COLOR);
        int backgroundColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_backgroundColor, BACKGROUND_COLOR);
        int toolbarColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_toolbarColor, TOOLBAR_COLOR);
        int primaryColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_primaryColor, PRIMARY_COLOR);
        int textColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_fontColor, FONT_COLOR);
        int disabledColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_disabledColor, DISABLED_COLOR);
        int separatorColor = getColor(resources, R.styleable.Theme_PayU_Fronts_payu_styles_separatorColor, SEPARATOR_COLOR);
        float windowContentPadding = getDimension(resources, R.styleable.Theme_PayU_Fronts_payu_styles_windowContentPadding, CONTENT_PADDING);
        int buttonStyleBasic = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleButtonBasic, BASIC_BUTTON_STYLE);
        int buttonTypePrimary = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleButtonPrimary, PRIMARY_BUTTON_STYLE);
        int textStyleTitle = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleTitle, TITLE_STYLE);
        int textStyleHeader = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleHeader, HEADER_STYLE);
        int textStyleHeadline = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleHeadline, HEADLINE_STYLE);
        int textStyleText = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleText, TEXT_STYLE);
        int textStyleDescription = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleDescription, DESCRIPTION_STYLE);
        int textStyleInput = getResourceId(resources, R.styleable.Theme_PayU_Fronts_payu_styles_textStyleInput, INPUT_STYLE);

        resources.recycle();

        return new LibraryStyleInfo.Builder()
                .withBackgroundColor(backgroundColor)
                .withToolbarColor(toolbarColor)
                .withPrimaryColor(primaryColor)
                .withTextColor(textColor)
                .withDisabledColor(disabledColor)
                .withAccentColor(accentColor)
                .withSeparatorColor(separatorColor)
                .withTextStyleButtonBasic(forButtonStyle(context, buttonStyleBasic, textColor, disabledColor))
                .withTextStyleButtonPrimary(forButtonStyle(context, buttonTypePrimary, textColor, disabledColor))
                .withTextStyleTitle(forStyle(context, textStyleTitle, textColor, disabledColor))
                .withTextStyleHeader(forStyle(context, textStyleHeader, textColor, disabledColor))
                .withTextStyleHeadline(forStyle(context, textStyleHeadline, textColor, disabledColor))
                .withTextStyleText(forStyle(context, textStyleText, textColor, disabledColor))
                .withTextStyleInput(forInputTextStyle(context, textStyleInput, textColor, disabledColor))
                .withWindowContentPadding(windowContentPadding)
                .withTextStyleDescription(forStyle(context, textStyleDescription, textColor, disabledColor))
                .build();
    }

    private static TextStyleInfo forStyle(Context context, int style, int defaultColor, int disabledColor) {
        int[] attrs = {
                R.attr.payu_styles_textSize, R.attr.payu_styles_textColor, R.attr.payu_styles_buttonBackgroundColor, R.attr.payu_styles_buttonDisabledBackgroundColor,
                R.attr.payu_styles_font, R.attr.payu_styles_fontPath, R.attr.payu_styles_paddingBottom, R.attr.payu_styles_paddingTop, R.attr.payu_styles_paddingRight,
                R.attr.payu_styles_paddingLeft};
        Arrays.sort(attrs);
        TypedArray typedArray = context.obtainStyledAttributes(style, attrs);
        TextStyleInfo styleInfo = getTextStyleInfo(defaultColor, disabledColor, typedArray);
        typedArray.recycle();
        return styleInfo;
    }

    private static ButtonStyleInfo forButtonStyle(Context context, int style, int defaultColor, int disabledColor) {
        int[] attrs = {
                R.attr.payu_styles_textSize, R.attr.payu_styles_textColor, R.attr.payu_styles_buttonBackgroundColor, R.attr.payu_styles_buttonDisabledBackgroundColor,
                R.attr.payu_styles_font, R.attr.payu_styles_fontPath, R.attr.payu_styles_paddingBottom, R.attr.payu_styles_paddingTop, R.attr.payu_styles_paddingRight,
                R.attr.payu_styles_paddingLeft};
        Arrays.sort(attrs);
        TypedArray typedArray = context.obtainStyledAttributes(style, attrs);
        int backgroundColor = typedArray.getColor(R.styleable.Style_PayU_Fronts_Text_payu_styles_buttonBackgroundColor, defaultColor);
        int disabledBackgroundColor = typedArray.getColor(R.styleable.Style_PayU_Fronts_Text_payu_styles_buttonDisabledBackgroundColor, defaultColor);
        TextStyleInfo styleInfo = getTextStyleInfo(defaultColor, disabledColor, typedArray);
        typedArray.recycle();
        return new ButtonStyleInfo(styleInfo, backgroundColor, disabledBackgroundColor);
    }

    private static TextStyleInfo forInputTextStyle(Context context, int style, int defaultColor, int disabledColor) {
        int[] attrs = {
                R.attr.payu_styles_textSize, R.attr.payu_styles_textColor, R.attr.payu_styles_buttonBackgroundColor, R.attr.payu_styles_buttonDisabledBackgroundColor,
                R.attr.payu_styles_font, R.attr.payu_styles_fontPath, R.attr.payu_styles_paddingBottom, R.attr.payu_styles_paddingTop, R.attr.payu_styles_paddingRight,
                R.attr.payu_styles_paddingLeft};
        Arrays.sort(attrs);
        TypedArray typedArray = context.obtainStyledAttributes(style, attrs);
        TextStyleInfo styleInfo = getEditTextStyleInfo(defaultColor, disabledColor, typedArray);
        typedArray.recycle();
        return styleInfo;
    }

    private static TextStyleInfo getEditTextStyleInfo(int defaultColor, int disabledColor, TypedArray typedArray) {
        //Padding is not set for editText
        int fontRes = typedArray.getResourceId(R.styleable.Style_PayU_Fronts_Text_payu_styles_font, NO_VALUE);
        String fontPath = typedArray.getString(R.styleable.Style_PayU_Fronts_Text_payu_styles_fontPath);
        int color = typedArray.getColor(R.styleable.Style_PayU_Fronts_Text_payu_styles_textColor, defaultColor);
        float textSize = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_textSize, DEFAULT_DIMENSION);


        return new TextStyleInfo.Builder()
                .withTextColor(getColorStateList(color, disabledColor))
                .withTextSize(textSize)
                .withFontPath(fontPath)
                .withFont(fontRes)
                .build();
    }

    private static TextStyleInfo getTextStyleInfo(int defaultColor, int disabledColor, TypedArray typedArray) {
        int fontRes = typedArray.getResourceId(R.styleable.Style_PayU_Fronts_Text_payu_styles_font, NO_VALUE);
        String fontPath = typedArray.getString(R.styleable.Style_PayU_Fronts_Text_payu_styles_fontPath);
        float paddingBottom = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_paddingBottom, DEFAULT_DIMENSION);
        float paddingTop = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_paddingTop, DEFAULT_DIMENSION);
        float paddingLeft = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_paddingLeft, DEFAULT_DIMENSION);
        float paddingRight = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_paddingRight, DEFAULT_DIMENSION);
        int color = typedArray.getColor(R.styleable.Style_PayU_Fronts_Text_payu_styles_textColor, defaultColor);
        float textSize = typedArray.getDimension(R.styleable.Style_PayU_Fronts_Text_payu_styles_textSize, DEFAULT_DIMENSION);
        return new TextStyleInfo.Builder()
                .withTextColor(getColorStateList(color, disabledColor))
                .withTextSize(textSize)
                .withPaddingBottom(paddingBottom)
                .withPaddingTop(paddingTop)
                .withPaddingLeft(paddingLeft)
                .withPaddingRight(paddingRight)
                .withFontPath(fontPath)
                .withFont(fontRes)
                .build();
    }

    private static int getColor(TypedArray typedArray, int styleableRes, String errorMissingResourceName) {
        if (typedArray.hasValue(styleableRes)) {
            int color = typedArray.getColor(styleableRes, NO_VALUE);
            if (color != NO_VALUE) {
                return color;
            }
        }
        throw new ClassConfigurationException(String.format(STYLE_CONFIGURATION_EXCEPTION_MESSAGE, errorMissingResourceName));
    }

    private static float getDimension(TypedArray typedArray, int styleableRes, String errorMissingResourceName) {
        if (typedArray.hasValue(styleableRes)) {
            float dimension = typedArray.getDimension(styleableRes, NO_VALUE);
            if (dimension != NO_VALUE) {
                return dimension;
            }
        }
        throw new ClassConfigurationException(String.format(STYLE_CONFIGURATION_EXCEPTION_MESSAGE, errorMissingResourceName));
    }

    private static int getResourceId(TypedArray typedArray, int styleableRes, String errorMissingResourceName) {
        if (typedArray.hasValue(styleableRes)) {
            int color = typedArray.getResourceId(styleableRes, NO_VALUE);
            if (color != NO_VALUE) {
                return color;
            }
        }
        throw new ClassConfigurationException(String.format(STYLE_CONFIGURATION_EXCEPTION_MESSAGE, errorMissingResourceName));
    }

    private static ColorStateList getColorStateList(int textColor, int disabledColor) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}  // disabled
        };

        int[] colors = new int[]{
                textColor,
                disabledColor
        };

        return new ColorStateList(states, colors);
    }
}
