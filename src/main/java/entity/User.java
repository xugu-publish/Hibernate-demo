package entity;

import java.io.Serializable;
import javax.persistence.Entity;//引入这个类就正确了
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库表对应实体类(表名：USERT)
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 */
@Entity
@Table(name = "USERT")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
	}

	User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
