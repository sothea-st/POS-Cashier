package com.example.pos.connection1.feature.imports;

import com.example.pos.connection1.feature.imports.dto.ImportRequest;
import com.example.pos.connection1.feature.imports.dto.ImportResponseById;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

/**
 * Service interface for managing import operations.
 */
public interface ImportService {

     /**
      * Creates a new import based on the provided import request.
      * 
      * @param importRequest The request object containing import details.
      */
     void createImport(ImportRequest importRequest);

     /**
      * Creates a new import based on the provided import request.
      * 
      * @param importRequest The request object containing import details.
     * @param id reqiured
      */
      void update(ImportRequest importRequest , int id);

     /**
      * Retrieves a paginated list of imports.
      * 
      * @param pageNumber The page number to retrieve (1-based index).
      * @param pageSize   The number of items per page.
      * @return A {@link JavaCollectionResponse} containing imported data.
      */
     JavaCollectionResponse<?> retrieve(int pageNumber, int pageSize);

     /**
      * Deletes an import record by its identifier.
      * 
      * @param id The identifier of the import record to delete.
      */
     void deleteById(int id);

     /**
      * Retrieves detailed information about an import by its identifier.
      * 
      * @param id The identifier of the import record to retrieve details for.
      * @return An {@link ImportResponseById} object containing import details.
      */
     ImportResponseById retrieveDetail(int id);


     /**
      * filter import 
      * @param value the value client want to filter
      * @param pageNumber The page number to retrieve (1-based index).
      * @param pageSize   The number of items per page.
      * @return A {@link JavaCollectionResponse} containing imported data.
      */
      JavaCollectionResponse<?> filter(int pageNumber, int pageSize, String value);
}
