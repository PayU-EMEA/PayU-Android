package com.payu.android.front.sdk.payment_add_card_module.issuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class VisaElectronCardTypeResolverTest {

    CardIssuerProvider objectUnderTest = CardIssuerProvider.getInstance();

    private final String mCardNumber;

    public VisaElectronCardTypeResolverTest(final String card) {
        mCardNumber = card;
    }

    @Parameterized.Parameters(name = "{index}: shouldReturnCardTypeVisa({0})")
    public static Iterable<String> maestroCard() {
        return Arrays.asList("4175002650864538", "4026824727747029", "4175006039779408", "4175001530286581",
                "4508832019589717", "4844755525928173", "4175001007582421", "4508434013720578", "4844001029201308",
                "4175001604028562", "4175005763068822", "4917839031055176", "4913781710273841", "4917156948922939",
                "4844142759437603", "4917736011036632", "4026423717321695", "4913540311294998", "4844099662704182",
                "4844829089744779", "4844905394094967", "4026241760342248", "4026125603293736", "4508133630754826",
                "4026855189202863", "4175001815548788", "4175003120669879", "4913439232250975", "4175007973460286",
                "4508404074952386", "4175006149648469", "4844449809193593", "4917673535917536", "4175004063439973",
                "4917778962461047", "4508670598030533", "4913980977845743", "4175000891936511", "4844104134771650",
                "4026528474116102", "4844208960571090", "4844562637780629", "4913040750281176", "4913124233889050",
                "4175008319947085", "4913838029296424", "4026754544597325", "4917224022706519", "4917437180255604",
                "4026159891823227");
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
