package com.example.pos.connection1.feature.reports.report_purchase_order;

import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface ReportPurchaseOrderService {
     // Method signature to retrieve purchase order reports
     JavaCollectionResponse<?> reportPurchaseOrder(ReportPurchaseOrderRequest reportPurchaseOrderRequest);


     JavaCollectionResponse<?> filter(int pageNumber ,int pageSize , String vendorName);
}
