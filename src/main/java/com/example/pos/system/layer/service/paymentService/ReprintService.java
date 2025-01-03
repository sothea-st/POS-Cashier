package com.example.pos.system.layer.service.paymentService;

import com.example.pos.system.layer.DTO.ReturnDetailsProduct;
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.settings.Company;
import com.example.pos.system.domain.payment.Payment;
import com.example.pos.system.domain.projection.PaymentProjection;
import com.example.pos.system.domain.projection.SaleDetailProjection;
import com.example.pos.system.domain.sourceData.ReturnProduct;
import com.example.pos.system.layer.repository.SaleDetailsRepository;
import com.example.pos.system.layer.repository.UserRepository;
import com.example.pos.system.layer.repository.companyRepository.CompanyRepository;
import com.example.pos.system.layer.repository.paymentRepository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;

@Service
public class ReprintService {
    @Autowired
    private PaymentRepository repo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SaleDetailsRepository saleDetailRepo;

    public Map<String, Object> readData(String paymentNo) {

        HashMap<String, Object> map = new HashMap<>();
        Company c = companyRepo.getInfoCompany();
        map.put("companyName", c.getCompanyName());
        map.put("companyAddres", c.getAddress());
        map.put("companyContact", c.getContact());
        map.put("companyLogo", c.getPhoto());
        map.put("vattin", c.getVattin());
        PaymentProjection paymentData = null;
        if (paymentNo.isEmpty()) {
            paymentData = repo.getPaymentDataWithoutPaymentNo();
        } else {
            paymentData = repo.getPaymentDataWithPaymentNo(paymentNo);
        }

        map.put("total", paymentData.getTotal());
        map.put("receiveUsd", paymentData.getReceive_usd());
        map.put("changeUsd", paymentData.getChange_usd());
        map.put("receiveKhr", paymentData.getReceive_khr());
        map.put("changeKhr", paymentData.getChange_khr());
        map.put("paymentNo", paymentData.getPayment_no());
        map.put("paymentBarcode", paymentData.getPayment_barcode());
        map.put("saleDate", paymentData.getSale_date());
        map.put("customerType", paymentData.getCustomer_type());
        map.put("returned", paymentData.getIs_return());
        map.put("discount", paymentData.getDiscount().doubleValue());
        List<SaleDetailProjection> dataSaleDetails = null;

        dataSaleDetails = saleDetailRepo.getDataDetail(paymentData.getUser_id(), paymentData.getSale_id());

        map.put("saleDetails", dataSaleDetails);
        String empName = userRepo.getNameEmp(paymentData.getUser_id());
        map.put("empName", empName);
        return map;
    }

    public Map<String, Object> readData(String paymentNo, ReturnProduct re) {

        HashMap<String, Object> map = new HashMap<>();
        Company c = companyRepo.getInfoCompany();
        map.put("companyName", c.getCompanyName());
        map.put("companyAddres", c.getAddress());
        map.put("companyContact", c.getContact());
        map.put("companyLogo", c.getPhoto());
        map.put("vattin", c.getVattin());
        PaymentProjection paymentData = null;
        if (paymentNo.isEmpty()) {
            paymentData = repo.getPaymentDataWithoutPaymentNo();
        } else {
            paymentData = repo.getPaymentDataWithPaymentNo(paymentNo);
        }

        map.put("total", paymentData.getTotal());
        map.put("receiveKhr", paymentData.getReceive_khr());
        map.put("changeKhr", paymentData.getChange_khr());
        map.put("receiveUsd", paymentData.getReceive_usd());
        map.put("changeUsd", paymentData.getChange_usd());

        String[] listPosId = paymentData.getPayment_no().split("-");
        String posID = listPosId[1];

        int countReturn = repo.countSaledReturn(JavaConstant.currentDate);

        String _returnInvoiceNumber = returnInvoiceNumber(countReturn, posID);

        Optional<Payment> _dataPayment = repo.findByPaymentNos(paymentData.getPayment_no());
        Payment _p = _dataPayment.get();
        _p.setReturnNumber(_returnInvoiceNumber);
        repo.save(_p);

        String _newInvoice = paymentData.getPayment_no() + " = " + _returnInvoiceNumber;

        map.put("paymentNo", _newInvoice);
        map.put("paymentBarcode", paymentData.getPayment_barcode());
        map.put("saleDate", paymentData.getSale_date());
        map.put("customerType", paymentData.getCustomer_type());
        map.put("returned", paymentData.getIs_return());
        double sumDiscontAmt = 0;
        List<ReturnDetailsProduct> dataSaleDetails = new ArrayList<>();

        for (int i = 0; i < re.getDataDetails().size(); i++) {
            var data = re.getDataDetails().get(i);
            ReturnDetailsProduct sale = new ReturnDetailsProduct(data.getQty(), data.getPrice(), data.getProName(),
                    data.getBarcode());
            sumDiscontAmt += data.getDiscountAmt();
            dataSaleDetails.add(sale);
        }
        map.put("discount", sumDiscontAmt);
        map.put("saleDetails", dataSaleDetails);
        String empName = userRepo.getNameEmp(paymentData.getUser_id());
        map.put("empName", empName);
        return map;
    }

    String returnInvoiceNumber(int count, String posId) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String _year = "" + currentYear;
        _year = _year.substring(2, _year.length());

        int month = currentDate.getMonthValue();
        String _m = "";
        if (month > 9) {
            _m = "" + month;
        } else {
            _m = "0" + month;
        }

        int day = currentDate.getDayOfMonth();
        String _d = "";
        if (day > 9) {
            _d = "" + day;
        } else {
            _d = "0" + day;
        }
        String invoice = "";

        if (count < 10) {
            invoice += "00" + count;
        } else if (count < 100) {
            invoice += "0" + count;
        } else {
            invoice += "" + count;
        }
        String _value = "SCN101-" + posId + "-" + _year + "" + _m + "" + _d + "" + invoice;
        return _value;
    }

}
