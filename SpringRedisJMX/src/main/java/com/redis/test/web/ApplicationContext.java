package com.redis.test.web;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java based root application context
 * 
 * @author ketav
 */

@Configuration
@EnableTransactionManagement
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.redis.test.dao",
		"com.redis.test.service", "com.redis.test.domain",
		"com.redis.test.aspects", "com.redis.test.annotations"})
//@Import({JMXContext.class})
//@ImportResource(value="classpath:com/redis/test/aspects/aspectsContext.xml")
public class ApplicationContext {
	
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
		ds.setUsername("postgres");
		ds.setPassword("abc123");
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setMaxActive(10);
		return ds;
	}
	
	@Bean @Autowired
	public LocalSessionFactoryBean sessionFactory(BasicDataSource dataSource){
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource);
		sf.setPackagesToScan("com.redis.test.domain");
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
		p.setProperty("hibernate.show_sql", "true");
		p.setProperty("hibernate.default_shema", "public");
		p.setProperty("hibernate.hbm2ddl", "create-drop");
		sf.setHibernateProperties(p);
		return sf;
	}
	
	@Bean @Autowired
	public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sf){
		return new HibernateTransactionManager(sf.getObject());
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory cf = new JedisConnectionFactory();
		cf.setHostName("localhost");
		cf.setPort(6379);
		/*JedisPoolConfig pc = new JedisPoolConfig();
		pc.set
		cf.setPoolConfig(new JedisPoolConfig());*/
		return cf;
	}
	
	@Bean @Autowired
	public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
		RedisTemplate<?, ?> t = new RedisTemplate<>();
		t.setConnectionFactory(jedisConnectionFactory);
		return t;
	}
	
	/*@Bean @Autowired
	public RedisAspect redisAspect(RedisTemplate<String, User> template){
		RedisAspect aspect = Aspects.aspectOf(RedisAspect.class);
		aspect.setRedisTemplate(template);
		aspect.setHashOps(template.opsForHash());
		return aspect;
	}*/

}
