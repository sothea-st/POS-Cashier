package com.example.pos.system.feature.settings.slot;

import com.example.pos.system.feature.settings.slot.dto.SlotRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/slots")
public class SlotController {
    // inject bean service
    private final SlotService slotService;

    /**
     * create slot
     * @param slotRequest
     * @return
     */
    @PostMapping
    ResponseSuccess create(@Valid @RequestBody SlotRequest slotRequest) {
        return slotService.create(slotRequest);
    }

    /**
     * read slot
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping
    JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false)Integer pageSize){
        return slotService.read(pageNumber, pageSize);
    }

    /**
     * read by id
     * @return
     */
    @GetMapping("/{id}")
    JavaResponse<?> readById(@PathVariable("id") Integer id){
        return slotService.readById(id);
    }

    /**
     * delete by id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseSuccess deleteById(@PathVariable("id") Integer id){
        return slotService.deleteById(id);
    }

    /**
     * update by id
     * @return
     */
    @PutMapping("/{id}")
    ResponseSuccess updateById(@Valid @RequestBody SlotRequest slotRequest, @PathVariable("id") Integer id){
        return slotService.updateById(slotRequest,id);
    }

    /**
     * search serch
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
        return slotService.search(pageNumber, pageSize, searchValue);
    }

    @GetMapping("/readByRangeId/{id}")
    JavaCollectionResponse<?> readByWarehouseId( @PathVariable("id") Integer id ){
        return slotService.readByRangeId(id);
    }


}
