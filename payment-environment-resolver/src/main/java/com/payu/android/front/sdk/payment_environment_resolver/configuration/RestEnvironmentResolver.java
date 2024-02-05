package com.payu.android.front.sdk.payment_environment_resolver.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.payu.android.front.sdk.payment_environment_resolver.classpath.ClassPathScanner;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.ProductionRestEnvironment;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.SandboxPartnersRestEnvironment;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.SandboxRestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Sets.newHashSet;

public class RestEnvironmentResolver {

    private static Set<Class<? extends RestEnvironment>> ENVIRONMENTS_ON_CLASSPATH = newHashSet();
    private ClassPathScanner mScanner;

    public RestEnvironmentResolver(EnvironmentClassPathScanner classPathScanner) {
        mScanner = classPathScanner;
    }

    @NonNull
    @Deprecated
    public RestEnvironment get(@NonNull Optional<String> environment) {
        return get(environment.orNull());
    }

    @NonNull
    public RestEnvironment get(@Nullable String environment) {
        return environment == null ? new SandboxRestEnvironment() : findMatchingEnvironment(environment);
    }

    @VisibleForTesting
    void invalidateCache() {
        ENVIRONMENTS_ON_CLASSPATH.clear();
    }

    private Optional<RestEnvironment> createEnvironmentInstance(Class<? extends RestEnvironment> environmentClass) {

        try {
            return fromNullable(environmentClass.newInstance());
        } catch (InstantiationException e) {
            return absent();
        } catch (IllegalAccessException e) {
            return absent();
        }
    }

    private void fillCache(Set<Class<? extends RestEnvironment>> environmentsOnClasspath) {
        ENVIRONMENTS_ON_CLASSPATH.addAll(environmentsOnClasspath);
    }

    private Set<Class<? extends RestEnvironment>> filterRestEnvironments(Set<Class<?>> classesWithinPayUSdk) {
        return new HashSet<Class<? extends RestEnvironment>>(FluentIterable.from(classesWithinPayUSdk)
                .filter(new Predicate<Class<?>>() {
                    public boolean apply(Class<?> input) {
                        return RestEnvironment.class.isAssignableFrom(input) && !Modifier.isAbstract(input.getModifiers());
                    }
                }).transform(new Function<Class<?>, Class<? extends RestEnvironment>>() {

                    @Override
                    public Class<? extends RestEnvironment> apply(Class<?> input) {
                        return (Class<? extends RestEnvironment>) input;
                    }
                }).toSet());
    }

    private RestEnvironment findMatchingEnvironment(final String environmentStringKey) {
        Optional<RestEnvironment> environmentInstanceFromCache = getMatchingEnvironment(environmentStringKey,
                ENVIRONMENTS_ON_CLASSPATH);

        if (environmentInstanceFromCache.isPresent()) {
            return environmentInstanceFromCache.get();
        }

        return getMatchingEnvironmentFromClasspath(environmentStringKey);
    }

    private Optional<RestEnvironment> getMatchingEnvironment(String environmentStringKey,
                                                             Set<Class<? extends RestEnvironment>> environmentsOnClasspath) {

        for (Class<? extends RestEnvironment> environmentClass : environmentsOnClasspath) {
            Optional<RestEnvironment> environmentInstance = createEnvironmentInstance(environmentClass);

            if (environmentInstance.isPresent() && isConfigurationKeyMatching(environmentInstance.get(), environmentStringKey)) {
                return of(environmentInstance.get());
            }
        }

        return absent();
    }

    private RestEnvironment getMatchingEnvironmentFromClasspath(String environmentStringKey) {
        Set<Class<? extends RestEnvironment>> environmentsOnClasspath = filterRestEnvironments(
                mScanner.getClassesWithinPackage());
        environmentsOnClasspath.add(ProductionRestEnvironment.class);
        environmentsOnClasspath.add(SandboxRestEnvironment.class);
        environmentsOnClasspath.add(SandboxPartnersRestEnvironment.class);

        fillCache(environmentsOnClasspath);
        Optional<RestEnvironment> environmentInstance = getMatchingEnvironment(environmentStringKey, environmentsOnClasspath);
        return environmentInstance.isPresent() ? environmentInstance.get() : new SandboxRestEnvironment();
    }

    private boolean isConfigurationKeyMatching(RestEnvironment restEnvironment, String environmentStringKey) {
        return restEnvironment.getStringRepresentation().equalsIgnoreCase(environmentStringKey);
    }
}
