package com.example.pos.connection1.feature.employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.components.JavaStorage;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.feature.employee.dto.UerAccountResponse;
import com.example.pos.connection1.feature.employee.dto.EmployeeResponse;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class EmployeeServiceImp implements EmployeeService{
    
    private final EmployeeRepository employeeRepository;
    private String idNotFound = "Id has not been found .";

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStoreRepository fileStore;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public EmployeeResponse readById(Integer id) {
        Employee employee = employeeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                 .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        return mEmployeeResponse(employee);
    }

    @Override
    public JavaCollectionResponse<?> read(int pageSize, int pageNumber) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);
        Page<Employee> pages = employeeRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

        List<EmployeeResponse> content = pages.getContent()
                                        .stream()
                                        .map(this::mEmployeeResponse)
                                        .toList();

        return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
    }
    

    @Override
    public EmployeeResponse create(Employee e, MultipartFile file) throws IOException {
        var createdBy = session.getAttribute(JavaConstant.userId);
        Optional<Employee> isExistContact = employeeRepository.checkPhoneNumber(e.getContact());
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

        employeeRepository.save(emp);

        int userCount = userRepository.userCount();
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
        System.out.println("password.getDefaultPassword() : " + password.getDefaultPassword());
        user.setPassword(passwordEncoder.encode(password.getDefaultPassword()));
        user.setRole(null);
        user.setUserCode(userCountRow);
        user.setEmpId(emp.getId());
        user.setCreateBy(e.getCreateBy());
        user.setRole(e.getRoleId().getId());
        userRepository.save(user);
        return mEmployeeResponse(emp);
    }

    @Override
    public EmployeeResponse updateById(Integer id, Employee e, MultipartFile file) throws IOException {
        Optional<Employee> op = employeeRepository.findById(id);
        Employee emp = op.get();
        // var createdBy = session.getAttribute(JavaConstant.userId);
        if (!e.getContact().equals(emp.getContact())) {
            boolean isExistContact = employeeRepository.existsByContact(e.getContact());
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

        employeeRepository.save(emp);

        Optional<User> user = userRepository.findByEmpId(id);
        User userData = user.get();
        userData.setFullName(e.getNameEn());
        userData.setRole(e.getRoleId().getId());
        userRepository.save(userData);

        return mEmployeeResponse(emp);
    }

    @Override
    public void deleteById(Integer id) {
        Employee employee = employeeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        employee.setStatus(false);
        employee.setDeleted(true);
        employeeRepository.save(employee);

        Optional<User> user = userRepository.findByEmpId(id);
        User userData = user.get();
        userData.setStatus(false);
        userData.setDeleted(true);
        userRepository.save(userData);
    }

    @Override
    public JavaCollectionResponse<?> searchEmployee(int pageSize, int pageNumber, String searchValue) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);
        Page<Employee> pages = employeeRepository.searchByNameEn(pageRequest,searchValue);

        List<EmployeeResponse> content = pages.getContent()
                                        .stream()
                                        .map(this::mEmployeeResponse)
                                        .toList();

        return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
    }

    public byte[] getImageEmployee(String id) {
        Optional<FileStore> data = fileStore.findById(id);
        return data.get().getData();
    }
   
    private EmployeeResponse mEmployeeResponse(Employee employee){
        return EmployeeResponse.builder()
                            .id(employee.getId())
                            .nameKh(employee.getNameKh())
                            .nameEn(employee.getNameEn())
                            .gender(employee.getGender())
                            .dob(employee.getDob())
                            .startDate(employee.getStartDate())
                            .imageName(employee.getImageName())
                            .contact(employee.getContact())
                            .roleId(employee.getRoleId().getId())
                            .address(employee.getAddress())
                            .roleName(employee.getRoleId().getRoleName())
                            .createBy(employee.getCreateBy())
                            .createDate(employee.getCreateDate())
                            .status(employee.isStatus())
                            .deleted(employee.isDeleted())
                            .build();
    }

    @Override
    public JavaCollectionResponse<?> readUserAcccount(int pageSize, int pageNumber) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);
        Page<User> pages = userRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

        List<UerAccountResponse> content = pages.getContent()
                                        .stream()
                                        .map(c -> mUerAccountResponse(c))
                                        .toList();

        return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
    }

    @Override
    public JavaCollectionResponse<?> searchUserAcccount(int pageSize, int pageNumber, String searchValue) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);
        Page<User> pages = userRepository.searchByFullName(pageRequest,searchValue);

        List<UerAccountResponse> content = pages.getContent()
                                        .stream()
                                        .map(c -> mUerAccountResponse(c))
                                        .toList();

        return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
    }

    private UerAccountResponse mUerAccountResponse(User user){
        return UerAccountResponse.builder()
                            .id(user.getId())
                            .fullName(user.getFullName())
                            .userCode(user.getUserCode())
                            .empId(user.getEmpId())
                            .build();
    }

}
