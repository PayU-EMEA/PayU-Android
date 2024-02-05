package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import static com.google.common.collect.Lists.newArrayList;

import android.net.Uri;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;

import java.util.List;

public class UriPathMatcher implements UriMatcher {

    private static final char SPLIT_PARAMETERS_ON = ';';
    private final String mPath;

    public UriPathMatcher(String path) {
        mPath = path;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UriPathMatcher that = (UriPathMatcher) o;
        return Objects.equal(this.mPath, that.mPath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mPath);
    }

    @Override
    public boolean matches(Uri uri) {
        List<String> pathList = splitPathAndParameters(uri);

        if (pathList.isEmpty()) {
            return false;
        }

        return pathList.get(0).equals(mPath);
    }

    private List<String> splitPathAndParameters(Uri uri) {
        return newArrayList(Splitter.on(SPLIT_PARAMETERS_ON).split(uri.getPath()).iterator());
    }
}
