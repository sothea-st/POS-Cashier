package Components;

import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 *
 * @author FRONT-END.06
 */
public class SubtotalPanel extends javax.swing.JPanel {
 
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");

     /**
      * @return the labelSubtotalKhr
      */
     public String getLabelSubtotalKhr() {
          return labelSubtotalKhr;
     }

     /**
      * @param labelSubtotalKhr the
      * labelSubtotalKhr to set
      */
     public void setLabelSubtotalKhr(String labelSubtotalKhr) {
          this.labelSubtotalKhr = labelSubtotalKhr;
          txtSubTotalKhr.setText(labelSubtotalKhr);
     }

     /**
      * @return the labelSubtotalUsd
      */
     public String getLabelSubtotalUsd() {
          return labelSubtotalUsd;
     }

     /**
      * @param labelSubtotalUsd the
      * labelSubtotalUsd to set
      */
     public void setLabelSubtotalUsd(String labelSubtotalUsd) {
          this.labelSubtotalUsd = labelSubtotalUsd;
          txtSubTotalUsd.setText(labelSubtotalUsd);
     }

     public String getLableDiscountUsd() {
          return lableDiscountUsd;
     }

     public void setLableDiscountUsd(String lableDiscountUsd) {
          this.lableDiscountUsd = lableDiscountUsd;
          txtDiscountUsd.setText(lableDiscountUsd);
     }

     public String getLableDiscountKhr() {
          return lableDiscountKhr;
     }

     public void setLableDiscountKhr(String lableDiscountKhr) {
          this.lableDiscountKhr = lableDiscountKhr;
          txtDiscountKhr.setText(lableDiscountKhr);
     }

     public String getLableDeliveryUsd() {
          return lableDeliveryUsd;
     }

     public void setLableDeliveryUsd(String lableDeliveryUsd) {
          this.lableDeliveryUsd = lableDeliveryUsd;
          txtDeliveryUsd.setText(lableDeliveryUsd);
     }

     public String getLableDeliveryKhr() {
          return lableDeliveryKhr;
     }

     public void setLableDeliveryKhr(String lableDeliveryKhr) {
          this.lableDeliveryKhr = lableDeliveryKhr;
          txtDeliveryKhr.setText(lableDeliveryKhr);
     }

     public String getLableTotalUsd() {
          return lableTotalUsd;
     }

     public void setLableTotalUsd(String lableTotalUsd) {
          this.lableTotalUsd = lableTotalUsd;
          txtTotalUsd.setText(lableTotalUsd);
     }

     public String getLableTotalKhr() {
          return lableTotalKhr;
     }

     public void setLableTotalKhr(String lableTotalKhr) {
          this.lableTotalKhr = lableTotalKhr;
          txtTotalKhr.setText(lableTotalKhr);
     }

     public void setLabelSubTitleToZero() {
          setLabelSubtotalUsd("$ 0.00");
          setLabelSubtotalKhr("0");
          setLableDiscountUsd("$ 0.00");
          setLableDiscountKhr("0");
          setLableDeliveryUsd("$ 0.00");
          setLableDeliveryKhr("0");
          setLableTotalUsd("$ 0.00");
          setLableTotalKhr("0");
     }

     public SubtotalPanel() {
          initComponents();
          setBackground(WindowColor.mediumGreen);
          setFont(WindowFonts.timeNewRomanBold14);
     }

     private String labelSubtotalUsd;
     private String labelSubtotalKhr;
     private String lableDiscountUsd;
     private String lableDiscountKhr;
     private String lableDeliveryUsd;
     private String lableDeliveryKhr;
     private String lableTotalUsd;
     private String lableTotalKhr;

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

     public void total(double price, Component[] listCom, double discountProduct, SubtotalPanel subtotalPanel) {
          double sumAmountUsd = price;
          double sumDiscount = discountProduct;

          if (listCom.length != 0) {
               for (int i = 0; i < listCom.length; i++) {
                    var data = ((BoxItem) listCom[i]);
                    // sub total usd
                    sumAmountUsd += JavaConstant.getReplace(data.getLabelAmountUsd());

                    // discont usd
                    int qty = data.getQty();

                    double discount = JavaConstant.getReplace(data.getDiscountAmount());
                    sumDiscount += Double.valueOf(discount);
               }
          }

          subtotalPanel.setLabelSubtotalUsd(dm.format(sumAmountUsd));
          double subTotalValueKh = JavaRoundDown.roundDown("" + sumAmountUsd * JavaConstant.exchangeRate);
          subtotalPanel.setLabelSubtotalKhr(kh.format(subTotalValueKh));

          subtotalPanel.setLableDiscountUsd(dm.format(sumDiscount));
          double disKh = JavaRoundDown.roundDown("" + sumDiscount * JavaConstant.exchangeRate);
          subtotalPanel.setLableDiscountKhr(kh.format(disKh));

          subtotalPanel.setLableDeliveryUsd(dm.format(0));
          subtotalPanel.setLableDeliveryKhr(kh.format(0));
          // total
          double total = sumAmountUsd - sumDiscount;
          subtotalPanel.setLableTotalUsd(dm.format(total));
          double valueKh = JavaRoundDown.roundDown("" + total * JavaConstant.exchangeRate);
          subtotalPanel.setLableTotalKhr(kh.format(valueKh));

          //          String khValue = kh.format(total * JavaConstant.exchangeRate);
          //          khValue = khValue.replaceAll(",", "");
          //          //          khValue = "9999967";
          //          int l = khValue.length();
          //          int begin = l - 2;
          //          String last2Number = khValue.substring(begin, l);
          //          String value = "";
          //          if (!last2Number.equals("00")) {
          //               String[] listStr = khValue.split("");
          //               int lengthChar = listStr.length;
          //
          //               switch (lengthChar) {
          //                    case 3:
          //                         value = JavaRoundUpKhr.roundUp3length(listStr);
          //                         break;
          //                    case 4:
          //                         value = JavaRoundUpKhr.roundUpKhr4length(listStr);
          //                         break;
          //                    case 5:
          //                         value = JavaRoundUpKhr.roundUpKhr5length(listStr);
          //                         break;
          //                    case 6:
          //                         value = JavaRoundUpKhr.roundUpKhr6length(listStr);
          //                         break;
          //                    case 7:
          //                         value = JavaRoundUpKhr.roundUpKhr7length(listStr);
          //                         break;
          //               }
          //          }
          //          System.err.println("data value = " + value);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSubTotalKhr = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDiscountKhr = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDeliveryKhr = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotalKhr = new javax.swing.JLabel();
        lbSubTotal = new Components.Label();
        lbDiscount = new Components.Label();
        lbDelivery = new Components.Label();
        lbTotal = new Components.Label();
        txtSubTotalUsd = new javax.swing.JLabel();
        txtDiscountUsd = new javax.swing.JLabel();
        txtDeliveryUsd = new javax.swing.JLabel();
        txtTotalUsd = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setFocusCycleRoot(true);

        txtSubTotalKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSubTotalKhr.setText("0");

        jLabel4.setText(":");

        jLabel7.setText(":");

        txtDiscountKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDiscountKhr.setText("0");

        jLabel11.setText(":");

        txtDeliveryKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDeliveryKhr.setText("0");

        jLabel15.setText(":");

        txtTotalKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTotalKhr.setText("0");

        lbSubTotal.setLabelName("Subtotal :");

        lbDiscount.setLabelName("Discount :");

        lbDelivery.setLabelName("Delivery Fee :");

        lbTotal.setLabelName("Total :");

        txtSubTotalUsd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSubTotalUsd.setText("$ 0.00");

        txtDiscountUsd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDiscountUsd.setText("$ 0.00");

        txtDeliveryUsd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDeliveryUsd.setText("$ 0.00");

        txtTotalUsd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTotalUsd.setText("$ 0.00");

        jLabel1.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel1.setText("៛");

        jLabel3.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel3.setText("៛");

        jLabel5.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel5.setText("៛");

        jLabel6.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel6.setText("៛");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(lbDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(lbDelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDeliveryUsd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiscountUsd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubTotalUsd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubTotalKhr)
                    .addComponent(txtDeliveryKhr, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiscountKhr, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotalKhr, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiscountUsd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDeliveryUsd))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15)
                                                .addComponent(txtTotalKhr)
                                                .addComponent(txtTotalUsd)
                                                .addComponent(jLabel6))
                                            .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtDeliveryKhr)
                                        .addComponent(jLabel5))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtDiscountKhr)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtSubTotalKhr)
                        .addComponent(txtSubTotalUsd)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private Components.Label lbDelivery;
    private Components.Label lbDiscount;
    private Components.Label lbSubTotal;
    private Components.Label lbTotal;
    private javax.swing.JLabel txtDeliveryKhr;
    private javax.swing.JLabel txtDeliveryUsd;
    private javax.swing.JLabel txtDiscountKhr;
    private javax.swing.JLabel txtDiscountUsd;
    private javax.swing.JLabel txtSubTotalKhr;
    private javax.swing.JLabel txtSubTotalUsd;
    private javax.swing.JLabel txtTotalKhr;
    private javax.swing.JLabel txtTotalUsd;
    // End of variables declaration//GEN-END:variables
}
