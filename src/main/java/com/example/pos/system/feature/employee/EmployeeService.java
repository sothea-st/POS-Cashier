package com.example.pos.system.feature.employee;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.pos.system.domain.settings.Employee;
import com.example.pos.system.feature.employee.dto.EmployeeResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface EmployeeService {
    EmployeeResponse readById(Integer id);

    JavaCollectionResponse<?> read(int pageSize, int pageNumber);

    EmployeeResponse create (Employee e, MultipartFile file) throws IOException;

    EmployeeResponse updateById (Integer id, Employee e, MultipartFile file) throws IOException;

    void deleteById(Integer id);

    JavaCollectionResponse<?> searchEmployee(Integer pageSize, Integer pageNumber, String searchValue);

    byte[] getImageEmployee(String id);

    JavaCollectionResponse<?> readUserAcccount(int pageSize, int pageNumber);

    JavaCollectionResponse<?> searchUserAcccount(Integer pageSize, Integer pageNumber, String searchValue);
}
