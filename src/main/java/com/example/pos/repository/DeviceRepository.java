package com.example.pos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {
     @Query(nativeQuery = true,value = "select count(*) from pos_device where user_id=? and date = ?")
     int count(int userId,String date);

     @Query(nativeQuery = true,value = "select device_name from pos_device pd where id = ?")
     String deviceName(int id);

} 
