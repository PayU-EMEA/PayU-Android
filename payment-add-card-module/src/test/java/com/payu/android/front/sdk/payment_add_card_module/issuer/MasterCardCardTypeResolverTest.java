package com.payu.android.front.sdk.payment_add_card_module.issuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class MasterCardCardTypeResolverTest {

    CardIssuerProvider objectUnderTest = CardIssuerProvider.getInstance();

    private final String mCardNumber;

    public MasterCardCardTypeResolverTest(final String card) {
        mCardNumber = card;
    }

    @Parameterized.Parameters(name = "{index}: shouldReturnCardTypeMasterCard({0})")
    public static Iterable<String> maestroCard() {
        return Arrays.asList("5190356833536357", "5315698426102067", "5103204650483012", "5137642240677777",
                "5173187481032519", "5353581133484056", "5283267444836862", "5538482073217697", "5164448615050889",
                "5475504997070285", "5487952300016170", "5496371886873855", "5313191154555575", "5164860132018993",
                "5284698890945501", "5117171042184109", "5219594283074027", "5563970142195371", "5557367952136215",
                "5134997968011200", "5457605963915155", "5137262160128139", "5533681297250278", "5433871133393421",
                "5255299881179435", "5161704575619912", "5243598798580758", "5519980685251965", "5197331830528106",
                "5586896786462624", "5269457798628159", "5426613419296665", "5538208655353075", "5113849453972161",
                "5583822020685039", "5501024973436309", "5557830652782463", "5559760785769194", "5359507438190878",
                "5233283944729490", "5568418015453654", "5352886060905630", "5312371889804362", "5591344120191198",
                "5473196467346154", "5141932286417509", "5570554740207555", "5360511554830355", "5585765310050356",
                "5581251863427360", "2222405343248877", "2226350165017735", "2225698426102067", "2223204650483012");
    }

    @Test
    public void shouldReturnCardTypeMasterCard() {
        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(mCardNumber);

        // expect
        assertThat(cardProvider).overridingErrorMessage(
                "Expected: " + CardIssuer.MASTER_CARD + " but was: " + cardProvider + " for number: " + mCardNumber).isEqualTo(CardIssuer.MASTER_CARD);
    }
}
