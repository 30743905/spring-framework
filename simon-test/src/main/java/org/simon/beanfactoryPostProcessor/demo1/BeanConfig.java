package org.simon.beanfactoryPostProcessor.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 你搞忘写注释了
 *
 * @author 张张/36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-16 14:21
 */
@Configuration
@ComponentScan("org.simon.beanfactoryPostProcessor")
public class BeanConfig {

  @Bean
  public Service2 service2(){
    return new Service2();
  }

}
