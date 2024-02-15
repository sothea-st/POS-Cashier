
package HoldOrder;

import Color.WindowColor;
import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class HoldItems extends javax.swing.JPanel {

    private String countNumber;
    private int qty;
    
    public HoldItems() {
          initComponents();
          buttonProcess.setButtonName("Process");
          buttonProcess.setBackground(WindowColor.green);
          btnDelete.setButtonName("Delete");
     }

     public void initEvent(ButtonEvent event) {
          buttonProcess.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelect(countNumber);
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
                    event.onRemove(countNumber);
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

        holditem = new javax.swing.JPanel();
        number = new javax.swing.JLabel();
        prodQty = new javax.swing.JLabel();
        buttonProcess = new ButtonPackage.ButtonCancel();
        btnDelete = new ButtonPackage.ButtonCancel();

        holditem.setBackground(new java.awt.Color(255, 255, 255));
        holditem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        number.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        number.setText("№ ");

        prodQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prodQty.setText("qty");

        buttonProcess.setBackground(new java.awt.Color(47, 152, 70));
        buttonProcess.setButtonName("Process");
        buttonProcess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonProcessMouseClicked(evt);
            }
        });

        btnDelete.setButtonName("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout holditemLayout = new javax.swing.GroupLayout(holditem);
        holditem.setLayout(holditemLayout);
        holditemLayout.setHorizontalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holditemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prodQty, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        holditemLayout.setVerticalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holditemLayout.createSequentialGroup()
                .addGroup(holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, holditemLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prodQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holditem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holditem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonProcessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonProcessMouseClicked

    }//GEN-LAST:event_buttonProcessMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
       
    }//GEN-LAST:event_btnDeleteMouseClicked

    public String getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(String countNumber) {
        this.countNumber = countNumber;
        number.setText(countNumber);
    }
    

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
        prodQty.setText(""+qty);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel btnDelete;
    private ButtonPackage.ButtonCancel buttonProcess;
    private javax.swing.JPanel holditem;
    private javax.swing.JLabel number;
    private javax.swing.JLabel prodQty;
    // End of variables declaration//GEN-END:variables
}
