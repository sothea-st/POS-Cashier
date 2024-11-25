package com.example.pos.system.layer.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.sourceData.CancelItemDetail;

@Repository
public interface CancelItemDetialsRepository  extends JpaRepository<CancelItemDetail,Integer>{
     
}
