package com.example.pos.system.layer.service.sourceDataService;
 
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.sourceData.Source;
import com.example.pos.system.layer.repository.sourceDataRepository.SourceRepository;
import com.example.pos.system.constant.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.*;
@Service
public class SourceService {
     @Autowired
     private SourceRepository repo;

     @Autowired
     private HttpSession session;
     public Source addSource(Source s) {
          var createBy = session.getAttribute(JavaConstant.userId);
          Source data = new Source();
          data.setCreateBy((Integer)createBy);
          data.setName(s.getName());
          repo.save(data);
          return data;
     }

     public List<Source> getSource(){
          return repo.getSource();
     }

     public Source getSourceById(int id) {
          Source data = repo.getSourceById(id);
          if( data == null ) throw new JavaNotFoundByIdGiven();
          return data;
     }

     public void deleteSource(int id,Source s) {
          Optional<Source> data =  repo.findById(id);
          Source sd = data.get();
          sd.setStatus(s.isStatus());
          sd.setDeleted(s.isDeleted());
          repo.save(sd);
     }

     public Source updateSource(int id , Source s) {
          Optional<Source> data =  repo.findById(id);
          Source sd = data.get();
          sd.setName(s.getName());
          repo.save(sd);
          return sd;
     }


}
