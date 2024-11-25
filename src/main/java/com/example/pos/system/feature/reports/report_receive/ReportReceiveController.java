package com.example.pos.system.feature.reports.report_receive;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reportReceive")
@RequiredArgsConstructor
public class ReportReceiveController {
    private final ReportReceiveService reportReceiveService;
    @GetMapping("/search/{value}")
    public JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "dateFrom" , required = false) String dateFrom,
            @RequestParam(name = "dateTo",required = false) String dateTo,
            @RequestParam(name = "receiveId", required = false) Integer receiveId,
            @PathVariable(name = "value") String value
    ) {
        return reportReceiveService.search(pageNumber, pageSize, dateFrom, dateTo, receiveId,value);
    }

    @GetMapping
    public JavaCollectionResponse<?> getReport(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "dateFrom" , required = false) String dateFrom,
            @RequestParam(name = "dateTo",required = false) String dateTo,
            @RequestParam(name = "receiveId", required = false) Integer receiveId
    ) {

        return reportReceiveService.reportReceive(pageNumber, pageSize, dateFrom, dateTo, receiveId);
    }

}
