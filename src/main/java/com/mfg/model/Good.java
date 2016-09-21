package com.mfg.model;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class Good {

	@Id	
	private String id;
	
	@NotNull
    @Size(min=1, max=50)
	private String name;
	
	@NotNull
	private Date productionDate;
	
	@NotNull
    @Min(0)
	private Integer age;
	
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, name='%s', age='%d', productionDate='%s']",
                id, name, age, productionDate.toString());
    }
}
