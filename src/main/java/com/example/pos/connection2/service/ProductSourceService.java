// package com.example.pos.connection2.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.pos.connection1.entity.Product;
// import com.example.pos.connection1.repository.ProductRepository;
// import com.example.pos.connection2.projections.ProductSourceProjection;
// import com.example.pos.connection2.repository.ProductSourceRepository;

// import jakarta.transaction.Transactional;

// @Service
// public class ProductSourceService {
//      @Autowired
//      private ProductSourceRepository repo;

//      @Autowired
//      private ProductRepository proRepo;

//      @Transactional
//     public void synchronizeProducts() {
//           for( ProductSourceProjection p : repo.getProductSource() ) {
            
//                Product pro = new Product();
//                pro.setCatId(p.getCategory_id());
//                // pro.setUnitTypeId(p.getUnitTypeId());
//                pro.setProNameKh(p.getName_kh());
//                pro.setProNameEn(p.getName());
//                pro.setCost(p.getCost());
//                pro.setPrice(p.getPrice());
//                // pro.setCostKhr(p.getCostKhr());
//                // pro.setPriceKhr(p.getPriceKhr());
//                // pro.setNote(p.getNote());
//                pro.setTaxId(3);
           
//                pro.setWeight("");
//                pro.setBarcode(p.getBarcode());
//                pro.setDiscount(p.getDiscount());
//                pro.setBrandId(0);
//                pro.setProImageName(p.getImage());
               
//                proRepo.save(pro);
//           }
//     }
// }
