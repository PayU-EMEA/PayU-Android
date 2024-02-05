package com.payu.android.front.sdk.payment_add_card_module.issuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class VisaCardTypeResolverTest {

    CardIssuerProvider objectUnderTest = CardIssuerProvider.getInstance();

    private final String mCardNumber;

    public VisaCardTypeResolverTest(final String card) {
        mCardNumber = card;
    }

    @Parameterized.Parameters(name = "{index}: shouldReturnCardTypeVisa({0})")
    public static Iterable<String> maestroCard() {
        return Arrays.asList("4483615377669679", "4639269673944", "4971519970418287", "4142215232100490",
                "4282443331473309", "4826147761989", "4931459794139", "4385626718323198", "4398591747617124", "4859321092830",
                "4155194944128889", "4502688620820115", "4629248643050", "4934994163035113", "4229691516480897",
                "4389879396699118", "4033992154705", "4782320207136", "4896717811111424", "4997276408438250", "4832504385404",
                "4659348066606558", "4475668919350", "4808564498305078", "4721769235027059", "4843709159999", "4598160237169",
                "4764479754027", "4030030089896917", "4735802259235730", "4240343352331934", "4907597813841",
                "4310034178503732", "4830221980762041", "4777781198059428", "4216239437401", "4618286054171", "4079922244975",
                "4811652023951659", "4074572564898287", "4077242455740900", "4712270426906830", "4332056301875",
                "4876458775728416", "4092536742382462", "4057703670253", "4093003356921", "4269416421201", "4560585895294",
                "4530223340392934");
    }

    @Test
    public void shouldReturnCardTypeVisa() {
        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(mCardNumber);

        // expect
        assertThat(cardProvider).overridingErrorMessage(
                "Expected: " + CardIssuer.VISA + " but was: " + cardProvider + " for number: " + mCardNumber).isEqualTo(CardIssuer.VISA);
    }
}
