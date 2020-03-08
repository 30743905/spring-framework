package org.simon.ioc.instantiationAwareBeanPostProcessor;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-04 下午 23:31
 * @Description:TODO
 */
@PropertySource("classpath:system.properties")
@Component
public class SystemConfig {
}
