package Model.Staff;

import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Controller.ActionSearchProductController.ActionSearchProduct;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import Products.ListProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class ActionSearchEmployee {
     
     public static void search(String searchValue) {
        
          Response response = JavaConnection.get(JavaRoute.searchEmployee + searchValue );
          System.out.println("response data = " + response);

          if (response.isSuccessful()) {
               try {
                    ListProduct listProd = new ListProduct(new JFrame(), true);
                    String responseData = response.body().string();
                    ObjectMapper obj = new ObjectMapper();
                    ProductSuccessData model = obj.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();

//                    if (listProduct.length > 0) {
//                         listProd.assignProduct(listProduct, listGetProduct);
//                         listGetProduct.revalidate();
//                         listGetProduct.repaint();
//                    } else {
//                         listGetProduct.removeAll();
//                         NotFound nofound = new NotFound();
//                         listGetProduct.add(nofound);
//                         listGetProduct.revalidate();
//                         listGetProduct.repaint();
//                    }

               } catch (Exception e) {
                    System.out.println("err from search product = " + e);
               }
          }
     }
}
