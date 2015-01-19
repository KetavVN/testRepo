package com.redis.test.service;

import com.redis.test.domain.User;


/**
 * user service
 * @author Ketav
 */
public interface UserService {
	public User findUser(String userName);
	public void saveUser(User user);
	public void removeUser(User user);
}
