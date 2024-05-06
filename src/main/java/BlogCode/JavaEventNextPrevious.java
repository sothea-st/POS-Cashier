/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Components.LabelFontGreen;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class JavaEventNextPrevious {
     
     public static void eventNext(LabelFontGreen next, int limit, LoginFormJdailog jdFormLogin) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    int count = jdFormLogin.getCount();
                    int _limitData = limit;
                    if (_limitData < count) {
                         _limitData += 10;
                    }
                    if (jdFormLogin.getBrandId() == 0) {
                         try {
                              Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + _limitData + "");
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
                         jdFormLogin.getProductByBrandID("" + jdFormLogin.getBrandId(), _limitData);
                    }
               }
          };
          next.initEvent(event);
     }

     public static void eventPrevious(LabelFontGreen previous, int limit, LoginFormJdailog jdFormLogin) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    int _limitData = limit;
                    if (_limitData != 10) {
                         _limitData -= 10;
                    }

                    if (jdFormLogin.getBrandId() == 0) {
                         if (limit != 0) {
                              try {
                                   Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + _limitData + "");
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
                         jdFormLogin.getProductByBrandID("" + jdFormLogin.getBrandId(), _limitData);
                    }

               }
          };
          previous.initEvent(event);
     }
}
