package com.payu.android.front.sdk.payment_library_core_android.permissions;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@TargetApi(Build.VERSION_CODES.M)
public class PermissionCheckActivity extends AppCompatActivity {

    public static final String EXTRA_PERMISSION_LIST = "payu_permission_list";
    private static final int PERMISSION_REQUEST_CODE = 666;

    private static PermissionCallback permissionCallback;

    private List<String> deniedPermissions;

    public static void setPermissionCallback(PermissionCallback permissionCallback) {
        PermissionCheckActivity.permissionCallback = permissionCallback;
    }

    public static void start(@NonNull Context context, @Nullable ArrayList<String> permissionListToCheck) {
        start(context, permissionListToCheck, null);
    }

    public static void start(@NonNull Context context, @Nullable ArrayList<String> permissionListToCheck, @Nullable PermissionCallback permissionCallback) {
        if (permissionCallback != null) {
            setPermissionCallback(permissionCallback);
        }

        Intent permissionCheckIntent = new Intent(checkNotNull(context), PermissionCheckActivity.class)
                .putStringArrayListExtra(EXTRA_PERMISSION_LIST, permissionListToCheck);
        context.startActivity(permissionCheckIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        List<String> permissionsToRequest = new ArrayList<>();
        if (intent != null && intent.hasExtra(EXTRA_PERMISSION_LIST)) {
            permissionsToRequest = intent.getStringArrayListExtra(EXTRA_PERMISSION_LIST);
        } else {
            finish();
        }

        getWindow().setStatusBarColor(0);

        deniedPermissions = new ArrayList<>();
        for (String permission : permissionsToRequest) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }

        if (deniedPermissions.isEmpty()) {
            permissionGranted();
            return;
        }

        requestPermissions(Iterables.toArray(deniedPermissions, String.class), PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }

        if (grantResults.length == 0) {
            permissionDenied();
            return;
        }

        deniedPermissions.clear();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (deniedPermissions.isEmpty()) {
            permissionGranted();
        } else {
            permissionDenied();
        }
    }

    @Override
    public void finish() {
        permissionCallback = null;
        super.finish();
    }

    private void permissionGranted() {
        PermissionCallback permissionCallback = PermissionCheckActivity.permissionCallback;
        finish();
        if (permissionCallback != null) {
            permissionCallback.onGranted();
        }
    }

    private void permissionDenied() {
        PermissionCallback permissionCallback = PermissionCheckActivity.permissionCallback;
        finish();
        if (permissionCallback != null) {
            permissionCallback.onDenied(deniedPermissions);
        }
    }

}
