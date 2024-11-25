package com.example.pos.connection2.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.feature.product.productV1.ProductService;
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequest;
import com.example.pos.connection2.entity.ProductByCategory;
import com.example.pos.connection2.repository.ProdcutByCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceManagement {
     @Autowired
     private ProdcutByCategoryRepository repo;
     @Autowired
     private ProductRepository repoD1;
     @Autowired
     private FileStoreRepository fileStore;

     private final ProductService productService;

     public List<ProductByCategory> getProduct() {

          for (ProductByCategory p : repo.getProduct()) {

               if (p.getStatus().equals("ENABLE") && p.getBarcode().length() == 13) {
                    String _weight = null;

                    if (p.getChoiceOptions() != null) {
                         // Your JSON string
                         String jsonString = p.getChoiceOptions();

                         // Convert the string to a JSONArray
                         JSONArray jsonArray = new JSONArray(jsonString);

                         // Iterate over each JSONObject in the JSONArray
                         for (int m = 0; m < jsonArray.length(); m++) {
                              JSONObject jsonObject = jsonArray.getJSONObject(m);

                              // Get values from each JSONObject
                              String name = jsonObject.getString("name");
                              String title = jsonObject.getString("title");

                              // Extract options JSONArray
                              JSONArray optionsArray = jsonObject.getJSONArray("options");

                              // Get the first option
                              JSONObject optionsObject = optionsArray.getJSONObject(0);
                              String option = optionsObject.getString("option");

                              _weight = option;
                         }
                    }

                    ProductRequest productRequest = ProductRequest.builder()
                              .subCatId(44)
                              .proNameEn(p.getName())
                              .proNameKh(p.getNameKh())
                              .cost(p.getCost())
                              .price(p.getPrice())
                              .margin("10%")
                              .brandId(7)
                              .barcode(p.getBarcode())
                              .createBy(1)
                              .taxId(3)
                              .vendorId(7)
                              .uomId(8)
                              .attributeId(7)
                              .productActiveId(1)
                              .countryId(11)
                              .choices(_weight)
                              .proImageName(p.getImage())
                              .build();

                    productService.create(productRequest);
               }
          }

          return repo.getProduct();
     }

}
