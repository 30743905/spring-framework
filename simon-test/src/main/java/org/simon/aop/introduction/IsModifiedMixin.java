package org.simon.aop.introduction;

import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInvocation;
import org.simon.aop.proxyfactory.TestInteceptor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-17 下午 14:58
 * @Description:TODO
 */
public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified, TestInterface {
	private boolean isModified = false;

	private Map<Method, Method> methodCache = new HashMap<>();

	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("*****>"+mi.getMethod().getName());
		if(!isModified){
			if(mi.getMethod().getName().startsWith("set") && (mi.getArguments().length == 1)){
				Method getter = getGetter(mi.getMethod());
				if(getter != null){
					Object newValue = mi.getArguments()[0];
					Object oldValue = getter.invoke(mi.getThis(), null);
					if(newValue == null && oldValue == null){
						isModified = false;
					}else if(newValue == null && oldValue != null){
						isModified = true;
					}else if(newValue != null && oldValue == null){
						isModified = true;
					}else{
						isModified = !newValue.equals(oldValue);
					}
				}
			}
		}
		return super.invoke(mi);
	}

	private Method getGetter(Method setter){
		Method getter = methodCache.get(setter);
		if(getter != null){
			return getter;
		}
		String getterName = setter.getName().replaceFirst("set", "get");
		try {
			getter = setter.getDeclaringClass().getMethod(getterName, null);
			synchronized (methodCache){
				methodCache.put(setter, getter);
			}
			return getter;
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	@Override
	public String test(String str) {
		return "test->"+str;
	}
}
