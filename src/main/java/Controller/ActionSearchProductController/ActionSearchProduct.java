package Controller.ActionSearchProductController;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JLabel;
import javax.swing.JPanel;
import okhttp3.Response;

public class ActionSearchProduct {

     public static boolean checkOnlyDigits(String value) {
          for (int i = 0; i < value.length(); i++) {
               if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                    return false;
               }
          }
          return true;
     }

     public static void searchProduct(String valueSearch, LoginFormJdailog jdFormLogin, JPanel panelProduct) {
          String code = "";
          boolean isCheck = ActionSearchProduct.checkOnlyDigits(valueSearch);
          if (isCheck) {
               code = "barcode";
          } else {
               code = "productNameEn";
          }
          Response response = null;

          if (code.equals("barcode")) {
               if (valueSearch.length() == 13) {
                    response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=" + code + "&valueSearch=" + valueSearch + "");
               }
          } else {
               if (valueSearch.length() >= 3) {
                    response = JavaConnection.get(JavaRoute.searchProductByBarcodeOrName + "?code=" + code + "&valueSearch=" + valueSearch + "");
               }
          }

          if (response.isSuccessful()) {
               try {
                    String responseData = response.body().string();
                    ObjectMapper obj = new ObjectMapper();
                    ProductSuccessData model = obj.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();
                    if (listProduct.length > 0) {
                         jdFormLogin.assignProduct(listProduct);
                    } else {
                         panelProduct.removeAll();
                         panelProduct.add(new JLabel(JavaConstant.noResult));
                         panelProduct.revalidate();
                         panelProduct.repaint();
                    }

               } catch (Exception e) {
                    System.out.println("err from search product = " + e);
               }
          }
     }

}
