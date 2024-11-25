package com.example.pos.connection1.feature.settings.warehouse;

import com.example.pos.connection1.entity.settings.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {

    // Check if warehouse name en is already existed?
    Boolean existsByWarehouseNameEnAndStatusTrueAndIsDeletedFalse(String warehouseNameEn);

    // Check if warehouse name kh is already existed?
    Boolean existsByWarehouseNameKhAndStatusTrueAndIsDeletedFalseAndWarehouseNameKhIsNotNull(String warehouseNameKh);

    // List warehouse with pageNumber and pageSize
    Page<Warehouse> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    // List warehouse without pageNumber and pageSize
    List<Warehouse> findByStatusTrueAndIsDeletedFalse();

    // Get warehouse by id
    Optional<Warehouse> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // Search warehouse by warehouseNameEn or warehouseNameKh
    @Query(value = """
       select u from Warehouse u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.warehouseNameEn) like lower(concat('%', :name, '%'))
       or lower(u.warehouseNameKh) like lower(concat('%', :name, '%')))
       """)
    Page<Warehouse> searchByWarehouseNameEnOrWarehouseNameKh(PageRequest pageRequest, @PathVariable("name") String name);

    // Search warehouse by warehouseNameEn or warehouseNameKh
    @Query(value = """
       select u from Warehouse u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.warehouseNameEn) like lower(concat('%', :name, '%'))
       or lower(u.warehouseNameKh) like lower(concat('%', :name, '%')))
       """)
    List<Warehouse> searchByWarehouseNameEnOrWarehouseNameKh(@PathVariable("name") String name);
}
