package com.example.pos.system.feature.brand;
import com.example.pos.system.feature.brand.dto.BrandRequest;
import com.example.pos.system.feature.brand.dto.BrandRequestUpdate;
import com.example.pos.system.feature.brand.dto.BrandResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface BrandServices {
    
    //read brand by id
    BrandResponse readById (Integer id);

    //raed all brand
    JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber);

    //create new brand
    BrandResponse create(BrandRequest brandRequest);

    //update brand
    BrandResponse update(Integer id, BrandRequestUpdate brandRequestUpdate);

    //delete brand
    void deleteById(Integer id);

    //search brand
    JavaCollectionResponse<?> search (Integer pageSize, Integer pageNumber, String searchValue);
}
