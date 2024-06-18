package com.example.pos.connection1.repository.shiftRepository;
import java.util.Optional;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.connection1.entity.OpenShift;

@Repository
public interface OpenShiftRepository extends JpaRepository<OpenShift,Integer> {

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand open_date = ?")
     Optional<List<OpenShift>> getPosIdByCurrentDate(String currentDate);

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tcount(*)\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand open_date = ?\r\n" + //
                    "\tand number_open_shift = 0")
     int countPosId(String currentDate);

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift pos\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand user_code = ?\r\n" + //
                    "\tand open_date = ?\r\n" + //
                    "\tand pos_id = ?\r\n" + //
                    "\tand number_open_shift = 0\r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "limit 1")
     OpenShift getDataOpenShift(String userCode , String date,String posId);

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift pos\r\n" + //
                    "where\r\n" + //
                    "\tstatus = true\r\n" + //
                    "\tand is_deleted = false\r\n" + //
                    "\tand user_code = ?\r\n" + //
                    "\tand open_date = ?\r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "limit 1")
     Optional<OpenShift> getNumberOpenShift(String userCode , String date);

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\t*\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift pos\r\n" + //
                    "where\r\n" + //
                    "\tuser_code = ?\r\n" + //
                    "\tand open_date = ?\r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "limit 1")
     OpenShift countOpenShift(String userCode, String date);

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tpos_id\r\n" + //
                    "from\r\n" + //
                    "\tpos_open_shift pos\r\n" + //
                    "where\r\n" + //
                    "\tuser_code = ?\r\n" + //
                    "\tand open_date = ?\r\n" + //
                    "order by\r\n" + //
                    "\tid desc\r\n" + //
                    "limit 1")
     String getPosId(String userCode, String date);
 
     
}  