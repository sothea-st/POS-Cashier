package com.example.pos.system.feature.brand.dto;

import java.util.Date;
import lombok.Builder;

@Builder
public record BrandResponse(
    Integer id,
    String brandNameEn,
    String brandNameKh,
    Integer createBy,
    Date createDate,
    Boolean status,
    Boolean isDeleted

) {

}
