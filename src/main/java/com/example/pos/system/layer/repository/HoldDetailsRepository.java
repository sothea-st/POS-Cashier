package com.example.pos.system.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.HoldeDetails;
@Repository
public interface HoldDetailsRepository extends JpaRepository<HoldeDetails,Integer> {

     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid\r\n" + //
                    "from\r\n" + //
                    "\tpos_hold_details\r\n" + //
                    "where\r\n" + //
                    "\thold_id = ?\r\n" + //
                    "\tand pro_id = ?")
     String getId(int holdId, int proId);

}
