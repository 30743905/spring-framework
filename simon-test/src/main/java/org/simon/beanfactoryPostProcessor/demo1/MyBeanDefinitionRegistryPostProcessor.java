package org.simon.beanfactoryPostProcessor.demo1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 你搞忘写注释了
 *
 * @author 张张/36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-17 10:48
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	private Logger log = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);

  /**
   * BeanDefinition创建完成，但是还不能创建Bean
   * @param registry
   * @throws BeansException
   */
  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    log.info("===============postProcessBeanDefinitionRegistry");
	  /**
	   * 获取已注册到IoC中的BeanDefinition信息
	   */
    Arrays.stream(registry.getBeanDefinitionNames()).forEach(x -> {
      log.info("beanName:{}", x);
    });

	  /**
	   * 向IoC中手动注册BeanDefinition
	   */
	  //方式一
    /*GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClass(Service3.class);*/

    //方式二
    /*AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
        .genericBeanDefinition(Service3.class).getBeanDefinition();*/

    //方式三
	  /**
	   * addPropertyValue()初始化常规变量
	   * addPropertyReference():初始化IoC引用给变量，其实等价于：
	   * 		beanDefinition.getPropertyValues().add(name, new RuntimeBeanReference(beanName));
	   */
    /*AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
        .genericBeanDefinition(Service3.class)
        .addPropertyReference("service2", "service2")
        .getBeanDefinition();*/

    AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(Service3.class);
    AnnotationConfigUtils.processCommonDefinitionAnnotations(beanDefinition);
    BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, "registerService3");
    BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);

    //将手动创建的BeanDefinition注册到IoC中
    registry.registerBeanDefinition("registerService3", beanDefinition);

    /**
     * 手动创建BeanDefinition肯定效率比较低，不太灵活，可以通过Scanner扫描指定路径下指定注解的类，注册到IOC中
     */
    /**
     * useDefaultFilters:是否使用默认的Filter，使用默认的Filter意味着会扫描那些拥有@Component、@Service、@Repository、@Controller注解的类
     */
/*    boolean useDefaultFilters = true;
    ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, useDefaultFilters);
    scanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent.class));//@MyComponent注解的类符合规则
    scanner.scan("org.simon.customer");*/  //scan(basePackage):开始扫描指定包路径

    register(registry);

  }

  private void register(BeanDefinitionRegistry registry){
    BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();


    boolean useDefaultFilters = true;
    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(useDefaultFilters);

    /**
     * considerMetaAnnotations=true:如果目标类上没有指定的注解，但是目标类上的某个注解上加上了指定的注解则该类也将匹配
     * considerInterfaces=true:则目标类实现的接口上拥有指定的注解时也将匹配
     */
    scanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent.class, true, false));
    Set<BeanDefinition> beanDefinitions = scanner
        .findCandidateComponents("org.simon.customer");
    for (BeanDefinition beanDefinition : beanDefinitions) {
      System.out.println(beanDefinition.getBeanClassName());
      String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
      registry.registerBeanDefinition(beanName, beanDefinition);
    }


  }

  /**
   * 可创建Bean
   * @param beanFactory
   * @throws BeansException
   */
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    log.info("===============postProcessBeanFactory");

	  AbstractBeanDefinition abstractBeanDefinition =
			  (AbstractBeanDefinition) beanFactory.getBeanDefinition("service5");
	  MutablePropertyValues pv =  abstractBeanDefinition.getPropertyValues();
	  pv.addPropertyValue("name", "Desc is changed from bean factory post processor");
	  abstractBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);




    for(String beanName:beanFactory.getBeanDefinitionNames()){
      log.info("beanName:{}, bean:{}", beanName/*, beanFactory.getBean(beanName)*/);//注意：这里不能getBean获取实例，不然@Autowired注解不生效
    }
/*    Service3 registerService3 = (Service3) beanFactory.getBean("registerService3");
    log.info("", registerService3);
    log.info("*****************service2:{}", registerService3.getService2());
    log.info("******************service1:{}", registerService3.getService1());*/
  }
}
