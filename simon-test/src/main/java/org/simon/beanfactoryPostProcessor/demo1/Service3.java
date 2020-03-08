package org.simon.beanfactoryPostProcessor.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-26 19:21 Description:TODO
 */
public class Service3 {
  private Logger logger = LoggerFactory.getLogger(Service3.class);

  @Autowired
  private Service2 service2;
  @Autowired
  private Service1 service1;

  public Service3(){
    try {
      logger.info("before construct service2, thread:[{}]", Thread.currentThread().getName());
      TimeUnit.SECONDS.sleep(1);
      logger.info("after construct service2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @PostConstruct
  private void init(){
    try {
      logger.info("before PostConstruct service2, thread:[{}]", Thread.currentThread().getName());
      TimeUnit.SECONDS.sleep(1);
      logger.info("after PostConstruct service2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

	public Service2 getService2() {
		return service2;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

	public Service1 getService1() {
		return service1;
	}

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}
}
