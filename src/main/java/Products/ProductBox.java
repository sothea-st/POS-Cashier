package Products;

import Color.WindowColor;
import Components.CircleShape;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.Icon;

/**
 *
 * @author FRONT-END.06
 */
public class ProductBox extends javax.swing.JPanel {

     private String qty;
     private int discountPercent;
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");

     public int getDiscountPercent() {
          return discountPercent;
     }

     public void setDiscountPercent(int discountPercent) {
          this.discountPercent = discountPercent;
          if (discountPercent > 0) {
               percent.setDiscountPercent("<html>" + discountPercent + "%" + "<br>" + "Off" + "</html>");
               percent.setVisible(true);
          } else {
               percent.setVisible(false);
          }
     }

     public String getQty() {
          return qty;
     }

     public void setQty(String qty) {
          this.qty = qty;
          lbQty.setText(qty);
     }

     public Icon getFlagImage() {
          return flagImage;
     }

     public void setFlagImage(Icon flagImage) {
          this.flagImage = flagImage;
          flagImg.setIcon(flagImage);
     }

     public String getBarcode() {
          return barcode;
     }

     public void setBarcode(String barcode) {
          this.barcode = barcode;
          txtBarcode.setText("Barcode :" + barcode);
     }

     public String getPrice() {
          return price;
     }

     public void setPrice(String price) {
          this.price = price;
          lbPrice.setText(price + " each");
     }

     public String getWeight() {
          return weight;
     }

     public void setWeight(String weight) {
          this.weight = weight;
          lbWeight.setText(weight);
     }

     public Icon getProductImage() {
          return productImage;
     }

     public void setProductImage(Icon productImage) {
          this.productImage = productImage;
          productImg.setIcon(productImage);
     }

     public String getProductName() {
          return ProductName;
     }

     public void setProductName(String ProductName) {
          this.ProductName = ProductName;
          lbName.setText(ProductName);
     }

     public void setDiscountPercentag(int discountPercentag, Double wasPrice) {
          this.discountPercentag = discountPercentag;
          if (discountPercentag > 0) {
               discount.setDiscountPrice("Was " + dm.format(wasPrice) );
               discount.setVisible(true);
          }
     }

     public String getProductStatus() {
          return productStatus;
     }

     public void setProductStatus(String productStatus) {
          this.productStatus = productStatus;
          buttonStatus.setButtonName(productStatus);
          if (productStatus == "Unavailable") {
               buttonStatus.setBgColor(WindowColor.darkred);
          } else if (productStatus == "Out Stock") {
               buttonStatus.setBgColor(WindowColor.darkred);
          } else {
               buttonStatus.setBgColor(WindowColor.darkGreen);
          }
     }

     /**
      * Creates new form ProductBox
      */
     public ProductBox() {
          initComponents();
          discount.setVisible(false);
          productBox.setBackground(WindowColor.white);
          setBackground(WindowColor.white);
          lbName.setFont(WindowFonts.timeNewRomanBold11);
          lbName.setForeground(WindowColor.black);
          lbWeight.setFont(WindowFonts.timeNewRomanBold10);
          lbWeight.setForeground(WindowColor.gray);
          lbPrice.setFont(WindowFonts.timeNewRomanBold12);
          lbPrice.setForeground(WindowColor.darkGreen);
          txtBarcode.setFont(WindowFonts.timeNewRomanBold9);
          txtBarcode.setForeground(WindowColor.gray);
          lbQty.setFont(WindowFonts.timeNewRomanBold12);
          lbQty.setVisible(false);

     }

     //=================================================
     public void initEvent(ButtonEvent event) {
          btnBuy.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onMouseClick();
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

     //=================================================Create Shadow Box
     private ShadowType shadowType;
     private int shadowSize = 3;
     private float shadowOpacity = 0.8f;
     private Color shadowColor = Color.GRAY;

     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          createShadow(grphcs);
          super.paintComponent(grphcs);
     }

     private void createShadow(Graphics grphcs) {
          Graphics2D g2 = (Graphics2D) grphcs;
          int size = shadowSize * 2;
          int x = 0;
          int y = 0;
          int width = getWidth() - size;
          int height = getHeight() - size;
          if (shadowType == ShadowType.TOP) {
               x = shadowSize;
               y = size;
          } else if (shadowType == ShadowType.BOT) {
               x = shadowSize;
               y = 0;
          } else if (shadowType == ShadowType.TOP_LEFT) {
               x = size;
               y = size;
          } else if (shadowType == ShadowType.TOP_RIGHT) {
               x = 0;
               y = size;
          } else if (shadowType == ShadowType.BOT_LEFT) {
               x = size;
               y = 0;
          } else if (shadowType == ShadowType.BOT_RIGHT) {
               x = 0;
               y = 0;
          } else {
               //  Center
               x = shadowSize;
               y = shadowSize;
          }
          BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g = img.createGraphics();
          g.setColor(getBackground());
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g.fillRoundRect(0, 0, width, height, 10, 10);

          //  Create Shadow
          ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
          g2.drawImage(render.createShadow(img), 0, 0, null);
          g2.drawImage(img, x, y, null);
     }

     private String ProductName;
     private Icon productImage;
     private String weight;
     private String price;
     private String barcode;
     private Icon flagImage;
     private int discountPercentag;
     private String productStatus;

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productBox = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        lbWeight = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JLabel();
        flagImg = new javax.swing.JLabel();
        buttonStatus = new Button.ButtonInstock();
        lbPrice = new javax.swing.JLabel();
        btnBuy = new Button.ButtonBuy();
        jLabel1 = new javax.swing.JLabel();
        discount = new Button.ButtonDiscount();
        lbQty = new javax.swing.JLabel();
        percent = new Components.CircleShape();
        productImg = new javax.swing.JLabel();

        productBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbName.setText("Name");
        productBox.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 91, 148, 25));

        lbWeight.setText("Weight");
        productBox.add(lbWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 122, 77, 15));

        txtBarcode.setText("Barcode :");
        productBox.add(txtBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 189, 110, 17));

        flagImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productBox.add(flagImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 122, 30, 15));
        productBox.add(buttonStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 166, 53, -1));

        lbPrice.setText("Price");
        productBox.add(lbPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 166, 89, -1));
        productBox.add(btnBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 189, 32, -1));
        productBox.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 143, 4, 17));
        productBox.add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 143, 67, -1));

        lbQty.setText("lbQty");
        productBox.add(lbQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 143, -1, -1));
        productBox.add(percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, 50));

        productImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productBox.add(productImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 146, 85));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productBox, javax.swing.GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.ButtonBuy btnBuy;
    private Button.ButtonInstock buttonStatus;
    private Button.ButtonDiscount discount;
    private javax.swing.JLabel flagImg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQty;
    private javax.swing.JLabel lbWeight;
    private Components.CircleShape percent;
    private javax.swing.JPanel productBox;
    private javax.swing.JLabel productImg;
    private javax.swing.JLabel txtBarcode;
    // End of variables declaration//GEN-END:variables
}
