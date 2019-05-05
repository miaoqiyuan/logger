package com.tstkj.logger.entry;

import java.io.Serializable;
import java.util.Date;

public class BaseInfoEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6180556059444308834L;

	// 操作的类全路径
	private String operationClass;
	// 操作方法名
	private String operationMethod;
	// 操作方法参数
	private String operationParam;
	// 操作类描述(null)
	private String operationclassDesc;
	// 操作方法名
	private String operationmethedDesc;
	private Date openrationStartTime;
	// 执行成功时间
	private Date operationTime;

	public String getOperationClass() {
		return operationClass;
	}

	public void setOperationClass(String operationClass) {
		this.operationClass = operationClass;
	}

	public String getOperationMethod() {
		return operationMethod;
	}

	public void setOperationMethod(String operationMethod) {
		this.operationMethod = operationMethod;
	}

	public String getOperationParam() {
		return operationParam;
	}

	public void setOperationParam(String operationParam) {
		this.operationParam = operationParam;
	}

	public String getOperationclassDesc() {
		return operationclassDesc;
	}

	public void setOperationclassDesc(String operationclassDesc) {
		this.operationclassDesc = operationclassDesc;
	}

	public String getOperationmethedDesc() {
		return operationmethedDesc;
	}

	public void setOperationmethedDesc(String operationmethedDesc) {
		this.operationmethedDesc = operationmethedDesc;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Date getOpenrationStartTime() {
		return openrationStartTime;
	}

	public void setOpenrationStartTime(Date openrationStartTime) {
		this.openrationStartTime = openrationStartTime;
	}

}
