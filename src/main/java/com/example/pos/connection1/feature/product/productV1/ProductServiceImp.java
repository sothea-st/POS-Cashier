package com.example.pos.connection1.feature.product.productV1;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.Attribute;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.entity.Country;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.Status;
import com.example.pos.connection1.entity.Uom;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.entity.Vendor;
import com.example.pos.connection1.entity.sourceData.Brand;
import com.example.pos.connection1.entity.sourceData.TaxProduct;
import com.example.pos.connection1.feature.attribute.AttributeRepository;
import com.example.pos.connection1.feature.country.CountryRepository;
import com.example.pos.connection1.feature.product.ProductRepository;
// import com.example.pos.connection1.feature.product.dto.ProductResponse;
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequest;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponse;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponseReadById;
import com.example.pos.connection1.feature.status.StatusRepository;
import com.example.pos.connection1.feature.uom.UomRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;
import com.example.pos.connection1.mapper.ProductMapper;
import com.example.pos.connection1.repository.CategoryRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.repository.sourceDataRepository.BrandRepository;
import com.example.pos.connection1.repository.sourceDataRepository.TaxProductRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
     // **************************** group bean ************************
     private final ProductRepository productRepository;
     private final CategoryRepository categoryRepository;
     private final ProductMapper productMapper;
     private final BrandRepository brandRepository;
     private final TaxProductRepository taxProductRepository;
     private final VendorRepository vendorRepository;
     private final UomRepository uomRepository;
     private final AttributeRepository attributeRepository;
     private final StatusRepository statusRepository;
     private final CountryRepository countryRepository;
     // **************************** end *******************************

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
     private String productIdNotFound = "Product not found with id: ";

     // **************************** end *******************************

     @Override
     public ProductResponse updateProductById(int id, ProductRequest productRequest) {
          Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));

          // validate subCategory
          Category subCategory = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(
                    productRequest.subCatId(), "subcategory")
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              subCategoryIdNotFound + productRequest.subCatId()));

          // validate brand
          Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.brandId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              brandIdNotFound + productRequest.brandId()));

          // validate tax
          TaxProduct tax = taxProductRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.taxId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              taxIdNotFound + productRequest.taxId()));

          // validate vendor
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.vendorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              vendorIdNotFound + productRequest.vendorId()));

          // validate uom
          Attribute attribute = attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.attributeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              attributeIdNotFound + productRequest.attributeId()));

          // validate uom
          Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.uomId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              uomIdNotFound + productRequest.uomId()));

          // validate satatus
          Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.productActiveId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              statusIdNotFound + productRequest.productActiveId()));

          // validate country
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.countryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              countryIdNotFound + productRequest.countryId()));

          // validate barcode
          if (!product.getBarcode().equals(productRequest.barcode())) {
               if (productRepository.existsByBarcode(productRequest.barcode())) {
                    throw new ResponseStatusException(
                              HttpStatus.CONFLICT, barcodeAlreadyExist + productRequest.barcode());
               }
          }

          String fileName = productRequest.proImageName() == null ? JavaConstant.defaultNameImage
                    : productRequest.proImageName();

          if (!product.getProImageName().equals(productRequest.proImageName())) {
               product.setProImageName(fileName);
          }

          product.setProNameKh(productRequest.proNameKh());
          product.setProNameEn(productRequest.proNameEn());
          product.setCost(productRequest.cost());
          product.setPrice(productRequest.price());
          product.setMargin(productRequest.margin());
          product.setBarcode(productRequest.barcode());
          product.setChoices(productRequest.choices());
          product.setCreateBy(productRequest.createBy());
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
          product.setCatId(productRequest.subCatId());
          productRepository.save(product);
          return productMapper.mapToProductResponse(product);
     }

     /*
      * delete product by id
      * 
      * @param id
      */
     @Override
     public void deleteById(int id) {
          Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));
          product.setStatus(false);
          product.setIsDeleted(true);
          productRepository.save(product);
     }

     /*
      * read product by id
      * 
      * @param pageNumber , pageSize
      */

     @Override
     public JavaCollectionResponse<?> read(int pageNumber, int pageSize) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          Page<Product> pages = productRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
          List<ProductResponse> data = pages.getContent().stream()
                    .map(productMapper::mapToProductResponse)
                    .toList();
          return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(data)
                    .build();
     }

     /*
      * read product by id
      * 
      * @param id
      */
     @Override
     public ProductResponseReadById readProductById(int id) {
          Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));
          return productMapper.mapToProductResponseReadById(product);
     }

     /*
      * create product
      * 
      * @param productRequest
      */
     @Override
     public ProductResponse create(ProductRequest productRequest) {
          // validate subCategory
          Category subCategory = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(
                    productRequest.subCatId(), "subcategory")
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              subCategoryIdNotFound + productRequest.subCatId()));

          // validate brand
          Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.brandId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              brandIdNotFound + productRequest.brandId()));

          // validate tax
          TaxProduct tax = taxProductRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.taxId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              taxIdNotFound + productRequest.taxId()));

          // validate vendor
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.vendorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              vendorIdNotFound + productRequest.vendorId()));

          // validate uom
          Attribute attribute = attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.attributeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              attributeIdNotFound + productRequest.attributeId()));

          // validate uom
          Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.uomId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              uomIdNotFound + productRequest.uomId()));

          // validate satatus
          Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.productActiveId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              statusIdNotFound + productRequest.productActiveId()));

          // validate country
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(productRequest.countryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              countryIdNotFound + productRequest.countryId()));

          // validate barcode
          if (productRepository.existsByBarcode(productRequest.barcode())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT, barcodeAlreadyExist + productRequest.barcode());
          }

          String fileName = productRequest.proImageName() == null ? JavaConstant.defaultNameImage
                    : productRequest.proImageName();

          Product product = productMapper.mapToProduct(productRequest);
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
          product.setProImageName(fileName);
          product.setCatId(productRequest.subCatId());
          productRepository.save(product);
          return productMapper.mapToProductResponse(product);
     }
}
