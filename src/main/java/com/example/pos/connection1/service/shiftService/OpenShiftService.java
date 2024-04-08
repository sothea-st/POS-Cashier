package com.example.pos.connection1.service.shiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.OpenShift;
import com.example.pos.connection1.repository.shiftRepository.OpenShiftRepository;

@Service
public class OpenShiftService {
     @Autowired
     private OpenShiftRepository repo;

     public OpenShift openShift(OpenShift s){
          OpenShift data = new OpenShift();
          data.setPosId(s.getPosId());
          data.setUserCode(s.getUserCode());
          data.setReserveUsd(s.getReserveUsd());
          data.setReserveKhr(s.getReserveKhr());
          data.setOpenTime(s.getOpenTime());
          data.setOpenDate(JavaConstant.currentDate);
          data.setCreateBy(s.getCreateBy());
          data.setNumberOpenShift(1);
          repo.save(data);
          return data;
     }

     public OpenShift getOpenShift(String userCode){
          OpenShift data = repo.countOpenShift(userCode, JavaConstant.currentDate);
          return data;
     }

}
