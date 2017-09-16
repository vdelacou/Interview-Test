package com.mfg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Project")
public class Good {

  @Id
  private String id;

  private String name;

  private int age;
  private long productionDate;
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
  public long getProductionDate() {
    return productionDate;
  }
  public void setProductionDate(long productionDate) {
    this.productionDate = productionDate;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  
  
  
}
