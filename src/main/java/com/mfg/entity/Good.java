package com.mfg.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author duand
 *
 */
public class Good implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String id;
	private String name;
	private Integer age;
	
	@JsonIgnoreProperties
	private Date productionDate = new Date();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name this name must be unique, not null and no more than 50 characters
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	/**
	 * 
	 * @param age must not be under zero
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Date getProductionDate() {
		return productionDate;
	}
	
	/**
	 * 
	 * @param productionDate the date the Good was product
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}
}
