package com.example.pos.system.feature.product.productV1.dto;
import java.math.*; 
public record ProductResponseReadById(
     Integer id,
     Integer subCatId,
     String proNameKh,
     String proNameEn,
     BigDecimal cost,
     BigDecimal price,
     String margin,
     Integer brandId,
     String barcode,
     Integer createBy,
     Integer taxId,
     Integer vendorId,
     Integer uomId,
     Integer attributeId,
     Integer productActiveId,
     Integer countryId,
     String choices,
     String proImageName,
     Integer qty,
     Integer warehouseId,
     Integer rangeId,
     Integer slotId
) {
     
}
