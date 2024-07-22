package com.example.pos.connection1.feature.tax;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.sourceData.TaxProduct;

@Repository
public interface TaxRepository extends JpaRepository<TaxProduct, Integer>{
    Optional<TaxProduct> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);
    Page<TaxProduct> findByStatusTrueAndIsDeletedFalse (PageRequest pageable);

    @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\ttax_name,\r\n" + //
                    "\trate_tax,\r\n" + //
                    "\tcreate_by,\r\n" + //
                    "\tcreate_date,\r\n" + //
                    "\tis_deleted,\r\n" + //
                    "\tstatus\r\n" + //
                    "from\r\n" + //
                    "\tpos_product_tax \r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand tax_name ilike %?% \r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "")
    Page<TaxProduct> searchTax (PageRequest pageable, String searchValue);
    
}
