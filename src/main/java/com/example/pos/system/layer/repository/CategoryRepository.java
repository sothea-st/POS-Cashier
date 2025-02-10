package com.example.pos.system.layer.repository;

import com.example.pos.system.domain.settings.Category;
import com.example.pos.system.domain.settings.Ranges;
import com.example.pos.system.layer.projections.GetCategoryByCode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c FROM Category c WHERE c.status = true AND c.isDeleted = false AND c.parentId = :parentId ORDER BY c.movePosition ASC")
    List<Category> getCategory(int parentId);

    @Query("SELECT c FROM Category c WHERE c.code = :code AND c.status = true AND c.isDeleted = false ORDER BY c.createDate DESC")
    Page<Category> findByCodeAndStatusTrueAndIsDeletedFalseWithSorting(@Param("code") String code, PageRequest pageable);

    @Query("SELECT c FROM Category c WHERE c.code = :code AND c.status = true AND c.isDeleted = false ORDER BY c.createDate DESC")
    List<Category> findByCodeAndStatusTrueAndIsDeletedFalseWithSorting(@Param("code") String code);

    //List get category with pagination
    Page<Category> findByCodeAndStatusTrueAndIsDeletedFalse(String code, PageRequest pageRequest);

    //List get category without pagination
    List<Category> findByCodeAndStatusTrueAndIsDeletedFalse(String code);



    //List get search category with pagination
//    Page<Category> findByCodeAndCatNameEnContainingIgnoreCaseAndStatusTrueAndIsDeletedFalse(String code, String catNameEn, PageRequest pageRequest);

    @Query(value = """
       select u from Category u
       where u.status = true
       and u.isDeleted = false
       and u.code = :code
       and (lower(u.catNameKh) like lower(concat('%', :name, '%'))
       or lower(u.catNameEn) like lower(concat('%', :name, '%')))
       """)
    Page<Category> searchByCatNameEnOrCatNameKh(PageRequest pageRequest, @PathVariable("name") String name,@PathVariable("code") String code);

    //List get search category without pagination
//    List<Category> findByCodeAndCatNameEnContainingIgnoreCaseAndStatusTrueAndIsDeletedFalse(String code, String catNameEn);

    @Query(value = "SELECT c FROM Category c WHERE c.status = true AND c.isDeleted = false AND c.code = :code ORDER BY c.movePosition ASC")
    List<Category> getCategoryByCode(String code);

    Optional<Category> findByParentIdAndStatusTrueAndIsDeletedFalse(int parentId);

    Optional<Category> findByIdAndStatusTrueAndIsDeletedFalse(int id);

    @Query(value = """
            WITH RECURSIVE category_hierarchy AS (
                -- Base case: Start with the given id
                SELECT id, parent_id
                FROM pos_category
                WHERE id = :subCatId
                
                UNION ALL
                
                -- Recursive case: Find the parent for the current id
                SELECT c.id, c.parent_id
                FROM pos_category c
                INNER JOIN category_hierarchy ch
                ON c.id = ch.parent_id
            )
            -- Select the topmost ancestor
            SELECT id
            FROM category_hierarchy
            WHERE parent_id = 0
            """, nativeQuery = true)
    Integer getDivisionId(@Param("subCatId") int subCatId);

    Optional<Category> findByIdAndStatusTrueAndIsDeletedFalseAndCode(int id, String code);

    boolean existsByCatNameKhAndCodeIgnoreCaseAndStatusTrueAndIsDeletedFalse(String catNameKh, String code);

    boolean existsByCatNameEnAndCodeIgnoreCaseAndStatusTrueAndIsDeletedFalse(String catNameKh, String code);

    Boolean existsByCatNameEnAndCodeAndStatusTrueAndIsDeletedFalse(String productTypeNameEn,String code);

    @Query(nativeQuery = true, value = """
                SELECT EXISTS (
                    SELECT 1 
                    FROM pos_category pc 
                    WHERE LOWER(pc.cat_name_en) = LOWER(:catNameEn)
                    AND pc.code = :code
                )
            """)
    boolean existsCatNameEnAndCode(@Param("catNameEn") String catNameEn, @Param("code") String code);


    @Query(nativeQuery = true, value = """
                SELECT EXISTS (
                    SELECT 1
                    FROM pos_category pc
                    WHERE cat_name_kh IS NOT NULL
                    AND TRIM(cat_name_kh) = :catNameKh
                    AND code = :code
                )
            """)
    boolean existsCatNameKhAndCode( @Param("catNameKh") String catNameKh, @Param("code") String code);


    @Query(nativeQuery = true, value = "select\r\n" + //
            "\t*\r\n" + //
            "from\r\n" + //
            "\tpos_category\r\n" + //
            "where\r\n" + //
            "\tstatus = true\r\n" + //
            "\tand is_deleted = false\r\n" + //
            "\tand id =?")
    Category getCategoryById(int id);

    @Query(nativeQuery = true, value = "select count(*) from pos_category")
    int countLengthRow();


    @Query(nativeQuery = true, value = "SELECT * FROM get_categories_by_code(?,?)")
    List<GetCategoryByCode> search(String code, String searchValue);


}
