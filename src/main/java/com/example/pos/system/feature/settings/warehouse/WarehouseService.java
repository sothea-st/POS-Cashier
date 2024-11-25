package com.example.pos.system.feature.settings.warehouse;

import com.example.pos.system.feature.settings.warehouse.dto.WarehouseRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;

public interface WarehouseService {
    /**
     * read warehouse
     * @param pageNumber
     * @param pageSize
     * @return
     */
    JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize);

    /**
     * search warehouse
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue);

    /**
     * read warehouse By id
     * @param id
     * @return
     */
    JavaResponse<?> readById(Integer id);

    /**
     * create warehouseRequest
     * @param warehouseRequest
     * @return
     */
    ResponseSuccess create(WarehouseRequest warehouseRequest);

    /**
     * update warehouseRequest
     * @param id
     * @param warehouseRequest
     * @return
     */
    ResponseSuccess update(WarehouseRequest warehouseRequest, Integer id);

    /**
     * delete warehouse by id
     * @param id
     * @return
     */
    ResponseSuccess delete(Integer id);
}
