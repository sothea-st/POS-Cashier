package com.example.pos.system.feature.reports.report_adjustment.dto;

import com.example.pos.system.domain.adjustment.AdjustmentDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CombineData {
    private Integer primaryKeyCatId;
    private Integer parentId;
    private String code;
    private String catNameEn;
    private AdjustmentDetail adjustmentDetail;
}
