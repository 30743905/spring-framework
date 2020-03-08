package org.simon.beanfactoryPostProcessor.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.stereotype.Component;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-26 19:21 Description:TODO
 */
@Component
public class Service1 {
  private Logger logger = LoggerFactory.getLogger(Service1.class);
  private Map<String,String> map = new HashMap();

  @Autowired
  private EnvironmentAware environmentAware;

  @Autowired
  private Service2 service2;
  @Autowired
  private Service4 service4;

  public Service1(){
    try {
      logger.info("before construct service1, thread:[{}]", Thread.currentThread().getName());
      TimeUnit.SECONDS.sleep(1);
      logger.info("after construct service1");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @PostConstruct
  private void init(){
    try {
      logger.info("before PostConstruct service1, thread:[{}]", Thread.currentThread().getName());
      TimeUnit.SECONDS.sleep(1);
      map.put("test1", "value1");
      logger.info("after PostConstruct service1");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Logger getLogger() {
    return logger;
  }

	public Service2 getService2() {
		return service2;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

	public Service4 getService4() {
		return service4;
	}

	public void setService4(Service4 service4) {
		this.service4 = service4;
	}
}
