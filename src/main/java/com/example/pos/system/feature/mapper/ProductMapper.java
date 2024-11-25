package com.example.pos.system.feature.mapper;

import com.example.pos.system.domain.Product;

import com.example.pos.system.feature.product.productV1.dto.ProductRequest;
import com.example.pos.system.feature.product.productV1.dto.ProductResponse;

import com.example.pos.system.feature.product.productV1.dto.ProductResponseReadById;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Maps fields from ProductRequest to Product
    Product mapToProduct(ProductRequest productRequest);

    default int defaultIfNull(Integer value) {
        return value != null ? value : 0;
    }
    // Maps fields from Product to ProductResponse
    @Mapping(source = "product.vendor.vendorName", target = "vendorName")
    @Mapping(source = "product.brand.brandNameEn", target = "brandNameEn")
    @Mapping(source = "product.attribute.attrNameEn", target = "attrNameEn")
    @Mapping(source = "product.uom.uomNameEn", target = "uomNameEn")
    @Mapping(source = "product.productActive.statusName", target = "statusName")
    @Mapping(source = "product.country.uuid", target = "countryImageName")
    @Mapping(source = "product.taxProduct.taxName", target = "taxName")
    @Mapping(source = "product.subCategory.catNameEn", target = "subCatNameEn")
    @Mapping(source = "product.importDetail.qtyOld", target = "qty" ,defaultValue = "0")
    @Mapping(source = "product.vendor.vendorCode", target = "vendorCode")
    ProductResponse mapToProductResponse(Product product);

    // Maps fields from Product to ProductResponseReadById for read operations
    @Mapping(source = "product.vendor.id", target = "vendorId")
    @Mapping(source = "product.brand.id", target = "brandId")
    @Mapping(source = "product.attribute.id", target = "attributeId")
    @Mapping(source = "product.uom.id", target = "uomId")
    @Mapping(source = "product.productActive.id", target = "productActiveId")
    @Mapping(source = "product.country.id", target = "countryId")
    @Mapping(source = "product.taxProduct.id", target = "taxId")
    @Mapping(source = "product.subCategory.id", target = "subCatId")
    @Mapping(source = "product.importDetail.qtyOld", target = "qty")
    ProductResponseReadById mapToProductResponseReadById(Product product);
 

}
