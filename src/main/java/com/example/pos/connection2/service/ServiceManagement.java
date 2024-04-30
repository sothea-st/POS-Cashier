package com.example.pos.connection2.service;

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

import java.util.*;

@Service
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
               // pro.setWeight(p.getWeight());
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
