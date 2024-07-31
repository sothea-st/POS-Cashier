package com.example.pos.connection1.feature.reports.report_receive;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface ReportReceiveService {
    JavaCollectionResponse<?> reportReceive(Integer pageNumber , Integer pageSize , String dateFrom , String dateTo , Integer receiveBy);
}
