package com.example.pos.system.layer.service.searchByBarcodeOrNameService;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.models.PaymentModel;
import com.example.pos.system.domain.models.ProductModel;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import com.example.pos.system.feature.attribute.product.ProductRepository;
import com.example.pos.system.layer.repository.paymentRepository.PaymentRepository;
import com.example.pos.system.layer.repository.productProjection.ProductProjection;
import com.example.pos.system.layer.repository.productProjection.ProductQty;
import com.example.pos.system.feature.attribute.product.productService.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchByBarcodeOrNameService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ImportDetailRepository repoImp;
    @Autowired
    private ProductService proService;
    @Autowired
    private PaymentRepository repoPayment;

    public List<ProductProjection> getProductByBarcodeInInvoice(String barcode, String invoice) {
        return repoPayment.getProductByBarcodeInInvoice(invoice, barcode);
    }

    public String getIncoive(String paymentBarcode) {
        return repoPayment.getInvoice(paymentBarcode);
    }

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

    public Map<String, Object> searchWithInvoiceNo(String invoiceNo, String barcode) {
        HashMap<String, Object> _map = new HashMap<>();

        int countRecord = repoPayment.isExistInvoice(invoiceNo);
        Integer saleId = repoPayment.getSaleId(invoiceNo);
        if (countRecord == 0) {
            _map.put("msg", JavaConstant.INVOICE_NUMBER_DOES_NOT_EXIST);
            return _map;
        }

        List<ProductQty> data = null;
        List<ProductModel> list = new ArrayList<>();
        PaymentModel _model = null;

        _model = repoPayment.getSomeData(invoiceNo);

        if (barcode == null) {
            data = repo.searchProductWithInvoiceNo(invoiceNo, JavaConstant.currentDate);
            for (int i = 0; i < data.size(); i++) {
                var val = data.get(i);
                ProductModel p = proModelQty(val, val.getQty());
                list.add(p);
            }
        } else {
            int countProductExistInInvoice = repo.countProductExistInIvoice(invoiceNo, barcode);

            if (countProductExistInInvoice == 0) {
                _map.put("msg", JavaConstant.PRODUCT_DOES_NOT_EXIST_IN_INVOICE_NUMBER);
                return _map;
            }

            data = repo.searchProductWithInvoiceNoAndBarcode(invoiceNo, barcode);
            for (int i = 0; i < data.size(); i++) {
                var val = data.get(i);
                ProductModel p = proModelQty(val, val.getQty());
                list.add(p);
            }
        }

        Double _getUsd = _model.getReceive_usd() != null ? _model.getReceive_usd().doubleValue() : 0;
        Double _getKhr = _model.getReceive_khr() != null ? _model.getReceive_khr().doubleValue() : 0;
        Double _cKhr = _model.getChange_khr() != null ? _model.getChange_khr().doubleValue() : 0;
        Double _cUsd = _model.getChange_usd() != null ? _model.getChange_usd().doubleValue() : 0;

        _map.put("receiveUsd", JavaConstant.getTwoPrecision(_getUsd));
        _map.put("changeUsd", JavaConstant.getTwoPrecision(_cUsd));

        String _stringData = _convertString(String.valueOf(_getKhr));
        _map.put("receiveKhr", _stringData);
        String cKhr = _convertString(String.valueOf(_cKhr));
        _map.put("changeKhr", cKhr);

        _map.put("msg", "success");
        _map.put("invoiceNo", invoiceNo);
        _map.put("saleId", saleId);

        _map.put("data", list);

        return _map;
    }

    String _convertString(String value) {
        value = value.replace(".", " ");
        String[] arr = value.split(" ");
        return arr[0];
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
                qty,
                data.getDiscount_type(),
                data.getChoices()

        );

        return p;
    }
}
