package com.example.pos.connection1.feature.vendor;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.connection1.entity.Vendor;
import com.example.pos.connection1.feature.vendor.dto.VendorRequest;
import com.example.pos.connection1.feature.vendor.dto.VendorResponse;
import com.example.pos.connection1.feature.vendor.dto.VendorUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorServiceImp implements VendorService {
     private final VendorRepository vendorRepository; // inject bean VendorRespository
     private String idNotFound = "Id has not been found .";
     private String contactAlreadyExist = "The contact already exist.";
     private String emailAlreadyExist = "The email already exist.";
     /*
      * update vendor by uuid
      * required paramater uuid , VendorUpdateRequest
      */
     @Override
     public VendorResponse updateByUuid(int id, VendorUpdateRequest vendorUpdateRequest) {
          // find vendor by uuid and it will validate if uuid wrong
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, idNotFound));

          if (!vendorUpdateRequest.contact().equals(vendor.getContact())) {
               // validate contact already exist
               if (vendorRepository.existsByContact(vendorUpdateRequest.contact())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,contactAlreadyExist);
               }
          }

          if (!vendorUpdateRequest.email().equals(vendor.getEmail())) {
               // validate email already exist
               if (vendorRepository.existsByEmail(vendorUpdateRequest.email())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,emailAlreadyExist);
               }
          }

          vendor.setVendorName(vendorUpdateRequest.vendorName());
          vendor.setAddress(vendorUpdateRequest.address());
          vendor.setEmail(vendorUpdateRequest.email());
          vendor.setWebsite(vendorUpdateRequest.website());
          vendorRepository.save(vendor);
          return mapToVendorResponse(vendor);
     }

     /*
      * delete vendor by uuid paramater
      */
     @Override
     public void delete(int id) {
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, idNotFound));

          vendor.setDeleted(true);
          vendor.setStatus(false);
          vendorRepository.save(vendor);
     }

     /*
      * read vendor by uuid
      * required paramater uuid
      */
     @Override
     public VendorResponse readByUuid(int id) {
          // validation uuid
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, idNotFound));
          return mapToVendorResponse(vendor);
     }

     /*
      * create new vendor
      * required paramater VendorRequest
      */
     @Override
     public VendorResponse create(VendorRequest vendorRequest) {

          // validate contact already exist
          if (vendorRepository.existsByContact(vendorRequest.contact())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT,contactAlreadyExist);
          }

          // validate email already exist
          if (vendorRepository.existsByEmail(vendorRequest.email())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT,emailAlreadyExist);
          }

          long count = vendorRepository.count();
          Vendor vendor = new Vendor();
          vendor.setVendorName(vendorRequest.vendorName());
          vendor.setUuid(UUID.randomUUID().toString()); // uuid random value
          vendor.setAddress(vendorRequest.address());
          vendor.setContact(vendorRequest.contact());
          vendor.setEmail(vendorRequest.email());
          vendor.setWebsite(vendorRequest.website());
          vendor.setVendorCode(getVDCode(count));
          vendor.setCreateBy(vendorRequest.createBy());
          vendorRepository.save(vendor);

          return VendorResponse.builder()
                    .vendorName(vendor.getVendorName())
                    .uuid(vendor.getUuid())
                    .address(vendor.getAddress())
                    .contact(vendor.getContact())
                    .email(vendor.getEmail())
                    .website(vendor.getWebsite())
                    .vdCode(vendor.getVendorCode())
                    .build();
     }

     String getVDCode(long count) {
          String vendorCode = "VD-";
          count++;
          if (count < 10) {
               vendorCode += "000" + count;
          } else if (count < 100) {
               vendorCode += "00" + count;
          } else if (count < 1000) {
               vendorCode += "0" + count;
          } else if (count < 10000) {
               vendorCode += "" + count;
          } else {
               vendorCode += "" + count;
          }
          return vendorCode;
     }

     /*
      * read all vendor
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
      * value was given from controller
      */
     @Override
     public JavaCollectionResponse<?> read(int pageSize, int pageNumber) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id"); // sort by id DESC 
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById); // pageNumber start:0,1,2,3...  pageSize:10  => 1 page has 10 items
          Page<Vendor> pages = vendorRepository.findByStatusTrueAndIsDeletedFalse(pageRequest); 

          List<VendorResponse> content = pages.getContent()
                    .stream()
                    .map(this::mapToVendorResponse)
                    .toList();

          return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
     }

     /*
      * helper method mapTpVendorResponse
      */
     private VendorResponse mapToVendorResponse(Vendor vendor) {
          return VendorResponse.builder()
                    .address(vendor.getAddress())
                    .contact(vendor.getContact())
                    .email(vendor.getEmail())
                    .website(vendor.getWebsite())
                    .uuid(vendor.getUuid())
                    .vdCode(vendor.getVendorCode())
                    .id(vendor.getId())
                    .vendorName(vendor.getVendorName())
                    .build();
     }

     @Override
     public JavaCollectionResponse<?> search(int pageSize, int pageNumber, String searchValue) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          Page<Vendor> pages = vendorRepository.findByVendorName(pageRequest,searchValue);

          List<VendorResponse> content = pages.getContent()
                              .stream()
                              .map(c->mapToVendorResponse(c))
                              .toList();
          
          return JavaCollectionResponse.builder()
                              .count(pages.getTotalElements())
                              .data(content)
                              .build();
     }
}
