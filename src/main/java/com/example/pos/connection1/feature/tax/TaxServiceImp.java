package com.example.pos.connection1.feature.tax;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.entity.sourceData.TaxProduct;
import com.example.pos.connection1.feature.tax.dto.TaxRequest;
import com.example.pos.connection1.feature.tax.dto.TaxRequestUpdate;
import com.example.pos.connection1.feature.tax.dto.TaxResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class TaxServiceImp implements TaxService {

    private final TaxRepository taxRepository;
    private String idNotFound = "Id has not been found .";
    private String nameAlreadyExisted = "The Tax Name is already existed.";

    // read tax by id
    @Override
    public TaxResponse readById(Integer id) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        return mTaxResponse(taxProduct);
    }

    // read list tax
    @Override
    public JavaCollectionResponse<?> readList(Integer pageSize, Integer pageNumber) {
        List<TaxResponse> data = null;

        if (pageNumber == null && pageSize == null) {
            data = taxRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .map(this::mTaxResponse)
                    .toList();

            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<TaxProduct> pages = taxRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

            List<TaxResponse> content = pages.getContent()
                    .stream()
                    .map(c -> mTaxResponse(c))
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
        }
    }

    // create new tax
    @Override
    public TaxResponse create(TaxRequest taxRequest) {

        // validate name already exist
        if (taxRepository.existsByTaxName(taxRequest.taxName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, nameAlreadyExisted);
        }

        TaxProduct taxProduct = new TaxProduct();
        taxProduct.setTaxName(taxRequest.taxName());
        taxProduct.setRateTax(taxRequest.rateTax());
        taxProduct.setCreateBy(taxRequest.createBy());
        taxRepository.save(taxProduct);
        return mTaxResponse(taxProduct);
    }

    // update tax by id
    @Override
    public TaxResponse update(Integer id, TaxRequestUpdate taxRequestUpdate) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        if (!taxRequestUpdate.taxName().equals(taxProduct.getTaxName())) {
            // validate name already exist
            if (taxRepository.existsByTaxName(taxRequestUpdate.taxName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, nameAlreadyExisted);
            }
        }

        taxProduct.setTaxName(taxRequestUpdate.taxName());
        taxProduct.setRateTax(taxRequestUpdate.rateTax());
        taxRepository.save(taxProduct);
        return mTaxResponse(taxProduct);
    }

    // delete tax by id
    @Override
    public void deleteById(Integer id) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        taxProduct.setStatus(false);
        taxProduct.setDeleted(true);
        taxRepository.save(taxProduct);
    }

    // search tax
    @Override
    public JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue) {
        List<TaxResponse> data = null;

        if (pageNumber == null && pageSize == null) {
            data = taxRepository.searchTax(searchValue).stream()
                    .map(this::mTaxResponse)
                    .toList();

            return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
        } else {

            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<TaxProduct> pages = taxRepository.searchTax(pageRequest, searchValue);
            
            List<TaxResponse> content = pages.getContent()
                    .stream()
                    .map(c -> mTaxResponse(c))
                    .toList();
            return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(content)
                    .build();
        }
    }

    // response
    private TaxResponse mTaxResponse(TaxProduct taxProduct) {
        return TaxResponse.builder()
                .id(taxProduct.getId())
                .tax_name(taxProduct.getTaxName())
                .rate_tax(taxProduct.getRateTax())
                .build();
    }

}
