package com.example.pos.connection1.service.sourceDataService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.sourceData.ReturnDetails;
import com.example.pos.connection1.entity.sourceData.ReturnProduct;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.SaleDetailsRepository;
import com.example.pos.connection1.repository.SaleRepository;
import com.example.pos.connection1.repository.paymentRepository.PaymentRepository;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.repository.sourceDataRepository.ReturnDetailsRepository;
import com.example.pos.connection1.repository.sourceDataRepository.ReturnProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pos.connection1.service.paymentService.ReprintService;

import java.util.*;
import jakarta.servlet.http.HttpSession;

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

            // restock qty back
            Optional<ImportDetail> dataImp = repoImport.findByImpId(proId);
            ImportDetail impDetail = dataImp.get();
            int restockQty = qtyReturn + impDetail.getQtyOld();
            impDetail.setQtyOld(restockQty);
            repoImport.save(impDetail);

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
