package com.example.pos.system.feature.company_profile;

import com.example.pos.system.domain.company_profile.CompanyProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile,Integer> {

    Boolean existsByPhoneNumberAndCode(String phoneNumber,String code);
    Boolean existsByEmailAndCode(String email,String code);
    Page<CompanyProfile> findByStatusTrueAndIsDeletedFalseAndCode(PageRequest pageRequest,String code);
    Optional<CompanyProfile>  findByIdAndStatusTrueAndIsDeletedFalseAndCode(Integer id,String code);

    @Query(nativeQuery = true,value = "SELECT public.get_gazetteers_address(?);")
    String getFullAddressKh(String villageCode);

    @Query(nativeQuery = true,value = "SELECT public.get_gazetteers_address_en(?);")
    String getFullAddressEn(String villageCode);

    @Query("SELECT COUNT(c) FROM CompanyProfile c WHERE c.code = :code")
    Long countByCode(@Param("code") String code);


    @Query("SELECT p FROM CompanyProfile p " +
            "WHERE p.status = true " +
            "AND p.isDeleted = false " +
            "AND p.code = :code " +
            "AND (" +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.email) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.customerId) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.phoneNumber) LIKE LOWER(CONCAT('%', :name, '%'))" +
            ")"
    )
    Page<CompanyProfile> search(PageRequest pageRequest,@Param("name") String name,@Param("code") String code);


}
