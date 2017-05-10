package com.service;

import com.po.Users;

public interface UserService {

	boolean checkEmailExist(String usermail);

	boolean checkUsernameExist(String username);

	int addUser(Users user) throws Exception;

}
