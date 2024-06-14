package Controller.ActionSearchProductController;

import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Products.ListProduct;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;

@Setter
@Getter
public class ActionSearchProd {
    private LoginFormJdailog jdLogin;
     private JPanel panelProduct;
     private JPanel category;

     public static boolean checkOnlyDigits(String value) {
          for (int i = 0; i < value.length(); i++) {
               if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                    return false;
               }
          }
          return true;
     }

     public static void searchProduct(String searchValue, JPanel listGetProduct) {
          String code = "";
          boolean isCheck = ActionSearchProduct.checkOnlyDigits(searchValue);
          if (isCheck) {
               code = "barcode";
          } else {
               code = "productNameEn";
          }
          Response response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=" + code + "&valueSearch=" + searchValue + "");

          if (response.isSuccessful()) {
               try {
                    ListProduct listProd = new ListProduct(new JFrame(), true);
                    String responseData = response.body().string();
                    ObjectMapper obj = new ObjectMapper();
                    ProductSuccessData model = obj.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();

                    if (listProduct.length > 0) {
                         listProd.assignProduct(listProduct, listGetProduct);
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                    } else {
                         listGetProduct.removeAll();
                         NotFound nofound = new NotFound();
                         listGetProduct.add(nofound);
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                    }

               } catch (Exception e) {
                    System.out.println("err from search product = " + e);
               }
          }
     }

     public void searchProducts(String searchValue, JPanel listGetProduct) {
          String code = "";
          boolean isCheck = ActionSearchProduct.checkOnlyDigits(searchValue);
          if (isCheck) {
               code = "barcode";
          } else {
               code = "productNameEn";
          }
          Response response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=" + code + "&valueSearch=" + searchValue + "");

          if (response.isSuccessful()) {
               try {
                    ListProduct listProd = new ListProduct(new JFrame(), true);
                    listProd.setPanelProduct(panelProduct);
                    listProd.setPanelCategory(category);
                    listProd.setJdLogin(jdLogin);
                    
                    String responseData = response.body().string();
                    ObjectMapper obj = new ObjectMapper();
                    ProductSuccessData model = obj.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();

                    if (listProduct.length > 0) {
                         listProd.assignProduct(listProduct, listGetProduct);
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                    } else {
                         listGetProduct.removeAll();
                         NotFound nofound = new NotFound();
                         listGetProduct.add(nofound);
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                    }

               } catch (Exception e) {
                    System.out.println("err from search product = " + e);
               }
          }
     }

}
