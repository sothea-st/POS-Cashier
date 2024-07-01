package com.example.pos.connection1.feature.vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.pos.connection1.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

     boolean existsByContact(String contact);

     boolean existsByEmail(String email);

     boolean existsByUuid(String uuid);
   
     Optional<Vendor> findByUuidAndStatusTrueAndIsDeletedFalse(String uuid);

     Page<Vendor> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

     Optional<Vendor> findByUuid(String uuid);
 
}
