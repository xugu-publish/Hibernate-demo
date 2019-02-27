package com.xugu.hibernate;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.hibernate.criterion.Projections;

/**
 * 数据库表Dml操作
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 *
 */
public class DatabaseDmlTest {

	private Logger logger = Logger.getLogger(DatabaseNativeTest.class);
	
	/**
	 * 插入表数据
	 */
	//@Test
	public void testInsert() {

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();
		// 创建事务
		Transaction tx = session.beginTransaction();
		// 数据Bean
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("测试用名");
		user.setPassword("123456");
		// 更新数据
		session.save(user);
		// 提交事务
		tx.commit();
		logger.info("插入数据：" + user);
		session.close();
		sessionFaction.close();
	}

	/**
	 * 查询表数据
	 */
	//@ Test
	public void testSelect() {

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();

		// 指定条件查询
		String hql = "FROM User AS U WHERE U.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", "401882cb68a6b5690168a6b56a590000");
		List<User> users = query.list();
		for (User user : users) {
			logger.info("查询数据: " + user);
		}
	}

	/**
	 * 更新表数据
	 */
	//@Test
	public void testUpdate() {

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();
		Transaction tx = session.beginTransaction();
		
		// 指定条件查询
		User user = null;
		String sql = "select * from usert where id='401882cb68a6b5690168a6b56a590000'";
		NativeQuery<User> query = session.createNativeQuery(sql, User.class);
		List<User> list = query.getResultList();
		for (User val : list) {
			user = val;
			logger.info("更新数据(更新前): " + user);
		}
			
		// 更新数据
		user.setPassword("新的密码");
		session.update(user);
		tx.commit();
		logger.info("更新数据(更新后): " + user);
		session.close();
		sessionFaction.close();
	}

	/**
	 * 删除表数据
	 */
	//@Test
	public void testDelete() {

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();
		Transaction tx = session.beginTransaction();
		
		// 数据Bean
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("测试删除");
		user.setPassword("密码");
		// 更新数据
		session.save(user);
		// 提交事务
		tx.commit();
		logger.info("删除数据(删除前)：" + user);
			
		tx = session.beginTransaction();
		user = session.get(User.class, user.getId());
		session.delete(user);
		tx.commit();
		logger.info("删除数据(删除后): " + user);
		session.close();
		sessionFaction.close();
	}
	
}
