package com.payu.android.front.sdk.payment_library_core_android.permissions;

import java.util.List;

/**
 * Callbacks used for receive information about granted or denied Android permissions which was requested.
 */
public interface PermissionCallback {
    /**
     * Method called when all requested permission was granted.
     */
    void onGranted();

    /**
     * Method called when at least one permission was denied.
     * @param deniedPermissions list of denied permissions.
     */
    void onDenied(List<String> deniedPermissions);
}
