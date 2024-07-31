package com.example.pos.connection1.feature.reports.report_receive;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reportReceive")
@RequiredArgsConstructor
public class ReportReceiveController {
    private final ReportReceiveService reportReceiveService;


    @GetMapping
    public JavaCollectionResponse<?> getReport(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "dateFrom" , required = false) String dateFrom,
            @RequestParam(name = "dateTo",required = false) String dateTo,
            @RequestParam(name = "receiveId", required = false) Integer receiveId
    ) {
        System.out.println("dddddddddd = " + pageNumber);
        return reportReceiveService.reportReceive(pageNumber, pageSize, dateFrom, dateTo, receiveId);
    }

}
