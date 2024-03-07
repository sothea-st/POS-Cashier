package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.entity.sourceData.TaxProduct;
import com.example.pos.projections.TaxProductProjection.TaxProductProjection;

@Repository
public interface TaxProductRepository extends JpaRepository<TaxProduct,Integer> {
     @Query(nativeQuery = true , value = "select id,tax_name,rate_tax from pos_product_tax where status = true and is_deleted = false")
     List<TaxProductProjection> getTax();


     @Query(nativeQuery = true , value = "select id,tax_name,rate_tax from pos_product_tax where status = true and is_deleted = false and id = ?")
     Optional<TaxProductProjection> getById(int id);

}
