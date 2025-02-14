package com.example.pos.system.feature.adjustment;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.adjustment.dto.AdjustmentRequest;
import com.example.pos.system.feature.adjustment.dto.UpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adjustments")
@RequiredArgsConstructor
public class AdjustmentController {
    // inject bean service
    private final AdjustmentService adjustmentService;

    @PostMapping
    public ResponseSuccess create(@Valid @RequestBody AdjustmentRequest adjustmentRequest) {
        return adjustmentService.create(adjustmentRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ) {
        return adjustmentService.read(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public JavaResponse<?> readById(@PathVariable Integer id) {
        return adjustmentService.readById(id);
    }

    @PutMapping("/{id}")
    public ResponseSuccess update(@PathVariable Integer id, @Valid @RequestBody AdjustmentRequest adjustmentRequest) {
        return adjustmentService.update(id, adjustmentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess delete(@PathVariable Integer id) {
        return adjustmentService.delete(id);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String search,
            @RequestParam String dateFrom,
            @RequestParam String dateTo
    ){
        return adjustmentService.search(dateFrom,dateTo,pageSize,pageNumber,search);
    }

    @GetMapping("/filter")
    public JavaCollectionResponse<?> filter(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String transaction,
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer reasonId
    ){
        return adjustmentService.filter(dateFrom,dateTo,pageSize,pageNumber,transaction,status,reasonId);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseSuccess updateStatus(@PathVariable Integer id , @Valid @RequestBody UpdateRequest updateRequest){
        return adjustmentService.updateStatus(id,updateRequest);
    }

}
