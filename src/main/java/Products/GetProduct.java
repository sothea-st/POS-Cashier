package Products;

import Constant.JavaConstant;
import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GetProduct extends javax.swing.JPanel {

     public GetProduct() {
          initComponents();
//          id.setVisible(false);
          JavaConstant.setPointer(btnDelete);
          JavaConstant.setPointer(btnEdit);
     }

     private String productName;
     private String productBarcode;
     private String productPrice;
     private int quantity;
     private int productId;
     private Icon image;
     private Icon imageDelete;
     private JPanel panelProduct;
     private String productStatus;

     private JPanel listGetProduct;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     
     public void setData(
          String _barcode,
          String _itemCode,
          String _division,
          String _vendorCode,
          String _vendorName,
          String _productName,
          String _productNameKh,
          String _qty,
          String _price,
          String _cost,
          String _productId
     ){
          barcode.setText(_barcode);
          itemCode.setText(_itemCode);
          division.setText(_division);
          vendorCode.setText(_vendorCode);
          vendorName.setText(_vendorName);
          proName.setText(_productName);
          proNameKh.setText(_productNameKh);
          qty.setText(_qty);
          price.setText(_price);
          cost.setText(_cost);
          productId = Integer.parseInt(_productId);
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
//          id.setText("" + productId);
     }

     public String getProductName() {
          return productName;
     }

     public void setProductName(String productName) {
          this.productName = productName;
          proName.setText(productName);
     }

     public String getProductBarcode() {
          return productBarcode;
     }

     public void setProductBarcode(String productBarcode) {
          this.productBarcode = productBarcode;
//          lbBarcode.setText(productBarcode);
     }

     public String getProductPrice() {
          return productPrice;
     }

     public void setProductPrice(String productPrice) {
          this.productPrice = productPrice;
          price.setText(productPrice);
     }

     public int getQty() {
          return quantity;
     }

     public void setQty(int quantity) {
          this.quantity = quantity;
          qty.setText("" + quantity);
     }

     public Icon getImage() {
          return image;
     }

     public void setImage(Icon image) {
          this.image = image;
          btnEdit.setIcon(image);
     }

     public String getProductStatus() {
          return productStatus;
     }

     public void setProductStatus(String productStatus) {
          this.productStatus = productStatus;
          price.setText(productStatus);
     }

     public Icon getImageDelete() {
          return imageDelete;
     }

     public void setImageDelete(Icon imageDelete) {
          this.imageDelete = imageDelete;
          btnDelete.setIcon(imageDelete);
     }

     public void initEvent(ButtonEvent event) {
          btnEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelect("" + productId);
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
                    event.onRemove("" + productId);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getProduct = new javax.swing.JPanel();
        proName = new javax.swing.JLabel();
        qty = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        barcode = new javax.swing.JLabel();
        itemCode = new javax.swing.JLabel();
        division = new javax.swing.JLabel();
        vendorCode = new javax.swing.JLabel();
        vendorName = new javax.swing.JLabel();
        proNameKh = new javax.swing.JLabel();
        cost = new javax.swing.JLabel();

        getProduct.setBackground(new java.awt.Color(255, 255, 255));
        getProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        proName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        proName.setForeground(new java.awt.Color(0, 0, 0));
        proName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        proName.setText("Product Name");

        qty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        qty.setForeground(new java.awt.Color(0, 0, 0));
        qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qty.setText("Quantity");

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        price.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        price.setForeground(new java.awt.Color(0, 0, 0));
        price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        price.setText("Price");

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        barcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        barcode.setForeground(new java.awt.Color(0, 0, 0));
        barcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barcode.setText("barcode");

        itemCode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        itemCode.setForeground(new java.awt.Color(0, 0, 0));
        itemCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemCode.setText("item code");

        division.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        division.setForeground(new java.awt.Color(0, 0, 0));
        division.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        division.setText("Divisoon");

        vendorCode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        vendorCode.setForeground(new java.awt.Color(0, 0, 0));
        vendorCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vendorCode.setText("Vendor Code");

        vendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        vendorName.setForeground(new java.awt.Color(0, 0, 0));
        vendorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        vendorName.setText("Vendor Name");

        proNameKh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        proNameKh.setForeground(new java.awt.Color(0, 0, 0));
        proNameKh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        proNameKh.setText("Product Name Khmer");

        cost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cost.setForeground(new java.awt.Color(0, 0, 0));
        cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cost.setText("Cost");

        javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
        getProduct.setLayout(getProductLayout);
        getProductLayout.setHorizontalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vendorCode, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        getProductLayout.setVerticalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qty)
                        .addComponent(proName)
                        .addComponent(btnEdit)
                        .addComponent(price)
                        .addComponent(barcode)
                        .addComponent(itemCode)
                        .addComponent(division)
                        .addComponent(vendorCode)
                        .addComponent(vendorName)
                        .addComponent(proNameKh)
                        .addComponent(cost))
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(getProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(getProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel cost;
    private javax.swing.JLabel division;
    private javax.swing.JPanel getProduct;
    private javax.swing.JLabel itemCode;
    private javax.swing.JLabel price;
    private javax.swing.JLabel proName;
    private javax.swing.JLabel proNameKh;
    private javax.swing.JLabel qty;
    private javax.swing.JLabel vendorCode;
    private javax.swing.JLabel vendorName;
    // End of variables declaration//GEN-END:variables
}
