package com.example.pos.connection1.feature.tax;

import java.util.List;

import org.json.HTTP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Override
    public TaxResponse readById(Integer id) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        return  mTaxResponse(taxProduct);
    }

    @Override
    public JavaCollectionResponse<?> readList(int pageSize, int pageNumber) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<TaxProduct> pages = taxRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
        
        List<TaxResponse> content = pages.getContent()
                        .stream()
                        .map(c->mTaxResponse(c))
                        .toList();
        return JavaCollectionResponse.builder()
                        .count(pages.getTotalElements())
                        .data(content)
                        .build();
    }

    @Override
    public TaxResponse create(TaxRequest taxRequest) {

        // validate name already exist
        if (taxRepository.existsByTaxName(taxRequest.taxName())) {
            throw new ResponseStatusException(
                      HttpStatus.CONFLICT,nameAlreadyExisted);
        }

        TaxProduct taxProduct = new TaxProduct();
        taxProduct.setTaxName(taxRequest.taxName());
        taxProduct.setRateTax(taxRequest.rateTax());
        taxProduct.setCreateBy(taxRequest.createBy());
        taxRepository.save(taxProduct);
        return mTaxResponse(taxProduct);
    }

    @Override
    public TaxResponse update(Integer id, TaxRequestUpdate taxRequestUpdate) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

        if (!taxRequestUpdate.taxName().equals(taxProduct.getTaxName())) {
            // validate name already exist
            if (taxRepository.existsByTaxName(taxRequestUpdate.taxName())) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT,nameAlreadyExisted);
            }
        }            

        taxProduct.setTaxName(taxRequestUpdate.taxName());
        taxProduct.setRateTax(taxRequestUpdate.rateTax());
        taxRepository.save(taxProduct);
        return mTaxResponse(taxProduct);
    }

    @Override
    public void deleteById(Integer id) {
        TaxProduct taxProduct = taxRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
        taxProduct.setStatus(false);
        taxProduct.setDeleted(true);
        taxRepository.save(taxProduct);
    }

    @Override
    public JavaCollectionResponse<?> search(int pageSize, int pageNumber, String searchValue) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<TaxProduct> pages = taxRepository.searchTax(pageRequest, searchValue);

        System.out.println("searchValue : " + searchValue);

        List<TaxResponse> content = pages.getContent()
                        .stream()
                        .map(c->mTaxResponse(c))
                        .toList();
        return JavaCollectionResponse.builder()
                        .count(pages.getTotalElements())
                        .data(content)
                        .build();
    }

    private TaxResponse mTaxResponse(TaxProduct taxProduct){
        return TaxResponse.builder()
                .id(taxProduct.getId())
                .tax_name(taxProduct.getTaxName())
                .rate_tax(taxProduct.getRateTax())
                .build();
    }

}
