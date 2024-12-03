package com.example.pos.system.config.authentication.controllers;

import com.example.pos.system.config.authentication.dtos.LoginUserDto;
import com.example.pos.system.config.authentication.dtos.RegisterUserDto;
import com.example.pos.system.config.authentication.services.AuthenticationService;
import com.example.pos.system.config.authentication.services.JwtService;
import com.example.pos.system.constant.JavaResponse;
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.JavaValidation;
import com.example.pos.system.layer.repository.DeviceRepository;
// import com.example.pos.connection1.repository.EmployeeRepository;
import com.example.pos.system.layer.repository.IPAddressRepository;
import com.example.pos.system.layer.repository.UserRepository;
import com.example.pos.system.layer.repository.roleAndPermissionRepository.RoleRepository;
import com.example.pos.system.layer.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.system.domain.general.Device;
import com.example.pos.system.domain.IPAddressPOSID;
import com.example.pos.system.domain.User;
import com.example.pos.system.feature.employee.EmployeeRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/auth")
@RestController
@Validated
@Slf4j
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    @Autowired
    private OpenShiftRepository repoOpen;

    @Autowired
    private EmployeeRepository repoEmp;

    @Autowired
    private RoleRepository repoRole;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private UserRepository userRepo;

    private final IPAddressRepository ipAddressRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService,
                                    IPAddressRepository ipAddressRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.ipAddressRepository = ipAddressRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        HashMap<String, Object> error = new HashMap<>();

        // String keyEmail ="email";
        // String email = JavaValidation.checkEmail(registerUserDto.getEmail());

        String keyFullName = "fullName";
        String fullName = JavaValidation.checkField(registerUserDto.getFullName(), keyFullName);

        String keyPassword = "password";
        String password = JavaValidation.checkPassword(registerUserDto.getPassword());

        // String keyRole ="role";
        // String role = JavaValidation.checkRole(""+registerUserDto.getRole() ,
        // keyRole);

        // String keyPhone ="phone";
        // String phone = JavaValidation.checkPhone(registerUserDto.getPhone());

        // if( !email.isEmpty() ) error.put(keyEmail, email);
        // if( !role.isEmpty() ) error.put(keyRole,role);
        if (!fullName.isEmpty())
            error.put(keyFullName, fullName);
        if (!password.isEmpty())
            error.put(keyPassword, password);
        // if( !phone.isEmpty() ) error.put(keyPhone,phone);
        if (!error.isEmpty())
            return ResponseEntity.status(500).body(error);

        User registeredUser = authenticationService.signup(registerUserDto);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", registeredUser.getId());
        map.put("name", registeredUser.getFullName());
        map.put("userCode", registeredUser.getUserCode());
        map.put("password", registeredUser.getPassword());

        // map.put("phone", registeredUser.getPhone());
        map.put("role", registeredUser.getRole());
        // return ResponseEntity.ok(registeredUser);
        return JavaResponse.success(map);
    }

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {

        HashMap<String, Object> map = new HashMap<>();
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        int getCountIP = ipAddressRepository.getCountIP();
        String posId = "";

        Optional<User> userCheck = userRepo.findByUserCodeAndStatusTrue(loginUserDto.getUserCode());


        // for do at home
        // if (!userCheck.isEmpty()) {
        //     map.put("token", jwtToken);
        //     map.put("id", authenticatedUser.getId());
        //     map.put("empId", authenticatedUser.getEmpId());
        //     map.put("userCode", authenticatedUser.getUserCode());
        //     map.put("roleId", authenticatedUser.getRole());
        //     map.put("roleName", "Admin");
        //     map.put("token", jwtToken);
        //     map.put("posId", posId);
        //     map.put("userName", "SOTHEA");
        //     map.put("msg", JavaConstant.success);
        //     return ResponseEntity.ok().body(map);
        // }
        // ============ end ==========


        if (userCheck.isEmpty()) {
            return ResponseEntity.ok().body(Map.of("msg", "Check your account and password again"));
        }

        if (loginUserDto.getIpAddress() != null && loginUserDto.getDeviceName() != null) {
            System.out.println("fffffffffffffffffffffffffffffffffff");

            if (getCountIP > 0) {
                Optional<IPAddressPOSID> data = ipAddressRepository.getIpAdrress();

                IPAddressPOSID val = data.get();
                if (val.getUserId() == authenticatedUser.getId()) {
                    posId = val.getPosId();
                } else {

                    if (val.getPosId().equals("01")) {
                        posId = "02";
                    } else if (val.getPosId().equals("02")) {
                        posId = "01";
                    }

                    IPAddressPOSID ipAddressPOSID = IPAddressPOSID.builder()
                            .ipAddress(loginUserDto.getIpAddress())
                            .deviceName(loginUserDto.getDeviceName())
                            .posId(posId)
                            .userId(authenticatedUser.getId())
                            .build();
                    ipAddressRepository.save(ipAddressPOSID);
                }

            } else {
                posId = "01";
                IPAddressPOSID ipAddressPOSID = IPAddressPOSID.builder()
                        .ipAddress(loginUserDto.getIpAddress())
                        .deviceName(loginUserDto.getDeviceName())
                        .posId(posId)
                        .userId(authenticatedUser.getId())
                        .build();
                ipAddressRepository.save(ipAddressPOSID);
            }
        }

        // LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
        // .setExpiresIn(jwtService.getExpirationTime());

        int countPosId = repoOpen.countPosId(JavaConstant.currentDate);
        // countPosId++;
        // String posId = "";
        // if (countPosId < 10) {
        // posId = "0" + countPosId;
        // } else {
        // posId = "" + countPosId;
        // }
        // user name
        String userName = null;
        if (authenticatedUser.getEmpId() != null) {
            userName = repoEmp.findById(authenticatedUser.getEmpId()).get().getNameEn();
        }

        String roleName = "This account not assign role yet.They can not use any function in system!";

        if (authenticatedUser.getRole() != null) {

            roleName = repoRole.findById(authenticatedUser.getRole()).get().getRoleName();
        }

        if (loginUserDto.getDeviceName() != null) {
            System.out.println("qqqqqqqqqqqqqqqqqqqqq");
            if (authenticatedUser.getDevice() == null) {
                System.out.println("11111111111111111111111");
                int count = deviceRepo.count(authenticatedUser.getId(), JavaConstant.currentDate);
                count++;
                Device d = new Device();
                d.setCount(count);
                d.setDeviceName(loginUserDto.getDeviceName());
                d.setDate(JavaConstant.currentDate);
                d.setUserId(authenticatedUser.getId());
                deviceRepo.save(d);
                Optional<User> u = userRepo.findById(authenticatedUser.getId());
                User _user = u.get();
                _user.setDevice(d.getId());
                userRepo.save(_user);
            } else {
//                System.out.println("33333333333333333333333333");
                String deviceName = deviceRepo.deviceName(authenticatedUser.getDevice());
                System.out.println("fffffffffffffff = " + deviceName);
                if (deviceName != null) {
                    deviceName = deviceName.toLowerCase();
                    if (!deviceName.equals(loginUserDto.getDeviceName().toLowerCase())) {
//                    System.out.println("66666666666666666666666666");
                        map.put("msg", "This user already used in other device !");
                        return ResponseEntity.ok().body(map);
                    }
                }

            }
        }

        httpSession.setAttribute(JavaConstant.userId, authenticatedUser.getId());
        httpSession.setAttribute(JavaConstant.userCode, authenticatedUser.getUserCode());

        map.put("id", authenticatedUser.getId());
        map.put("empId", authenticatedUser.getEmpId());
        map.put("userCode", authenticatedUser.getUserCode());
        map.put("roleId", authenticatedUser.getRole());
        map.put("roleName", roleName);
        map.put("token", jwtToken);
        map.put("posId", posId);
        map.put("userName", userName);
        map.put("msg", JavaConstant.success);

        return ResponseEntity.ok().body(map);
    }

}