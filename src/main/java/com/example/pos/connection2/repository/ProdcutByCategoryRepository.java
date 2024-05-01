package com.example.pos.connection2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.example.pos.connection2.DTO.ProductByCategoryDTO;
import com.example.pos.connection2.entity.ProductByCategory;
import com.example.pos.connection2.models.ProductModelD2;

import java.util.*;
@Repository
public interface ProdcutByCategoryRepository  extends JpaRepository<ProductByCategory,Integer>{
     // @Query(nativeQuery = true , value = "SELECT p.id , p.name , p.image FROM product_by_categories as p")
     // List<ProductByCategoryProjection> getData();

     // @Query(nativeQuery = true , value = "SELECT c.id ,c.name,c.parent_category_id FROM categories as c where shop_id = 1 and c.parent_category_id is not null")
     // List<CategoryProjection> getCategory();

     @Query(nativeQuery = true , value = "SELECT  *"+
                    "FROM \r\n" + //
                    "    product_by_categories pbc")
     List<ProductByCategory> getProduct();

}
