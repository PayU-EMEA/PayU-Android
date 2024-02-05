package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.encoder;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.PostDataEncoder;

import java.util.Map;

public class UrlEscaperPostDataEncoder implements PostDataEncoder {
    @Override
    public byte[] encode(final Map<String, String> parameters) {

        final String joinedParams = Joiner.on("&")
                .withKeyValueSeparator("=")
                .join(Iterables.transform(parameters.entrySet(), new UrlFormParamEscapingFunction()));
        return joinedParams.getBytes();
    }

    private static class UrlFormParamEscapingFunction implements Function<Map.Entry<String, String>, Map.Entry<String, String>> {
        @Override
        public Map.Entry<String, String> apply(final Map.Entry<String, String> input) {
            return new UrlFormParamEscapedMapEntry(input);
        }

        private static class UrlFormParamEscapedMapEntry implements Map.Entry<String, String> {
            private final String key;
            private final String value;

            public UrlFormParamEscapedMapEntry(Map.Entry<String, String> input) {
                Escaper escaper = UrlEscapers.urlFormParameterEscaper();
                key = escaper.escape(input.getKey());
                value = escaper.escape(input.getValue());
            }

            @Override
            public String getKey() {
                return key;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public String setValue(String object) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
