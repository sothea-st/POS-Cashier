package com.example.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.entity.Hold;
import com.example.pos.projections.holdProjection.HoldProjection;
import com.example.pos.repository.HoldRepository;
import java.util.*;
@Service
public class HoldService {
     @Autowired
     private HoldRepository repo;

     public Hold addHold(Hold h) {
          Hold data = new Hold();
          data.setNote(h.getNote());
          data.setQtyHole(h.getQtyHole());
          data.setCreateBy(h.getCreateBy());
          repo.save(data);
          return data;
     }

     public List<HoldProjection> getHold(){
          return repo.getHold();
     }

     public void deleteHold(int id,Hold h) {
          Optional<Hold> data = repo.findById(id);
          Hold d = data.get();
          d.setStatus(h.isStatus());
          d.setDeleted(h.isDeleted());
          repo.save(d);
     }

     


}
