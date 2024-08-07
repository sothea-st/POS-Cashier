package com.example.pos.connection1.service.sourceDataService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.*;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.sourceData.ReturnDetails;
import com.example.pos.connection1.entity.sourceData.ReturnProduct;
import com.example.pos.connection1.feature.imports.ImportRepository;
import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.SaleDetailsRepository;
import com.example.pos.connection1.repository.SaleFiFoRepository;
import com.example.pos.connection1.repository.SaleRepository;
import com.example.pos.connection1.repository.paymentRepository.PaymentRepository;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.repository.sourceDataRepository.ReturnDetailsRepository;
import com.example.pos.connection1.repository.sourceDataRepository.ReturnProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.pos.connection1.service.paymentService.ReprintService;

import java.util.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReturnProductService {
    @Autowired
    private ReturnProductRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private ReturnDetailsRepository repoDetail;

    @Autowired
    private PaymentRepository repoPayment;

    @Autowired
    private ImportDetailRepository repoImport;

    @Autowired
    private SaleRepository repoSale;

    @Autowired
    private ReprintService reprintService;

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;

    @Autowired
    private SaleFiFoRepository saleFiFoRepository;

    @Autowired
    private ProductRepository productRepository;


    public Map<String, Object> returnProduct(ReturnProduct re) {

        String time = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(Calendar.getInstance().getTime());

        ReturnProduct r = new ReturnProduct();
        r.setCreateBy(re.getCreateBy());
        r.setPaymentNo(re.getPaymentNo());
        r.setReturnTime(time);
        r.setReturnDate(JavaConstant.currentDate);
        r.setReasonId(re.getReasonId());
        r.setReturnAmount(re.getReturnAmount());
        r.setSaleId(re.getSaleId());
        repo.save(r);

        // update payment is_return by payment no
        Optional<Payment> data = repoPayment.getDataPayment(re.getPaymentNo());
        Payment pay = data.get();
        pay.setIsReturn("returned");
        repoPayment.save(pay);

        List<ReturnDetails> listDetail = re.getDataDetails();
        Payment pays = repoPayment.findByPaymentNo(re.getPaymentNo());
        int posSaleID = pays.getSaleId();

        double sumTotalReturn = 0;

        for (int i = 0; i < listDetail.size(); i++) {
            int proId = listDetail.get(i).getProId();
            var val = listDetail.get(i);

            sumTotalReturn += val.getQty() * val.getPrice().doubleValue();

            int qtyReturn = listDetail.get(i).getQty();
            ReturnDetails obj = new ReturnDetails();
            obj.setProId(listDetail.get(i).getProId());
            obj.setQty(listDetail.get(i).getQty());
            obj.setReturnId(r.getId());
            obj.setPrice(listDetail.get(i).getPrice());
            obj.setAmount(listDetail.get(i).getAmount());
            obj.setDiscount(listDetail.get(i).getDiscount());
            obj.setDiscountAmt(listDetail.get(i).getDiscountAmt());

            repoDetail.save(obj);

            // ole mechanism
            // restock qty back
//            Optional<ImportDetail> dataImp = repoImport.findByImpId(proId);
//            ImportDetail impDetail = dataImp.get();
//            int restockQty = qtyReturn + impDetail.getQtyOld();
//            impDetail.setQtyOld(restockQty);
//            repoImport.save(impDetail);

            // ============ update column is_returned in table pos_sale_details to returned
            Optional<SaleDetail> listSaleDetails = saleDetailsRepository.findBySaleIdAndProductId(posSaleID,
                    listDetail.get(i).getProId());
            SaleDetail valueDetail = listSaleDetails.get();
            int qtyForReturned = valueDetail.getQtyReturned() + val.getQty();
            int checkQty = valueDetail.getQty() - qtyForReturned;
            if (checkQty != 0) {
                valueDetail.setQtyReturned(qtyForReturned);
                saleDetailsRepository.save(valueDetail);
            } else {
                valueDetail.setIsReturned("returned");
                valueDetail.setQtyReturned(valueDetail.getQty());
                saleDetailsRepository.save(valueDetail);
            }

        }

        for (int i = 0; i < listDetail.size(); i++) {
            int proId = listDetail.get(i).getProId();
            var item = listDetail.get(i);

            System.out.println("item qty ============ " + item.getQty());

            Product product = productRepository.findById(proId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id : " + proId)
            );
            List<SaleFiFo> saleFiFos = saleFiFoRepository.findByPaymentNoAndSaleQtyGreaterThanAndProductOrderByLocalDateDesc(re.getPaymentNo(), 0, product);
            int countSaleQty = 0;
            for( SaleFiFo s : saleFiFos ) {
                countSaleQty += s.getSaleQty();
            }


            if (item.getQty() == countSaleQty) {
                System.out.println("1111111111111111111111");
                for (SaleFiFo val : saleFiFos) {
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
                    ImportDetail detail = repoImport.getImpIdAndProductAndLocalDate(
                            val.getAnImport().getId(),
                            val.getProduct().getId(),
                            val.getLocalDate()
                    );
                    int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();
                    int qty = qtyOld + val.getSaleQty();
                    detail.setQtyOld(qty);
                    repoImport.save(detail);

                    SaleFiFo saleFiFo = saleFiFoRepository.findById(val.getId()).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale FiFo not found with id : " + val.getId())
                    );
                    saleFiFo.setSaleQty(0);
                    saleFiFoRepository.save(saleFiFo);
                }
            } else {
                System.out.println("2222222222222222222222222222222");
                int _itemQty = item.getQty();
                for ( int j = 0 ; j < saleFiFos.size() ; j++ ) {
                    var val = saleFiFos.get(j);


                    System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                    SaleFiFo saleFiFo = saleFiFoRepository.findById(val.getId()).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale FiFo not found with id : " + val.getId())
                    );


                    System.out.println("hhhhhhhhhhhhhhh = " + _itemQty + " " + saleFiFo.getSaleQty());

                    ImportDetail detail = repoImport.getImpIdAndProductAndLocalDate(
                            val.getAnImport().getId(),
                            val.getProduct().getId(),
                            val.getLocalDate()
                    );

                    if ( j == 0 && _itemQty <= saleFiFo.getSaleQty()) {

                        int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();
                        int qty = qtyOld + item.getQty();
                        detail.setQtyOld(qty);
                        repoImport.save(detail);

                        saleFiFo.setSaleQty(saleFiFo.getSaleQty() - item.getQty());
                        saleFiFoRepository.save(saleFiFo);
                        break;
                    }

                      _itemQty = _itemQty - saleFiFo.getSaleQty();
                    if (_itemQty > 0) {

                        int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();
                        detail.setQtyOld(qtyOld + saleFiFo.getSaleQty());
                        repoImport.save(detail);

                        saleFiFo.setSaleQty(0);
                        saleFiFoRepository.save(saleFiFo);
                    } else {


                        int _q = saleFiFo.getSaleQty() + _itemQty; // _itemQty can be -1

                        int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();

                        detail.setQtyOld(qtyOld + _q);
                        repoImport.save(detail);

                        saleFiFo.setSaleQty(_q);
                        saleFiFoRepository.save(saleFiFo);
                    }

                }
            }
        }


        int saleId = repoDetail.getSaleId(re.getPaymentNo(), JavaConstant.currentDate);
        Optional<Sale> dataSale = repoSale.findById(saleId);
        Sale result = dataSale.get();
        double valueReturn = result.getTotal().doubleValue() - sumTotalReturn;

        result.setSaleIsReturn("returned");
        result.setTotalReturn(BigDecimal.valueOf(sumTotalReturn - result.getDiscount().doubleValue()));
        result.setTotalMinusTotalReturn(BigDecimal.valueOf(valueReturn));
        repoSale.save(result);


        return reprintService.readData(re.getPaymentNo(), re);

    }

    public ProductProjection searchProdcutByBarcode(String barcode) {
        ProductProjection data = repo.getProductByBarcode(barcode);
        return data;
    }
}
