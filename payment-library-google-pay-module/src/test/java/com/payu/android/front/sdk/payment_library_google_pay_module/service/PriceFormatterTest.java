package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class PriceFormatterTest {
    private PriceFormatter objectUnderTest;

    @DataProvider
    public static Object[][] priceDataProvider() {
        return new Object[][] {
                { 100, "1.00" },
                { 150, "1.50" },
                { 155, "1.55" },
                { 60, "0.60" },
                { 1, "0.01" }
        };
    }

    @Test
    @UseDataProvider("priceDataProvider")
    public void shouldReturnProperFormattedPrice(int price, String expected) {
        //given
        PriceFormatter priceFormatter = new PriceFormatter(price);

        //when
        //then
        assertThat(priceFormatter.asString()).isEqualTo(expected);
    }
}
