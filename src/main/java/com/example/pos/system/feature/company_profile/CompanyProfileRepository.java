package com.example.pos.system.feature.company_profile;

import com.example.pos.system.domain.company_profile.CompanyProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}
