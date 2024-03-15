package Controller.ActionProduct;

import BlogCode.ActionUpdateQty;
import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.PackageProduct.ProductModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import Products.ProductBox;
import UpdateQty.UpdateQtyModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import okhttp3.Response;
import org.json.JSONObject;

public class ActionProduct {

     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");

     private Button btnLogin;
     private JLabel boxUserName;
     private JPanel category;
     private JPanel panelProduct;
     private JScrollPane jScrollPaneCategory;
     private JPanel detailItem;
     private JPanel boxOne;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;
     private Button buttonHoldOrder;
     private JPanel panelPagination;
     private int count;
     private ButtonCancel btnCancel;
     public static int marginRight = 15;

     public ActionProduct() {
     }

     public void product(int catId, int limit, JPanel panelProduct) {
          try {
               Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + catId + "&limit=" + limit + "");
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();
                    setCount(data.getCount());
                    assignProduct(listData, panelProduct);
               } else {
                    System.err.println("fail loading product 333");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void getAllProduct(JPanel panelProduct) {
          try {
               Response response = JavaConnection.get(JavaRoute.getAllProduct);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();
                    setCount(data.getCount());
                    assignProduct(listData, panelProduct);
               } else {
                    System.err.println("fail loading product 333");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void assignProduct(ProductDataModel[] listData, JPanel panelProduct) {
          ArrayList<ProductModel> listProduct = new ArrayList<>();

          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               ProductModel product = new ProductModel(
                    obj.getID(),
                    obj.getCatID(),
                    obj.getFlag(),
                    obj.getWeight(),
                    obj.getCost(),
                    obj.getProImageName(),
                    obj.getPrice(),
                    obj.getBarcode(),
                    obj.getProNameKh(),
                    obj.getProNameEn(),
                    obj.getProductStatus(),
                    obj.getDiscount(),
                    obj.getQty()
               );
               listProduct.add(product);

          }
          appendProduct(listProduct, panelProduct);
     }

     void appendProduct(ArrayList<ProductModel> listProduct, JPanel panelProduct) {

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 1}; // 1 align item to top
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 1}; // 1 align item to left 

          panelProduct.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (int i = 0; i < listProduct.size(); i++) {

               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
//               gbc.weightx=1;
               gbc.anchor = gbc.NORTH;

               gbc.insets = new Insets(5, 0, 5, marginRight);
               x++;
               if (x == JavaConstant.rowNum) {
                    x = 0;
                    y++;
               }
               var listData = listProduct.get(i);

               double price = listData.getPrice();
               double discount = (listData.getDiscount() * price) / 100;
               ProductBox product = new ProductBox();

               // event button buy
               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onMouseClick() {

//                         //Show message When no item or unavailable item
                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);

//                         //=================================
//                         int qty = Integer.valueOf(product.getQty());
//
//                         if (qty == 1) {
//                              product.setProductStatus("Out Stock");
//                         }
//
//                         if (!listData.getProductStatus().isEmpty() && qty == 0) {
//                              j.setMessage(JavaMessage.productOutStock);
//                              j.setVisible(true);
//                              return;
//                         }
//                         qty--;
//                         product.setQty("" + qty);
//                         JavaConstant.productQTyLeft = qty;


                         //===================================
                         int qty = Integer.valueOf(product.getQty());
                         if (!listData.getProductStatus().isEmpty()) {
                              if (JavaConstant.checkOpenShift) {
                                   if(qty > 0){
                                       ActionUpdateQty.updateQty(listData.getId(), "remove", product);
                                       eventBtnBuy(listData, 1, product);
                                   }else{
                                        j.setMessage(JavaMessage.productOutStock);
                                        j.setVisible(true);
                                        return;
                                   }
                              } else {
                                   j.setMessage(JavaConstant.openShiftFirst);
                                   j.setVisible(true);
                              }
                         } else {
                              j.setMessage("Products are not avalible for sale!");
                              j.setVisible(true);
                         }
                    }
               };

               product.setQty("" + listData.getQty());
               product.initEvent(event);

//================================Product Status============================
               if (listData.getQty() > 0) {
                    product.setProductStatus(listData.getProductStatus());
               } else {
                    if (listData.getProductStatus().isEmpty()) {
                         product.setProductStatus("Unavailable");
                    } else {
                         product.setProductStatus("Out Stock");
                    }
               }

//==========================================================================
               product.setDiscountPercentag(listData.getDiscount(), price);

               product.setDiscountPercent(listData.getDiscount());

               String productName;
               if (listData.getProductNameEn().length() > 22) {
                    productName = listData.getProductNameEn().substring(0, 21) + "...";
               } else {
                    productName = listData.getProductNameEn();
               }

               product.setProductName("<html>" + productName + "</html>");
               product.setWeight(listData.getWeight());

               if (listData.getDiscount() > 0) {
                    double discountPrice = price - (listData.getDiscount() * price) / 100;
                    double dis4Length = JavaConstant.get4Length("" + discountPrice);
                    product.setPrice(dm.format(dis4Length));
               } else {
                    double _price = JavaConstant.get4Length("" + price);
                    product.setPrice(dm.format(_price));
               }

               product.setBarcode(listData.getBarcode());
               // read image from api 
               try {
                    Response responseProductImg = JavaConnection.get(JavaRoute.readImage + listData.getProImageName());
                    byte[] imagePro = responseProductImg.body().bytes();
                    product.setProductImage(new ImageIcon(imagePro));

                    Response img = JavaConnection.get(JavaRoute.readImage + listData.getFlag());
                    byte[] imgs = img.body().bytes();
                    product.setFlagImage(new ImageIcon(imgs));

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }

               panelProduct.add(product, gbc);

          }
     }

     public void eventBtnBuy(ProductModel listData, int qtyData, ProductBox product) {
          double price = listData.getPrice();
          double discount = (listData.getDiscount() * price) / 100;
          discount = JavaConstant.get4Length("" + discount); // get 2 precision

          BoxItem box = new BoxItem();
          box.setProductBox(product);
          box.setWasPrice("" + price);
          box.setBtnPayment(btnPayment);
          box.setButtonHoldOrder(buttonHoldOrder);
          box.setBtnCancel(btnCancel);
          box.setLbQty(listData.getQty());
          Component[] listCom = detailItem.getComponents();
          if (listCom.length != 0) {
               for (int i = 0; i < listCom.length; i++) {
                    var obj = ((BoxItem) listCom[i]);
                    int proId = obj.getProductId();
                    int qty = obj.getQty();
                    if (proId == listData.getId()) {
                         qty++;
                         obj.setQty(qty);
                         double newAmountUsd = qty * price;
                         if (listData.getDiscount() > 0) {
                              newAmountUsd = price * qty;
                         }
                         obj.setLabelAmountUsd(dm.format(newAmountUsd));
                         double valueRoundDown1 = JavaRoundDown.roundDown("" + newAmountUsd * JavaConstant.exchangeRate);
                         obj.setLabelAmountKh(kh.format(valueRoundDown1));
                         box.setSubtotalPanel(subtotalPanel);
                         obj.setDiscountAmount(dm.format(qty * discount));
                         box.setListCom(listCom);
                         box.setDetailItem(detailItem);
                         subtotalPanel.total(0, listCom, 0, subtotalPanel);
                         return;
                    }
               }
          }

          box.setDiscountDigit(listData.getDiscount());
          box.setLabelProductName(listData.getProductNameEn());
          box.setLabelWeight(listData.getWeight());
          box.setLabelBarcode(listData.getBarcode());

          if (qtyData > 1) {
               box.setLabelPrice(dm.format(price));
               box.setLabelAmountUsd(dm.format(price * qtyData));

               double valueRoundDown = JavaRoundDown.roundDown("" + price * qtyData * JavaConstant.exchangeRate);
               box.setLabelAmountKh(kh.format(valueRoundDown));

               box.setDiscountAmount(dm.format(discount * qtyData));
               box.setDiscountAmt(dm.format(discount));
               box.setQty(qtyData);

          } else {
               box.setLabelPrice(dm.format(price));
               box.setLabelAmountUsd(dm.format(price));

               double valueRoundDown = JavaRoundDown.roundDown("" + price * JavaConstant.exchangeRate);
               box.setLabelAmountKh(kh.format(valueRoundDown));

               box.setDiscountAmount(dm.format(discount));
               box.setDiscountAmt(dm.format(discount));
               box.setQty(1);

          }

          try {
               Response responseProductImage = JavaConnection.get(JavaRoute.readImage + listData.getProImageName());
               byte[] images = responseProductImage.body().bytes();
               box.setIconImage(new ImageIcon(images));
          } catch (Exception e) {
          }
          box.setProductId(listData.getId());

          detailItem.add(box);
          // detailItem.add(Box.createRigidArea(new Dimension(2, 2)));
          detailItem.revalidate();
          detailItem.repaint();
          detailItem.setBorder(new BevelBorder(BevelBorder.RAISED));
          detailItem.setLayout(new BoxLayout(detailItem, BoxLayout.PAGE_AXIS));
          detailItem.setBackground(WindowColor.white);

          if (qtyData > 1) {
               subtotalPanel.total(price * qtyData, listCom, discount * qtyData, subtotalPanel);
          } else {
               subtotalPanel.total(price, listCom, discount, subtotalPanel);
          }

          // add list has one box to BoxItem (note: must be add)
          Component[] listCom1 = detailItem.getComponents();
          box.setDetailItem(detailItem);
          box.setSubtotalPanel(subtotalPanel);
          box.setListCom(listCom1);

          btnPayment.setBackground(WindowColor.lightBlue);
          buttonHoldOrder.setBackground(WindowColor.yellow);
          btnCancel.setBackground(WindowColor.darkred);

     }

     public int getCount() {
          return count;
     }

     public void setCount(int count) {
          this.count = count;
     }

     public Button getBtnLogin() {
          return btnLogin;
     }

     public void setBtnLogin(Button btnLogin) {
          this.btnLogin = btnLogin;
     }

     public JPanel getCategory() {
          return category;
     }

     public void setCategory(JPanel category) {
          this.category = category;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JScrollPane getjScrollPaneCategory() {
          return jScrollPaneCategory;
     }

     public void setjScrollPaneCategory(JScrollPane jScrollPaneCategory) {
          this.jScrollPaneCategory = jScrollPaneCategory;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public JPanel getBoxOne() {
          return boxOne;
     }

     public void setBoxOne(JPanel boxOne) {
          this.boxOne = boxOne;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     public JLabel getBoxUserName() {
          return boxUserName;
     }

     public void setBoxUserName(JLabel boxUserName) {
          this.boxUserName = boxUserName;
     }

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

}
