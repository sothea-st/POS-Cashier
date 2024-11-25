package com.example.pos.system.layer.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.sourceData.CancelItem;

@Repository
public interface CancelItemRepository extends JpaRepository<CancelItem,Integer> {
     
}
