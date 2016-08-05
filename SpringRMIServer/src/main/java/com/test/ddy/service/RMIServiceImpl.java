package com.test.ddy.service;

import org.apache.log4j.Logger;

import com.test.ddy.model.User;

public class RMIServiceImpl implements IRMIService {
	
	private static final Logger logger = Logger.getLogger(RMIServiceImpl.class);
			
	@Override
	public String getUserName(String name, User user) {
		// TODO Auto-generated method stub
		return "hello,"+name+"User:"+user.getName()+","+user.getAddress();
	}

}
