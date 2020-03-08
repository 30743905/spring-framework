/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.util.Assert;

/**
 * Describes scope characteristics for a Spring-managed bean including the scope
 * name and the scoped-proxy behavior.
 *
 * <p>The default scope is "singleton", and the default is to <i>not</i> create
 * scoped-proxies.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @since 2.5
 * @see ScopeMetadataResolver
 * @see ScopedProxyMode
 */
//ScopeMetadata就是对Scope注解的描述转换,
public class ScopeMetadata {

	/**
	 * 作用域
	 * 1.单例（Singleton）：在整个应用中，只创建bean的一个实例。
	 * 2.原型（Prototype）：每次注入或者通过spring应用上下文获取的时候，都会创建一个新的bean实例。
	 * 3.会话（Session:WebApplicationContext.SCOPE_SESSION）：在web应用中，为每个会话创建一个bean实例。
	 * 4.请求（Request:WebApplicationContext.SCOPE_REQUEST）：在Web应用中，为每个请求创建一个bean实例。
	 */
	private String scopeName = BeanDefinition.SCOPE_SINGLETON;

	/**
	 * 代理模式
	 * DEFAULT一般和NO效果一致，不进行代理
	 * INTERFACES：使用JDK动态代理基于接口代理
	 * TARGET_CLASS：使用cglib基于类进行动态代理
	 *
	 */
	private ScopedProxyMode scopedProxyMode = ScopedProxyMode.NO;


	/**
	 * Set the name of the scope.
	 */
	public void setScopeName(String scopeName) {
		Assert.notNull(scopeName, "'scopeName' must not be null");
		this.scopeName = scopeName;
	}

	/**
	 * Get the name of the scope.
	 */
	public String getScopeName() {
		return this.scopeName;
	}

	/**
	 * Set the proxy-mode to be applied to the scoped instance.
	 */
	public void setScopedProxyMode(ScopedProxyMode scopedProxyMode) {
		Assert.notNull(scopedProxyMode, "'scopedProxyMode' must not be null");
		this.scopedProxyMode = scopedProxyMode;
	}

	/**
	 * Get the proxy-mode to be applied to the scoped instance.
	 */
	public ScopedProxyMode getScopedProxyMode() {
		return this.scopedProxyMode;
	}

}
