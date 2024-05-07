package com.example.pos.connection1.repository;

import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.repository.productProjection.ProductQty;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.projections.HeadProductProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tpc.id,\r\n" + //
                        "\tpc.barcode,\r\n" + //
                        "\tpc.cat_id ,\r\n" + //
                        "\tpc.brand_id ,\r\n" + //
                        "\tpc.flag ,\r\n" + //
                        "\tpc.weight ,\r\n" + //
                        "\tpc.pro_image_name ,\r\n" + //
                        "\tpc.brand_id ,\r\n" + //
                        "\tpc.pro_name_en ,\r\n" + //
                        "\tpc.pro_name_kh ,\r\n" + //
                        "\tpc.cost,\r\n" + //
                        "\tpc.price ,\r\n" + //
                        "\tpc.product_status ,\r\n" + //
                        "\tpc.discount ,\r\n" + //
                        "\tpc.code_out_stock ,\r\n" + //
                        "\tpc.code_expired\r\n" + //
                        "from\r\n" + //
                        "\tpos_product pc\r\n" + //
                        "where\r\n" + //
                        "\tpc.status = true\r\n" + //
                        "\tand pc.is_deleted = false\r\n" + //
                        "order by\r\n" + //
                        "\tpc.create_date desc limit ?")
        List<ProductProjection> getNewProduct(int limit);

        @Query(nativeQuery = true, value = "select id,pro_name_en from product_header")
        List<HeadProductProjection> getHead();

        boolean existsByProNameKh(String name);

        boolean existsByProNameEn(String name);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,\r\n"
                        + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,\r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired \r\n" + //
                        "from pos_product pc\r\n" + //
                        "where pc.status=true and pc.is_deleted=false order by pc.id desc limit ?")
        List<ProductProjection> getProduct(int limit);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,\r\n"
                        + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,\r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired \r\n" + //
                        "from pos_product pc\r\n" + //
                        "where pc.status=true and pc.is_deleted=false order by pc.id desc")
        List<ProductProjection> getAllProduct();

        @Query(nativeQuery = true, value = "select count(*) from pos_product where status=true and is_deleted=false")
        int countRow();

        @Query(nativeQuery = true, value = "select * from pos_product where status=true and is_deleted=false and id=?")
        Product getProductById(int id);

        @Query(nativeQuery = true, value = "select * from pos_product where status=true and is_deleted=false and id=?")
        Optional<Product> getProductByOptionalId(int id);

        // @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
        //                 + //
        //                 "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
        //                 "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired  \r\n" + //
        //                 "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
        //                 "and pc.cat_id = ? order by id desc limit ?")
        // List<ProductProjection> getProductByCatId(int catId, int limit);

        
        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
                        + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired  \r\n" + //
                        "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
                        "and pc.cat_id = ? order by id desc limit ? offset ?")
        List<ProductProjection> getProductByCatId(int catId, int limit ,int page);

        @Query(nativeQuery = true, value = " select count(*) from pos_product pp where status = true and is_deleted = false and cat_id = ?")
        int countProduct(int catId);

        @Query(nativeQuery = true, value = " select count(*) from pos_product pp where status = true and is_deleted = false and brand_id = ?")
        int countProductByBrandId(int brandId);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
                        + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired  \r\n" + //
                        "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
                        "and pc.brand_id = ? order by id desc limit ? OFFSET  ?")
        List<ProductProjection> getProductByBrandId(int brandId, int limit ,int page);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
                        + //
                        " pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,  \r\n" + //
                        " pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired    \r\n" + //
                        " from pos_product pc where pc.status=true and pc.is_deleted=false  \r\n" + //
                        " and pc.pro_name_en  ilike %?%")
        List<ProductProjection> searchProductByName(String proNameEn);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,  \r\n"
                        + //
                        " pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,   \r\n" + //
                        " pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired    \r\n" + //
                        " from pos_product pc where pc.status=true and pc.is_deleted=false  \r\n" + //
                        " and pc.barcode like  %?%")
        List<ProductProjection> searchProductByBarcode(String barcode);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,   \r\n"
                        + //
                        "\t  pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price,psd.qty,     \r\n" + //
                        "\t  pc.product_status ,psd.discount,psd.discount_type ,pc.code_out_stock ,pc.code_expired   \r\n"
                        + //
                        "\t from pos_payment pp inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "\t inner join pos_product pc on pc.id = psd.pro_id  inner join pos_sale ps on ps.id = pp.sale_id \r\n"
                        + //
                        "\t where pp.payment_no = ?  and pp.is_return is null and ps.sale_date = ?")
        List<ProductQty> searchProductWithInvoiceNo(String invoiceNO, String currentDate);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        " pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,  \r\n" + //
                        " pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price,psd.qty,     \r\n" + //
                        " pc.product_status ,psd.discount,psd.discount_type ,pc.code_out_stock ,pc.code_expired ,  psd.qty    \r\n"
                        + //
                        "from\r\n" + //
                        "\tpos_payment pp\r\n" + //
                        "inner join pos_sale ps on\r\n" + //
                        "\tps.id = pp.sale_id\r\n" + //
                        "inner join pos_sale_details psd on\r\n" + //
                        "\tpsd.sale_id = ps.id\r\n" + //
                        "inner join pos_product pc on\r\n" + //
                        "\tpc.id = psd.pro_id\r\n" + //
                        "where\r\n" + //
                        "\tpp.payment_no = ?\r\n" + //
                        "\tand pc.barcode = ? and psd.is_returned is null")
        List<ProductQty> searchProductWithInvoiceNoAndBarcode(String invoiceNO, String barcode);

}
