package com.example.pos.connection1.service;

import com.example.pos.connection1.DTO.ReportRequest;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.Vendor;
import com.example.pos.connection1.entity.models.ProductAddRemoveQty;
import com.example.pos.connection1.projections.ReportImport.ReportImportProjection;
import com.example.pos.connection1.repository.EmployeeRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
 

import lombok.RequiredArgsConstructor;

import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportService {

    // ********************* Group Beand ************************
    // private final ImportRepository repo;
    private final ImportDetailRepository repoDetail;
    private final ProductRepository repoProduct;
    private final VendorRepository vendorRepository;
    private final EmployeeRepository employeeRepository;
    // ********************* end ********************************

    // ********************* Group variable *********************



    // ********************* end ********************************



    public List<ReportImportProjection> reportImport(ReportRequest reportRequest) {
        LocalDate dateFrom = LocalDate.parse(reportRequest.dateFrom());
        LocalDate dateTo = LocalDate.parse(reportRequest.dateTo());

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        if (dateTo.isAfter(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The field dateTo can not greater than current date : " + formattedDate + ".");
        }

        if (dateFrom.isAfter(dateTo))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The feild dateFrom must be smaller than field dateTo .");
        // return repo.getReport(dateFrom, dateTo);
        return null;
    }

    public void addImport(Import imp) {

        LocalDate localDate = LocalDate.now();
        Import data = new Import();

   


        int count =0;
        count++;
        String impNo = "";
        if (count < 10) {
            impNo = "0000" + count;
        } else if (count < 100) {
            impNo = "000" + count;
        } else if (count < 1000) {
            impNo = "00" + count;
        } else if (count < 10000) {
            impNo = "0" + count;
        } else {
            impNo = "" + count;
        }

        data.setImpNo(impNo);

        // data.setEmpId(imp.getEmpId());
        // data.setVendor(imp.getSubId());
        data.setImpDate(imp.getImpDate());
        data.setDiscount(imp.getDiscount());
        data.setTotal(imp.getTotal());
        data.setCreateBy(imp.getCreateBy());
        data.setDateLocal(localDate);
        // repo.save(data);

        List<ImportDetail> listDetail = imp.getDetails();

        // int[] listProId = {
        // 672,
        // 673,
        // 674,
        // 675,
        // 676,
        // 677,
        // 678,
        // 679,
        // 680,
        // 681,
        // 587
        // };

        // for (int i = 0; i < listProId.length; i++) {
        // var value = listDetail.get(i);
        // int productId = listProId[i];

        // repoProduct.findById(productId).orElseThrow(
        // () -> new ResponseStatusException(
        // HttpStatus.NOT_FOUND,
        // "product id has not been found ."));

        // int qtyNew = 122;
        // ImportDetail details = new ImportDetail();
        // ImportDetail getImpDetails = repoDetail.getDataImportDetail(productId);

        // if (getImpDetails == null) {
        // details.setQtyOld(qtyNew);
        // } else {
        // int qtyOld = getImpDetails.getQtyOld();
        // int qty = qtyOld + qtyNew;
        // details.setQtyOld(qty);
        // }

        // details.setImpId(data.getId());
        // details.setProductId(productId);
        // details.setQtyNew(qtyNew);
        // details.setCost(value.getCost());
        // details.setAmount(value.getAmount());
        // details.setExpireDate(value.getExpireDate());
        // details.setCreateBy(imp.getCreateBy());
        // repoDetail.save(details);

        // Optional<Product> p = repoProduct.findById(productId);
        // Product pp = p.get();
        // pp.setProductStatus("In Stock");
        // // pp.setCost(value.getCost());
        // pp.setImportDetail(details); // update last importDetailsId
        // repoProduct.save(pp);
        // }

        for (int i = 0; i < listDetail.size(); i++) {
            // var value = listDetail.get(i);
            // int productId = value.getProductId();

            // repoProduct.findById(productId).orElseThrow(
            //         () -> new ResponseStatusException(
            //                 HttpStatus.NOT_FOUND,
            //                 "product id has not been found ."));

            // int qtyNew = value.getQtyNew();
            // ImportDetail details = new ImportDetail();
            // ImportDetail getImpDetails = repoDetail.getDataImportDetail(productId);

            // if (getImpDetails == null) {
            //     details.setQtyOld(qtyNew);
            // } else {
            //     int qtyOld = getImpDetails.getQtyOld();
            //     int qty = qtyOld + qtyNew;
            //     details.setQtyOld(qty);
            // }

            // details.setImpId(data.getId());
            // details.setProductId(productId);
            // details.setQtyNew(qtyNew);
            // details.setCost(value.getCost());
            // details.setAmount(value.getAmount());
            // details.setExpireDate(value.getExpireDate());
            // details.setCreateBy(imp.getCreateBy());
            // repoDetail.save(details);

            // Optional<Product> p = repoProduct.findById(productId);
            // Product pp = p.get();
            // pp.setProductStatus("In Stock");
            // // pp.setCost(value.getCost());
            // pp.setImportDetail(details); // update last importDetailsId
            // repoProduct.save(pp);
        }
    }

    public int updateQty(ProductAddRemoveQty listProId) {
        int size = listProId.getListProId().size();
        if (size == 1) {
            for (int i = 0; i < listProId.getListProId().size(); i++) {
                Optional<ImportDetail> impData = repoDetail.findByImpId(listProId.getListProId().get(i).getProId());
                int id = impData.get().getId();
                int qty = impData.get().getQtyOld();
                String _sign = listProId.getListProId().get(i).getSign();
                if (_sign.equals("add")) {
                    qty = qty + listProId.getListProId().get(i).getQty();
                } else if (_sign.equals("remove")) {
                    qty--;
                }
                Optional<ImportDetail> data = repoDetail.findById(id);
                ImportDetail imp = data.get();
                imp.setQtyOld(qty);
                repoDetail.save(imp);
                // get product with qty updated
                int _oldQty = repoDetail.getOldQty(listProId.getListProId().get(i).getProId());
                return _oldQty;
            }
        } else {
            for (int i = 0; i < listProId.getListProId().size(); i++) {
                var _data = listProId.getListProId().get(i);
                Optional<ImportDetail> impData = repoDetail.findByImpId(_data.getProId());
                int id = impData.get().getId();
                int qty = impData.get().getQtyOld();
                String _sign = listProId.getListProId().get(i).getSign();
                if (_sign.equals("add")) {
                    qty = qty + listProId.getListProId().get(i).getQty();
                }
                Optional<ImportDetail> data = repoDetail.findById(id);
                ImportDetail imp = data.get();
                imp.setQtyOld(qty);
                repoDetail.save(imp);
                // get product with qty updated
                // int _oldQty =
                // repoDetail.getOldQty(listProId.getListProId().get(i).getProId());
                // return _oldQty;
            }
        }

        return 0;

    }

}
