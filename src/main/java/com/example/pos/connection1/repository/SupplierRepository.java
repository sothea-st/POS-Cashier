package com.example.pos.connection1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.Supplier;
import java.util.*;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
 
    boolean existsByContact(String contact);

    @Query(nativeQuery = true,value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_supplier\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "order by\r\n" + //
                "\tid desc")
    ArrayList<Supplier> getListSupplier();

    @Query(nativeQuery = true,value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_supplier\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "\tand id =?")
    Supplier getSupplierById(int id);

}  