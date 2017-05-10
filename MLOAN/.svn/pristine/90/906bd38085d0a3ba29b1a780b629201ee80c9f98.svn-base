package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.UsersMapper;
import com.po.Users;
import com.service.UserService;

import jdk.nashorn.internal.runtime.UserAccessorProperty;


@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public boolean checkEmailExist(String usermail) {
			Map params=new HashMap<>();
			params.put("usermail", usermail);
			int count= usersMapper.countByConditions(params);
			if(count==0){
					return false;
			}else{
					return true;
			}
	}

	@Override
	public boolean checkUsernameExist(String username) {
			Map params=new HashMap<>();
			params.put("username", username);
			int count= usersMapper.countByConditions(params);
			if(count==0){
					return false;
			}else{
					return true;
			}
	}

	@Override
	public int addUser(Users user){
		 	Integer count;
			try {
				count = usersMapper.addUser(user);
			} catch (Exception e) {
				String message = e.getMessage();
				if(message.contains("for key 'usermail_unique'")){
					count=500;
				}else if(message.contains("for key 'username_unique'")){
					count=600;
				}else{
					count=0;
				}
			}
			return count;
	}
	

}
