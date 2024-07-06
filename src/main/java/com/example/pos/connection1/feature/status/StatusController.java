package com.example.pos.connection1.feature.status;

import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.status.dto.StatusRequest;
import com.example.pos.connection1.feature.status.dto.StatusResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    /*
      * get status by id
    */
    @GetMapping("/{id}")
    StatusResponse readById(@PathVariable("id") Integer id){
        return statusService.readById(id);
    }

    /*
      * get all status
    */
    @GetMapping
    JavaCollectionResponse<?> read(
        @RequestParam(defaultValue = "10", required = false ) int pageSize, 
        @RequestParam(defaultValue = "0", required = false) int pageNumber
    ){
        return statusService.read(pageSize, pageNumber);
    }

    /*
      * create status
    */
    @PostMapping
    StatusResponse create(@Valid @RequestBody StatusRequest statusRequest){
        return statusService.create(statusRequest);
    }

    /*
      * update status
    */
    @PutMapping("/{id}")
    StatusResponse update(@PathVariable("id") Integer id, @Valid @RequestBody StatusRequest statusRequest){
        return statusService.update(id,statusRequest);
    } 

    /*
      * delete status
    */
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Integer id){
        statusService.delete(id);
    }

}
