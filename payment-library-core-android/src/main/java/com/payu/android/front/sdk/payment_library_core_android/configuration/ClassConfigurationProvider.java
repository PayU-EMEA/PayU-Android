package com.payu.android.front.sdk.payment_library_core_android.configuration;

import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;

public class ClassConfigurationProvider {
    protected ConfigurationDataProvider configurationDataProvider;

    protected ClassConfigurationProvider(
            ConfigurationDataProvider configurationDataProvider) {
        this.configurationDataProvider = configurationDataProvider;
    }

    protected Object createObjectFromClass(String className) {
        return createObjectFromClass(className, null);
    }

    protected Object createObjectFromClass(String className, @Nullable Application application) {
        try {
            if (application != null) {
                return Class.forName(className)
                            .getDeclaredConstructor(Context.class)
                            .newInstance(application.getApplicationContext());

            } else {
                return Class.forName(className).newInstance();
            }
        } catch (InstantiationException e) {
            throw new ClassConfigurationException(String.format(
                    "Provided class %s cannot be instantiated. Make sure that the class you provided is proper java class in separate file. ",
                    className));
        } catch (IllegalAccessException e) {
            throw new ClassConfigurationException(
                    String.format("We cannot access constructor for the provided class: %s. Make sure that the class has public constructor.",
                            className));
        } catch (ClassNotFoundException e) {
            throw new ClassConfigurationException(
                    String.format("Provided class %s cannot be found. Make sure that class has been provided with proper package name.", className));
        } catch (NoSuchMethodException e) {
            throw new ClassConfigurationException(
                    String.format("Provided class %s cannot be instantiated. Make sure it has constructor matching super class.", className));
        } catch (InvocationTargetException e) {
            throw new ClassConfigurationException(String.format("Cannot instantiate %s", className), e);
        }
    }
}
