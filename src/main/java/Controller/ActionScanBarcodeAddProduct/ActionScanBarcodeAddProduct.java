package Controller.ActionScanBarcodeAddProduct;

import Button.Button;
import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import HoldOrder.HoldModelDir.DataListHold;
import HoldOrder.HoldModelDir.ListDetailHold;
import HoldOrder.HoldModelDir.ResultHoldSuccess;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.PackageProduct.ProductModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import Model.ReturnModel.ModelReturnData;
import Model.ReturnModel.ResultDataReturnModel;
import Products.ProductBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import okhttp3.Response;

public class ActionScanBarcodeAddProduct extends ActionProduct {

     private Button btnLogin;
     private JLabel boxUserName;
     private JPanel category;
     private JPanel panelProduct;
     private JScrollPane jScrollPaneCategory;
     private JPanel detailItem;
     private JPanel boxOne;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;
     private JPanel panelPagination;
     private Button btnReturn;

     public Button getBtnReturn() {
          return btnReturn;
     }

     public void setBtnReturn(Button btnReturn) {
          this.btnReturn = btnReturn;
     }

     public void scanBarcode(String barcode, LoginFormJdailog jdFormLogin) {
          if (barcode.length() == 13) {
               Response response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=barcode&valueSearch=" + barcode);
               func(response, jdFormLogin);
          }
     }

     public void scanWithoutBarcode(String barcode, LoginFormJdailog jdFormLogin) {
          Response response = JavaConnection.get(JavaRoute.searchWithInvoice + "?invoiceNo=" + barcode);
          func(response, jdFormLogin);
     }

     public void scanWithoutReturn(String barcode, LoginFormJdailog jdFormLogin) {
          Response response = JavaConnection.get(JavaRoute.searchWithInvoice + "?invoiceNo=" + barcode);

          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ModelReturnData model = objMap.readValue(responseData, ModelReturnData.class);
                    ProductDataModel[] listProduct = model.getData();

                    ModelReturnData.receive_usd = model.getReceiveUsd();
                    ModelReturnData.receive_khr = model.getReceiveKhr();
                    ModelReturnData.change_usd = model.getChangeUsd();
                    ModelReturnData.change_khr = model.getChangeKhr();

                    if (listProduct.length == 0) {
                         JavaConstant.isReturn = null;
                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                         j.setMessage("The invoie already returned !");
                         j.setVisible(true);
                         return;
                    }

                    ProductModel product = null;
                    for (int i = 0; i < listProduct.length; i++) {
                         var obj = listProduct[i];

                         product = new ProductModel(
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
                         jdFormLogin.scanbarCodeAddProduct(product);
                    }
                    btnPayment.setButtonName("Return");
                    JavaConstant.isReturn = "return";
                    btnReturn.setBackground(WindowColor.lightGray);
               }
          } catch (Exception e) {

          }
     }

     void func(Response response, LoginFormJdailog jdFormLogin) {
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData model = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();
                    if (listProduct.length == 0) {
                         msgAlertErr();
                         return;
                    }
                    int orgQty = listProduct[0].getQty();
//                    setQtyJPanel(panelProduct, detailItem, listProduct[0].getBarcode(), orgQty);
                 
                    ProductModel product = null;
                    for (int i = 0; i < listProduct.length; i++) {
                         var obj = listProduct[i];
                         product = new ProductModel(
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
                         jdFormLogin.scanbarCodeAddProduct(product, "scan");
                    }

               }
          } catch (Exception e) {
               msgAlertErr();
          }
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

     public void setQtyJPanel(JPanel pnaleJPanel, JPanel detailItem, String barcode, int orgQty) {

          getHold();
          Component[] listCome1 = panelProduct.getComponents();
          Component[] listDetailItem = detailItem.getComponents();
          int qtySale = 1;
          for (Component c : listDetailItem) {
               var data = ((BoxItem) c);
               if (barcode.equals(data.getLabelBarcode())) {
                    qtySale = 0;
                    qtySale = Integer.parseInt(data.getQty() + "");
                    qtySale++;
                    break;
               }
          }

          for (Component c : listCome1) {
               var data = ((ProductBox) c);
               if (barcode.equals(data.getBarcode())) {

                    if (data.getQty().equals("0")) {
                         return;
                    }

                    orgQty = orgQty - qtySale;
                    if (orgQty < 0) {
                         orgQty = 0;
                    }
                    data.setQty("" + orgQty);


                    if (data.getQty().equals("0")) {
                         data.setProductStatus(JavaMessage.outStock);
                    }

               }
          }

     }

     void msgAlertErr() {
          JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
          j.setMessage("The product does not exist in system!");
          j.setVisible(true);
     }

     public static String returnProducts(String barcode, String invoiceNo, LoginFormJdailog jdFormLogin) {
          String status = null;
          if (barcode.length() == 13) {
               Response response = JavaConnection.get(JavaRoute.returnProduct + "/getProduct?barcode=" + barcode + "&invoiceNo=" + invoiceNo + "");
               try {
                    if (response.isSuccessful()) {
                         String responseData = response.body().string();
                         ObjectMapper objMap = new ObjectMapper();
                         ProductSuccessData model = objMap.readValue(responseData, ProductSuccessData.class);
                         ProductDataModel[] listProduct = model.getData();

                         ProductModel product = null;
                         for (int i = 0; i < listProduct.length; i++) {
                              var obj = listProduct[i];
                              product = new ProductModel(
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
                         }
                         jdFormLogin.scanbarCodeAddProduct(product);
                         status = "success";
                    }
               } catch (Exception e) {
                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    j.setMessage("Something wrong with invoice number or barcode!");
                    j.setVisible(true);
                    System.err.println("error scan barcode = " + e);
               }
          } else {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("Barcode must be 13 length!");
               j.setVisible(true);
          }
          return status;
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
}
