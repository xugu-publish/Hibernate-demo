package com.xugu.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;


public class TestUser {
	//@Test
	//insert
	public void testInsert()
	{

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
		User user = new User();
		user.setName("cao");
		user.setPassword("123456");
		session.save(user);
		tx.commit();
		session.close();
		sessionFaction.close();
	}
	//@ Test
	//select
	public void testSelect()
	{

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();
		
		//全表查询
//		String hql = "FROM User";
//		List<User> users = query.list();
//        for (User user : users) {
//            System.out.println("Id: "+user.getId()+" User: " + user.getName()+" Password: "+user.getPassword());
//        }
		
		//指定条件查询
		String hql = "FROM User AS U WHERE U.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", 1);
        List<User> users = query.list();
        for (User user : users) {
            System.out.println("Id: "+user.getId()+" User: " + user.getName()+" Password: "+user.getPassword());
        }
	}
	
	//@Test
	//update
	public void testUpdate()
	{

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
		User user = session.get(User.class, 2);
		user.setPassword("123456789");
		session.update(user);
		tx.commit();
		session.close();
		sessionFaction.close();
	}
	
	//@Test
	//update
	public void testDelete()
	{
		
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
		User user = session.get(User.class, 2);
		session.delete(user);
		tx.commit();
		session.close();
		sessionFaction.close();
	}
	
	@Test
	//truncate
	public void TestTruncate()
	{

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
		User user = new User(1,"cao","123456");
		session.delete("from user",user);
		
	    tx.commit();
	    session.close();
	    sessionFaction.close();
	}
}
