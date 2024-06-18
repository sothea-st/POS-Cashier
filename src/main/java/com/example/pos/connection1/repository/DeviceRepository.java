package com.example.pos.connection1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {
     @Query(nativeQuery = true,value = "select\r\n" + //
                    "\tcount(*)\r\n" + //
                    "from\r\n" + //
                    "\tpos_device\r\n" + //
                    "where\r\n" + //
                    "\tuser_id =?\r\n" + //
                    "\tand date = ?")
     int count(int userId,String date);

     @Query(nativeQuery = true,value = "select\r\n" + //
                    "\tdevice_name\r\n" + //
                    "from\r\n" + //
                    "\tpos_device pd\r\n" + //
                    "where\r\n" + //
                    "\tid = ?")
     String deviceName(int id);

} 
