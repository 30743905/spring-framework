package org.simon.ioc.beandefinition;

import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionValueResolver;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-10 下午 16:35
 * @Description:TODO
 */
public class MainTest {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		GenericBeanDefinition item = new GenericBeanDefinition();
		//通过Class初始化一个BeanDefinition
		item.setBeanClass(Service.class);
		//设置属性值
		item.getPropertyValues().add("name","test");
		//注册到BeanFactory中
		context.registerBeanDefinition("item",item);

		//刷新IoC容器，
		//注意：如果使用public AnnotationConfigApplicationContext(String... basePackages)这个构造器，则默认已经调用了refresh方法
		context.refresh();

		Service bean= (Service) context.getBean("item");
		System.out.println(JSON.toJSONString(bean, true));
	}

	@Test
	public void test2(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		//注册一个父BeanDefinition
		RootBeanDefinition item=new RootBeanDefinition();
		item.setBeanClass(Service.class);
		item.getPropertyValues().add("name","test");
		context.registerBeanDefinition("item",item);

		//注册一个子BeanDefinition
		ChildBeanDefinition item2=new ChildBeanDefinition("item");
		item2.setBeanClass(SubService.class);
		//该子BeanDefinition默认会继承父BeanDefinition的设置信息，所以，name属性会被设置为test
		item2.getPropertyValues().add("text","text");
		context.registerBeanDefinition("item2",item2);

		context.refresh();
		Service bean= (Service) context.getBean("item");
		System.out.println(JSON.toJSONString(bean, true));

		SubService subService = (SubService) context.getBean("item2");
		System.out.println(JSON.toJSONString(subService, true));
	}

	@Test
	public void test3(){
		GenericApplicationContext ctx = new GenericApplicationContext();

		//注册Service2
		BeanDefinitionBuilder service2Builder = BeanDefinitionBuilder
				.rootBeanDefinition(Service2.class)
				//设置属性
				.addPropertyValue("name", "service2-Joe");
		ctx.registerBeanDefinition("bean-service2", service2Builder.getBeanDefinition());

		//注册Service
		BeanDefinitionBuilder serviceBuilder = BeanDefinitionBuilder.rootBeanDefinition(Service.class)
				//设置属性注入依赖
				.addPropertyReference("service2", "bean-service2");
		ctx.registerBeanDefinition("bean-service", serviceBuilder.getBeanDefinition());

		//刷新容器
		ctx.refresh();

		System.out.println(ctx.getBean("bean-service2"));
		System.out.println(ctx.getBean("bean-service"));
		System.out.println(((Service)ctx.getBean("bean-service")).getService2());
	}

	@Test
	public void test4() {
		GenericApplicationContext ctx = new GenericApplicationContext();

		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(Service.class);
		bdb.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bdb.addPropertyValue("name", "zhangsan");
		bdb.applyCustomizers(new BeanDefinitionCustomizer()
		{
			@Override
			public void customize(BeanDefinition bd) {
				bdb.addPropertyValue("name", "lisi");
			}
		});
		ctx.registerBeanDefinition("bean-service", bdb.getBeanDefinition());

		ctx.refresh();
		System.out.println(((Service)ctx.getBean("bean-service")).getName());
	}

	@Test
	public void test5(){
		GenericApplicationContext ctx = new GenericApplicationContext();

		//注册Service2
		BeanDefinitionBuilder service2Builder = BeanDefinitionBuilder
				.rootBeanDefinition(Service2.class)
				//设置属性
				.addPropertyValue("name", "service2-Joe");
		ctx.registerBeanDefinition("bean-service2", service2Builder.getBeanDefinition());

		//注册Service
		BeanDefinitionBuilder serviceBuilder = BeanDefinitionBuilder.rootBeanDefinition(Service.class)
				//设置属性注入依赖
				.addPropertyValue("name", "service-zhangsan")
				.addPropertyReference("service2", "bean-service2");

		AbstractBeanDefinition serviceBeanDefinition = serviceBuilder.getBeanDefinition();

		TypeConverter typeConverter = ctx.getBeanFactory().getTypeConverter();
		/**
		 * 默认BeanDefinitionValueResolver是非public类，这里是没法直接new构建的，我使用的源码环境修改为public所以可以
		 */
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(
				ctx.getDefaultListableBeanFactory(), "service2", serviceBeanDefinition, typeConverter);

		//获取BeanDefinition的PropertyValues
		MutablePropertyValues pvs = serviceBeanDefinition.getPropertyValues();
		pvs.stream().forEach(pv -> {
			System.out.println("开始解析PropertyValue, name:"+pv.getName()+", value:"+pv.getValue()+", value类型:"+pv.getValue().getClass());
			Object ret = valueResolver.resolveValueIfNecessary(pv, pv.getValue());
			System.out.println("解析完成PropertyValue, value:"+pv.getValue()+",解析返回:"+ret+",返回类型:"+ret.getClass()+",返回值hash:"+System.identityHashCode(ret));
		});
	}
}
