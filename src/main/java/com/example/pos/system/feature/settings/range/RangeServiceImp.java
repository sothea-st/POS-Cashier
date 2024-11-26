package com.example.pos.system.feature.settings.range;

import com.example.pos.system.domain.settings.Ranges;
import com.example.pos.system.domain.settings.Warehouse;
import com.example.pos.system.feature.settings.range.dto.RangeRequest;
import com.example.pos.system.feature.settings.range.dto.RangeResponse;
import com.example.pos.system.feature.settings.range.dto.WarehouseDetail;
import com.example.pos.system.feature.settings.warehouse.WarehouseRepository;
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
public class RangeServiceImp implements RangeService {
    // inject bean repository
    private final RangeRepository rangeRepository;
    private final WarehouseRepository warehouseRepository;

    // variable not found
    private final String warehouseIdNotFound = "Warehouse is not found with id : ";
    private final String nameEnAlreadyExisted = "RangeNameEn is already existed!";
    private final String nameKhAlreadyExisted = "RangeNameKh is already existed!!";
    private final String rangeIdNotFound = "Range is not found with id : ";

    @Override
    public JavaCollectionResponse<?> readByWarehouseId(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, warehouseIdNotFound + id));

        List<RangeResponse> data = warehouse.getRanges().stream()
                .map(this::mapToRangeResponse).toList();


        return JavaCollectionResponse.builder()
                .count(warehouse.getRanges().size())
                .data(data)
                .build();
    }

    /**
     * create rangeRequest
     * @param rangeRequest
     * @return
     */
    @Override
    public ResponseSuccess create(RangeRequest rangeRequest) {

        if( rangeRepository.existsByRangeNameEnAndStatusTrueAndIsDeletedFalse(rangeRequest.rangeNameEn()) ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,nameEnAlreadyExisted);
        }

        if( rangeRepository.existsByRangeNameKhAndStatusTrueAndIsDeletedFalseAndRangeNameKhIsNotNull(rangeRequest.rangeNameKh()) ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,nameKhAlreadyExisted);
        }

        // get warehouse
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(rangeRequest.warehouseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,warehouseIdNotFound+rangeRequest.warehouseId()));

        Ranges range = new Ranges();
        range.setRangeNameEn(rangeRequest.rangeNameEn());
        range.setRangeNameKh(rangeRequest.rangeNameKh());
        range.setWarehouse(warehouse);
        range.setCreatedBy(rangeRequest.createBy());
        range.setStatus(true);
        range.setIsDeleted(false);
        rangeRepository.save(range);

        return ResponseSuccess.builder().build();
    }

    /**
     * read range
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {
        long totalPageNumber = 0;
        List<RangeResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = rangeRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .sorted(Comparator.comparing(Ranges::getId).reversed())
                    .map(this::mapToRangeResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Ranges> pages = rangeRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToRangeResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * read ranges by id
     * @param id
     * @return
     */
    @Override
    public JavaResponse<?> readById(Integer id) {
        // validate range id exist or not
        Ranges ranges = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,rangeIdNotFound+id));

        return JavaResponse.builder()
                .data(mapToRangeResponse(ranges))
                .build();
    }

    /**
     * delete range by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess deleteById(Integer id) {
        // validate range id exist or not
        Ranges ranges = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,rangeIdNotFound+id));

        ranges.setStatus(false);
        ranges.setIsDeleted(true);
        rangeRepository.save(ranges);
        return ResponseSuccess.builder().build();
    }

    /**
     * update range by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess updateById(RangeRequest rangeRequest, Integer id) {

        // get warehouse
        Warehouse warehouse = warehouseRepository.findByIdAndStatusTrueAndIsDeletedFalse(rangeRequest.warehouseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,warehouseIdNotFound+rangeRequest.warehouseId()));

        // validate range id exist or not
        Ranges ranges = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,rangeIdNotFound+id));

        //Check if range name en is already existed?
        if (!ranges.getRangeNameEn().equals(rangeRequest.rangeNameEn()) &&
                rangeRepository.existsByRangeNameEnAndStatusTrueAndIsDeletedFalse(rangeRequest.rangeNameEn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExisted);
        }

        //Check if range name kh is already existed?
        if (ranges.getRangeNameKh() != null) {
            if (!ranges.getRangeNameKh().equals(rangeRequest.rangeNameKh()) &&
                    rangeRepository.existsByRangeNameKhAndStatusTrueAndIsDeletedFalseAndRangeNameKhIsNotNull(rangeRequest.rangeNameKh())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExisted);
            }
        }

        ranges.setRangeNameEn(rangeRequest.rangeNameEn());
        ranges.setRangeNameKh(rangeRequest.rangeNameKh());
        ranges.setCreatedBy(rangeRequest.createBy());
        ranges.setWarehouse(warehouse);
        rangeRepository.save(ranges);

        return ResponseSuccess.builder().build();
    }

    /**
     * search range
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue) {
        long totalPageNumber = 0;
        List<RangeResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = rangeRepository.searchByRangeNameEnOrRangeNameKh(searchValue).stream()
                    .sorted(Comparator.comparing(Ranges::getId).reversed())
                    .map(this::mapToRangeResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Ranges> pages = rangeRepository.searchByRangeNameEnOrRangeNameKh(pageRequest,searchValue);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToRangeResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }


    private RangeResponse mapToRangeResponse(Ranges ranges){
        return RangeResponse.builder()
                .id(ranges.getId())
                .rangeNameEn(ranges.getRangeNameEn())
                .rangeNameKh(ranges.getRangeNameKh())
                .warehouse(WarehouseDetail.builder()
                        .id(ranges.getWarehouse().getId())
                        .warehouseNameEn(ranges.getWarehouse().getWarehouseNameEn())
                        .warehouseNameKh(ranges.getWarehouse().getWarehouseNameKh())
                        .build())
                .build();
    }
}
