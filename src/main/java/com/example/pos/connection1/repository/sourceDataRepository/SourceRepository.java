package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.connection1.entity.sourceData.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source,Integer>{
     @Query(nativeQuery = true , value = "select * from pos_source where status =  true and is_deleted =  false order by id desc")
     List<Source> getSource();

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_source\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand id = ?")
     Source getSourceById(int id);
}
