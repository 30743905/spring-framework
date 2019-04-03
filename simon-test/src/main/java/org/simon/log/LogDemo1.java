package org.simon.log;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.layout.TTLLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-07 下午 22:28
 * @Description:TODO
 */
public class LogDemo1 {

	@Test
	public void test1(){
		LoggerContext loggerContext = new LoggerContext();
		Logger logger = loggerContext.getLogger(LogDemo1.class);

		BasicConfigurator basicConfigurator = new BasicConfigurator();
		basicConfigurator.configure(loggerContext);

		logger.info("hello,world");
	}

	@Test
	public void test2(){
		LoggerContext lc = new LoggerContext();

		ConsoleAppender<ILoggingEvent> ca = new ConsoleAppender<ILoggingEvent>();
		//ca.setContext(lc);
		ca.setName("console");
		LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
		encoder.setContext(lc);


		// same as
		// PatternLayout layout = new PatternLayout();
		// layout.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
		TTLLLayout layout = new TTLLLayout();

		//layout.setContext(lc);
		layout.start();
		encoder.setLayout(layout);

		ca.setEncoder(encoder);
		ca.start();

		Logger logger = lc.getLogger(LogDemo1.class);
		logger.addAppender(ca);

		logger.info("hello,world");

		/*Logger rootLogger = lc.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.addAppender(ca);*/


	}

}
