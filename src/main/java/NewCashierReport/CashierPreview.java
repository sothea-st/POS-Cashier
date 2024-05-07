package NewCashierReport;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Fonts.WindowFonts;
import Model.Report.Data;
import Model.Report.DataSuccessCashierReport;
import Model.Report.SummerySale;
import Model.Report.SummeryVat;
import static NewCashierReport.CashierReporting.setFontSizeForLabels;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import okhttp3.Response;
import pdf.MyPrinter;

public class CashierPreview extends javax.swing.JDialog {

    private DataSuccessCashierReport getData;
    
    DecimalFormat dm = new DecimalFormat("$ #,##0.00");
    DecimalFormat kh = new DecimalFormat("#,##0");
    
    public CashierPreview(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFontAndBackground();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);

        setFontSizeForLabels(reportPanel, 11);
        getContentPane().setBackground(Color.white);
    }
    
     public DataSuccessCashierReport getGetData() {
          return getData;
     }

     public void setGetData(DataSuccessCashierReport getData) {
          this.getData = getData;
          assignValue(getData);
     }
    
    public static void setFontSizeForLabels(Container container, int size) {
          Component[] components = container.getComponents();
          for (Component component : components) {
               if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    Font currentFont = label.getFont();
                    Font boldFont = new Font(label.getFont().getFontName(), Font.BOLD, size);
                    label.setFont(boldFont);
               } else if (component instanceof Container) {
                    setFontSizeForLabels((Container) component, size);
               }
          }
     }
    
    private void setFontAndBackground() {
          vattin.setFont(WindowFonts.timeNewRoman11);
          branch.setFont(WindowFonts.timeNewRoman11);
          openTill.setFont(WindowFonts.timeNewRomanBold11);
          openCashKhr.setFont(WindowFonts.timeNewRoman11);
          openCashUsd.setFont(WindowFonts.timeNewRoman11);
    }
    
    
    private void assignValue(DataSuccessCashierReport getData) {
        
          var data = getData.getData();
          try {
               Response response = JavaConnection.get(JavaRoute.readImage + data.getCompanyLogo());
               byte[] images = response.body().bytes();
               lbLogo.setIcon(new ImageIcon(images));
               companyname.setText(data.getCompanyName());
               posID.setLabelName("POS № : " + data.getPosID());
               openDate.setLabelName("Open Date : " + data.getOpenDate());
               closeDate.setLabelName("Close Date : " + data.getCloseDate());
               cashier.setText("Cashier : " + data.getUserName());
               invoicenumber.setLabelName("Invoice № : " + data.getPaymentNoFirst() + " To " + data.getPaymentNoLast());
               openCashUsd.setText("USD " + dm.format(data.getOpenCashUsd()));
               openCashKhr.setText("KHR " + kh.format(data.getOpenCashKhr()));
               openTill.setText("Open Till : " + dm.format(data.getOpenCashKhr() / JavaConstant.exchangeRate + data.getOpenCashUsd()));
               cashierCount.setText(dm.format(data.getCashierCount()));
               displaySaleSummary(data);
               displaySummeryAllProVat(data);
               displayPaymentSummary(data);
               displayDiscount(data);
               displaySummeryVat(data);
               setFontSizeForLabels(reportPanel, 11);
          } catch (Exception e) {
               System.err.println("getting error at " + e);
          }
     }

     double sumNetSale;
     double sumSubTotal;

     private void displaySaleSummary(Data data) {
          summarySale.removeAll();
          SummerySale[] summerySale = data.getSummerySale();

          for (int i = 0; i < summerySale.length; i++) {
               var list = summerySale[i];
               BoxReport report = new BoxReport();
               report.setTitle(list.getTitle());
               report.setNum("" + list.getSaleOfNum());
               report.setAmount(dm.format(list.getTotal()));
               summarySale.add(report);
               summarySale.add(Box.createRigidArea(new Dimension(1, 1)));
          }

          sumSubTotal = summerySale[0].getTotal() - (summerySale[1].getTotal() + summerySale[2].getTotal() + summerySale[3].getTotal());
          subTotal.setText(dm.format(sumSubTotal));

          transactionSale.setText("Transaction Sale : " + dm.format(sumSubTotal));
          summarySale.setLayout(new BoxLayout(summarySale, BoxLayout.Y_AXIS));
          summarySale.setBorder(new EmptyBorder(2, 2, 2, 2));
     }

     private void displaySummeryVat(Data data) {
          SummeryVat[] listSummaryVat = data.getSummeryVat();

          for (int i = 0; i < listSummaryVat.length; i++) {
               var list = listSummaryVat[i];
               BoxReport report = new BoxReport();
               report.setTitle(list.getTitle());
               report.setNum("");

               if (list.getTotal() == 0) {
                    report.setAmount("-");
               } else {
                    report.setAmount(dm.format(list.getTotal()));
               }

               summaryVatPanel.add(report);
               summaryVatPanel.add(Box.createRigidArea(new Dimension(1, 1)));
               sumNetSale += list.getTotal();
          }

          jLabel19.setText(dm.format(sumSubTotal - sumNetSale));
          summaryVatPanel.setLayout(new BoxLayout(summaryVatPanel, BoxLayout.Y_AXIS));
          summaryVatPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
     }

     private void displaySummeryAllProVat(Data data) {
          netSalePanel.removeAll();
          SummeryVat[] listSummaryProvat = data.getSummeryAllProVat();
          for (int i = 0; i < listSummaryProvat.length; i++) {
               var list = listSummaryProvat[i];
               BoxReport report = new BoxReport();
               report.setTitle(list.getTitle());
               report.setNum("");
               if (list.getTotal() == 0) {
                    report.setAmount("-");
               } else {
                    report.setAmount(dm.format(list.getTotal()));
               }

               netSalePanel.add(report);
               netSalePanel.add(Box.createRigidArea(new Dimension(1, 1)));
          }
          netSalePanel.setLayout(new BoxLayout(netSalePanel, BoxLayout.Y_AXIS));
          netSalePanel.setBorder(new EmptyBorder(2, 2, 2, 2));
     }

     private void displayPaymentSummary(Data data) {
          paymentPanel.removeAll();
          SummerySale[] listSummarySale = data.getSummeryPayemnt();
           double sumTotalPayment = 0;
          
          for (int i = 0; i < listSummarySale.length; i++) {
               var list = listSummarySale[i];
               BoxReport report = new BoxReport();
               
               report.setTitle(list.getTitle());
               report.setNum("" + list.getSaleOfNum());
               report.setAmount(dm.format(list.getTotal()));
               
               paymentPanel.add(report);
               paymentPanel.add(Box.createRigidArea(new Dimension(2, 2)));
               sumTotalPayment += list.getTotal();
          }

          closedAmount.setText("Close Amount : " + dm.format(data.getCashierCount()- (data.getOpenCashKhr() / JavaConstant.exchangeRate + data.getOpenCashUsd()+sumTotalPayment)));
          sumTotal.setText(dm.format(sumTotalPayment));
          paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
          paymentPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
     }

     private void displayDiscount(Data data) {
          summaryDiscount.removeAll();
          SummerySale[] listDiscountSummary = data.getDiscountSummery().getPercentag();

          for (int i = 0; i < listDiscountSummary.length; i++) {
               var list = listDiscountSummary[i];
               BoxReport report = new BoxReport();

               if (list.getSaleOfNum() > 0) {
                    report.setTitle(list.getTitle());
                    report.setNum("" + list.getSaleOfNum());
                    report.setAmount(dm.format(list.getTotal()));
                    summaryDiscount.add(report);
                    summaryDiscount.add(Box.createRigidArea(new Dimension(2, 2)));
               }
          }

          BoxReport report = new BoxReport();
          report.setTitle("Discount in $");
          report.setNum("" + data.getDiscountSummery().getCash().getQtySaledDollar());
          report.setAmount(dm.format(data.getDiscountSummery().getCash().getAmountSaledDollar()));
          summaryDiscount.add(report);
          summaryDiscount.setLayout(new BoxLayout(summaryDiscount, BoxLayout.Y_AXIS));
          summaryDiscount.setBorder(new EmptyBorder(2, 2, 2, 2));
     }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        reportPanel = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        companyname = new javax.swing.JLabel();
        vattin = new javax.swing.JLabel();
        branch = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        posID = new Components.LabelReceipt();
        openDate = new Components.LabelReceipt();
        closeDate = new Components.LabelReceipt();
        invoicenumber = new Components.LabelReceipt();
        jLabel34 = new javax.swing.JLabel();
        openCashKhr = new javax.swing.JLabel();
        openCashUsd = new javax.swing.JLabel();
        labelReceipt9 = new Components.LabelReceipt();
        openTill = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        summarySale = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        lbSubTotal = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        netSalePanel = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        labelReceipt20 = new Components.LabelReceipt();
        paymentPanel = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        summaryDiscount = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cashierCount = new javax.swing.JLabel();
        sumTotal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        subTotal = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        cashier = new javax.swing.JLabel();
        transactionSale = new javax.swing.JLabel();
        closedAmount = new javax.swing.JLabel();
        countedDifferent = new javax.swing.JLabel();
        summaryVatPanel = new javax.swing.JPanel();
        labelReceipt26 = new Components.LabelReceipt();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPrint = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        reportPanel.setBackground(new java.awt.Color(255, 255, 255));
        reportPanel.setPreferredSize(new java.awt.Dimension(420, 1200));

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setIcon(new javax.swing.ImageIcon("D:\\POSCASHIERMASTER\\tt_pos_window\\src\\main\\resources\\image\\redant.png")); // NOI18N

        companyname.setFont(new java.awt.Font("Khmer OS Muol", 1, 11)); // NOI18N
        companyname.setForeground(new java.awt.Color(0, 0, 0));
        companyname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        companyname.setText("រេដ​ អាន អិចប្រេស ឯ.ក");

        vattin.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        vattin.setForeground(new java.awt.Color(56, 56, 56));
        vattin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vattin.setText("(VATTIN): K008-902200332");

        branch.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        branch.setForeground(new java.awt.Color(56, 56, 56));
        branch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        branch.setText("Branch/Shop : 101-Red Ant Toul Kork");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("SHIFT REPORT");

        posID.setLabelName("POS № : POS001");

        openDate.setLabelName("Open Date : 27-01-24 0:00");

        closeDate.setLabelName("Open Date : 27-01-24 14:21");

        invoicenumber.setLabelName("Invoice № : 101-01-C124000001 To 101-01-C124000150");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("..............................................................................................................................................");

        openCashKhr.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        openCashKhr.setForeground(new java.awt.Color(0, 0, 0));
        openCashKhr.setText("KHR 200,000");

        openCashUsd.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        openCashUsd.setForeground(new java.awt.Color(0, 0, 0));
        openCashUsd.setText("USD 50");

        labelReceipt9.setLabelName("Reason of cash different :");

        openTill.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        openTill.setForeground(new java.awt.Color(0, 0, 0));
        openTill.setText("Open Till : $ 99.98");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("# Num");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Amounts");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("SALES SUMMARY");

        summarySale.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout summarySaleLayout = new javax.swing.GroupLayout(summarySale);
        summarySale.setLayout(summarySaleLayout);
        summarySaleLayout.setHorizontalGroup(
            summarySaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        summarySaleLayout.setVerticalGroup(
            summarySaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));

        lbSubTotal.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        lbSubTotal.setForeground(new java.awt.Color(0, 0, 0));
        lbSubTotal.setText("Subtotal :");

        jSeparator4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Net Sale");

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));

        netSalePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout netSalePanelLayout = new javax.swing.GroupLayout(netSalePanel);
        netSalePanel.setLayout(netSalePanelLayout);
        netSalePanelLayout.setHorizontalGroup(
            netSalePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
        netSalePanelLayout.setVerticalGroup(
            netSalePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("PAYMENT SUMMARY");

        labelReceipt20.setLabelName("Cash Payment :");

        paymentPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        lbTotal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 0, 0));
        lbTotal.setText("Total");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("TRANSACTION DISCOUNT");

        summaryDiscount.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout summaryDiscountLayout = new javax.swing.GroupLayout(summaryDiscount);
        summaryDiscount.setLayout(summaryDiscountLayout);
        summaryDiscountLayout.setHorizontalGroup(
            summaryDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        summaryDiscountLayout.setVerticalGroup(
            summaryDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cashier Count");

        cashierCount.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        cashierCount.setForeground(new java.awt.Color(0, 0, 0));
        cashierCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cashierCount.setText("700.03");

        sumTotal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        sumTotal.setForeground(new java.awt.Color(0, 0, 0));
        sumTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sumTotal.setText("727.23");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("637.50");

        subTotal.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        subTotal.setForeground(new java.awt.Color(0, 0, 0));
        subTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subTotal.setText("701.25");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        cashier.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        cashier.setForeground(new java.awt.Color(0, 0, 0));
        cashier.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cashier.setText("Cashier : RAE-004 davin");

        transactionSale.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        transactionSale.setForeground(new java.awt.Color(0, 0, 0));
        transactionSale.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        transactionSale.setText("Transaction Sale : $ 701.25");

        closedAmount.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        closedAmount.setForeground(new java.awt.Color(0, 0, 0));
        closedAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        closedAmount.setText("Closed Amount : $ 700.03");

        countedDifferent.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        countedDifferent.setForeground(new java.awt.Color(0, 0, 0));
        countedDifferent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countedDifferent.setText("Counted Different : $ 0.00");

        summaryVatPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout summaryVatPanelLayout = new javax.swing.GroupLayout(summaryVatPanel);
        summaryVatPanel.setLayout(summaryVatPanelLayout);
        summaryVatPanelLayout.setHorizontalGroup(
            summaryVatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        summaryVatPanelLayout.setVerticalGroup(
            summaryVatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        labelReceipt26.setLabelName("Discount in %");

        jSeparator8.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator8.setForeground(new java.awt.Color(102, 102, 102));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("................................................................................................................................................");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText(".................................................................................................................................................");

        javax.swing.GroupLayout reportPanelLayout = new javax.swing.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sumTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addComponent(openDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                                .addComponent(closeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addComponent(posID, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(openCashKhr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(openCashUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addComponent(labelReceipt9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(openTill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(closedAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(transactionSale, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(countedDifferent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(24, 24, 24))
                    .addGroup(reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(invoicenumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(summarySale, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(summaryVatPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(netSalePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(jSeparator8)))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(24, 24, 24))
                    .addGroup(reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelReceipt26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(summaryDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reportPanelLayout.createSequentialGroup()
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelReceipt20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cashierCount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cashier, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27))))
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(branch, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(companyname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vattin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                        .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))))
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyname)
                .addGap(5, 5, 5)
                .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(branch, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(posID, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(cashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(invoicenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openTill, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionSale, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportPanelLayout.createSequentialGroup()
                        .addComponent(openCashKhr, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(openCashUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(countedDifferent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelReceipt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(closedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(summarySale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(summaryVatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator8)
                            .addComponent(jSeparator4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(netSalePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelReceipt20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sumTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(labelReceipt26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashierCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );

        jScrollPane1.setViewportView(reportPanel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnPrint.setBackground(new java.awt.Color(47, 155, 70));
        btnPrint.setButtonName("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
       PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();

         PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, printAttributes);
         if (printServices.length > 0) {
              PrinterJob printerJob = PrinterJob.getPrinterJob();
              try {
                   // Set the print service
                   printerJob.setPrintService(printServices[0]);
                   PageFormat pageFormat = printerJob.defaultPage();
                   Paper paper = new Paper();

                   paper.setSize(5.13 * 72, 15 * 72); // A6 size in points (1 inch = 72 points)
                   paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                   pageFormat.setPaper(paper);

                   printerJob.setPrintable(new MyPrinter(reportPanel), pageFormat);
                   // Print without showing the print dialog
                   printerJob.print();

              } catch (PrinterException ex) {
                   ex.printStackTrace();
              }
         } else {
              System.out.println("No printer found.");
         }
    }//GEN-LAST:event_btnPrintMouseClicked
                            

     public void printComponenet(Component component) {
          PrinterJob pj = PrinterJob.getPrinterJob();
          pj.setJobName(" Print Component ");

          pj.setPrintable(new Printable() {
               public int print(Graphics pg, PageFormat pf, int pageNum) {
                    if (pageNum > 0) {
                         return Printable.NO_SUCH_PAGE;
                    }

                    Graphics2D g2 = (Graphics2D) pg;
                    g2.translate(pf.getImageableX(), pf.getImageableY());
                    component.paint(g2);
                    return Printable.PAGE_EXISTS;
               }
          });
          if (pj.printDialog() == false) {
               return;
          }

          try {
               pj.print();
          } catch (PrinterException ex) {
               // handle exception
          }
     }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CashierPreview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierPreview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierPreview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierPreview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CashierPreview dialog = new CashierPreview(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel branch;
    private Button.Button btnPrint;
    private javax.swing.JLabel cashier;
    private javax.swing.JLabel cashierCount;
    private Components.LabelReceipt closeDate;
    private javax.swing.JLabel closedAmount;
    private javax.swing.JLabel companyname;
    private javax.swing.JLabel countedDifferent;
    private Components.LabelReceipt invoicenumber;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private Components.LabelReceipt labelReceipt20;
    private Components.LabelReceipt labelReceipt26;
    private Components.LabelReceipt labelReceipt9;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbSubTotal;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JPanel netSalePanel;
    private javax.swing.JLabel openCashKhr;
    private javax.swing.JLabel openCashUsd;
    private Components.LabelReceipt openDate;
    private javax.swing.JLabel openTill;
    private javax.swing.JPanel paymentPanel;
    private Components.LabelReceipt posID;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JLabel subTotal;
    private javax.swing.JLabel sumTotal;
    private javax.swing.JPanel summaryDiscount;
    private javax.swing.JPanel summarySale;
    private javax.swing.JPanel summaryVatPanel;
    private javax.swing.JLabel transactionSale;
    private javax.swing.JLabel vattin;
    // End of variables declaration//GEN-END:variables
}
