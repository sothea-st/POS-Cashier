package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.example.pos.connection1.entity.Attribute;
import com.example.pos.connection1.entity.sourceData.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {

    Optional<Brand> findByIdAndStatusTrueAndIsDeletedFalse(int id);

    boolean existsByBrandNameEn(String brandNameEn);
    boolean existsByBrandNameKh(String brandNameKh);

    @Query(nativeQuery = true , value = "select * from pos_brand where status = true and is_deleted = false order by id desc")
    List<Brand> getListBrand();

    @Query(nativeQuery = true , value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_brand\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "\tand id = ?")
    Brand getBrandById(int id);


    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tb.id ,\r\n" + //
            "\tb.brand_name_en,\r\n" + //
            "\tb.brand_name_kh,\r\n" + //
            "\tb.create_by,\r\n" + //
            "\tb.create_date,\r\n" + //
            "\tb.is_deleted,\r\n" + //
            "\tb.status\r\n" + //
            "from\r\n" + //
            "\tpos_brand b\r\n" + //
            "where\r\n" + //
            "\tb.status = true\r\n" + //
            "\tand b.is_deleted = false\r\n" + //
            "\tand b.brand_name_en ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tb.id desc\r\n" + //
            "")
    List<Brand> searchBrand(String valueSearch);
}
