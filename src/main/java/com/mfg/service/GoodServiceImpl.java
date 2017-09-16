package com.mfg.service;

import com.mfg.Repository.GoodRepository;
import com.mfg.domain.Good;
import com.mfg.domain.GoodCreateDto;
import com.mfg.domain.GoodUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * Created by hushuming on 2017/1/14.
 */

@Component("goodService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodServiceImpl implements GoodService {

    private final GoodRepository repository;


    @Override
    public void createValidate(GoodCreateDto dto) {
        Assert.hasText(dto.getName(),"name not be empty");
        Assert.isTrue(dto.getName().trim().length()<=50,
                "name's length must be no more than 50");

        Boolean unique=!repository.findByName(dto.getName()).isPresent();

        Assert.isTrue(unique,"name has exist");
        Assert.isTrue(dto.getAge()>=0,"age not be under zero");
    }

    @Override
    public void updateValidate(GoodUpdateDto dto) {

        boolean idExist=repository.findById(dto.getId()).isPresent();
        Assert.isTrue(idExist,"id not exist");

        boolean nameCorrect=dto.getName()!=null&&dto.getName().trim().length()>0&&
                dto.getName().trim().length()<=50;

        Assert.isTrue(dto.getAge()>=0,"age not be under zero");

        if(dto.getName()!=null){
            Assert.isTrue(nameCorrect,"name is not correct");
            Boolean unique=!repository.findByName(dto.getName()).isPresent();
            Assert.isTrue(unique,"name has exist");
        }


    }

    @Override
    public Good createDtoToGood(GoodCreateDto dto) {
        Good good=new Good();
        good.setName(dto.getName());
        good.setAge(dto.getAge());
        good.setProductionDate(LocalDateTime.now());
        return good;
    }


}
