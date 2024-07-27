package com.example.pos.connection1.feature.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pos.connection1.entity.Employee;
import java.util.Optional;


// public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
//     Optional<Employee> findById(Integer id);

//     Optional<Employee> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

//     Page<Employee> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);
    
//     Page<Employee> findByNameEn(PageRequest pageable, String searchValue);
// }
