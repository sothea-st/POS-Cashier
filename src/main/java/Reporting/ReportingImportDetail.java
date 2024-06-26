package Reporting;

import Event.ButtonEvent;

public class ReportingImportDetail extends javax.swing.JDialog {

     public ReportingImportDetail(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          initComponents();
          dateFrom.setLabelTextField("Date From");
          dateTo.setLabelTextField("Date to");
//          dateFrom.unFocus();
//          dateTo.unFocus();
          searchField.setFocus();
          groupEvent();
     }

     //     =============== event ================
     private void groupEvent() {

          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          dateFrom.initEvent(btnevent);
          dateTo.initEvent(btnevent);
          searchField.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          titlePopUp = new Components.LabelPopUpTitle();
          jPanel1 = new javax.swing.JPanel();
          searchField = new Components.SearchField();
          groupButtonExport1 = new Reporting.GroupButtonExport();
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

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          titlePopUp.setLabelTitle("Reporting Import");

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(26, 26, 26)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(groupButtonExport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(0, 9, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(dateFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(dateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(9, 9, 9)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(groupButtonExport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
          jLabel2.setText("Product Barcode");

          jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(255, 255, 255));
          jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel3.setText("Cost");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setText("Product Name");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Quantity");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(255, 255, 255));
          jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel7.setText("Amount");

          jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel8.setForeground(new java.awt.Color(255, 255, 255));
          jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel8.setText("discount");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(255, 255, 255));
          jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel9.setText("Import Date");

          jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(255, 255, 255));
          jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel10.setText("Total");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

          javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
          panelItem.setLayout(panelItemLayout);
          panelItemLayout.setHorizontalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1316, Short.MAX_VALUE)
          );
          panelItemLayout.setVerticalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 627, Short.MAX_VALUE)
          );

          jScrollPaneProduct.setViewportView(panelItem);

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
               .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jScrollPaneProduct)))
                    .addContainerGap(22, Short.MAX_VALUE))
               .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPaneProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          String dateFromValue = dateFrom.getValueTextField();
          String dateToValue = dateTo.getValueTextField();

          System.out.println("dateFrom : " + dateFromValue + " dateTo : " + dateToValue);
     }//GEN-LAST:event_buttonSaveMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReportingImportDetail dialog = new ReportingImportDetail(new javax.swing.JFrame(), true);
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
     private Reporting.GroupButtonExport groupButtonExport1;
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
     private javax.swing.JScrollPane jScrollPaneProduct;
     private javax.swing.JPanel panelItem;
     private Components.SearchField searchField;
     private Components.LabelPopUpTitle titlePopUp;
     // End of variables declaration//GEN-END:variables
}
