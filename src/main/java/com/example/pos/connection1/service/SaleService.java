package com.example.pos.connection1.service;

import com.example.pos.connection1.DTO.ReportRequest;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.controller.generateBarcode.BarcodeGenerator;
import com.example.pos.connection1.entity.*;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.people.Customer;
import com.example.pos.connection1.feature.product.ProductRepository;
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
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    private ProductRepository productRepository;

    //    List<ReportSaledResponse>
    public List<ReportSaledResponse> searchReportSaled(String dateFromValue, String dateToValue, Integer pageNumber,
                                                       Integer pageSize, Integer userId, String searchValue) {

        LocalDate dateFrom = LocalDate.parse(dateFromValue);
        LocalDate dateTo = LocalDate.parse(dateToValue);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        List<ReportSaledProjection> resultSearch = new ArrayList<>();
        if (dateFrom.isAfter(dateTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The field dateFrom must be smaller than field dateTo .");
        }

        if (dateTo.isAfter(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The field dateTo can not greater than current date : " + formattedDate + ".");
        }


        resultSearch = repo.searchReportSale(
                dateFromValue,
                dateToValue,
                searchValue,
                userId,
                pageSize,
                pageNumber
        );
        return reportResponse(resultSearch, null);
    }

    public List<ReportSaledResponse> reportSaled(String dateFromValue, String dateToValue, Integer pageNumber,
                                                 Integer pageSize, Integer userId) {

        LocalDate dateFrom = LocalDate.parse(dateFromValue);
        LocalDate dateTo = LocalDate.parse(dateToValue);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        if (dateFrom.isAfter(dateTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The field dateFrom must be smaller than field dateTo .");
        }

        if (dateTo.isAfter(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The field dateTo can not greater than current date : " + formattedDate + ".");
        }

        List<ReportSaledProjection> reportSaled = new ArrayList<>();

        if (dateFromValue.equals(dateToValue) && userId != null) {
            String[] arrDateTo = dateFromValue.split("-");
            String dateToStr = arrDateTo[2] + "-" + arrDateTo[1] + "-" + arrDateTo[0];
            reportSaled = repo.getReportSaleInToday(dateToStr, userId);
            return reportResponse(reportSaled, null);
        }

        reportSaled = repo.getReportSaleds(dateFrom, dateTo, userId, pageNumber, pageSize);
        return reportResponse(reportSaled, null);
    }

    private List<ReportSaledResponse> reportResponse(List<ReportSaledProjection> reportSaled,
                                                     String searchProductName) {
        List<ReportSaledResponse> listResponse = new ArrayList<>();

        reportSaled.forEach(report -> {

            double totalSaledExcludeVAT = 0;
            double vatAmt = 0;
            double plt = 0;
            double netSale = 0;
            double margin = 0;
            double total = report.getAmount().doubleValue();

            if (report.getDiscount_case() != null) {
                total = report.getAmount().doubleValue() - report.getDiscount(); // getDiscount is value already
                // calculate
            }

            String _total = String.format("%.2f", total / 1.1);
            String _totalSaledExludeVAT = String.format("%.2f", ((totalSaledExcludeVAT / 1.1) * 0.1));
            String _netSale = String.format("%.2f", total - vatAmt - plt);
            String _margin = String.format("%.2f", netSale - report.getCost().doubleValue());

            totalSaledExcludeVAT = Double.parseDouble(_total);
            vatAmt = Double.parseDouble(_totalSaledExludeVAT);
            netSale = Double.parseDouble(_netSale);
            margin = Double.parseDouble(_margin);

            if (report.getTax_name().equals("PLT")) {
                plt = (totalSaledExcludeVAT / 1.006) * 0.2 * 0.03;
            }
            ReportSaledResponse reportSaledResponse = ReportSaledResponse.builder()
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
                    .barcode(report.getBarcode())
                    .invoiceNumber(report.getinvoice_number())
                    .userName(report.getfull_name() == null ? null : report.getfull_name())
                    .build();

            if (searchProductName != null) {
                if (report.getPro_name_en().toLowerCase().contains(searchProductName.toLowerCase())) {
                    listResponse.add(reportSaledResponse);
                }
            } else {
                listResponse.add(reportSaledResponse);
            }
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
        LocalDate currentDate = LocalDate.now();
        // Define a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

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
        sale.setDateLocal(currentDate);
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
            Optional<Product> product = productRepository.findById(productId);
            if (product != null) {

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

                int qtyCheckStoke =0;
                List<ImportDetail> lists = repoImp.findByProductAndStatusTrueAndIsDeletedFalseAndQtyOldGreaterThanOrderByLocalDateAsc(product, 0);
                for (int j = 0 ; j < lists.size() ; j++) {
                    var data = lists.get(j);

                    if( j == 0  && data.getQtyOld() >= qtyNew) {
                        int qty = data.getQtyOld() - qtyNew;
                        Optional<ImportDetail> updateDetail = Optional.ofNullable(repoImp.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                        if(updateDetail.isPresent()) {
                            ImportDetail update = updateDetail.get();
                            update.setQtyOld(qty);
                            repoImp.save(update);
                        }
                        break;
                    }


                    if(  j == 0  && qtyNew > data.getQtyOld() ) {
                        qtyCheckStoke = qtyNew - data.getQtyOld();
                        Optional<ImportDetail> updateDetail = Optional.ofNullable(repoImp.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                        if(updateDetail.isPresent()) {
                            ImportDetail update = updateDetail.get();
                            update.setQtyOld(0);
                            repoImp.save(update);
                        }
                    } else {
                        if( data.getQtyOld() >= qtyCheckStoke ) {
                            int qty = data.getQtyOld() - qtyCheckStoke;
                            Optional<ImportDetail> updateDetail = Optional.ofNullable(repoImp.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                            if(updateDetail.isPresent()) {
                                ImportDetail update = updateDetail.get();
                                update.setQtyOld(qty);
                                repoImp.save(update);
                            }
                            break;
                        } else {
                            qtyCheckStoke = qtyCheckStoke - data.getQtyOld();
                            Optional<ImportDetail> updateDetail = Optional.ofNullable(repoImp.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                            if(updateDetail.isPresent()) {
                                ImportDetail update = updateDetail.get();
                                update.setQtyOld(0);
                                repoImp.save(update);
                            }
                        }
                    }
                }


//            **************** old mechanism **************
//            ImportDetail getQtyOld = repoImp.getDataImportDetail(productId);
//            int qtyOld = getQtyOld.getQtyOld();
//            int qty = qtyOld - qtyNew;
//
//            Optional<ImportDetail> getImportDetail = repoImp.findByImpId(productId);
//            ImportDetail obj = getImportDetail.get();
//            obj.setQtyOld(qty);
//            repoImp.save(obj);


            }
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
