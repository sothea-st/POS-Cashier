/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Components.LabelFontGreen;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Cursor;
import javax.swing.JFrame;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class JavaEventNextPrevious {

     public static void eventNext(LabelFontGreen next, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    
                    
                    int count = jdFormLogin.getCount();

                    count = count - JavaConstant.limit;
                    if (count <= 0) {
                         return;
                    }

                    JavaConstant.limit = JavaConstant.limit + 20;
                    JavaConstant.page++;

                    int _limit = 0;
                    if (count > 20) {
                         _limit = 20;
                    } else {
                         _limit = count;
                    }

                    if (JavaConstant.brandId == 0) {
                         try {
                              Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + _limit + "&page=" + JavaConstant.page);
                           
                              if (response.isSuccessful()) {
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class
                                   );
                                   ProductDataModel[] listData = data.getData();
                                   jdFormLogin.assignProduct(listData);
                                
                              } else {
                                   System.err.println("fail loading product");
                              }
                         } catch (Exception e) {
                              System.err.println("error getting product " + e);
                         }
                    } else {
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, _limit);
                    }
               }
          };
          next.initEvent(event);
     }

     public static void eventPrevious(LabelFontGreen previous, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    JavaConstant.page--;
                    if (JavaConstant.page <= 0) {
                         JavaConstant.page = 1;
                         return;
                    }
                    JavaConstant.limit = JavaConstant.limit - 20;
                    if (JavaConstant.brandId == 0) {
                         if (limit != 0) {
                              Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=20&page=" + JavaConstant.page);
                             
                              try {
                                   if (response.isSuccessful()) {
                                        String responseData = response.body().string();
                                        ObjectMapper objMap = new ObjectMapper();
                                        ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class
                                        );
                                        ProductDataModel[] listData = data.getData();
                                        jdFormLogin.assignProduct(listData);
                                     
                                   } else {
                                        System.err.println("fail loading product");
                                   }
                              } catch (Exception e) {
                                   System.err.println("error getting product " + e);
                              }
                         }
                    } else {
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, JavaConstant.limit);
                    }

               }
          };
          previous.initEvent(event);
     }
}
