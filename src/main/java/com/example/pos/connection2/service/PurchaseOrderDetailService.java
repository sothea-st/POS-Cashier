// package com.example.pos.connection2.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.pos.connection1.repository.ProductRepository;
// import com.example.pos.connection2.entity.PurchaseOrderDetail;
// import com.example.pos.connection2.projections.CategoryProjection;
// import com.example.pos.connection2.repository.ProductCategoryRepo;
// import com.example.pos.connection2.repository.PurchaseOrderDetailRepo;
// import java.util.*;
// @Service
// public class PurchaseOrderDetailService {
//      @Autowired
//      private PurchaseOrderDetailRepo repo;

//      @Autowired
//      private ProductCategoryRepo proRepo;

//      public void updateOrderQty(int proId){
//           int p = repo.getOrderQty(proId);
//           p--;

//           int id = repo.getPurchaseId(proId);
//           Optional<PurchaseOrderDetail> data = repo.findById(id);
//           PurchaseOrderDetail _data = data.get();

//           _data.setOrderQty(p);
//           repo.save(_data);
//      }


//      public List<CategoryProjection> getCategory(){
//           return proRepo.getCategory();
//      }

// }
