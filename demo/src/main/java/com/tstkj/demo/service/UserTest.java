package com.tstkj.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tstkj.demo.dao.Mapper;
import com.tstkj.logger.entry.OperationUserInfo;
import com.tstkj.logger.service.OperationUserService;

@Component
public class UserTest implements OperationUserService {
	@Autowired
	private Mapper mapper;

	@Override
	public OperationUserInfo getUserInfo() {
		OperationUserInfo u = new OperationUserInfo();
		u.setUserId(mapper.test("111111"));
		u.setUserName("admin");
		u.setLoginName("admin");
		return u;
	}

}
