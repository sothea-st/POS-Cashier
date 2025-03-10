package com.example.pos.system.feature.order_online;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.order_online.dto.OrderOnlineRequest;
import com.example.pos.system.feature.order_online.dto.RejectReason;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orderOnline")
@RequiredArgsConstructor
public class OrderOnlineController {

    // inject bean service
    private final OrderOnlineService orderOnlineService;

    @PostMapping
    public ResponseSuccess create(@Valid @RequestBody OrderOnlineRequest request){
        return orderOnlineService.create(request);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam(required = false) String orderStatus,
            @RequestParam(required = false) String paymentStatus
    ) {
        return orderOnlineService.read(pageNumber,pageSize,dateFrom,dateTo,orderStatus,paymentStatus);
    }


    @GetMapping("/search")
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam(required = false) String orderStatus,
            @RequestParam(required = false) String paymentStatus,
            @RequestParam(required = false) String search
    ) {
        return orderOnlineService.search(pageNumber,pageSize,dateFrom,dateTo,orderStatus,paymentStatus,search);
    }

    @GetMapping("/calculate")
    public JavaResponse<?> calculate(){
        return orderOnlineService.calculate();
    }

    @GetMapping("/update/orderStatus/{id}")
    public JavaResponse<?> updateOrderStatus(@PathVariable Integer id ,@RequestParam String orderStatus ) {
        return  orderOnlineService.updateOrderStatus(id,orderStatus);
    }

    @PutMapping("/update/reject/{id}")
    public ResponseSuccess reject(@PathVariable Integer id ,@RequestBody RejectReason rejectReason ) {
        return  orderOnlineService.rejectOrder(id,rejectReason);
    }
}
