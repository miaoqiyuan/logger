package com.tstkj.demo.dao;

import org.springframework.stereotype.Component;

@Component
public class Mapper {
	
	public String test(String id) {
		return id+"mapper return";
	}

}
