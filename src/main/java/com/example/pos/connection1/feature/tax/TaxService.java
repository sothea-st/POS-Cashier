package com.example.pos.connection1.feature.tax;

import com.example.pos.connection1.feature.tax.dto.TaxRequest;
import com.example.pos.connection1.feature.tax.dto.TaxRequestUpdate;
import com.example.pos.connection1.feature.tax.dto.TaxResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface TaxService {
    TaxResponse readById (Integer id);
    JavaCollectionResponse<?> readList (int pageSize, int pageNumber);
    TaxResponse create (TaxRequest taxRequest);
    TaxResponse update (Integer id, TaxRequestUpdate taxRequestUpdate);
    void deleteById (Integer id);
    JavaCollectionResponse<?> search (int pageSize, int pageNumber, String searchValue);
}
