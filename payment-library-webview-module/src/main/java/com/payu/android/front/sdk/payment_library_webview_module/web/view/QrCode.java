package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.content.ContentValues;
import android.webkit.WebView;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;

import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class QrCode {
    private static final String TAG = QrCode.class.getName();
    private final Context context;
    private final WebView webView;
    private final RestEnvironment environment;

    public QrCode(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
        this.environment = ConfigurationEnvironmentProvider.provideEnvironment(context);
    }

    @JavascriptInterface
    public void share(String imageData, String fileName) {
        getCurrentUrlOrigin(urlOrigin -> {
            if (urlOrigin != null && urlOrigin.equals(environment.getOrigin())) {
                shareQrCode(imageData, fileName);
            }

        });
    }

    private void shareQrCode(String imageData, String fileName) {
        try {
            byte[] decodedBytes = Base64.decode(imageData, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");

            Uri imageUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (imageUri != null) {
                OutputStream outputStream = context.getContentResolver().openOutputStream(imageUri);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/png");
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(shareIntent, "QR"));
            }
        } catch (Exception e) {
            Log.w(TAG, "Share error: " + e.getMessage());
        }
    }

    private void getCurrentUrlOrigin(UrlOriginCallback callback) {
        if (webView != null) {
            ((Activity) context).runOnUiThread(() -> {
                String url = webView.getUrl();

                try {
                    URI uri = new URI(url);
                    callback.onUrlReceived(uri.getHost());
                } catch (URISyntaxException e) {
                    callback.onUrlReceived(null);
                }
            });
        } else {
            callback.onUrlReceived(null);
        }
    }
}
