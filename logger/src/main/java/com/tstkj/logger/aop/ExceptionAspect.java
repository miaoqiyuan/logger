package com.tstkj.logger.aop;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.tstkj.logger.entry.ExceptionEntry;
import com.tstkj.logger.entry.OperationUserInfo;
import com.tstkj.logger.service.OperationUserService;
import com.tstkj.logger.util.GetBaseInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Aspect
public class ExceptionAspect {
	private Logger log = LoggerFactory.getLogger(ExceptionAspect.class);
	
	private String appName;

	private OperationUserService operationUser;

	private ApplicationContext context;

	private JedisPool pool;

	@AfterThrowing(throwing = "ex", pointcut = "execution(* com.tstkj.*.service.*.*(..))")
	public void after(JoinPoint p, Throwable ex)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		System.out.println("@AfterThrowing");
		ExceptionEntry entry = new ExceptionEntry();
		entry.setExMsg(StringUtils.isEmpty(ex.getMessage()) ? "未知异常" : ex.getMessage());
		buildExEntry(p, entry);
		entry.setOperationTime(new Date());
		log.info(entry.toString());
		saveLog(entry);
	}

	private ExceptionEntry buildExEntry(JoinPoint p, ExceptionEntry entry) {
		OperationUserInfo userInfo = operationUser.getUserInfo();
		entry.setOperationUserId(userInfo.getUserId());
		entry.setOperationUserName(userInfo.getUserName());
		BeanUtils.copyProperties(GetBaseInfo.buildBaseInfo(p), entry);
		return entry;
	}

	private void saveLog(ExceptionEntry oe) {
		if (pool != null) {
			Jedis j = pool.getResource();
			j.select(1);
			j.set(UUID.randomUUID().toString(), JSONObject.toJSONString(oe));
		} else {
			log.info("未初始化redis连接信息,日志信息无法持久化保存");
		}
	}

	public OperationUserService getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(OperationUserService operationUser) {
		this.operationUser = operationUser;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
