package com.mfg.domain;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by hushuming on 2017/1/13.
 *
 * create Good object
 */

@Data
@Document
@QueryEntity    //使用qsdl
public class Good implements Serializable{

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private int age;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime productionDate;


    public Good(){
        this.id= UUID.randomUUID().toString();
    }

}
