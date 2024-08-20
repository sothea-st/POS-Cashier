package com.example.pos.connection1.feature.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.sourceData.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

        Optional<Brand> findById(Integer id);

        boolean existsByBrandNameEn(String brandNameEn);

        Optional<Brand> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

        // List with pagination
        Page<Brand> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

        // List without pagination
        List<Brand> findByStatusTrueAndIsDeletedFalse();

        // Query for search
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
        // search with pagination
        Page<Brand> searchBrand(PageRequest pageable, String valueSearch);

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
        // search without pagination
        List<Brand> searchBrand(String valueSearch);
}
