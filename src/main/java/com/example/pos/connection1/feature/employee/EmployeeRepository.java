package com.example.pos.connection1.feature.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pos.connection1.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    boolean existsByContactAndStatusTrueAndIsDeletedFalse(String contact);

    Optional<Employee> findById(Integer id);

    Optional<Employee> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Page<Employee> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

    @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t*\r\n" + //
                        "from\r\n" + //
                        "\tpos_employee p\r\n" + //
                        "where\r\n" + //
                        "\tp.contact = ?\r\n" + //
                        "\tand p.status = true\r\n" + //
                        "\tand p.is_deleted = false\r\n" + //
                        "order by\r\n" + //
                        "\tp.id\r\n" + //
                        "limit 1")
    Optional<Employee> checkPhoneNumber(String contact);

    @Query(nativeQuery = true, value = "select\r\n" + //
        "\t*\r\n" + //
        "from\r\n" + //
        "\tpos_employee p\r\n" + //
        "where\r\n" + //
        "\tp.name_en ilike %?%\r\n" + //
        "\tand p.status = true\r\n" + //
        "\tand p.is_deleted = false\r\n" + //
        "order by\r\n" + //
        "\tp.id desc\r\n")
    Page<Employee> searchByNameEn(PageRequest pageable, String searchValue);

}
