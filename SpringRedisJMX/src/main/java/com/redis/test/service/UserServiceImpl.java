package com.redis.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redis.test.annotations.RCached;
import com.redis.test.dao.UserDAO;
import com.redis.test.domain.User;

/**
 * user service implementation
 * 
 * @author Ketav
 */

@Service
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired @Qualifier("hibernateUserDAO")
	private UserDAO userDAO;
	
	private static final String mapName = "users";
	
	@Override @RCached(key=mapName)
	public User findUser(String userName) {
		return userDAO.getUserByName(userName);
	}

	@Override @RCached(key=mapName)
	public void saveUser(User user) {
		userDAO.addUser(user);
	}
	
	@Override @RCached(key=mapName)
	public void removeUser(User user){
		userDAO.deleteUser(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
