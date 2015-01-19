package com.redis.test.dao;

import com.redis.test.domain.User;

/**
 * dao interface for user
 * 
 * @author Ketav
 */

public interface UserDAO {
	public User getUserByName(String userName);
	public void addUser(User user);
	public void deleteUser(User user);
}
