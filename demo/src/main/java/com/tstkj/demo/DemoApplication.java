package com.tstkj.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.tstkj.demo.service.UserTest;
import com.tstkj.logger.aop.ExceptionAspect;
import com.tstkj.logger.aop.OperationAspect;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class DemoApplication {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Lazy(true)
	public ExceptionAspect initAop() {
		ExceptionAspect ex = new ExceptionAspect();
		UserTest u = context.getBean(UserTest.class);
		ex.setContext(context);
		ex.setOperationUser(u);
		ex.setAppName("计算模块");
		ex.setPool(context.getBean(JedisPool.class));
		return ex;
	}

	@Bean
	@Lazy(true)
	public OperationAspect initopt() {
		OperationAspect ex = new OperationAspect();
		UserTest u = context.getBean(UserTest.class);
		ex.setOperationUser(u);
		ex.setPool(context.getBean(JedisPool.class));
		return ex;
	}

	@Bean
	public JedisPool initJedis() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(100);
		config.setMaxWaitMillis(100);
		config.setMaxTotal(10);
		config.setMinIdle(100);
		JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
		return pool;
	}
}
