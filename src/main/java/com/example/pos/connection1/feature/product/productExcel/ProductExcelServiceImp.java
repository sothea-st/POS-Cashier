package com.example.pos.connection1.feature.product.productExcel;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.connection1.entity.Attribute;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.entity.Country;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.Status;
import com.example.pos.connection1.entity.Uom;
import com.example.pos.connection1.entity.Vendor;
import com.example.pos.connection1.entity.sourceData.Brand;
import com.example.pos.connection1.entity.sourceData.TaxProduct;
import com.example.pos.connection1.feature.attribute.AttributeRepository;
import com.example.pos.connection1.feature.brand.BrandRepository;
import com.example.pos.connection1.feature.country.CountryRepository;
import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.feature.product.productExcel.dto.ProductExcelDetail;
import com.example.pos.connection1.feature.product.productExcel.dto.ProductMultipleInsert;
import com.example.pos.connection1.feature.status.StatusRepository;
import com.example.pos.connection1.feature.tax.TaxRepository;
import com.example.pos.connection1.feature.settings.uom.UomRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;
import com.example.pos.connection1.repository.CategoryRepository;
// import com.example.pos.connection1.repository.sourceDataRepository.BrandRepository;
// import com.example.pos.connection1.repository.sourceDataRepository.TaxProductRepository;
import java.math.*;
import java.util.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductExcelServiceImp implements ProductExcelService {
     // **************************** group bean ************************
     private final ProductRepository productRepository;
     private final CategoryRepository categoryRepository;
     private final BrandRepository brandRepository;
     private final TaxRepository taxRepository;
     private final VendorRepository vendorRepository;
     private final UomRepository uomRepository;
     private final AttributeRepository attributeRepository;
     private final StatusRepository statusRepository;
     private final CountryRepository countryRepository;
     // **************************** end ***********************************

     // **************************** group variable ************************
     private String subCategoryIdNotFound = "Sub category not found with id: ";
     private String brandIdNotFound = "Brand not found with id: ";
     private String taxIdNotFound = "Tax not found with id: ";
     private String vendorIdNotFound = "Vendor not found with id: ";
     private String uomIdNotFound = "Uom not found with id: ";
     private String attributeIdNotFound = "Attribute not found with id: ";
     private String statusIdNotFound = "Status not found with id: ";
     private String countryIdNotFound = "Country not found with id: ";
     private String barcodeAlreadyExist = "Barcode already exist with: ";
     // **************************** end *******************************
   
     @Override
     public void create(ProductMultipleInsert productMultipleInsert) {
          List<ProductExcelDetail> lists = productMultipleInsert.getLists();
          List<Product> products = new ArrayList<>();
          long count = productRepository.count();
          count--;


          for (ProductExcelDetail productExcelDetail : lists) {

               if (productExcelDetail.getBarcode() != null &&
                         productExcelDetail.getVendorId() != null &&
                         productExcelDetail.getSubCatId() != null &&
                         productExcelDetail.getProductName() != null) {
                    // validate barcode
                    if (productRepository.existsByBarcodeAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getBarcode())) {
                         System.out.println("ddddddddddddddddd");
                         throw new ResponseStatusException(
                                 HttpStatus.CONFLICT, barcodeAlreadyExist + productExcelDetail.getBarcode());
                    }
                    if( productRepository.existsByProNameEnAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getProductName()) ) {
                         throw new ResponseStatusException(HttpStatus.CONFLICT , "Product Name : "+productExcelDetail.getProductName()+" already exists ");
                    }
                    if( productRepository.existsByProNameKhAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getProductNameKh()) ) {
                         throw new ResponseStatusException(HttpStatus.CONFLICT , "Product Name Kh: "+productExcelDetail.getProductName()+" already exists ");
                    }

                    // validate subCategory
                    Category subCategory = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(
                              productExcelDetail.getSubCatId(), "subcategory")
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        subCategoryIdNotFound + productExcelDetail.getSubCatId()));

                    // validate brand
                    Brand brand = brandRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getBrandId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        brandIdNotFound + productExcelDetail.getBrandId()));

                    // validate tax
                    TaxProduct tax = taxRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getTaxId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        taxIdNotFound + productExcelDetail.getTaxId()));

                    // validate vendor
                    Vendor vendor = vendorRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getVendorId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        vendorIdNotFound + productExcelDetail.getVendorId()));

                    // validate uom
                    Attribute attribute = attributeRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getAttributeId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        attributeIdNotFound + productExcelDetail.getAttributeId()));

                    // validate uom
                    Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getUomId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        uomIdNotFound + productExcelDetail.getUomId()));

                    // validate satatus
                    Status status = statusRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getStatusId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        statusIdNotFound + productExcelDetail.getStatusId()));

                    // validate country
                    Country country = countryRepository
                              .findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getCountryId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        countryIdNotFound + productExcelDetail.getCountryId()));


                    count++;
                    Product product = new Product();

                    product.setSubCategory(subCategory);
                    product.setBrand(brand);
                    product.setTaxProduct(tax);
                    product.setVendor(vendor);
                    product.setUom(uom);
                    product.setAttribute(attribute);
                    product.setProductActive(status);
                    product.setCountry(country);
                    product.setStatus(true);
                    product.setIsDeleted(false);
                    product.setDiscount(BigDecimal.valueOf(0));
                    product.setProImageName(productExcelDetail.getPhoto());
                    product.setCatId(productExcelDetail.getSubCatId());
                    product.setProNameEn(productExcelDetail.getProductName());
                    product.setProNameKh(productExcelDetail.getProductNameKh());
                    product.setCost(productExcelDetail.getCost());
                    product.setPrice(productExcelDetail.getPrice());
                    product.setMargin(productExcelDetail.getMargin());
                    product.setChoices(productExcelDetail.getChoiceValue());
                    product.setCreateBy(productExcelDetail.getCreateBy());
                    product.setBarcode(productExcelDetail.getBarcode());
                    product.setItemCode(generateItemCode(count));
                    products.add(product);
               } else {
                    break;
               }
          }
          productRepository.saveAll(products);
     }

     private String generateItemCode(long count) {
          count++;
          return String.format("%07d", count);
     }
}
