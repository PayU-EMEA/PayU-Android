package com.payu.android.front.sdk.payment_add_card_module.issuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class MaestroCardTypeResolverTest {

    CardIssuerProvider objectUnderTest = CardIssuerProvider.getInstance();

    private final String mCardNumber;

    public MaestroCardTypeResolverTest(final String card) {
        mCardNumber = card;
    }

    @Parameterized.Parameters(name = "{index}: shouldReturnCardTypeMaestro({0})")
    public static Iterable<String> maestroCard() {
        return Arrays.asList("6759661663221391204", "63043187042137291", "676396413045368283",
                "6304805488293", "5020790344411757", "67636515344502503",
                "6761500686697238915", "6759814068514549607", "6763108974848", "6761364852906", "50388873422022233",
                "676276306442330671", "5020841184114638776", "6761528583006313",
                "6761818205966", "6759951822312389",
                "5018428730125491", "6763653649795", "5018892337392574", "6763812452389925312", "6304311392633",
                "6762767783005334449", "6759047451575591974", "50184842142785136", "50182010571046642", "6762182567872149",
                "50186473685732844", "6762013632998678", "6761819583310177503", "6761720966286172947", "502050334654832333",
                "6759849925059529", "67614825410760851", "6304669919342",
                "5018661317066221438", "5893884475450785",
                "6379468730192910", "6385095330064645", "6395194398975384", "6393064414378685",
                "6393357109042933", "6377955586230473", "6381609432014102", "6386249418974832", "6391319735830265",
                "6373023596990174", "6378292006203552", "6378545675911874", "6377725818738894", "6378363559006765",
                "6388746824182262", "6376586660305898", "6388635222681936", "6377654610486181", "6391381977329653",
                "6385768034580781", "6385563587397166", "6373679326906358", "6371690145870644", "6386252399092251",
                "6373258465366722", "6385310772305301", "6382288410905653", "6392264727925757", "6398028641426638",
                "6387823755303687", "6388413394681005", "6383727305632194", "6382320614857800", "6386157784067018",
                "6383452176472527", "6371091462930873", "6374516950481242", "6380612722736369", "6386744789949454",
                "6382766363481715", "6390817474822626", "6371156573146873", "6377878686750507", "6398250461927756",
                "6392179247024891", "6374985867362987", "6371607528311798", "6370409659507281", "6379097214289577",
                "6376906000197690");
    }

    @Test
    public void shouldReturnCardTypeMaestro() {

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(mCardNumber);

        // expect
        // then
        assertThat(cardProvider).overridingErrorMessage(
                "Expected: " + CardIssuer.MAESTRO + " but was: " + cardProvider + " for number: " + mCardNumber).isEqualTo(CardIssuer.MAESTRO);
    }
}
