package com.example.pos.system.feature.settings.uom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.pos.system.domain.settings.Warehouse;
import com.example.pos.system.feature.settings.uom.dto.UomResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.settings.warehouse.dto.WarehouseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.system.domain.settings.Uom;
import com.example.pos.system.feature.settings.uom.dto.UomRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class UomServiceImp implements UomService{

    // inject bean
    private final UomRepository uomRepository;
    // variable not found
    private final String uomIdNotFound = "Uom not found with id : ";

    private final String nameEnAlreadyExist = "UomNameEn is already existed!";
    private final String nameKhAlreadyExist = "UomNameKh is already existed!";

    /**
     * read uom
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {

        long totalPageNumber = 0;
        List<UomResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = uomRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .sorted(Comparator.comparing(Uom::getId).reversed())
                    .map(this::mapToUomResponses)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Uom> pages = uomRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);


            // assign total pages
            totalPageNumber = pages.getTotalElements();


            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToUomResponses)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * search uom
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue) {
        long totalPageNumber = 0;
        List<UomResponse> data = new ArrayList<>();

        if (pageNumber == null && pageSize == null) {
            // map value to List
            data = uomRepository.searchByUomNameEnOrUomNameKh(searchValue).stream()
                    .sorted(Comparator.comparing(Uom::getId).reversed())
                    .map(this::mapToUomResponses)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        } else {
            System.out.println("fffffffffffffffffffffffffff");
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Uom> pages = uomRepository.searchByUomNameEnOrUomNameKh(pageRequest, searchValue);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToUomResponses)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * read uom By id
     * @param id
     * @return
     */
    @Override
    public JavaResponse<?> readById(Integer id) {

        // validate uom id exist or not
        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,uomIdNotFound+id));

        return JavaResponse.builder()
                .data(mapToUomResponses(uom))
                .build();

    }

    /**
     * create uomRequest
     * @param uomRequest
     * @return
     */
    @Override
    public ResponseSuccess create(UomRequest uomRequest) {

        String uomNameKh = uomRequest.uomNameKh();
        if (uomNameKh != null) {
            uomNameKh = uomNameKh.isEmpty() ? null : uomRequest.uomNameKh();
        }

        if (uomRepository.existsByUomNameEnAndStatusTrueAndIsDeletedFalse(uomRequest.uomNameEn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExist);
        }
        if (uomRepository.existsByUomNameKhAndStatusTrueAndIsDeletedFalseAndUomNameKhIsNotNull(uomRequest.uomNameKh()) && uomNameKh != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExist);
        }

        Uom uom = new Uom();
        uom.setUomNameEn(uomRequest.uomNameEn());
        uom.setUomNameKh(uomNameKh);
        uom.setCreatedBy(uomRequest.createdBy());
        uom.setStatus(true);
        uom.setIsDeleted(false);
        uomRepository.save(uom);
        return ResponseSuccess.builder().build();
    }

    /**
     * update uom
     * @param id
     * @param uomRequest
     * @return
     */
    @Override
    public ResponseSuccess update(UomRequest uomRequest, Integer id) {

        String uomNameKh = uomRequest.uomNameKh();
        if (uomNameKh != null) {
            uomNameKh = uomNameKh.isEmpty() ? null : uomRequest.uomNameKh();
        }

        // validate uom id exist or not
        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,uomIdNotFound+id));

        if (!uom.getUomNameEn().equals(uomRequest.uomNameEn()) &&
                uomRepository.existsByUomNameEnAndStatusTrueAndIsDeletedFalse(uomRequest.uomNameEn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExist);
        }
        if (!uom.getUomNameKh().equals(uomRequest.uomNameKh()) &&
                uomRepository.existsByUomNameKhAndStatusTrueAndIsDeletedFalseAndUomNameKhIsNotNull(uomRequest.uomNameKh()) && uomNameKh != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExist);
        }

        uom.setUomNameEn(uomRequest.uomNameEn());
        uom.setUomNameKh(uomNameKh);
        uom.setCreatedBy(uomRequest.createdBy());
        uomRepository.save(uom);
        return ResponseSuccess.builder().build();
    }

    /**
     * delete uom by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess delete(Integer id) {
        // validate uom id exist or not
        Uom uom = uomRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,uomIdNotFound+id));

        uom.setStatus(false);
        uom.setIsDeleted(true);
        uomRepository.save(uom);
        return ResponseSuccess.builder().build();
    }


    /**
     * Response
     * @param uom
     * @return
     */
    private UomResponse mapToUomResponses(Uom uom){
        System.out.println("hhhhhhhhhhhhhhhhh = " + uom.getUomNameEn());
        return UomResponse.builder()
                .id(uom.getId())
                .uomNameEn(uom.getUomNameEn())
                .uomNameKh(uom.getUomNameKh())
                .build();
    }
}
