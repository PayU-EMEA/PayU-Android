package com.payu.android.front.sdk.payment_library_core_android.styles;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StylerTest {
    private TextViewStyle styler;
    @Mock
    private TextStyleInfo textStyleInfo;
    @Mock
    private Context context;
    @Mock
    private TextView textView;
    @Mock
    private FontProvider fontProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        styler = new TextViewStyle(textStyleInfo, fontProvider);
    }

    @Test
    public void testTextSizeIsSetProperly() {
        //given
        TextView textView = mock(TextView.class);
        float textSize = 1.f;
        when(textStyleInfo.getTextSize()).thenReturn(textSize);

        //when
        styler.applyTo(textView);

        //then
        verify(textView, times(1)).setTextSize(eq(TypedValue.COMPLEX_UNIT_PX), eq(textSize));
    }

    @Test
    public void testTextColorIsSetProperly() {
        //given
        TextView textView = mock(TextView.class);
        int textColor = 1;
        ColorStateList color = new ColorStateList(new int[1][1], new int[1]);
        when(textStyleInfo.getTextColor()).thenReturn(color);

        //when
        styler.applyTo(textView);

        //then
        verify(textView, times(1)).setTextColor(eq(color));
    }

    @Test
    public void testFontIsSetWhenNotEmpty() {
        //given
        TextView textView = mock(TextView.class);
        when(fontProvider.loadTypeface(anyInt(), any())).thenReturn(mock(Typeface.class));

        //when
        styler.applyTo(textView);

        //then
        verify(textView, times(1)).setTypeface(any(Typeface.class));
    }
    @Test
    public void testFontIsNotSetWhenEmpty() {
        //given
        TextView textView = mock(TextView.class);
        when(fontProvider.loadTypeface(anyInt(), any())).thenReturn(null);

        //when
        styler.applyTo(textView);

        //then
        verify(textView, times(0)).setTypeface(any(Typeface.class));
    }

}