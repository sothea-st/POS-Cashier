package com.example.pos.system.layer.service.sourceDataService;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.sourceData.CustomerType;
import com.example.pos.system.layer.repository.sourceDataRepository.CustomerTypeRepository;
import com.example.pos.system.constant.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerTypeService {
     @Autowired 
     private CustomerTypeRepository repo;

     @Autowired
     private HttpSession session;

     public CustomerType add(CustomerType c) {
          var createBy = session.getAttribute(JavaConstant.userId);
          CustomerType data = new CustomerType();
          data.setName(c.getName());
          data.setCreateBy((Integer)createBy);
          repo.save(data);
          return data;
     }

     public List<CustomerType> read(){
          return repo.getCustomerType();
     }

     public CustomerType readById(int id) {
          CustomerType data = repo.getCustomerTypeById(id);
          if( data == null ) throw new JavaNotFoundByIdGiven();
          return data;
     }

     public void delete(int id , CustomerType c) {
          Optional<CustomerType> data = repo.findById(id);
          CustomerType cc = data.get();
          cc.setStatus(c.isStatus());
          cc.setDeleted(c.isDeleted());
          repo.save(cc);
     }

     public CustomerType update(int id , CustomerType c) {
          Optional<CustomerType> data = repo.findById(id);
          CustomerType cc = data.get();
          cc.setName(c.getName());
          repo.save(cc);
          return cc;
     }

}
