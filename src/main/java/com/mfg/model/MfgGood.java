package com.mfg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @PackageName com.mfg.model
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
public class MfgGood {
    @Id
    private Long id; //id
    @NotNull
    @Indexed(unique = true)
    private String name; ///the name of the Good, this name must be unique, not null and no more than 50 characters
    @Min(0)
    private Integer age; //the age of the Good, must not be under zero
    private Date productionDate; //the date the Good was product

    public MfgGood(String name, Integer age, Date productionDate) {
        this.name = name;
        this.age = age;
        this.productionDate = productionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
