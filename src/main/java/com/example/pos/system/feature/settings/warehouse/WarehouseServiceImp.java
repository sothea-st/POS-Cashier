package com.example.pos.system.feature.settings.warehouse;

import com.example.pos.system.domain.settings.Warehouse;
import com.example.pos.system.feature.settings.warehouse.dto.WarehouseRequest;
import com.example.pos.system.feature.settings.warehouse.dto.WarehouseResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImp implements WarehouseService {
    // inject bean repository
    private final WarehouseRepository warehouseRepository;
    // variable not found
    private final String nameEnAlreadyExist = "WarehouseNameEn is already existed!";
    private final String nameKhAlreadyExist = "WarehouseNameEn is already existed!";
    private final String warehouseIdNotFound = "Warehouse not found with id : ";

    /**
     * read warehouse
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {
        long totalPageNumber = 0;
        List<WarehouseResponse> data = new ArrayList<>();

        if (pageNumber == null && pageSize == null) {
            // map value to List
            data = warehouseRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .sorted(Comparator.comparing(Warehouse::getId).reversed())
                    .map(this::mapToWarehouseResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Warehouse> pages = warehouseRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToWarehouseResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * search warehouse
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue) {
        long totalPageNumber = 0;
        List<WarehouseResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = warehouseRepository.searchByWarehouseNameEnOrWarehouseNameKh(searchValue).stream()
                    .sorted(Comparator.comparing(Warehouse::getId).reversed())
                    .map(this::mapToWarehouseResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Warehouse> pages = warehouseRepository.searchByWarehouseNameEnOrWarehouseNameKh(pageRequest,searchValue);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToWarehouseResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * read warehouse By id
     * @param id
     * @return
     */
    @Override
    public JavaResponse<?> readById(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, warehouseIdNotFound + id));

        return JavaResponse.builder()
                .data(mapToWarehouseResponse(warehouse))
                .build();
    }

    /**
     * create warehouseRequest
     * @param warehouseRequest
     * @return
     */
    @Override
    public ResponseSuccess create(WarehouseRequest warehouseRequest) {

        String warehouseNameKh = warehouseRequest.warehouseNameKh();
        if (warehouseNameKh != null) {
            warehouseNameKh = warehouseNameKh.isEmpty() ? null : warehouseRequest.warehouseNameKh();
        }

        if (warehouseRepository.existsByWarehouseNameEnAndStatusTrueAndIsDeletedFalse(warehouseRequest.warehouseNameEn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExist);
        }
        if (warehouseRepository.existsByWarehouseNameKhAndStatusTrueAndIsDeletedFalseAndWarehouseNameKhIsNotNull(warehouseRequest.warehouseNameKh()) && warehouseNameKh != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExist);
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseNameEn(warehouseRequest.warehouseNameEn());
        warehouse.setWarehouseNameKh(warehouseNameKh);
        warehouse.setCreatedBy(warehouseRequest.createBy());
        warehouse.setStatus(true);
        warehouse.setIsDeleted(false);
        warehouseRepository.save(warehouse);
        return ResponseSuccess.builder().build();
    }

    /**
     * update warehouseRequest
     * @param id
     * @param warehouseRequest
     * @return
     */
    @Override
    public ResponseSuccess update(WarehouseRequest warehouseRequest, Integer id) {

        String warehouseNameKh = warehouseRequest.warehouseNameKh();
        if (warehouseNameKh != null) {
            warehouseNameKh = warehouseNameKh.isEmpty() ? null : warehouseRequest.warehouseNameKh();
        }

        // validate warehouse id exist or not
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, warehouseIdNotFound + id));

        //Check if warehouse name en is already existed?
        if (!warehouse.getWarehouseNameEn().equals(warehouseRequest.warehouseNameEn()) &&
                warehouseRepository.existsByWarehouseNameEnAndStatusTrueAndIsDeletedFalse(warehouseRequest.warehouseNameEn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExist);
        }

        //Check if warehouse name kh is already existed?
        if (warehouse.getWarehouseNameKh() != null) {
            if (!warehouse.getWarehouseNameKh().equals(warehouseRequest.warehouseNameKh()) &&
                    warehouseRepository.existsByWarehouseNameKhAndStatusTrueAndIsDeletedFalseAndWarehouseNameKhIsNotNull(warehouseRequest.warehouseNameKh()) && warehouseNameKh != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExist);
            }
        }

        warehouse.setWarehouseNameEn(warehouseRequest.warehouseNameEn());
        warehouse.setWarehouseNameKh(warehouseNameKh);
        warehouse.setCreatedBy(warehouseRequest.createBy());
        warehouseRepository.save(warehouse);

        return ResponseSuccess.builder().build();
    }

    /**
     * delete warehouse by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess delete(Integer id) {
        // validate warehouse id exist or not
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, warehouseIdNotFound + id));

        warehouse.setStatus(false);
        warehouse.setIsDeleted(true);
        warehouseRepository.save(warehouse);
        return ResponseSuccess.builder().build();
    }

    private WarehouseResponse mapToWarehouseResponse(Warehouse warehouse) {
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .warehouseNameEn(warehouse.getWarehouseNameEn())
                .warehouseNameKh(warehouse.getWarehouseNameKh())
                .build();
    }
}
