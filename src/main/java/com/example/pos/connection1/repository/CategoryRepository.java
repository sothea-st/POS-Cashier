package com.example.pos.connection1.repository;

import com.example.pos.connection1.DTO.categoryDto.CategoryResponse;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.projections.GetCategoryByCode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c FROM Category c WHERE c.status = true AND c.isDeleted = false AND c.parentId = :parentId ORDER BY c.movePosition ASC")
    List<Category> getCategory(int parentId);


    Page<Category> findByCodeAndStatusTrueAndIsDeletedFalse(String code, PageRequest pageRequest);
    Page<Category> findByCodeAndCatNameEnContainingIgnoreCaseAndStatusTrueAndIsDeletedFalse(String code,String catNameEn,PageRequest pageRequest);


    @Query(value = "SELECT c FROM Category c WHERE c.status = true AND c.isDeleted = false AND c.code = :code ORDER BY c.movePosition ASC")
    List<Category> getCategoryByCode(String code);

    Optional<Category> findByParentIdAndStatusTrueAndIsDeletedFalse(int parentId);

    Optional<Category> findByIdAndStatusTrueAndIsDeletedFalse(int id);

    Optional<Category> findByIdAndStatusTrueAndIsDeletedFalseAndCode(int id,String code);
    
    boolean existsByCatNameKhIgnoreCaseAndStatusTrueAndIsDeletedFalse(String catNameKh);
    boolean existsByCatNameEnIgnoreCaseAndStatusTrueAndIsDeletedFalse(String catNameKh);

    @Query(nativeQuery = true,value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_category\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "\tand id =?")
    Category getCategoryById(int id);

    @Query(nativeQuery = true , value = "select count(*) from pos_category")
    int countLengthRow();


    @Query(nativeQuery = true, value ="SELECT * FROM get_categories_by_code(?,?)")
    List<GetCategoryByCode> search (String code, String searchValue);



}
