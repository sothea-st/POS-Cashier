package com.example.pos.system.feature.settings.range;

import com.example.pos.system.feature.settings.range.dto.RangeRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;


public interface RangeService {

    /**
     * create rangeRequest
     * @param rangeRequest
     * @return
     */
    ResponseSuccess create(RangeRequest rangeRequest);

    /**
     * read range
     * @param pageNumber
     * @param pageSize
     * @return
     */
    JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize);

    /**
     * read ranges by id
     * @param id
     * @return
     */
    JavaResponse<?> readById(Integer id);

    /**
     * delete range by id
     * @param id
     * @return
     */
    ResponseSuccess deleteById(Integer id);

    /**
     * update range by id
     * @param id
     * @return
     */
    ResponseSuccess updateById(RangeRequest rangeRequest,Integer id);

    /**
     * search range
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue);

}
