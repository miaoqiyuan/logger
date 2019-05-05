package com.tstkj.logger.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import org.aspectj.lang.JoinPoint;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;
import com.tstkj.logger.anno.TstLogger;
import com.tstkj.logger.entry.BaseInfoEntry;

public class GetBaseInfo {

	public static BaseInfoEntry buildBaseInfo(JoinPoint p) {
		BaseInfoEntry base = new BaseInfoEntry();
		base.setOpenrationStartTime(new Date());
		String methodName = p.getSignature().getName();
		base.setOperationMethod(methodName);
		Method[] methods = p.getTarget().getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				TstLogger lo = m.getAnnotation(TstLogger.class);
				if (lo != null) {
					base.setOperationmethedDesc(lo.value());
				}
			}
		}
		Class<? extends Object> c = p.getTarget().getClass();
		base.setOperationClass(c.getName());
		TstLogger l = (TstLogger) c.getAnnotation(TstLogger.class);
		if (l != null) {
			base.setOperationclassDesc(l.value());
		}
		Object[] obj = p.getArgs();
		HashMap<String, Object> param = new HashMap<>();
		//过滤jsr303 校验结果
		for (int i = 0; i < obj.length; i++) {
			if (checkBaseDataType(obj[i])) {
				param.put("arg" + i, obj[i]);
			} else if (obj[i] instanceof BindingResult) {
				param.put("arg" + i, "BindingResult");
			} else {
				param.put("arg" + i, obj[i]);
			}
		}
		if (param.size() > 0) {
			String par = JSONObject.toJSONString(param);
			base.setOperationParam(par);
		}
		base.setOperationTime(new Date());
		return base;
	}

	/**
	 * 判断o是否是基本数据类型
	 * 
	 * @param o
	 * @return
	 */
	public static boolean checkBaseDataType(Object o) {
		if ((o instanceof String) || (o instanceof Integer) || (o instanceof Boolean) || (o instanceof Double)
				|| (o instanceof Long) || (o instanceof Short)) {
			return true;
		} else {
			return false;
		}
	}

}
