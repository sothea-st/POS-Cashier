package com.example.pos.system.feature.brand;

import java.util.List;
import java.util.Optional;

import com.example.pos.system.domain.settings.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.sourceData.Brand;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findById(Integer id);

    boolean existsByBrandNameEn(String brandNameEn);

    Optional<Brand> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // List with pagination
    Page<Brand> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

    // List without pagination
    List<Brand> findByStatusTrueAndIsDeletedFalse();

    @Query(value = """
            select u from Brand u
            where u.status = true
            and u.isDeleted = false
            and (lower(u.brandNameKh) like lower(concat('%', :name, '%'))
            or lower(u.brandNameEn) like lower(concat('%', :name, '%')))
            """)
    Page<Brand> searchByNameEnOrNameKh(PageRequest pageRequest, @PathVariable("name") String name);
}
