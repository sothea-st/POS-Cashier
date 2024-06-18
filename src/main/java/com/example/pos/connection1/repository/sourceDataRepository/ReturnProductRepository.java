package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.sourceData.ReturnProduct;
import com.example.pos.connection1.repository.productProjection.ProductProjection;

@Repository
public interface ReturnProductRepository  extends JpaRepository<ReturnProduct,Integer>{
     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\tcat_id ,\r\n" + //
                    "\tbrand_id ,\r\n" + //
                    "\tflag ,\r\n" + //
                    "\tweight ,\r\n" + //
                    "\tpro_image_name ,\r\n" + //
                    "\tbarcode ,\r\n" + //
                    "\tpro_name_en ,\r\n" + //
                    "\tpro_name_kh ,\r\n" + //
                    "\tcost,\r\n" + //
                    "\tprice,\r\n" + //
                    "\tproduct_status ,\r\n" + //
                    "\tdiscount\r\n" + //
                    "from\r\n" + //
                    "\tpos_product pp\r\n" + //
                    "where\r\n" + //
                    "\tbarcode = ?")
     ProductProjection getProductByBarcode(String barcode);
}
