package Stock.PurchaseOrderCheck;

import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;

import Stock.PurchaseOrder.GetDetailPurchase;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import okhttp3.Response;
import org.json.JSONObject;

public class DetailPurchaseOrderCheck extends javax.swing.JDialog {

     private POCheckDetailsModel pOCheckDetailsModel;
     private ListPurchaseOrderCheck obj;

     public DetailPurchaseOrderCheck(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setResizable(false);
          header.setBackground(WindowColor.darkGreen);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     }

     public POCheckDetailsModel getpOCheckDetailsModel() {
          return pOCheckDetailsModel;
     }

     public ListPurchaseOrderCheck getObj() {
          return obj;
     }

     public void setObj(ListPurchaseOrderCheck obj) {
          this.obj = obj;
     }

     public void setpOCheckDetailsModel(POCheckDetailsModel p) {
          this.pOCheckDetailsModel = p;
          vendorName.setLabelName(p.getVendorName());
          transactionNo.setLabelName(String.valueOf(p.getTransactionNo()));
          puchaseOrderNo.setLabelName(p.getPurchaseOrderNo());
          referenceNo.setLabelName(p.getReferenceNo());
          transactionDate.setLabelName(p.getTransactionDate());
          orderDate.setLabelName(p.getOrderDate());
          totalQty.setLabelName(String.valueOf(p.getTotalQty()));
          totalCost.setLabelName("$".concat(String.valueOf(p.getTotalCost())));
          requestBy.setLabelName(p.getRequestBy().getName());
          requestDate.setLabelName(p.getRequestBy().getDate());
          checkBy.setLabelName(p.getCheckedBy().getName());
          checkDate.setLabelName(p.getCheckedBy().getDate());
          appendData(p.getDetails());
     }

     void appendData(PODetailItemModel[] details) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetDetailOrder.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          int index = 0;
          if (details.length > 0) {
               for (int i = 0; i < details.length; i++) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = x;
                    gbc.gridy = y;
                    gbc.gridwidth = 1;
                    gbc.anchor = gbc.NORTH;
                    x++;
                    if (x == 1) {
                         x = 0;
                         y++;
                    }
                    var listData = details[i];
                    index++;
                    GetDetailPurchase b = new GetDetailPurchase();

                    b.setValue(
                         String.valueOf(index),
                         String.valueOf(listData.getBarcode()),
                         String.valueOf(listData.getProNameEn()),
                         String.valueOf(listData.getDivision()),
                         String.valueOf(listData.getDepartment()),
                         String.valueOf(listData.getCategory()),
                         String.valueOf(listData.getSubCategory()),
                         String.valueOf(listData.getAvailableQty()),
                         String.valueOf(listData.getOrderQty()),
                         String.valueOf(listData.getCost()),
                         String.valueOf(listData.getTotalCost())
                    );

                    listGetDetailOrder.add(b, gbc);
               }
          } else {
               listGetDetailOrder.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               listGetDetailOrder.add(nofound, BorderLayout.CENTER);
               listGetDetailOrder.add(nofound);
               listGetDetailOrder.revalidate();
               listGetDetailOrder.repaint();
          }

          listGetDetailOrder.revalidate();
          listGetDetailOrder.repaint();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          labelPopUpTitle2 = new Components.LabelPopUpTitle();
          header = new javax.swing.JPanel();
          jLabel6 = new javax.swing.JLabel();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel14 = new javax.swing.JLabel();
          jLabel15 = new javax.swing.JLabel();
          jLabel16 = new javax.swing.JLabel();
          jLabel17 = new javax.swing.JLabel();
          jLabel18 = new javax.swing.JLabel();
          jLabel19 = new javax.swing.JLabel();
          jLabel20 = new javax.swing.JLabel();
          jScrollPane1 = new javax.swing.JScrollPane();
          listGetDetailOrder = new javax.swing.JPanel();
          lbVendorName = new Components.Label();
          vendorName = new Components.Label();
          lbTransactionNo = new Components.Label();
          transactionNo = new Components.Label();
          lbPurchaseeOrderNo = new Components.Label();
          puchaseOrderNo = new Components.Label();
          lbReferenceNo = new Components.Label();
          referenceNo = new Components.Label();
          lbOrderDate = new Components.Label();
          lbTotalQty = new Components.Label();
          lbTotalCost = new Components.Label();
          lbTransactionDate = new Components.Label();
          totalQty = new Components.Label();
          totalCost = new Components.Label();
          requestBy = new Components.Label();
          requestDate = new Components.Label();
          lbTransactionDate1 = new Components.Label();
          lbTransactionDate2 = new Components.Label();
          lbTransactionDate3 = new Components.Label();
          checkBy = new Components.Label();
          checkDate = new Components.Label();
          comment = new Components.Label();
          lbTotalQty1 = new Components.Label();
          lbTotalQty2 = new Components.Label();
          orderDate = new Components.Label();
          transactionDate = new Components.Label();
          buttonSave = new ButtonPackage.ButtonSave();
          buttonCancel = new ButtonPackage.ButtonCancel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle2.setLabelTitle(" Detail Purchase Order Check");

          header.setBackground(new java.awt.Color(0, 0, 0));

          jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel6.setForeground(new java.awt.Color(255, 255, 255));
          jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel6.setText("#");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(255, 255, 255));
          jLabel11.setText("Product Name");

          jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(255, 255, 255));
          jLabel12.setText("Division Name");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(255, 255, 255));
          jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel13.setText("Barcode");

          jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(255, 255, 255));
          jLabel14.setText("Category Name");

          jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel15.setForeground(new java.awt.Color(255, 255, 255));
          jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel15.setText("Available Qty");

          jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel16.setForeground(new java.awt.Color(255, 255, 255));
          jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel16.setText("Cost");

          jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel17.setForeground(new java.awt.Color(255, 255, 255));
          jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel17.setText("Total Cost");

          jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel18.setForeground(new java.awt.Color(255, 255, 255));
          jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel18.setText("Order Qty");

          jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel19.setForeground(new java.awt.Color(255, 255, 255));
          jLabel19.setText("Sub Category Name");

          jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel20.setForeground(new java.awt.Color(255, 255, 255));
          jLabel20.setText("Department Name");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel6)
                         .addComponent(jLabel11)
                         .addComponent(jLabel12)
                         .addComponent(jLabel14)
                         .addComponent(jLabel13)
                         .addComponent(jLabel15)
                         .addComponent(jLabel16)
                         .addComponent(jLabel17)
                         .addComponent(jLabel18)
                         .addComponent(jLabel19)
                         .addComponent(jLabel20))
                    .addContainerGap(12, Short.MAX_VALUE))
          );

          jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
          jScrollPane1.setBorder(null);

          listGetDetailOrder.setBackground(new java.awt.Color(176, 215, 181));

          javax.swing.GroupLayout listGetDetailOrderLayout = new javax.swing.GroupLayout(listGetDetailOrder);
          listGetDetailOrder.setLayout(listGetDetailOrderLayout);
          listGetDetailOrderLayout.setHorizontalGroup(
               listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1555, Short.MAX_VALUE)
          );
          listGetDetailOrderLayout.setVerticalGroup(
               listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 492, Short.MAX_VALUE)
          );

          jScrollPane1.setViewportView(listGetDetailOrder);

          lbVendorName.setLabelName("Vendor Name :");

          vendorName.setLabelName("");

          lbTransactionNo.setLabelName("Transaction № :");

          transactionNo.setLabelName("");

          lbPurchaseeOrderNo.setLabelName("Purchase Order № :");

          puchaseOrderNo.setLabelName("");

          lbReferenceNo.setLabelName("Reference № :");

          referenceNo.setLabelName("");

          lbOrderDate.setLabelName("Order Date :");

          lbTotalQty.setLabelName("Requested By :");

          lbTotalCost.setLabelName("Requested Date :");

          lbTransactionDate.setLabelName("Transaction Date :");

          totalQty.setLabelName("");

          totalCost.setLabelName("");

          requestBy.setLabelName("");

          requestDate.setLabelName("");

          lbTransactionDate1.setLabelName("Checked By :");

          lbTransactionDate2.setLabelName("Checked Date :");

          lbTransactionDate3.setLabelName("Comment :");

          checkBy.setLabelName("");

          checkDate.setLabelName("");

          comment.setLabelName("");

          lbTotalQty1.setLabelName("Total Cost :");

          lbTotalQty2.setLabelName("Total Qty :");

          orderDate.setLabelName("");

          transactionDate.setLabelName("");

          buttonSave.setTitleButton("Check");
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          buttonCancel.setButtonName("Reject");
          buttonCancel.setName(""); // NOI18N
          buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonCancelMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jScrollPane1)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(transactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(referenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbPurchaseeOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(puchaseOrderNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(vendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(transactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(55, 55, 55)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(jPanel1Layout.createSequentialGroup()
                                                       .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                       .addComponent(requestDate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addGroup(jPanel1Layout.createSequentialGroup()
                                                       .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                       .addComponent(requestBy, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addGroup(jPanel1Layout.createSequentialGroup()
                                                       .addComponent(lbTotalQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                       .addComponent(totalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addGroup(jPanel1Layout.createSequentialGroup()
                                                       .addComponent(lbTotalQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                       .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbTransactionDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbTransactionDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(checkDate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(lbTransactionDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(checkBy, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGap(20, 20, 20))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(vendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(lbTransactionDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(checkBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(orderDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbOrderDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbVendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(lbTransactionDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(checkDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(transactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbTransactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                         .addComponent(lbTotalQty2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(totalQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(14, 14, 14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbTotalQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                              .addComponent(puchaseOrderNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(totalCost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(lbTransactionDate3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(comment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbPurchaseeOrderNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(12, 12, 12)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTotalQty, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                        .addComponent(referenceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbReferenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addComponent(requestBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(lbTransactionDate, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                             .addComponent(transactionDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(lbTotalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(requestDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                              .addGap(18, 18, 18)
                              .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, 0)
                              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(18, 18, 18)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(17, 17, 17))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         LocalDate currentDate = LocalDate.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         String checkDate = currentDate.format(formatter);

         if (pOCheckDetailsModel.getDetails().length == 0) {
              JOptionPane.showMessageDialog(this, "Invalid!");
              return;
         }

         JSONObject json = new JSONObject();
         json.put("createBy", JavaConstant.cashierId);
         json.put("remark", "check");
         json.put("role", JavaConstant.roleName);
         json.put("checkDate", checkDate);

         Response response = JavaConnection.post(JavaRoute.imports + "/checkingRequest/" + puchaseOrderNo.getLabelName(), json);

         try {
              String responeData = response.body().string();
              if (response.isSuccessful()) {
                   System.out.println("responeData : " + responeData);
                   dispose();
                   obj.getData(obj);
              }
         } catch (Exception e) {
              System.out.println("error : " + e);
         }

    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

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
               java.util.logging.Logger.getLogger(DetailPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(DetailPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(DetailPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(DetailPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    DetailPurchaseOrderCheck dialog = new DetailPurchaseOrderCheck(new javax.swing.JFrame(), true);
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
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Components.Label checkBy;
     private Components.Label checkDate;
     private Components.Label comment;
     private javax.swing.JPanel header;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel15;
     private javax.swing.JLabel jLabel16;
     private javax.swing.JLabel jLabel17;
     private javax.swing.JLabel jLabel18;
     private javax.swing.JLabel jLabel19;
     private javax.swing.JLabel jLabel20;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.LabelPopUpTitle labelPopUpTitle2;
     private Components.Label lbOrderDate;
     private Components.Label lbPurchaseeOrderNo;
     private Components.Label lbReferenceNo;
     private Components.Label lbTotalCost;
     private Components.Label lbTotalQty;
     private Components.Label lbTotalQty1;
     private Components.Label lbTotalQty2;
     private Components.Label lbTransactionDate;
     private Components.Label lbTransactionDate1;
     private Components.Label lbTransactionDate2;
     private Components.Label lbTransactionDate3;
     private Components.Label lbTransactionNo;
     private Components.Label lbVendorName;
     private javax.swing.JPanel listGetDetailOrder;
     private Components.Label orderDate;
     private Components.Label puchaseOrderNo;
     private Components.Label referenceNo;
     private Components.Label requestBy;
     private Components.Label requestDate;
     private Components.Label totalCost;
     private Components.Label totalQty;
     private Components.Label transactionDate;
     private Components.Label transactionNo;
     private Components.Label vendorName;
     // End of variables declaration//GEN-END:variables
}
