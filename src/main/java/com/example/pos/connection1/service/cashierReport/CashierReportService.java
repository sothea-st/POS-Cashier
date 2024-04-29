package com.example.pos.connection1.service.cashierReport;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.models.SummeryCashierReport;
import com.example.pos.connection1.entity.models.VatProductModel;
import com.example.pos.connection1.repository.EmployeeRepository;
import com.example.pos.connection1.repository.SaleDetailsRepository;
import com.example.pos.connection1.repository.SaleRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.repository.companyRepository.CompanyRepository;
import com.example.pos.connection1.repository.paymentRepository.PaymentRepository;
import com.example.pos.connection1.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.connection1.repository.shiftRepository.OpenShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.connection1.entity.CloseShift;
import com.example.pos.connection1.entity.Company;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.entity.OpenShift;
import com.example.pos.connection1.entity.User;

import java.util.*;
import java.math.*;

@Service
public class CashierReportService {

    @Autowired
    private CompanyRepository repoCompany;

    @Autowired
    private EmployeeRepository repoEmp;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private OpenShiftRepository reposOpenShift;

    @Autowired
    private CloseShiftRepository closeShiftRepo;

    @Autowired
    private SaleRepository repoSale;

    @Autowired
    private PaymentRepository repoPay;

    @Autowired
    private SaleDetailsRepository repoSaleDetail;

    private HashMap<String, Object> map = new HashMap<>();

 

    public Map<String, Object> cashierReport(String userCode, int userId, String posId) {
        int id = userId;
        // get company info
        Company company = repoCompany.getInfoCompany();
        map.put("companyName", company.getCompanyName());
        map.put("companyContact", company.getContact());
        map.put("companyAddress", company.getAddress());
        map.put("companyLogo", company.getPhoto());

        // get user name
        User user = repoUser.getUserById(id);
        Employee employee = repoEmp.getEmployeeById(user.getEmpId());
        map.put("userName", employee.getNameEn());

        // get posId, openDate , openCash from openShift
        OpenShift openShift = reposOpenShift.getDataOpenShift(userCode, JavaConstant.currentDate, posId);

        map.put("posId", openShift.getPosId());
        map.put("openDate", openShift.getOpenTime());
        map.put("openCashKhr", openShift.getReserveKhr());
        map.put("openCashUsd", openShift.getReserveUsd());

        double _usd = openShift.getReserveKhr().doubleValue() / JavaConstant.exchangeRate;
        double totalWithdrawal = _usd + openShift.getReserveUsd().doubleValue();
        totalWithdrawal = JavaConstant.getTwoPrecision(totalWithdrawal);

        // get closeCash, closeDate from CloseShift
        CloseShift closeShift = closeShiftRepo.getCloseShift(userCode, JavaConstant.currentDate, posId);
        map.put("closeCash", 1);
        map.put("closeDate", closeShift.getCloseTime());

        // Sale summery
        SalesSummery(id, posId, userCode);
        vatProductSummery(posId, userCode);
        summerAllProVat(posId, userCode, totalWithdrawal);
        // // payment summery
        paymentSummery(id, posId, userCode);
        // // discount summery
        discountSummery(id, posId, userCode);
        return map;
    }

    public void summerAllProVat(String posId, String userCode, double totalWithdrawal) {
        ArrayList<VatProductModel> data = new ArrayList<>();

        Double vat = repoSaleDetail.vat(JavaConstant.currentDate, posId, userCode);
        vat = vat == null ? 0 : vat;
        map.put("totalWithdrawal", 0);
        map.put("cashierTotal", vat - 0);

        Double noneVat = repoSaleDetail.noneVat(JavaConstant.currentDate, posId, userCode);
        noneVat = noneVat == null ? 0 : noneVat;

        Double vatStateChrge = repoSaleDetail.vatStateCharge(JavaConstant.currentDate, posId, userCode);
        vatStateChrge = vatStateChrge == null ? 0 : vatStateChrge;

        Double plt = repoSaleDetail.plt(JavaConstant.currentDate, posId, userCode);
        plt = plt == null ? 0 : plt;
        data.add(new VatProductModel("VAT Taxable Value", BigDecimal.valueOf(vat)));
        data.add(new VatProductModel("Non-VAT Taxable Value", BigDecimal.valueOf(noneVat)));
        data.add(new VatProductModel("VAT State Charge Value", BigDecimal.valueOf(vatStateChrge)));
        data.add(new VatProductModel("Public Lighting Tax Base", BigDecimal.valueOf(plt)));
        map.put("SummeryAllProVat", data);
    }

    public void vatProductSummery(String posId, String userCode) {
        ArrayList<VatProductModel> data = new ArrayList<>();

        Double vat10 = repoSaleDetail.vat10(JavaConstant.currentDate, posId, userCode);
        vat10 = vat10 == null ? 0 : vat10;

        Double vat3 = repoSaleDetail.vat3(JavaConstant.currentDate, posId, userCode);
        vat3 = vat3 == null ? 0 : vat3;

        data.add(new VatProductModel("VAT 10 %", BigDecimal.valueOf(vat10)));
        data.add(new VatProductModel("Public Lighting Tax", BigDecimal.valueOf(vat3)));
        map.put("SummeryVat", data);
    }

    public void discountSummery(int userId, String posId, String userCode) {
        List<Integer> listDiscount = new ArrayList<>();
        listDiscount.add(10);
        // listDiscount.add(15);
        listDiscount.add(20);
        listDiscount.add(30);
        listDiscount.add(50);
        List<SummeryCashierReport> discount = new ArrayList<>();
        for (int i = 0; i < listDiscount.size(); i++) {
            int disQty = 0;
            List<Integer> disStr = repoSaleDetail.totalQty(JavaConstant.currentDate, posId,  Double.valueOf(listDiscount.get(i)));
            if (!disStr.isEmpty())
                disQty = disStr.size();

            double disAmount = 0;
            String disAmountStr = repoSaleDetail.totalAmount(userId, JavaConstant.currentDate, listDiscount.get(i),
                    posId, JavaConstant.currentDate, userCode);
            if (disAmountStr != null)
                disAmount = Double.valueOf(disAmountStr);

            disAmount = JavaConstant.getTwoPrecision(disAmount);
            discount.add(new SummeryCashierReport(listDiscount.get(i) + "%", disQty, BigDecimal.valueOf(disAmount)));
        }

        int _disQtyDollar = 0;
        String _qtyDollar = repoSaleDetail.totalQtyDollar(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate, userCode);
        if (_qtyDollar != null)
            _disQtyDollar = Integer.valueOf(_qtyDollar);

        double _saledDollar = 0;
        String _amSaledDollar = repoSaleDetail.totalSaledDollar(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate, userCode);

        if (_amSaledDollar != null)
            _saledDollar = Double.valueOf(_amSaledDollar);

        HashMap<String ,Object> discountDollar = new HashMap<>();
        discountDollar.put("qtySaledDollar", _disQtyDollar);
        discountDollar.put("amountSaledDollar", BigDecimal.valueOf(_saledDollar));

        HashMap<String, Object> _map = new HashMap<>();
        _map.put("percentag", discount);
        _map.put("cash", discountDollar);
        map.put("discountSummery", _map);
    }

    public void paymentSummery(int userId, String posId, String userCode) {
        CloseShift dCloseShift = closeShiftRepo.getCloseShift(userCode, JavaConstant.currentDate, posId);
        int qtyUsd = repoSale.countSaledNumUsd(userId, JavaConstant.currentDate, posId);


        double amountPayUsd = dCloseShift.getCashUsd().doubleValue();

        int qtyKhr = repoSale.countSaledNumKhr(userId, JavaConstant.currentDate, posId);

        double amountPayKhr = dCloseShift.getCashKhr().doubleValue() / JavaConstant.exchangeRate;

        int qtyAba = repoSale.countSaledNumAba(userId, JavaConstant.currentDate, posId);

        double amountAba = dCloseShift.getKhqrAba().doubleValue();

        int qtyMnk = repoSale.countSaledNumMnk(userId, JavaConstant.currentDate, posId);

        double amountMnk = dCloseShift.getKhqrMnk().doubleValue();

        double cashCount = dCloseShift.getCashCount()  == null ? 0 : dCloseShift.getCashCount().doubleValue()  ;

        int qtyExpress = 0;
        String qtyExpressStr = repoSale.totalCountQtyExpress(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate, userCode);
        if (qtyExpressStr != null)
            qtyExpress = Integer.valueOf(qtyExpressStr);

        double amountExpress = dCloseShift.getExpress().doubleValue();

        int qtyCredit = repoSale.countSaledNumCredit(userId, JavaConstant.currentDate, posId);

        double amountCredit = dCloseShift.getCreditCard().doubleValue();

        amountPayUsd = JavaConstant.getTwoPrecision(amountPayUsd);
        amountPayKhr = JavaConstant.getTwoPrecision(amountPayKhr);
        amountMnk = JavaConstant.getTwoPrecision(amountMnk);
        amountAba = JavaConstant.getTwoPrecision(amountAba);
        amountExpress = JavaConstant.getTwoPrecision(amountExpress);
        amountCredit = JavaConstant.getTwoPrecision(amountCredit);
        cashCount = JavaConstant.getTwoPrecision(cashCount);

        ArrayList<SummeryCashierReport> payment = new ArrayList<>();
        // payment.add(new SummeryCashierReport("RED ANT EXPRESS", qtyExpress,
        // BigDecimal.valueOf(amountExpress)));
        payment.add(new SummeryCashierReport("Cash- Riels ("+ addCommas(dCloseShift.getCashKhr()+"") +")", qtyKhr, BigDecimal.valueOf(amountPayKhr)));
        payment.add(new SummeryCashierReport("Cash- Dollars", qtyUsd, BigDecimal.valueOf(amountPayUsd)));
        payment.add(new SummeryCashierReport("MNK QR Pay", qtyMnk, BigDecimal.valueOf(amountMnk)));
        payment.add(new SummeryCashierReport("ABA QR Pay", qtyAba, BigDecimal.valueOf(amountAba)));
        payment.add(new SummeryCashierReport("ABA-Card Payment", qtyCredit, BigDecimal.valueOf(amountCredit)));
        payment.add(new SummeryCashierReport("Cash Count", 0, BigDecimal.valueOf(cashCount)));

        map.put("summeryPayemnt", payment);
    }

    public void SalesSummery(int userId, String posId, String userCode) {
        String paymentNoFirst = repoPay.getFirstPaymentNumber(userId, JavaConstant.currentDate);
        String paymentNoLast = repoPay.getLastPaymentNumber(userId, JavaConstant.currentDate);
        map.put("paymentNoFirst", paymentNoFirst);
        map.put("paymentNoLast", paymentNoLast);

        int qtyDiscount = repoSaleDetail.totalQtyDiscount(JavaConstant.currentDate, posId, userCode);
 
        double amountDiscount = 0.00;
        String listDiscountQty = repoSaleDetail.totalAmountDiscount(JavaConstant.currentDate, posId, userCode);
        if (listDiscountQty != null)
            amountDiscount = Double.valueOf(listDiscountQty);
 
        int returnQty = repoSaleDetail.numRetured(JavaConstant.currentDate,posId,userCode);

        // double returnAmount = 0;
        Double returnAmountDiscount = repoSaleDetail.totalReturnAmountDiscount(JavaConstant.currentDate, posId,userCode);
        returnAmountDiscount = returnAmountDiscount == null ? 0 : returnAmountDiscount;


        int numOfSale = repoSaleDetail.numOfSale(JavaConstant.currentDate, posId, userCode);
        Double totalAmount = repoSaleDetail.totalSaledAmount(JavaConstant.currentDate, posId, userCode);

        ArrayList<SummeryCashierReport> summery = new ArrayList<>();

        totalAmount = JavaConstant.getTwoPrecision(totalAmount == null ? 0 : totalAmount);
        returnAmountDiscount = JavaConstant.getTwoPrecision(returnAmountDiscount);
        amountDiscount = JavaConstant.getTwoPrecision(amountDiscount);
        summery.add(new SummeryCashierReport("Total Sales", numOfSale, BigDecimal.valueOf(totalAmount)));
        summery.add(new SummeryCashierReport("Total Refund/Return", returnQty, BigDecimal.valueOf(returnAmountDiscount)));
        summery.add(new SummeryCashierReport("Total Voids", 0, BigDecimal.valueOf(0)));
        summery.add(new SummeryCashierReport("Disounts", qtyDiscount, BigDecimal.valueOf(amountDiscount)));
        map.put("SummerySale", summery);
    }

    public static String addCommas(String str) {
        StringBuilder result = new StringBuilder();
        int length = str.length();
        int count = 0;

        // Iterate through the string from right to left
        for (int i = length - 1; i >= 0; i--) {
            char c = str.charAt(i);
            result.insert(0, c); // Insert character at the beginning of the result string
            count++;

            // Insert comma after every 3 characters, except at the beginning
            if (count % 3 == 0 && i != 0) {
                result.insert(0, ',');
            }
        }

        return result.toString();
    }
}
