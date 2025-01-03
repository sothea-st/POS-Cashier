/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Constant.PanelToImageConverter;

import Components.ReceiptBox;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Model.Reprint.DataSuccessModel;
import Model.Reprint.ReprintModel;
import Model.Reprint.SaleDetailModel;
import feature.Print.EpsonPrinter;
import static Receipt.Receipt.formatString;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class FrameReceiptForPrint extends javax.swing.JFrame {

     /**
      * Creates new form NewJFrame
      */
     public FrameReceiptForPrint() {
          initComponents();
     }
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private Icon logoIcon;
     private ReprintModel listReprint;
     private String titleCompany;
     private String titleAddress;

     public String getTitleAddress() {
          return titleAddress;
     }

     public void setTitleAddress(String titleAddress) {
          this.titleAddress = titleAddress;
          address.setText(titleAddress);
     }

     public Icon getLogoIcon() {
          return logoIcon;
     }

     public void setLogoIcon(Icon logoIcon) {
          this.logoIcon = logoIcon;
          logo.setIcon(logoIcon);
     }

     public ReprintModel getListReprint() {
          return listReprint;
     }

     public void setListReprint(ReprintModel listReprint) {
          this.listReprint = listReprint;

          SaleDetailModel[] listSale = listReprint.getSaleDetails();

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

     public String getTitleCompany() {
          return titleCompany;
     }

     public void setTitleCompany(String titleCompany) {
          this.titleCompany = titleCompany;
          companyname.setText(titleCompany);
     }

     public void setFontSizeForLabels(Container container, int size) {
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

     public void setDataSuccess(DataSuccessModel dataSuccess) {
          assignValue(dataSuccess);
     }

     private void assignValue(DataSuccessModel dataSuccess) {
          var data = dataSuccess.getData();
          try {

               Response response = JavaConnection.get(JavaRoute.readImage + data.getCompanyLogo());
               byte[] images = response.body().bytes();
               logo.setIcon(new ImageIcon(images));
               companyname.setText(data.getCompanyName());

               address.setText("<html>អាសយដ្ឋាន៖ " + data.getCompanyAddres() + "</html>");
               vattin.setText(data.getVattin());
               cusName.setText(data.getCustomerType());
               invoiceNo.setText(data.getPaymentNo());

               if (data.getCompanyContact() != null || !data.getCompanyContact().isEmpty()) {
                    contact.setText(formatString(data.getCompanyContact()));
               }

               saleDate.setText(data.getSaleDate());
               cashierName.setText(data.getEmpName());

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

               Response generateCode = JavaConnection.get(JavaRoute.generateBarcode + data.getPaymentNo());
               byte[] barcode = generateCode.body().bytes();
               generateBarcode.setIcon(new ImageIcon(barcode));
               invoiceCode.setText(data.getPaymentNo());

               jLabel35.setVisible(false);
               jLabel45.setVisible(false);
               jLabel36.setVisible(false);
               exchangeDollar.setVisible(false);
               jLabel7.setVisible(false);

               SaleDetailModel[] listSale = data.getSaleDetails();

               for (int i = 0; i < listSale.length; i++) {
                    var list = listSale[i];
                    ReceiptBox res = new ReceiptBox();
                    res.setProductName(list.getProNameEn());
                    res.setBarcodeStr(list.getBarcode());
                    res.setQtyStr("" + list.getQty());
                    res.setUnitPriceStr(dm.format(list.getPrice()));
                    double amount = list.getQty() * list.getPrice();
                    res.setAmountStr(dm.format(amount));
                    countProduct.add(res);
                    countProduct.add(Box.createRigidArea(new Dimension(2, 2)));
                    System.err.println("data boc 333= " + list.getProNameEn());

               }
               countProduct.setLayout(new BoxLayout(countProduct, BoxLayout.Y_AXIS));
               countProduct.setBorder(new EmptyBorder(2, 2, 2, 2));
               countProduct.revalidate();
               countProduct.repaint();
          } catch (Exception e) {
               System.err.println("getting error at " + e);
          }

     }

     private void displayProduct(ReprintModel data) {
          SaleDetailModel[] listSale = data.getSaleDetails();

          for (int i = 0; i < listSale.length; i++) {
               var list = listSale[i];
               ReceiptBox res = new ReceiptBox();
               res.setProductName(list.getProNameEn());
               res.setBarcodeStr(list.getBarcode());
               res.setQtyStr("" + list.getQty());
               res.setUnitPriceStr(dm.format(list.getPrice()));
               double amount = list.getQty() * list.getPrice();
               res.setAmountStr(dm.format(amount));
               countProduct.add(res);
               countProduct.add(Box.createRigidArea(new Dimension(2, 2)));
               System.err.println("data boc = " + list.getProNameEn());

          }
          countProduct.setLayout(new BoxLayout(countProduct, BoxLayout.Y_AXIS));
          countProduct.setBorder(new EmptyBorder(2, 2, 2, 2));
          countProduct.revalidate();
          countProduct.repaint();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          print = new javax.swing.JPanel();
          logo = new javax.swing.JLabel();
          companyname = new javax.swing.JLabel();
          jPanel3 = new javax.swing.JPanel();
          jLabel4 = new javax.swing.JLabel();
          vattin = new javax.swing.JLabel();
          address = new javax.swing.JLabel();
          jSeparator1 = new javax.swing.JSeparator();
          jPanel4 = new javax.swing.JPanel();
          jLabel8 = new javax.swing.JLabel();
          cusName = new javax.swing.JLabel();
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
          jSeparator2 = new javax.swing.JSeparator();
          jPanel7 = new javax.swing.JPanel();
          jLabel21 = new javax.swing.JLabel();
          jLabel25 = new javax.swing.JLabel();
          jLabel22 = new javax.swing.JLabel();
          jLabel26 = new javax.swing.JLabel();
          jLabel23 = new javax.swing.JLabel();
          jLabel27 = new javax.swing.JLabel();
          jLabel24 = new javax.swing.JLabel();
          jLabel28 = new javax.swing.JLabel();
          jSeparator3 = new javax.swing.JSeparator();
          jSeparator4 = new javax.swing.JSeparator();
          jLabel35 = new javax.swing.JLabel();
          jLabel36 = new javax.swing.JLabel();
          jLabel37 = new javax.swing.JLabel();
          jLabel38 = new javax.swing.JLabel();
          jLabel45 = new javax.swing.JLabel();
          jPanel1 = new javax.swing.JPanel();
          jLabel30 = new javax.swing.JLabel();
          jLabel2 = new javax.swing.JLabel();
          jLabel29 = new javax.swing.JLabel();
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
          jLabel40 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          jLabel41 = new javax.swing.JLabel();
          countProduct = new javax.swing.JPanel();
          generateBarcode = new javax.swing.JLabel();
          invoiceCode = new javax.swing.JLabel();
          jLabel7 = new javax.swing.JLabel();
          exchangeDollar = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
               .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel3Layout.createSequentialGroup()
                              .addGap(5, 5, 5)
                              .addComponent(jLabel4)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel3Layout.createSequentialGroup()
                              .addGap(33, 33, 33)
                              .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(26, Short.MAX_VALUE))
          );
          jPanel3Layout.setVerticalGroup(
               jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(vattin, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );

          jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
          jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

          jPanel4.setBackground(new java.awt.Color(255, 255, 255));

          jLabel8.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel8.setForeground(new java.awt.Color(56, 56, 56));
          jLabel8.setText("ឈ្មោះអតិថិជន ៖");

          cusName.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          cusName.setForeground(new java.awt.Color(56, 56, 56));
          cusName.setText("អតិថិជនទូរទៅ");

          jLabel10.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(56, 56, 56));
          jLabel10.setText("សាខាហាង ៖");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(56, 56, 56));
          jLabel11.setText("Red Ant");

          jLabel12.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(56, 56, 56));
          jLabel12.setText("លេខវិក្ក័យប័ត្រ ៖");

          invoiceNo.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          invoiceNo.setForeground(new java.awt.Color(56, 56, 56));
          invoiceNo.setText("000000243");

          jLabel14.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(56, 56, 56));
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
          jLabel18.setText("អ្នកគិតលុយ ៖");

          cashierName.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          cashierName.setForeground(new java.awt.Color(56, 56, 56));
          cashierName.setText("RAE-0004");

          jLabel20.setFont(new java.awt.Font("Khmer OS Muol", 1, 11)); // NOI18N
          jLabel20.setForeground(new java.awt.Color(56, 56, 56));
          jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          jLabel20.setText("វិក្កយបត្រ");

          jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          jLabel46.setForeground(new java.awt.Color(56, 56, 56));
          jLabel46.setText(" /  Invoice");

          javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
          jPanel4.setLayout(jPanel4Layout);
          jPanel4Layout.setHorizontalGroup(
               jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel4Layout.createSequentialGroup()
                              .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(invoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(6, 6, 6)
                              .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addGroup(jPanel4Layout.createSequentialGroup()
                              .addComponent(jLabel8)
                              .addGap(6, 6, 6)
                              .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)))
                              .addGap(0, 0, Short.MAX_VALUE))))
          );
          jPanel4Layout.setVerticalGroup(
               jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel20)
                         .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel8)
                         .addComponent(cusName)
                         .addComponent(jLabel10)
                         .addComponent(jLabel11))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel12)
                         .addComponent(invoiceNo)
                         .addComponent(jLabel14)
                         .addComponent(contact))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel16)
                         .addComponent(saleDate)
                         .addComponent(jLabel18)
                         .addComponent(cashierName)))
          );

          jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
          jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

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
                    .addGap(40, 40, 40)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
          );
          jPanel7Layout.setVerticalGroup(
               jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel21)
                         .addComponent(jLabel22)
                         .addComponent(jLabel23)
                         .addComponent(jLabel24))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel25)
                         .addComponent(jLabel26)
                         .addComponent(jLabel27)
                         .addComponent(jLabel28)))
          );

          jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
          jSeparator3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

          jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
          jSeparator4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

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

          jLabel30.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel30.setForeground(new java.awt.Color(56, 56, 56));
          jLabel30.setText("បញ្ចុះតម្លែ");

          jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          jLabel2.setForeground(new java.awt.Color(56, 56, 56));
          jLabel2.setText("/ Discount");

          jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
          jLabel29.setForeground(new java.awt.Color(56, 56, 56));
          jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          jLabel29.setText("- $");

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

          jLabel40.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel40.setForeground(new java.awt.Color(56, 56, 56));
          jLabel40.setText("សេវាដឹក ");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(56, 56, 56));
          jLabel9.setText("/  Delivery Fee :");

          jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
          jLabel41.setForeground(new java.awt.Color(56, 56, 56));
          jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          jLabel41.setText("- $");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(jLabel31)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(jLabel3)
                              .addGap(14, 14, 14))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(receiveUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(jLabel32)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel39))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(jLabel34)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(changeUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(11, 11, 11))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(jLabel30)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel2))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(jLabel40)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel9)))))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addComponent(totalKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(3, 3, 3)
                              .addComponent(jLabel1))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addComponent(receiveKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(3, 3, 3)
                              .addComponent(jLabel5))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                   .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(changeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(3, 3, 3)
                              .addComponent(jLabel6))))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel30)
                         .addComponent(jLabel2)
                         .addComponent(jLabel29))
                    .addGap(0, 0, 0)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel40)
                         .addComponent(jLabel9)
                         .addComponent(jLabel41))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel31)
                         .addComponent(jLabel3)
                         .addComponent(totalprice))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jLabel32)
                              .addComponent(jLabel39))
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(totalKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel43)
                         .addComponent(receiveKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(receiveUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(changeUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(changeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
          );

          countProduct.setBackground(new java.awt.Color(255, 255, 255));

          javax.swing.GroupLayout countProductLayout = new javax.swing.GroupLayout(countProduct);
          countProduct.setLayout(countProductLayout);
          countProductLayout.setHorizontalGroup(
               countProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 331, Short.MAX_VALUE)
          );
          countProductLayout.setVerticalGroup(
               countProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 119, Short.MAX_VALUE)
          );

          generateBarcode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          generateBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

          invoiceCode.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          invoiceCode.setText("jLabel1");

          jLabel7.setFont(new java.awt.Font("Khmer OS Content", 1, 11)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(56, 56, 56));
          jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          jLabel7.setText("៛");

          exchangeDollar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
          exchangeDollar.setForeground(new java.awt.Color(56, 56, 56));
          exchangeDollar.setText("jLabel9");

          javax.swing.GroupLayout printLayout = new javax.swing.GroupLayout(print);
          print.setLayout(printLayout);
          printLayout.setHorizontalGroup(
               printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(printLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                              .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(printLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, printLayout.createSequentialGroup()
                                                  .addGap(106, 106, 106)
                                                  .addComponent(jLabel35)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel45)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jLabel36)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(exchangeDollar)
                                                  .addGap(6, 6, 6)
                                                  .addComponent(jLabel7))
                                             .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(countProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                   .addComponent(jSeparator1)
                                   .addComponent(jSeparator2))
                              .addGap(65, 65, 65))
                         .addGroup(printLayout.createSequentialGroup()
                              .addGap(14, 14, 14)
                              .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE))))
               .addGroup(printLayout.createSequentialGroup()
                    .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(printLayout.createSequentialGroup()
                              .addGap(59, 59, 59)
                              .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(generateBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                         .addGroup(printLayout.createSequentialGroup()
                              .addGap(164, 164, 164)
                              .addComponent(invoiceCode)))
                    .addGap(0, 0, Short.MAX_VALUE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(36, 36, 36))
               .addGroup(printLayout.createSequentialGroup()
                    .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(printLayout.createSequentialGroup()
                              .addGap(141, 141, 141)
                              .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(printLayout.createSequentialGroup()
                              .addGap(113, 113, 113)
                              .addComponent(companyname, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          printLayout.setVerticalGroup(
               printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(companyname)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(countProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel35)
                         .addComponent(jLabel45)
                         .addComponent(jLabel36)
                         .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(exchangeDollar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel37)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(generateBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(invoiceCode)
                    .addContainerGap(95, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 513, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addGap(0, 19, Short.MAX_VALUE)
                         .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 19, Short.MAX_VALUE)))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 895, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addGap(0, 0, Short.MAX_VALUE)
                         .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 0, Short.MAX_VALUE)))
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

     public void printPanel(DataSuccessModel dataSuccess) {
          print.revalidate();
          print.repaint();
          EpsonPrinter.printReceipt(print);
//
//          PrinterJob printerJob = PrinterJob.getPrinterJob();
//          printerJob.setPrintable(new Printable() {
//               public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
//                    if (pageIndex > 0) {
//                         return Printable.NO_SUCH_PAGE;
//                    }
//
//                    Graphics2D g2d = (Graphics2D) graphics;
//                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//
//                    // Print the JPanel content
//                    print.printAll(g2d);
//
//                    return Printable.PAGE_EXISTS;
//               }
//          });
//
//          if (printerJob.printDialog()) {
//               try {
//                    printerJob.print();
//               } catch (PrinterException ex) {
//                    ex.printStackTrace();
//               }
//          }
     }

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
               java.util.logging.Logger.getLogger(FrameReceiptForPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(FrameReceiptForPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(FrameReceiptForPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(FrameReceiptForPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the form */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    new FrameReceiptForPrint().setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel address;
     private javax.swing.JLabel cashierName;
     private javax.swing.JLabel changeKhr;
     private javax.swing.JLabel changeUsd;
     private javax.swing.JLabel companyname;
     private javax.swing.JLabel contact;
     private javax.swing.JPanel countProduct;
     private javax.swing.JLabel cusName;
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
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel20;
     private javax.swing.JLabel jLabel21;
     private javax.swing.JLabel jLabel22;
     private javax.swing.JLabel jLabel23;
     private javax.swing.JLabel jLabel24;
     private javax.swing.JLabel jLabel25;
     private javax.swing.JLabel jLabel26;
     private javax.swing.JLabel jLabel27;
     private javax.swing.JLabel jLabel28;
     private javax.swing.JLabel jLabel29;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel30;
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
     private javax.swing.JLabel jLabel40;
     private javax.swing.JLabel jLabel41;
     private javax.swing.JLabel jLabel43;
     private javax.swing.JLabel jLabel44;
     private javax.swing.JLabel jLabel45;
     private javax.swing.JLabel jLabel46;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JPanel jPanel4;
     private javax.swing.JPanel jPanel7;
     private javax.swing.JSeparator jSeparator1;
     private javax.swing.JSeparator jSeparator2;
     private javax.swing.JSeparator jSeparator3;
     private javax.swing.JSeparator jSeparator4;
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
