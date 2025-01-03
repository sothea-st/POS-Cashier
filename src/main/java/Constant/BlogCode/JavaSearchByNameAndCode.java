/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant.BlogCode;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Components.Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SearchField;
import Components.TextField;
import Constant.JavaConstant;
import Controller.ActionScanBarcodeAddProduct.ActionScanBarcodeAddProduct;
import Controller.ActionSearchProductController.ActionSearchProduct;
import Components.Event.ButtonEvent;
import feature.LoginAndLogoutForm.LoginFormJdailog;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JavaSearchByNameAndCode {

     private static ButtonCancel btnCancel;
     private static Button btnPayment;
     private static Button btnReturn;

     public static void searchProduct(JPanel panelProduct, SearchField searchBox, JPanel panelPagination, LoginFormJdailog jdFormLogin, JPanel category, TextField textField) {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    String valueSearch = searchBox.getValueTextSearch();

                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    if (JavaConstant.token != null) {

                         if (valueSearch.isEmpty()) {

                              panelProduct.removeAll();
                              panelProduct.revalidate();
                              panelProduct.repaint();
                              jdFormLogin.runData();
                              textField.setFocus();

                              return;
                         }

                         if (JavaConstant.checkOpenShift) {
                              ActionSearchProduct.searchProduct(valueSearch, jdFormLogin, panelProduct, panelPagination);
                              panelPagination.setVisible(false);

                              // each time search product by barcode or name category will remove bg color 
                              if (jdFormLogin.getCatName() != null) {
                                   Component[] listCom = category.getComponents();
                                   int index = Integer.parseInt(jdFormLogin.getCatName());
                                   listCom[index].setBackground(WindowColor.darkGreen);
                              }
                         } else {
                              j.setMessage(JavaConstant.openShiftFirst);
                              j.setVisible(true);
                         }
                    } else {
                         j.setMessage(Components.MessageAlert.Message.OverallMessage);
                         j.setVisible(true);
                    }
               }
          };
          searchBox.initEvent(event);
     }

     public static void scanProduct(TextField textField, LoginFormJdailog jdFormLogin, JPanel panelProduct, JPanel detailItem) {

          ButtonEvent eventData = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
//                    if (JavaConstant.isReturn != null) { // protect when cashier processing return
//                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
//                         j.setMessage(JavaAlertMessage.returnMsg);
//                         j.setVisible(true);
//                         textField.setValueTextField("");
//                         return;
//                    }

                    String barcode = textField.getValueTextField();
                   
                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    if (JavaConstant.token != null) {
                         if (barcode.length() == 13) {
 
                              if (JavaConstant.checkOpenShift) {
                                   
                                   ActionScanBarcodeAddProduct a = new ActionScanBarcodeAddProduct();
                                   a.setPanelProduct(panelProduct);
                                   a.setDetailItem(detailItem);
                                   a.setBtnCancel(btnCancel);
                                   a.setBtnPayment(btnPayment);
                                   a.setBtnReturn(btnReturn);

                                   if (JavaConstant.isReturn != null) { // for return
                                        // JavaConstant.returnByBarcode is store value invoice number
                                        a.returnWithBarcode(barcode, jdFormLogin, JavaConstant.tmpInvoice); 
                                   } else { // for sale 
                                        if (JavaConstant.returnByBarcode == null) { // for sale
                                             a.scanBarcode(barcode, jdFormLogin);
                                        } else { // for return
                                             // JavaConstant.returnByBarcode is store value invoice number
                                             a.returnWithBarcode(barcode, jdFormLogin, JavaConstant.returnByBarcode); 
                                        }
                                   }

                                   textField.setValueTextField("");
                              } else {
                                   j.setMessage(JavaConstant.openShiftFirst);
                                   j.setVisible(true);
                              }
                         }
                    } else {
                         j.setMessage(Components.MessageAlert.Message.OverallMessage);
                         j.setVisible(true);
                    }
               }
          };
          textField.initEvent(eventData);
     }

     public static ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public static void setBtnCancel(ButtonCancel btnCancel) {
          JavaSearchByNameAndCode.btnCancel = btnCancel;
     }

     public static Button getBtnPayment() {
          return btnPayment;
     }

     public static void setBtnPayment(Button btnPayment) {
          JavaSearchByNameAndCode.btnPayment = btnPayment;
     }

     public static Button getBtnReturn() {
          return btnReturn;
     }

     public static void setBtnReturn(Button btnReturn) {
          JavaSearchByNameAndCode.btnReturn = btnReturn;
     }

}
