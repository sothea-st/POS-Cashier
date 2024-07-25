package Reporting;

import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Products.ListProduct;
import Reporting.ActionSearch.ActionSearchReportPurchaseOrder;
import Reporting.ReportingItem.ReportOfPurchase;
import Reporting.export.ExportReportPurchaseOrderToCSV;
import Reporting.export.ExportReportPurchaseOrderToExcel;
import Reporting.export.ExportReportPurchaseOrderToPDF;
import Reporting.model.ReportingDetailResponse;
import Reporting.model.ReportingRespone;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import okhttp3.Response;
import org.json.JSONObject;
import pdf.PrintListPDF;
import pdf.PrintToCSV;
import pdf.PrintToExcel;

public class ReportingPurchaseOrder extends javax.swing.JDialog {

     public ArrayList<ReportingDetailResponse> listDetail = new ArrayList<>();
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private String dateFromValue;
     private String dateToValue;

     public ReportingPurchaseOrder(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          initComponents();

          searchField.setFocus();
          jScrollPaneProduct.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          // custome scrollbar ui
          jScrollPaneProduct.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPaneProduct.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPaneProduct.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPaneProduct.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          // set background color
          panelItem.setBackground(WindowColor.mediumGreen);
          header.setBackground(WindowColor.darkGreen);
//          appendPurchaeOrder(panelItem);

          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          dateFrom.initEvent(btnevent);
          dateTo.initEvent(btnevent);

          groupEvent(this);

          paginationPanel.setVisible(false);
     }

     public void groupEvent(ReportingPurchaseOrder re) {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          dateFrom.initEvent(btnevent);
          dateTo.initEvent(btnevent);

          ButtonEvent searchEvent = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    searchValue = searchField.getValueTextSearch();
                    if (searchValue.isEmpty()) {
                         isCheckSearch = true;
                         pageNumber = "0";
                         setData(true);
                         return;
                    }
                    setData(false);

               }
          };
          searchField.initEvent(searchEvent);

          // event export to excel
          ButtonEvent excel = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    export(1);
               }
          };

          groupButtonExport.excelEvent(excel);

          // event export to csv
          ButtonEvent csv = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    export(2);
               }
          };

          groupButtonExport.csvEvent(csv);
          // event export to pdf
          ButtonEvent pdf = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    export(3);
               }
          };

          groupButtonExport.pdfEvent(pdf);

          ButtonEvent paginationEvent = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1;
                         pageNumber = String.valueOf(_value);

                         System.out.println("listDetail : " + listDetail.size());

//                         if (!listDetail.isEmpty()) {
//                              paginationPanel.resetPage();
//                              pageNumber = "0";
//                         }

                         setData(true);
                    }
               }
          };
          paginationPanel.initEvent(paginationEvent);
     }

     private void export(int type) {
          if (listDetail.isEmpty()) {
               JOptionPane.showMessageDialog(null, "Can not export .");
               return;
          }
          Response response = JavaConnection.get(JavaRoute.reportPurchaseOrder + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
          try {
               String responeData = response.body().string();
               ObjectMapper objectMapper = new ObjectMapper();
               ReportingRespone data = objectMapper.readValue(responeData, ReportingRespone.class);
               ReportingDetailResponse[] lists = data.getData();
               listDetail.clear();
               listDetail.addAll(Arrays.asList(lists));

               switch (type) {
                    case 1 -> {
                         ListProduct.msgPrint(PrintToExcel.folderPath);
                         ExportReportPurchaseOrderToExcel.toExcel(listDetail);
                         break;
                    }
                    case 2 -> {
                         ListProduct.msgPrint(PrintToCSV.folderPath);
                         ExportReportPurchaseOrderToCSV.toCSV(listDetail);
                         break;
                    }

                    case 3 -> {
                         ListProduct.msgPrint(PrintListPDF.folderPath);
                         try {
                              ExportReportPurchaseOrderToPDF.printListPdf(listDetail);
                         } catch (IOException ex) {
                              Logger.getLogger(ReportingImportDetail.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    }

               }

          } catch (Exception e) {
               System.err.println("error export : " + e);
          }
     }

     public void reloadPanel() {
          panelItem.removeAll();
          panelItem.repaint();
          panelItem.revalidate();
     }

     public void appendPurchaeOrder(ReportingDetailResponse[] list) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          reloadPanel();
          panelItem.setLayout(gridBagLayout);
          if (list.length == 0) {
               panelItem.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               panelItem.add(nofound, BorderLayout.CENTER);
               panelItem.add(nofound);
               panelItem.revalidate();
               panelItem.repaint();
          }
          int x = 0;
          int y = 0;

          for (int i = 0; i < list.length; i++) {
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
               var data = list[i];

               ReportOfPurchase b = new ReportOfPurchase();
               b.setData(
                    String.valueOf(i + 1),
                    String.valueOf(data.getPurchaseOrderNo()),
                    String.valueOf(data.getTransactionNo()),
                    String.valueOf(data.getTransactionDate()),
                    String.valueOf(data.getOrderDate()),
                    String.valueOf(data.getReferenceNo()),
                    String.valueOf(data.getVendorName()),
                    String.valueOf(data.getTotalQty()),
                    "$".concat(String.valueOf(data.getTotalCost()))
               );
               panelItem.add(b, gbc);
          }

          panelItem.revalidate();
          panelItem.repaint();
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        jPanel2 = new javax.swing.JPanel();
        searchField = new Components.SearchField();
        groupButtonExport = new Reporting.GroupButtonExport();
        buttonSave = new ButtonPackage.ButtonSave();
        dateFrom = new DatePicker.DatePicker();
        dateTo = new DatePicker.DatePicker();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPaneProduct = new javax.swing.JScrollPane();
        panelItem = new javax.swing.JPanel();
        btnCancel = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Reporting Purchase Order");

        searchField.setPlaceholder("Search by name or barcode");
        searchField.setValueTextSearch("");

        buttonSave.setTitleButton("Apply");
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("#");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Transaction №");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Trasaction Date");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Purchase Order №");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Order Date");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Reference №");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total Qty");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total Cost");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vendor Name");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPaneProduct.setBorder(null);

        panelItem.setPreferredSize(new java.awt.Dimension(1408, 430));

        javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
        panelItem.setLayout(panelItemLayout);
        panelItemLayout.setHorizontalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1408, Short.MAX_VALUE)
        );
        panelItemLayout.setVerticalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jScrollPaneProduct.setViewportView(panelItem);

        btnCancel.setButtonName("Close");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneProduct, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPaneProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         setData(true);
    }//GEN-LAST:event_buttonSaveMouseClicked

     void setData(boolean isCheck) {
          dateFromValue = dateFrom.getValueTextField();
          dateToValue = dateTo.getValueTextField();

          if (dateFromValue == null || dateFromValue.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Date From can not be empty!");
               return;
          }

          if (dateToValue == null || dateToValue.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Date To can not be empty!");
               return;
          }

          dateFromValue = JavaConstant.formateDateYYYYMMDD(dateFromValue);
          dateToValue = JavaConstant.formateDateYYYYMMDD(dateToValue);

          JSONObject json = new JSONObject();
          json.put("dateFrom", dateFromValue);
          json.put("dateTo", dateToValue);

          try {

//               if (!listDetail.isEmpty()) {
//                    paginationPanel.resetPage();
//                    pageNumber = "0";
//               }

               Response response = null;
               if (isCheck) { // isCheck true is get items
                    response = JavaConnection.get(JavaRoute.reportPurchaseOrder + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
               } else { // isCheck false is search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.filterReportPurchaseOrder + "" + searchValue + "?pageNumber=0&pageSize=50");
               }

               String dataResponse = response.body().string();
               JSONObject jsonResponse = new JSONObject(dataResponse);
               if (jsonResponse.has("error")) {
                    JSONObject error = jsonResponse.getJSONObject("error");
                    String reason = error.getString("reason");
                    JOptionPane.showMessageDialog(null, reason);
               } else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ReportingRespone data = objectMapper.readValue(dataResponse, ReportingRespone.class);
                    ReportingDetailResponse[] lists = data.getData();

                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage();
                    }

                    listDetail.clear();
                    listDetail.addAll(Arrays.asList(lists));
                    appendPurchaeOrder(lists);
                    if (lists.length != 0) {
                         paginationPanel.setVisible(true);
                    }
               }
          } catch (Exception e) {
               System.out.println("error : " + e);
          }
     }

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

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
               java.util.logging.Logger.getLogger(ReportingPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ReportingPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ReportingPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ReportingPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReportingPurchaseOrder dialog = new ReportingPurchaseOrder(new javax.swing.JFrame(), true);
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
    private Button.Button btnCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private DatePicker.DatePicker dateFrom;
    private DatePicker.DatePicker dateTo;
    private Reporting.GroupButtonExport groupButtonExport;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPaneProduct;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelItem;
    private Components.SearchField searchField;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
