package com.example.pos.connection1.feature.product.productV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.pos.connection1.repository.ImportDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.constant.JavaConstant;
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
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequest;
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequestVendorOrSubCateId;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponse;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponseByFilter;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponseReadById;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponseReadByProductId;
import com.example.pos.connection1.feature.status.StatusRepository;
import com.example.pos.connection1.feature.tax.TaxRepository;
import com.example.pos.connection1.feature.uom.UomRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;
import com.example.pos.connection1.mapper.ProductMapper;
import com.example.pos.connection1.repository.CategoryRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
    // **************************** group bean ************************

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final TaxRepository taxRepository;
    private final VendorRepository vendorRepository;
    private final UomRepository uomRepository;
    private final AttributeRepository attributeRepository;
    private final StatusRepository statusRepository;
    private final CountryRepository countryRepository;
    private final ImportDetailRepository repoImp;
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
    public JavaCollectionResponse<?> searchByStatus(Integer pageNumber, Integer pageSize, String value , String status) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Product> products = null;
        boolean isCheck = JavaConstant.onlyDigits(value, value.length());
        Status status1 = statusRepository.findByStatusName(status)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Status not found with statusName : " + status));

        if (isCheck) {
            products = productRepository
                    .findByBarcodeIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(pageRequest, value ,status1);
        } else {
            products = productRepository
                    .findByProNameEnIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(pageRequest, value,status1);
        }

        List<ProductResponse> data = products.getContent().stream()
                .map(productMapper::mapToProductResponse)
                .toList();
        return JavaCollectionResponse.builder()
                .count(products.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public JavaCollectionResponse<?> listByStatus(Integer pageNumber, Integer pageSize, String status) {
        List<ProductResponse> data = null;


        if (pageNumber == null && pageSize == null) {
            Status status1 = statusRepository.findByStatusName(status).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found with StatusName : " + status)
            );
            data = productRepository.findByStatusTrueAndIsDeletedFalseAndProductActive(status1).stream()
                    .map(productMapper::mapToProductResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

            Status status1 = statusRepository.findByStatusName(status).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found with StatusName : " + status)
            );

            Page<Product> pages = productRepository.findByStatusTrueAndIsDeletedFalseAndProductActive(pageRequest, status1);
            data = pages.getContent().stream()
                    .map(productMapper::mapToProductResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(data)
                    .build();
        }
    }

    /**
     * Retrieves products based on the vendor ID and optionally a subcategory
     * ID. If subcategory ID is provided, filters products by both vendor ID and
     * subcategory ID. If subcategory ID is null, filters products only by
     * vendor ID.
     *
     * @param p ProductRequestVendorOrSubCateId object containing vendor ID and
     *          optional subcategory ID.
     * @return A JavaCollectionResponse containing filtered products and count.
     */
    @Override
    public JavaCollectionResponse<?> findByVendorIdOrSubCategoryId(ProductRequestVendorOrSubCateId p) {
        List<Product> products = new ArrayList<>();
        if (p.subCatId() != null) {
            products = productRepository.findByVendorIdAndSubCategoryIdAndStatusTrueAndIsDeletedFalse(
                    p.vendorId(), p.subCatId());
        } else {
            products = productRepository
                    .findByVendorIdAndStatusTrueAndIsDeletedFalse(p.vendorId());
        }

        List<ProductResponseByFilter> data = products.stream()
                .map(product -> {
                    Integer qty = repoImp.sumQtyByProId(product.getId());
                    if (qty == null) qty = 0;
                    return ProductResponseByFilter.builder()
                            .id(product.getId())
                            .barcode(product.getBarcode())
                            .proNameEn(product.getProNameEn())
                            .division(product.getSubCategory().getCatNameEn())
                            .availableQty(qty)
                            .qty(1)
                            .cost(product.getCost())
                            .amount(product.getCost())
                            .build();
                })
                .toList(); // Collect stream into a list

        return JavaCollectionResponse.builder()
                .count(products.size())
                .data(data)
                .build();
    }

    /**
     * search a collection of products based on pagination parameters.
     *
     * @param pageNumber The page number of the results to retrieve.
     * @param pageSize   The number of products per page.
     * @return A collection response containing products for the specified page.
     */
    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String value) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Product> products = null;
        boolean isCheck = JavaConstant.onlyDigits(value, value.length());
        if (isCheck) {
            products = productRepository
                    .findByBarcodeIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(pageRequest, value);
        } else {
            products = productRepository
                    .findByProNameEnIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(pageRequest, value);
        }

        List<ProductResponse> data = products.getContent().stream()
                .map(productMapper::mapToProductResponse)
                .toList();
        return JavaCollectionResponse.builder()
                .count(products.getTotalElements())
                .data(data)
                .build();
    }

    /**
     * Updates an existing product identified by its unique identifier.
     *
     * @param id             The unique identifier of the product to update.
     * @param productRequest The updated details of the product.
     * @return The response containing details of the updated product.
     */
    @Override
    public ProductResponse updateProductById(int id, ProductRequest productRequest) {
        Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));

        // validate subCategory
        Category subCategory = subCategory(productRequest.subCatId());

        // validate brand
        Brand brand = brand(productRequest.brandId());

        // validate tax
        TaxProduct tax = taxProduct(productRequest.taxId());

        // validate vendor
        Vendor vendor = vendor(productRequest.vendorId());

        // validate uom
        Attribute attribute = attribute(productRequest.attributeId());

        // validate uom
        Uom uom = uom(productRequest.uomId());

        // validate satatus
        Status status = status(productRequest.productActiveId());

        // validate country
        Country country = country(productRequest.countryId());

        // validate barcode
        if (!product.getBarcode().equals(productRequest.barcode())) {
            checkBarcodeExists(productRequest.barcode());
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

    /**
     * Deletes a product identified by its unique identifier.
     *
     * @param id The unique identifier of the product to delete.
     */
    @Override
    public void deleteById(int id) {
        Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));
        product.setStatus(false);
        product.setIsDeleted(true);
        productRepository.save(product);
    }

    /**
     * Retrieves a collection of products based on pagination parameters.
     *
     * @param pageNumber The page number of the results to retrieve.
     * @param pageSize   The number of products per page.
     * @return A collection response containing products for the specified page.
     */
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {
        List<ProductResponse> data = null;
        if (pageNumber == null && pageSize == null) {
            data = productRepository.findByStatusTrueAndIsDeletedFalseOrderByIdDesc().stream()
                    .map(this::mapToProductResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Product> pages = productRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
            data = pages.getContent().stream()
                    .map(this::mapToProductResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(data)
                    .build();
        }
    }

    private ProductResponse mapToProductResponse(Product p){
        Integer qty = repoImp.sumQtyByProId(p.getId());
        if (qty == null) qty = 0;
        return ProductResponse.builder()
                .id(p.getId())
                .subCatNameEn(p.getSubCategory().getCatNameEn())
                .proNameKh(p.getProNameKh())
                .proNameEn(p.getProNameEn())
                .cost(p.getCost())
                .price(p.getPrice())
                .margin(p.getMargin())
                .brandNameEn(p.getBrand().getBrandNameEn())
                .barcode(p.getBarcode())
                .createBy(p.getCreateBy())
                .taxName(p.getTaxProduct().getTaxName())
                .vendorName(p.getVendor().getVendorName())
                .uomNameEn(p.getUom().getNameEn())
                .attrNameEn(p.getAttribute().getAttrNameEn())
                .statusName(p.getProductActive().getStatusName())
                .countryImageName(p.getCountry().getCountryName())
                .choices(p.getChoices())
                .proImageName(p.getProImageName())
                .qty(qty)
                .itemCode(p.getItemCode())
                .vendorCode(p.getVendor().getVendorCode())
                .build();
    }

    /**
     * Retrieves product details by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return The response containing details of the product found by ID.
     */
    @Override
    public ProductResponseReadById readProductById(int id) {
        Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productIdNotFound + id));
        return productMapper.mapToProductResponseReadById(product);
    }


    //Get List Detail Product By Id When imported
    @Override
    public List<ProductResponseReadByProductId> readProductByProductId(int id) {
        List<ProductResponseReadByProductId> product = productRepository.geProductByIdProduct(id);
        return product;
    }

    /**
     * Creates a new product based on the provided product request.
     *
     * @param productRequest The details of the product to create.
     * @return The response containing details of the created product.
     */
    @Override
    public ProductResponse create(ProductRequest productRequest) {

        // validate subCategory
        Category subCategory = subCategory(productRequest.subCatId());

        // validate brand
        Brand brand = brand(productRequest.brandId());

        // validate tax
        TaxProduct tax = taxProduct(productRequest.taxId());

        // validate vendor
        Vendor vendor = vendor(productRequest.vendorId());

        // validate uom
        Attribute attribute = attribute(productRequest.attributeId());

        // validate uom
        Uom uom = uom(productRequest.uomId());

        // validate satatus
        Status status = status(productRequest.productActiveId());

        // validate country
        Country country = country(productRequest.countryId());

        // validate barcode
        checkBarcodeExists(productRequest.barcode());

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
        product.setItemCode(generateItemCode(productRepository.count()));
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    private String generateItemCode(long count) {
        count++;
        return String.format("%07d", count);
    }

    private Category subCategory(Integer subCatId) {
        return categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(subCatId, "subcategory")
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, subCategoryIdNotFound + subCatId));
    }

    private Brand brand(Integer brandId) {
        return brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(brandId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, brandIdNotFound + brandId));
    }

    private TaxProduct taxProduct(Integer taxId) {
        return taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(taxId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, taxIdNotFound + taxId));
    }

    private Vendor vendor(Integer vendorId) {
        return vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(vendorId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, vendorIdNotFound + vendorId));
    }

    private Attribute attribute(Integer attributeId) {
        return attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(attributeId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, attributeIdNotFound + attributeId));
    }

    private Uom uom(Integer uomId) {
        return uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(uomId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, uomIdNotFound + uomId));
    }

    private Status status(Integer statusId) {
        return statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(statusId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, statusIdNotFound + statusId));
    }

    private Country country(Integer countryId) {
        return countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(countryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, countryIdNotFound + countryId));
    }

    private void checkBarcodeExists(String barcode) {
        if (productRepository.existsByBarcodeAndStatusIsTrueAndIsDeletedIsFalse(barcode)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, barcodeAlreadyExist + barcode);
        }
    }

}
