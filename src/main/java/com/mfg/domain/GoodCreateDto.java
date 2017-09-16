package com.mfg.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hushuming on 2017/1/14.
 *
 *   object  created from front
 */

@Getter
@Setter
public class GoodCreateDto implements Serializable {

    private String name;
    private int age;
}
