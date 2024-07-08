package com.example.pos.connection1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {




//    Optional<User> findByEmail(String email);
    Optional<User>  findByUserCode(String userCode);

//    boolean existsByEmail(String email);

//    boolean existsByPhone(String phone);

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