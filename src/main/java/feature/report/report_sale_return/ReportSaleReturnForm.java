package feature.report.report_sale_return;

import Components.Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Components.CustomeUI.CustomScrollBarUI;
import Components.Event.ButtonEvent;
 
import feature.Stock.Products.ListProduct;
 
import Reporting.ReportingSaled;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.report.report_sale_return.dto.ReportSaleReturnResponse;
import feature.report.report_sale_return.dto.ReportSaleReturnResponse.ReportSaleReturnDetailResponse;
import feature.report.report_sale_return.export.ExportReportReturnToCSV;
import feature.report.report_sale_return.export.ExportReportReturnToEXCEL;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;
import feature.Print.pdf.PrintToCSV;
import feature.Print.pdf.PrintToExcel;

public class ReportSaleReturnForm extends javax.swing.JDialog {


     private String pageNumber = "1";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String dateFromValue;
     private String dateToValue;
     private String searchValue = null;
     public ArrayList<ReportSaleReturnDetailResponse> listDetail = new ArrayList<>();
     private ReportSaleReturnDetailResponse[] returnDetail;

     public ReportSaleReturnForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          searchField.setFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

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

          //addComboUser();
          paginationPanel.setVisible(false);
          JavaConstant.addTitleAndLogo(this, "Reporting Sale Return");

          eventPagination();
          eventSearchSaleReport();
          groupEventExport();
          groupButtonExport.setPdf();
     }

     private void groupEventExport() {

          // event export to excel
          ButtonEvent excel = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    try {
                         if (listDetail.isEmpty()) {
                              JOptionPane.showMessageDialog(null, "Can not export .");
                              return;
                         }

                         Response response = JavaConnection.get(JavaRoute.returnReport + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
                        
                         String responseData = response.body().string();
                         JSONObject jsonResponse = new JSONObject(responseData);
                         if (jsonResponse.has("error")) {
                              JSONObject error = jsonResponse.getJSONObject("error");
                              String reason = error.getString("reason");
                              JOptionPane.showMessageDialog(null, reason);
                         } else {
                              ObjectMapper objectMapper = new ObjectMapper();
                              ReportSaleReturnResponse data = objectMapper.readValue(responseData, ReportSaleReturnResponse.class);
                              ReportSaleReturnDetailResponse[] details = data.getData();
                              ListProduct.msgPrint(PrintToExcel.folderPath);
                              ExportReportReturnToEXCEL.toExcel(details);
                         }

                    } catch (IOException ex) {
                         Logger.getLogger(ReportingSaled.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
          };
          groupButtonExport.excelEvent(excel);

          // event export to csv
          ButtonEvent csv = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    if (listDetail.isEmpty()) {
                         JOptionPane.showMessageDialog(null, "Can not export .");
                         return;
                    }

                    Response response = JavaConnection.get(JavaRoute.returnReport + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);

                    try {
                         String responseData = response.body().string();
                         JSONObject jsonResponse = new JSONObject(responseData);
                         if (jsonResponse.has("error")) {
                              JSONObject error = jsonResponse.getJSONObject("error");
                              String reason = error.getString("reason");
                              JOptionPane.showMessageDialog(null, reason);
                         } else {
                              ObjectMapper objectMapper = new ObjectMapper();
                              ReportSaleReturnResponse data = objectMapper.readValue(responseData, ReportSaleReturnResponse.class);
                              ReportSaleReturnDetailResponse[] details = data.getData();
                              ListProduct.msgPrint(PrintToCSV.folderPath);
                              ExportReportReturnToCSV.toCSV(details);
                         }

                    } catch (Exception e) {
                         System.out.println("error : " + e);
                    }

               }
          };
          groupButtonExport.csvEvent(csv);

          // event export to pdf
//          ButtonEvent pdf = new ButtonEvent() {
//               @Override
//               public void onMouseClick() {
//
//                    if (listDetail.isEmpty()) {
//                         JOptionPane.showMessageDialog(null, "Can not export .");
//                         return;
//                    }
//
//                    ListProduct.msgPrint(PrintListPDF.folderPath);
//                    try {
//                         ExportReportSaleToPDF.printListPdf(saledDetail);
//                    } catch (IOException ex) {
//                         Logger.getLogger(ReportingImportDetail.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//               }
//          };
//          groupButtonExport.pdfEvent(pdf);
     }

     
     private void eventPagination() {
          ButtonEvent paginationEvent = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value); // value pageNumber star from 1 
                         
                         pageNumber = String.valueOf(_value);
                         getReport(true);
                    }
               }
          };
          paginationPanel.initEvent(paginationEvent);
     }

     private void eventSearchSaleReport() {
          // this event was called when user type on searchTextField 
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = searchField.getValueTextSearch();
                              paginationPanel.resetPage();
                              pageNumber = "0";

                              if (searchValue.isEmpty()) {
                                   isCheckSearch = true;
                                   pageNumber = "1";
                                   getReport(true);
                                   return;
                              }
                              getReport(false);
                         }
                    };
                    Timer time = new Timer();
                    time.schedule(task, 500);
               }
          };
          searchField.initEvent(events);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          mainPanel = new javax.swing.JPanel();
          jPanel1 = new javax.swing.JPanel();
          searchField = new Components.SearchField();
          groupButtonExport = new Reporting.GroupButtonExport();
          buttonSave = new ButtonPackage.ButtonSave();
          dateFrom = new FormComponent.datepicker.JavaDatePicker();
          dateTo = new FormComponent.datepicker.JavaDatePicker();
          header = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          jLabel10 = new javax.swing.JLabel();
          jLabel6 = new javax.swing.JLabel();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel18 = new javax.swing.JLabel();
          btnCancel = new Button.Button();
          jScrollPaneProduct = new javax.swing.JScrollPane();
          panelItem = new javax.swing.JPanel();
          paginationPanel = new pagination.PaginationPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          buttonSave.setTitleButton("Apply");
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          dateFrom.setLabelName("Date From *");

          dateTo.setLabelName("Date To *");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, 0)
                              .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 506, Short.MAX_VALUE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(buttonSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(2, 2, 2)
                              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
          );

          header.setBackground(new java.awt.Color(0, 0, 0));

          jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel1.setForeground(new java.awt.Color(255, 255, 255));
          jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel1.setText("#");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setText("Product Name");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Price");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(255, 255, 255));
          jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel9.setText("Cost ");

          jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(255, 255, 255));
          jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel10.setText("Date");

          jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel6.setForeground(new java.awt.Color(255, 255, 255));
          jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel6.setText("Qty");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(255, 255, 255));
          jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel11.setText("Staff");

          jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(255, 255, 255));
          jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel12.setText("Invoice №");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(255, 255, 255));
          jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel13.setText("Reason");

          jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel18.setForeground(new java.awt.Color(255, 255, 255));
          jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel18.setText("Discount");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(111, 111, 111)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(732, 732, 732))
               .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                         .addGap(766, 766, 766)
                         .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addContainerGap(1032, Short.MAX_VALUE)))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
          );

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          jScrollPaneProduct.setBorder(null);
          jScrollPaneProduct.setPreferredSize(new java.awt.Dimension(990, 361));

          panelItem.setPreferredSize(new java.awt.Dimension(990, 361));

          javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
          panelItem.setLayout(panelItemLayout);
          panelItemLayout.setHorizontalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1217, Short.MAX_VALUE)
          );
          panelItemLayout.setVerticalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 378, Short.MAX_VALUE)
          );

          jScrollPaneProduct.setViewportView(panelItem);

          javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
          mainPanel.setLayout(mainPanelLayout);
          mainPanelLayout.setHorizontalGroup(
               mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(601, 601, 601)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPaneProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          mainPanelLayout.setVerticalGroup(
               mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPaneProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGap(10, 10, 10)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         getReport(true);
    }//GEN-LAST:event_buttonSaveMouseClicked
     private void getReport(boolean isCheck) {

          dateFromValue = JavaConstant.formatDate(dateFrom.getSelectedDate());
          dateToValue = JavaConstant.formatDate(dateTo.getSelectedDate());

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

          //System.err.println("dateFromValue : " + dateFromValue);
          //System.err.println("dateToValue : " + dateToValue);
          
          Response response = null;
          String endpoint = "";

          if (isCheck) {
               response = JavaConnection.get(JavaRoute.returnReport + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize);
          } else {
               isCheckSearch = false;
               response = JavaConnection.get(JavaRoute.returnReport + "/search?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue + "&search=" + searchValue);
          }
          
          try {

               String responseData = response.body().string();
               JSONObject jsonResponse = new JSONObject(responseData);
               if (jsonResponse.has("error")) {
                    JSONObject error = jsonResponse.getJSONObject("error");
                    String reason = error.getString("reason");
                    JOptionPane.showMessageDialog(null, reason);
               } else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ReportSaleReturnResponse data = objectMapper.readValue(responseData, ReportSaleReturnResponse.class);

                    returnDetail = data.getData();

                    // pagination blog
                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage(data.getCount());
                    }

                    listDetail.clear();
                    listDetail.addAll(Arrays.asList(returnDetail));
                    appendItem(returnDetail);

                    if (returnDetail.length != 0) {
                         paginationPanel.setVisible(true);
                    }
               }

          } catch (Exception e) {
               System.err.println("error : gggggggggg " + e);
          }
     }

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();

    }//GEN-LAST:event_btnCancelMouseClicked
     void reloadPanel() {
          panelItem.removeAll();
          panelItem.revalidate();
          panelItem.repaint();
     }

     void appendItem(ReportSaleReturnDetailResponse[] listData) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          reloadPanel();
          panelItem.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          int index = 0;

          if (listData.length == 0) {
               panelItem.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               panelItem.add(nofound, BorderLayout.CENTER);
               panelItem.add(nofound);
               panelItem.revalidate();
               panelItem.repaint();
               paginationPanel.setVisible(false);
          }
          for (int i = 0; i < listData.length; i++) {
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
               index++;
               var item = listData[i];

               SaleReturnData data = new SaleReturnData();

               data.setData(
                    String.valueOf(i + 1),
                    String.valueOf(item.getInvoiceNo()),
                    String.valueOf(JavaConstant.formateDateDDMMYYYY(item.getDate())),
                    String.valueOf(item.getProductName()+" "+item.getChoices()),
                    String.valueOf(item.getQty()),
                    JavaConstant.setAmount(item.getPrice()),
                    JavaConstant.setAmount(item.getDiscount()),
                    JavaConstant.setAmount(item.getCost()),
                    String.valueOf(item.getReason()),
                    String.valueOf(item.getStaff())
               );

               paginationPanel.setVisible(true);
               panelItem.add(data, gbc);
          }

          panelItem.revalidate();
          panelItem.repaint();
     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReportSaleReturnForm dialog = new ReportSaleReturnForm(new javax.swing.JFrame(), true);
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
     private FormComponent.datepicker.JavaDatePicker dateFrom;
     private FormComponent.datepicker.JavaDatePicker dateTo;
     private Reporting.GroupButtonExport groupButtonExport;
     private javax.swing.JPanel header;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel18;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPaneProduct;
     private javax.swing.JPanel mainPanel;
     private pagination.PaginationPanel paginationPanel;
     private javax.swing.JPanel panelItem;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
