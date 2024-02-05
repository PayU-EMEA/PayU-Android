package com.payu.android.front.sdk.payment_library_core_android.configuration;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

/**
 * This interface has to be implemented in order to provide custom style from PayU Mobile SDK.
 */
public interface StyleConfiguration {
    /**
     * Icon was not provided
     */
    int NO_ICON = -1;

    /**
     * url path was not provided
     */
    String EMPTY_PATH = "";

    /**
     * Provided style will override default properties provided by PayU. This style needs to be a child of @style/Theme.PayU.Fronts.
     * Supported list of configurable properties:
     * <p>
     * <declare-styleable name="Theme.PayU.Fronts">
     * <attr name="payu_styles_accentColor" format="color|reference" />
     * <attr name="payu_styles_backgroundColor" format="color|reference" />
     * <attr name="payu_styles_primaryColor" format="color|reference" />
     * <attr name="payu_styles_fontColor" format="color|reference" />
     * <attr name="payu_styles_disabledColor" format="color|reference" />
     * <attr name="payu_styles_separatorColor" format="color|reference" />
     * <attr name="payu_styles_windowContentPadding" format="dimension|reference" />
     * <attr name="payu_styles_textStyleButtonBasic" format="reference" />
     * <attr name="payu_styles_textStyleButtonPrimary" format="reference" />
     * <attr name="payu_styles_textStyleTitle" format="reference" />
     * <attr name="payu_styles_textStyleHeader" format="reference" />
     * <attr name="payu_styles_textStyleHeadline" format="reference" />
     * <attr name="payu_styles_textStyleText" format="reference" />
     * <attr name="payu_styles_textStyleInput" format="reference" />
     * <attr name="payu_styles_textStyleDescription" format="reference" />
     * </declare-styleable>
     * <p>
     * Where all the properties starting with 'payu_styles_textStyle*' can be configured with style defining following attributes:
     * <p>
     * <declare-styleable name="Style.PayU.Fronts.Text">
     * <attr name="payu_styles_textColor" format="color|reference" />
     * <attr name="payu_styles_textSize" format="dimension|reference" />
     * <attr name="payu_styles_fontPath" format="string|reference" />
     * <attr name="payu_styles_font" format="reference" />
     * <attr name="payu_styles_paddingTop" format="dimension|reference" />
     * <attr name="payu_styles_paddingBottom" format="dimension|reference" />
     * <attr name="payu_styles_paddingLeft" format="dimension|reference" />
     * <attr name="payu_styles_paddingRight" format="dimension|reference" />
     * </declare-styleable>
     * </p>
     * <p>
     * For the attributes, which have not been overridden, default PayU style will be used.
     */
    @StyleRes
    int payuStyle();

    /**
     * Provided drawable will be used as library icon visible in payu_toolbar
     * if {@linkplain StyleConfiguration#NO_ICON} library will use PayU logo as payu_toolbar icon
     *
     * @return drawable resource or {@linkplain StyleConfiguration#NO_ICON}
     */
    @DrawableRes
    int payuLibraryIcon();

    /**
     * Provided path to resource in www.
     * Icon will be displayed in AddNewCardButton on PaymentChooserActivity
     *
     * @return path to icon
     */
    @DrawableRes
    int pathIconAddNewCard();

    /**
     * Provided path to resource in www.
     * Icon will be displayed in PayByLinkButton on PaymentChooserActivity
     *
     * @return path to icon
     */
    @DrawableRes
    int pathIconPBLPayment();

}
