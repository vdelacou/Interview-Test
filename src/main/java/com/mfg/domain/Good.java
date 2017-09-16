package com.mfg.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/19.
 */
@Document
public class Good {

    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    @Indexed(unique = true)
    private String name;

    @Min(0)
    private int age;

    @CreatedDate
    private Date productionDate;


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


    public Good(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Good(){

    }
}
