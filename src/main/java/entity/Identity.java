package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库表对应实体类-测试表字段自增(表名：IDTY)
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 */
@Entity
@Table(name = "IDTY")
public class Identity {
	private int id;
	private String name;

	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Identity() {
	}
	
	Identity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	Identity(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Idty [id=" + id + ", name=" + name + "]";
	}
}
