package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.projections.ReportImport.ReportImportProjection;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface ImportRepository extends JpaRepository<Import,Integer> {

    @Query(nativeQuery = true , value = "select count(*) from pos_import")
    int countRecord();


    @Query(nativeQuery = true , value = "select\r\n" + //
                "\tpp.cost,\r\n" + //
                "\tpp.qty_old ,\r\n" + //
                "\tpp.amount ,\r\n" + //
                "\tpp.pro_id ,\r\n" + //
                "\tp.imp_date ,\r\n" + //
                "\tpp2.pro_name_en,\r\n" + //
                "\tpp2.pro_image_name,\r\n" + //
                "\tpu.full_name\r\n" + //
                "from\r\n" + //
                "\tpos_import p\r\n" + //
                "inner join\r\n" + //
                "\tpos_import_detail pp\r\n" + //
                "on\r\n" + //
                "\tpp.imp_id = p.id\r\n" + //
                "inner join \r\n" + //
                "pos_product pp2 on\r\n" + //
                "\tpp2.id = pp.pro_id\r\n" + //
                "inner join \r\n" + //
                "\tpos_user pu \r\n" + //
                "\ton\r\n" + //
                "\tpu.id = p.create_by\r\n" + //
                "where\r\n" + //
                "\tp.imp_date \r\n" + //
                "between \r\n" + //
                "\t? \r\n" + //
                "and \r\n" + //
                "\t?")
    List<ReportImportProjection> getReport(String dateFrom , String dateTo);


}
