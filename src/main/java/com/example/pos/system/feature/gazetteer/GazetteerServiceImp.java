package com.example.pos.system.feature.gazetteer;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.feature.gazetteer.dto.GazetteerResponse;
import com.example.pos.system.feature.gazetteer.projection.GazetteerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GazetteerServiceImp implements GazetteerService{
    // inject bean
    private final GazetteerRepository gazetteerRepository;

    @Override
    public JavaCollectionResponse<?> getProvince() {

        List<GazetteerProjection> data = gazetteerRepository.getProvinces();

        long count = data.size();

        return JavaCollectionResponse.builder()
                .count(count)
                .data(gazetteerRepository.getProvinces().stream()
                        .map(val -> GazetteerResponse.builder()
                                .id(Integer.valueOf(val.getCode()))
                                .nameKh(val.getName_kh())
                                .nameLatin(val.getName_latin())
                                .build()
                        ).toList())
                .build();
    }

    @Override
    public JavaCollectionResponse<?> getDistrict(String code) {
        List<GazetteerProjection> data = gazetteerRepository.getDistricts(code);
        long count = data.size();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(gazetteerRepository.getDistricts(code).stream()
                        .map(val -> GazetteerResponse.builder()
                                .id(Integer.valueOf(val.getCode()))
                                .nameKh(val.getName_kh())
                                .nameLatin(val.getName_latin())
                                .build()
                        ).toList())
                .build();
    }

    @Override
    public JavaCollectionResponse<?> getCommune(String code) {
        List<GazetteerProjection> data = gazetteerRepository.getCommunes(code);
        long count = data.size();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(gazetteerRepository.getCommunes(code).stream()
                        .map(val -> GazetteerResponse.builder()
                                .id(Integer.valueOf(val.getCode()))
                                .nameKh(val.getName_kh())
                                .nameLatin(val.getName_latin())
                                .build()
                        ).toList())
                .build();
    }

    @Override
    public JavaCollectionResponse<?> getVillage(String code) {
        List<GazetteerProjection> data = gazetteerRepository.getVillages(code);
        long count = data.size();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(gazetteerRepository.getVillages(code).stream()
                        .map(val -> GazetteerResponse.builder()
                                .id(Integer.valueOf(val.getCode()))
                                .nameKh(val.getName_kh())
                                .nameLatin(val.getName_latin())
                                .build()
                        ).toList())
                .build();
    }

    @Override
    public JavaResponse<?> getAddress(String code) {
        String data = gazetteerRepository.getAddress(code);
        return JavaResponse.builder()
                .data(data)
                .build();
    }

    @Override
    public JavaResponse<?> getAddressEn(String code) {
        String data = gazetteerRepository.getAddressEn(code);
        return JavaResponse.builder()
                .data(data)
                .build();
    }
}
