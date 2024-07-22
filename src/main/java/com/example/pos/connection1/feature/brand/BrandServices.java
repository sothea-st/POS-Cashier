package com.example.pos.connection1.feature.brand;
import com.example.pos.connection1.feature.brand.dto.BrandRequest;
import com.example.pos.connection1.feature.brand.dto.BrandRequestUpdate;
import com.example.pos.connection1.feature.brand.dto.BrandResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface BrandServices {
    
    BrandResponse readById (Integer id);

    JavaCollectionResponse<?> read(int pageSize, int pageNumber);

    BrandResponse create(BrandRequest brandRequest);

    BrandResponse update(Integer id, BrandRequestUpdate brandRequestUpdate);

    void deleteById(Integer id);

    JavaCollectionResponse<?> search (int pageSize, int pageNumber, String searchValue);
}
