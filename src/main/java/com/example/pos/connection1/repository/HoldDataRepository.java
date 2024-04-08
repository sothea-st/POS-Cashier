package com.example.pos.connection1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.HoldData;
@Repository
public interface HoldDataRepository extends JpaRepository<HoldData,Integer> {
     
}
