package com.example.pos.connection2.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.ProductRepository;
import com.example.pos.connection2.entity.ProductByCategory;
import com.example.pos.connection2.models.ProductModelD2;
import com.example.pos.connection2.repository.ProdcutByCategoryRepository;
 
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Service
@Slf4j
public class ServiceManagement {
     @Autowired
     private ProdcutByCategoryRepository repo;
     @Autowired
     private ProductRepository repoD1;
     @Autowired
     private FileStoreRepository fileStore;

     public List<ProductByCategory> getProduct() {

          for (ProductByCategory p : repo.getProduct()) {
               Product pro = new Product();
               pro.setCatId(1);
               // pro.setUnitTypeId(p.getUnitTypeId());
               pro.setProNameKh(p.getNameKh());
               pro.setProNameEn(p.getName());
               pro.setCost(p.getCost());
               pro.setPrice(p.getPrice());
               // pro.setCostKhr(p.getCostKhr());
               // pro.setPriceKhr(p.getPriceKhr());
               // pro.setNote(p.getNote());
               pro.setTaxId(3);
               pro.setCreateBy(1);

               String _weight=null;

               if (p.getChoiceOptions()  != null) {
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


               log.info("weight data : " + _weight);

               pro.setWeight(_weight);
               pro.setBarcode(p.getBarcode());
               pro.setDiscount(p.getDiscount());
               pro.setBrandId(0);
               // pro.setDiscountPercentag(p.getDiscountPercentag().isEmpty() ? "0" :
               // p.getDiscountPercentag());
               pro.setProImageName(p.getImage());
               pro.setProductStatus(p.getStatus()); // for detail product in or out stock
               repoD1.save(pro);
          }

          return repo.getProduct();
     }

}
