package Products;

import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class GetProduct extends javax.swing.JPanel {


    public GetProduct() {
        initComponents();
        id.setVisible(false);
    }

    private String productName;
    private String productBarcode;
    private String productPrice;
    private int quantity;
    private int productId;
    private int numberNo;
    private JPanel panelProduct;

    private JPanel listGetProduct;

    public JPanel getPanelProduct() {
         return panelProduct;
    }

    public void setPanelProduct(JPanel panelProduct) {
         this.panelProduct = panelProduct;
    }

    @SuppressWarnings("unchecked")
    
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

     public int getQty() {
          return quantity;
     }

     public void setQty(int quantity) {
          this.quantity = quantity;
          qty.setText(""+quantity);
     }

    public int getNumberNo() {
        return numberNo;
    }

    public void setNumberNo(int numberNo) {
        this.numberNo = numberNo;
        number.setText(""+numberNo);
    }
     
     
     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getProduct = new javax.swing.JPanel();
        lbBarcode = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        qty = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        number = new javax.swing.JLabel();

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

        qty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        qty.setForeground(new java.awt.Color(0, 0, 0));
        qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qty.setText("Quantity");

        id.setText("jLabel1");

        number.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        number.setForeground(new java.awt.Color(0, 0, 0));
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("Number");

        javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
        getProduct.setLayout(getProductLayout);
        getProductLayout.setHorizontalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getProductLayout.setVerticalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBarcode)
                    .addComponent(lbPrice)
                    .addComponent(qty)
                    .addComponent(lbName)
                    .addComponent(id)
                    .addComponent(number))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel getProduct;
    private javax.swing.JLabel id;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel number;
    private javax.swing.JLabel qty;
    // End of variables declaration//GEN-END:variables
}
