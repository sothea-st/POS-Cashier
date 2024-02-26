package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Hold;
import com.example.pos.entity.HoldeDetails;
@Repository
public interface HoldDetailsRepository extends JpaRepository<HoldeDetails,Integer> {

     @Query(nativeQuery = true , value = "update pos_hold_details set status = false and is_deleted = true where hold_id = ? and pro_id = ?")
     void deleteProductByHoldId(int holdId, int proId);
}
