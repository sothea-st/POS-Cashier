package com.example.pos.system.layer.service.sourceDataService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.sourceData.CancelItem;
import com.example.pos.system.domain.sourceData.CancelItemDetail;
import com.example.pos.system.layer.repository.sourceDataRepository.CancelItemDetialsRepository;
import com.example.pos.system.layer.repository.sourceDataRepository.CancelItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class CancelItemService {
     @Autowired
     private CancelItemRepository repo;

     @Autowired
     private HttpSession session;

     @Autowired
     private CancelItemDetialsRepository repoDetails;

     public void cancelAndDeleteItem(CancelItem c, String type){
          var createBy = session.getAttribute(JavaConstant.userId);
          String time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
          String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
          // String code = c.getListCancelDetail().size() == 1 ?  "delete" : "cancel";
        
          CancelItem data = new CancelItem();
          data.setCode(type);
          data.setReasonId(c.getReasonId());
          data.setCreateBy(c.getCreateBy());
          data.setCancelTime(time);
          data.setCancelDate(date);
          repo.save(data);
          for( int i = 0 ; i < c.getListCancelDetail().size() ; i++ ) {
               CancelItemDetail d= new CancelItemDetail();
              
               d.setProId(c.getListCancelDetail().get(i).getProId());
               d.setCancelId(data.getId());
               repoDetails.save(d);
          }
     }

}
