#虚谷数据库使用Hibernate框架的demo程序

一. Demo程序使用步骤：
1. 从https://github.com/xugu-publish/xugu-hibernate-demo.git下载最新源代码，并引入工程
2. 在pom.xml中配置lib下的虚谷数据库jdbc依赖包，将依赖包拷贝只maven工程对应的环境目录结构中，并update maven工程
3. 执行init_db.sql中的SQL语句(创建表及初始化数据)
4. 执行测试类，查看运行结果

二. 文件介绍
1. hibernate.cfg.xml、User.hbm.xml为Hibertae的配置文件 
2. User、Identity类为实体类，其配置文件为相应名字的xml文件。 
3. DatabaseDmlTest 类实现了通过Dao对象对表数据的增删查改和清空。
4. DatabaseNativeTest 类实现了不通过dao对象执行自定义查询语句、多表join查询。 
5. lib为虚谷驱动，在meven目录下加入驱动，或者add to build path(pom驱动的dependency注释掉)