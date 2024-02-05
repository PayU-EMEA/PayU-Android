package com.payu.android.front.sdk.payment_library_core_android.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class Permissions {

    public static void check(@NonNull Context context, List<String> permissionsNeeded, @NonNull PermissionCallback permissionCallback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // no need to request permissions
            permissionCallback.onGranted();
        } else {
            Set<String> permissions = new LinkedHashSet<>(permissionsNeeded);
            if (isAllPermissionsGranted(context, permissions)) {
                permissionCallback.onGranted();
            } else {
                PermissionCheckActivity.setPermissionCallback(permissionCallback);
                PermissionCheckActivity.start(context, new ArrayList<>(permissions));
            }
        }
    }

    private static boolean isAllPermissionsGranted(@NonNull Context context, Set<String> permissions) {
        boolean allPermissionsGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }
        return allPermissionsGranted;
    }
}
