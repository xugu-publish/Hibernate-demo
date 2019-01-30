#虚谷数据库使用Hibernate框架的demo程序

1、hibernate.cfg.xml为Hibertae的配置文件
2、Tt1、Tt2、User类为实体类，其配置文件为相应名字的xml文件。
3、TestUser 类实现了通过Dao对象对表数据的增删查改和清空。
4、UserTest 类实现了不通过dao对象执行自定义查询语句、多表join查询。
5、target/lib为虚谷JDBC驱动