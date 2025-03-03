package com.example.pos.system.feature.company_profile;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.company_profile.dto.BusinessRequest;
import com.example.pos.system.feature.company_profile.dto.IndividualRequest;

public interface CompanyProfileService {

    /**
     * create individual
     * @param individualRequest
     * @return
     */
    ResponseSuccess createIndividual(IndividualRequest individualRequest);

    JavaCollectionResponse<?> read(Integer pageNumber,Integer pageSize,String code);

    JavaCollectionResponse<?> search(Integer pageNumber,Integer pageSize,String code,String searchValue);

    JavaResponse<?> readByIdAndCode(Integer id,String code);

    ResponseSuccess deleteByIdAndCode(Integer id,String code);

    ResponseSuccess createBusiness(BusinessRequest businessRequest);

    ResponseSuccess updateBusiness(Integer id , BusinessRequest businessRequest);

    ResponseSuccess updateIndividual(Integer id , IndividualRequest individualRequest);



}
