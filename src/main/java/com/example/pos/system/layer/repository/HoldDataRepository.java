package com.example.pos.system.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.HoldData;
@Repository
public interface HoldDataRepository extends JpaRepository<HoldData,Integer> {
     
}
