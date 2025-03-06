package com.example.pos.system.feature.reports.report_stock.stock;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface StockService {
    JavaCollectionResponse<?> read(Integer pageNumber,Integer pageSize,Integer statusId);
}
