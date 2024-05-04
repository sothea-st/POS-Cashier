package Receipt;

import Components.ReceiptBox;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Model.Reprint.DataSuccessModel;
import Model.Reprint.ReprintModel;
import Model.Reprint.SaleDetailModel;
import PanelToImageConverter.FrameReceiptForPrint;
import PanelToImageConverter.TestPanel;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import okhttp3.Response;
import pdf.MyPrinter;

public class Receipt extends javax.swing.JDialog {

     private DataSuccessModel dataSuccess;
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private String customerName;

     public String getCustomerName() {
          return customerName;
     }

     public void setCustomerName(String customerName) {
          this.customerName = customerName;
//          cusName.setText(customerName);
     }

     public Receipt(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          exchangeDollar.setText(kh.format(JavaConstant.exchangeRate));

          jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          setFontSizeForLabels(print, 12);
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          setBackground(Color.WHITE);
          getContentPane().setBackground(Color.WHITE);
//          invoiceNo.setFont(new Font("Time New Roman", Font.BOLD, 9));
    

     }

     public Receipt(java.awt.Frame parent, boolean modal, DataSuccessModel data) {
          super(parent, modal);
          setDataSuccess(data);
          initComponents();
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          exchangeDollar.setText(kh.format(JavaConstant.exchangeRate));

          jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          setFontSizeForLabels(print, 11);
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          setBackground(Color.WHITE);
          getContentPane().setBackground(Color.WHITE);
//          invoiceNo.setFont(new Font("Time New Roman", Font.BOLD, 9));

     }

     public void chartAndPrint() {
          printReceipt();
     }

     public void visible() {
          setVisible(true);
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

     public void printComponenet() {
          PrinterJob pj = PrinterJob.getPrinterJob();
          pj.setJobName(" Print Component ");

          pj.setPrintable(new Printable() {
               public int print(Graphics pg, PageFormat pf, int pageNum) {
                    if (pageNum > 0) {
                         return Printable.NO_SUCH_PAGE;
                    }

                    Graphics2D g2 = (Graphics2D) pg;
                    g2.translate(pf.getImageableX(), pf.getImageableY());
                    print.paint(g2);
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

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        print = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        companyname = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        vattin = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        invoiceNo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        saleDate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cashierName = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbDiscountKh = new javax.swing.JLabel();
        lbDiscountUs = new javax.swing.JLabel();
        discountVal = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        totalprice = new javax.swing.JLabel();
        totalKhr = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        receiveUsd = new javax.swing.JLabel();
        receiveKhr = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        changeUsd = new javax.swing.JLabel();
        changeKhr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbDeliveryKh = new javax.swing.JLabel();
        lbDeliveryUs = new javax.swing.JLabel();
        deliveryVal = new javax.swing.JLabel();
        countProduct = new javax.swing.JPanel();
        generateBarcode = new javax.swing.JLabel();
        invoiceCode = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        exchangeDollar = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBack = new Button.Button();
        btnPrint = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        print.setBackground(new java.awt.Color(255, 255, 255));
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        companyname.setFont(new java.awt.Font("Khmer OS Muol", 1, 10)); // NOI18N
        companyname.setForeground(new java.awt.Color(56, 56, 56));
        companyname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        companyname.setText("រេដ​ អាន អិចប្រេស ឯ.ក");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(56, 56, 56));
        jLabel4.setText("លេខអត្តសញ្ញាណសារពើពន្ធ​(VATTIN)៖");

        vattin.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        vattin.setForeground(new java.awt.Color(56, 56, 56));
        vattin.setText("038545848965886");

        address.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        address.setForeground(new java.awt.Color(56, 56, 56));
        address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address.setText("ផ្ទះលេខ១៣៩១២ ផ្លូវ ៥៩៨ ភូមិខ១ សង្កាត់ច្រាំងចំរេះទី២  ខណ្ឌឬស្សីកែវ រាជធានីភ្នំពេញ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(address))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(56, 56, 56));
        jLabel10.setText("សាខាហាង ៖");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(56, 56, 56));
        jLabel11.setText("Red Ant");

        jLabel12.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(56, 56, 56));
        jLabel12.setText("លេខវិក្កយបត្រ ៖");

        invoiceNo.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        invoiceNo.setForeground(new java.awt.Color(56, 56, 56));
        invoiceNo.setText("000000243");

        jLabel14.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(56, 56, 56));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("លេខទូរសព្ឌ ៖");

        contact.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        contact.setForeground(new java.awt.Color(56, 56, 56));
        contact.setText("093 999 699");

        jLabel16.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(56, 56, 56));
        jLabel16.setText("កាលបរិច្ឆេទ ៖");

        saleDate.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        saleDate.setForeground(new java.awt.Color(56, 56, 56));
        saleDate.setText("22-12-2023 9:24");

        jLabel18.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(56, 56, 56));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("អ្នកគិតលុយ ៖");

        cashierName.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        cashierName.setForeground(new java.awt.Color(56, 56, 56));
        cashierName.setText("Sokhom Sodanin");

        jLabel20.setFont(new java.awt.Font("Khmer OS Muol", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(56, 56, 56));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("វិក្កយបត្រ");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(56, 56, 56));
        jLabel46.setText(" /  INVOICE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cashierName, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
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
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(56, 56, 56));
        jLabel35.setText("អត្រាប្តូរប្រាក់ ");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(56, 56, 56));
        jLabel36.setText("1 USD = ");

        jLabel37.setFont(new java.awt.Font("Garamond", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(56, 56, 56));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Thank you for choosing \"RED ANT Express\" !!");

        jLabel38.setFont(new java.awt.Font("Garamond", 1, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(56, 56, 56));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Receipt required for exchange or return items");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(56, 56, 56));
        jLabel45.setText("/  Exchange Rate :");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbDiscountKh.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        lbDiscountKh.setForeground(new java.awt.Color(56, 56, 56));
        lbDiscountKh.setText("បញ្ចុះតម្លៃ");

        lbDiscountUs.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lbDiscountUs.setForeground(new java.awt.Color(56, 56, 56));
        lbDiscountUs.setText("/ Discount :");

        discountVal.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        discountVal.setForeground(new java.awt.Color(56, 56, 56));
        discountVal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        discountVal.setText("- $");

        jLabel31.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(56, 56, 56));
        jLabel31.setText("សរុប(រួមអាករ) ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(56, 56, 56));
        jLabel3.setText("/  Total (All Tax Included)-USD :");

        jLabel32.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
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

        jLabel33.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(56, 56, 56));
        jLabel33.setText("ប្រាក់ទទួល");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(56, 56, 56));
        jLabel43.setText(" /  Received :");

        receiveUsd.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        receiveUsd.setForeground(new java.awt.Color(56, 56, 56));
        receiveUsd.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        receiveUsd.setText("- $");

        receiveKhr.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        receiveKhr.setForeground(new java.awt.Color(56, 56, 56));
        receiveKhr.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        receiveKhr.setText("-");

        jLabel34.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(56, 56, 56));
        jLabel34.setText("ប្រាក់អាប់ ");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(56, 56, 56));
        jLabel44.setText("/  Change :");

        changeUsd.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        changeUsd.setForeground(new java.awt.Color(56, 56, 56));
        changeUsd.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        changeUsd.setText("- $");

        changeKhr.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        changeKhr.setForeground(new java.awt.Color(56, 56, 56));
        changeKhr.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        changeKhr.setText("-");

        jLabel1.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(56, 56, 56));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("៛");

        jLabel5.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(56, 56, 56));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("៛");

        jLabel6.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(56, 56, 56));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("៛");

        lbDeliveryKh.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        lbDeliveryKh.setForeground(new java.awt.Color(56, 56, 56));
        lbDeliveryKh.setText("សេវាដឹក ");

        lbDeliveryUs.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lbDeliveryUs.setForeground(new java.awt.Color(56, 56, 56));
        lbDeliveryUs.setText("/  Delivery Fee :");

        deliveryVal.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        deliveryVal.setForeground(new java.awt.Color(56, 56, 56));
        deliveryVal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        deliveryVal.setText("- $");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(receiveUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                        .addComponent(totalKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(receiveKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(changeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deliveryVal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbDeliveryKh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDeliveryUs))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbDiscountKh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDiscountUs)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addComponent(discountVal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(discountVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDiscountKh)
                        .addComponent(lbDiscountUs, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDeliveryUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deliveryVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDeliveryKh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addComponent(receiveUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(receiveKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(changeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
            .addGap(0, 117, Short.MAX_VALUE)
        );

        generateBarcode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        generateBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        invoiceCode.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        invoiceCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invoiceCode.setText("jLabel1");

        jLabel7.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(56, 56, 56));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("៛");

        exchangeDollar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        exchangeDollar.setForeground(new java.awt.Color(56, 56, 56));
        exchangeDollar.setText("jLabel9");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("................................................................................................................................................");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("...................................................................................................................................................");

        javax.swing.GroupLayout printLayout = new javax.swing.GroupLayout(print);
        print.setLayout(printLayout);
        printLayout.setHorizontalGroup(
            printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exchangeDollar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, printLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(countProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(companyname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(generateBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(invoiceCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(24, 24, 24))
            .addGroup(printLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        printLayout.setVerticalGroup(
            printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(countProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(exchangeDollar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generateBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceCode)
                .addGap(114, 114, 114))
        );

        jScrollPane1.setViewportView(print);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnBack.setButtonName("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(47, 155, 70));
        btnPrint.setButtonName("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
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
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
//         printComponenet();
         printReceipt();
         dispose();
    }//GEN-LAST:event_btnPrintMouseClicked

     public void printReceipt() {
          // ============= print with device
          print.revalidate();
          print.repaint();
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
                    paper.setSize(4.13 * 72, 5.83 * 72); // A6 size in points (1 inch = 72 points)
                    paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                    pageFormat.setPaper(paper);

                    printerJob.setPrintable(new MyPrinter(print), pageFormat);
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

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    Receipt dialog = new Receipt(new javax.swing.JFrame(), true);
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

     public DataSuccessModel getDataSuccess() {
          return dataSuccess;
     }

     public static String formatString(String input) {
          StringBuilder formatted = new StringBuilder();
          int count = 0;
          for (int i = 0; i < input.length(); i++) {
               formatted.append(input.charAt(i));
               count++;
               if (count == 3 && i != input.length() - 1) {
                    formatted.append(" ");
                    count = 0;
               }
          }
          return formatted.toString();
     }

     public void setDataSuccess(DataSuccessModel dataSuccess) {
          this.dataSuccess = dataSuccess;
          assignValue(dataSuccess);
     }

     private void assignValue(DataSuccessModel dataSuccess) {
          var data = dataSuccess.getData();
          try {
               displayProduct(data);
               Response response = JavaConnection.get(JavaRoute.readImage + data.getCompanyLogo());
               byte[] images = response.body().bytes();
               logo.setIcon(new ImageIcon(images));
               companyname.setText(data.getCompanyName());

               address.setText("<html>អាសយដ្ឋាន៖ " + data.getCompanyAddres() + "</html>");
               vattin.setText(data.getVattin());
//               cusName.setText(data.getCustomerType());
               invoiceNo.setText(data.getPaymentNo());
               
               //Set Discount
               if(data.getDiscount() > 0){
                   discountVal.setText(dm.format(data.getDiscount()));
               }else{
                   discountVal.setVisible(false);
                   lbDiscountUs.setVisible(false);
                   lbDiscountKh.setVisible(false);
               }
               

               if (data.getCompanyContact() != null || !data.getCompanyContact().isEmpty()) {
                    contact.setText(formatString(data.getCompanyContact()));
               }

               
               saleDate.setText(data.getSaleDate());
               cashierName.setText( data.getEmpName());
               
               totalprice.setText(dm.format(data.getTotal()));
               double totalkh = JavaRoundDown.roundDown("" + data.getTotal() * JavaConstant.exchangeRate);
               totalKhr.setText(kh.format(totalkh));

               if (data.getReceiveUsd() != 0 && data.getReceiveKhr() != 0) {
                    if (data.getChangeKhr() != 0) {
                         changeKhr.setText(kh.format(data.getChangeKhr()));
                    }

                    if (data.getChangeUsd() != 0) {
                         changeUsd.setText(dm.format(data.getChangeUsd()));
                    }
                    receiveUsd.setText(dm.format(data.getReceiveUsd()));
                    receiveKhr.setText(kh.format(data.getReceiveKhr()));
               } else {
                    if (data.getReceiveUsd() != 0) {
                         receiveUsd.setText(dm.format(data.getReceiveUsd()));
                         changeUsd.setText(dm.format(data.getChangeUsd()));
                    }

                    if (data.getReceiveKhr() != 0) {
                         receiveKhr.setText(kh.format(data.getReceiveKhr()));
                         changeKhr.setText(kh.format(data.getChangeKhr()));
                    }
               }

               Response generateCode = JavaConnection.get(JavaRoute.generateBarcode + data.getPaymentBarcode());
//               Response generateCode = JavaConnection.get(JavaRoute.generateBarcode + "1000000");

               byte[] barcode = generateCode.body().bytes();
               generateBarcode.setIcon(new ImageIcon(barcode));
               
               invoiceCode.setText(data.getPaymentNo());

               jLabel35.setVisible(false);
               jLabel45.setVisible(false);
               jLabel36.setVisible(false);
               exchangeDollar.setVisible(false);
               jLabel7.setVisible(false);
               
               //Set Delivery
               deliveryVal.setVisible(false);
               lbDeliveryUs.setVisible(false);
               lbDeliveryKh.setVisible(false);
               

          } catch (Exception e) {
               System.err.println("getting error at " + e);
          }

     }

     private void displayProduct(ReprintModel data) {
          SaleDetailModel[] listSale = data.getSaleDetails();

          for (int i = 0; i < listSale.length; i++) {
               var list = listSale[i];
               ReceiptBox re = new ReceiptBox();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private Button.Button btnBack;
    private Button.Button btnPrint;
    private javax.swing.JLabel cashierName;
    private javax.swing.JLabel changeKhr;
    private javax.swing.JLabel changeUsd;
    private javax.swing.JLabel companyname;
    private javax.swing.JLabel contact;
    private javax.swing.JPanel countProduct;
    private javax.swing.JLabel deliveryVal;
    private javax.swing.JLabel discountVal;
    private javax.swing.JLabel exchangeDollar;
    private javax.swing.JLabel generateBarcode;
    private javax.swing.JLabel invoiceCode;
    private javax.swing.JLabel invoiceNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDeliveryKh;
    private javax.swing.JLabel lbDeliveryUs;
    private javax.swing.JLabel lbDiscountKh;
    private javax.swing.JLabel lbDiscountUs;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel print;
    private javax.swing.JLabel receiveKhr;
    private javax.swing.JLabel receiveUsd;
    private javax.swing.JLabel saleDate;
    private javax.swing.JLabel totalKhr;
    private javax.swing.JLabel totalprice;
    private javax.swing.JLabel vattin;
    // End of variables declaration//GEN-END:variables
}
