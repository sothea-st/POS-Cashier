package Reporting.ReportInventory;

import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Products.ListProduct;
import Reporting.ReportInventory.ReportInventoryModel.ReportInventoryDetail;
import Reporting.ReportInventory.export.ExportReportInventoryToCSV;
import Reporting.ReportInventory.export.ExportReportInventoryToExcel;
import Reporting.ReportingImportDetail;
import Reporting.ReportingItem.ReportOfPurchase;
import Reporting.ReportingPurchaseOrder;
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

public class ReportInventoryForm extends javax.swing.JDialog {

     public ArrayList<ReportInventoryModel.ReportInventoryDetail> listDetail = new ArrayList<>();
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private String dateFromValue;
     private String dateToValue;

     public ReportInventoryForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          setTitle("Report Inventory");
          initComponents();

          searchField.setFocus();
          jScrollPaneP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          // custome scrollbar ui
          jScrollPaneP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPaneP.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPaneP.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPaneP.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          // set background color
          panelItem.setBackground(WindowColor.mediumGreen);
          header.setBackground(WindowColor.darkGreen);

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

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

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
          jLabel11 = new javax.swing.JLabel();
          jScrollPaneP = new javax.swing.JScrollPane();
          panelItem = new javax.swing.JPanel();
          paginationPanel = new pagination.PaginationPanel();
          btnCancel = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
          jLabel2.setText("Product Name");

          jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(255, 255, 255));
          jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel3.setText("Beginning Qty");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel4.setText("Date");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Stock In");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(255, 255, 255));
          jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel7.setText("Available Qty");

          jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel8.setForeground(new java.awt.Color(255, 255, 255));
          jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel8.setText("Return Out Qty");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(255, 255, 255));
          jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel9.setText("Ending Qty");

          jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(255, 255, 255));
          jLabel10.setText("Return In Qty");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(255, 255, 255));
          jLabel11.setText("Stock Out Qty");

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
                    .addGap(45, 45, 45)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(13, 13, 13))
          );

          jScrollPaneP.setBorder(null);

          panelItem.setPreferredSize(new java.awt.Dimension(1200, 430));

          javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
          panelItem.setLayout(panelItemLayout);
          panelItemLayout.setHorizontalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1433, Short.MAX_VALUE)
          );
          panelItemLayout.setVerticalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 430, Short.MAX_VALUE)
          );

          jScrollPaneP.setViewportView(panelItem);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(jScrollPaneP)
                                   .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(18, 18, 18))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPaneP, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(24, Short.MAX_VALUE))
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
               Response response = null;
               if (isCheck) { // isCheck true is get items
                    response = JavaConnection.get(JavaRoute.reportInventory + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
               } else { // isCheck false is search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.reportInventory + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue +"&search="+searchValue);
               }

               String dataResponse = response.body().string();
               JSONObject jsonResponse = new JSONObject(dataResponse);
               if (jsonResponse.has("error")) {
                    JSONObject error = jsonResponse.getJSONObject("error");
                    String reason = error.getString("reason");
                    JOptionPane.showMessageDialog(null, reason);
               } else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ReportInventoryModel data = objectMapper.readValue(dataResponse, ReportInventoryModel.class);
                    ReportInventoryModel.ReportInventoryDetail[] lists = data.getData();

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

     public void appendPurchaeOrder(ReportInventoryDetail[] list) {
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

               ReportInventoryItem b = new ReportInventoryItem();
               b.setData(
                    String.valueOf(i + 1),
                    String.valueOf(data.getDate()),
                    String.valueOf(data.getProductName()),
                    String.valueOf(data.getBeginningQty()),
                    String.valueOf(data.getStockInQty()),
                    String.valueOf(data.getAvailableQty()),
                    String.valueOf(data.getStockOutQty()),
                    String.valueOf(data.getReturnOutQty()),
                    String.valueOf(data.getEndingQty()),
                    String.valueOf(data.getReturnInQty())
               );
               panelItem.add(b, gbc);
          }

          panelItem.revalidate();
          panelItem.repaint();
     }

     public void reloadPanel() {
          panelItem.removeAll();
          panelItem.repaint();
          panelItem.revalidate();
     }

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked
     
      public void groupEvent(ReportInventoryForm re) {
           
           groupButtonExport.setPdf(); // hide btnPdf
           
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
          Response response = JavaConnection.get(JavaRoute.reportInventory + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
          try {
               String responeData = response.body().string();
               ObjectMapper objectMapper = new ObjectMapper();
               ReportInventoryModel data = objectMapper.readValue(responeData, ReportInventoryModel.class);
               ReportInventoryDetail[] lists = data.getData();
               listDetail.clear();
               listDetail.addAll(Arrays.asList(lists));

               switch (type) {
                    case 1 -> {
                         
                         ListProduct.msgPrint(PrintToExcel.folderPath);
                         ExportReportInventoryToExcel.toExcel(listDetail);
                         break;
                    }
                    case 2 -> {
                         ListProduct.msgPrint(PrintToCSV.folderPath);
                         ExportReportInventoryToCSV.toCSV(listDetail);
                         break;
                    }

//                    case 3 -> {
//                         ListProduct.msgPrint(PrintListPDF.folderPath);
//                         try {
//                              ExportReportPurchaseOrderToPDF.printListPdf(listDetail);
//                         } catch (IOException ex) {
//                              Logger.getLogger(ReportingImportDetail.class.getName()).log(Level.SEVERE, null, ex);
//                         }
//                    }

               }

          } catch (Exception e) {
               System.err.println("error export : " + e);
          }
     }
     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReportInventoryForm dialog = new ReportInventoryForm(new javax.swing.JFrame(), true);
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
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JScrollPane jScrollPaneP;
     private pagination.PaginationPanel paginationPanel;
     private javax.swing.JPanel panelItem;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
