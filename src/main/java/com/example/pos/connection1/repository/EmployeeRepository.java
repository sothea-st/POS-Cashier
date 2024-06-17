package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.projections.AccountUserProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByContact(String contact);

    @Query(nativeQuery = true, value = "select * from pos_employee p where p.contact = ? and p.status = true and p.is_deleted = false order by p.id limit 1")
    Optional<Employee> checkPhoneNumber(String contact);

    @Query(nativeQuery = true, value = "select * from pos_employee pe where status = true and is_deleted = false order by id desc")
    List<Employee> getEmployee();

    @Query(nativeQuery = true, value = " select * from pos_employee where status = true and is_deleted =  false and id = ?")
    Employee getEmployeeById(int id);

    // @Query(nativeQuery = true , value = "select ")
    // String getEmpName(int id);

    @Query(nativeQuery = true, value = "  select u.user_code ,u.full_name,u.id,u.emp_id  from pos_user u where u.status =true and u.is_deleted =false order by u.id desc")
    List<AccountUserProjection> getAccountUserProjections();

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tu.user_code ,\r\n" + //
            "\tu.full_name,\r\n" + //
            "\tu.id,\r\n" + //
            "\tu.emp_id\r\n" + //
            "from\r\n" + //
            "\tpos_user u\r\n" + //
            "where\r\n" + //
            "\tu.status = true\r\n" + //
            "\tand u.is_deleted = false\r\n" + //
            "\tand u.user_code ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tu.id desc\r\n" + //
            "")
    List<AccountUserProjection> getAccountUserProjectionsByUserCode(String userCode);

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tu.user_code ,\r\n" + //
            "\tu.full_name,\r\n" + //
            "\tu.id,\r\n" + //
            "\tu.emp_id\r\n" + //
            "from\r\n" + //
            "\tpos_user u\r\n" + //
            "where\r\n" + //
            "\tu.status = true\r\n" + //
            "\tand u.is_deleted = false\r\n" + //
            "\tand u.full_name ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tu.id desc\r\n" + //
            "")
    List<AccountUserProjection> getAccountUserProjectionsByUserName(String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM pos_employee p WHERE p.name_en  ILIKE %?% and p.status = true and p.is_deleted = false")
    List<Employee> findByNameEn(String nameEn);

}
