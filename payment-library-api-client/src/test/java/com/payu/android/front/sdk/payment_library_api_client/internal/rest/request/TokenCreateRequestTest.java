package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenCreateRequestTest {
    private final Gson gson = new Gson();

    @Test
    public void shouldSerializeAddCardRequestAsExpectedJson() {
        TokenCreateRequest request = new TokenCreateRequest(
                "200003",
                TokenType.SINGLE,
                new Card("4444333322221111", "124", "02", "2029")
        );

        JsonObject jsonObject = JsonParser.parseString(gson.toJson(request)).getAsJsonObject();
        JsonObject cardObject = jsonObject.getAsJsonObject("card");

        assertThat(jsonObject.get("posId").getAsString()).isEqualTo("200003");
        assertThat(jsonObject.get("type").getAsString()).isEqualTo("SINGLE");
        assertThat(cardObject.get("number").getAsString()).isEqualTo("4444333322221111");
        assertThat(cardObject.get("expirationMonth").getAsString()).isEqualTo("02");
        assertThat(cardObject.get("expirationYear").getAsString()).isEqualTo("2029");
        assertThat(cardObject.get("cvv").getAsString()).isEqualTo("124");
    }

    @Test
    public void shouldDeserializeAddCardResponseAsExpected() {
        TokenCreateResponse response = gson.fromJson(
                "{\"value\":\"TOKD_1LPLPW0MMKWZ9F4GK7Fq65H2aT0\",\"maskedCard\":\"444433******1111\"}",
                TokenCreateResponse.class
        );

        assertThat(response.getValue()).isEqualTo("TOKD_1LPLPW0MMKWZ9F4GK7Fq65H2aT0");
        assertThat(response.getMaskedCard()).isEqualTo("444433******1111");
    }
}
