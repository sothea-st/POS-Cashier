package com.example.pos.system.layer.service.shiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.system.domain.CloseShift;
import com.example.pos.system.domain.OpenShift;

import com.example.pos.system.layer.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.system.layer.repository.shiftRepository.OpenShiftRepository;

import java.util.*;

import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Service
public class CloseShiftService {
    @Autowired
    private CloseShiftRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private OpenShiftRepository repoOpen;

    public CloseShift closeShift(CloseShift c) {
      
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
        String closeDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        CloseShift data = new CloseShift();
        data.setCloseTime(timeStamp);
        data.setUserCode(c.getUserCode());
        data.setCloseDate(closeDate);
        data.setExpress(c.getExpress());
        data.setCashKhr(c.getCashKhr());
        data.setCashUsd(c.getCashUsd());
        data.setKhqrMnk(c.getKhqrMnk());
        data.setKhqrAba(c.getKhqrAba());
        data.setCreditCard(c.getCreditCard());
        data.setCreateBy(c.getUserId());
        data.setPosId(c.getPosId());
        data.setActive("closed");
     
        repo.save(data);

        Optional<OpenShift> open = repoOpen.getNumberOpenShift(c.getUserCode(),closeDate);
        OpenShift obj = open.get();
        obj.setNumberOpenShift(0);
        repoOpen.save(obj);
        return data;
    }

   

}
