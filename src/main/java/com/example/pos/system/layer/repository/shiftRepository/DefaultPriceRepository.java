package com.example.pos.system.layer.repository.shiftRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.system.domain.sourceData.DefaultPrice;
import com.example.pos.system.layer.projections.defaultPriceProjection.DefaultPriceProjection;

@Repository
public interface DefaultPriceRepository extends JpaRepository<DefaultPrice,Integer> {
     @Query(nativeQuery = true , value = "select *  from pos_default_price where status = true and is_deleted = false order by id desc")
     List<DefaultPrice> getListDefaultPrice();


     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\tdefault_price_usd ,\r\n" + //
                    "\tdefautl_price_khr\r\n" + //
                    "from\r\n" + //
                    "\tpos_default_price\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand id = ?")
     DefaultPriceProjection getDefaultPriceById(int id);

}
