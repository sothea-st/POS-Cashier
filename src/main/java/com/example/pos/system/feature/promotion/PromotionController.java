package com.example.pos.system.feature.promotion;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.promotion.dto.request.PromotionRequest;
import com.example.pos.system.feature.promotion.dto.request.PromotionStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotions")
public class PromotionController {
    // inject bean service
    private final PromotionService promotionService;

    @PostMapping
    public ResponseSuccess create(@Valid @RequestBody PromotionRequest promotionRequest){
        return promotionService.create(promotionRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ) {
        return promotionService.read(pageNumber,pageSize);
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess delete(@PathVariable Integer id) {
        return promotionService.delete(id);
    }

    @GetMapping("/{id}")
    public JavaResponse<?> readById(@PathVariable Integer id) {
        return promotionService.readById(id);
    }

    @PutMapping("/{id}")
    public ResponseSuccess update(@PathVariable Integer id , @Valid @RequestBody PromotionRequest promotionRequest){
        return promotionService.update(id,promotionRequest);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String search
    ){
        return promotionService.search(pageNumber,pageSize,search);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseSuccess updateStatus(@PathVariable Integer id , @Valid @RequestBody PromotionStatusRequest promotionStatusRequest){
        return promotionService.updateStatus(id,promotionStatusRequest);
    }

}
