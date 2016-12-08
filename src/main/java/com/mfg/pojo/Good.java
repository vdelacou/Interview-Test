package com.mfg.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Good implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218700640961573332L;
	
	private String _id;
	
	private String name;
	
	private int age;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField (format="yyyy-MM-dd")  
	private Date productionDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	
}
