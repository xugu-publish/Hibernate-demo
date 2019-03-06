package mapper;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import entity.Identity;

/**
 * 数据库表自增
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 *
 */
public class DatabaseIdentityTest {

	private Logger logger = Logger.getLogger(DatabaseIdentityTest.class);
	
	/**
	 * 插入数据
	 */
	@Test
	public void insert() {

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

		// 插入一条记录
		Identity identity = new Identity();
		identity.setName(UUID.randomUUID().toString());
		session.save(identity);
		// 提交事务
		transaction.commit();
		logger.info("插入一条数据：" + identity);
		
		String sql = "select * from idty";
		NativeQuery<Identity> query = session.createNativeQuery(sql, Identity.class);
		List<Identity> list = query.getResultList();
		for (Identity o : list) {
			logger.info("Idty表数据：" + o);
		}
		
		// 关闭工厂
		session.close();
		// 关闭工厂类
		sessionFaction.close();
	}
}
