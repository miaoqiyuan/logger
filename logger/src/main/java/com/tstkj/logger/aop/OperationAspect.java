package com.tstkj.logger.aop;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.tstkj.logger.entry.OperaEntry;
import com.tstkj.logger.entry.OperationUserInfo;
import com.tstkj.logger.service.LogService;
import com.tstkj.logger.service.OperationUserService;
import com.tstkj.logger.util.GetBaseInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Aspect
public class OperationAspect {
	private Logger log = LoggerFactory.getLogger(OperationAspect.class);

	private OperationUserService operationUser;

	private ApplicationContext context;

	private JedisPool pool;

	@Pointcut("execution (* com.tstkj.*.controller.*.create*(..))")
	public void createPointcut() {

	}

	@Pointcut("execution (* com.tstkj.*.controller.*.modify*(..))")
	public void modifyPointcut() {

	}

	@Pointcut("execution (* com.tstkj.*.controller.*.remove*(..))")
	public void removePointcut() {

	}

	@Around("createPointcut()")
	public Object createOperation(ProceedingJoinPoint p) throws Throwable {
		OperaEntry oe = new OperaEntry();
		oe.setOperationType("add");
		oe.setOperationTypeChn("添加");
		this.buildOperaEntry(p, oe);
		return formatResult(p, oe);
	}

	@Around("modifyPointcut()")
	public Object modifyOperation(ProceedingJoinPoint p) throws Throwable {
		OperaEntry oe = new OperaEntry();
		oe.setOperationType("update");
		oe.setOperationTypeChn("修改");
		this.buildOperaEntry(p, oe);
		return formatResult(p, oe);
	}

	@Around("removePointcut()")
	public Object deleteOperation(ProceedingJoinPoint p) throws Throwable {
		OperaEntry oe = new OperaEntry();
		oe.setOperationType("delete");
		oe.setOperationTypeChn("删除");
		this.buildOperaEntry(p, oe);
		LogService logservice = (LogService) context.getBean(p.getTarget().getClass());
		oe.setOperationParam(logservice.getInfoById((String) p.getArgs()[0]));
		return formatResult(p, oe);
	}

	private Object formatResult(ProceedingJoinPoint p, OperaEntry oe) throws Throwable {
		Object o = p.proceed();
		System.out.println(o);
		System.out.println(o.getClass().getName());
		if (!(o instanceof String)) {
			System.out.println("ex");
			oe.setOperationStatus("fail");
		} else {
			System.out.println("success");
			oe.setOperationStatus("success");
		}
		log.info(oe.toString());
		saveLog(oe);
		return o;
	}

	private void saveLog(OperaEntry oe) {
		if (pool != null) {
			Jedis j = pool.getResource();
			j.select(0);
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

	private OperaEntry buildOperaEntry(JoinPoint p, OperaEntry oe) {
		OperationUserInfo userInfo = operationUser.getUserInfo();
		oe.setOperationUserId(userInfo.getUserId());
		oe.setOperationUserName(userInfo.getUserName());
		BeanUtils.copyProperties(GetBaseInfo.buildBaseInfo(p), oe);
		return oe;
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

}
