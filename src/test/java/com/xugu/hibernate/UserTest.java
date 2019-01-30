package com.xugu.hibernate;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;
import org.junit.Test;

public class UserTest {

	// @Test
	public void test() {

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

		User user = new User(123, "xugu", "1433223");
		session.save(user);
		// 提交事务
		transaction.commit();
		// 关闭工厂
		session.close();
		// 关闭工厂类
		sessionFaction.close();
	}

	//@Test
	public void list() {
		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();

		String sql = "select * from usert";
		NativeQuery<User> query = session.createNativeQuery(sql, User.class);
		List<User> list = query.getResultList();
		for (User o : list) {
			System.out.println(o.getId() + "::" + o.getName() + "::" + o.getPassword());
		}
	}

	 @Test
	public void getCustomer() {
		String sql = "SELECT t.id as id ,COUNT(d.id2) as countid  FROM tt1 t  LEFT JOIN tt2 d   ON t.id = SUBSTR(d.id2,0,1)  GROUP BY  t.id";
		// 创建工厂
		SessionFactory sessionFaction = null;
		// 加载
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
		// 创建Session工厂
		sessionFaction = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		// 创建、打开会话
		Session session = sessionFaction.openSession();

		NativeQuery<HashMap<String, Object>> query = session.createNativeQuery(sql);
		//query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(DataCount.class));
		query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<HashMap<String, Object>> list = query.getResultList();
		for (HashMap<String, Object> d : list) {
			System.out.println(d.get("ID")+"  "+d.get("COUNTID"));
		}
	}
	 
	
}
