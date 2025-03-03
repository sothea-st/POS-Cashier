package com.example.pos.system.feature.gazetteer;


import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;

public interface GazetteerService {

     JavaCollectionResponse<?> getProvince();

     JavaCollectionResponse<?> getDistrict(String code);

     JavaCollectionResponse<?> getCommune(String code);

     JavaCollectionResponse<?> getVillage(String code);

     JavaResponse<?> getAddress(String code);

     JavaResponse<?> getAddressEn(String code);
}
