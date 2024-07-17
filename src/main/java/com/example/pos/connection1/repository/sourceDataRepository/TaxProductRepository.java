package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.connection1.entity.sourceData.TaxProduct;
import com.example.pos.connection1.projections.TaxProductProjection.TaxProductProjection;


@Repository
public interface TaxProductRepository extends JpaRepository<TaxProduct,Integer> {


     Optional<TaxProduct> findByIdAndStatusTrueAndIsDeletedFalse(int id);
     @Query(nativeQuery = true , value = "select id,tax_name,rate_tax from pos_product_tax where status = true and is_deleted = false")
     List<TaxProductProjection> getTax();


     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\ttax_name,\r\n" + //
                    "\trate_tax\r\n" + //
                    "from\r\n" + //
                    "\tpos_product_tax\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand id = ?")
     Optional<TaxProductProjection> getById(int id);


     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\ttax_name,\r\n" + //
                    "\trate_tax\r\n" + //
                    "from\r\n" + //
                    "\tpos_product_tax\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand tax_name ilike %?% \r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "")
     List<TaxProductProjection> searchTax(String searchvalue);

}
