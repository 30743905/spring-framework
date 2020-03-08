package org.simon.beanfactoryPostProcessor.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-26 19:21 Description:TODO
 */
//@Component
public class Service2 {
  private Logger logger = LoggerFactory.getLogger(Service2.class);
  private Map<String,String> map = new HashMap();

  public Service2(){
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
}
