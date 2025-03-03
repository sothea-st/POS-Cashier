package com.example.pos.system.feature.gazetteer;

import com.example.pos.system.domain.Gazetteer;
import com.example.pos.system.feature.gazetteer.projection.GazetteerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GazetteerRepository extends JpaRepository<Gazetteer,Integer> {

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_province();")
    List<GazetteerProjection> getProvinces();

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_district(:code);")
    List<GazetteerProjection> getDistricts(@Param("code") String code);

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_commune(:code);")
    List<GazetteerProjection> getCommunes(@Param("code") String code);

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_village(:code);")
    List<GazetteerProjection> getVillages(@Param("code") String code);

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_address(:code);")
    String getAddress(@Param("code") String code);

    @Query(nativeQuery = true , value = "select * from  public.get_gazetteers_address_en(:code);")
    String getAddressEn(@Param("code") String code);

    Optional<Gazetteer> findByCode(String code);


}
