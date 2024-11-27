package com.example.pos.system.feature.settings.slot;

import com.example.pos.system.domain.settings.Ranges;
import com.example.pos.system.domain.settings.Slot;
import com.example.pos.system.feature.settings.range.RangeRepository;
import com.example.pos.system.feature.settings.slot.dto.RangeDetail;
import com.example.pos.system.feature.settings.slot.dto.SlotRequest;
import com.example.pos.system.feature.settings.slot.dto.SlotResponse;
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
public class SlotServiceImp implements SlotService{
    // inject bean repository
    private final SlotRepository slotRepository;

    private final RangeRepository rangeRepository;

    // variable not found
    private final String rangeIdNotFound = "Range not found with id : ";
    private final String slotNotFound = "Slot not found with id : ";
    private final String nameEnAlreadyExist = "SlotNameEn is already existed!";
    private final String nameKhAlreadyExist = "SlotNameKh is already existed!";

    /**
     * create slot
     * @param slotRequest
     * @return
     */
    @Override
    public ResponseSuccess create(SlotRequest slotRequest) {

        //validate range id exist ot not
        Ranges ranges = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(slotRequest.rangeId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,rangeIdNotFound+slotRequest.rangeId()));

//        if( slotRepository.existsBySlotNameEnAndStatusTrueAndIsDeletedFalse(slotRequest.slotNameEn()) ) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,nameEnAlreadyExist);
//        }

//        if( slotRepository.existsBySlotNameKhAndStatusTrueAndIsDeletedFalseAndSlotNameKhIsNotNull(slotRequest.slotNameKh()) ) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,nameKhAlreadyExist);
//        }

        Slot slot = new Slot();
        slot.setSlotNameEn(slotRequest.slotNameEn());
        slot.setSlotNameKh(slotRequest.slotNameKh());
        slot.setCreatedBy(slotRequest.createBy());
        slot.setStatus(true);
        slot.setIsDeleted(false);
        slot.setRanges(ranges);
        slotRepository.save(slot);

        return ResponseSuccess.builder().build();
    }

    /**
     * read slot
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {
        long totalPageNumber = 0;
        List<SlotResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = slotRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .sorted(Comparator.comparing(Slot::getId).reversed())
                    .map(this::mapToSlotResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Slot> pages = slotRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToSlotResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    /**
     * read slot by id
     * @param id
     * @return
     */
    @Override
    public JavaResponse<?> readById(Integer id) {

        // validate slot id exist or not
        Slot slot = slotRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,slotNotFound+id));

        return JavaResponse.builder()
                .data(mapToSlotResponse(slot))
                .build();
    }

    /**
     * delete slot by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess deleteById(Integer id) {
        // validate slot id exist or not
        Slot slot = slotRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,slotNotFound+id));
        slot.setStatus(false);
        slot.setIsDeleted(true);
        slotRepository.save(slot);
        return ResponseSuccess.builder().build();
    }

    /**
     * update by id
     * @param id
     * @return
     */
    @Override
    public ResponseSuccess updateById(SlotRequest slotRequest, Integer id) {
        // validate slot id exist or not
        Slot slot = slotRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,slotNotFound+id));

        //Check if nameEn is already existed?
//        if (!slot.getSlotNameEn().equals(slotRequest.slotNameEn()) &&
//                rangeRepository.existsByRangeNameEnAndStatusTrueAndIsDeletedFalse(slotRequest.slotNameEn())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, nameEnAlreadyExist);
//        }

        //Check if nameKh is already existed?
//        if (slot.getSlotNameKh() != null) {
//            if (!slot.getSlotNameKh().equals(slotRequest.slotNameKh()) &&
//                    rangeRepository.existsByRangeNameKhAndStatusTrueAndIsDeletedFalseAndRangeNameKhIsNotNull(slotRequest.slotNameKh())) {
//                throw new ResponseStatusException(HttpStatus.CONFLICT, nameKhAlreadyExist);
//            }
//        }

        //validate range id exist ot not
        Ranges ranges = rangeRepository.findByIdAndStatusTrueAndIsDeletedFalse(slotRequest.rangeId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,rangeIdNotFound+slotRequest.rangeId()));

        slot.setSlotNameKh(slotRequest.slotNameKh());
        slot.setSlotNameEn(slotRequest.slotNameEn());
        slot.setCreatedBy(slotRequest.createBy());
        slot.setRanges(ranges);
        slotRepository.save(slot);

        return ResponseSuccess.builder().build();
    }

    /**
     * search
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue) {
        long totalPageNumber = 0;
        List<SlotResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = slotRepository.searchBySlotNameEnOrSlotNameKh(searchValue).stream()
                    .sorted(Comparator.comparing(Slot::getId).reversed())
                    .map(this::mapToSlotResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");

            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<Slot> pages = slotRepository.searchBySlotNameEnOrSlotNameKh(pageRequest,searchValue);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToSlotResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    private SlotResponse mapToSlotResponse(Slot slot){
        return SlotResponse.builder()
                .id(slot.getId())
                .slotNameEn(slot.getSlotNameEn())
                .slotNameKh(slot.getSlotNameKh())
                .range(RangeDetail.builder()
                        .id(slot.getRanges().getId())
                        .rangeNameEn(slot.getRanges().getRangeNameEn())
                        .rangeNameKh(slot.getRanges().getRangeNameKh())
                        .build())
                .build();
    }
}
