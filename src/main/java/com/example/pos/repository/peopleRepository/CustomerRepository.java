package com.example.pos.repository.peopleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.pos.entity.people.Customer;
import com.example.pos.projections.CustomerPointProjection.CustomerPointProjection;
import com.example.pos.projections.customerProjection.CustomerProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(nativeQuery = true , value = "select count(*) from pos_customer")
    int countRecord();

    @Query(nativeQuery = true , value = " select contact,point_earned,customer_id ,total_amount_earned  from pos_customer pc where contact = ? and status = true and is_deleted = false")
    CustomerPointProjection getPoint(String phone);

    @Query(nativeQuery = true , value = " select contact,point_earned,customer_id ,total_amount_earned  from pos_customer pc where customer_id = ? and status = true and is_deleted = false")
    CustomerPointProjection getPointByCustomerId(String customerId);
    
    boolean existsByContact(String contact);

    @Query(nativeQuery = true , value = "select * from pos_customer pc  \r\n" + 
            "  where pc.status = true and pc.is_deleted = false")
    List<Customer> readCustomer();

}
