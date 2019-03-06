package entity;

import java.math.BigDecimal;

/**
 * 封装数据类(用于非实体类数据封装)
 * 
 * @author xugu-publish
 * @date 2019-02-01
 * @since 1.8
 */
public class DataCount {
	String id;
	BigDecimal countId;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public BigDecimal getCountId() {

		return countId;
	}

	public void setCountId(BigDecimal countId) {

		this.countId = countId;
	}

	public DataCount() {
	}
	
	@Override
	public String toString() {
		return "Bean数据 [id=" + id + ", countId=" + countId + "]";
	}
}
