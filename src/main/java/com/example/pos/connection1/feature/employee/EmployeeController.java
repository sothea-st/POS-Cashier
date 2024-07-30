package com.example.pos.connection1.feature.employee;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.feature.employee.dto.EmployeeResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;

    //Detail Employee
    @GetMapping("/{id}")
    EmployeeResponse readById(@PathVariable("id") Integer id){
        return employeeService.readById(id);
    }

    //List Employee
    @GetMapping
    JavaCollectionResponse<?> read(
        @RequestParam(defaultValue = "10", required = false ) int pageSize, 
        @RequestParam(defaultValue = "0", required = false )int pageNumber){
            return employeeService.read(pageSize, pageNumber);
    }

    //Addd Employee
    @PostMapping
    public ResponseEntity<?> create(@Valid @ModelAttribute Employee e,
        @RequestParam(value = "image", required = false) MultipartFile file) throws IOException, java.io.IOException {
        HashMap<String, String> err = new HashMap<>();
        String key = "contact";
        String contact = JavaValidation.checkPhone(e.getContact());

        if (!contact.isEmpty()) {
            err.put(key, contact);
            return ResponseEntity.status(500).body(err);
        }
        EmployeeResponse data = employeeService.create(e, file);
        return JavaResponse.success(data);
    }

    //Update Employee
    @PostMapping("/{id}")
    public ResponseEntity<?> updateById (@Valid @PathVariable("id") int id, @ModelAttribute Employee e,
            @RequestParam(name = "image", required = false) MultipartFile file) throws IOException, java.io.IOException {

        HashMap<String, String> err = new HashMap<>();
        String key = "contact";
        String contact = JavaValidation.checkPhone(e.getContact());

        if (!contact.isEmpty()) {
            err.put(key, contact);
            return ResponseEntity.status(500).body(err);
        }
        EmployeeResponse data = employeeService.updateById(id, e, file);
        return JavaResponse.success(data);
    }

    //Delete Employee
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
		employeeService.deleteById(id);
	}

    //Search Employee
    @GetMapping("/searchEmployee/{nameEn}")
    JavaCollectionResponse<?> searchEmployee(
        @RequestParam(defaultValue = "10", required = false ) int pageSize, 
        @RequestParam(defaultValue = "0", required = false )int pageNumber,
        @PathVariable("nameEn") String searchValue){
            return employeeService.searchEmployee(pageSize, pageNumber,searchValue);
    }

    @GetMapping("/readFileById/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
         byte[] data = employeeService.getImageEmployee(id);
         return ResponseEntity.status(HttpStatus.OK)
                   .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                   .body(data);
    }

    @GetMapping("/userAccount")
    JavaCollectionResponse<?> readUserAcccount(
        @RequestParam(defaultValue = "10", required = false ) int pageSize, 
        @RequestParam(defaultValue = "0", required = false )int pageNumber){
            return employeeService.readUserAcccount(pageSize, pageNumber);
    }

    @GetMapping("/searchUserAccount/{fullName}")
    JavaCollectionResponse<?> searchUserAcccount(
        @RequestParam(defaultValue = "10", required = false ) int pageSize, 
        @RequestParam(defaultValue = "0", required = false )int pageNumber,
        @PathVariable("fullName") String searchValue){
            return employeeService.searchUserAcccount(pageSize, pageNumber, searchValue);
    }

}
