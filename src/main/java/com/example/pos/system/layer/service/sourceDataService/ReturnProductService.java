package com.example.pos.system.layer.service.sourceDataService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.*;
import com.example.pos.system.domain.payment.Payment;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.domain.sourceData.Reason;
import com.example.pos.system.domain.sourceData.ReturnDetails;
import com.example.pos.system.domain.sourceData.ReturnProduct;
import com.example.pos.system.domain.stock.ImportDetail;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.user_permission.reports.report_inventoory.ReportInventoryService;
import com.example.pos.system.feature.user_permission.reports.report_inventoory.dto.ReportInventoryRequest;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import com.example.pos.system.layer.repository.SaleDetailsRepository;
import com.example.pos.system.layer.repository.SaleFiFoRepository;
import com.example.pos.system.layer.repository.SaleRepository;
import com.example.pos.system.layer.repository.paymentRepository.PaymentRepository;
import com.example.pos.system.layer.repository.productProjection.ProductProjection;
import com.example.pos.system.layer.repository.sourceDataRepository.ReasonRepository;
import com.example.pos.system.layer.repository.sourceDataRepository.ReturnDetailsRepository;
import com.example.pos.system.layer.repository.sourceDataRepository.ReturnProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.pos.system.layer.service.paymentService.ReprintService;

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

    @Autowired
    private ReportInventoryService reportInventoryService;

    @Autowired
    private ReasonRepository reasonRepository;


    public Map<String, Object> returnProduct(ReturnProduct re) {

        String time = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(Calendar.getInstance().getTime());

        Reason reason = reasonRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(re.getReasonId(), "return")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reason not found with id : " + re.getReasonId()));

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
            // Get product ID and item from listDetail
            int proId = listDetail.get(i).getProId();
            var item = listDetail.get(i);

            // Print item quantity for debugging
            System.out.println("item qty ============ " + item.getQty());

            // Retrieve product by ID from product repository, throw exception if not found
            Product product = productRepository.findById(proId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id : " + proId)
            );

            // Fetch all SaleFiFo entries for the given paymentNo, with saleQty > 0, and ordered by localDate descending
            List<SaleFiFo> saleFiFos = saleFiFoRepository.findByPaymentNoAndSaleQtyGreaterThanAndProductOrderByLocalDateTimeDesc(re.getPaymentNo(), 0, product);

            // Calculate total sale quantity from fetched SaleFiFo entries
            int countSaleQty = 0;
            for (SaleFiFo s : saleFiFos) {
                countSaleQty += s.getSaleQty();
            }

            // Check if item quantity matches total sale quantity
            if (item.getQty() == countSaleQty) {
                // Process if item quantity matches total sale quantity

                // Iterate through each SaleFiFo entry
                for (SaleFiFo val : saleFiFos) {
                    // Retrieve ImportDetail for the SaleFiFo entry
                    ImportDetail detail = repoImport.getImpIdAndProductAndLocalDate(
                            val.getAnImport().getId(),
                            val.getProduct().getId(),
                            val.getLocalDate()
                    );

                    // Calculate quantity old (qtyOld) or initialize to 0 if null
                    int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();


                    // qty that return
                    int itemReturnQty = val.getSaleQty();

                    // check reason
                    // not add qty to stock
                    // reason : it's damaged or expired

                    if( reason.getReason().equals("Damaged") || reason.getReason().equals("Expired") ) {
                        itemReturnQty = 0;
                    }

                    // Calculate new quantity (qty) by adding current sale quantity
                    int qty = qtyOld + itemReturnQty;

                    // Update qtyOld with new calculated quantity
                    detail.setQtyOld(qty);

                    // Save updated ImportDetail
                    repoImport.save(detail);

                    // Set sale quantity to 0 for the current SaleFiFo entry
                    SaleFiFo saleFiFo = saleFiFoRepository.findById(val.getId()).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale FiFo not found with id : " + val.getId())
                    );
                    saleFiFo.setSaleQty(0);
                    saleFiFoRepository.save(saleFiFo);
                }
            } else {
                // Process if item quantity does not match total sale quantity

                // Initialize _itemQty to item quantity
                int _itemQty = item.getQty();
                // Iterate through each SaleFiFo entry
                for (int j = 0; j < saleFiFos.size(); j++) {
                    var val = saleFiFos.get(j);
                    // Retrieve SaleFiFo entry by ID
                    SaleFiFo saleFiFo = saleFiFoRepository.findById(val.getId()).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale FiFo not found with id : " + val.getId())
                    );

                    // Retrieve ImportDetail for the SaleFiFo entry
                    ImportDetail detail = repoImport.getImpIdAndProductAndLocalDate(
                            val.getAnImport().getId(),
                            val.getProduct().getId(),
                            val.getLocalDate()
                    );

                    // Check if this is the first SaleFiFo entry and item quantity is less than or equal to sale quantity
                    if (j == 0 && _itemQty <= saleFiFo.getSaleQty()) {
                        // Update ImportDetail with new quantity
                        int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();

                        // qty that return
                        int itemReturnQty = item.getQty();

                        // check reason
                        // not add qty to stock
                        // reason : it's damaged or expired
                        if( reason.getReason().equals("Damaged") || reason.getReason().equals("Expired") ) {
                            itemReturnQty = 0;
                        }

                        int qty = qtyOld + itemReturnQty;


                        detail.setQtyOld(qty);
                        repoImport.save(detail);

                        // Update SaleFiFo with adjusted sale quantity
                        saleFiFo.setSaleQty(saleFiFo.getSaleQty() - item.getQty());
                        saleFiFoRepository.save(saleFiFo);
                        break;
                    }

                    // Subtract sale quantity from _itemQty
                    _itemQty = _itemQty - saleFiFo.getSaleQty();

                    // If _itemQty is greater than 0, update ImportDetail and SaleFiFo accordingly
                    if (_itemQty > 0) {
                        int qtyOld = detail.getQtyOld() == null ? 0 : detail.getQtyOld();
                        detail.setQtyOld(qtyOld + saleFiFo.getSaleQty());
                        repoImport.save(detail);

                        saleFiFo.setSaleQty(0);
                        saleFiFoRepository.save(saleFiFo);
                    } else {
                        // If _itemQty is less than or equal to 0, adjust quantities for ImportDetail and SaleFiFo
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


        // add report to reportInventory
        // add report

        re.getReasonId();


        List<ReportInventoryRequest> reportInventoryRequests = new ArrayList<>();
        for (int i = 0; i < listDetail.size(); i++) {
            var value = listDetail.get(i);

            if (reason.getReason().equals("Damaged") || reason.getReason().equals("Expired")) { // return but not plus qty
                reportInventoryRequests.add(ReportInventoryRequest.builder()
                        .productId(value.getProId())
                        .impDate(JavaConstant.currentDate)
                        .stockInQty(0)
                        .stockOutQty(0)
                        .returnInQty(0)
                        .returnOutQty(value.getQty())
                        .build());
            } else { // return stock in
                reportInventoryRequests.add(ReportInventoryRequest.builder()
                        .productId(value.getProId())
                        .impDate(JavaConstant.currentDate)
                        .stockInQty(0)
                        .stockOutQty(0)
                        .returnInQty(value.getQty())
                        .build());
            }


        }
        reportInventoryService.create(reportInventoryRequests);


        return reprintService.readData(re.getPaymentNo(), re);

    }

    public ProductProjection searchProdcutByBarcode(String barcode) {
        ProductProjection data = repo.getProductByBarcode(barcode);
        return data;
    }
}
