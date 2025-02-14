package com.example.pos.system.feature.reports.report_adjustment;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/repostAdjustments")
public class ReportAdjustmentController {
    // inject bean service
    private final ReportAdjustmentService reportAdjustmentService;

    @GetMapping
    public JavaCollectionResponse<?> getReport(
            @RequestParam String dateFrom,
            @RequestParam String dateTo
    ){
        return reportAdjustmentService.getReport(dateFrom,dateTo);
    }

}
