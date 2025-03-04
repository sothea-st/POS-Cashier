package com.example.pos.system.feature.company_profile;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.User;
import com.example.pos.system.domain.company_profile.CompanyProfile;
import com.example.pos.system.domain.promotion.Promotion;
import com.example.pos.system.feature.company_profile.dto.BusinessRequest;
import com.example.pos.system.feature.company_profile.dto.BusinessResponse;
import com.example.pos.system.feature.company_profile.dto.IndividualRequest;
import com.example.pos.system.feature.company_profile.dto.IndividualResponse;
import com.example.pos.system.feature.promotion.dto.response.PromotionResponse;
import com.example.pos.system.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyProfileServiceImp implements CompanyProfileService {
    // inject bean repository
    private final CompanyProfileRepository companyProfileRepository;
    private final UserRepository userRepository;

    // variable not found
    private String userNotFound = "User not found with  id : ";
    private String phoneNumberAlreadyExist = "Phone number already exist with : ";
    private String emailAlreadyExist = "email already exist with : ";
    private String individualNotFound = "Individual not found with id : ";
    private String businessNotFound = "Business not found with id : ";

    @Override
    public ResponseSuccess createIndividual(IndividualRequest individualRequest) {

        // validate age 18
        JavaConstant.validateAge(individualRequest.dob());

        if (companyProfileRepository.existsByPhoneNumberAndCode(individualRequest.phoneNumber(), "Individual")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, phoneNumberAlreadyExist + individualRequest.phoneNumber());
        }

        if (companyProfileRepository.existsByEmailAndCode(individualRequest.email(), "Individual")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, emailAlreadyExist + individualRequest.email());
        }

        // check user
        User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(individualRequest.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFound + individualRequest.createdBy()));


        CompanyProfile companyProfile = new CompanyProfile();

        // call updateAndSave
        updateAndSave(companyProfile, user, individualRequest);
        return ResponseSuccess.builder().build();
    }

    @Override
    public ResponseSuccess updateIndividual(Integer id, IndividualRequest individualRequest) {

        // validate age 18
        JavaConstant.validateAge(individualRequest.dob());

        CompanyProfile companyProfile = companyProfileRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(id, "Individual")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, individualNotFound + id));

        if (
                !companyProfile.getPhoneNumber().equals(individualRequest.phoneNumber()) &&
                        companyProfileRepository.existsByPhoneNumberAndCode(individualRequest.phoneNumber(), "Individual")
        ) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, phoneNumberAlreadyExist + individualRequest.phoneNumber());
        }

        if (
                !companyProfile.getEmail().equals(individualRequest.email()) &&
                        companyProfileRepository.existsByEmailAndCode(individualRequest.email(), "Individual")
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, emailAlreadyExist + individualRequest.email());
        }

        // check user
        User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(individualRequest.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFound + individualRequest.createdBy()));


        // call updateAndSave
        updateAndSave(companyProfile, user, individualRequest);


        return ResponseSuccess.builder().build();
    }

    @Override
    public ResponseSuccess createBusiness(BusinessRequest businessRequest) {

        if (companyProfileRepository.existsByPhoneNumberAndCode(businessRequest.phoneNumber(), "Business")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, phoneNumberAlreadyExist + businessRequest.phoneNumber());
        }

        if (companyProfileRepository.existsByEmailAndCode(businessRequest.email(), "Business")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, emailAlreadyExist + businessRequest.email());
        }
        // check user
        User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(businessRequest.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFound + businessRequest.createdBy()));


        CompanyProfile companyProfile = new CompanyProfile();
        // call updateAndSave
        updateAndSave(companyProfile, user, businessRequest);

        return ResponseSuccess.builder().build();
    }

    @Override
    public ResponseSuccess updateBusiness(Integer id, BusinessRequest businessRequest) {

        CompanyProfile companyProfile = companyProfileRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(id, "Business")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, individualNotFound + id));

        if (
                !companyProfile.getPhoneNumber().equals(businessRequest.phoneNumber()) &&
                        companyProfileRepository.existsByPhoneNumberAndCode(businessRequest.phoneNumber(), "Business")
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, phoneNumberAlreadyExist + businessRequest.phoneNumber());
        }

        if (
                !companyProfile.getEmail().equals(businessRequest.email()) &&
                        companyProfileRepository.existsByEmailAndCode(businessRequest.email(), "Business")
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, emailAlreadyExist + businessRequest.email());
        }
        // check user
        User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(businessRequest.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFound + businessRequest.createdBy()));


        // call updateAndSave
        updateAndSave(companyProfile, user, businessRequest);

        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String code) {
        if (!List.of("Individual", "Business").contains(code.trim())) {
            throw new IllegalArgumentException("Invalid code: " + code);
        }
        return fetchData(pageNumber, pageSize, code);
    }

    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String code, String searchValue) {
        List<?> data = new ArrayList<>();
        long totalElements = 0;


        boolean isPagination = (pageNumber != null && pageSize != null);
        if (isPagination) {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            Page<CompanyProfile> pages = companyProfileRepository.search(pageRequest, searchValue, code);

            data = pages.stream()
                    .map(code.equals("Individual") ? this::mapToIndividualResponse : this::mapTopBusinessResponse)
                    .toList();

            totalElements = pages.getTotalElements();
        } else {
            data = companyProfileRepository.search(null, searchValue, code).stream()
                    .map(code.equals("Individual") ? this::mapToIndividualResponse : this::mapTopBusinessResponse)
                    .toList();

            totalElements = data.size();
        }


        return JavaCollectionResponse.builder()
                .data(data)
                .count(totalElements)
                .build();

    }


    @Override
    public JavaResponse<?> readByIdAndCode(Integer id, String code) {
        if (!List.of("Individual", "Business").contains(code.trim())) {
            throw new IllegalArgumentException("Invalid code: " + code);
        }
        return fetchData(id, code);
    }

    @Override
    public ResponseSuccess deleteByIdAndCode(Integer id, String code) {

        if (!List.of("Individual", "Business").contains(code)) {
            throw new IllegalArgumentException("Invalid code: " + code);
        }

        CompanyProfile companyProfile = companyProfileRepository
                .findByIdAndStatusTrueAndIsDeletedFalseAndCode(id, code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        (code.trim().equals("Individual") ? individualNotFound : businessNotFound) + id));

        companyProfile.setStatus(false);
        companyProfile.setIsDeleted(true);
        companyProfileRepository.save(companyProfile);

        return ResponseSuccess.builder().build();
    }


    private void updateAndSave(CompanyProfile companyProfile, User user, BusinessRequest businessRequest) {
        companyProfile.setStatus(true);
        companyProfile.setIsDeleted(false);
        companyProfile.setCreatedBy(user);
        companyProfile.setCode("Business");
        companyProfile.setCustomerName(businessRequest.customerName());
        companyProfile.setCompanyName(businessRequest.companyName());
        companyProfile.setPhoneNumber(businessRequest.phoneNumber());
        companyProfile.setEmail(businessRequest.email());
        companyProfile.setVatNumber(businessRequest.vatNumber());
        companyProfile.setHome(businessRequest.home());
        companyProfile.setStreet(businessRequest.street());
        companyProfile.setProvince(businessRequest.province());
        companyProfile.setDistrict(businessRequest.district());
        companyProfile.setCommune(businessRequest.commune());
        companyProfile.setVillage(businessRequest.village());
        companyProfileRepository.save(companyProfile);
    }


    private void updateAndSave(CompanyProfile companyProfile, User user, IndividualRequest individualRequest) {
        companyProfile.setStatus(true);
        companyProfile.setIsDeleted(false);
        companyProfile.setCreatedBy(user);
        companyProfile.setCode("Individual");
        companyProfile.setCustomerId(generateCustomerId());

        companyProfile.setFirstName(individualRequest.firstName());
        companyProfile.setLastName(individualRequest.lastName());
        companyProfile.setGender(individualRequest.gender());
        companyProfile.setNationality(individualRequest.nationality());
        companyProfile.setPhoneNumber(individualRequest.phoneNumber());

        companyProfile.setProfileImage(individualRequest.profileName());
        companyProfile.setEmail(individualRequest.email());
        companyProfile.setDob(LocalDate.parse(individualRequest.dob()));
        companyProfile.setHome(individualRequest.home());
        companyProfile.setLat(individualRequest.lat());
        companyProfile.setLng(individualRequest.lng());
        companyProfile.setStreet(individualRequest.street());
        companyProfile.setProvince(individualRequest.province());
        companyProfile.setDistrict(individualRequest.district());
        companyProfile.setCommune(individualRequest.commune());
        companyProfile.setVillage(individualRequest.village());
        companyProfile.setProfileImage(individualRequest.profileName());
        companyProfileRepository.save(companyProfile);
    }

    private String generateCustomerId() {
        long count = companyProfileRepository.countByCode("Individual") + 1;
        return String.format("CID-%06d", count);
    }


    private JavaResponse<?> fetchData(Integer id, String code) {
        CompanyProfile companyProfile = companyProfileRepository
                .findByIdAndStatusTrueAndIsDeletedFalseAndCode(id, code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        (code.equals("Individual") ? individualNotFound : businessNotFound) + id));

        Object response = code.equals("Individual")
                ? mapToIndividualResponse(companyProfile)
                : mapTopBusinessResponse(companyProfile);

        return JavaResponse.builder().data(response).build();
    }

    private JavaCollectionResponse<?> fetchData(Integer pageNumber, Integer pageSize, String code) {
        boolean isPagination = (pageNumber != null && pageSize != null);
        List<?> data;
        long totalElements;

        if (isPagination) {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            Page<CompanyProfile> pages = companyProfileRepository.findByStatusTrueAndIsDeletedFalseAndCode(pageRequest, code);

            data = pages.stream()
                    .map(code.equals("Individual") ? this::mapToIndividualResponse : this::mapTopBusinessResponse)
                    .toList();

            totalElements = pages.getTotalElements();
        } else {
            data = companyProfileRepository.findByStatusTrueAndIsDeletedFalseAndCode(null, code).stream()
                    .map(code.equals("Individual") ? this::mapToIndividualResponse : this::mapTopBusinessResponse)
                    .toList();

            totalElements = data.size();
        }

        return JavaCollectionResponse.builder()
                .data(data)
                .count(totalElements)
                .build();
    }

    private BusinessResponse mapTopBusinessResponse(CompanyProfile companyProfile) {
        return BusinessResponse.builder()
                .customerName(companyProfile.getCustomerName())
                .companyName(companyProfile.getCompanyName())
                .phoneNumber(companyProfile.getPhoneNumber())
                .email(companyProfile.getEmail())
                .vatNumber(companyProfile.getVatNumber())
                .home(companyProfile.getHome())
                .street(companyProfile.getStreet())
                .province(companyProfile.getProvince())
                .district(companyProfile.getDistrict())
                .commune(companyProfile.getCommune())
                .village(companyProfile.getVillage())
                .fullAddressKh(companyProfileRepository.getFullAddressKh(companyProfile.getVillage()))
                .fullAddressEn(companyProfileRepository.getFullAddressEn(companyProfile.getVillage()))
                .build();

    }

    private IndividualResponse mapToIndividualResponse(CompanyProfile companyProfile) {

        return IndividualResponse.builder()
                .id(companyProfile.getId())
                .customerId(companyProfile.getCustomerId())
                .firstName(companyProfile.getFirstName())
                .lastName(companyProfile.getLastName())
                .gender(companyProfile.getGender())
                .nationality(companyProfile.getNationality())
                .phoneNumber(companyProfile.getPhoneNumber())
                .email(companyProfile.getEmail())
                .dob(companyProfile.getDob().toString())
                .home(companyProfile.getHome())
                .lat(companyProfile.getLat())
                .lng(companyProfile.getLng())
                .street(companyProfile.getStreet())
                .province(companyProfile.getProvince())
                .district(companyProfile.getDistrict())
                .commune(companyProfile.getCommune())
                .village(companyProfile.getVillage())
                .profileImage(companyProfile.getProfileImage())
                .fullAddressKh(companyProfileRepository.getFullAddressKh(companyProfile.getVillage()))
                .fullAddressEn(companyProfileRepository.getFullAddressEn(companyProfile.getVillage()))
                .createdDate(JavaConstant.convertCreatedDateToTime(companyProfile.getCreatedDate().toString()))
                .build();
    }

}
