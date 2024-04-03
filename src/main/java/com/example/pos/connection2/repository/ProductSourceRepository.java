package com.example.pos.connection2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.connection2.entity.ProductSource;
import com.example.pos.connection2.projections.ProductSourceProjection;

@Repository
public interface ProductSourceRepository extends JpaRepository<ProductSource,Integer> {
     @Query(nativeQuery = true , value = "select pbc.category_id ,pbc.name ,pbc.name_kh ,pbc.image ,pbc.price ,pbc.cost ,pbc.status ,pbc.barcode ,pbc.discount ,pbc.brand from product_by_categories pbc limit 2" )
     List<ProductSourceProjection> getProductSource();
}
