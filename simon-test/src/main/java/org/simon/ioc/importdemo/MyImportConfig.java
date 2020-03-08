package org.simon.ioc.importdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 11:50
 */
//@Configuration
public class MyImportConfig {
	@Bean
	public Service2 service2(){
		return new Service2();
	}
}
