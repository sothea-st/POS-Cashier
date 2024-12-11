package com.example.pos.system.feature.settings.uom;

import com.example.pos.system.feature.settings.uom.dto.UomRequest;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/uom")
@RequiredArgsConstructor

public class UomController {

    // inject bean service
    private final UomService uomService;

    /**
     * create uom
     * @param uomRequest
     * @return
     */
    @PostMapping
    ResponseSuccess create(@Valid @RequestBody UomRequest uomRequest){
        return uomService.create(uomRequest);
    }

    /**
     * read uom
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping
    JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false)Integer pageSize){
        return uomService.read(pageNumber, pageSize);
    }


    /**
     * read by id uom
     * @return
     */
    @GetMapping("/{id}")
    JavaResponse<?> read(@PathVariable("id") Integer id){
        return uomService.readById(id);
    }

    /**
     * update uom
     * @param id
     * @param uomRequest
     * @return
     */
    @PutMapping("/{id}")
    ResponseSuccess update(@Valid @RequestBody UomRequest uomRequest, @PathVariable("id") Integer id){
        return uomService.update(uomRequest,id);
    }

    /**
     * delete uom by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseSuccess delete(@PathVariable("id") Integer id){
        return uomService.delete(id);
    }

    /**
     * search Uom
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @GetMapping("/search")
    JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "search") String searchValue){
        System.out.println("11111111111111111111111");
        return uomService.search(pageNumber,pageSize,searchValue);
    }

}
