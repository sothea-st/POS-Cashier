package com.example.pos.system.feature.reports.report_stock.stock;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reportStock")
public class StockController {
    // inject bean service
    private final StockService stockService;


    @GetMapping("/stockAvailable")
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam Integer statusId
    ) {
        return stockService.read(pageNumber, pageSize, statusId);
    }

    @GetMapping("/stockAvailable/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam Integer statusId,
            @RequestParam String search
    ) {
        return stockService.search(pageNumber, pageSize, statusId, search);

    }

}
