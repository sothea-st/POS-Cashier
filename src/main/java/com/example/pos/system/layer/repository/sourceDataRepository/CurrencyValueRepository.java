package com.example.pos.system.layer.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.system.domain.sourceData.CurrencyValue;
@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue,Integer> {

    @Query(nativeQuery = true , value = "select * from pos_currency_value where status = true and is_deleted = false order by id desc")
    List<CurrencyValue> getAllCurrencyValue();

    @Query(nativeQuery = true , value = "select\r\n" + //
                "\t*\r\n" + //
                "from\r\n" + //
                "\tpos_currency_value\r\n" + //
                "where\r\n" + //
                "\tstatus = true\r\n" + //
                "\tand is_deleted = false\r\n" + //
                "\tand id = ?")
    CurrencyValue getCurrencyById(int id);

}
