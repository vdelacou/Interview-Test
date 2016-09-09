/**
 * Author: Dustin Mao
 */
package com.mfg;

import org.springframework.data.annotation.Id;
//import java.util.Date;

public class Good {

  @Id 
  private long id;

  private String name;
  private int age;
  //private Date productionDate;

  public Good(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
