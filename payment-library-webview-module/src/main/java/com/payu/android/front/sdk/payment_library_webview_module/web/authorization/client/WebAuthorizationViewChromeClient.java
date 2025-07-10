package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.payu.android.front.sdk.payment_library_webview_module.web.view.WebPaymentActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebAuthorizationViewChromeClient extends WebChromeClient {

    private static final int MAX_PROGRESS = 100;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 503;

    private final WeakReference<ProgressBar> mProgressBar;

    private final WebPaymentActivity webPaymentActivity;

    private PermissionRequest pendingRequest;

    public WebAuthorizationViewChromeClient(ProgressBar progressBar, WebPaymentActivity webPaymentActivity) {
        mProgressBar = new WeakReference<>(progressBar);
        this.webPaymentActivity = webPaymentActivity;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        ProgressBar progressBar = mProgressBar.get();

        if (progressBar != null) {
            updateProgress(progressBar, newProgress);
        }
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        String[] requestedResources = request.getResources();
        List<String> requiredPermissions = new ArrayList<>();

        boolean cameraNeeded = Arrays.asList(requestedResources)
                .contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE);

        if (cameraNeeded) {
            requiredPermissions.add(Manifest.permission.CAMERA);
        }

        if (!requiredPermissions.isEmpty()) {
            pendingRequest = request;
            ActivityCompat.requestPermissions(
                    webPaymentActivity,
                    requiredPermissions.toArray(new String[0]),
                    CAMERA_PERMISSION_REQUEST_CODE
            );
        } else {
            request.grant(request.getResources());
        }
    }

    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && pendingRequest != null) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                pendingRequest.grant(pendingRequest.getResources());
            } else {
                pendingRequest.deny();
            }
            pendingRequest = null;
        }
    }


    private void updateProgress(ProgressBar progressBar, int newProgress) {
        progressBar.setProgress(newProgress);

        if (newProgress == MAX_PROGRESS) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
