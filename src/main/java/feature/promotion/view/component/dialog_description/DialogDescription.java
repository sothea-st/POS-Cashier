package feature.promotion.view.component.dialog_description;

import Components.Color.WindowColor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class DialogDescription extends javax.swing.JDialog {

     public DialogDescription(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();

          appendData();
     }

     private void appendData() {

          panelData.removeAll();

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.columnWidths = new int[]{0}; // Only one column
          gridBagLayout.columnWeights = new double[]{1}; // Column stretches properly
          gridBagLayout.rowWeights = new double[10]; // Dynamic row sizing

          panelData.setLayout(gridBagLayout);

          for (int i = 0; i < 10; i++) {
               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = 0; // Always in the first column
               gbc.gridy = i; // Stack items vertically
               gbc.gridwidth = 1;
               gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally
               gbc.anchor = GridBagConstraints.NORTH; // Align items to the top
               gbc.insets = new Insets(5, 5, 5, 5); // Padding between items

               DescriptionRowData rowData = new DescriptionRowData();
               // rowData.setPreferredSize(new Dimension(200, 30)); // Optional: Set size

               panelData.add(rowData, gbc);
          }

// Refresh UI
          panelData.revalidate();
          panelData.repaint();

     }

     private void custom() {
          setBackground(WindowColor.slightGreen);
          setResizable(false);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          btnCancel = new Button.Button();
          btnSelect = new ButtonPackage.ButtonSave();
          descriptionHeader1 = new feature.promotion.view.component.dialog_description.DescriptionHeader();
          searchField = new Components.SearchField();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          btnSelect.setTitleButton("Select");
          btnSelect.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnSelectMouseClicked(evt);
               }
          });

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          jScrollPane.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 867, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 408, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(jScrollPane)
                         .addComponent(searchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(descriptionHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(descriptionHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void btnSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseClicked

     }//GEN-LAST:event_btnSelectMouseClicked

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
               java.util.logging.Logger.getLogger(DialogDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(DialogDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(DialogDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(DialogDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    DialogDescription dialog = new DialogDescription(new javax.swing.JFrame(), true);
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
     private ButtonPackage.ButtonSave btnSelect;
     private feature.promotion.view.component.dialog_description.DescriptionHeader descriptionHeader1;
     private javax.swing.JScrollPane jScrollPane;
     private javax.swing.JPanel panelData;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
