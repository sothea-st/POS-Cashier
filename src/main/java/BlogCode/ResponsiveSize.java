/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Components.LabelFontGreen;
import Components.SubtotalPanel;
import Constant.JavaConstant;
import Controller.ActionProduct.ActionProduct;
import LoginAndLogoutForm.LoginFormJdailog;
import View.MainPage.MainPage;
import static View.MainPage.MainPage.isFullScreen;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ResponsiveSize {

     private Timer timer;

     private JPanel detailItem;
     private JPanel panelProduct;
     private SubtotalPanel totalPanel;
     private Button btnPayment;
     private ButtonCancel btnCancel;
     private Button buttonHoldOrder;
     private LoginFormJdailog jdFormLogin;
     private Button btnReturn;
     private JLabel titleOrder;
     private JPanel panelPagination;
     private LabelFontGreen previous;
     private LabelFontGreen next;

     public ResponsiveSize(
          JPanel detailItem,
          JPanel panelProduct,
          SubtotalPanel totalPanel,
          Button btnPayment,
          ButtonCancel btnCancel,
          Button buttonHoldOrder,
          LoginFormJdailog jdFormLogin,
          Button btnReturn,
          JLabel titleOrder,
          JPanel panelPagination,
          LabelFontGreen previous,
          LabelFontGreen next
     ) {

          this.detailItem = detailItem;
          this.panelProduct = panelProduct;
          this.totalPanel = totalPanel;
          this.btnCancel = btnCancel;
          this.btnPayment = btnPayment;
          this.buttonHoldOrder = buttonHoldOrder;
          this.jdFormLogin = jdFormLogin;
          this.btnReturn = btnReturn;
          this.titleOrder = titleOrder;
          this.panelPagination = panelPagination;
     }

     public void resizeEvent(MainPage mainPage) {
          // delay 250 for loading 
          timer = new Timer(100, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    Dimension size = mainPage.getSize();
                    int width = size.width;

//                    System.out.println("width : " + width);
                    if (width > 1900) {
                         if ((mainPage.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                              isFullScreen = true;
                         }
                         ActionProduct.marginRight = 15;
                         if (jdFormLogin.getCatId() != 0) {
                              resizeWithData(7);
                         } else {
                              if (JavaConstant.checkOpenShift) {
                                   resizeWithData(7);
                              }
                         }
                    } else if (width > 1680) {
                         ActionProduct.marginRight = 15;
                         resizeWithData(6);
                    } else if (width <= 1491) {
                         JOptionPane.showMessageDialog(null, "There are limited for resizing!");
                         mainPage.setSize(1540, 907);
                         ActionProduct.marginRight = 3;
                         resizeWithData(5);
                    } else {
                         ActionProduct.marginRight = 15;
                         if (jdFormLogin.getCatId() != 0) {
                              resizeWithData(5);
                         } else {
                              if (JavaConstant.checkOpenShift) {
                                   resizeWithData(5);
                              }
                         }

                         isFullScreen = false;
                    }
               }
          });
          timer.setRepeats(false); // Only fire once
          mainPage.addComponentListener(new ComponentAdapter() {
               @Override
               public void componentResized(ComponentEvent e) {
                    timer.restart();
               }
          });
     }

     public void resizeWithData(int num) {
          ActionProduct a = new ActionProduct();
          a.setDetailItem(detailItem);
          a.setSubtotalPanel(totalPanel);
          a.setBtnPayment(btnPayment);
          a.setBtnCancel(btnCancel);
          a.setButtonHoldOrder(buttonHoldOrder);
          a.setPanelProduct(panelProduct);
          a.setBtnReturn(btnReturn);
          a.setTitleOrder(titleOrder);
          a.setPrevious(previous);
          a.setNext(next);
          a.setPanelPagination(panelPagination);
          JavaConstant.rowNum = num;
          a.setPanelPagination(panelPagination);
          panelProduct.removeAll();

          if (JavaConstant.brandId != 0) {
               jdFormLogin.getProductByBrandID("" + JavaConstant.brandId, JavaConstant.limitPagination);
          } else {
               if (jdFormLogin.getCatId() == 0) {
                    a.newProduct(JavaConstant.limitPagination, panelProduct);
               } else {

                    if (JavaConstant.checkOpenShift) {
                         String catNameLowerCase = jdFormLogin.getTitleCategory().toLowerCase();

                         switch (catNameLowerCase) {
                              case "new items" -> { // new items 
                                   System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh");
                                   a.newProduct(JavaConstant.limitPagination, panelProduct);
                              }

                              case "promotion" -> {    // promotion
                                   System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
                                   a.getPromotion(jdFormLogin.getCatId(), JavaConstant.limitPagination, panelProduct);
                              }

                              default -> {
                                   System.out.println("22222222222222222222222222222 ");
                                   a.product(jdFormLogin.getCatId(), JavaConstant.limitPagination, panelProduct);
                              }

                         }
                    }
               }
          }

          panelProduct.revalidate();
          panelProduct.repaint();
     }
}
