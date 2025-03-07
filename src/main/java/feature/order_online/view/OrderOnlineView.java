package feature.order_online.view;

import Components.Color.WindowColor;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class OrderOnlineView extends javax.swing.JDialog {

     public OrderOnlineView(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          setBackground(WindowColor.slightGreen);
          setTitle("Order Online");
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          boxPanelOnline1 = new feature.order_online.component.BoxPanelOnline();
          boxPanelOnline2 = new feature.order_online.component.BoxPanelOnline();
          boxPanelOnline3 = new feature.order_online.component.BoxPanelOnline();
          btnCancel = new Button.Button();
          paginationPanel = new pagination.PaginationPanel();
          searchField = new Components.SearchField();
          objDate = new FormComponent.datepicker.JavaDatePicker();
          objDate1 = new FormComponent.datepicker.JavaDatePicker();
          objNationality = new FormComponent.combobox.JavaCombobox();
          objNationality1 = new FormComponent.combobox.JavaCombobox();
          buttonSave = new ButtonPackage.ButtonSave();
          orderOnlineHeader1 = new feature.order_online.component.OrderOnlineHeader();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          boxPanelOnline1.setBg(new java.awt.Color(51, 153, 255));
          boxPanelOnline1.setTitle("NEW ORDERS");

          boxPanelOnline2.setBg(new java.awt.Color(255, 51, 51));
          boxPanelOnline2.setTitle(" CANCELLED");

          boxPanelOnline3.setBg(new java.awt.Color(0, 204, 102));
          boxPanelOnline3.setTitle("COMPLETED");

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          objDate.setLabelName("Date of Birth");

          objDate1.setLabelName("Date of Birth");

          objNationality.setLabelName("Nationality *");
          objNationality.setName(""); // NOI18N

          objNationality1.setLabelName("Nationality *");
          objNationality1.setName(""); // NOI18N

          buttonSave.setPreferredSize(new java.awt.Dimension(78, 35));
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          jScrollPane.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1827, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 450, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(30, 30, 30))
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1827, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(layout.createSequentialGroup()
                                        .addComponent(boxPanelOnline1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(boxPanelOnline2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(boxPanelOnline3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(layout.createSequentialGroup()
                                        .addComponent(objDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objNationality1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(orderOnlineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 1827, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(0, 30, Short.MAX_VALUE))))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(boxPanelOnline3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(boxPanelOnline1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(boxPanelOnline2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGap(12, 12, 12)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objNationality1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(6, 6, 6))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(14, 14, 14)))
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(orderOnlineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          
     }//GEN-LAST:event_buttonSaveMouseClicked

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    OrderOnlineView dialog = new OrderOnlineView(new javax.swing.JFrame(), true);
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
     private feature.order_online.component.BoxPanelOnline boxPanelOnline1;
     private feature.order_online.component.BoxPanelOnline boxPanelOnline2;
     private feature.order_online.component.BoxPanelOnline boxPanelOnline3;
     private Button.Button btnCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private javax.swing.JScrollPane jScrollPane;
     private FormComponent.datepicker.JavaDatePicker objDate;
     private FormComponent.datepicker.JavaDatePicker objDate1;
     private FormComponent.combobox.JavaCombobox objNationality;
     private FormComponent.combobox.JavaCombobox objNationality1;
     private feature.order_online.component.OrderOnlineHeader orderOnlineHeader1;
     private pagination.PaginationPanel paginationPanel;
     private javax.swing.JPanel panelData;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
