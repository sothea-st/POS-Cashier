package Return;

import Components.JavaAlertMessage;
import Components.ReceiptBox;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Fonts.WindowFonts;
import Model.Reprint.DataSuccessModel;
import Model.Reprint.ReprintModel;
import Model.Reprint.SaleDetailModel;
import Print.EpsonPrinter;
import Receipt.Receipt;
import static Receipt.Receipt.formatString;
import static Receipt.Receipt.setFontSizeForLabels;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import okhttp3.Response;
import pdf.MyPrinter;
import pdf.PrintPanelToPDF;

public class PrinterReturn extends javax.swing.JDialog {

     private DataSuccessModel dataSuccess;

     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");

     public PrinterReturn(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          setFontSizeForLabels(printerReturn, 11);
          jLabel46.setFont(WindowFonts.timeNewRomanBold14);

          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
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

     public DataSuccessModel getDataSuccess() {
          return dataSuccess;
     }

     public void setDataSuccess(DataSuccessModel dataSuccess) {
          this.dataSuccess = dataSuccess;
          assignValue(dataSuccess);
     }

     String pfdNameInvoice;
     int numberOfItem=0;
     
     private void assignValue(DataSuccessModel dataSuccess) {
          var data = dataSuccess.getData();
          try {
               Response response = JavaConnection.get(JavaRoute.readImage + data.getCompanyLogo());
               byte[] images = response.body().bytes();
               logo.setIcon(new ImageIcon(images));
               companyname.setText(data.getCompanyName());
               address.setText("<html>អាសយដ្ឋាន៖ " + data.getCompanyAddres() + "</html>");
               vattin.setText(data.getVattin());
               invoiceNo.setText(data.getPaymentNo());
               pfdNameInvoice = data.getPaymentNo();

               int currentYear = Year.now().getValue();
               String _year = "" + currentYear;
               _year = _year.substring(2, _year.length());
               String newInvoice = "CN" + _year;

               String[] parts = data.getPaymentNo().split("-");
               parts[2] = newInvoice;
               String _payNo = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3];

               invoiceNo1.setText(_payNo);
               cashier.setText(data.getEmpName());
               saleDate.setText(data.getSaleDate());

               if (data.getCompanyContact() != null || !data.getCompanyContact().isEmpty()) {
                    contact.setText(formatString(data.getCompanyContact()));
               }

               displayProduct(data);

//               if (JavaConstant.returnByBarcode != null) {
//                    System.err.println("3333333333333333333");
               double sum = 0;
               for (SaleDetailModel s : data.getSaleDetails()) {
                    sum += s.getPrice() * s.getQty();
               }
               if( data.getDiscount() != 0 ) {
                    sum = sum - data.getDiscount();
               }

               totalprice.setText(dm.format(sum));
               double totalkh = JavaRoundDown.roundDown("" + sum * JavaConstant.exchangeRate);
               totalKhr.setText(kh.format(totalkh));

//               } else {
//                    totalprice.setText(dm.format(data.getTotal()));
//                    double totalkh = JavaRoundDown.roundDown("" + data.getTotal() * JavaConstant.exchangeRate);
//                    totalKhr.setText(kh.format(totalkh));
//               }
               if (data.getDiscount() != 0) {
                    discount.setText(dm.format(data.getDiscount()));
               } else {
                    discount.setVisible(false);
                    discountKh.setVisible(false);
                    discountUsd.setVisible(false);
               }

          } catch (Exception e) {
               System.err.println("getting error at " + e);
          }
     }

     SaleDetailModel[] listSale = null;
     
     private void displayProduct(ReprintModel data) {
          listSale = data.getSaleDetails();
          numberOfItem = listSale.length;
          
          
          for (int i = 0; i < listSale.length; i++) {
               var list = listSale[i];
               ReturnBox re = new ReturnBox();
               re.setProductName(list.getProNameEn());
               re.setBarcodeStr(list.getBarcode());
               re.setQtyStr("" + list.getQty());
               re.setUnitPriceStr(dm.format(list.getPrice()));
               double amount = list.getQty() * list.getPrice();
               re.setAmountStr(dm.format(amount));
               countProduct.add(re);
               countProduct.add(Box.createRigidArea(new Dimension(2, 2)));
               countProduct.setLayout(new BoxLayout(countProduct, BoxLayout.Y_AXIS));
               countProduct.setBorder(new EmptyBorder(2, 2, 2, 2));
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        printerReturn = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        companyname = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        vattin = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        branch = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        invoiceNo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cashier = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        saleDate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        invoiceNo1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        discountKh = new javax.swing.JLabel();
        discountUsd = new javax.swing.JLabel();
        discount = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        totalprice = new javax.swing.JLabel();
        totalKhr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        countProduct = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnPrint = new Button.Button();
        btnBack = new Button.Button();
        btnPdf = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        printerReturn.setBackground(new java.awt.Color(255, 255, 255));
        printerReturn.setForeground(new java.awt.Color(255, 255, 255));
        printerReturn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        companyname.setFont(new java.awt.Font("Khmer OS Muol", 1, 10)); // NOI18N
        companyname.setForeground(new java.awt.Color(56, 56, 56));
        companyname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        companyname.setText("រេដ​ អាន អិចប្រេស ឯ.ក");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(56, 56, 56));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("លេខអត្តសញ្ញាណសារពើពន្ធ​(VATTIN)៖");

        vattin.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        vattin.setForeground(new java.awt.Color(56, 56, 56));
        vattin.setText("038545848965886");

        address.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        address.setForeground(new java.awt.Color(56, 56, 56));
        address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address.setText("អាសយដ្ខាន ​៖​ផ្ទះលេខ១៣៩១២ ផ្លូវ ៥៩៨ ភូមិខ១ សង្កាត់ច្រាំងចំរេះទី២ ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(vattin, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(56, 56, 56));
        jLabel10.setText("សាខាហាង ៖");

        branch.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        branch.setForeground(new java.awt.Color(56, 56, 56));
        branch.setText("101-រេដអាន អិចប្រេស ទួលគោក");

        jLabel12.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(56, 56, 56));
        jLabel12.setText("ប័ណ្ណឥណទាន ៖");

        invoiceNo.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        invoiceNo.setForeground(new java.awt.Color(56, 56, 56));
        invoiceNo.setText("000000243");

        jLabel14.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(56, 56, 56));
        jLabel14.setText("អ្នកគិតលុយ ៖");

        cashier.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        cashier.setForeground(new java.awt.Color(56, 56, 56));
        cashier.setText("RAE0004");

        jLabel16.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(56, 56, 56));
        jLabel16.setText("កាលបរិច្ឆេទ ៖");

        saleDate.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        saleDate.setForeground(new java.awt.Color(56, 56, 56));
        saleDate.setText("22-12-2023 9:24 AM");

        jLabel18.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(56, 56, 56));
        jLabel18.setText("លេខទូរសព្ឌ ៖");

        contact.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        contact.setForeground(new java.awt.Color(56, 56, 56));
        contact.setText("023 666 6696");

        jLabel20.setFont(new java.awt.Font("Khmer OS Muol", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(56, 56, 56));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("ប័ណ្ណឥណទាន");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(56, 56, 56));
        jLabel46.setText(" /  CREDIT NOTE");

        jLabel17.setFont(new java.awt.Font("Khmer OS Content", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(56, 56, 56));
        jLabel17.setText("យោងវិក្កយត្រលេខ ៖");

        invoiceNo1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        invoiceNo1.setForeground(new java.awt.Color(56, 56, 56));
        invoiceNo1.setText("............................................");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel46)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(branch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(branch, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cashier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(saleDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invoiceNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(56, 56, 56));
        jLabel21.setText("ឈ្មោះទំនិញ");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(56, 56, 56));
        jLabel25.setText("Item Name");

        jLabel22.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(56, 56, 56));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("បរិមាណ");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(56, 56, 56));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Quantity");

        jLabel23.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(56, 56, 56));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("ថ្លៃឯកតា");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(56, 56, 56));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Price");

        jLabel24.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(56, 56, 56));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ថ្លៃទំនិញ");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(56, 56, 56));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Net Price");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        jLabel37.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(56, 56, 56));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Thank you for choosing \"RED ANT Express\" !!");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        discountKh.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        discountKh.setForeground(new java.awt.Color(56, 56, 56));
        discountKh.setText("បញ្ចុះតម្លែ");

        discountUsd.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        discountUsd.setForeground(new java.awt.Color(56, 56, 56));
        discountUsd.setText("/  Discount :");

        discount.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        discount.setForeground(new java.awt.Color(56, 56, 56));
        discount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        discount.setText("- $");

        jLabel31.setFont(new java.awt.Font("Khmer OS Content", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(56, 56, 56));
        jLabel31.setText("សរុប(រួមអាករ) ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(56, 56, 56));
        jLabel3.setText("/  Total (All Tax Included)-USD :");

        jLabel32.setFont(new java.awt.Font("Khmer OS Content", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(56, 56, 56));
        jLabel32.setText("សរុប(រួមអាករ) ");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(56, 56, 56));
        jLabel39.setText("/  Total (All Tax Included)-Riel :");

        totalprice.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        totalprice.setForeground(new java.awt.Color(56, 56, 56));
        totalprice.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalprice.setText("$ 5.55");

        totalKhr.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        totalKhr.setForeground(new java.awt.Color(56, 56, 56));
        totalKhr.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalKhr.setText("23,310");

        jLabel1.setFont(new java.awt.Font("Khmer OS Content", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(56, 56, 56));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("៛");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(discountKh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discountUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(discount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(discountUsd, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(discountKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(totalKhr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        countProduct.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout countProductLayout = new javax.swing.GroupLayout(countProduct);
        countProduct.setLayout(countProductLayout);
        countProductLayout.setHorizontalGroup(
            countProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        countProductLayout.setVerticalGroup(
            countProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("..............................................................................................................................................");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("..............................................................................................................................................");

        javax.swing.GroupLayout printerReturnLayout = new javax.swing.GroupLayout(printerReturn);
        printerReturn.setLayout(printerReturnLayout);
        printerReturnLayout.setHorizontalGroup(
            printerReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printerReturnLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(printerReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printerReturnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(companyname, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(printerReturnLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        printerReturnLayout.setVerticalGroup(
            printerReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printerReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(countProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );

        jScrollPane1.setViewportView(printerReturn);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnPrint.setBackground(new java.awt.Color(47, 155, 70));
        btnPrint.setButtonName("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
        });

        btnBack.setButtonName("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnPdf.setBackground(new java.awt.Color(153, 102, 0));
        btnPdf.setButtonName("PDF");
        btnPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPdfMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        
         if (listSale.length > 1) {
              printReceipt();
         } else {
              EpsonPrinter.printReceipt(printerReturn);
         }

         dispose();

    }//GEN-LAST:event_btnPrintMouseClicked

    
    //    ======================== new test =====================
     private double calculatePanelHeight() {
          // Calculate the total height needed to accommodate all items in the JPanel
          double totalHeight = 0;
          for (Component component : printerReturn.getComponents()) {
               totalHeight += component.getHeight();
          }
          return totalHeight;
     }

     public void printReceipt() {
          // ============= print with device
          printerReturn.revalidate();
          printerReturn.repaint();
          PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();

          PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, printAttributes);
          if (printServices.length > 0) {
               PrinterJob printerJob = PrinterJob.getPrinterJob();
               try {
                    // Set the print service
                    printerJob.setPrintService(printServices[0]);

//                 PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = printerJob.defaultPage();
                    Paper paper = new Paper();

                    paper.setSize(4.13 * 72, calculatePanelHeight()); // A6 size in points (1 inch = 72 points)

//                    paper.setSize(print.getPreferredSize().getWidth(), print.getPreferredSize().getHeight());
                    paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                    pageFormat.setPaper(paper);

                    printerJob.setPrintable(new MyPrinter(printerReturn), pageFormat);
                    // Print without showing the print dialog
                    printerJob.print();

               } catch (PrinterException ex) {
                    ex.printStackTrace();
               }
          } else {
               System.out.println("No printer found.");
          }
     }
    
    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseClicked
        try {
            new PrintPanelToPDF(printerReturn).printPdf(pfdNameInvoice,numberOfItem);
            dispose();
            JavaAlertMessage j = new JavaAlertMessage(new JFrame() , true);
            j.setMessage("PDF was saved to path " + PrintPanelToPDF.folderPath);
            j.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PrinterReturn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPdfMouseClicked

     /**
      * @param args the command line
      * arguments
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
               java.util.logging.Logger.getLogger(PrinterReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(PrinterReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(PrinterReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(PrinterReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PrinterReturn dialog = new PrinterReturn(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel address;
    private javax.swing.JLabel branch;
    private Button.Button btnBack;
    private Button.Button btnPdf;
    private Button.Button btnPrint;
    private javax.swing.JLabel cashier;
    private javax.swing.JLabel companyname;
    private javax.swing.JLabel contact;
    private javax.swing.JPanel countProduct;
    private javax.swing.JLabel discount;
    private javax.swing.JLabel discountKh;
    private javax.swing.JLabel discountUsd;
    private javax.swing.JLabel invoiceNo;
    private javax.swing.JLabel invoiceNo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel printerReturn;
    private javax.swing.JLabel saleDate;
    private javax.swing.JLabel totalKhr;
    private javax.swing.JLabel totalprice;
    private javax.swing.JLabel vattin;
    // End of variables declaration//GEN-END:variables
}
