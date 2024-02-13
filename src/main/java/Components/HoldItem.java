package Components;

import Color.WindowColor;
import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoldItem extends javax.swing.JPanel {

     private String name;
     private String qty;

     public HoldItem() {
          initComponents();
          buttonCancel.setButtonName("Process");
          buttonCancel.setBackground(WindowColor.green);
          btnDelete.setButtonName("Delete");
     }

     public void initEvent(ButtonEvent event) {
          buttonCancel.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelect(name);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });
          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onRemove(name);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }

          });
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        holdItem = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        buttonCancel = new ButtonPackage.ButtonCancel();
        btnDelete = new ButtonPackage.ButtonCancel();

        holdItem.setBackground(new java.awt.Color(255, 255, 255));
        holdItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbName.setText("Name");

        lbQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("qty");

        buttonCancel.setBackground(new java.awt.Color(47, 152, 70));
        buttonCancel.setButtonName("Process");
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        btnDelete.setButtonName("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout holdItemLayout = new javax.swing.GroupLayout(holdItem);
        holdItem.setLayout(holdItemLayout);
        holdItemLayout.setHorizontalGroup(
            holdItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holdItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        holdItemLayout.setVerticalGroup(
            holdItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holdItemLayout.createSequentialGroup()
                .addGroup(holdItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, holdItemLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(holdItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holdItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holdItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked

     }//GEN-LAST:event_buttonCancelMouseClicked

     private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
          // TODO add your handling code here:
     }//GEN-LAST:event_btnDeleteMouseClicked

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
          lbName.setText(name);
     }

     public String getQty() {
          return qty;
     }

     public void setQty(String qty) {
          this.qty = qty;
          lbQty.setText(qty);
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel btnDelete;
    private ButtonPackage.ButtonCancel buttonCancel;
    private javax.swing.JPanel holdItem;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbQty;
    // End of variables declaration//GEN-END:variables
}
