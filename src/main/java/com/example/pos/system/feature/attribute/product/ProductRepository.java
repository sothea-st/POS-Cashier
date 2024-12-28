package com.example.pos.system.feature.attribute.product;

import com.example.pos.system.domain.settings.Status;
import com.example.pos.system.feature.attribute.product.productV1.dto.ProductResponseReadByProductId;
import com.example.pos.system.layer.repository.productProjection.ProductProjection;
import com.example.pos.system.layer.repository.productProjection.ProductQty;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.layer.projections.HeadProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

        List<Product> findByVendorIdAndSubCategoryIdAndStatusTrueAndIsDeletedFalse(int vendorId, int subCategoryId);
        List<Product> findByVendorIdAndStatusTrueAndIsDeletedFalse(int vendorId);
        Page<Product> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);
        Page<Product> findByStatusTrueAndIsDeletedFalseAndProductActive(PageRequest pageRequest, Status status);

        List<Product> findByStatusTrueAndIsDeletedFalseAndProductActive(Status status);

        List<Product> findByStatusTrueAndIsDeletedFalseOrderByIdDesc();
        Optional<Product> findByIdAndStatusTrueAndIsDeletedFalse(int id);
        Page<Product> findByProNameEnIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(PageRequest pageRequest ,String name);
        List<Product> findByProNameEnIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(String name);
        Page<Product> findByProNameEnIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(PageRequest pageRequest ,String name,Status status);
        List<Product> findByProNameEnIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(String name,Status status);
        Page<Product> findByBarcodeIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(PageRequest pageRequest ,String name);

        List<Product> findByBarcodeIgnoreCaseContainingAndStatusTrueAndIsDeletedFalse(String name);

        Page<Product> findByBarcodeIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(PageRequest pageRequest ,String name, Status status);
        List<Product> findByBarcodeIgnoreCaseContainingAndProductActiveAndStatusTrueAndIsDeletedFalse(String name, Status status);

        boolean existsByBarcodeAndStatusIsTrueAndIsDeletedIsFalse(String barcode);
        boolean existsByProNameEnAndStatusIsTrueAndIsDeletedIsFalse(String proNameEn);
        boolean existsByProNameKhAndStatusIsTrueAndIsDeletedIsFalse(String proNameKh);


        
        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t*\r\n" + //
                        "from\r\n" + //
                        "\tpos_product pp\r\n" + //
                        "where\r\n" + //
                        "\tbarcode = ?")
        Product getBarcode(String barcode);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.choices as weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired , pc.choices \n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id  = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "order by\n" +
                "\tpc.create_date desc\n" +
                "limit ?\n" +
                " ")
        List<ProductProjection> getNewProduct(int limit);

        @Query(nativeQuery = true, value = "select id,pro_name_en from product_header")
        List<HeadProductProjection> getHead();

        boolean existsByProNameKh(String name);

        boolean existsByProNameEn(String name);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "order by\n" +
                "\tpc.id desc\n" +
                "limit ?")
        List<ProductProjection> getProduct(int limit);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid  as flag ,\n" +
                "\tpc.weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "order by\n" +
                "\tpc.id desc\n" +
                "limit ? offset ?")
        List<ProductProjection> getAllProduct(int perPage, int page);

        @Query(nativeQuery = true, value = "select count(*) from pos_product where status=true and is_deleted=false")
        int countRow();

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t*\r\n" + //
                        "from\r\n" + //
                        "\tpos_product\r\n" + //
                        "where\r\n" + //
                        "\tstatus = true\r\n" + //
                        "\tand is_deleted = false\r\n" + //
                        "\tand id =?")
        Product getProductById(int id);

        //Query Select detail product that been imported 
        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t pp.id, \r\n" + //
                        "\t pid.qty_old, \r\n" + //
                        "\t pid.create_date as local_date , \r\n" + //
                        "\t pp.price, \r\n" + //
                        "\t pid.cost \r\n" + //
                        "from pos_product pp inner join pos_import_detail pid on pid.pro_id = pp.id\r\n" + //
                        "where\r\n" + //
                        "\t pid.qty_old > 0 \r\n" + //
                        "\t and pp.id = ? \r\n" + //
                        "\t order by local_date asc")
        List<ProductResponseReadByProductId> geProductByIdProduct(int id);


        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tpid.qty_old\r\n" + //
                        "from\r\n" + //
                        "\tpos_import_detail pid\r\n" + //
                        "where\r\n" + //
                        "\tpro_id = ?\r\n" + //
                        "order by\r\n" + //
                        "\tid desc\r\n" + //
                        "limit 1")
        Integer getOldQty(int id);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t*\r\n" + //
                        "from\r\n" + //
                        "\tpos_product\r\n" + //
                        "where\r\n" + //
                        "\tstatus = true\r\n" + //
                        "\tand is_deleted = false\r\n" + //
                        "\tand id =?")
        Optional<Product> getProductByOptionalId(int id);

        // @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id
        // ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
        // + //
        // "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
        // "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired \r\n" +
        // //
        // "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
        // "and pc.cat_id = ? order by id desc limit ?")
        // List<ProductProjection> getProductByCatId(int catId, int limit);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.choices as weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired , pc.choices\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "\tand pc.cat_id = ?\n" +
                "order by\n" +
                "\tpc.id desc\n" +
                "limit ? offset ?")
        List<ProductProjection> getProductByCatId(int catId, int limit, int page);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tcount(*)\r\n" + //
                        "from\r\n" + //
                        "\tpos_product pp\r\n" + //
                        "where\r\n" + //
                        "\tstatus = true\r\n" + //
                        "\tand is_deleted = false\r\n" + //
                        "\tand cat_id = ?")
        int countProduct(int catId);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tcount(*)\r\n" + //
                        "from\r\n" + //
                        "\tpos_product pp\r\n" + //
                        "where\r\n" + //
                        "\tstatus = true\r\n" + //
                        "\tand is_deleted = false\r\n" + //
                        "\tand brand_id = ?")
        int countProductByBrandId(int brandId);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n"
                        + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired ,pc.choices  \r\n" + //
                        "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
                        "and pc.brand_id = ? order by id desc limit ? OFFSET  ?")
        List<ProductProjection> getProductByBrandId(int brandId, int limit, int page);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.choices as weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired , pc.choices\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "\tand pc.discount > 0\n" +
                "order by\n" +
                "\tpc.id desc")
        List<ProductProjection> getProductPromotion();

        @Query(nativeQuery = true, value = "select count(*) from pos_product pp where pp.status = true and pp.is_deleted = false and pp.discount > 0")
        int countProductDiscount();

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.choices as weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired , pc.choices\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "inner join pos_countries pc2 \n" +
                "on pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "\tand pc.pro_name_en ilike %?%")
        List<ProductProjection> searchProductByName(String proNameEn);

        @Query(nativeQuery = true, value = "select\n" +
                "\tpc.id,\n" +
                "\tpc.barcode,\n" +
                "\tpc.cat_id ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc2.uuid as flag ,\n" +
                "\tpc.choices as weight ,\n" +
                "\tpc.pro_image_name ,\n" +
                "\tpc.brand_id ,\n" +
                "\tpc.pro_name_en ,\n" +
                "\tpc.pro_name_kh ,\n" +
                "\tpc.cost,\n" +
                "\tpc.price ,\n" +
                "\tpc.product_status ,\n" +
                "\tpc.discount ,\n" +
                "\tpc.code_out_stock ,\n" +
                "\tpc.code_expired , pc.choices\n" +
                "from\n" +
                "\tpos_product pc\n" +
                "\tinner join pos_countries pc2 \n" +
                "\ton pc2.id = pc.country_id \n" +
                "where\n" +
                "\tpc.status = true\n" +
                "\tand pc.is_deleted = false\n" +
                "\tand pc.barcode like %?%")
        List<ProductProjection> searchProductByBarcode(String barcode);

        @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,   \r\n"
                        + //
                        "\t  pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price,( psd.qty - psd.qty_returned ) as qty ,     \r\n"
                        + //
                        "\t  pc.product_status ,psd.discount,psd.discount_type ,pc.code_out_stock ,pc.code_expired ,pc.choices  \r\n"
                        + //
                        "\t from pos_payment pp inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "\t inner join pos_product pc on pc.id = psd.pro_id  inner join pos_sale ps on ps.id = pp.sale_id \r\n"
                        + //
                        "\t where pp.payment_no = ?  and   psd.is_returned  is null and ps.sale_date = ?")
        List<ProductQty> searchProductWithInvoiceNo(String invoiceNO, String currentDate);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        " pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name ,  \r\n" + //
                        " pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price,psd.qty,     \r\n" + //
                        " pc.product_status ,psd.discount,psd.discount_type ,pc.code_out_stock,pc.choices ,pc.code_expired , ( psd.qty - psd.qty_returned ) as qty    \r\n"
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

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tcount(psd.*)\r\n" + //
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
                        "\tand pc.barcode = ?\r\n")
        int countProductExistInIvoice(String invoiceNO, String barcode);
}
