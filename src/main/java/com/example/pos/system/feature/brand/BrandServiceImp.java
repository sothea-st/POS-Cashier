package com.example.pos.system.feature.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.system.domain.sourceData.Brand;
import com.example.pos.system.feature.brand.dto.BrandRequest;
import com.example.pos.system.feature.brand.dto.BrandRequestUpdate;
import com.example.pos.system.feature.brand.dto.BrandResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImp implements BrandServices{
    
    private final BrandRepository brandRepository;
    private String idNotFound = "Id has not been found .";
    private String nameAlreadyExisted = "The Brand Name is already existed.";

    //get brand by id
    @Override
    public BrandResponse readById(Integer id){
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        return mBrandResponse(brand);
    }

    //get list brand 
    @Override
    public JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber) {
        List<BrandResponse> data = null;

        if (pageNumber == null && pageSize == null) {
            data = brandRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .map(this::mBrandResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        }else{
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
    }

    //create brand
    @Override
    public BrandResponse create(BrandRequest brandRequest) {

        // validate name already exist
        if (brandRepository.existsByBrandNameEn(brandRequest.brandNameEn())) {
            throw new ResponseStatusException(
                      HttpStatus.CONFLICT,nameAlreadyExisted);
        }

        Brand brand =  new Brand();
        brand.setBrandNameEn(brandRequest.brandNameEn());
        brand.setBrandNameKh(brandRequest.brandNameKh());
        brand.setCreateBy(brandRequest.createBy());
        brand.setStatus(true);
        brand.setDeleted(false);
        brandRepository.save(brand);
        return mBrandResponse(brand);
    }

    //Update brand
    @Override
    public BrandResponse update(Integer id, BrandRequestUpdate brandRequestUpdate) {
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        if (!brandRequestUpdate.brandNameEn().equals(brand.getBrandNameEn())) {
            // validate name already exist
            if (brandRepository.existsByBrandNameEn(brandRequestUpdate.brandNameEn())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,nameAlreadyExisted);
            }
        }

        brand.setBrandNameEn(brandRequestUpdate.brandNameEn());
        brand.setBrandNameKh(brandRequestUpdate.brandNameKh());
        brandRepository.save(brand);
        return mBrandResponse(brand);
    }

    //delete brand
    @Override
    public void deleteById(Integer id) {
        Brand brand = brandRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        brand.setStatus(false);
        brand.setDeleted(true);
        brandRepository.save(brand);
    }

    //search brand by brand name
    @Override
    public JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue) {
        List<BrandResponse> data = null;

        System.out.println("pageNumber " + pageNumber);
        System.out.println("pageSize " + pageSize);

        if (pageNumber == null && pageSize == null) {
            data = brandRepository.searchBrand(searchValue).stream()
                    .map(this::mBrandResponse)
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        }else{
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
    }

    //response
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
