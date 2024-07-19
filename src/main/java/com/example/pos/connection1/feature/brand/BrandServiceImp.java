package com.example.pos.connection1.feature.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.entity.sourceData.Brand;
import com.example.pos.connection1.feature.brand.dto.BrandRequest;
import com.example.pos.connection1.feature.brand.dto.BrandRequestUpdate;
import com.example.pos.connection1.feature.brand.dto.BrandResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImp implements BrandServices{
    
    private final BrandRepository brandRepository;
    private String idNotFound = "Id has not been found .";

    @Override
    public BrandResponse readById(Integer id){
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        return mBrandResponse(brand);
    }

    @Override
    public JavaCollectionResponse<?> read(int pageSize, int pageNumber) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Brand> pages = brandRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
        
        List<BrandResponse> content = pages.getContent()
                            .stream()
                            .map(c->mBrandResponse(c))
                            .toList();
        return JavaCollectionResponse.builder()
                            .count(pages.getTotalElements())
                            .data(content)
                            .build();
    }

    @Override
    public BrandResponse create(BrandRequest brandRequest) {
        Brand brand =  new Brand();
        brand.setBrandNameEn(brandRequest.brandNameEn());
        brand.setBrandNameKh(brandRequest.brandNameKh());
        brand.setCreateBy(brandRequest.createBy());
        brand.setStatus(true);
        brand.setDeleted(false);
        brandRepository.save(brand);
        return mBrandResponse(brand);
    }

    @Override
    public BrandResponse update(Integer id, BrandRequestUpdate brandRequestUpdate) {
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        brand.setBrandNameEn(brandRequestUpdate.brandNameEn());
        brand.setBrandNameKh(brandRequestUpdate.brandNameKh());
        brandRepository.save(brand);
        return mBrandResponse(brand);
    }

    @Override
    public void deleteById(Integer id) {
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        brand.setStatus(false);
        brand.setDeleted(true);
        brandRepository.save(brand);
    }

    @Override
    public JavaCollectionResponse<?> search(int pageSize, int pageNumber, String searchValue) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Brand> pages = brandRepository.searchBrand(pageRequest, searchValue);
        
        List<BrandResponse> content = pages.getContent()
                            .stream()
                            .map(c->mBrandResponse(c))
                            .toList();
        return JavaCollectionResponse.builder()
                            .count(pages.getTotalElements())
                            .data(content)
                            .build();
    }

    private BrandResponse mBrandResponse(Brand brand){
        return BrandResponse.builder()
                .id(brand.getId())
                .brandNameEn(brand.getBrandNameEn())
                .brandNameKh(brand.getBrandNameKh())
                .createBy(brand.getCreateBy())
                .createDate(brand.getCreateDate())
                .status(brand.isStatus())
                .isDeleted(brand.isDeleted())
                .build();
    }
}
