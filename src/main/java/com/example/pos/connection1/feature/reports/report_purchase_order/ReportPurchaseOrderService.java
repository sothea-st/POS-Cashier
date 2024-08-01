package com.example.pos.connection1.feature.reports.report_purchase_order;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface ReportPurchaseOrderService {
     // Method signature to retrieve purchase order reports
     JavaCollectionResponse<?> reportPurchaseOrder(Integer pageNumber , Integer pageSize , String dateFrom  , String dateTo);

     JavaCollectionResponse<?> filter(int pageNumber ,int pageSize , String vendorName);



     JavaCollectionResponse<?> getReportByRemark(Integer pageNumber , Integer pageSize , String dateFrom  , String dateTo , Integer requestId , Integer checkId , Integer approvedId , Integer rejectId , String remark );


     JavaCollectionResponse<?> search(Integer pageNumber , Integer pageSize , String dateFrom  , String dateTo , Integer requestId , Integer checkId , Integer approvedId , Integer rejectId , String remark , String searchValue );

}
