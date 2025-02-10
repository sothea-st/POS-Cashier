package com.example.pos.system.feature.attribute;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.pos.system.domain.settings.Attribute;
import com.example.pos.system.feature.attribute.dto.AttributeRequest;
import com.example.pos.system.feature.attribute.dto.AttributeResponse;
import com.example.pos.system.feature.attribute.dto.AttributeUpdateRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImp implements AttributeService {

     private final AttributeRepository attributeRepository;
     private String idNotFound = "Id has not been found .";
     private String nameAlreadyExisted = "The Attribute Name is already existed.";

     /*
      * read attribute by id
      * required paramater id
      */
     @Override
     public AttributeResponse readById(Integer id) {
          Attribute attribute = attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
          return mAttributeResponse(attribute);
     }

     /*
      * read all attribute
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
      * value was given from controller
      */
     @Override
     public JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber) {
          List<AttributeResponse> data = null;

          if (pageNumber == null && pageSize == null) {
               data = attributeRepository.findByStatusTrueAndIsDeletedFalse().stream()
                         .map(this::mAttributeResponse)
                         .toList();
               return JavaCollectionResponse.builder()
                         .count(data.size())
                         .data(data)
                         .build();
          }else{
               Sort sortById = Sort.by(Sort.Direction.DESC, "id"); // sort by id DESC
               PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById); // pageNumber start:0,1,2,3...
                                                                                     // pageSize:10
                                                                                     // => 1 page has 10 items
               Page<Attribute> pages = attributeRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

               List<AttributeResponse> content = pages.getContent()
                         .stream()
                         .map(c -> mAttributeResponse(c))
                         .toList();

               return JavaCollectionResponse.builder()
                         .count(pages.getTotalElements())
                         .data(content)
                         .build();

          }
     }

     /*
      * create new attribute
      * required paramater attributeRequest
      */
     @Override
     public AttributeResponse create(AttributeRequest attributeRequest) {

          // validate name already exist
          if (attributeRepository.existsByAttrNameEn(attributeRequest.attrNameEn())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT,nameAlreadyExisted);
          }

          Attribute attribute = new Attribute();
          attribute.setAttrNameEn(attributeRequest.attrNameEn());
          attribute.setAttrNameKh(attributeRequest.attrNameKh());
          attribute.setStatus(true);
          attribute.setIsDeleted(false);
          attributeRepository.save(attribute);
          return mAttributeResponse(attribute);
     }

     /*
      * update attribute by id
      * required paramater id , attributeUpdateRequest
      */
     @Override
     public AttributeResponse updateById(Integer id, AttributeUpdateRequest attributeUpdateRequest) {
          Attribute attribute = attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

          if (!attributeUpdateRequest.attrNameEn().equals(attribute.getAttrNameEn())) {
               // validate name already exist
               if (attributeRepository.existsByAttrNameEn(attributeUpdateRequest.attrNameEn())) {
                         throw new ResponseStatusException(HttpStatus.CONFLICT,nameAlreadyExisted);
               }
          }

          attribute.setAttrNameEn(attributeUpdateRequest.attrNameEn());
          attribute.setAttrNameKh(attributeUpdateRequest.attrNameKh());
          attribute.setStatus(true);
          attribute.setIsDeleted(false);
          attributeRepository.save(attribute);
          return mAttributeResponse(attribute);
     }

     /*
      * delete attribute by id
      */
     @Override
     public void deleteById(Integer id) {
          Attribute attribute = attributeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

          attribute.setStatus(false);
          attribute.setIsDeleted(true);
          attributeRepository.save(attribute);
     }

     /*
      * helper method mAttributeResponse
      */
     private AttributeResponse mAttributeResponse(Attribute attribute) {
          return AttributeResponse.builder()
                    .id(attribute.getId())
                    .attrNameEn(attribute.getAttrNameEn())
                    .attrNameKh(attribute.getAttrNameKh())
                    .status(attribute.getStatus())
                    .isDeleted(attribute.getIsDeleted())
                    .build();
     }

     /*
      * read search attribute
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
      * value was given from controller
      */
     @Override
     public JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String valueSearch) {
          List<AttributeResponse> data = null;
          if (pageNumber == null && pageSize == null) {
               data = attributeRepository.searchByNameEnOrNameKh(null,valueSearch).stream()
                         .map(this::mAttributeResponse)
                         .toList();
               return JavaCollectionResponse.builder()
                         .count(data.size())
                         .data(data)
                         .build();
          }else{
               Sort sortById = Sort.by(Sort.Direction.DESC, "id"); // sort by id DESC
               PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById); // pageNumber start:0,1,2,3...
                                                                                     // pageSize:10
                                                                                     // => 1 page has 10 items
               Page<Attribute> pages = attributeRepository.searchByNameEnOrNameKh(pageRequest, valueSearch);

               List<AttributeResponse> content = pages.getContent()
                         .stream()
                         .map(c -> mAttributeResponse(c))
                         .toList();

               return JavaCollectionResponse.builder()
                         .count(pages.getTotalElements())
                         .data(content)
                         .build();
          }
          
     }
}
