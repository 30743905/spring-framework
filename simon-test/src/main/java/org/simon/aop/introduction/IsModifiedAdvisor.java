package org.simon.aop.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-17 下午 15:09
 * @Description:将advice封装成一个IntroductionAdvisor
 */
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
	public IsModifiedAdvisor(){
		//传入一个IsModifiedMixin类型的Advice实例，该Advice实现的接口在代理对象上都会实现
		super(new IsModifiedMixin());
	}
}
