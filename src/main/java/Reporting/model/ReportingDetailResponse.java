package Reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ReportingDetailResponse {
    private String purchaseOrderNo;
    private Integer transactionNo;
    private String transactionDate;
    private String orderDate;
    private String referenceNo;
    private String vendorName;
    private Integer totalQty;
    private Double totalCost;
    private String requestBy;
    private String requestDate;
    private String checkBy;
    private String checkDate;
    private String approvedBy;
    private String approvedDate;
    private String rejectBy;
    private String rejectDate;
    private String remark;
}
