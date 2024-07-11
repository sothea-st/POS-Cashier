package Stock.PurchaseOrder;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.combobox.ComboBoxSelection;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONObject;

public class AddPurchaseOrder extends javax.swing.JDialog {

     private String vendorId;
     private String subCatId;
     private PurchaseOrderDetailResponse[] listPurchase;
     private ArrayList<ImportDetailOrder> listImport = new ArrayList<>();
     private int totalQty = 0;
     private double totalCost = 0;

     public AddPurchaseOrder(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          header.setBackground(WindowColor.darkGreen);
          txtReference.requestFocus();
          groupCmb();

          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
     }

     private void groupCmb() {
          //  ============== combobox cmbVendorName ================
          ButtonEvent vendorEvent = new ButtonEvent() {
               @Override
               public void onSelect(String id) {
                    vendorId = id;
               }
          };
          cmbVendorName.initEvent(vendorEvent);
          // vendorName is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbVendorName, JavaRoute.vendor, "vendorName");
          //  ============== end ================

          //  ============== combobox cmbSubCategory ================
          ButtonEvent subCatEvent = new ButtonEvent() {
               @Override
               public void onSelect(String id) {
                    subCatId = id;
               }
          };
          cmbSubCategory.initEvent(subCatEvent);
          // catNameEn is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbSubCategory, JavaRoute.subcategory, "catNameEn");
          //  ============== end ================
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel2 = new javax.swing.JPanel();
          labelPopUpTitle2 = new Components.LabelPopUpTitle();
          label5 = new Components.Label();
          label20 = new Components.Label();
          cmbSubCategory = new Components.ComboBox();
          cmbVendorName = new Components.ComboBox();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          jLabel12 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel14 = new javax.swing.JLabel();
          header = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jLabel2 = new javax.swing.JLabel();
          jLabel3 = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          jLabel6 = new javax.swing.JLabel();
          jLabel7 = new javax.swing.JLabel();
          jLabel8 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          label8 = new Components.Label();
          datePicker1 = new DatePicker.DatePicker();
          txtReference = new Components.TextField();
          label21 = new Components.Label();
          label22 = new Components.Label();
          datePicker2 = new DatePicker.DatePicker();
          button1 = new Button.Button();
          jScrollPane = new javax.swing.JScrollPane();
          jLabel10 = new javax.swing.JLabel();
          lbTotalQty = new javax.swing.JLabel();
          jLabel15 = new javax.swing.JLabel();
          lbTotalCost = new javax.swing.JLabel();
          jScrollPane1 = new javax.swing.JScrollPane();
          panel = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle2.setLabelTitle("Add Purchase Order");

          label5.setLabelName("Vendor Name");

          label20.setLabelName("Category");

          buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonCancelMouseClicked(evt);
               }
          });

          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(204, 0, 0));
          jLabel12.setText("*");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(204, 0, 0));
          jLabel13.setText("*");

          jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(204, 0, 0));
          jLabel14.setText("*");

          header.setBackground(new java.awt.Color(0, 0, 0));

          jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel1.setForeground(new java.awt.Color(255, 255, 255));
          jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel1.setText("#");

          jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel2.setForeground(new java.awt.Color(255, 255, 255));
          jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel2.setText("Barcode");

          jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(255, 255, 255));
          jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel3.setText("Division");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setText("Product Name");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Cost");

          jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel6.setForeground(new java.awt.Color(255, 255, 255));
          jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel6.setText("Amount");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(255, 255, 255));
          jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel7.setText("Available QTY");

          jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel8.setForeground(new java.awt.Color(255, 255, 255));
          jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel8.setText("Qty");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(255, 255, 255));
          jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel9.setText("Action");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1)
                         .addComponent(jLabel2)
                         .addComponent(jLabel3)
                         .addComponent(jLabel5)
                         .addComponent(jLabel4)
                         .addComponent(jLabel6)
                         .addComponent(jLabel7)
                         .addComponent(jLabel8)
                         .addComponent(jLabel9))
                    .addContainerGap(12, Short.MAX_VALUE))
          );

          label8.setLabelName("Reference №");

          txtReference.setLabelTextField("Reference №");

          label21.setLabelName("Order Date");

          label22.setLabelName("Transaction Date");

          button1.setBackground(new java.awt.Color(47, 152, 70));
          button1.setButtonName("Apply");
          button1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    button1MouseClicked(evt);
               }
          });

          jScrollPane.setBorder(null);

          jLabel10.setText("Total Qty  :");

          lbTotalQty.setText("0");

          jLabel15.setText("Total Cost :");

          lbTotalCost.setText("0");

          jScrollPane1.setBorder(null);

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1146, Short.MAX_VALUE)
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 540, Short.MAX_VALUE)
          );

          jScrollPane1.setViewportView(panel);

          javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
          jPanel2.setLayout(jPanel2Layout);
          jPanel2Layout.setHorizontalGroup(
               jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(jPanel2Layout.createSequentialGroup()
                              .addContainerGap()
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(18, 18, 18)
                                                  .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(18, 18, 18)
                                                  .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                   .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(25, 25, 25)
                                                  .addComponent(cmbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(cmbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(35, 35, 35)
                                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(txtReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(jScrollPane1))))
                    .addGap(20, 20, 20))
          );
          jPanel2Layout.setVerticalGroup(
               jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGroup(jPanel2Layout.createSequentialGroup()
                                   .addGap(4, 4, 4)
                                   .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addComponent(label20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(cmbVendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(cmbSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(label21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(label22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(datePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel10)
                         .addComponent(lbTotalQty))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel15)
                         .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(8, 8, 8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

    }//GEN-LAST:event_buttonSaveMouseClicked

     private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
          if (vendorId == null || vendorId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Vendor Id can not be empty!");
               return;
          }

          JSONObject json = new JSONObject();
          json.put("vendorId", vendorId);
          if (subCatId != null) {
               json.put("subCatId", subCatId);
          }

          Response response = JavaConnection.post(JavaRoute.vendorOrSubCategory, json);

          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper obj = new ObjectMapper();
                    PurchaseOrderResponse data = obj.readValue(responseData, PurchaseOrderResponse.class);
                    listPurchase = data.getData();
                    appendData();
               }
          } catch (Exception e) {
               System.out.println("error : " + e);
          }
     }//GEN-LAST:event_button1MouseClicked

     private void appendData() {
          reloadPanel();

          for (PurchaseOrderDetailResponse p : listPurchase) {
               ImportDetailOrder pp = new ImportDetailOrder(
                    p.getID(),
                    p.getBarcode(),
                    p.getProNameEn(),
                    p.getDivision(),
                    p.getAvailableQty(),
                    p.getQty(),
                    p.getCost(),
                    p.getAmount()
               );
               listImport.add(pp);
          }

          setDetail(listImport);
     }

     private void setDetail(ArrayList<ImportDetailOrder> list) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panel.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          if (list.isEmpty()) {
               panel.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               panel.add(nofound, BorderLayout.CENTER);
               panel.add(nofound);
               panel.revalidate();
               panel.repaint();
          }
          int i = 0;
          for (ImportDetailOrder p : list) {
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
               i++;
               TdDetailPurchaseOrder detail = new TdDetailPurchaseOrder();
               detail.setDetail(
                    String.valueOf(i),
                    String.valueOf(p.getBarcode()),
                    String.valueOf(p.getProNameEn()),
                    String.valueOf(p.getDivision()),
                    String.valueOf(p.getAvailableQty()),
                    String.valueOf(p.getQty()),
                    "$".concat(String.valueOf(p.getCost())),
                    "$".concat(String.valueOf(p.getAmount())),
                    String.valueOf(p.getID())
               );

               totalQty += p.getQty();
               totalCost += p.getCost().doubleValue();

               try {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              detail.setImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }

               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onRemove(String index) {
                         eventRemove(index);
                    }

                    @Override
                    public void onKeyPress() {
                         calculate();
                    }

               };

               detail.initEvent(event);
               panel.add(detail, gbc);
          }

          lbTotalQty.setText(String.valueOf(totalQty));
          lbTotalCost.setText("$".concat(String.format("%.2f", totalCost)));

     }

     private void reloadPanel() {
          panel.removeAll();
          panel.revalidate();
          panel.repaint();
     }

     private void calculate() {
          Component[] listCom = panel.getComponents();
          totalCost = 0;
          totalQty = 0;
          for (Component c : listCom) {
               var objData = ((TdDetailPurchaseOrder) c);
               String _amount = objData.getAmountValue().replace("$", "");
               _amount = _amount.replace(",", "");
               totalCost += Double.parseDouble(_amount);
               Integer _qty = Integer.valueOf(objData.getQtyUnit());
               totalQty += _qty;
          }
          lbTotalCost.setText("$".concat(String.format("%.2f", totalCost)));
          lbTotalQty.setText(String.valueOf(totalQty));

          for (int i = 0; i < listCom.length; i++) {
               var obj = ((TdDetailPurchaseOrder) listCom[i]);
               String ind = String.valueOf(i + 1);
               obj.setIndex(ind);
          }
     }

     private void eventRemove(String index) {
          try {
               UIManager UI = new UIManager();
               UI.put("OptionPane.background", WindowColor.mediumGreen);
               UI.put("Panel.background", WindowColor.mediumGreen);
               UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

               int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ?",
                    "Delete?", JOptionPane.YES_NO_OPTION);

               if (resp == JOptionPane.YES_OPTION) {
                    int ind = Integer.parseInt(index) - 1;
                    Component[] listComponent = panel.getComponents();
                    for (int i = 0; i < listComponent.length; i++) {
                         if (ind == i) {
                              panel.remove(ind);
                              break;
                         }
                    }
                    calculate();
               } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               }

          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
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
               java.util.logging.Logger.getLogger(AddPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(AddPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(AddPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(AddPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AddPurchaseOrder dialog = new AddPurchaseOrder(new javax.swing.JFrame(), true);
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
     private Button.Button button1;
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Components.ComboBox cmbSubCategory;
     private Components.ComboBox cmbVendorName;
     private DatePicker.DatePicker datePicker1;
     private DatePicker.DatePicker datePicker2;
     private javax.swing.JPanel header;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel15;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JScrollPane jScrollPane;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.Label label20;
     private Components.Label label21;
     private Components.Label label22;
     private Components.Label label5;
     private Components.Label label8;
     private Components.LabelPopUpTitle labelPopUpTitle2;
     private javax.swing.JLabel lbTotalCost;
     private javax.swing.JLabel lbTotalQty;
     private javax.swing.JPanel panel;
     private Components.TextField txtReference;
     // End of variables declaration//GEN-END:variables
}
