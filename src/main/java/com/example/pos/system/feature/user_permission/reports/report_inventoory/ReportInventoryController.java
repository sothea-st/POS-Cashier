package com.example.pos.system.feature.user_permission.reports.report_inventoory;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportInventory")
public class ReportInventoryController {
    private final ReportInventoryService reportInventoryService;

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo
    ){
        return reportInventoryService.read(dateFrom,dateTo,pageSize,pageNumber);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo,
            @RequestParam(name = "search") String search
    ){
        return reportInventoryService.search(dateFrom,dateTo,pageSize,pageNumber,search);
    }


}
