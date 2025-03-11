package com.example.pos.system.feature.reports.report_product_stock_in;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface ReportProductStockInService {

    JavaCollectionResponse<?> read(Integer pageNumber,Integer pageSize,String dateFrom,String dateTo);

}
