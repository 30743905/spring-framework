package org.simon.beanfactoryPostProcessor.demo1;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
//import org.simon.customer.Service5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * 你搞忘写注释了
 *
 * @author 张张/36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-17 10:55
 */
public class TestMain {

	private static Logger log = LoggerFactory.getLogger(TestMain.class);

	@Test
  public void test1(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
    context.register(BeanConfig.class);

    /*ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
    scanner.scan("org.simon.customer");*/

    ConfigurableEnvironment env = context.getEnvironment();
    System.out.println();


    context.refresh();




    log.info("=====service1:{}", System.identityHashCode(context.getBean(Service1.class)));
    log.info("=====service2:{}", System.identityHashCode(context.getBean(Service2.class)));

    log.info("=========service1.service2:{}", System.identityHashCode(context.getBean(Service1.class).getService2()));
    log.info("=========service1.service4:{}", System.identityHashCode(context.getBean(Service1.class).getService4()));

    //log.info("=========registerService3.service1:{}", System.identityHashCode(context.getBean("registerService3", Service3.class).getService1()));
    //log.info("=========registerService3.service2:{}", System.identityHashCode(context.getBean("registerService3", Service3.class).getService2()));

    log.info("=====service5:{}", System.identityHashCode(context.getBean(Service5.class)));
    //log.info("=====service5.service2:{}", System.identityHashCode(context.getBean(Service5.class).getService2()));
    //log.info("=====service5.service4:{}", System.identityHashCode(context.getBean(Service5.class).getService4()));

    log.info("=====nService6:{}", context.containsBean("nService6"));
  }

	@Test
	public void test2(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Service2.class);

		context.refresh();

		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
