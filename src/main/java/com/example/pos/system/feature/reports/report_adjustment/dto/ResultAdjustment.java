package com.example.pos.system.feature.reports.report_adjustment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResultAdjustment {
   private AdjustmentData department;
   private AdjustmentData product;
   private List<AdjustmentDataDetail> details;
}
