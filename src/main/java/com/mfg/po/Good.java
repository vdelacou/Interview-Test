package com.mfg.po;

import com.mfg.vo.ValidatorGroup;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by lw on 16/12/7.
 */
@Document
public class Good {

    @Id
    private String id;

    @NotNull(message="name cannot be null")
    @Size(max=50,message="name max length 50")
    @Indexed(unique = true)
    private String name;

    @Min(value=1,message="age at least should be 1")
    @NotNull(message = "age cannot be null")
    private Integer age;

    @CreatedDate
    private Date productionDate;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
