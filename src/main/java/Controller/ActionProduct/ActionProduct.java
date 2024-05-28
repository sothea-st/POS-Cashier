package Controller.ActionProduct;

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
import HoldOrder.HoldModelDir.DataListHold;
import HoldOrder.HoldModelDir.ListDetailHold;
import HoldOrder.HoldModelDir.ResultHoldSuccess;
import Model.PackageProduct.ProductModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import Products.ProductBox;
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
import org.json.JSONArray;
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
     private Button btnReturn;
     private Button buttonHoldOrder;
     private JPanel panelPagination;
     private int count;
     private ButtonCancel btnCancel;
     public static int marginRight = 15;
     private JLabel titleOrder;

     public ActionProduct() {
     }

     public void product(int catId, int limit, JPanel panelProduct) {
          try {
               Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + catId + "&limit=" + limit + "&page=" + JavaConstant.page);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();

                    if (listData.length == 0) {
                         JavaConstant.setResultNotFound(panelProduct, panelPagination);
                         return;
                    }

                    setCount(data.getCount());
                    assignProduct(listData, panelProduct);
               } else {
                    System.err.println("fail loading product 333");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void newProduct(int catId, int limit, JPanel panelProduct) {
          try {
               Response response = JavaConnection.get(JavaRoute.getNewPrdduct);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();
                    
                    if (listData.length == 0) {
                         JavaConstant.setResultNotFound(panelProduct, panelPagination);
                         return;
                    }

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
               Response response = JavaConnection.get(JavaRoute.getAllProduct + "&perPage=200&page=1");

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();
                    
                    if (listData.length == 0) {
                         JavaConstant.setResultNotFound(panelProduct, panelPagination);
                         return;
                    }

                    setCount(data.getCount());
                    assignProduct(listData, panelProduct);
               } else {
                    System.err.println("fail loading product ");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void getPromotion(int catId, int limit, JPanel panelProduct) {
          try {
               Response response = JavaConnection.get(JavaRoute.getPromotion);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();

                    if (listData.length == 0) {
                         JavaConstant.setResultNotFound(panelProduct, panelPagination);
                         return;
                    }

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
                    obj.getQty(),
                    obj.getDiscountType()
               );
               listProduct.add(product);
          }
          appendProduct(listProduct, panelProduct);
     }
     DataListHold[] listHoldData;
     ListDetailHold[] listHoldDetails;

     void getHold() {
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

     void appendProduct(ArrayList<ProductModel> listProduct, JPanel panelProduct) {

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 1}; // 1 align item to top
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 1}; // 1 align item to left 

          panelProduct.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

//          ========== get hole qty =================
          getHold();

          for (int i = 0; i < listProduct.size(); i++) {

               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
//               gbc.weightx=1;
               gbc.anchor = gbc.NORTH;

               gbc.insets = new Insets(0, 0, 5, 4);
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

                         //Show message When no item or unavailable item
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
                                   if (qty > 0) {
                                        if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
                                             j.setMessage(JavaAlertMessage.returnMsg);
                                             j.setVisible(true);
                                             return;
                                        }
                                        eventBtnBuy(listData, 1, product);
                                        qty--;
                                        product.setQty("" + qty);
                                        if (qty == 0) {
                                             product.setProductStatus(JavaMessage.outStock);
                                        }

                                   } else {
                                        product.setProductStatus(JavaMessage.outStock);
                                        j.setMessage(JavaMessage.productOutStock);
                                        j.setVisible(true);
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

               //    =============================== for update qty ===========================
               Component[] listDetailItem = detailItem.getComponents();

               int qtyForShow = listData.getQty();

               if (JavaConstant.returnByBarcode == null) { // if cashier use function return this not working
                    if (listDetailItem.length > 0) {
                         for (Component c : listDetailItem) {
                              var objData = ((BoxItem) c);
                              if (listData.getBarcode().equals(objData.getLabelBarcode())) {
                                   int saleQty = objData.getQty();
                                   qtyForShow = qtyForShow - saleQty;
                                   break;
                              }
                         }
                    }

                    //   =============== update qty with hole ==================
                    if (listHoldData.length > 0) {
                         for (DataListHold c : listHoldData) {
                              ListDetailHold[] l = c.getListDetails();
                              for (ListDetailHold dd : l) {
                                   if (dd.getBarcode().equals(listData.getBarcode())) {
                                        qtyForShow = qtyForShow - dd.getQty();
                                   }
                              }
                         }
                    }
               }

               product.setQty("" + qtyForShow);

               product.initEvent(event);

               product.setOrgQty(listData.getQty());

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

               if (qtyForShow == 0) {
                    product.setProductStatus("Out Stock");
               }

//==========================================================================
               product.setDiscountPercentag(listData.getDiscount(), price);

               product.setDiscountPercent(listData.getDiscount());

//               String productName;
//               if (listData.getProductNameEn().length() > 22) {
//                    productName = listData.getProductNameEn().substring(0, 21) + "...";
//               } else {
//                    productName = listData.getProductNameEn();
//               }
               product.setProductName("<html>" + listData.getProductNameEn() + "</html>");

//               ====================== get weight ====================
               String _weight = "";
               // Your JSON string
               String jsonString = listData.getWeight();

               // Convert the string to a JSONArray
               JSONArray jsonArray = new JSONArray(jsonString);

               // Iterate over each JSONObject in the JSONArray
               for (int m = 0; m < jsonArray.length(); m++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(m);

                    // Get values from each JSONObject
                    String name = jsonObject.getString("name");
                    String title = jsonObject.getString("title");

                    // Extract options JSONArray
                    JSONArray optionsArray = jsonObject.getJSONArray("options");

                    // Get the first option
                    JSONObject optionsObject = optionsArray.getJSONObject(0);
                    String option = optionsObject.getString("option");

                    _weight = option;
               }

               product.setWeight(_weight);
               if (listData.getDiscount() > 0) {
                    double discountPrice = price - (listData.getDiscount() * price) / 100;
                    double dis4Length = JavaConstant.get4Length("" + discountPrice);
                    product.setPrice(dm.format(discountPrice));
               } else {
                    double _price = JavaConstant.get4Length("" + price);
                    product.setPrice(dm.format(price));
               }

               product.setBarcode(listData.getBarcode());
               // read image from api 

               try {

                    if (listData.getProImageName() != null) {
//                         product.setProductImage("http://localhost:8090/api/public/addImageForBackground/" + listData.getProImageName());
                         product.setProductImage(JavaConstant.urlImage + listData.getProImageName());
                    }

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }

               try {
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
          BoxItem box = new BoxItem();
          if (JavaConstant.returnByBarcode != null) { // this for protect return item by barcode and limited with qty
               box.setMaxQty(listData.getQty());
          }

          if (JavaConstant.isReturn != null) { // this for protect return item by barcode and limited with qty
               box.setMaxQty(listData.getQty());
          }
          
 
          box.setDiscountCase(listData.getDiscountType());
          
          double price = listData.getPrice();

          double discount = (listData.getDiscount() * price) / 100;
          discount = JavaConstant.get4Length("" + discount); // get 2 precision

          box.setProductBox(product);
          box.setPanelProduct(panelProduct);

          box.setWasPrice("" + price);
          box.setBtnPayment(btnPayment);
          box.setButtonHoldOrder(buttonHoldOrder);
          box.setBtnCancel(btnCancel);
          box.setBtnReturn(btnReturn);
          box.setTitleOrder(titleOrder);
          box.setLbQty(listData.getQty());
          Component[] listCom = detailItem.getComponents();

          if (listCom.length != 0) {

               for (int i = 0; i < listCom.length; i++) {
                    var obj = ((BoxItem) listCom[i]);
                    int proId = obj.getProductId();
                    int qty = obj.getQty();
                    if (proId == listData.getId()) {

                         double _discountUnit = JavaConstant.getReplace(obj.getDiscountAmount());
                         qty++;

                         if (JavaConstant.returnByBarcode != null) { // this for protect return item by barcode and limited with qty
                              if (qty > listData.getQty()) {
                                   return;
                              }
                         }

                         if (JavaConstant.isReturn != null) { // this for protect return item by barcode and limited with qty
                              if (qty > listData.getQty()) {
                                   return;
                              }
                         }

                         obj.setQty(qty);
                         double newAmountUsd = qty * price;
                         if (listData.getDiscount() > 0) {
                              newAmountUsd = price * qty;
                         }
                         obj.setLabelAmountUsd(dm.format(newAmountUsd));
                         double valueRoundDown1 = JavaRoundDown.roundDown("" + newAmountUsd * JavaConstant.exchangeRate);
                         obj.setLabelAmountKh(kh.format(valueRoundDown1));
                         box.setSubtotalPanel(subtotalPanel);

                         if (_discountUnit > 0) {
                              double _disUniteItem = ((qty * price) / 100) * obj.getDiscountDigit();
                              obj.setDiscountAmount(dm.format(_disUniteItem));
                         } else {
                              obj.setDiscountAmount(dm.format(qty * discount));
                         }

                         if (obj.getDiscountType() != null) {
                              if (obj.getDiscountType().equals("dollar")) {
                                   obj.setDiscountAmount(dm.format(qty * obj.getDiscountValue()));
                              }
                         }

                         box.setListCom(listCom);
                         box.setDetailItem(detailItem);
                         subtotalPanel.total(0, listCom, 0, subtotalPanel);
                         return;
                    }
               }
          }

          box.setDiscountDigit(listData.getDiscount());
          box.setLabelProductName(listData.getProductNameEn());

//        ====================== get weight ====================
          String _weight = "";
          // Your JSON string
          String jsonString = listData.getWeight();

          // Convert the string to a JSONArray
          JSONArray jsonArray = new JSONArray(jsonString);

          // Iterate over each JSONObject in the JSONArray
          for (int m = 0; m < jsonArray.length(); m++) {
               JSONObject jsonObject = jsonArray.getJSONObject(m);

               // Get values from each JSONObject
               String name = jsonObject.getString("name");
               String title = jsonObject.getString("title");

               // Extract options JSONArray
               JSONArray optionsArray = jsonObject.getJSONArray("options");

               // Get the first option
               JSONObject optionsObject = optionsArray.getJSONObject(0);
               String option = optionsObject.getString("option");

               _weight = option;
          }

          box.setLabelWeight(_weight);

          box.setLabelBarcode(listData.getBarcode());
          box.setOldDiscount(listData.getDiscount());

          if (JavaConstant.tmpInvoice == null) {
               //          ==== cut qty in panel product =====
               Component[] listPanel = panelProduct.getComponents();
               for (Component c : listPanel) {
                    var _cPro = ((ProductBox) c);
                    if (_cPro.getBarcode().equals(listData.getBarcode())) {
                         int _qtyData = Integer.parseInt("" + _cPro.getQty());
                         _qtyData--;
                         _cPro.setQty(_qtyData + "");
                         break;
                    }
               }
          }

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

          if (listData.getDiscount() > 0) {

               if (JavaConstant.isReturn == null) {
                    box.setDiscountValue(listData.getDiscount());
               } else {
                    box.setDiscountValue(listData.getDiscount() * listData.getQty());
               }

               if (listData.getDiscountType() != null) {
                    if (listData.getDiscountType().equals("dollar")) {
                         box.setDiscountAmount(dm.format(listData.getQty() * listData.getDiscount()));
                         box.setDiscountAmt(dm.format(listData.getQty() * listData.getDiscount()));
                    }
               }
          }

          try {
               box.setIconImage(JavaConstant.urlImage + listData.getProImageName());
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

          if (listData.getDiscountType() != null) {
               if (listData.getDiscountType().equals("dollar")) {
                    subtotalPanel.total(price * qtyData, listCom, listData.getQty() * listData.getDiscount(), subtotalPanel);
               }
          }

          // add list has one box to BoxItem (note: must be add)
          Component[] listCom1 = detailItem.getComponents();
          box.setDetailItem(detailItem);
          box.setSubtotalPanel(subtotalPanel);
          box.setListCom(listCom1);

          if (JavaConstant.tmpInvoice == null) {
               btnPayment.setBackground(WindowColor.lightBlue);
               buttonHoldOrder.setBackground(WindowColor.yellow);
               btnCancel.setBackground(WindowColor.darkred);
               btnReturn.setBackground(WindowColor.lightGray);
               titleOrder.setVisible(true);
               titleOrder.setText("CURRENT ORDER");
          }

          btnPayment.setBackground(WindowColor.lightBlue);
          buttonHoldOrder.setBackground(WindowColor.yellow);
          btnCancel.setBackground(WindowColor.darkred);
          btnReturn.setBackground(WindowColor.lightGray);
          titleOrder.setVisible(true);
          titleOrder.setText("CURRENT ORDER");

          if (JavaConstant.tmpInvoice != null) {
               titleOrder.setVisible(true);
               titleOrder.setText("SALE RETURN");
          }

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

     public Button getBtnReturn() {
          return btnReturn;
     }

     public void setBtnReturn(Button btnReturn) {
          this.btnReturn = btnReturn;
     }

     // public void setBtnReturn(Button btnReturn) {
     //      this.btnReturn = btnReturn;
     // }
     public JLabel getTitleOrder() {
          return titleOrder;
     }

     public void setTitleOrder(JLabel titleOrder) {
          this.titleOrder = titleOrder;
     }

}
