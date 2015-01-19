package com.redis.test.dao;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.test.domain.User;

@Repository("redisUserDAO")
public class RedisUserDAO implements UserDAO{
	
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, User> hashOps;
	
	private static final String mapName = "users";
	
	@Override
	public User getUserByName(String userName) {
		return hashOps.get(mapName, userName);
	}

	@Override
	public void addUser(User user) {
		hashOps.put(mapName, user.getUserName(), user);
		redisTemplate.expire(mapName, 10, TimeUnit.SECONDS);
	}

	@Override
	public void deleteUser(User user) {
		hashOps.delete(mapName, user.getUserName());
	}

}
