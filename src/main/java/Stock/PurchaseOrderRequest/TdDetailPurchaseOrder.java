package Stock.PurchaseOrderRequest;

import Constant.JavaConstant;
import Event.ButtonEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import javax.swing.Icon;
 
public class TdDetailPurchaseOrder extends javax.swing.JPanel {

     private String id;
     private Icon image;
     private String index;
     private String amountValue;
     private String productId;
     

     public TdDetailPurchaseOrder() {
          initComponents();
          JavaConstant.setPointer(btnDelete);
     }

     public void setDetail(
          String _number,
          String _barcode,
          String _proName,
          String _division,
          String _availableQty,
          String _qty,
          String _cost,
          String _amount,
          String _productId
     ) {
       
          number.setText(_number);
          barcode.setText(_barcode);
          productName.setText(_proName);
          division.setText(_division);
          availableQty.setText(_availableQty);
          qty.setText(_qty);
          cost.setText(_cost);
          amount.setText(_amount);
          productId = _productId;
          index = _number;
 
     }
     
     
     public void setDetailSecond(
          String _number,
          String _barcode,
          String _proName,
          String _division,
          String _availableQty,
          String _qty,
          String _cost,
          String _amount,
          String _id,
          String _productId
     ) {
          number.setText(_number);
          barcode.setText(_barcode);
          productName.setText(_proName);
          division.setText(_division);
          availableQty.setText(_availableQty);
          qty.setText(_qty);
          cost.setText(_cost);
          amount.setText(_amount);
          id = _id;
          productId = _productId;
          index = _number;
 
     }
     
     public Integer getId(){
          return Integer.valueOf(id);
     }
     
     
     public Integer getProductId(){
          return Integer.valueOf(productId);
     }
     
     public String getCost(){
          String costValue = cost.getText().replace("$","");
          costValue = costValue.replace(",", "");
          return costValue;
     }
     
     public String getAmount(){
          String amountValue = amount.getText().replace("$","");
          amountValue = amountValue.replace(",", "");
          return amountValue;
     }

     public String getAmountValue() {
          return amount.getText();
     }

     public void setAmountValue(String amountValue) {
          this.amountValue = amountValue;
     }
     
     public String getQtyUnit(){
          return qty.getText();
     }
     
     public void setIndex(String i){
          number.setText(i);
     }

 

     
     public Icon getImage() {
          return image;
     }

     public void setImage(Icon image) {
          this.image = image;
          btnDelete.setIcon(image);
     }

     public void initEvent(ButtonEvent event) {
          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onRemove(index);
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

          qty.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
//                    JavaConstant.onlyDigits(qtyValue);
               }

               @Override
               public void keyPressed(KeyEvent e) {
//                    JavaConstant.onlyDigits(qtyValue);
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String _cost = cost.getText();
                    _cost = _cost.replace("$", "");
                    _cost = _cost.replace(",", "");

                    double qtyValue = Double.parseDouble(qty.getText());
                    double costValue = Double.parseDouble(_cost);
                    double result = qtyValue * costValue;

                    String _text = JavaConstant.setAmount(BigDecimal.valueOf(result));
                    amount.setText(_text);
                    event.onKeyPress();
               }

          });
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getProduct = new javax.swing.JPanel();
        cost = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        barcode = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        division = new javax.swing.JLabel();
        availableQty = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        btnDelete = new javax.swing.JLabel();

        getProduct.setBackground(new java.awt.Color(255, 255, 255));
        getProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cost.setText("Cost");

        number.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("Number");

        barcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        barcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barcode.setText("Barcode");

        productName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productName.setText("Product Name");

        division.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        division.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        division.setText("Division");

        availableQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        availableQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableQty.setText("Available Qty");

        amount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amount.setText("Amount");

        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.setText("Qty");

        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

        javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
        getProduct.setLayout(getProductLayout);
        getProductLayout.setHorizontalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availableQty, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        getProductLayout.setVerticalGroup(
            getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(availableQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(division, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(productName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(barcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(getProductLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(getProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(getProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amount;
    private javax.swing.JLabel availableQty;
    private javax.swing.JLabel barcode;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel cost;
    private javax.swing.JLabel division;
    private javax.swing.JPanel getProduct;
    private javax.swing.JLabel number;
    private javax.swing.JLabel productName;
    private javax.swing.JTextField qty;
    // End of variables declaration//GEN-END:variables
}
