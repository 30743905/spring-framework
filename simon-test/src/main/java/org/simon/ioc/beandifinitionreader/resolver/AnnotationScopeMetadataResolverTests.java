package org.simon.ioc.beandifinitionreader.resolver;

import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;


/**
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-15 0:16
 * @Description:TODO
 */
public class AnnotationScopeMetadataResolverTests {

	private AnnotationScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();


	@Test
	public void resolveScopeMetadataShouldNotApplyScopedProxyModeToSingleton() {
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(AnnotatedWithSingletonScope.class);
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals(BeanDefinition.SCOPE_SINGLETON, scopeMetadata.getScopeName());
		assertEquals(ScopedProxyMode.NO, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void resolveScopeMetadataShouldApplyScopedProxyModeToPrototype() {
		this.scopeMetadataResolver = new AnnotationScopeMetadataResolver(ScopedProxyMode.INTERFACES);
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(AnnotatedWithPrototypeScope.class);
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals(BeanDefinition.SCOPE_PROTOTYPE, scopeMetadata.getScopeName());
		assertEquals(ScopedProxyMode.INTERFACES, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void resolveScopeMetadataShouldReadScopedProxyModeFromAnnotation() {
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(AnnotatedWithScopedProxy.class);
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals("request", scopeMetadata.getScopeName());
		assertEquals(TARGET_CLASS, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void customRequestScope() {
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(AnnotatedWithCustomRequestScope.class);
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals("request", scopeMetadata.getScopeName());
		assertEquals(ScopedProxyMode.NO, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void customRequestScopeViaAsm() throws IOException {
		MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();
		MetadataReader reader = readerFactory.getMetadataReader(AnnotatedWithCustomRequestScope.class.getName());
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(reader.getAnnotationMetadata());
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals("request", scopeMetadata.getScopeName());
		assertEquals(ScopedProxyMode.NO, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void customRequestScopeWithAttribute() {
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(
				AnnotatedWithCustomRequestScopeWithAttributeOverride.class);
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals("request", scopeMetadata.getScopeName());
		assertEquals(TARGET_CLASS, scopeMetadata.getScopedProxyMode());
	}

	@Test
	public void customRequestScopeWithAttributeViaAsm() throws IOException {
		MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();
		MetadataReader reader = readerFactory.getMetadataReader(AnnotatedWithCustomRequestScopeWithAttributeOverride.class.getName());
		AnnotatedBeanDefinition bd = new AnnotatedGenericBeanDefinition(reader.getAnnotationMetadata());
		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(bd);
		assertNotNull("resolveScopeMetadata(..) must *never* return null.", scopeMetadata);
		assertEquals("request", scopeMetadata.getScopeName());
		assertEquals(TARGET_CLASS, scopeMetadata.getScopedProxyMode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void ctorWithNullScopedProxyMode() {
		new AnnotationScopeMetadataResolver(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setScopeAnnotationTypeWithNullType() {
		scopeMetadataResolver.setScopeAnnotationType(null);
	}


	@Retention(RetentionPolicy.RUNTIME)
	@Scope("request")
	@interface CustomRequestScope {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Scope("request")
	@interface CustomRequestScopeWithAttributeOverride {

		ScopedProxyMode proxyMode();
	}

	@Scope("singleton")
	private static class AnnotatedWithSingletonScope {
	}

	@Scope("prototype")
	private static class AnnotatedWithPrototypeScope {
	}

	@Scope(scopeName = "request", proxyMode = TARGET_CLASS)
	private static class AnnotatedWithScopedProxy {
	}

	@CustomRequestScope
	private static class AnnotatedWithCustomRequestScope {
	}

	@CustomRequestScopeWithAttributeOverride(proxyMode = TARGET_CLASS)
	private static class AnnotatedWithCustomRequestScopeWithAttributeOverride {
	}

}
