/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Components.Color.WindowColor;
import Components.LabelFontGreen;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Components.Event.ButtonEvent;
import feature.LoginAndLogoutForm.LoginFormJdailog;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;

@Setter
@Getter
public class JavaEventNextPrevious {
     private Integer dataA;

     public static void eventNext(LabelFontGreen next, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame, JPanel panelProduct, LabelFontGreen previous) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    int count = jdFormLogin.getCount();
                  
                    if (JavaConstant.limit > count) {
                         next.setBackground(WindowColor.lightGray);
                         return;
                    }
                     
                    previous.setBackground(WindowColor.white);
                    next.setBackground(WindowColor.white);

                    JavaConstant.limit = JavaConstant.limit + JavaConstant.limitPagination;
                    JavaConstant.page += JavaConstant.limitPagination;
                     
                    if (JavaConstant.brandId == 0) {
                         try {

                              String _titleCate = jdFormLogin.getTitleCategory().toLowerCase();
                              Response response = null;
                              if (_titleCate.equals("new items")) {
                                   response = JavaConnection.get(JavaRoute.getNewPrdduct + "?limit=" + JavaConstant.limit + "&page=" + JavaConstant.page);
                                  
                              } else {
                                   response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + JavaConstant.limitPagination + "&page=" + JavaConstant.page);
                              }

                              if (response.isSuccessful()) {
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                                   ProductDataModel[] listData = data.getData();
                                    
                                   if (listData.length < JavaConstant.limitPagination) {
                                        next.setBackground(WindowColor.lightGray);
                                   }
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
                         jdFormLogin.setNext(next);
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, JavaConstant.limitPagination);
                    }
               }
          };
          next.initEvent(event);
     }

     public static void eventPrevious(LabelFontGreen previous, int limit, LoginFormJdailog jdFormLogin, JFrame mainFrame, LabelFontGreen next) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    if (JavaConstant.page == 0) {
                         previous.setBackground(WindowColor.lightGray);
                         return;
                    }

                    previous.setBackground(WindowColor.white);
                    next.setBackground(WindowColor.white);

                    JavaConstant.page = JavaConstant.page - JavaConstant.limitPagination;
                    JavaConstant.limit = JavaConstant.limit - JavaConstant.limitPagination;

                    if (JavaConstant.page == 0) {
                         previous.setBackground(WindowColor.lightGray);
                    }

                    if (JavaConstant.brandId == 0) {
                         if (limit != 0) {
                              Response response = null;
                              String _titleCate = jdFormLogin.getTitleCategory().toLowerCase();
                              if (_titleCate.equals("new items")) {
                                   response = JavaConnection.get(JavaRoute.getNewPrdduct + "?limit=" + JavaConstant.limit + "&page=" + JavaConstant.page);
                              } else {
                                   response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + JavaConstant.limitPagination + "&page=" + JavaConstant.page);
                              }

                              try {
                                   if (response.isSuccessful()) {
                                        String responseData = response.body().string();
                                        ObjectMapper objMap = new ObjectMapper();
                                        ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                                        ProductDataModel[] listData = data.getData();
                                        if (listData.length < JavaConstant.limitPagination) {
                                             next.setBackground(WindowColor.lightGray);
                                        }
                                        jdFormLogin.assignProduct(listData);

                                   } else {
                                        System.err.println("fail loading product");
                                   }
                              } catch (Exception e) {
                                   System.err.println("error getting product " + e);
                              }
                         }
                    } else {
                        
                         jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, JavaConstant.limitPagination);
                    }
               }
          };
          previous.initEvent(event);
     }
}
