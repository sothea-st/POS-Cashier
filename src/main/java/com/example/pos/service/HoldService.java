package com.example.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pos.entity.Hold;
import com.example.pos.entity.HoldeDetails;
import com.example.pos.projections.holdProjection.HoldDataModel;
import com.example.pos.projections.holdProjection.HoldDataProjection;
import com.example.pos.projections.holdProjection.HoldDetailModel;
import com.example.pos.projections.holdProjection.HoldProjection;
import com.example.pos.repository.HoldDetailsRepository;
import com.example.pos.repository.HoldRepository;
import com.example.pos.repository.productProjection.ProductProjection;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.*;

@Service
public class HoldService {
     @Autowired
     private HoldRepository repo;

     @Autowired
     private HoldDetailsRepository holdDetail;

     @Autowired
     private HoldDetailsRepository detailRepo;

     public Hold addHold(Hold h) {
          Hold data = new Hold();
          data.setNote(h.getNote());
          data.setQtyHole(h.getQtyHole());
          data.setCreateBy(h.getCreateBy());
          repo.save(data);
          for (int i = 0; i < h.getListHoldDetail().size(); i++) {
               HoldeDetails d = new HoldeDetails();
               int id = h.getListHoldDetail().get(i).getId();
               d.setProId(id);
               d.setHoldId(data.getId());
               d.setQtyHold(h.getListHoldDetail().get(i).getQtyHold());
               detailRepo.save(d);
          }
          data.setListHoldDetail(h.getListHoldDetail());
          return data;
     }

     public HashMap<String, Object> getHold(int userID) {
          HashMap<String, Object> map = new HashMap<>();
          List<HoldDataProjection> dataHold = repo.getHoldDataAll(userID);
          List<HoldDetailModel> list = new ArrayList<>();
          for (int i = 0; i < dataHold.size(); i++) {
               var data = dataHold.get(i);
               List<HoldProjection> dataDetail = repo.getHoldDataById(data.getId());
            
               HoldDetailModel hold = new HoldDetailModel();
               hold.setId(data.getId());
               hold.setNote(data.getNote());
               hold.setQtyHold(data.getQty_hold());
               hold.setListDetails(dataDetail);
               list.add(hold);
          }
          map.put("data", list);
          return map;
     }

     public void deleteHold(Hold h) {
          for (int i = 0; i < h.getListHoldDetail().size(); i++) {
               int id = h.getListHoldDetail().get(i).getId();
               Optional<Hold> data = repo.findById(id);
               Hold d = data.get();
               d.setStatus(false);
               d.setDeleted(true);
               d.setReasonId(h.getReasonId());
               repo.save(d);
          }
     }

     public void deleteHoldByItem(int holdId, int proId){
          String holdDetailId = holdDetail.getId(holdId, proId);
          if( holdDetailId == null ) throw new JavaNotFoundByIdGiven();
          holdDetail.deleteById(Integer.parseInt(holdDetailId));
     }

}
