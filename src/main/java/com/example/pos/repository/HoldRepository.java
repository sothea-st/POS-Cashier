package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Hold;
import com.example.pos.projections.holdProjection.HoldProjection;

import java.util.*;
@Repository
public interface HoldRepository extends JpaRepository<Hold,Integer> {
     @Query(nativeQuery = true , value = "select id,note,qty_hold from pos_hold where status = true and is_deleted = false")
     List<HoldProjection> getHold();
}
