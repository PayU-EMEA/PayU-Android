package com.payu.android.front.sdk.payment_library_core_android.util;

import android.content.res.Resources;

import com.payu.android.front.sdk.payment_library_core_android.configuration.resource.StringResourceIdProvider;

import org.mockito.Mockito;

public class StringResourceMockConfigurator {

    private final Resources resourcesMock;
    private final StringResourceIdProvider idProviderMock;

    public StringResourceMockConfigurator(Resources resourcesMock, StringResourceIdProvider idProviderMock) {
        this.resourcesMock = resourcesMock;
        this.idProviderMock = idProviderMock;
    }

    public void resetMock() {
        Mockito.reset(idProviderMock);
        Mockito.reset(resourcesMock);
    }

    public void whenStringForKeyIsRequestedReturn(String resourceKey, String returnedString) {
        int hashForKey = getHashForKey(resourceKey);
        Mockito.when(idProviderMock.getStringIdByName(resourceKey)).thenReturn(hashForKey);
        Mockito.when(resourcesMock.getString(hashForKey)).thenReturn(returnedString);
    }

    public void whenKeyRequestedThrowNotFound(String resourceKey) {
        int hashForKey = getHashForKey(resourceKey);
        Mockito.when(idProviderMock.getStringIdByName(resourceKey)).thenReturn(hashForKey);
        Mockito.when(resourcesMock.getString(hashForKey)).thenThrow(new Resources.NotFoundException());
    }

    public void whenBoolForKeyIsRequestedReturn(String resourceKey, boolean value) {
        int hashForKey = getHashForKey(resourceKey);
        Mockito.when(idProviderMock.getBooleanIdByName(resourceKey)).thenReturn(hashForKey);
        Mockito.when(resourcesMock.getBoolean(hashForKey)).thenReturn(value);

    }

    public void whenKeyRequestedThenReturnTrue(String resourceKey) {
        whenBoolForKeyIsRequestedReturn(resourceKey, true);
    }
    public void whenKeyRequestedThenReturnFalse(String resourceKey) {
        whenBoolForKeyIsRequestedReturn(resourceKey, false);
    }

    private int getHashForKey(String resourceKey) {
        return resourceKey.hashCode();
    }
}