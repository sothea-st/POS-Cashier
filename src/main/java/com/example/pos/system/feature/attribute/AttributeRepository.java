package com.example.pos.system.feature.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    boolean existsByAttrNameEn(String attrNameEn);

    Optional<Attribute> findById(Integer id);

    Optional<Attribute> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // List with pagination
    Page<Attribute> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

    // List without pagination
    List<Attribute> findByStatusTrueAndIsDeletedFalse();

    // Query for search
    @Query(nativeQuery = true, value = "select\r\n" + //
            "\ta.id ,\r\n" + //
            "\ta.attr_name_en,\r\n" + //
            "\ta.attr_name_kh,\r\n" + //
            "\ta.is_deleted,\r\n" + //
            "\ta.status\r\n" + //
            "from\r\n" + //
            "\tpos_attribute a\r\n" + //
            "where\r\n" + //
            "\ta.status = true\r\n" + //
            "\tand a.is_deleted = false\r\n" + //
            "\tand a.attr_name_en ilike %?% \r\n" + //
            "order by\r\n" + //
            "\ta.id desc\r\n" + //
            "")
    // search with pagination
    Page<Attribute> findByAttrNameEn(PageRequest pageable, String valueSearch);

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\ta.id ,\r\n" + //
            "\ta.attr_name_en,\r\n" + //
            "\ta.attr_name_kh,\r\n" + //
            "\ta.is_deleted,\r\n" + //
            "\ta.status\r\n" + //
            "from\r\n" + //
            "\tpos_attribute a\r\n" + //
            "where\r\n" + //
            "\ta.status = true\r\n" + //
            "\tand a.is_deleted = false\r\n" + //
            "\tand a.attr_name_en ilike %?% \r\n" + //
            "order by\r\n" + //
            "\ta.id desc\r\n" + //
            "")
    // search without pagination
    List<Attribute> findByAttrNameEn(String valueSearch);

}
