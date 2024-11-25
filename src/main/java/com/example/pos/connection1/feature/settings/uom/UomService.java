package com.example.pos.connection1.feature.settings.uom;

import com.example.pos.connection1.feature.settings.uom.dto.UomRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import com.example.pos.connection1.util.response_success.JavaResponse;
import com.example.pos.connection1.util.response_success.ResponseSuccess;

public interface UomService {

    /**
     * read uom
     * @param pageNumber
     * @param pageSize
     * @return
     */
    JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize);

    /**
     * search uom
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue);

    /**
     * read uom By id
     * @param id
     * @return
     */
    JavaResponse<?> readById(Integer id);

    /**
     * create uom
     * @param uomRequest
     * @return
     */
    ResponseSuccess create(UomRequest uomRequest);

    /**
     * update uom
     * @param id
     * @param uomRequest
     * @return
     */
    ResponseSuccess update(UomRequest uomRequest, Integer id);

    /**
     * delete uom by id
     * @param id
     * @return
     */
    ResponseSuccess delete(Integer id);

}
