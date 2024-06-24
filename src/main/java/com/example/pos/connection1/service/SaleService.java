package com.example.pos.connection1.service;

import com.example.pos.connection1.DTO.ReportRequest;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.controller.generateBarcode.BarcodeGenerator;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.people.Customer;
import com.example.pos.connection1.projections.ReportImport.ReportSaledProjection;
import com.example.pos.connection1.projections.ReportImport.ReportSaledResponse;
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
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.time.*;

@Service
@Slf4j
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

    public List<ReportSaledResponse> reportSaled(ReportRequest reportRequest) {
        List<ReportSaledProjection> reportSaled = repo.getReportSaled(reportRequest.dateFrom(), reportRequest.dateTo());
        List<ReportSaledResponse> listResponse = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#0.00");
        reportSaled.forEach(report -> {

            double totalSaledExcludeVAT = 0;
            double vatAmt = 0;
            double plt=0;
            double netSale=0;
            double margin=0;
            double total =report.getAmount().doubleValue();


            log.info("vat : " + report.getTax_name());
            if (report.getDiscount_case() != null) {
                total = report.getAmount().doubleValue() - report.getDiscount();  // getDiscount is value already calculate
            }
            totalSaledExcludeVAT = Double.parseDouble(df.format(total/1.1));
            vatAmt = Double.parseDouble(df.format((totalSaledExcludeVAT/1.1)*0.1));
            netSale = Double.parseDouble(df.format(total - vatAmt - plt));
            margin = Double.parseDouble(df.format(netSale - report.getCost().doubleValue()));

            if( report.getTax_name().equals("PLT") ) {
                plt = (totalSaledExcludeVAT/1.006)*0.2*0.03;
            }

            listResponse.add(ReportSaledResponse.builder()
                    .saleDate(report.getSale_date())
                    .proNameEn(report.getPro_name_en())
                    .proImageName(report.getPro_image_name())
                    .qty(report.getQty())
                    .discountCase(report.getDiscount_case())
                    .discountPercentage(report.getdiscount_percentage())
                    .discount(report.getDiscount())
                    .price(report.getPrice())
                    .amountWithTax(report.getAmount())
                    .taxType(report.getTax_name())
                    .totalSaledExcludeVAT(BigDecimal.valueOf(totalSaledExcludeVAT))
                    .vatAmt(BigDecimal.valueOf(vatAmt))
                    .plt(BigDecimal.valueOf(plt))
                    .netSale(BigDecimal.valueOf(netSale))
                    .cost(report.getCost())
                    .margin(BigDecimal.valueOf(margin))
                    .build());
        });

        return listResponse;

    }

    // this function will return invoice
    public Map<String, Object> saleProduct(Sale s) throws Exception {
        var createBy = session.getAttribute(JavaConstant.userId);

        int userId = s.getUserId();
        String posId = s.getPosId();
        int count = payRepo.countSale(JavaConstant.currentDate);

        count++;

        String paymentNo = paymentNo(count, posId);

        String paymentBarcode = paymentBarcode(count);

        Sale sale = new Sale();
        sale.setUserId(userId);
        sale.setPosId(posId);
        sale.setUserCode(s.getUserCode());
        sale.setSaleDate(JavaConstant.currentDate);
        sale.setDiscount(s.getDiscount());
        sale.setSubTotal(s.getSubTotal());
        sale.setDeliveryFee(s.getDeliveryFee());
        sale.setActive("Active");
        sale.setTotal(s.getTotal());
        sale.setDiscountCase(s.getDiscountCase());
        sale.setSaleIsReturn(s.getSaleIsReturn());
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

        addPayment(paymentNo, saleId, p, userId, paymentBarcode, posId);

        return reprintService.readData("");

    }

    public void addCustomer(Customer cus, String cusId) {
        Customer cusData = new Customer();
        cusData.setCusName(cus.getCusName());
        cusData.setContact(cus.getContact());
        cusData.setGender(cus.getGender());
        cusData.setNationality(cus.getNationality());
        cusData.setCustomerId(cus.getCustomerId());
        cusData.setPointEarned(cus.getPointEarned());
        cusData.setEmail(cus.getEmail());
        cusData.setCoupon(cus.getCoupon());
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

    public void addPayment(String paymentNo, int saleId, Payment p, int createBy, String paymentBarcode, String posId)
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
        data.setPosId(posId);
        data.setCreateBy(createBy);
        payRepo.save(data);

        BufferedImage barcode = barcodeGenerator.generateUSPSBarcodeImage(paymentBarcode);
        byte[] bytes = BarcodeGenerator.bufferedImageToByteArray(barcode, "jpg");
        // save information image to table pos_file
        FileStore f = new FileStore(paymentBarcode, paymentBarcode, "image/jpeg", bytes);
        fileStore.save(f);

    }

    String paymentNo(int count, String posId) {
        String _value = invoiceId(count);
        String paymentNo = "RIV101-" + posId + "-" + _value;
        return paymentNo;
    }

    String invoiceId(int count) {
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

        String _value = "" + _year + "" + _m + "" + _d + "" + invoice;
        return _value;
    }

    String paymentBarcode(int count) {

        String paymentNo = invoiceId(count);
        // if (count < 10) {
        // paymentNo += "000000" + count;
        // } else if (count < 100) {
        // paymentNo += "00000" + count;
        // } else if (count < 1000) {
        // paymentNo += "0000" + count;
        // } else if (count < 10000) {
        // paymentNo += "000" + count;
        // } else if (count < 100000) {
        // paymentNo += "00" + count;
        // } else if (count < 1000000) {
        // paymentNo += "0" + count;
        // } else {
        // paymentNo += "" + count;
        // }
        return paymentNo;
    }

}
