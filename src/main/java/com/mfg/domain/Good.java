package com.mfg.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sun.istack.internal.NotNull;

@Document(collection="good")
public class Good implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private Integer age;
	private Date productionDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@Size(max = 50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Min(0)
	public Integer getAge() {
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

	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", age=" + age + ", productionDate=" + productionDate + "]";
	}
	
}
