package com.example.pos.system.feature.attribute.product.productExcel;

import com.example.pos.system.domain.settings.*;
import com.example.pos.system.feature.attribute.product.productExcel.dto.ProductMultipleInsert;
import com.example.pos.system.feature.settings.range.RangeRepository;
import com.example.pos.system.feature.settings.slot.SlotRepository;
import com.example.pos.system.feature.settings.warehouse.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.system.domain.sourceData.Brand;
import com.example.pos.system.domain.sourceData.TaxProduct;
import com.example.pos.system.feature.attribute.AttributeRepository;
import com.example.pos.system.feature.brand.BrandRepository;
import com.example.pos.system.feature.country.CountryRepository;
import com.example.pos.system.feature.attribute.product.ProductRepository;
import com.example.pos.system.feature.attribute.product.productExcel.dto.ProductExcelDetail;
import com.example.pos.system.feature.status.StatusRepository;
import com.example.pos.system.feature.tax.TaxRepository;
import com.example.pos.system.feature.settings.uom.UomRepository;
import com.example.pos.system.feature.vendor.VendorRepository;
import com.example.pos.system.layer.repository.CategoryRepository;
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
    private final WarehouseRepository warehouseRepository;
    private final RangeRepository rangeRepository;
    private final SlotRepository slotRepository;
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



                if( productExcelDetail.getBarcode().length() != 13 ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "barcode must be 13 digits at product Name : " + productExcelDetail.getProductName() +
                            " with barcode : " + productExcelDetail.getBarcode());
                }

                if( productExcelDetail.getVendorId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Vendor Name is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getBrandId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Brand is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getSubCatId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sub category is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getAttributeId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Attribute is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getUomId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Uom is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getStatusId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getCountryId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Country is required at product Name : " + productExcelDetail.getProductName());
                }

                if( productExcelDetail.getTaxId() == null ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Tax is required at product Name : " + productExcelDetail.getProductName());
                }


                String proNameKh = productExcelDetail.getProductNameKh();
                if (proNameKh != null && proNameKh.isEmpty()) {
                    proNameKh = null;
                }

                // validate barcode
                if (productRepository.existsByBarcodeAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getBarcode())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT, barcodeAlreadyExist + productExcelDetail.getBarcode());
                }
                if (productRepository.existsByProNameEnAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getProductName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Product Name : " + productExcelDetail.getProductName() + " already exists ");
                }
                if (productRepository.existsByProNameKhAndStatusIsTrueAndIsDeletedIsFalse(productExcelDetail.getProductNameKh())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Product Name Kh: " + productExcelDetail.getProductNameKh() + " already exists ");
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


                Warehouse warehouse = null;
                if (productExcelDetail.getWarehouseId() != null) {
                    warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getWarehouseId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found with id : " + productExcelDetail.getWarehouseId()));
                }

                Ranges range = null;
                if (productExcelDetail.getRangeId() != null) {
                    range = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getRangeId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Range not found with id : " + productExcelDetail.getRangeId()));
                }
                Slot slot = null;
                if (productExcelDetail.getSlotId() != null) {
                    slot = slotRepository.findByIdAndStatusTrueAndIsDeletedFalse(productExcelDetail.getSlotId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found with id : " + productExcelDetail.getSlotId()));
                }


                count++;
                Product product = new Product();

                product.setWarehouse(warehouse);
                product.setRange(range);
                product.setSlot(slot);

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
                product.setProNameKh(proNameKh);
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
