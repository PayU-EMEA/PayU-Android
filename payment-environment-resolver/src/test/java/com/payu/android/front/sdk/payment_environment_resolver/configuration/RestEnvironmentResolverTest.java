package com.payu.android.front.sdk.payment_environment_resolver.configuration;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.ProductionRestEnvironment;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.SandboxRestEnvironment;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.TestRestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.base.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class RestEnvironmentResolverTest {

    RestEnvironmentResolver objectUnderTest;
    @Mock
    EnvironmentClassPathScanner classPathScannerMock;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new RestEnvironmentResolver(classPathScannerMock);
    }

    @Test
    public void shouldFallbackToLocalEnvironmentIfNoMatchingFound() {
        // when
        RestEnvironment restEnvironment = objectUnderTest.get(of("non-existing-environment"));

        // then
        assertThat(restEnvironment).isInstanceOf(SandboxRestEnvironment.class);
    }

    @Test
    public void shouldFindMatchingEnvironment() {
        // given
        given(classPathScannerMock.getClassesWithinPackage()).willReturn(
                Sets.<Class<?>>newHashSet(TestRestEnvironment.class));

        // when
        RestEnvironment restEnvironment = objectUnderTest.get(of(new TestRestEnvironment().getStringRepresentation()));

        // then
        assertThat(restEnvironment).isInstanceOf(TestRestEnvironment.class);
    }

    @Test
    public void shouldFindMatchingEnvironmentInCache() {
        // given
        given(classPathScannerMock.getClassesWithinPackage()).willReturn(
                Sets.<Class<?>>newHashSet(TestRestEnvironment.class));
        objectUnderTest.get(of(new TestRestEnvironment().getStringRepresentation()));

        // when
        objectUnderTest.get(of(new TestRestEnvironment().getStringRepresentation()));

        // then
        verify(classPathScannerMock, times(1)).getClassesWithinPackage();
    }

    @Test
    public void shouldResolveDefaultEnvironmentForAbsent() {
        // when
        RestEnvironment restEnvironment = objectUnderTest.get(Optional.<String>absent());

        // then
        assertThat(restEnvironment).isInstanceOf(SandboxRestEnvironment.class);
    }

    @Test
    public void shouldResolveProductionRestEnvironmentEvenWhenItWasNotPresentInClassPath() {
        // given
        given(classPathScannerMock.getClassesWithinPackage()).willReturn(Sets.<Class<?>>newHashSet());

        // when
        RestEnvironment restEnvironment = objectUnderTest.get(new ProductionRestEnvironment().getStringRepresentation());

        // then
        assertThat(restEnvironment).isInstanceOf(ProductionRestEnvironment.class);
    }

    @Before
    public void tearDown() {
        objectUnderTest.invalidateCache();
    }
}