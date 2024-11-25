package com.example.pos.system.feature.settings.warehouse;

import com.example.pos.system.feature.settings.warehouse.dto.WarehouseRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    // inject bean service
    private final WarehouseService warehouseService;


    /**
     * read warehouse
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping
    JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize){
        return warehouseService.read(pageNumber,pageSize);
    }

    /**
     * search warehouse
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
        return warehouseService.search(pageNumber, pageSize, searchValue);
    }

    /**
     * read warehouse By id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    JavaResponse<?> readById(@PathVariable("id") Integer id){
        return warehouseService.readById(id);
    }

    /**
     * create warehouse
     * @param warehouseRequest
     * @return
     */
    @PostMapping
    ResponseSuccess create(@Valid @RequestBody WarehouseRequest warehouseRequest){
        return warehouseService.create(warehouseRequest);
    }

    /**
     * update warehouse
     * @param id
     * @param warehouseRequest
     * @return
     */
    @PutMapping("/{id}")
    ResponseSuccess update(@Valid @RequestBody WarehouseRequest warehouseRequest, @PathVariable("id") Integer id){
        return warehouseService.update(warehouseRequest,id);
    }

    /**
     * delete warehouse id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseSuccess delete(@PathVariable("id") Integer id){
        return warehouseService.delete(id);
    }
}
