package com.example.pos.system.feature.reports.report_receive;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface ReportReceiveService {
    JavaCollectionResponse<?> reportReceive(Integer pageNumber , Integer pageSize , String dateFrom , String dateTo , Integer receiveBy);


    JavaCollectionResponse<?> search(Integer pageNumber , Integer pageSize , String dateFrom , String dateTo , Integer receiveBy , String value);
}
