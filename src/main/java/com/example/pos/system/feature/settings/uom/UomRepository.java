package com.example.pos.system.feature.settings.uom;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.system.domain.settings.Uom;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface UomRepository extends JpaRepository<Uom, Integer> {

    // Check if uom name en is already existed?
    Boolean existsByUomNameEnAndStatusTrueAndIsDeletedFalse(String uomNameEn);

    // Check if uom name kh is already existed?
    Boolean existsByUomNameKhAndStatusTrueAndIsDeletedFalseAndUomNameKhIsNotNull(String uomNameKh);

    // List Uom with pageNumber and pageSize
    Page<Uom> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    // List Uom without pageNumber and pageSize
    List<Uom> findByStatusTrueAndIsDeletedFalse();

    // Get Uom by id
    Optional<Uom> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // Search uom by UomNameEn or UomNameKh
    @Query(value = """
       select u from Uom u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.uomNameEn) like lower(concat('%', :name, '%'))
       or lower(u.uomNameKh) like lower(concat('%', :name, '%')))
       """)
    Page<Uom> searchByUomNameEnOrUomNameKh(PageRequest pageRequest, @PathVariable("name") String name);

    // Search uom by UomNameEn or UomNameKh
    @Query(value = """
       select u from Uom u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.uomNameEn) like lower(concat('%', :name, '%'))
       or lower(u.uomNameKh) like lower(concat('%', :name, '%')))
       """)
    List<Uom> searchByUomNameEnOrUomNameKh(@PathVariable("name") String name);

}
