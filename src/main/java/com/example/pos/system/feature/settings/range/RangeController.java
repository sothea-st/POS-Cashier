package com.example.pos.system.feature.settings.range;

import com.example.pos.system.feature.settings.range.dto.RangeRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranges")
public class RangeController {
    // inject bean service
    private final RangeService rangeService;

    /**
     * create
     * @return
     */
    @PostMapping
    public ResponseSuccess create(@Valid @RequestBody RangeRequest rangeRequest){
        return rangeService.create(rangeRequest);
    }

    /**
     * read
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping
    JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false)Integer pageSize
    ){
        return rangeService.read(pageNumber, pageSize);
    }

    /**
     * read by id
     * @return
     */
    @GetMapping("/{id}")
    JavaResponse<?> readById(@PathVariable("id") Integer id){
        return rangeService.readById(id);
    }

    /**
     * Delete by id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseSuccess deleteById(@PathVariable("id") Integer id){
        return rangeService.deleteById(id);
    }

    /**
     * update by id
     * @return
     */
    @PutMapping("/{id}")
    ResponseSuccess updateById( @Valid @RequestBody RangeRequest rangeRequest, @PathVariable("id") Integer id){
        return rangeService.updateById(rangeRequest,id);
    }


    /**
     * search range
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @GetMapping("/search")
    JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "search", required = false) String searchValue){
        return rangeService.search(pageNumber, pageSize, searchValue);
    }


    @GetMapping("/readByWarehouseId/{id}")
    JavaCollectionResponse<?> readByWarehouseId( @PathVariable("id") Integer id ){
        return rangeService.readByWarehouseId(id);
    }

}
