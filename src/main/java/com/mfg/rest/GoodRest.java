package com.mfg.rest;

import com.mfg.Repository.GoodRepository;
import com.mfg.domain.GoodCreateDto;
import com.mfg.domain.GoodUpdateDto;
import com.mfg.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hushuming on 2017/1/13.
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodRest {


    private final GoodRepository repository;
    private final GoodService goodService;

    //get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10

    @GetMapping("/good")
    public Page queryRecentGoods(
            @PageableDefault(page = 0, size = 10, sort = "productionDate",
                    direction = Sort.Direction.DESC)
                    Pageable pageable){

        return repository.findAll(pageable);
    }

    //GET /api/good/{id}: get a good by it's id

    @GetMapping("/good/{id}")
    public ResponseEntity findById(@PathVariable String id){
                return ResponseEntity.ok(repository.findById(id).orElse(null));
    }

    // post by json in body a new Good to create it
    @PostMapping("/good")
    public ResponseEntity save(@RequestBody GoodCreateDto dto){

        goodService.createValidate(dto);
        repository.save(goodService.createDtoToGood(dto));
        return ResponseEntity.ok("ok");
    }

    // put by json in body an existing Good to update it

    @PutMapping("/good")
    public ResponseEntity update(@RequestBody GoodUpdateDto dto){
        goodService.updateValidate(dto);

            repository.findById(dto.getId())
                    .ifPresent(e->{
                        if(dto.getName()!=null){
                            e.setName(dto.getName());
                        }
                        if(dto.getAge()>0){
                            e.setAge(dto.getAge());
                        }
                        repository.save(e);
                    });
        return ResponseEntity.ok("ok");
    }


    // DELETE /api/good/{id}: delete a Good by it's id

    @DeleteMapping("/good/{id}")
    public ResponseEntity del(@PathVariable String id){

        Assert.isTrue(repository.findById(id).isPresent(),"id not exist!");
        repository.findById(id)
                .ifPresent(e->repository.delete(id));
        return ResponseEntity.ok("ok");
    }


}
