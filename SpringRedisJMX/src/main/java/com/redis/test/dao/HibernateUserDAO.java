package com.redis.test.dao;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redis.test.domain.User;


/**
 * implementation class for user dao.
 * @author Ketav
 *
 */
@Repository("hibernateUserDAO")
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public class HibernateUserDAO implements UserDAO {
	
	@Inject
	private LocalSessionFactoryBean lsf;
	
	private Session getCurrentSession(){
		try{
			return lsf.getObject().getCurrentSession();
		} catch (HibernateException e){
			return lsf.getObject().openSession();
		}
	}

	@Override
	public User getUserByName(String username) {
		return (User) getCurrentSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("user.userName", username))
				.uniqueResult();
	}

	@Override
	public void addUser(User user) {
		getCurrentSession().save(user);
	}
	
	@Override
	public void deleteUser(User user) {
		getCurrentSession().delete(user);
	}

}
