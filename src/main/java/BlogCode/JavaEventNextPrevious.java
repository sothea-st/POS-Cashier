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
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class JavaEventNextPrevious {

     public static void eventNext(LabelFontGreen next, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame, JPanel panelProduct) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    int count = jdFormLogin.getCount();
                
                    
                    if( JavaConstant.limit > count ) {
                         return;
                    }

                    JavaConstant.limit = JavaConstant.limit + 20;
                    JavaConstant.page += 20;
                    
 

                    if (JavaConstant.brandId == 0) {
                         try {
                              Response response = null;
                              if (jdFormLogin.getCatId() == 2) {
                                   
                                   response = JavaConnection.get(JavaRoute.getNewPrdduct + "?limit="+JavaConstant.limit+"&page=" + JavaConstant.page);
                                   
                              } else {
                                   response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=20&page=" + JavaConstant.page);
                              }

                              if (response.isSuccessful()) {
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                                   ProductDataModel[] listData = data.getData();
                                   panelProduct.removeAll();
                                   panelProduct.revalidate();
                                   panelProduct.repaint();
                                   jdFormLogin.assignProduct(listData);
                              } else {
                                   System.err.println("fail loading product");
                              }
                         } catch (Exception e) {
                              System.err.println("error getting product " + e);
                         }
                    } else {
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, 20);
                    }
               }
          };
          next.initEvent(event);
     }

     public static void eventPrevious(LabelFontGreen previous, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                 
                    if( JavaConstant.page == 0 ) return;
                    
                    JavaConstant.page = JavaConstant.page - 20;
                    JavaConstant.limit = JavaConstant.limit - 20;
                    
                    if (JavaConstant.brandId == 0) {
                         if (limit != 0) {
                              Response response = null;
                              if (jdFormLogin.getCatId() == 2) {
                                   response = JavaConnection.get(JavaRoute.getNewPrdduct + "?limit=20&page=" + JavaConstant.page);
                              } else {
                                   response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=20&page=" + JavaConstant.page);
                              }

                              try {
                                   if (response.isSuccessful()) {
                                        String responseData = response.body().string();
                                        ObjectMapper objMap = new ObjectMapper();
                                        ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
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
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, 20);
                    }

               }
          };
          previous.initEvent(event);
     }
}
