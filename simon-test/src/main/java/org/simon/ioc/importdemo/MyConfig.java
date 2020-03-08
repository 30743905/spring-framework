package org.simon.ioc.importdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 11:51
 */
@Import(MyImportConfig.class)
public class MyConfig {
	@Bean
	public Service1 service1(){
		return new Service1();
	}
}
