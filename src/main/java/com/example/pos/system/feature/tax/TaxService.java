package com.example.pos.system.feature.tax;

import com.example.pos.system.feature.tax.dto.TaxRequest;
import com.example.pos.system.feature.tax.dto.TaxRequestUpdate;
import com.example.pos.system.feature.tax.dto.TaxResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface TaxService {
    //read tax by id
    TaxResponse readById (Integer id);

    //read list tax
    JavaCollectionResponse<?> readList (Integer pageSize, Integer pageNumber);

    //create new tax
    TaxResponse create (TaxRequest taxRequest);

    //update tax by id
    TaxResponse update (Integer id, TaxRequestUpdate taxRequestUpdate);

    //delete tax by id
    void deleteById (Integer id);

    //search tax
    JavaCollectionResponse<?> search (Integer pageSize, Integer pageNumber, String searchValue);
}
