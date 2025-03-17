package com.example.pos.system.feature.reports.report_product_stock_in;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reportProductStockIn")
@RequiredArgsConstructor
public class ReportProductStockInController {
    private final ReportProductStockInService reportProductStockInService;


    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String dateFrom,
            @RequestParam String dateTo
    ){
        return reportProductStockInService.read(pageNumber,pageSize,dateFrom,dateTo);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam(required = false) String search
    ){
        return reportProductStockInService.search(pageNumber,pageSize,dateFrom,dateTo,search);
    }


}
