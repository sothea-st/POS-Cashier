package Discount;

import Event.ButtonEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GetProduct extends javax.swing.JPanel {

     private String productName;
     private String productBarcode;
     private String productPrice;
     private int productDiscount;
     private int productId;
     private JPanel panelProduct;

     private JPanel listGetProduct;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     
     
     public void initEvent(ButtonEvent event) {
          buttonEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onClick();
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
     
     
     
     public GetProduct() {
          initComponents();
          id.setVisible(false);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getProduct = new javax.swing.JPanel();
        lbBarcode = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        discount = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        buttonEdit = new Button.ButtonInstock();

        getProduct.setBackground(new java.awt.Color(255, 255, 255));
        getProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbBarcode.setForeground(new java.awt.Color(0, 0, 0));
        lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBarcode.setText("Product Barcode");

        lbPrice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(0, 0, 0));
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrice.setText("Product Price");

        lbName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbName.setForeground(new java.awt.Color(0, 0, 0));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName.setText("Product Name");

        discount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        discount.setForeground(new java.awt.Color(0, 0, 0));
        discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discount.setText("Discount");

        id.setText("jLabel1");

        buttonEdit.setButtonName("Add Discount");
        buttonEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
        getProduct.setLayout(getProductLayout);
        getProductLayout.setHorizontalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getProductLayout.setVerticalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbBarcode)
                        .addComponent(lbPrice)
                        .addComponent(discount)
                        .addComponent(lbName)
                        .addComponent(id)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(getProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(getProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseClicked
//         DiscountByItem dis = new DiscountByItem(new JFrame(), true);
//         dis.setId(productId);
//         dis.setListGetProduct(listGetProduct);
//       
//         dis.setVisible(true);
    }//GEN-LAST:event_buttonEditMouseClicked

     public JPanel getListGetProduct() {
          return listGetProduct;
     }

     public void setListGetProduct(JPanel listGetProduct) {
          this.listGetProduct = listGetProduct;
     }

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
          id.setText("" + productId);
     }

     public String getProductName() {
          return productName;
     }

     public void setProductName(String productName) {
          this.productName = productName;
          lbName.setText(productName);
     }

     public String getProductBarcode() {
          return productBarcode;
     }

     public void setProductBarcode(String productBarcode) {
          this.productBarcode = productBarcode;
          lbBarcode.setText(productBarcode);
     }

     public String getProductPrice() {
          return productPrice;
     }

     public void setProductPrice(String productPrice) {
          this.productPrice = productPrice;
          lbPrice.setText(productPrice);
     }

     public int getProductDiscount() {
          return productDiscount;
     }

     public void setProductDiscount(int productDiscount) {
          this.productDiscount = productDiscount;
          discount.setText(productDiscount + " " + "%");
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.ButtonInstock buttonEdit;
    private javax.swing.JLabel discount;
    private javax.swing.JPanel getProduct;
    private javax.swing.JLabel id;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    // End of variables declaration//GEN-END:variables
}
