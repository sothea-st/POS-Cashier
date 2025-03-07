package com.example.pos.system.feature.order_online;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.SaleFiFo;
import com.example.pos.system.domain.User;
import com.example.pos.system.domain.order_online.OrderOnline;
import com.example.pos.system.domain.order_online.OrderOnlineDetail;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.domain.settings.Status;
import com.example.pos.system.domain.stock.Import;
import com.example.pos.system.domain.stock.ImportDetail;
import com.example.pos.system.feature.imports.ImportRepository;
import com.example.pos.system.feature.order_online.dto.OrderOnlineDetailRequest;
import com.example.pos.system.feature.order_online.dto.OrderOnlineRequest;
import com.example.pos.system.feature.order_online.dto.OrderOnlineResponse;
import com.example.pos.system.feature.order_online.dto.OrderProductDetailResponse;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.status.StatusRepository;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import com.example.pos.system.layer.repository.SaleFiFoRepository;
import com.example.pos.system.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderOnlineServiceImp implements OrderOnlineService {

    // inject bean repository
    private final OrderOnlineRepository orderOnlineRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ImportDetailRepository importDetailRepository;
    private final ImportRepository importRepository;
    private final SaleFiFoRepository saleFiFoRepository;
    private final StatusRepository statusRepository;

    @Override
    public ResponseSuccess create(OrderOnlineRequest request) {

        // validate user
        User user = userRepository.findById(request.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + request.createdBy()));

        String currentDate = JavaConstant.formatDateYYYYMMDD(JavaConstant.currentDate);

        long countOrdersToday = orderOnlineRepository.countOrdersToday(LocalDate.parse(currentDate));

        String _paymentNo = generateOrderCode(countOrdersToday);

        OrderOnline orderOnline = new OrderOnline();
        orderOnline.setCreateBy(user);
        orderOnline.setStatus(true);
        orderOnline.setIsDeleted(false);
        orderOnline.setAccept("New"); // Accept , Pick & Pack , Out for Delivery , Delivery , Pay

        orderOnline.setOrderDate(LocalDate.parse(request.orderDate()));
        orderOnline.setOrderNumber(_paymentNo);
        orderOnline.setOrderStatus(request.orderStatus());
        orderOnline.setCustomerId(request.customerId());
        orderOnline.setCustomerName(request.customerName());
        orderOnline.setDeliveryInformation(request.deliveryInformation());
        orderOnline.setTotalAmount(request.totalAmount());
        orderOnline.setPaymentMethod(request.paymentMethod());
        orderOnline.setPaymentStatus(request.paymentStatus());
        orderOnline.setCustomerNote(request.customerNote());

        orderOnline.setSubTotal(request.subTotal());
        orderOnline.setDeliveryFee(request.deliveryFee());
        orderOnline.setDiscount(request.discount());
        orderOnline.setGrandTotal(request.grandTotal());

        List<OrderOnlineDetail> details = new ArrayList<>();

        for (OrderOnlineDetailRequest detail : request.detail()) {

            Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(detail.productId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id : " + detail.productId()));

            if (detail.qtySale() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Qty must be greater than 0");
            }

            OrderOnlineDetail orderOnlineDetail = new OrderOnlineDetail();
            orderOnlineDetail.setOrderOnline(orderOnline);
            orderOnlineDetail.setProduct(product);
            orderOnlineDetail.setQtySale(detail.qtySale());
            orderOnlineDetail.setDiscountPrice(detail.discountPrice());

            details.add(orderOnlineDetail);
        }

        orderOnline.setOrderOnlineDetails(details);

        orderOnlineRepository.save(orderOnline);


        if (orderOnline.getId() > 0) {
            cutStockWithFifo(request.detail(), _paymentNo);
        }

        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, String orderStatus, String paymentStatus) {

        // validate date
        JavaConstant.validationDate(dateFrom, dateTo);

        List<OrderOnlineResponse> list = new ArrayList<>();
        Page<OrderOnline> pages = null;
        long count = 0;

        if (pageNumber == null && pageSize == null) { // when pageNumber is null and pageSize is null
            if (orderStatus == null && paymentStatus == null) { //  when orderStatus is  null and paymentStatus is  null
                pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        null);
            } else if (orderStatus != null && paymentStatus == null){
                if( orderStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            null);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            null);
                }
            } else if (orderStatus == null && paymentStatus != null) {
                if( paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            null);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            paymentStatus,
                            null);
                }
            } else {
                if( orderStatus.equals("All") && paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            null);
                } else if ( orderStatus.equals("All") && !paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            paymentStatus,
                            null);
                }  else if ( !orderStatus.equals("All") && paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            null);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatusAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            paymentStatus,
                            null);
                }
            }
        } else { // when pageNumber is not null and pageSize is not null
            Sort sortByCreateDate = Sort.by(Sort.Direction.DESC, "createDate");
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortByCreateDate);

            if (orderStatus == null && paymentStatus == null) { //  when orderStatus is  null and paymentStatus is  null
                pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest);
            } else if (orderStatus != null && paymentStatus == null){
                if( orderStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            pageRequest);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            pageRequest);
                }
            } else if (orderStatus == null && paymentStatus != null) {
                if( paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            pageRequest);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            paymentStatus,
                            pageRequest);
                }
            } else {
                if( orderStatus.equals("All") && paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            pageRequest);
                } else if ( orderStatus.equals("All") && !paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            paymentStatus,
                            pageRequest);
                }  else if ( !orderStatus.equals("All") && paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            pageRequest);
                } else {
                    pages = orderOnlineRepository.findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatusAndPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            orderStatus,
                            paymentStatus,
                            pageRequest);
                }
            }

        }

        list = pages.getContent().stream()
                .map(this::mapToOrderOnlineResponse).toList();

        count = pages.getTotalElements();

        return JavaCollectionResponse.builder()
                .data(list)
                .count(count)
                .build();
    }


    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, String orderStatus, String paymentStatus, String search) {

        // validate date
        JavaConstant.validationDate(dateFrom, dateTo);

        List<OrderOnlineResponse> list = new ArrayList<>();
        Page<OrderOnline> pages = null;
        long count = 0;

        Sort sortByCreateDate = Sort.by(Sort.Direction.DESC, "createDate");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortByCreateDate);


        if (orderStatus != null && paymentStatus != null) {

            if (orderStatus.equals("All") && paymentStatus.equals("All")) {
                pages = orderOnlineRepository.searchByCustomerNameAndOrderNumber(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        search,
                        pageRequest);
            } else {
                if (orderStatus.equals("All") && !paymentStatus.equals("All")) {
                    pages = orderOnlineRepository.searchByPaymentStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            search,
                            paymentStatus,
                            pageRequest);
                } else if ( !orderStatus.equals("All") && paymentStatus.equals("All") ) {
                    pages = orderOnlineRepository.searchByOrderStatus(
                            LocalDate.parse(dateFrom),
                            LocalDate.parse(dateTo),
                            search,
                            orderStatus,
                            pageRequest);
                }
            }
        } else {


            if (orderStatus == null && paymentStatus == null) {

                pages = orderOnlineRepository.searchByCustomerNameAndOrderNumber(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        search,
                        pageRequest);
            } else if (orderStatus != null && paymentStatus == null) {

                pages = orderOnlineRepository.searchByOrderStatus(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        search,
                        orderStatus,
                        pageRequest);
            } else if (paymentStatus != null && orderStatus == null) {
                pages = orderOnlineRepository.searchByPaymentStatus(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        search,
                        paymentStatus,
                        pageRequest);
            }

        }


        list = pages.getContent().stream()
                .map(this::mapToOrderOnlineResponse).toList();

        count = pages.getTotalElements();

        return JavaCollectionResponse.builder()
                .data(list)
                .count(count)
                .build();
    }


    private void cutStockWithFifo(List<OrderOnlineDetailRequest> details, String paymentNo) {

        for (int i = 0; i < details.size(); i++) {
            OrderOnlineDetailRequest detail = details.get(i);
            int productId = detail.productId();
            int qtyNew = detail.qtySale();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id : " + productId));

            int qtyCheckStoke = 0;
            List<ImportDetail> lists = importDetailRepository.findByProductAndStatusTrueAndIsDeletedFalseAndQtyOldGreaterThanOrderByCreateDateAsc(product, 0);// get list import where product = ? and qtyOld > 0
            // loop lists
            for (int j = 0; j < lists.size(); j++) {
                var data = lists.get(j);

                if (j == 0 && data.getQtyOld() >= qtyNew) {
                    int qty = data.getQtyOld() - qtyNew;
                    Optional<ImportDetail> updateDetail = Optional.ofNullable(importDetailRepository.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                    if (updateDetail.isPresent()) {

                        // update qtyOld to importDetail
                        ImportDetail update = updateDetail.get();
                        update.setQtyOld(qty);
                        importDetailRepository.save(update);

                        // Update product active status when qty = 0
                        if (qty == 0) {
                            // Safely get status
                            Optional<Status> statusOpt = statusRepository.findByStatusName("Inactive");
                            if (statusOpt.isPresent()) { // Ensure status exists
                                product.setProductActive(statusOpt.get());
                                // Save the updated product
                                productRepository.save(product);
                            }
                        }

                        saveFiFo(productId, update.getImpId(), qtyNew, paymentNo, update.getLocalDate(), update.getCreateDate()); // save data as fifo
                    }
                    break;
                }

                if (j == 0 && qtyNew > data.getQtyOld()) {
                    qtyCheckStoke = qtyNew - data.getQtyOld();
                    Optional<ImportDetail> updateDetail = Optional.ofNullable(importDetailRepository.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                    if (updateDetail.isPresent()) {
                        // update qtyOld to importDetail
                        ImportDetail update = updateDetail.get();
                        update.setQtyOld(0);
                        importDetailRepository.save(update);

                        // update product active when qty = 0
                        if (qtyCheckStoke == 0) {

                            Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(1) // status = 1 is inActive
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found with id : 1"));
                            product.setProductActive(status);
                            productRepository.save(product);
                        }

                        saveFiFo(productId, update.getImpId(), data.getQtyOld(), paymentNo, update.getLocalDate(), update.getCreateDate()); // save data as fifo
                    }
                } else {
                    if (data.getQtyOld() >= qtyCheckStoke) {
                        int qty = data.getQtyOld() - qtyCheckStoke;
                        Optional<ImportDetail> updateDetail = Optional.ofNullable(importDetailRepository.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                        if (updateDetail.isPresent()) {
                            // update qtyOld to importDetail
                            ImportDetail update = updateDetail.get();
                            update.setQtyOld(qty);
                            importDetailRepository.save(update);

                            // update product active when qtyCheckStoke = 0
                            if (qtyCheckStoke == 0) {

                                Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(1) // status = 1 is inActive
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found with id : 1"));
                                product.setProductActive(status);
                                productRepository.save(product);
                            }

                            saveFiFo(productId, update.getImpId(), qtyCheckStoke, paymentNo, update.getLocalDate(), update.getCreateDate()); // save data as fifo
                        }
                        break;
                    } else {
                        qtyCheckStoke = qtyCheckStoke - data.getQtyOld();
                        Optional<ImportDetail> updateDetail = Optional.ofNullable(importDetailRepository.getImpIdAndProduct(data.getImpId(), data.getProduct().getId()));
                        if (updateDetail.isPresent()) {
                            // update qtyOld to importDetail
                            ImportDetail update = updateDetail.get();
                            update.setQtyOld(0);
                            importDetailRepository.save(update);

                            // update product active when qtyCheckStoke = 0
                            if (qtyCheckStoke == 0) {

                                Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(1) // status = 1 is inActive
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found with id : 1"));
                                product.setProductActive(status);
                                productRepository.save(product);
                            }

                            saveFiFo(productId, update.getImpId(), data.getQtyOld(), paymentNo, update.getLocalDate(), update.getCreateDate());
                        }
                    }
                }

            }
        }

    }


    private void saveFiFo(int productId, int impId, int qtyNew, String paymentNo, LocalDate localDate, Date localDateTime) {
        SaleFiFo saleFiFo = new SaleFiFo();

        Product pId = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id : " + productId)
        );

        Import anImport = importRepository.findById(impId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found with id : " + impId)
        );
        saleFiFo.setSaleQty(qtyNew);
        saleFiFo.setProduct(pId);
        saleFiFo.setAnImport(anImport);
        saleFiFo.setPaymentNo(paymentNo);
        saleFiFo.setLocalDate(localDate);
        saleFiFo.setLocalDateTime(localDateTime);
        saleFiFoRepository.save(saleFiFo);
    }

    public static String generateOrderCode(long lastSequence) {
        // Get today's date in "yyyyMMdd" format
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Increment sequence
        long newSequence = lastSequence + 1;

        // Format to 3 digits (e.g., 001, 002, 003)
        String sequenceStr = String.format("%03d", newSequence);

        // Generate final code
        return "ECO-" + today + sequenceStr;
    }

    private OrderOnlineResponse mapToOrderOnlineResponse(OrderOnline orderOnline) {
        return OrderOnlineResponse.builder()
                .id(orderOnline.getId())
                .orderDate(orderOnline.getOrderDate().toString())
                .orderNumber(orderOnline.getOrderNumber())
                .orderStatus(orderOnline.getOrderStatus())
                .customerId(orderOnline.getCustomerId())
                .customerName(orderOnline.getCustomerName())
                .phoneNumber(orderOnline.getPhoneNumber())
                .deliveryInformation(orderOnline.getDeliveryInformation())
                .totalAmount(orderOnline.getTotalAmount())
                .paymentMethod(orderOnline.getPaymentMethod())
                .paymentStatus(orderOnline.getPaymentStatus())
                .deliveryAddress(orderOnline.getDeliveryAddress())
                .customerNote(orderOnline.getCustomerNote())
                .subTotal(orderOnline.getSubTotal())
                .discount(orderOnline.getDiscount())
                .deliveryFee(orderOnline.getDeliveryFee())
                .grandTotal(orderOnline.getGrandTotal())
                .details(orderOnline.getOrderOnlineDetails().stream()
                        .map(product -> OrderProductDetailResponse.builder()
                                .barcode(product.getProduct().getBarcode())
                                .englishName(product.getProduct().getProNameEn())
                                .khmerName(product.getProduct().getProNameKh())
                                .qty(product.getQtySale())
                                .salePrice(product.getProduct().getPrice())
                                .discountType(String.valueOf(product.getDiscountPrice()).concat("%"))
                                .discountPrice(calculate(product.getProduct().getPrice(), product.getDiscountPrice()))
                                .total(calculateSalePrice(product.getProduct().getPrice(), product.getDiscountPrice()))
                                .build()).toList())
                .build();
    }

    private String calculate(BigDecimal price, BigDecimal discount) {
        BigDecimal result = price.multiply(discount).divide(BigDecimal.valueOf(100));
        // Set scale to 2 decimal places without rounding
        result = result.setScale(2, BigDecimal.ROUND_UP);
        return "$" + result.toString();
    }

    private BigDecimal calculateSalePrice(BigDecimal price, BigDecimal discount) {
        if (price == null || discount == null) {
            throw new IllegalArgumentException("Price and discount must not be null");
        }

        BigDecimal discountAmount = price.multiply(discount).divide(BigDecimal.valueOf(100));
        BigDecimal salePrice = price.subtract(discountAmount);
        salePrice = salePrice.setScale(2, BigDecimal.ROUND_UP);

        return salePrice;
    }


}
