package com.example.pos.connection1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 
import com.example.pos.connection1.entity.IPAddressPOSID;
@Repository
public interface IPAddressRepository  extends JpaRepository<IPAddressPOSID,Integer>{
     
     @Query(nativeQuery = true , value = "select count(*) from pos_id")
     int getCountIP();

     @Query(nativeQuery = true , value = "select *  from pos_id pi2 where pi2.ip_address = ?")
     Optional<IPAddressPOSID> getIpAdrress(String ipAddress);

     // @Query(nativeQuery = true , value = "delete from pos_id where user_id = ?")
     // void deleteRecord(String userId);

     // void deleteByUserId(String userID);

}
