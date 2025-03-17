package com.example.pos.system.feature.gazetteer;


import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gazetteer")
@RequiredArgsConstructor
public class GazetteerController {

    // inject service
    private final GazetteerService gazetteerService;

    @GetMapping("/provinces")
    public JavaCollectionResponse<?> getProvince(){
        return gazetteerService.getProvince();
    }

    @GetMapping("/districts/{code}")
    public JavaCollectionResponse<?> getDistrict(
            @PathVariable String code
    ){
        return gazetteerService.getDistrict(code);
    }

    @GetMapping("/communes/{code}")
    public JavaCollectionResponse<?> getCommune(
            @PathVariable String code
    ){
        return gazetteerService.getCommune(code);
    }

    @GetMapping("/villages/{code}")
    public JavaCollectionResponse<?> getVillage(
            @PathVariable String code
    ){
        return gazetteerService.getVillage(code);
    }

    @GetMapping("/addresses/{code}")
    public JavaResponse<?> getAddress(
            @PathVariable String code
    ){
        return gazetteerService.getAddress(code);
    }

    @GetMapping("/addresses/en/{code}")
    public JavaResponse<?> getAddressEn(
            @PathVariable String code
    ){
        return gazetteerService.getAddressEn(code);
    }

}
