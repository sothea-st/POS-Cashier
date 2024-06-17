package com.example.pos.connection1.service;

import com.example.pos.connection1.components.JavaStorage;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.projections.AccountUserProjection;
import com.example.pos.connection1.repository.EmployeeRepository;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private FileStoreRepository fileStore;

    @Autowired
    private UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public List<Employee> searchEmp(String value) {
        return repo.findByNameEn(value);
    }

    public EmployeeService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Employee addEmployee(Employee e, MultipartFile file) throws IOException {
        var createdBy = session.getAttribute(JavaConstant.userId);

        Optional<Employee> isExistContact = repo.checkPhoneNumber(e.getContact());

        JavaValidation.phoneAlreadyExist(isExistContact.isEmpty()  ? false : true);

        Employee emp = new Employee();
        emp.setNameKh(e.getNameKh());
        emp.setNameEn(e.getNameEn());
        emp.setGender(e.getGender());
        emp.setDob(e.getDob());
        emp.setAddress(e.getAddress());
        emp.setContact(e.getContact());
        emp.setStartDate(e.getStartDate());
        emp.setCreateBy(e.getCreateBy());
        emp.setRoleId(e.getRoleId());

        if (file == null || file.isEmpty()) {
            emp.setImageName(JavaConstant.defaultNameImage);
        } else {
            // JavaStorage.storeImage(file); for save image to path assests/product in
            // project
            String fileName = JavaStorage.setFileName(file.getOriginalFilename());
            emp.setImageName(fileName);

            // save information image to table pos_file
            // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            emp.setImageName(fileName);
        }

        repo.save(emp);

        int userCount = userRepo.userCount();
        String userCountRow = "";
        userCount++;

        if (userCount < 10) {
            userCountRow = "000" + userCount;
        } else if (userCount < 100) {
            userCountRow = "00" + userCount;
        } else if (userCount < 1000) {
            userCountRow = "0" + userCount;
        } else {
            userCountRow = "0" + userCount;
        }

        User user = new User();
        user.setFullName(emp.getNameEn());
        JavaConstant password = new JavaConstant();
        user.setPassword(passwordEncoder.encode(password.getDefaultPassword()));
        user.setRole(null);
        user.setUserCode(userCountRow);
        user.setEmpId(emp.getId());
        user.setCreateBy(e.getCreateBy());
        user.setRole(e.getRoleId());
        userRepo.save(user);
        return emp;
    }

    public List<Employee> getEmployee() { 
        System.out.println("ssssssssssssssssssssssss");

        return repo.getEmployee();
    }

    public Employee getEmployeeById(int id) {
        return repo.getEmployeeById(id);
    }

    public void deleteEmployeeById(int id) {
        Optional<Employee> op = repo.findById(id);
        Employee emp = op.get();
        emp.setDeleted(true);
        emp.setStatus(false);
        repo.save(emp);

        Optional<User> user = userRepo.findByEmpId(id);
        User userData = user.get();
        userData.setStatus(false);
        userData.setDeleted(true);
        userRepo.save(userData);
    }

    public Employee updateEmployee(int id, Employee e, MultipartFile file) throws IOException {
        Optional<Employee> op = repo.findById(id);
        Employee emp = op.get();
        // var createdBy = session.getAttribute(JavaConstant.userId);
        if (!e.getContact().equals(emp.getContact())) {
            boolean isExistContact = repo.existsByContact(e.getContact());
            JavaValidation.phoneAlreadyExist(isExistContact);
        }

        emp.setNameKh(e.getNameKh());
        emp.setNameEn(e.getNameEn());
        emp.setGender(e.getGender());
        emp.setDob(e.getDob());
        emp.setAddress(e.getAddress());
        emp.setContact(e.getContact());
        emp.setStartDate(e.getStartDate());
        emp.setCreateBy(e.getCreateBy());
        emp.setRoleId(e.getRoleId());
        if (file != null) {
            // save information image to table pos_file
            // String imgName = JavaStorage.setFileName(file.getOriginalFilename());
            String imgName = file.getOriginalFilename();
            FileStore f1 = new FileStore(imgName, imgName, file.getContentType(), file.getBytes());
            fileStore.save(f1);
            emp.setImageName(imgName);
        }

        repo.save(emp);

        Optional<User> user = userRepo.findByEmpId(id);
        User userData = user.get();
        userData.setFullName(e.getNameEn());
        userData.setRole(e.getRoleId());
        userRepo.save(userData);

        return emp;
    }

    public byte[] getImageEmployee(String id) {
        Optional<FileStore> data = fileStore.findById(id);
        return data.get().getData();
    }

    public List<AccountUserProjection> getUserAccount() {
        return repo.getAccountUserProjections();
    }

}
