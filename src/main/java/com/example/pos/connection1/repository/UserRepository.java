package com.example.pos.connection1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByIdAndStatusTrueAndIsDeletedFalse(int id);



    Page<User> findByStatusTrueAndIsDeletedFalse(PageRequest pageaable);

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\t*\r\n" + //
            "from\r\n" + //
            "\tpos_user u\r\n" + //
            "where\r\n" + //
            "\tu.status = true\r\n" + //
            "\tand u.is_deleted = false\r\n" + //
            "\tand u.full_name ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tu.id desc\r\n" + //
            "")
    Page<User> searchByFullName (PageRequest pageaable, String searchValue);

    @Query(nativeQuery = true, value = "SELECT \n" +
            "    * \n" +
            "FROM \n" +
            "    pos_user u \n" +
            "WHERE \n" +
            "    u.status = true \n" +
            "    AND u.is_deleted = false \n" +
            "    AND (u.user_code ILIKE %?1% \n" +
            "    OR u.full_name ILIKE %?1%) \n" +
            "ORDER BY \n" +
            "    u.id DESC")
    List<User> searchByFullName(@Param("searchValue") String searchValue);

    Optional<User>  findByUserCode(String userCode);

    @Query(nativeQuery = true,value = "select\r\n" + //
                "\tcount(*)\r\n" + //
                "from\r\n" + //
                "\tpos_user")
    int userCount();

    @Query(nativeQuery = true , value = "select\r\n" + //
                "\tpe.name_en\r\n" + //
                "from\r\n" + //
                "\tpos_user pu\r\n" + //
                "inner join pos_employee pe on\r\n" + //
                "\tpu.emp_id = pe.id\r\n" + //
                "where\r\n" + //
                "\tpe.status = true\r\n" + //
                "\tand pe.is_deleted = false\r\n" + //
                "\tand pu.status = true\r\n" + //
                "\tand pu.is_deleted = false\r\n" + //
                "\tand pu.id = ?")
    String getNameEmp(int id);

    @Query(nativeQuery = true,value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_user\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "\tand id =?")
    User getUserById(int id);
 
    Optional<User> findByEmpId(Integer empId);

    @Query(nativeQuery = true , value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_user pu\r\n" + //
                "where\r\n" + //
                "\tpu.user_code = ?\r\n" + //
                "\tand pu.status = true\r\n" + //
                "\tand pu.is_deleted = false")
    Optional<User>  findByUserCodeAndStatusTrue(String userCode);

    

}