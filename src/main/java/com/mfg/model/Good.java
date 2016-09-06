package com.mfg.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
public class Good implements Serializable{
    @Id
    private int id;

    private String name;

    private int age;

    private Date productionDate;

    public Good() {
    }

    public Good(int id, String name, int age, Date productionDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.productionDate = productionDate;
    }

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
}
