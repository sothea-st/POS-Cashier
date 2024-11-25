package com.example.pos.system.layer.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.UnitType;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Integer> {
    boolean existsByUnitTypeNameKh(String nameKh);

    boolean existsByUnitTypeNameEn(String nameEn);

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\t*\r\n" + //
            "from\r\n" + //
            "\tpos_unit_type\r\n" + //
            "where\r\n" + //
            "\tstatus = true\r\n" + //
            "\tand is_deleted = false\r\n" + //
            "order by\r\n" + //
            "\tid desc")
    ArrayList<UnitType> getUnitType();

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\t*\r\n" + //
            "from\r\n" + //
            "\tpos_unit_type\r\n" + //
            "where\r\n" + //
            "\tstatus = true\r\n" + //
            "\tand is_deleted = false\r\n" + //
            "\tand id =?")
    UnitType getUnitTypeById(int id);

}
