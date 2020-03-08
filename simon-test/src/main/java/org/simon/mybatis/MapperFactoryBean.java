package org.simon.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-17 23:56
 */
public class MapperFactoryBean implements FactoryBean, InvocationHandler {

	private Class clazz;
	public MapperFactoryBean(Class clazz){
		this.clazz = clazz;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Sql sql = method.getAnnotation(Sql.class);
		if(sql != null){
			System.out.println("=========proxy:sql:"+sql.value()+", args:"+ Arrays.toString(args));
		}
		return null;
	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, this);
	}

	@Override
	public Class<?> getObjectType() {
		return clazz;
	}
}
