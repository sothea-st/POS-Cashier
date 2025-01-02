package com.example.pos.system.feature.reports.report_return;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returnReport")
@RequiredArgsConstructor
public class ReportReturnController {
    // inject bean service
    private  final  ReportReturnService reportReturnService;


    @GetMapping
    public JavaCollectionResponse<?> getReturnReport(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo,
            @RequestParam(name = "pageSize",required = false) Integer pageSize,
            @RequestParam(name = "pageNumber",required = false) Integer pageNumber
    ){
        return reportReturnService.reportReturn(dateFrom,dateTo,pageSize,pageNumber);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo,
            @RequestParam(name = "search",required = false) String search
    ){
        return reportReturnService.search(dateFrom,dateTo,search);
    }

}
