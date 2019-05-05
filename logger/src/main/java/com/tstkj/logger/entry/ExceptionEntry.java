package com.tstkj.logger.entry;

import java.io.Serializable;

public class ExceptionEntry extends BaseInfoEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4287874538578698740L;
	// 操作人ID
	private String operationUserId;
	// 操作人用户名
	private String operationUserName;
	// 错误信息
	private String exMsg;

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

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[用户:").append(this.operationUserName).append("执行:").append(getOperationClass()).append(".")
				.append(getOperationMethod()).append("时发生错误").append(",参数为:").append(getOperationParam())
				.append("错误信息为:").append(exMsg).append("]");
		return sb.toString();
	}

}
