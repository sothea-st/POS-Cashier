package com.example.pos.system.feature.reports.report_receive.report_return;

import com.example.pos.system.constant.JavaResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface ReportReturnService {

    JavaCollectionResponse<?> reportReturn(String dateFrom , String dateTo,Integer pageSize , Integer pageNumber);
    JavaCollectionResponse<?> search(String dateFrom , String dateTo,String search);

}
