package com.example.pos.connection1.feature.reports.report_purchase_order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/reportPurchaseOrder")
@RequiredArgsConstructor
public class ReportPurchaseOrderController {
    private final ReportPurchaseOrderService reportPurchaseOrderService;

    @PostMapping
    public JavaCollectionResponse<?> purchase(
            @Valid @RequestBody ReportPurchaseOrderRequest reportPurchaseOrderRequest) {
        return reportPurchaseOrderService.reportPurchaseOrder(reportPurchaseOrderRequest);
    }

    @GetMapping("/filter/{vendorName}")
    public JavaCollectionResponse<?> filter(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "100", required = false) int pageSize,
            @PathVariable("vendorName") String vendorName) {
        return reportPurchaseOrderService.filter(pageNumber, pageSize, vendorName);
    }

}
