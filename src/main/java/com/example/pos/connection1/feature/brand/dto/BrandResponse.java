package com.example.pos.connection1.feature.brand.dto;

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
