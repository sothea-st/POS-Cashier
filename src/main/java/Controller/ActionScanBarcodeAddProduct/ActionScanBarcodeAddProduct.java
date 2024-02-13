package Controller.ActionScanBarcodeAddProduct;

import Button.Button;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.PackageProduct.ProductModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
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

     public static void scanBarcode(String barcode, LoginFormJdailog jdFormLogin) {
          if (barcode.length() == 13) {
               Response response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=barcode&valueSearch=" + barcode);
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
                    }
               } catch (Exception e) {
                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    j.setMessage("The product does not exist in system!");
                    j.setVisible(true);
                    System.err.println("error scan barcode = " + e);
               }
          }
     }

     public static String returnProduct(String barcode, String invoiceNo, LoginFormJdailog jdFormLogin) {
          String status=null;
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
                         status="success";
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
