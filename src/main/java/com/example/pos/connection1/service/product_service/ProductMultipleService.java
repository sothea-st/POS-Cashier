package com.example.pos.connection1.service.product_service;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.repository.ProductRepository;
import com.example.pos.connection1.service.product_service.dto.ProductMultiple;
import com.example.pos.connection1.service.product_service.dto.ProductMultipleRequest;

import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMultipleService {
     private final ProductRepository productRepository;

     public Map<String,Object> addMultipleProduct(ProductMultiple lists) {
          List<Product> products = new ArrayList<>();
          Map<String ,Object> map = new HashedMap<>();

          log.info("length before : " + lists.getLists().size());

          for (ProductMultipleRequest p : lists.getLists()) {
               if (p.getBarcode() != null &&
                         p.getVendorId() != null &&
                         p.getCatId() != null &&
                         p.getProductName() != null) {
                         
                    if (productRepository.existsByBarcode(p.getBarcode())) {
                         map.put("code", 409);
                         map.put("barcode", p.getBarcode());
                         return map;
                    }

                    Product product = new Product();
                    product.setBarcode(p.getBarcode());
                    product.setVendorId(p.getVendorId());
                    product.setCatId(p.getCatId());
                    product.setProNameEn(p.getProductName());
                    product.setProNameKh(p.getProductNameKh());
                    product.setCost(p.getCost());
                    product.setPrice(p.getPrice());
                    product.setMargin(p.getMargin());
                    product.setAttributeId(p.getAttributeId());
                    product.setChoices(p.getChoiceValue());
                    product.setUomId(p.getUomId());
                    product.setProductActive(p.getStatus());
                    product.setCountryId(p.getCountryId());
                    product.setTaxId(p.getTaxId());
                    product.setProImageName(p.getPhoto());

                    product.setCreateBy(p.getCreateBy());
                    product.setProductStatus(null);
                    product.setDiscount(BigDecimal.valueOf(0));
                    product.setNote(null);

                    products.add(product);
               } else {
                    break;
               }
          }
          productRepository.saveAll(products);
          map.put("code", 200);
          return map;
     }
}
