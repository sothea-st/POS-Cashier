package com.example.pos.connection1.feature.product.productV1;

import com.example.pos.connection1.feature.product.productV1.dto.ProductRequest;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponse;
import com.example.pos.connection1.feature.product.productV1.dto.ProductResponseReadById;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface ProductService {
    /**
     * Creates a new product based on the provided product request.
     * 
     * @param productRequest The details of the product to create.
     * @return The response containing details of the created product.
     */
    ProductResponse create(ProductRequest productRequest);

    /**
     * Retrieves product details by its unique identifier.
     * 
     * @param id The unique identifier of the product.
     * @return The response containing details of the product found by ID.
     */
    ProductResponseReadById readProductById(int id);

    /**
     * Retrieves a collection of products based on pagination parameters.
     * 
     * @param pageNumber The page number of the results to retrieve.
     * @param pageSize   The number of products per page.
     * @return A collection response containing products for the specified page.
     */
    JavaCollectionResponse<?> read(int pageNumber, int pageSize);

    /**
     * Deletes a product identified by its unique identifier.
     * 
     * @param id The unique identifier of the product to delete.
     */
    void deleteById(int id);

    /**
     * Updates an existing product identified by its unique identifier.
     * 
     * @param id             The unique identifier of the product to update.
     * @param productRequest The updated details of the product.
     * @return The response containing details of the updated product.
     */
    ProductResponse updateProductById(int id, ProductRequest productRequest);
}
