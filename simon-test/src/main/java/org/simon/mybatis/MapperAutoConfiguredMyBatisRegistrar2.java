package org.simon.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-17 23:55
 */
public class MapperAutoConfiguredMyBatisRegistrar2
		implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware
{

	private ResourceLoader resourceLoader;

	private BeanFactory beanFactory;

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs =
				AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));

		String[] basePackages = annoAttrs.getStringArray("basePackages");

		/*ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Mapper.class));
		int count = scanner.scan(basePackages);
		System.out.println("scan finish, count:"+count);*/



		//ClassPathScanningCandidateComponentProvider只能扫描类，不能扫描接口
/*		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(Mapper.class));
		Set<BeanDefinition> bdSet = new HashSet<>();
		Arrays.stream(basePackages).forEach(x -> {
			bdSet.addAll(provider.findCandidateComponents(x));
		});
		System.out.println("count:"+bdSet.size());
		bdSet.forEach(definition -> {
			ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition)definition;

			Mapper mapper = scannedGenericBeanDefinition.getBeanClass().getAnnotation(Mapper.class);
			String value = mapper.value();

			Class<?> interfClazz = scannedGenericBeanDefinition.getBeanClass();
			scannedGenericBeanDefinition.setBeanClass(MapperFactoryBean.class);
			scannedGenericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(interfClazz.getName());
			registry.registerBeanDefinition(value, scannedGenericBeanDefinition);

		});*/


	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}