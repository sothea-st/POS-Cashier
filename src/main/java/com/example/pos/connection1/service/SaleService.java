package com.example.pos.connection1.service;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.controller.generateBarcode.BarcodeGenerator;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.people.Customer;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.SaleDetailsRepository;
import com.example.pos.connection1.repository.SaleRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.repository.companyRepository.CompanyRepository;
import com.example.pos.connection1.repository.paymentRepository.PaymentRepository;
import com.example.pos.connection1.repository.peopleRepository.CustomerRepository;
import com.example.pos.connection1.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.connection1.service.paymentService.ReprintService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.Year;
import java.util.*;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private SaleDetailsRepository repoDetail;

    @Autowired
    private ImportDetailRepository repoImp;

    @Autowired
    private PaymentRepository payRepo;

    @Autowired
    private CompanyRepository repoCompany;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private CustomerRepository cusRepo;

    @Autowired
    private OpenShiftRepository repoOpen;

    @Autowired
    BarcodeGenerator barcodeGenerator;

    @Autowired
    private FileStoreRepository fileStore;

    @Autowired
    private ReprintService reprintService;

    // this function will return invoice
    public HashMap<String, Object> saleProduct(Sale s) throws Exception {
        var createBy = session.getAttribute(JavaConstant.userId);

        int userId = s.getUserId();
        // System.out.println("user id = " + userId);
        HashMap<String, Object> map = new HashMap<>();
        String posId = repoOpen.getPosId(s.getUserCode(), JavaConstant.currentDate);
        Sale sale = new Sale();
        sale.setUserId(userId);
        sale.setPosId(posId);
        sale.setUserCode(s.getUserCode());
        sale.setSaleDate(JavaConstant.currentDate);
        sale.setDiscount(s.getDiscount());
        sale.setSubTotal(s.getSubTotal());
        sale.setDeliveryFee(s.getDeliveryFee());
        // sale.setTotal(s.getTotal());
        sale.setTotal(s.getTotal());
        sale.setDiscountCase(s.getDiscountCase());
        sale.setSaleIsReturn(s.getSaleIsReturn());
        // sale.setDataPay(new Payment(userId, posId, posId, userId, null, null, null, posId, null, null, posId, userId, userId, posId, posId, posId, userId, null, false, false));
        sale.setCreateBy(userId);

        Customer cus = s.getCustomer();
        String cusId = null;
        int countId = cusRepo.countRecord();
        countId++;
        if (cus != null) {
            cusId = customerId(countId);
            addCustomer(cus, cusId);
            sale.setCusId(cusId);
        }
        repo.save(sale);

        int saleId = sale.getId();
        List<SaleDetail> details = s.getDataSale();

        for (int i = 0; i < details.size(); i++) {
            var detail = details.get(i);
            int productId = detail.getProductId();
            int qtyNew = detail.getQty();

            SaleDetail dataDetail = new SaleDetail();
            dataDetail.setSaleId(saleId);
            dataDetail.setProductId(productId);
            dataDetail.setQty(qtyNew);
            dataDetail.setPrice(detail.getPrice());
            dataDetail.setAmount(detail.getAmount());
            dataDetail.setDiscount(detail.getDiscount());
            dataDetail.setCreateBy(userId);
            dataDetail.setDiscountType(detail.getDiscountType());
            repoDetail.save(dataDetail);

            ImportDetail getQtyOld = repoImp.getDataImportDetail(productId);
            int qtyOld = getQtyOld.getQtyOld();
            int qty = qtyOld - qtyNew;

            Optional<ImportDetail> getImportDetail = repoImp.findByImpId(productId);
            ImportDetail obj = getImportDetail.get();
            obj.setQtyOld(qty);
            repoImp.save(obj);
        }

        // save payment
        Payment p = s.getDataPay();
        int count = payRepo.countRecord();
        count++;
        String paymentNo = paymentNo(count, s.getPosId());
        String paymentBarcode = paymentBarcode(count);
        addPayment(paymentNo, saleId, p, userId, paymentBarcode);
        // Company companyInfo = repoCompany.getInfoCompany();
        // map.put("companyName", companyInfo.getCompanyName());
        // map.put("vattin", companyInfo.getVattin());
        // map.put("companyContact", companyInfo.getContact());
        // map.put("companyAddress", companyInfo.getAddress());
        // map.put("companyLogo", companyInfo.getPhoto());
        // String empName = repoUser.getNameEmp(userId);
        // map.put("empName", empName);
        // map.put("saleDate", s.getSaleDate());
        // map.put("paymentNo", paymentNo);
        // map.put("paymentBarcode", paymentBarcode);
        // map.put("total", s.getTotal());
        // // map.put("totalKhr", s.getTotal());
        // map.put("receiveUsd", p.getReceiveUsd());
        // map.put("receiveKhr", p.getReceiveKhr());
        // map.put("changeUsd", p.getChangeUsd());
        // map.put("changeKhr", p.getChangeKhr());
        // map.put("receiveUsd", p.getReceiveUsd());
        // map.put("receiveKhr", p.getReceiveKhr());
        // map.put("customerType", "អតិថិជនទូទៅ");
        // map.put("returned", null);

        // List<SaleDetailProjection> listProjection = repoDetail.getDataDetail(userId ,
        // saleId);
        // map.put("saleDetails", listProjection);
        return reprintService.readData("");

    }

    public void addCustomer(Customer cus, String cusId) {
        Customer cusData = new Customer();
        cusData.setCusName(cus.getCusName());
        cusData.setContact(cus.getContact());
        // cusData.setEarning(cus.getEarning());
        // cusData.setEmail(cus.getEmail());
        // cusData.setCoupon(cus.getCoupon());
        cusData.setGender(cus.getGender());
        cusData.setNationality(cus.getNationality());
        cusData.setCustomerId(cusId);
        cusRepo.save(cusData);
    }

    public String customerId(int countId) {
        String cusId = "";
        if (countId < 10) {
            cusId = "000" + countId;
        } else if (countId < 100) {
            cusId = "00" + countId;
        } else if (countId < 1000) {
            cusId = "0" + countId;
        } else {
            cusId = "" + countId;
        }
        return cusId;
    }

    public void addPayment(String paymentNo, int saleId, Payment p, int createBy, String paymentBarcode)
            throws Exception {
        Payment data = new Payment();
        data.setPaymentBarcode(paymentBarcode);
        data.setPaymentNo(paymentNo);
        data.setSaleId(saleId);
        data.setReceiveKhr(p.getReceiveKhr());
        data.setReceiveUsd(p.getReceiveUsd());
        data.setRemainingKhr(p.getRemainingKhr());
        data.setRemainingUsd(p.getRemainingUsd());
        data.setChangeKhr(p.getChangeKhr());
        data.setChangeUsd(p.getChangeUsd());
        data.setPaymentType(p.getPaymentType());
        data.setCustomerTypeId(p.getCustomerTypeId());
        data.setSourceId(p.getSourceId());
        data.setDiscountType(p.getDiscountType());
        data.setDiscountValue(p.getDiscountValue());
        data.setCreateBy(createBy);
        payRepo.save(data);

        BufferedImage barcode = barcodeGenerator.generateUSPSBarcodeImage(paymentBarcode);
        byte[] bytes = BarcodeGenerator.bufferedImageToByteArray(barcode, "jpg");
        // save information image to table pos_file
        FileStore f = new FileStore(paymentBarcode, paymentBarcode, "image/jpeg", bytes);
        fileStore.save(f);
        
    }

    String paymentNo(int count, String posId) {
        int currentYear = Year.now().getValue();
        String _year = "" + currentYear;
        _year = _year.substring(2, _year.length());
        // System.out.println("Current Year: " + _year.substring(2, _year.length()));
        String paymentNo = "101-" + posId + "-"+_year+"-";
        if (count < 10) {
            paymentNo += "00000" + count;
        } else if (count < 100) {
            paymentNo += "0000" + count;
        } else if (count < 1000) {
            paymentNo += "000" + count;
        } else if (count < 10000) {
            paymentNo += "00" + count;
        } else if (count < 100000) {
            paymentNo += "0" + count;
        } else if (count < 1000000) {
            paymentNo += "" + count;
        }
        return paymentNo;
    }

    String paymentBarcode(int count) {
        String paymentNo = "";
        if (count < 10) {
            paymentNo += "00000" + count;
        } else if (count < 100) {
            paymentNo += "0000" + count;
        } else if (count < 1000) {
            paymentNo += "000" + count;
        } else if (count < 10000) {
            paymentNo += "00" + count;
        } else if (count < 100000) {
            paymentNo += "0" + count;
        } else if (count < 1000000) {
            paymentNo += "" + count;
        }
        return paymentNo;
    }

}
