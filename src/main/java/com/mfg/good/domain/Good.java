package com.mfg.good.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Sand Wen
 * @since 2016.9.7
 */
@Document(collection = "good")
public class Good implements Serializable {
    private ObjectId id;

    @Indexed(unique = true)
    private String name;
    private int age;
    private Date productionDate;

    public Good(){
        id = ObjectId.get();
    }

    @Id
    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        if(ObjectId.isValid(id)){
            this.id = new ObjectId(id);
        }
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
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotNull
    public Date getProductionDate() {
        return productionDate;
    }


    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
