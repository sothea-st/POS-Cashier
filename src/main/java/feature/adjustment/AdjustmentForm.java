package feature.adjustment;

import feature.adjustment.adjustmetn_detail.AdjustmentDetail;
import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;

import Constant.JavaRoute;
import FormComponent.combobox.JavaComboBoxSelection;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.adjustment.adjustment_controller.AdjustmentController;
import feature.adjustment.component.AdjustmentItem;
import feature.adjustment.model.AdjustmentModel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import okhttp3.Response;

public class AdjustmentForm extends javax.swing.JDialog {

     private String pageNumber = "1";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private int dataCount = 0;
     private String pageType;

     private AdjustmentModel.AdjustmentDetail[] listData;

     private AdjustmentForm adjustmentForm;

     private AdjustmentController adjustmentController;

     public AdjustmentForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          adjustmentForm = this;
          adjustmentController = new AdjustmentController(this);

          groupButtonExport.setPdf();

          custom();

          cmdStatus();

          cmdVendor();

          getAdjustment();

          eventSearch();

          eventPagination();
     }

     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value); // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         getData(true);
                    }
               }

               // for pagination
               @Override
               public void onMouseClick(String value, String pType) {
                    pageType = pType;
               }
          };
          paginationPanel.initEvent(event);
     }

     //Action Search
     private void eventSearch() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = searchField.getValueTextSearch();
                              paginationPanel.resetPage();
                              pageNumber = "1";

                              if (searchValue.isEmpty()) {
                                   isCheckSearch = true;
                                   pageNumber = "1";
                                   getData(true);
                                   return;
                              }
                              getData(false);
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               }
          };
          searchField.initEvent(event);
     }

     private void getAdjustment() {
          // Get current date
          LocalDate currentDate = LocalDate.now();
          String endDate = currentDate.toString();

          // Get the start of the month
          LocalDate startOfMonth = currentDate.withDayOfMonth(1);
          String statDate = startOfMonth.toString();

          objDateFrom.setSelectedDate(JavaConstant.formateDateDDMMYYYY(statDate));
          objDateTo.setSelectedDate(JavaConstant.formateDateDDMMYYYY(endDate));

          getData(true);
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          panelData.setBackground(WindowColor.mediumGreen);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          btnCancel = new Button.Button();
          panelTop = new javax.swing.JPanel();
          objDateFrom = new FormComponent.datepicker.JavaDatePicker();
          objDateTo = new FormComponent.datepicker.JavaDatePicker();
          buttonSave = new ButtonPackage.ButtonSave();
          objTransaction = new FormComponent.JavaTextField();
          objStatus = new FormComponent.combobox.JavaCombobox();
          objReason = new FormComponent.combobox.JavaCombobox();
          buttonSave1 = new ButtonPackage.ButtonSave();
          adjustmentHeader1 = new feature.adjustment.component.AdjustmentHeader();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          paginationPanel = new pagination.PaginationPanel();
          searchField = new Components.SearchField();
          groupButtonExport = new Reporting.GroupButtonExport();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          objDateFrom.setLabelName("Date From *");

          objDateTo.setLabelName("Date To *");

          buttonSave.setTitleButton("+ Add");
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          objTransaction.setLabelName("Transaction");
          objTransaction.setPlaceHolder("Transaction");

          objStatus.setLabelName("Status");
          objStatus.setName(""); // NOI18N

          objReason.setLabelName(" Reason");
          objReason.setName(""); // NOI18N

          buttonSave1.setTitleButton("Find");
          buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSave1MouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
          panelTop.setLayout(panelTopLayout);
          panelTopLayout.setHorizontalGroup(
               panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelTopLayout.createSequentialGroup()
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelTopLayout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addComponent(objDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(12, 12, 12)
                              .addComponent(objStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(12, 12, 12)
                              .addComponent(objReason, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(12, 12, 12)
                              .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelTopLayout.createSequentialGroup()
                              .addGap(1462, 1462, 1462)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          panelTopLayout.setVerticalGroup(
               panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelTopLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelTopLayout.createSequentialGroup()
                              .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(8, 8, 8))
                         .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(objDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(objDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(objStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(objReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(objTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(10, Short.MAX_VALUE))
          );

          jScrollPane.setBorder(null);

          panelData.setPreferredSize(new java.awt.Dimension(1530, 477));

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1532, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 500, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addComponent(adjustmentHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 1532, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                              .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                    .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(groupButtonExport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(adjustmentHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane)
                    .addGap(10, 10, 10)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          dispose();
          AdjustmentCreateForm adjustmentCreateForm = new AdjustmentCreateForm(new Frame(), true);
          adjustmentCreateForm.setVisible(true);
     }//GEN-LAST:event_buttonSaveMouseClicked

     private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked
          getData(true);
     }//GEN-LAST:event_buttonSave1MouseClicked

     public void getData(boolean isCheck) {

          String transaction = objTransaction.getValueTextField();
          String status = objStatus.getSelectedItem();
          String reason = objReason.getSelectedItem();

          StringBuilder filterBuilder = new StringBuilder();

          if (isCheck) { // true  get data
               if (transaction != null && !transaction.isEmpty()) {
                    filterBuilder.append("&transaction=").append(transaction);
               }
          } else { // false search
               
               if (searchValue != null && !searchValue.isEmpty()) {
                    isCheckSearch = false;
                    filterBuilder.append("&transaction=").append(searchValue);
               }
          }

          if (!"0".equals(status)) {
               filterBuilder.append("&status=").append(status);
          }

          if (!"0".equals(reason)) {
               filterBuilder.append("&reasonId=").append(reason);
          }

          String filter = filterBuilder.toString();

          Response response = null;

          response = JavaConnection.get(JavaRoute.filterAdjustment + ""
               + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom="
               + objDateFrom.getSelectedDate() + "&dateTo=" + objDateTo.getSelectedDate() + filter);

          try {

               if (response.isSuccessful()) {
                    String responeData = response.body().string();

                    // create ojbect mapper 
                    ObjectMapper objMapper = new ObjectMapper();

                    // convert responseData to Object
                    AdjustmentModel data = objMapper.readValue(responeData, AdjustmentModel.class);

                    // pagination code
                    dataCount = (int) data.getCount();
                    if (isCheck) { // true get
                         paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
                    } else { // false search
                         paginationPanel.resetPage(dataCount);
                    }

                    if (listData != null) {
                         Arrays.fill(listData, null);
                    }
                    listData = data.getData(); // Assign new data

                    setData();

               } else {
                    System.err.println("fail loading product");
               }

          } catch (Exception e) {
               System.err.println("error get adjustmens : " + e);
          }
     }

     private void setData() {

          panelData.removeAll();
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          if (listData.length > 0) {
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
                    Integer adjustmentId = listData[i].getId();

                    AdjustmentItem item = new AdjustmentItem(listData[i], (i + 1));

                    ButtonEvent statusEvent = new ButtonEvent() {
                         @Override
                         public void onSelected(String key) {
                              adjustmentController.updateStatus(adjustmentId, key);
                         }

                         @Override
                         public void onDelete() {
                              adjustmentController.alertOption(adjustmentId);
                         }

                         @Override
                         public void onInfo() {
                              AdjustmentDetail detail = new AdjustmentDetail(new JFrame(), true);
                              detail.setAdjustmentId(adjustmentId);
                              detail.setVisible(true);
                         }

                         @Override
                         public void onEdit() {
                              dispose();
                              AdjustmentCreateForm adjustmentCreateForm = new AdjustmentCreateForm(new Frame(), true);
                              adjustmentCreateForm.update(adjustmentId);
                              adjustmentCreateForm.setVisible(true);
                         }

                    };
                    item.initEvent(statusEvent);

                    paginationPanel.setVisible(true);
                    panelData.add(item, gbc);
               }
          } else {
               panelData.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               panelData.add(nofound, BorderLayout.CENTER);
               panelData.add(nofound);
               panelData.revalidate();
               panelData.repaint();
               paginationPanel.setVisible(false);
          }

          panelData.revalidate();
          panelData.repaint();
     }

     private void cmdStatus() {
          LinkedHashMap<String, String> map = new LinkedHashMap<>();
          map.put("Draft", "Draft");
          map.put("Posted", "Posted");
          map.put("Cancelled", "Cancelled");
          objStatus.setMap(map);
     }

     private void cmdVendor() {
          JavaComboBoxSelection.addComboBox(objReason,
               JavaRoute.reason + "Adjustment",
               "reason",
               JavaComboBoxSelection.DESC);
     }

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AdjustmentForm dialog = new AdjustmentForm(new javax.swing.JFrame(), true);
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
     private feature.adjustment.component.AdjustmentHeader adjustmentHeader1;
     private Button.Button btnCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private ButtonPackage.ButtonSave buttonSave1;
     private Reporting.GroupButtonExport groupButtonExport;
     private javax.swing.JScrollPane jScrollPane;
     private FormComponent.datepicker.JavaDatePicker objDateFrom;
     private FormComponent.datepicker.JavaDatePicker objDateTo;
     private FormComponent.combobox.JavaCombobox objReason;
     private FormComponent.combobox.JavaCombobox objStatus;
     private FormComponent.JavaTextField objTransaction;
     private pagination.PaginationPanel paginationPanel;
     private javax.swing.JPanel panel;
     private javax.swing.JPanel panelData;
     private javax.swing.JPanel panelTop;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
