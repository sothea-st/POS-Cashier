package com.example.pos.connection1.feature.vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
import com.example.pos.connection1.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

     boolean existsByContact(String contact);

     boolean existsByEmail(String email);

     boolean existsByUuid(String uuid);

     boolean existsById(int id);

   
     Optional<Vendor> findByUuidAndStatusTrueAndIsDeletedFalse(String uuid);

     Page<Vendor> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

     Optional<Vendor> findByUuid(String uuid);

     Optional<Vendor> findByIdAndStatusTrueAndIsDeletedFalse(int id);
 


     @Query(nativeQuery = true, value = "select\r\n" + //
            "\tv.id ,\r\n" + //
            "\tv.vendor_name,\r\n" + //
            "\tv.uuid,\r\n" + //
            "\tv.address,\r\n" + //
            "\tv.contact,\r\n" + //
            "\tv.email,\r\n" + //
            "\tv.website,\r\n" + //
            "\tv.vendor_code,\r\n" + //
            "\tv.create_by,\r\n" + //
            "\tv.create_date,\r\n" + //
            "\tv.is_deleted,\r\n" + //
            "\tv.status\r\n" + //
            "from\r\n" + //
            "\tpos_vendors v\r\n" + //
            "where\r\n" + //
            "\tv.status = true\r\n" + //
            "\tand v.is_deleted = false\r\n" + //
            "\tand v.vendor_name ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tv.id desc\r\n" + //
            "")
     Page<Vendor> findByVendorName (PageRequest pageable, String searchValue);


 
}
