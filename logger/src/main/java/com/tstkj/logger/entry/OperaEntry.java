package com.tstkj.logger.entry;

import java.io.Serializable;

/**
 * @author mqy
 *
 */
public class OperaEntry extends BaseInfoEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1280531868053458060L;
	// 操作人ID
	private String operationUserId;
	// 操作人用户名
	private String operationUserName;
	// 操作类型
	private String operationType;// add update del
	// 操作类型中文
	private String operationTypeChn;
	// 操作状态(成功 or 失败)
	private String operationStatus; // success fail

	public String getOperationUserId() {
		return operationUserId;
	}

	public void setOperationUserId(String operationUserId) {
		this.operationUserId = operationUserId;
	}

	public String getOperationUserName() {
		return operationUserName;
	}

	public void setOperationUserName(String operationUserName) {
		this.operationUserName = operationUserName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationTypeChn() {
		return operationTypeChn;
	}

	public void setOperationTypeChn(String operationTypeChn) {
		this.operationTypeChn = operationTypeChn;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[用户:").append(this.operationUserName).append("执行了:").append(getOperationClass()).append("(")
				.append(getOperationclassDesc()).append(")").append("的").append(getOperationMethod()).append("(")
				.append(getOperationmethedDesc()).append(")").append("方法").append(",参数为:").append(getOperationParam())
				.append("]");
		return sb.toString();
	}

}
