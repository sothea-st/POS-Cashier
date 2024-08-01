package com.example.pos.connection1.feature.reports.report_purchase_order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
            @Valid @RequestParam(name = "dateTo") String dateTo,
            @RequestParam(name = "requestId", required = false) Integer requestId,
            @RequestParam(name = "checkId", required = false) Integer checkId,
            @RequestParam(name = "approvedId", required = false) Integer approvedId,
            @RequestParam(name = "rejectId", required = false) Integer rejectId,
            @RequestParam(name = "remark", required = false) String remark) {
        return reportPurchaseOrderService.getReportByRemark(pageNumber, pageSize, dateFrom, dateTo, requestId, checkId,
                approvedId, rejectId, remark);
    }

    @GetMapping("/search/{value}")
    public JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize,
            @Valid @RequestParam(name = "dateFrom") String dateFrom,
            @Valid @RequestParam(name = "dateTo") String dateTo,
            @RequestParam(name = "requestId", required = false) Integer requestId,
            @RequestParam(name = "checkId", required = false) Integer checkId,
            @RequestParam(name = "approvedId", required = false) Integer approvedId,
            @RequestParam(name = "rejectId", required = false) Integer rejectId,
            @RequestParam(name = "remark", required = false) String remark,
            @PathVariable(name = "value") String value
    ) {
        return reportPurchaseOrderService.search(pageNumber, pageSize, dateFrom, dateTo, requestId, checkId,
                approvedId, rejectId, remark, value);
    }


}
