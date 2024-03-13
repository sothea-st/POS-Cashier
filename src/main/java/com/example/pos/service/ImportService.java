package com.example.pos.service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Import;
import com.example.pos.entity.ImportDetail;
import com.example.pos.entity.Product;
import com.example.pos.entity.models.ProIdModel;
import com.example.pos.entity.models.ProductAddRemoveQty;
import com.example.pos.entity.models.ProductModel;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.ImportRepository;
import com.example.pos.repository.ProductRepository;
import com.example.pos.repository.productProjection.ProductProjection;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImportService {
    @Autowired
    private ImportRepository repo;

    @Autowired
    private ImportDetailRepository repoDetail;

    @Autowired
    private ProductRepository repoProduct;

    @Autowired
    private HttpSession session;

    public void addImport(Import imp) {

        Import data = new Import();

        int count = repo.countRecord();
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

        data.setEmpId(imp.getEmpId());
        data.setSubId(imp.getSubId());
        data.setImpDate(imp.getImpDate());
        data.setDiscount(imp.getDiscount());
        data.setTotal(imp.getTotal());
        data.setCreateBy(imp.getCreateBy());
        repo.save(data);

        List<ImportDetail> listDetail = imp.getDetails();

        for (int i = 0; i < listDetail.size(); i++) {
            var value = listDetail.get(i);
            int productId = value.getProductId();
            int qtyNew = value.getQtyNew();
            ImportDetail details = new ImportDetail();
            ImportDetail getImpDetails = repoDetail.getDataImportDetail(productId);

            if (getImpDetails == null) {
                details.setQtyOld(qtyNew);
            } else {
                int qtyOld = getImpDetails.getQtyOld();
                int qty = qtyOld + qtyNew;
                details.setQtyOld(qty);
            }

            details.setImpId(data.getId());
            details.setProductId(productId);
            details.setQtyNew(qtyNew);
            details.setCost(value.getCost());
            details.setAmount(value.getAmount());
            details.setExpireDate(value.getExpireDate());
            details.setCreateBy(imp.getCreateBy());
            repoDetail.save(details);

            Optional<Product> p = repoProduct.findById(productId);
            Product pp = p.get();
            pp.setProductStatus("In Stock");
            pp.setCost(value.getCost());
            repoProduct.save(pp);

        }
    }

    public int updateQty(ProductAddRemoveQty listProId) {
        if (listProId.getListProId().size() == 1) {
            for (int i = 0; i < listProId.getListProId().size(); i++) {
                Optional<ImportDetail> impData = repoDetail.findByImpId(listProId.getListProId().get(i).getProId());
                int id = impData.get().getId();
                int qty = impData.get().getQtyOld();
                String _sign = listProId.getListProId().get(i).getSign();
                if (_sign.equals("add")) {
                    qty++;
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
        }

        return 0;

    }

}
