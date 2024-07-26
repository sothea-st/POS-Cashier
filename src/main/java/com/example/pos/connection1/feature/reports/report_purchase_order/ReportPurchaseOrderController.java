package com.example.pos.connection1.feature.reports.report_purchase_order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/reportPurchaseOrder")
@RequiredArgsConstructor
public class ReportPurchaseOrderController {
    private final ReportPurchaseOrderService reportPurchaseOrderService;

    @GetMapping
    public JavaCollectionResponse<?> purchase(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @Valid @RequestParam(name = "dateFrom") String dateFrom,
            @Valid @RequestParam(name = "dateTo") String dateTo) {
        return reportPurchaseOrderService.reportPurchaseOrder(pageNumber, pageSize, dateFrom, dateTo);
    }

    @GetMapping("/filter/{vendorName}")
    public JavaCollectionResponse<?> filter(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "100", required = false) int pageSize,
            @PathVariable("vendorName") String vendorName) {
        return reportPurchaseOrderService.filter(pageNumber, pageSize, vendorName);
    }


    @GetMapping("/getReportPoByRemark")
    public JavaCollectionResponse<?> getReportByRemark(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @Valid @RequestParam(name = "dateFrom") String dateFrom,
            @Valid @RequestParam(name = "dateTo") String dateTo ,
            @Valid @RequestParam(name = "userID") Integer userId ,
            @Valid @RequestParam(name = "remark") String remark
            ) {
        return reportPurchaseOrderService.getReportByRemark(pageNumber, pageSize, dateFrom, dateTo,userId,remark);
    }

 
}
