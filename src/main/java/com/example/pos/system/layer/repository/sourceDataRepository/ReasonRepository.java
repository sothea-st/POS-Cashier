package com.example.pos.system.layer.repository.sourceDataRepository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.system.domain.sourceData.Reason;

import javax.swing.text.html.Option;

@Repository
public interface ReasonRepository extends JpaRepository<Reason,Integer> {
     @Query(nativeQuery = true , value = "select * from pos_reason where status = true and is_deleted=false order by id desc")
     List<Reason> getReason();

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_reason\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand id = ?")
     Reason getReasonById(int id);

     Optional<Reason> findByIdAndStatusTrueAndIsDeletedFalseAndCode(Integer id,String code);

     @Query(nativeQuery = true , value = "select * from pos_reason pr where code = ? order by id desc")
     List<Reason> getReasonByCode(String code);

     Optional<Reason> findByIdAndCodeAndStatusTrueAndIsDeletedFalse(Integer id,String code);


}
