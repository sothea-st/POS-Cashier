package com.example.pos.connection1.feature.settings.slot;

import com.example.pos.connection1.feature.settings.slot.dto.SlotRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import com.example.pos.connection1.util.response_success.JavaResponse;
import com.example.pos.connection1.util.response_success.ResponseSuccess;

public interface SlotService {

    /**
     * create slot
     * @param slotRequest
     * @return
     */
    ResponseSuccess create(SlotRequest slotRequest);

    /**
     * read slot
     * @param pageNumber
     * @param pageSize
     * @return
     */
    JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize);

    /**
     * read slot by id
     * @param id
     * @return
     */
    JavaResponse<?> readById(Integer id);

    /**
     * delete slot by id
     * @param id
     * @return
     */
    ResponseSuccess deleteById(Integer id);

    /**
     * update by id
     * @param id
     * @return
     */
    ResponseSuccess updateById(SlotRequest slotRequest, Integer id);

    /**
     * search
     * @param pageNumber
     * @param pageSize
     * @param searchValue
     * @return
     */
    JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String searchValue);

}
