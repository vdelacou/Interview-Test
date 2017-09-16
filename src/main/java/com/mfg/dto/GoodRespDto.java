package com.mfg.dto;

import java.io.Serializable;

public class GoodRespDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;

  private int age;
  private long productionDate;

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

  public long getProductionDate() {
    return productionDate;
  }

  public void setProductionDate(long productionDate) {
    this.productionDate = productionDate;
  }


}
