package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.sourceData.CancelItemDetail;

@Repository
public interface CancelItemDetialsRepository  extends JpaRepository<CancelItemDetail,Integer>{
     
}
