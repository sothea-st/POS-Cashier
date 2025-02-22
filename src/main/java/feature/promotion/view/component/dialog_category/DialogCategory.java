package feature.promotion.view.component.dialog_category;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.promotion.controller.DialogCategoryController;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DialogCategory extends javax.swing.JDialog {

     private Boolean isSelectAll = false;

     private DialogCategoryController controller;

     public DialogCategory(java.awt.Frame parent, boolean modal) {

          super(parent, modal);

          initComponents();

          custom();

          controller = new DialogCategoryController(this);
     }

     private void custom() {
          setResizable(false);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);

          setBackground(WindowColor.slightGreen);

          lbCategory.setFont(WindowFonts.timeNewRomanBold14);
          lbSelectAll.setFont(WindowFonts.timeNewRomanBold14);

          JavaConstant.setPointer(boxCheckAll);

          JavaConstant.setScroll(jScrollPane);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          btnCancel = new Button.Button();
          lbCategory = new javax.swing.JLabel();
          lbSelectAll = new javax.swing.JLabel();
          boxCheckAll = new javax.swing.JLabel();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          btnSelect = new ButtonPackage.ButtonSave();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          lbCategory.setText("Select Category");

          lbSelectAll.setText("Select All");

          boxCheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          boxCheckAll.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    boxCheckAllMouseClicked(evt);
               }
          });

          jScrollPane.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 867, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 375, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          btnSelect.setTitleButton("Select");
          btnSelect.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnSelectMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(lbCategory)
                                   .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbSelectAll)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(boxCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(jScrollPane))
                              .addGap(20, 20, 20))))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(boxCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane)
                    .addGap(18, 18, 18)
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

     private void boxCheckAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxCheckAllMouseClicked
          isSelectAll = !isSelectAll;

          ImageIcon icon = null;

          if (isSelectAll) { // true
               icon = new ImageIcon(getClass().getResource("/icon/checked.png"));
          } else { // false
               icon = new ImageIcon(getClass().getResource("/icon/check.png"));
          }

          controller.read();

          boxCheckAll.setIcon(icon);
     }//GEN-LAST:event_boxCheckAllMouseClicked

     private void btnSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseClicked
          controller.getProductByCategory();
     }//GEN-LAST:event_btnSelectMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    DialogCategory dialog = new DialogCategory(new javax.swing.JFrame(), true);
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
     private javax.swing.JLabel boxCheckAll;
     private Button.Button btnCancel;
     private ButtonPackage.ButtonSave btnSelect;
     private javax.swing.JScrollPane jScrollPane;
     private javax.swing.JLabel lbCategory;
     private javax.swing.JLabel lbSelectAll;
     private javax.swing.JPanel panelData;
     // End of variables declaration//GEN-END:variables
}
