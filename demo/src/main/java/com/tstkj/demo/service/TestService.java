package com.tstkj.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tstkj.demo.dao.Mapper;

@Service
public class TestService {
	
	@Autowired
	private Mapper mapper;

	
	public String test(String id)throws NullPointerException {
		return mapper.test(id);
	}
	
	
	public void t() throws NullPointerException{
		throw new NullPointerException("就是错了");
	}
}
