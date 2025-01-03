package Components;

import BlogCode.JavaActionDiscount;
import Button.ButtonAddProduct;
import Components.Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoundDown;
import Constant.JavaRoundUpKhr;
import Constant.JavaRoute;

import feature.DeleteAndCancel.DeleteDialog;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import feature.HoldOrder.HoldModelDir.DataListHold;
import feature.HoldOrder.HoldModelDir.ListDetailHold;
import feature.HoldOrder.HoldModelDir.ResultHoldSuccess;
import feature.Stock.Products.ProductBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import okhttp3.Response;

public class BoxItem extends javax.swing.JPanel {

     private String cost;

     public String getCost() {
          return cost;
     }

     public void setCost(String cost) {
          this.cost = cost;
     }

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

     private String discountDollar;

     public String getDiscountDollar() {
          return discountDollar;
     }

     public void setDiscountDollar(String discountDollar) {
          double _getCal = JavaConstant.getReplace(discountDollar);
          _getCal = qty * _getCal;
          txtDiscount.setText("Discount : " + dm.format(_getCal));
          this.discountDollar = discountDollar;
     }

     public void setDiscountDollar(String discountDollar, int _qty) {
          double _getCal = JavaConstant.getReplace(discountDollar);
          _getCal = _qty * _getCal;

          this.discountDollar = discountDollar;
          txtDiscount.setText("Discount : " + dm.format(_getCal));
     }

     public void setIconImage(Icon iconImage) {
          this.iconImage = iconImage;
          img.setIcon(iconImage);
     }

     public void setIconImage(String imageName) throws IOException {
          
          if( imageName != null ) {
                 String _url = new JavaBaseUrl().getBaseUrl() + JavaRoute.bgImage + imageName;
                 if( JavaConstant.doesUrlExist(_url) ) {
                      JavaConstant.coverImage(_url, img, 60, 100);
                 }
          }
          
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

     public double getDiscountDigit() {
          return discountDigit;
     }

     public void setDiscountDigit(double discountDigit) {
          this.discountDigit = discountDigit;
          boxDiscount.setText("" + discountDigit);
     }

     public JLabel getTxtDiscount() {
          return txtDiscount;
     }

     public void setTxtDiscount(JLabel txtDiscount) {
          this.txtDiscount = txtDiscount;
     }

     public Button.Button getBtnReturn() {
          return btnReturn;
     }

     public void setBtnReturn(Button.Button btnReturn) {
          this.btnReturn = btnReturn;
     }

     public ButtonAddProduct getButtonAddProduct() {
          return buttonAddProduct;
     }

     public void setButtonAddProduct(ButtonAddProduct buttonAddProduct) {
          this.buttonAddProduct = buttonAddProduct;
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

     public ProductBox getProductBox() {
          return productBox;
     }

     public void setProductBox(ProductBox productBox) {
          this.productBox = productBox;
     }

     public int getLbQty() {
          return lbQty;
     }

     public void setLbQty(int lbQty) {
          this.lbQty = lbQty;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public int getMaxQty() {
          return maxQty;
     }

     public void setMaxQty(int maxQty) {
          this.maxQty = maxQty;
     }

     public double getOldDiscount() {
          return oldDiscount;
     }

     public void setOldDiscount(double oldDiscount) {
          this.oldDiscount = oldDiscount;
     }

     public JLabel getTitleOrder() {
          return titleOrder;
     }

     public void setTitleOrder(JLabel titleOrder) {
          this.titleOrder = titleOrder;
     }

     public JPanel getBoxOne() {
          return boxOne;
     }

     public void setBoxOne(JPanel boxOne) {
          this.boxOne = boxOne;
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
     private double discountDigit;
     private String wasPrice;
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private String discountAmt;
     private Button.Button btnPayment;
     private ButtonPackage.ButtonCancel btnCancel;
     private Button.Button buttonHoldOrder;
     private String discountType;
     private double discountValue;
     private ProductBox productBox;
     private int lbQty;
     private JPanel panelProduct;
     private int maxQty;
     private double oldDiscount;
     private Button.Button btnReturn;
     private JLabel titleOrder;
     private JPanel boxOne;

     private String discountCase;

     public String getDiscountCase() {
          return discountCase;
     }

     public void setDiscountCase(String discountCase) {
          this.discountCase = discountCase;
     }

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

          boxDiscount.setVisible(false);
          getImageBtnDelete();
          JavaConstant.setPointer(btnDelete);
          eventAddAndRemove();

          ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("image/Delete.png"));
          btnDelete.setIcon(icon);

//          Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
//          setBorder(border);
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

     public void eventAddAndRemove() {

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
     }

     DataListHold[] listHoldData;
     ListDetailHold[] listHoldDetails;

     public void getHold() {
          try {
               Response response = JavaConnection.get(JavaRoute.holdOrder + "?userId=" + JavaConstant.cashierId);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ResultHoldSuccess data = objMap.readValue(responseData, ResultHoldSuccess.class);
                    listHoldData = data.getData();
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     void sumTotal(String sign) {
          int getQty = getQty();

          if (sign == "+") {

               getHold();

               // add qty 
               getQty++;
               if (JavaConstant.tmpInvoice != null) {  // protect when cashier processing return 

                    int qtyTmp = Integer.parseInt(buttonAddProduct.getLbQty().getText());
                    qtyTmp++;
                    if (qtyTmp > getMaxQty()) {
                         return;
                    }
               }
               if (JavaConstant.tmpInvoice != null) {   /// ====== when cashier return by barcode

                    int qtyTmp = Integer.parseInt(buttonAddProduct.getLbQty().getText());
                    qtyTmp++;

                    if (qtyTmp > getMaxQty()) {
                         return;
                    }

               } else {
                    Component[] listCome1 = panelProduct.getComponents();
                    for (Component c : listCome1) {
                         var data = ((ProductBox) c);
                         if (labelBarcode.equals(data.getBarcode())) {

                              if (data.getQty().equals("0")) {
                                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                                   j.setMessage(JavaMessage.productOutStock);
                                   j.setVisible(true);
                                   return;
                              }

                              int orgQty = data.getOrgQty();
                              orgQty = orgQty - getQty;
                              if (orgQty < 0) {
                                   orgQty = 0;
                              }

                              //    =============== update qty with hole ==================
                              if (listHoldData.length > 0) {
                                   for (DataListHold cv : listHoldData) {
                                        ListDetailHold[] l = cv.getListDetails();
                                        for (ListDetailHold dd : l) {
                                             if (dd.getBarcode().equals(labelBarcode)) {
                                                  int holdQty = dd.getQty();
                                                  orgQty = orgQty - holdQty;
                                                  break;
                                             }
                                        }
                                   }
                              }

                              if (data.getQty().equals("0")) {
                                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                                   j.setMessage(JavaMessage.productOutStock);
                                   j.setVisible(true);
                                   return;
                              }

                              data.setQty("" + orgQty);
                              if (data.getQty().equals("0")) {
                                   data.setProductStatus(JavaMessage.outStock);
                              }
                         }
                    }
               }

          } else if (sign == "-") {

               getHold();
               // remove qty 
               getQty--;

               if (JavaConstant.tmpInvoice != null) { // protect when cashier processing return 
//                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
//                    j.setMessage(JavaAlertMessage.returnMsg);
//                    j.setVisible(true);
//                    return;
               }

               if (JavaConstant.tmpInvoice != null) { /// ====== when cashier return by barcode
//                    if (getQty == 1) {
//                         return;
//                    }
//                    getQty--;
//                  calculateQtyReturn("-");
               } else {

                    Component[] listCome1 = panelProduct.getComponents();
                    for (Component c : listCome1) {
                         var data = ((ProductBox) c);
                         if (labelBarcode.equals(data.getBarcode())) {
                              int orgQty = data.getOrgQty();

                              //    =============== update qty with hole ==================
                              if (listHoldData.length > 0) {
                                   for (DataListHold cv : listHoldData) {
                                        ListDetailHold[] l = cv.getListDetails();
                                        for (ListDetailHold dd : l) {
                                             if (dd.getBarcode().equals(labelBarcode)) {
                                                  int holdQty = dd.getQty();
                                                  orgQty = orgQty - holdQty;
                                                  break;
                                             }
                                        }
                                   }
                              }

                              if (getQty != 0) {
                                   orgQty = orgQty - getQty;
                                   data.setQty("" + orgQty);
                                   data.setProductStatus(JavaMessage.inStock);
                              }

                         }
                    }
               }

          }

          if (getQty != 0) {

               double priceUsd = JavaConstant.getReplace(getLabelPrice());
               setQty(getQty);
               double subAmountUsd = priceUsd * getQty;
               setLabelAmountUsd(dm.format(subAmountUsd));
               double _amountKh = JavaRoundDown.roundDown(JavaRoundDown.exchangeKh(subAmountUsd));
               setLabelAmountKh(JavaRoundUpKhr.setRoundNumber(_amountKh));

               if (getDiscountDigit() > 0) {  // for percent
                    double _disUniteItem = ((qty * priceUsd * getDiscountDigit()) / 100);
                    txtDiscount.setText("Discount : " + dm.format(Double.valueOf(_disUniteItem)));
                    setDiscountAmount(dm.format(_disUniteItem)); // subtotal discount will be count
               } else { // for dollar
                    double _discoutnAmt = JavaConstant.getReplace(discountAmt) * getQty;
                    txtDiscount.setText("Discount : " + dm.format(_discoutnAmt));
                    setDiscountAmount(dm.format(_discoutnAmt)); // subtotal discount will be count

                    if (discountType != null) {
                         if (discountType.equals("dollar")) {
                              double _discoutnAmt2 = discountValue * getQty;
                              txtDiscount.setText("Discount : " + dm.format(_discoutnAmt2));
                              setDiscountAmount(dm.format(_discoutnAmt2)); // subtotal discount will be count
                         }
                    }
               }

               if (getDiscountCase() != null) {
                    if (getDiscountCase().equals("dollar")) {
                         double _calValue = getQty * discountDigit;
                         txtDiscount.setText("Discount : " + dm.format(_calValue));
                         setDiscountAmount(dm.format(_calValue)); // subtotal discount will be count
                    }
               }

          }

          // ============ for subtotal panel
          Component[] listCom = detailItem.getComponents();
          subtotalPanel.total(0, listCom, 0, subtotalPanel);
     }

     public void calculateQtyReturn(String signData) {
          Component[] listPanelProduct = panelProduct.getComponents();
          Component[] listDetails = detailItem.getComponents();

          for (Component c : listDetails) {
               var _data = ((BoxItem) c);
               int _qty = _data.getQty();
               for (Component cc : listPanelProduct) {
                    var pp = ((ProductBox) cc);
                    int _qtyPanel = Integer.parseInt(pp.getQty());
                    if (pp.getBarcode().equals(_data.getLabelBarcode())) {
                         if (signData.equals("-")) {
                              _qtyPanel--;
                         } else {
                              _qtyPanel++;
                         }

                         pp.setQty("" + _qtyPanel);
                         break;
                    }
               }
          }

     }

     //=================================================Create Shadow Box
     private ShadowType shadowType;
     private int shadowSize = 1;
     private float shadowOpacity = 0.2f;
     private Color shadowColor = Color.black;

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
          g.fillRoundRect(0, 0, width, height, 0, 0);

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
          img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/default-product-small.png"))); // NOI18N

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
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
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
                                   .addComponent(lbWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbSale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
         DeleteDialog delete = new DeleteDialog(new JFrame(), true);
         Component[] listDelete = btnDelete.getParent().getParent().getComponents();
         var b = (BoxItem) btnDelete.getParent();
         if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
              deleteItem();
              return;
         }

         delete.setDetailItem(detailItem);
         delete.setListCom(listDelete);
         delete.setProductId(b.getProductId());
         delete.setSubtotalPanel(subtotalPanel);
         delete.setBtnPayment(btnPayment);
         delete.setBtnCancel(btnCancel);
         delete.setPanelProduct(panelProduct);
         delete.setButtonHoldOrder(buttonHoldOrder);
         delete.setBtnReturn(btnReturn);
         delete.setBarcode(b.getLabelBarcode());
         delete.setTitleOrder(titleOrder);
//         delete.setProductBox(productBox);
         delete.setQty(qty);
         delete.setVisible(true);

    }//GEN-LAST:event_btnDeleteMouseClicked

     public void deleteItem() {
          double sumSubTotalUsd = 0;
          double sumDiscount = 0;
          Component[] listC = detailItem.getComponents();

          for (int i = 0; i < listC.length; i++) {
               var d = (BoxItem) listC[i];
               if (productId == d.getProductId()) {
                    detailItem.remove(i);
                    detailItem.revalidate();
                    detailItem.repaint();
               } else {
                    var data = (BoxItem) listC[i];
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
                    double discountValue = JavaConstant.getReplace(d.getDiscountAmount());
                    sumDiscount += discountValue;
               }
          }

          subtotalPanel.setLabelSubtotalUsd(dm.format(sumSubTotalUsd));
          double _subTotalKh = JavaRoundDown.roundDown("" + sumSubTotalUsd * JavaConstant.exchangeRate);
          subtotalPanel.setLabelSubtotalKhr(kh.format(_subTotalKh));

          subtotalPanel.setLableDiscountUsd(dm.format(sumDiscount));
          double _disKh = JavaRoundDown.roundDown("" + sumDiscount * JavaConstant.exchangeRate);
          subtotalPanel.setLableDiscountKhr(kh.format(_disKh));

          // total
          double total = sumSubTotalUsd - sumDiscount;
          subtotalPanel.setLableTotalUsd(dm.format(total));
          double _total = JavaRoundDown.roundDown("" + total * JavaConstant.exchangeRate);
          subtotalPanel.setLableTotalKhr(kh.format(_total));

          //          Component[] l = detailItem.getComponents();
          if (listC.length == 1) {
               btnPayment.setBackground(WindowColor.lightGray);
               btnCancel.setBackground(WindowColor.lightGray);
               buttonHoldOrder.setBackground(WindowColor.lightGray);
               btnReturn.setBackground(WindowColor.brown);
               subtotalPanel.setLabelSubTitleToZero();
               detailItem.setBackground(WindowColor.mediumGreen);

               btnPayment.setButtonName("Payment");
               titleOrder.setText("CURRENT ORDER");
               detailItem.setBackground(WindowColor.slightGreen);
               detailItem.setBorder(null);

//               ============= success delete reset value to default =============
               JavaConstant.setBackQty(detailItem, panelProduct);
               JavaConstant.resetValueReturn();
          }
     }


    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

         if (evt.getClickCount() == 2 && !evt.isConsumed()) {
              evt.consume();
              JavaConstant.productId = productId;
              JavaConstant.discountAmount = Double.valueOf(discountAmount.replace("$", ""));

              Component[] listCom1 = detailItem.getComponents();
//              for (int i = 0; i < listCom1.length; i++) {
//                   var obj = ((BoxItem) listCom1[i]);
//                   if (obj.getProductId() != JavaConstant.productId) {
//                        obj.setBorder(null);
//                        obj.revalidate();
//                        obj.repaint();
//                   }
//              }

              if (JavaConstant.productId == productId) {

                   this.setBorder(BorderFactory.createLineBorder(Color.RED));
                   this.revalidate();
                   this.repaint();
                   JavaActionDiscount.discount(detailItem, subtotalPanel);

                   for (int i = 0; i < listCom1.length; i++) {
                        var obj = ((BoxItem) listCom1[i]);

//                        if (obj.getOldDiscount() > 0) {
                        this.setBorder(null);
                        this.revalidate();
                        this.repaint();
                        return;
//                        }
                   }
              }
         }

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
