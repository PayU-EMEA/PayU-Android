package com.payu.android.front.sdk.payment_environment_resolver.classpath;

import android.content.pm.ApplicationInfo;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

import dalvik.system.DexFile;

import static com.google.common.collect.Sets.newHashSet;

public class ClassPathScanner {

    private String mPackagePrefix;
    private ApplicationInfo mApplicationInfo;

    public ClassPathScanner(@NonNull String packagePrefix, @NonNull ApplicationInfo applicationInfo) {
        mPackagePrefix = packagePrefix;
        mApplicationInfo = applicationInfo;
    }

    public Set<Class<?>> getClassesWithinPackage() {
        DexFile dexFile = null;
        try {
            Set<Class<?>> classes = newHashSet();
            dexFile = new DexFile(mApplicationInfo.sourceDir);
            Enumeration<String> classpathEntries = dexFile.entries();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            while (classpathEntries.hasMoreElements()) {
                String classEntry = classpathEntries.nextElement();

                if (isWithinEnvironmentsPackage(classEntry)) {
                    classes.add(classLoader.loadClass(classEntry));
                }
            }
            return classes;
        } catch (IOException e) {
            return Collections.emptySet();
        } catch (ClassNotFoundException e) {
            return Collections.emptySet();
        } catch (NullPointerException e) {
            // XXX Due to failing robolectric
            return Collections.emptySet();
        } finally {
            try {
                dexFile.close();
            } catch (IOException e) {
                //ignore
            }
        }

    }

    private boolean isWithinEnvironmentsPackage(String classEntry) {
        return classEntry.toLowerCase().startsWith(mPackagePrefix);
    }
}
