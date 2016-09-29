package com.mfg.entities;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

public class Good {
	 @Id
	 public String id;
	 
	 @NotNull
	 @NotEmpty
	 @Size(max=50,min=1)
	 private String name;
	 
	 @DecimalMin(value="1")
	 private int age;
	 
	 private Date productionDate;
	 
	 public Good(){}

	 public Good(String name, int age, Date productionDate){
		 this.name = name;
		 this.age =age;
		 this.productionDate = productionDate;
	 }
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

	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", age=" + age
				+ ", productionDate=" + productionDate + "]";
	}
	 
	 
}
