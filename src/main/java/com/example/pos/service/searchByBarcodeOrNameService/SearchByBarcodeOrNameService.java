package com.example.pos.service.searchByBarcodeOrNameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Product;
import com.example.pos.entity.models.ProductModel;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.ProductRepository;
import com.example.pos.repository.productProjection.ProductProjection;
import com.example.pos.repository.productProjection.ProductQty;
import com.example.pos.service.ProductService;

@Service
public class SearchByBarcodeOrNameService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ImportDetailRepository repoImp;
    @Autowired
    private ProductService proService;

    public List<ProductModel> search(String code, String nameSearch) {
        List<ProductProjection> data = null;
        List<ProductModel> list = new ArrayList<>();
        if (code.equals("barcode")) {
            data = repo.searchProductByBarcode(nameSearch);
        } else {
            data = repo.searchProductByName(nameSearch);
        }
        for (int i = 0; i < data.size(); i++) {
            var val = data.get(i);
            Integer qty = repoImp.getQty(val.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proService.proModel(val, qty);
            list.add(p);
        }
        return list;
    }

    public List<ProductModel> searchWithInvoiceNo(String invoiceNo) {
        List<ProductQty> data = null;
        List<ProductModel> list = new ArrayList<>();
        data = repo.searchProductWithInvoiceNo(invoiceNo,JavaConstant.currentDate);
        for (int i = 0; i < data.size(); i++) {
            var val = data.get(i);
            ProductModel p = proModelQty(val, val.getQty());
            list.add(p);
        }
        return list;
    }
    public ProductModel proModelQty(ProductQty data, int qty) {
        ProductModel p = new ProductModel(
                data.getBrand_id(),
                data.getPro_name_kh(),
                data.getPro_image_name(),
                data.getProduct_status(),
                data.getPro_name_en(),
                data.getId(),
                data.getFlag(),
                data.getDiscount(),
                data.getCost(),
                data.getPrice(),
                data.getWeight(),
                data.getBarcode(),
                data.getCat_id(),
                data.getCode_expired(),
                data.getCode_out_stock(),
                qty);
        return p;
    }
}
