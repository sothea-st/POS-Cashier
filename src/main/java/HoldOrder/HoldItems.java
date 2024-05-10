
package HoldOrder;

import Color.WindowColor;
import Event.ButtonEvent;
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
          buttonProcess.setButtonName("Process");
//          buttonProcess.setBackground(WindowColor.green);
          btnDelete.setButtonName("Delete");
//          btnDelete.setBackground(WindowColor.darkred);
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
        btnDelete = new Button.ButtonBuy();
        buttonProcess = new Button.ButtonInstock();

        holditem.setBackground(new java.awt.Color(255, 255, 255));
        holditem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        holditem.setPreferredSize(new java.awt.Dimension(583, 40));

        number.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        number.setText("№ ");

        prodQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prodQty.setText("qty");

        javax.swing.GroupLayout holditemLayout = new javax.swing.GroupLayout(holditem);
        holditem.setLayout(holditemLayout);
        holditemLayout.setHorizontalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holditemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prodQty, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(57, 57, 57)
                .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        holditemLayout.setVerticalGroup(
            holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holditemLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(holditemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(buttonProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(prodQty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(holditem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(holditem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    private Button.ButtonBuy btnDelete;
    private Button.ButtonInstock buttonProcess;
    private javax.swing.JPanel holditem;
    private javax.swing.JLabel number;
    private javax.swing.JLabel prodQty;
    // End of variables declaration//GEN-END:variables
}
