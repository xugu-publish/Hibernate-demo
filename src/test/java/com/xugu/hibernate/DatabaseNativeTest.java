package com.xugu.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
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
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;
import org.junit.Test;

/**
 * 数据库表操作(非实体类数据获取)
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 *
 */
public class DatabaseNativeTest {

	private Logger logger = Logger.getLogger(DatabaseNativeTest.class);

	/**
	 * 建立会话
	 */
	@Test
	public void testInsert() {

		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();
		// 创建事务
		Transaction transaction = session.beginTransaction();

		// 数据Bean
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("测试用名");
		user.setPassword("123456");
		// 插入一条数据
		session.save(user);
		// 提交事务
		transaction.commit();
		// 关闭工厂
		session.close();
		// 关闭工厂类
		sessionFaction.close();
		logger.info("插入数据：" + user);
	}

	/**
	 * 获取实体表数据
	 */
	@Test
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

		// 查询数据
		String sql = "select * from usert";
		NativeQuery<User> query = session.createNativeQuery(sql, User.class);
		List<User> list = query.getResultList();
		for (User o : list) {
			logger.info(o.getId() + "::" + o.getName() + "::" + o.getPassword());
		}
	}

	/**
	 * 获取非实体表数据
	 */
	@Test
	public void testStatis() {
		String sql = "SELECT id as \"id\", count(id) as \"countId\" from USERT group by id";
		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();

		// 执行原生SQL语句
		Query<DataCount> query = session.createNativeQuery(sql);
		// SQL语句返回结果集封装为Bean
		query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(DataCount.class));
		List<DataCount> list = query.getResultList();
		for (DataCount o : list) {
			logger.info("非实体类数据：" + o);
		}
	}
}
