package com.example.pos.connection1.service.cashierReport;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaRoundUp;
import com.example.pos.connection1.entity.models.SummeryCashierReport;
import com.example.pos.connection1.entity.models.VatProductModel;
import com.example.pos.connection1.projections.SaleSomeFieldProject;
import com.example.pos.connection1.projections.discountProjection.DiscountProjection;
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
import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.entity.User;
import java.text.DecimalFormat;
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

        Double cashierCount = closeShift.getExpress().doubleValue() +
                closeShift.getKhqrMnk().doubleValue() +
                closeShift.getKhqrAba().doubleValue() +
                closeShift.getCreditCard().doubleValue() +
                closeShift.getCashUsd().doubleValue() +
                closeShift.getCashKhr().doubleValue() / JavaConstant.exchangeRate;

        map.put("closeCash", 1);
        map.put("cashierCount", BigDecimal.valueOf(Double.valueOf(df.format(cashierCount))));
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
        data.add(new VatProductModel("VAT Taxable Value", BigDecimal.valueOf(Double.valueOf(df.format(vat)))));
        data.add(new VatProductModel("Non-VAT Taxable Value", BigDecimal.valueOf(Double.valueOf(df.format(noneVat)))));
        data.add(new VatProductModel("VAT State Charge Value",
                BigDecimal.valueOf(Double.valueOf(df.format(vatStateChrge)))));
        data.add(new VatProductModel("Public Lighting Tax Base", BigDecimal.valueOf(Double.valueOf(df.format(plt)))));
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

    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfKh = new DecimalFormat("#");

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
            List<Integer> disStr = repoSaleDetail.totalQty(JavaConstant.currentDate, posId,
                    Double.valueOf(listDiscount.get(i)));
            if (!disStr.isEmpty())
                disQty = disStr.size();

            double disAmount = 0;
            String disAmountStr = repoSaleDetail.totalAmount(userId, listDiscount.get(i), JavaConstant.currentDate,
                    posId);
            if (disAmountStr != null)
                disAmount = Double.valueOf(disAmountStr);

            if (disQty > 0) {
                discount.add(new SummeryCashierReport(listDiscount.get(i) + "%", disQty,
                        BigDecimal.valueOf(Double.valueOf(df.format(disAmount)))));
            }

        }

        int _disQtyDollar = 0;
        String _qtyDollar = repoSaleDetail.totalQtyDollar(userId, JavaConstant.currentDate, posId);
        if (_qtyDollar != null)
            _disQtyDollar = Integer.valueOf(_qtyDollar);

        double _saledDollar = 0;
        String _amSaledDollar = repoSaleDetail.totalSaledDollar(userId, JavaConstant.currentDate, posId);

        if (_amSaledDollar != null)
            _saledDollar = Double.valueOf(_amSaledDollar);

        HashMap<String, Object> discountDollar = new HashMap<>();
        discountDollar.put("qtySaledDollar", _disQtyDollar);
        discountDollar.put("amountSaledDollar", BigDecimal.valueOf(Double.valueOf(df.format(_saledDollar))));

        HashMap<String, Object> _map = new HashMap<>();
        _map.put("percentag", discount);
        _map.put("cash", discountDollar);
        map.put("discountSummery", _map);
    }

    public void paymentSummery(int userId, String posId, String userCode) {

        List<Integer> qtyUsd = repoSale.countSaledNumUsd(userId, JavaConstant.currentDate, posId);
        List<DiscountProjection> _cashUsd = repoSale.countSaledUsd(userId, JavaConstant.currentDate, posId);
        double _calculateCashUsd=0;
        for( DiscountProjection d : _cashUsd ) {
            Integer _qty = d.getQty() - d.getQty_returned();
            Double _price = d.getPrice() * _qty;
            if( d.getDiscount_type() != null )  {
                if( d.getDiscount_type().equals("dollar") ) {
                    _calculateCashUsd += (_price * _qty) - d.getDiscount(); 
                    System.out.println("dddddddddd : " + _price + " ffffffff : " + " fffff :  " +  d.getDiscount() + "=========== " + ((_price * _qty) - d.getDiscount() ));
                } else {
                    Double _val = _price  - (_price *  d.getDiscount())/100;
                    System.out.println("rrrrrrrrrrrrr  = " + _val);
                    _calculateCashUsd += _val; 
                }
            } else {
                Double _val = _price  - (_price *  d.getDiscount())/100;
                _calculateCashUsd += _val;
            }
        }




        List<Integer> qtyKhr = repoSale.countSaledNumKhr(userId, JavaConstant.currentDate, posId);
        Double _cashKhr = repoSale.countSaledCashKhr(userId, JavaConstant.currentDate, posId);
        _cashKhr = _cashKhr == null ? 0 : _cashKhr;

        int qtyAba = repoSale.countSaledNumAba(userId, JavaConstant.currentDate, posId);
        Double _cashAba = repoSale.countSaledAba(userId, JavaConstant.currentDate, posId);
        _cashAba = _cashAba == null ? 0 : _cashAba;

        int qtyMnk = repoSale.countSaledNumMnk(userId, JavaConstant.currentDate, posId);
        Double _cashMnk = repoSale.countSaledMnk(userId, JavaConstant.currentDate, posId);
        _cashMnk = _cashMnk == null ? 0 : _cashMnk;

        int qtyExpress = 0;
        String qtyExpressStr = repoSale.totalCountQtyExpress(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate, userCode);
        if (qtyExpressStr != null)
            qtyExpress = Integer.valueOf(qtyExpressStr);

        int qtyCredit = repoSale.countSaledNumCredit(userId, JavaConstant.currentDate, posId);
        Double _cashCredit = repoSale.countSaledCredit(userId, JavaConstant.currentDate, posId);
        _cashCredit = _cashCredit == null ? 0 : _cashCredit;

        ArrayList<SummeryCashierReport> payment = new ArrayList<>();

        payment.add(new SummeryCashierReport(
                "Cash-Riels " + JavaRoundUp.setRoundNumber(_cashKhr * JavaConstant.exchangeRate) + "", qtyKhr.size(),
                BigDecimal.valueOf(Double.valueOf(df.format(_cashKhr)))));
        payment.add(new SummeryCashierReport("Cash- Dollars", qtyUsd.size(),
                BigDecimal.valueOf(Double.valueOf(df.format(_calculateCashUsd)))));
        payment.add(new SummeryCashierReport("MNK QR Pay", qtyMnk,
                BigDecimal.valueOf(Double.valueOf(df.format(_cashMnk)))));
        payment.add(new SummeryCashierReport("ABA QR Pay", qtyAba,
                BigDecimal.valueOf(Double.valueOf(df.format(_cashAba)))));
        payment.add(new SummeryCashierReport("ABA-Card Payment", qtyCredit,
                BigDecimal.valueOf(Double.valueOf(df.format(_cashCredit)))));

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

        int returnQty = repoSaleDetail.numRetured(JavaConstant.currentDate, posId, userCode);

        // double returnAmount = 0;
        Double returnAmountDiscount = repoSaleDetail.totalReturnAmountDiscount(JavaConstant.currentDate, posId,
                userCode);
        returnAmountDiscount = returnAmountDiscount == null ? 0 : returnAmountDiscount;

        int numOfSale = repoSaleDetail.numOfSale(JavaConstant.currentDate, posId, userCode);

        List<SaleSomeFieldProject> totalAmount = repoSaleDetail.totalSaledAmount(JavaConstant.currentDate, posId,
                userCode);

        double _sumTotal = 0;
        for (SaleSomeFieldProject s : totalAmount) {

            if (s.getDiscount_case() != null) {
                if (s.getDiscount_case().equals("promotion")) { // this case means items have discount from backend
                    double _val = s.getSub_total() - s.getDiscount();
                    _sumTotal += _val;
                } else {
                    _sumTotal += s.getSub_total();
                }
            } else {
                _sumTotal += s.getSub_total();
            }

        }

        ArrayList<SummeryCashierReport> summery = new ArrayList<>();

        // totalAmount = JavaConstant.getTwoPrecision(totalAmount == null ? 0 :
        // totalAmount);
        returnAmountDiscount = JavaConstant.getTwoPrecision(returnAmountDiscount);
        amountDiscount = JavaConstant.getTwoPrecision(amountDiscount);
        summery.add(new SummeryCashierReport("Total Sales", numOfSale,
                BigDecimal.valueOf(Double.valueOf(df.format(_sumTotal)))));
        summery.add(
                new SummeryCashierReport("Total Refund/Return", returnQty, BigDecimal.valueOf(returnAmountDiscount)));
        summery.add(new SummeryCashierReport("Total Voids", 0, BigDecimal.valueOf(0)));
        summery.add(new SummeryCashierReport("Discounts", qtyDiscount,
                BigDecimal.valueOf(Double.valueOf(df.format(amountDiscount)))));
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
