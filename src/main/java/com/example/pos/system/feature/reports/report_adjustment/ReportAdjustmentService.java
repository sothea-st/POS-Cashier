package com.example.pos.system.feature.reports.report_adjustment;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface ReportAdjustmentService {
    JavaCollectionResponse<?> getReport(String dateFrom ,String dateTo);

}
