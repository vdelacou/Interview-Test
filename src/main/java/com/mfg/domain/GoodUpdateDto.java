package com.mfg.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hushuming on 2017/1/14.
 *
 *  object updated from front
 */

@Getter
@Setter
public class GoodUpdateDto implements Serializable{

    private String id;
    private String name;
    private int age;

}
