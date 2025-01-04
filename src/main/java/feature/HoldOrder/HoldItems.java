
package feature.HoldOrder;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class HoldItems extends javax.swing.JPanel {

    private String countNumber;
    private int qty;
    
    private JPanel panelProduct;
    private JPanel detailItem;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }
    
    
    
    public HoldItems() {
          initComponents();
//          buttonProcess.setButtonName("Process");
//          buttonProcess.setBackground(WindowColor.green);
//          btnDelete.setButtonName("Delete");
//          btnDelete.setBackground(WindowColor.darkred);
     }

     public void initEvent(ButtonEvent event) {
          btnEdit.addMouseListener(new MouseListener() {
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
          btnDelete1.addMouseListener(new MouseListener() {
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
        btnDelete1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();

        holditem.setBackground(new java.awt.Color(255, 255, 255));
        holditem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        holditem.setPreferredSize(new java.awt.Dimension(583, 40));

        number.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("№ ");

        prodQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prodQty.setText("qty");

        btnDelete1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

        javax.swing.GroupLayout holditemLayout = new javax.swing.GroupLayout(holditem);
        holditem.setLayout(holditemLayout);
        holditemLayout.setHorizontalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holditemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(prodQty, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        holditemLayout.setVerticalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(prodQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holditem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holditem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel btnDelete1;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JPanel holditem;
    private javax.swing.JLabel number;
    private javax.swing.JLabel prodQty;
    // End of variables declaration//GEN-END:variables
}
