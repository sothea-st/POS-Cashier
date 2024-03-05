package com.example.pos.service.sourceDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.people.Customer;
import com.example.pos.projections.CustomerPointProjection.CustomerPointProjection;
import com.example.pos.projections.customerProjection.CustomerProjection;
import com.example.pos.repository.peopleRepository.CustomerRepository;
import com.example.pos.service.SaleService;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    @Autowired
    private SaleService sale;

    public Customer add(Customer c) {
        int count = repo.countRecord();
   
        count++;
        String custId =  customerId(count);
        Customer data = new Customer();
        data.setCustomerTypeId(c.getCustomerTypeId());
        data.setCusName(c.getCusName());
        data.setContact(c.getContact());
        data.setGender(c.getGender());
        // data.setEmail(c.getEmail());
        data.setNationality(c.getNationality());
        data.setCreateBy(c.getCreateBy());
        data.setCustomerId(custId);
        repo.save(data);
        return data;
    }


    public CustomerPointProjection getPoint(String phone) {
        boolean isCheck = JavaConstant.onlyDigits(phone, phone.length());
        if( isCheck  ) { // is true for check phone number
            return repo.getPoint(phone);
        }
        return repo.getPointByCustomerId(phone);
    }


    public String customerId(int countId) {
        String cusId = "K";
        if (countId < 10) {
            cusId += "000000" + countId;
        } else if (countId < 100) {
            cusId += "00000" + countId;
        } else if (countId < 1000) {
            cusId += "0000" + countId;
        } else if (countId < 10000) {
            cusId += "000" + countId;
        }  else if (countId < 100000) {
            cusId += "00" + countId;
        } else if (countId < 1000000) {
            cusId += "0" + countId;
        } else {
            cusId += "" + countId;
        }
        return cusId;
    }

    public List<Customer> read() {
        return repo.readCustomer();
    }

    public Customer readById(int id) {
        Optional<Customer> c = repo.findById(id);
        if (c == null)
            throw new JavaNotFoundByIdGiven();
        return c.get();
    }

    public void deleteById(int id, Customer c) {
        Optional<Customer> cc = repo.findById(id);
        Customer data = cc.get();
        if (c == null)
            throw new JavaNotFoundByIdGiven();
        data.setStatus(c.isStatus());
        data.setDeleted(c.isDeleted());
        repo.save(data);
    }

    public Customer update(int id, Customer c) {
        Optional<Customer> cc = repo.findById(id);
        Customer data = cc.get();
        if (c == null)
            throw new JavaNotFoundByIdGiven();
        data.setCusName(c.getCusName());
        data.setContact(c.getContact());
        data.setGender(c.getGender());
        data.setNationality(c.getNationality());
        // data.setEmail(c.getEmail());
        data.setCustomerTypeId(c.getCustomerTypeId());
        // data.setCoupon(c.getCoupon());
        // data.setEarning(c.getEarning());
        repo.save(data);
        return data;
    }

    public String getCustomerId() {
        int count = repo.countRecord();
        String custId = sale.customerId(count);
        return custId;
    }

}
