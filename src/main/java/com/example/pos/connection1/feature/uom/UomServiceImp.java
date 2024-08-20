package com.example.pos.connection1.feature.uom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.connection1.entity.Uom;
import com.example.pos.connection1.feature.uom.dto.UomRequest;
import com.example.pos.connection1.feature.uom.dto.UomResponse;
import com.example.pos.connection1.feature.uom.dto.UomUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class UomServiceImp implements UomService{

    private final UomRepository uomRepository;
    private String idNotFound = "Id has not been found .";
    private String nameAlreadyExisted = "The UOM Name is already existed.";

    /*
      * read vendor by id
      * required paramater id
    */
    @Override
    public UomResponse readById(Integer id) {
        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        return mapTUomResponse(uom);
    }

    /*
      * create new uom 
      * required paramater uomrequest
    */
    @Override
    public UomResponse create(UomRequest uomRequest){

        // validate name already exist
        if (uomRepository.existsByNameEn(uomRequest.nameEn())) {
              throw new ResponseStatusException(
                        HttpStatus.CONFLICT,nameAlreadyExisted);
        }

        Uom uom = new Uom();
        uom.setNameEn(uomRequest.nameEn());
        uom.setNameKh(uomRequest.nameKh());
        uom.setStatus(true);
        uom.setIsDeleted(false);
        uomRepository.save(uom);

        return mapTUomResponse(uom);
    }

    /*
      * update uom by id
      * required paramater id , UomUpdateRequest
    */
    @Override
    public UomResponse updateById(Integer id, UomUpdateRequest uomUpdateRequest){

        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        if (!uomUpdateRequest.nameEn().equals(uom.getNameEn())) {
            // validate name already exist
            if (uomRepository.existsByNameEn(uomUpdateRequest.nameEn())) {
                      throw new ResponseStatusException(HttpStatus.CONFLICT,nameAlreadyExisted);
            }
        }

        uom.setNameEn(uomUpdateRequest.nameEn());
        uom.setNameKh(uomUpdateRequest.nameKh());
        uom.setStatus(true);
        uom.setIsDeleted(false);
        uomRepository.save(uom);
        return mapTUomResponse(uom);
    }
    
    /*
      * read all uom
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
      * value was given from controller
    */
    @Override
    public JavaCollectionResponse<?> read (Integer pageSize, Integer pageNumber){

        List<UomResponse> data = null;

        if(pageNumber == null && pageSize == null){
          data = uomRepository.findByStatusTrueAndIsDeletedFalse().stream()
                          .map(this::mapTUomResponse)
                          .toList();
          return JavaCollectionResponse.builder()
                          .count(data.size())
                          .data(data)
                          .build();
        }else{
          Sort sortById = Sort.by(Sort.Direction.DESC, "id"); // sort by id DESC 
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize , sortById); // pageNumber start:0,1,2,3...  pageSize:10  => 1 page has 10 items
          Page<Uom> pages = uomRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

          List<UomResponse> content = pages.getContent()
                          .stream()
                          .map(c->mapTUomResponse(c))
                          .toList();
                      
          return JavaCollectionResponse.builder()
                          .count(pages.getTotalElements())
                          .data(content)
                          .build();
        }
    }

    /*
      * delete uom by id
    */
    @Override
    public void deleteById(Integer id){
        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
            uom.setStatus(false);
            uom.setIsDeleted(true);
        uomRepository.save(uom);
    }

    /*
      * helper method mapTUomResponse
    */
    private UomResponse mapTUomResponse(Uom uom){
        return UomResponse.builder()
            .id(uom.getId())
            .nameEn(uom.getNameEn())
            .nameKh(uom.getNameKh())
            .status(uom.getStatus())
            .isDeleted(uom.getIsDeleted())
            .build();
    }

    /*
      * read search uom
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0
      * value was given from controller
    */
    @Override
    public JavaCollectionResponse<?> search(Integer pageSize,Integer pageNumber, String valueSearch) {

       List<UomResponse> data = null;

       System.out.println("data :" + data);

        if(pageNumber == null && pageSize == null){
          data = uomRepository.searchByNameEn(valueSearch).stream()
                          .map(this::mapTUomResponse)
                          .toList();
          return JavaCollectionResponse.builder()
                          .count(data.size())
                          .data(data)
                          .build();
        }else{

          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);
          Page<Uom> pages = uomRepository.searchByNameEn(pageRequest,valueSearch);

          List<UomResponse> content = pages.getContent()
                    .stream()
                    .map(c->mapTUomResponse(c))
                    .toList();

        return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
        }
    }
}
