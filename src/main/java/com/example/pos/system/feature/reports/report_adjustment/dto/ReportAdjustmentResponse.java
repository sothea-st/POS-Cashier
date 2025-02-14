package com.example.pos.system.feature.reports.report_adjustment.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReportAdjustmentResponse {
   private   Integer divisionId;
   private   String divisionName;
   private   ResultAdjustment result;
}
