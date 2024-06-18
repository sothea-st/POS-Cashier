package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(nativeQuery = true,value = "select * from pos_category  where  status=true and is_deleted=false and parent_id=? order by move_position asc")
    ArrayList<Category> getCategory(int parentId);

    boolean existsByCatNameKh(String catNameKh);
    boolean existsByCatNameEn(String catNameKh);

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


}
