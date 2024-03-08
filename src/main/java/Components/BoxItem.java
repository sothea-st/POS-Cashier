package Components;

import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import DeleteAndCancel.DeleteDialog;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import NewDiscounts.Discounting;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class BoxItem extends javax.swing.JPanel {
     
     
     public int getLabelQuantity() {
          return labelQuantity;
     }

     public void setLabelQuantity(int labelQuantity) {
          this.labelQuantity = labelQuantity;
     }

     public String getLabelAmountKh() {
          return labelAmountKh;
     }

     public void setLabelAmountKh(String labelAmountKh) {
          this.labelAmountKh = labelAmountKh;
          amountkh.setText(labelAmountKh);
     }

     public String getLabelAmountUsd() {
          return labelAmountUsd;
     }

     public void setLabelAmountUsd(String labelAmountUsd) {
          this.labelAmountUsd = labelAmountUsd;
          amountUsd.setText(labelAmountUsd);
     }

     public String getLabelBarcode() {
          return labelBarcode;
     }

     public void setLabelBarcode(String labelBarcode) {
          this.labelBarcode = labelBarcode;
          lbBarcode.setText("Barcode : " + labelBarcode);
     }

     public String getLabelPrice() {
          return labelPrice;
     }

     public void setLabelPrice(String labelPrice) {
          this.labelPrice = labelPrice;
          lbPrice.setText(labelPrice + " each");
     }

     public String getLabelWeight() {
          return labelWeight;
     }

     public void setLabelWeight(String labelWeight) {
          this.labelWeight = labelWeight;
          lbWeight.setText(labelWeight);
     }

     public Icon getIconImage() {
          return iconImage;
     }

     public String getDiscountAmount() {
          return discountAmount;
     }

     public void setDiscountAmount(String discountAmount) {
          this.discountAmount = discountAmount;
          txtDiscount.setText("Discount : " + discountAmount);
     }

     public void setIconImage(Icon iconImage) {
          this.iconImage = iconImage;
          img.setIcon(iconImage);
     }

     public String getLabelProductName() {
          return LabelProductName;
     }

     public void setLabelProductName(String LabelProductName) {
          this.LabelProductName = LabelProductName;
          title.setText(LabelProductName);
     }

     public int getQty() {
          return qty;
     }

     public void setQty(int qty) {
          this.qty = qty;
          buttonAddProduct.setQuantity(qty);
     }

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
          proId.setText("" + productId);
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public int getDiscountDigit() {
          return discountDigit;
     }

     public void setDiscountDigit(int discountDigit) {
          this.discountDigit = discountDigit;
          boxDiscount.setText("" + discountDigit);
     }

     //=================================================
     public void initEvent(ButtonEvent event) {
          btnDelete.addMouseListener(new MouseListener() {
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

     public String getWasPrice() {
          return wasPrice;
     }

     public void setWasPrice(String wasPrice) {
          this.wasPrice = wasPrice;
     }

     public String getDiscountAmt() {
          return discountAmt;
     }

     public void setDiscountAmt(String discountAmt) {
          this.discountAmt = discountAmt;
     }

     public Button.Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button.Button btnPayment) {
          this.btnPayment = btnPayment;
     }

    public ButtonPackage.ButtonCancel getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ButtonPackage.ButtonCancel btnCancel) {
        this.btnCancel = btnCancel;
    }
     
     public Button.Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button.Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
     
     
     
     /**
      * Creates new form BoxItem
      */
     private String LabelProductName;
     private Icon iconImage;
     private String labelWeight;
     private String labelPrice;
     private String labelBarcode;
     private String labelAmountUsd;
     private String labelAmountKh;
     private String discountAmount;
     private int labelQuantity;
     private int qty;
     private int productId;
     private SubtotalPanel subtotalPanel;
     private Component[] listCom;
     private JPanel detailItem;
     private int discountDigit;
     private String wasPrice;
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private String discountAmt;
     private Button.Button btnPayment;
     private ButtonPackage.ButtonCancel btnCancel;
     private Button.Button buttonHoldOrder;
     private String discountType;
     private double discountValue;
     

     public BoxItem() {
          initComponents();

          title.setFont(WindowFonts.timeNewRomanBold11);
          title.setForeground(WindowColor.black);
          lbWeight.setFont(WindowFonts.timeNewRomanBold10);
          lbWeight.setForeground(WindowColor.gray);
          lbSale.setFont(WindowFonts.timeNewRomanBold11);
          lbSale.setForeground(WindowColor.green);
          lbPrice.setFont(WindowFonts.timeNewRomanBold11);
          lbPrice.setForeground(WindowColor.darkGreen);
          amountUsd.setFont(WindowFonts.timeNewRomanBold11);
          amountUsd.setForeground(WindowColor.darkGreen);
          amountkh.setFont(WindowFonts.timeNewRomanBold11);
          amountkh.setForeground(WindowColor.darkGreen);
          lbBarcode.setFont(WindowFonts.timeNewRoman11);
          txtDiscount.setFont(WindowFonts.timeNewRomanBold11);

          // new (hello world) 18-01-2024
          proId.setVisible(false);
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void btnPlus() {
                    sumTotal("+");
               }

               @Override
               public void btnMinus() {
                    sumTotal("-");
               }
          };
          buttonAddProduct.initEvent(event);

          boxDiscount.setVisible(false);
          getImageBtnDelete();
          
          
     }

     void getImageBtnDelete() {
          Response response = JavaConnection.get(JavaRoute.bgImage + "Delete.png");
          try {
               byte[] btnImage = response.body().bytes();
               btnDelete.setIcon(new ImageIcon(btnImage));
          } catch (Exception e) {
               System.err.println("err = " + e);
          }
     }

     void sumTotal(String sign) {

          int getQty = getQty();
          if (sign == "+") {
                // add qty 
                if(getProductId() == productId){
                    if(JavaConstant.productQTyLeft > 0){
                         getQty++;
                    }else{
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage("Products are out of stock!");
                        j.setVisible(true);
                        return;
                    }
                }
          } else if (sign == "-") {
               // remove qty 
               getQty--;
          }
          

          if (getQty != 0) {
               String priceUsd = getLabelPrice();
               priceUsd = priceUsd.replace("$", "");
               priceUsd = priceUsd.replace(",", "");
               setQty(getQty);
               double subAmountUsd = Double.valueOf(priceUsd) * getQty;
               setLabelAmountUsd(dm.format(subAmountUsd));
               double _amountKh = JavaRoundDown.roundDown(JavaRoundDown.exchangeKh(subAmountUsd));
               setLabelAmountKh(kh.format(_amountKh));
               double _discoutnAmt = JavaConstant.getReplace(discountAmt) * qty;
               txtDiscount.setText("Discount : " + dm.format(_discoutnAmt));
               setDiscountAmount(dm.format(_discoutnAmt));
          }

          // ============ for subtotal panel
          int count = buttonAddProduct.getParent().getParent().getComponentCount();
          Component[] list = buttonAddProduct.getParent().getParent().getComponents();
          
          Component[] listCom = detailItem.getComponents();
          System.err.println("data detailItem length = " + listCom.length);
          double sumSubTotalUsd = 0;
          double sumDiscount = 0;

          for (int i = 0; i < listCom.length; i++) {
               var data = (BoxItem) listCom[i];
               String priceStr = data.getLabelPrice();
               priceStr = priceStr.replace("$", "");
               priceStr = priceStr.replace(",", "");
               double price = Double.valueOf(priceStr);
               int qty = data.getQty();
               double amount = price * qty;
               sumSubTotalUsd += amount;

               String discount = data.getDiscountAmount();
               discount = discount.replace("$", "");
               discount = discount.replace(",", "");
               double discountValue = Double.valueOf(discount);
               sumDiscount += Double.valueOf(discountValue);

          }
          subtotalPanel.total(0, listCom, 0, subtotalPanel);

//          subtotalPanel.setLabelSubtotalUsd(dm.format(sumSubTotalUsd));
//          double _subTotalKh = JavaRoundDown.roundDown("" + sumSubTotalUsd * JavaConstant.exchangeRate);
//          subtotalPanel.setLabelSubtotalKhr(kh.format(_subTotalKh));
//
//          subtotalPanel.setLableDiscountUsd(dm.format(sumDiscount));
//          double _subDiscountKh = JavaRoundDown.roundDown("" + sumDiscount * JavaConstant.exchangeRate);
//          subtotalPanel.setLableDiscountKhr(kh.format(_subDiscountKh));
//
//          // total
//          double total = sumSubTotalUsd - sumDiscount;
//          subtotalPanel.setLableTotalUsd(dm.format(total));
//          double _total = JavaRoundDown.roundDown("" + total * JavaConstant.exchangeRate);
//          subtotalPanel.setLableTotalKhr(kh.format(_total));
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

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        lbWeight = new javax.swing.JLabel();
        lbSale = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbBarcode = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JLabel();
        amountUsd = new javax.swing.JLabel();
        amountkh = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        buttonAddProduct = new Button.ButtonAddProduct();
        jLabel1 = new javax.swing.JLabel();
        proId = new javax.swing.JLabel();
        boxDiscount = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        title.setText("Pizza");

        lbWeight.setText("Weight");

        lbSale.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbSale.setForeground(new java.awt.Color(47, 155, 70));
        lbSale.setText("Sale Price :");

        lbPrice.setText("Price");

        lbBarcode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbBarcode.setText("Barcode :");

        txtDiscount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDiscount.setForeground(new java.awt.Color(204, 0, 0));
        txtDiscount.setText("Discount :");

        amountUsd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        amountUsd.setForeground(new java.awt.Color(16, 107, 67));
        amountUsd.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        amountUsd.setText("$ 1.40");

        amountkh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        amountkh.setForeground(new java.awt.Color(16, 107, 67));
        amountkh.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        amountkh.setText("0");

        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        buttonAddProduct.setQuantity(1);

        jLabel1.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(16, 107, 67));
        jLabel1.setText("៛");

        proId.setText("jLabel2");

        boxDiscount.setText("boxDiscunt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(proId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(117, 117, 117)
                        .addComponent(txtDiscount))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbSale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxDiscount)
                            .addComponent(buttonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(amountkh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(amountUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3)))))
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(proId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boxDiscount)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(amountkh)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSale)
                                    .addComponent(lbPrice)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(buttonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(amountUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBarcode)
                            .addComponent(txtDiscount))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked

         Component[] listDelete = btnDelete.getParent().getParent().getComponents();
         var b = (BoxItem) btnDelete.getParent();
         DeleteDialog delete = new DeleteDialog(new JFrame(), true);
         delete.setDetailItem(detailItem);
         delete.setListCom(listDelete);
         delete.setProductId(b.getProductId());
         delete.setSubtotalPanel(subtotalPanel);
         delete.setBtnPayment(btnPayment);
         delete.setBtnCancel(btnCancel);
         delete.setButtonHoldOrder(buttonHoldOrder);
         delete.setVisible(true);

    }//GEN-LAST:event_btnDeleteMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        JavaConstant.productId = productId;
        
        if(JavaConstant.productId == productId){
            this.setBorder( BorderFactory.createLineBorder(Color.RED) );
        }
        
        Component[] listCom1 = detailItem.getComponents();
        for (int i = 0; i < listCom1.length; i++) {
            var obj = ((BoxItem) listCom1[i]);
            if(obj.getProductId() != JavaConstant.productId){
            obj.setBorder(null);
            }
        }
                    
        this.setBorder( BorderFactory.createLineBorder(Color.RED) );
        JavaConstant.discountAmount = Double.valueOf(discountAmount.replace("$", ""));
        
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amountUsd;
    private javax.swing.JLabel amountkh;
    private javax.swing.JLabel boxDiscount;
    private javax.swing.JLabel btnDelete;
    private Button.ButtonAddProduct buttonAddProduct;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbSale;
    private javax.swing.JLabel lbWeight;
    private javax.swing.JLabel proId;
    private javax.swing.JLabel title;
    private javax.swing.JLabel txtDiscount;
    // End of variables declaration//GEN-END:variables
}
