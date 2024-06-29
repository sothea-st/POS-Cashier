package com.example.pos.connection1.feature.vendor;

import java.util.UUID;

import org.apache.el.stream.Stream;
import org.springframework.dao.DataIntegrityViolationException;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorServiceImp implements VendorService {
     private final VendorRepository vendorRepository;
     private String uuidNotFound = "Uuid has not been found .";

     /*
      * update vendor by uuid
      * required paramater uuid , VendorUpdateRequest
      */
     @Override
     public VendorResponse updateByUuid(String uuid, VendorUpdateRequest vendorUpdateRequest) {
          Vendor vendor = vendorRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, uuidNotFound));

          vendor.setVendorName(vendorUpdateRequest.vendorName());
          vendor.setAddress(vendorUpdateRequest.address());
          vendor.setContact(vendorUpdateRequest.contact());
          vendor.setEmail(vendorUpdateRequest.email());
          vendor.setWebsite(vendorUpdateRequest.website());
          try {
               vendorRepository.save(vendor);
          } catch (DataIntegrityViolationException e) {
               // for ignore when data has duplicate value but for only operation update
          }
      
          return mapToVendorResponse(vendor);
     }

     /*
      * delete vendor by uuid paramater
      */
     @Override
     public void delete(String uuid) {
          Vendor vendor = vendorRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, uuidNotFound));

          vendor.setDeleted(true);
          vendor.setStatus(false);
          vendorRepository.save(vendor);
     }

     /*
      * read vendor by uuid
      * required paramater uuid
      */
     @Override
     public VendorResponse readByUuid(String uuid) {
          // validation uuid
          Vendor vendor = vendorRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND, uuidNotFound));
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
                         HttpStatus.CONFLICT,
                         "The contact already exist.");
          }

          // validate email already exist
          if (vendorRepository.existsByEmail(vendorRequest.email())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT,
                         "The email already exist.");
          }

          long count = vendorRepository.count();
          Vendor vendor = new Vendor();
          vendor.setVendorName(vendorRequest.vendorName());
          vendor.setUuid(UUID.randomUUID().toString());
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
     public Page<VendorResponse> read(int pageSize, int pageNumber) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          Page<Vendor> pages = vendorRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
          return pages.map(this::mapToVendorResponse);
     }

     /*
      * helper method mapTpVendorResponse
      */
     private VendorResponse mapToVendorResponse(Vendor vendor) {
          return VendorResponse.builder()
                    .vendorName(vendor.getVendorName())
                    .address(vendor.getAddress())
                    .contact(vendor.getContact())
                    .email(vendor.getEmail())
                    .website(vendor.getWebsite())
                    .uuid(vendor.getUuid())
                    .vdCode(vendor.getVendorCode())
                    .build();
     }
}
