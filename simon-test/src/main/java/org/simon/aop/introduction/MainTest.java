package org.simon.aop.introduction;

import org.junit.Test;
import org.simon.aop.proxyfactory.ProxyFactoryTest;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Arrays;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-17 下午 22:41
 * @Description:TODO
 */
public class MainTest {

	@Test
	public void test1(){
		Contact target = new Contact();
		target.setName("John Mayer");

		IntroductionAdvisor advisor = new IsModifiedAdvisor();

		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		pf.setOptimize(true);

		Contact proxy = (Contact) pf.getProxy();
		IsModified proxyInterface = (IsModified) proxy;

		System.out.println("proxy type:"+proxy.getClass().getName());
		Arrays.stream(proxy.getClass().getInterfaces()).forEach(x -> System.out.println("interface:"+x.getName()));
		System.out.println(proxy.getClass().getSuperclass());

		System.out.println("Is Contact:"+(proxy instanceof Contact));
		System.out.println("Is IsModified:"+(proxy instanceof IsModified));

		System.out.println("isModified:"+proxyInterface.isModified());
		proxy.setName("John Mayer");
		System.out.println("isModified:"+proxyInterface.isModified());
		proxy.setName("Eric Clapton");
		System.out.println("isModified:"+proxyInterface.isModified());

		System.out.println(((TestInterface)proxy).test("zhangsan"));
	}
}
