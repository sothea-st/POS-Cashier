package View.MainPage;

import Color.WindowColor;
import Components.BackgroundImage;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import Controller.ActionScanBarcodeAddProduct.ActionScanBarcodeAddProduct;
import Controller.ActionSearchProductController.ActionSearchProduct;
import Customer.Customer;
import Customer.CustomerFunction;
import Customer.JdailogCustomer;
import DefaultPrice.DataModelDefaultPrice;
import DeleteAndCancel.CancelDialog;
import Discount.DiscountType;
import Discount.OverallDiscount;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import HoldOrder.HoldeModel;
import HoldOrder.ListHoldOrder;
import LoginAndLogoutForm.LoginFormJdailog;
import LoginAndLogoutForm.LogoutDialog;
import Model.CashierReport.DataSuccessModelReport;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import NewDiscounts.Discounting;
import OpenAndCloseShift.CloseShift;
import OpenAndCloseShift.OpenShiftJdailog;
import Payment.PaymentOption;
import Receipt.CashierReport;
import Return.ApprovalCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONObject;

public class MainPage extends javax.swing.JFrame {

     private Color bgColor = new Color(204, 204, 204);
     private Color activeColor = new Color(56, 56, 56);
     private JPanel detailProduct;
     private String valueSearch;
     private int limit = 10;

     LoginFormJdailog jdFormLogin = new LoginFormJdailog(new JFrame(), true);

     private Timer timer;

     public MainPage() {
          initComponents();
          event();
          setBackground();
          currenDateTime();
          jScrollPaneDetail.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPaneCategory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
          BackgroundImage bgimg = new BackgroundImage();
          panelProduct.removeAll();
          panelProduct.add(bgimg);
          panelProduct.revalidate();
          panelProduct.repaint();
          jScrollPaneCategory.setVisible(false);
          panelPagination.setVisible(false);
          searchBox.requestFocusInWindow();
          eventSearchProduct();
          eventInputOrScanBarcode();
          nextEvent();
          previousEvent();
          getLogo();
          getUserIcon();
          existFun();
          setIconImage(new ImageIcon(bg).getImage());
          setTitle("King Mart");
//          setExtendedState(JFrame.MAXIMIZED_BOTH);
          currentDate.setVisible(false);
          resizeEvent();
          getShoppingImage();
//           
          searchBox.disabledTextField(false);
          textField.disabledTextField(false);

     }

     public static boolean isFullScreen = false;

     public void resizeEvent() {
          // delay 250 for loading 
          timer = new Timer(250, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {

                    Dimension size = getSize();
                    int width = size.width;

                    if (width > 1900) {
                         if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
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
                         setSize(1491, 907);
                         ActionProduct.marginRight = 5;
                         resizeWithData(5);
                    } else {
                         ActionProduct.marginRight = 15;
//                         setSize(1499, 907);
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
          addComponentListener(new ComponentAdapter() {
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
          JavaConstant.rowNum = num;
          panelProduct.removeAll();
          if (jdFormLogin.getCatId() == 0) {
               a.getAllProduct(panelProduct);
          } else {
               a.product(jdFormLogin.getCatId(), jdFormLogin.getLimit(), panelProduct);
          }
          panelProduct.revalidate();
          panelProduct.repaint();
     }

     void existFun() {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent evt) {
                    int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                         "Exit?", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
               }
          });
     }
     byte[] bg = null;

     void getLogo() {
          Response response = JavaConnection.getWithoutToken(JavaRoute.bgImage + "King Mart Small Logo.png");
          if (response.isSuccessful()) {
               try {
                    bg = response.body().bytes();
                    lbLogo.setIcon(new ImageIcon(bg));
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     void getUserIcon() {
          Response response = JavaConnection.getWithoutToken(JavaRoute.bgImage + "UserIcon.png");
          if (response.isSuccessful()) {
               try {
                    byte[] bg = response.body().bytes();
                    imgUser.setIcon(new ImageIcon(bg));
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     void getShoppingImage() {
          Response response = JavaConnection.getWithoutToken(JavaRoute.bgImage + "shopping-cart.png");
          if (response.isSuccessful()) {
               try {
                    byte[] bg = response.body().bytes();
                    imageShopping.setIcon(new ImageIcon(bg));
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     private void nextEvent() {

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {

                    int count = jdFormLogin.getCount();
                    if (limit < count) {
                         limit += 10;
                    }
                    if (jdFormLogin.getBrandId() == 0) {
                         try {
                              Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + limit + "");
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
                         jdFormLogin.getProductByBrandID("" + jdFormLogin.getBrandId(), limit);
                    }
               }
          };
          next.initEvent(event);
     }

     private void previousEvent() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    if (limit != 10) {
                         limit -= 10;
                    }

                    if (jdFormLogin.getBrandId() == 0) {
                         if (limit != 0) {
                              try {
                                   Response response = JavaConnection.get(JavaRoute.getProductByCatId + "?catId=" + jdFormLogin.getCatId() + "&limit=" + limit + "");
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
                         jdFormLogin.getProductByBrandID("" + jdFormLogin.getBrandId(), limit);
                    }

               }
          };
          previous.initEvent(event);
     }

     private void eventInputOrScanBarcode() {
          // this event was called when user type on textField 
          ButtonEvent eventData = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    String barcode = textField.getValueTextField();

                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    if (JavaConstant.token != null) {
                         if (barcode.length() == 13) {
                              if (JavaConstant.checkOpenShift) {
                                   new ActionScanBarcodeAddProduct().scanBarcode(barcode, jdFormLogin);
                                   textField.setValueTextField("");
                              } else {
                                   j.setMessage(JavaConstant.openShiftFirst);
                                   j.setVisible(true);
                              }
                         }
                    } else {
                         j.setMessage(MessageAlert.Message.OverallMessage);
                         j.setVisible(true);
                    }
               }
          };
          textField.initEvent(eventData);
     }

     private void eventSearchProduct() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    valueSearch = searchBox.getValueTextSearch();

                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    if (JavaConstant.token != null) {

                         if (valueSearch.isEmpty()) {
                              panelProduct.removeAll();
                              panelProduct.revalidate();
                              panelProduct.repaint();
                              return;
                         }

                         if (JavaConstant.checkOpenShift) {
                              ActionSearchProduct.searchProduct(valueSearch, jdFormLogin, panelProduct);
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
                         j.setMessage(MessageAlert.Message.OverallMessage);
                         j.setVisible(true);
                    }
               }
          };
          searchBox.initEvent(event);
     }

     private void setBackground() {
          mainPanel.setBackground(WindowColor.slightGreen);
          panelCategory.setBackground(WindowColor.darkGreen);
          category.setBackground(WindowColor.darkGreen);
          menuBar.setBackground(WindowColor.darkGreen);
          jScrollPaneCategory.setBackground(WindowColor.darkGreen);
          day.setBackground(WindowColor.slightGreen);
          panelprocessing.setBackground(WindowColor.slightGreen);
          panelProduct.setBackground(WindowColor.slightGreen);
          panelPagination.setBackground(WindowColor.slightGreen);
          boxOne.setBackground(WindowColor.slightGreen);
          detailItem.setBackground(WindowColor.slightGreen);
          panelCart.setBackground(WindowColor.darkGreen);

     }

     private void currenDateTime() {
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy hh:mm:ss a");
          LocalDateTime date = LocalDateTime.now();
          currentDate.setText(dtf.format(date));
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JPanel();
        panelCategory = new javax.swing.JPanel();
        jScrollPaneCategory = new javax.swing.JScrollPane();
        category = new javax.swing.JPanel();
        textField = new Components.TextField();
        panelCart = new javax.swing.JPanel();
        countCircleShape = new Components.countCircleShape();
        imageShopping = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        imgUser = new javax.swing.JLabel();
        day = new javax.swing.JPanel();
        panelprocessing = new javax.swing.JPanel();
        totalPanel = new Components.SubtotalPanel();
        btnReturn = new Button.Button();
        buttonHoldOrder = new Button.Button();
        btnPayment = new Button.Button();
        btnLogin = new Button.Button();
        btnOpenShift = new Button.Button();
        btnReprint = new Button.Button();
        buttonDiscount = new Button.Button();
        buttonCashier = new Button.Button();
        buttonCustomer = new Button.Button();
        btnCancel = new ButtonPackage.ButtonCancel();
        searchBox = new Components.SearchField();
        currentDate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelProduct = new javax.swing.JPanel();
        lbPOSId = new javax.swing.JLabel();
        jScrollPaneDetail = new javax.swing.JScrollPane();
        boxOne = new javax.swing.JPanel();
        detailItem = new javax.swing.JPanel();
        boxUserName = new javax.swing.JLabel();
        panelPagination = new javax.swing.JPanel();
        cmboxBrand = new Components.ComboBox();
        next = new Components.LabelFontGreen();
        previous = new Components.LabelFontGreen();
        homeMenu = new Components.LabelTitle();
        breadcrumb = new Components.LabelTitle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPaneCategory.setBorder(null);

        javax.swing.GroupLayout categoryLayout = new javax.swing.GroupLayout(category);
        category.setLayout(categoryLayout);
        categoryLayout.setHorizontalGroup(
            categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        categoryLayout.setVerticalGroup(
            categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPaneCategory.setViewportView(category);

        javax.swing.GroupLayout panelCategoryLayout = new javax.swing.GroupLayout(panelCategory);
        panelCategory.setLayout(panelCategoryLayout);
        panelCategoryLayout.setHorizontalGroup(
            panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1074, Short.MAX_VALUE)
            .addGroup(panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCategoryLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelCategoryLayout.setVerticalGroup(
            panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
        );

        textField.setLabelTextField("Scan or input barcode");

        panelCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCartMouseClicked(evt);
            }
        });
        panelCart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelCart.add(countCircleShape, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));
        panelCart.add(imageShopping, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, -1, -1));

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarLayout.createSequentialGroup()
                .addComponent(panelCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(panelCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarLayout.createSequentialGroup()
                .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        imgUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout dayLayout = new javax.swing.GroupLayout(day);
        day.setLayout(dayLayout);
        dayLayout.setHorizontalGroup(
            dayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        dayLayout.setVerticalGroup(
            dayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        btnReturn.setBackground(new java.awt.Color(204, 204, 204));
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setButtonName("Return");
        btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnMouseClicked(evt);
            }
        });

        buttonHoldOrder.setBackground(new java.awt.Color(204, 204, 204));
        buttonHoldOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonHoldOrder.setButtonName("Hold Order");
        buttonHoldOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHoldOrderMouseClicked(evt);
            }
        });

        btnPayment.setBackground(new java.awt.Color(204, 204, 204));
        btnPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnPayment.setButtonName("Payment");
        btnPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPaymentMouseClicked(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(47, 155, 70));
        btnLogin.setButtonName("Login");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
        });

        btnOpenShift.setBackground(new java.awt.Color(204, 204, 204));
        btnOpenShift.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenShift.setButtonName("Open Shift");
        btnOpenShift.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenShiftMouseClicked(evt);
            }
        });

        btnReprint.setBackground(new java.awt.Color(204, 204, 204));
        btnReprint.setForeground(new java.awt.Color(255, 255, 255));
        btnReprint.setButtonName("Reprint");
        btnReprint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReprintMouseClicked(evt);
            }
        });

        buttonDiscount.setBackground(new java.awt.Color(204, 204, 204));
        buttonDiscount.setForeground(new java.awt.Color(255, 255, 255));
        buttonDiscount.setButtonName("Discount");
        buttonDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDiscountMouseClicked(evt);
            }
        });

        buttonCashier.setBackground(new java.awt.Color(204, 204, 204));
        buttonCashier.setForeground(new java.awt.Color(255, 255, 255));
        buttonCashier.setButtonName("Cashier Report");
        buttonCashier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCashierMouseClicked(evt);
            }
        });

        buttonCustomer.setBackground(new java.awt.Color(204, 204, 204));
        buttonCustomer.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomer.setButtonName("Customer ");
        buttonCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCustomerMouseClicked(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 204, 204));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelprocessingLayout = new javax.swing.GroupLayout(panelprocessing);
        panelprocessing.setLayout(panelprocessingLayout);
        panelprocessingLayout.setHorizontalGroup(
            panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelprocessingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addGroup(panelprocessingLayout.createSequentialGroup()
                        .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelprocessingLayout.createSequentialGroup()
                                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnOpenShift, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                            .addComponent(buttonCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelprocessingLayout.createSequentialGroup()
                                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReprint, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(buttonHoldOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(buttonDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(buttonCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelprocessingLayout.setVerticalGroup(
            panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelprocessingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHoldOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCashier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchBox.setPlaceholder("Search by name or barcode");

        currentDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        currentDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        currentDate.setText("Tuesday, 02 January 2024 03:50:00 PM");

        jScrollPane2.setBorder(null);

        panelProduct.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(panelProduct);

        lbPOSId.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbPOSId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbPOSId.setText("POS ID :");

        jScrollPaneDetail.setBorder(null);

        javax.swing.GroupLayout detailItemLayout = new javax.swing.GroupLayout(detailItem);
        detailItem.setLayout(detailItemLayout);
        detailItemLayout.setHorizontalGroup(
            detailItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        detailItemLayout.setVerticalGroup(
            detailItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        boxOne.add(detailItem);

        jScrollPaneDetail.setViewportView(boxOne);

        boxUserName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        next.setLabelName(">");

        previous.setLabelName("<");

        homeMenu.setBackground(new java.awt.Color(47, 155, 70));
        homeMenu.setLabelTitle("Home");

        breadcrumb.setBackground(new java.awt.Color(47, 155, 70));
        breadcrumb.setLabelTitle("NEW ITEMS");

        javax.swing.GroupLayout panelPaginationLayout = new javax.swing.GroupLayout(panelPagination);
        panelPagination.setLayout(panelPaginationLayout);
        panelPaginationLayout.setHorizontalGroup(
            panelPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPaginationLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(homeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(breadcrumb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPaginationLayout.createSequentialGroup()
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmboxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelPaginationLayout.setVerticalGroup(
            panelPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPaginationLayout.createSequentialGroup()
                .addComponent(cmboxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(panelPaginationLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(breadcrumb, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(homeMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lbPOSId, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(currentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(panelPagination, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelprocessing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(menuBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(imgUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbPOSId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(currentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(boxUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addComponent(menuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPaneDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelprocessing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(panelPagination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     private void loginFunc() {
          jdFormLogin.setBoxUserName(boxUserName);
          jdFormLogin.setBtnLogin(btnLogin);
          jdFormLogin.setCategory(category);
          jdFormLogin.setPanelProduct(panelProduct);
          jdFormLogin.setjScrollPaneCategory(jScrollPaneCategory);
          jdFormLogin.setPanelPagination(panelPagination);
          jdFormLogin.setDetailItem(detailItem);
          jdFormLogin.setBoxOne(boxOne);
          jdFormLogin.setSubtotalPanel(totalPanel);
          jdFormLogin.setBtnPayment(btnPayment);
          jdFormLogin.setCmboxBrand(cmboxBrand);
          jdFormLogin.setBtnOpenShift(btnOpenShift);
          jdFormLogin.setLbPOSId(lbPOSId);
          jdFormLogin.setLimit(limit);
          jdFormLogin.setSearchBox(searchBox);
          jdFormLogin.setTextField(textField);
          jdFormLogin.setBtnPayment(btnPayment);
          jdFormLogin.setButtonHoldOrder(buttonHoldOrder);
          jdFormLogin.setBtnCancel(btnCancel);
          jdFormLogin.setBtnReprint(btnReprint);
          jdFormLogin.setButtonCustomer(buttonCustomer);
          jdFormLogin.setButtonDiscount(buttonDiscount);
          jdFormLogin.setBtnreturn(btnReturn);
          jdFormLogin.setBreadcrumb(breadcrumb);
     }

     //Action Button Login and Logout
    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
         String buttonName = btnLogin.getButtonName().toLowerCase();
         if (buttonName.equals("login")) {
              loginFunc();
              jdFormLogin.setVisible(true);
         } else if (buttonName.equals("logout")) {
              LogoutDialog logout = new LogoutDialog(new JFrame(), true);
              logout.setBoxUserName(boxUserName);
              logout.setBtnLogin(btnLogin);
              logout.setCategory(category);
              logout.setPanelProduct(panelProduct);
              logout.setjScrollPaneCategory(jScrollPaneCategory);
              logout.setPanelPagination(panelPagination);
              logout.setDetailItem(detailItem);
              logout.setBoxOne(boxOne);
              logout.setBtnPayment(btnPayment);
              logout.setLbPOSId(lbPOSId);
              logout.setTextField(textField);
              logout.setSearchBox(searchBox);
              logout.setSubtotalPanel(totalPanel);
              logout.setButtonCashier(buttonCashier);
              logout.setBtnOpenShift(btnOpenShift);
              logout.setVisible(true);
         }
    }//GEN-LAST:event_btnLoginMouseClicked

     //Action Button Open And Close Shift
    private void btnOpenShiftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenShiftMouseClicked
         String buttonName = btnOpenShift.getButtonName().toLowerCase();
         if (JavaConstant.token != null) {
              if (buttonName.equals("open shift")) {
                   OpenShiftJdailog jdOpenShift = new OpenShiftJdailog(new JFrame(), true, btnOpenShift);
                   try {
                        Response response = JavaConnection.get(JavaRoute.getDefaultPrice);
                        if (response.isSuccessful()) {
                             String myObject = response.body().string();
                             ObjectMapper objMap = new ObjectMapper();
                             DataModelDefaultPrice d = objMap.readValue(myObject, DataModelDefaultPrice.class);
                             jdOpenShift.setDetailItem(detailItem);
                             jdOpenShift.setSubtotalPanel(totalPanel);
                             jdOpenShift.setCategory(category);
                             jdOpenShift.setPanelProduct(panelProduct);
                             jdOpenShift.setSearchBox(searchBox);
                             jdOpenShift.setTextField(textField);
                             jdOpenShift.setBtnPayment(btnPayment);
                             jdOpenShift.setLimit(limit);
                             jdOpenShift.setPanelPagination(panelPagination);
                             jdOpenShift.setBtnreturn(btnReturn);
                             jdOpenShift.setBtnReprint(btnReprint);
                             jdOpenShift.setButtonDiscount(buttonDiscount);
                             jdOpenShift.setButtonCustomer(buttonCustomer);
                             jdOpenShift.setButtonHoldOrder(buttonHoldOrder);
                             jdOpenShift.setBtnCancel(btnCancel);
                             jdOpenShift.setDataSuccess(d);
                        }
                   } catch (Exception e) {
                        System.err.println("error getting default price " + e);
                   }

                   jdOpenShift.setJdLoginForm(jdFormLogin);
                   jdOpenShift.setVisible(true);

              } else if (buttonName.equals("close shift")) {

                   Component[] listCom1 = detailItem.getComponents();
                   JavaAlertMessage j = new JavaAlertMessage(this, true);
                   if (listCom1.length != 0) {
                        j.setMessage("You have to remove the produt that has been bought or do the payment first!");
                        j.setVisible(true);
                        return;
                   }

                   if (!JavaConstant.listHoldOrder.isEmpty()) {
                        j.setMessage("There are any trancsactions not yet completed in Hold function!");
                        j.setVisible(true);
                        return;
                   }

                   CloseShift close = new CloseShift(new JFrame(), true, btnOpenShift);
                   close.setPanelProduct(panelProduct);
                   close.setPanelPagination(panelPagination);
                   close.setSearchBox(searchBox);
                   close.setTextField(textField);
                   close.setCategory(category);
                   close.setButtonCustomer(buttonCustomer);
                   close.setButtonDiscount(buttonDiscount);
                   close.setBtnReprint(btnReprint);
                   close.setBtnreturn(btnReturn);
                   close.setButtonCashier(buttonCashier);
                   close.setVisible(true);
              }
         }
    }//GEN-LAST:event_btnOpenShiftMouseClicked

     //Action Button Reprint
    private void btnReprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReprintMouseClicked
         if (JavaConstant.token != null) {
              //========================= reprint with supervisor===========
              if (JavaConstant.checkOpenShift) {
                   ApprovalCode approval = new ApprovalCode(new JFrame(), true);
                   approval.setJdFormLogin(jdFormLogin);
                   approval.setTypeForm("reprint");
                   approval.setVisible(true);
              }
              //========================= reprint without supervisor===========
//              ReprintJdailog rep = new ReprintJdailog(new JFrame(), true);
//              rep.setTitle("Reprint Invoice");
//              rep.setTextButtonLeft("Reprint by Last");
//              rep.setTextButtonRight("Reprint by Invoice №");
//              rep.setTypeForm("reprint");
//              rep.setVisible(true);
         }
    }//GEN-LAST:event_btnReprintMouseClicked

     //Action Button payment
    private void btnPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseClicked

         if (JavaConstant.token != null) {
              Component[] listCom = detailItem.getComponents();
              if (listCom.length != 0) {
                   PaymentOption pay = new PaymentOption(new JFrame(), true);
                   pay.setTotalUsd(totalPanel.getLableTotalUsd());
                   pay.setListCom(listCom);
                   pay.setSubtotalPanel(totalPanel);
                   pay.setDetailItem(detailItem);
                   pay.setBoxOne(boxOne);
                   pay.setBtnPayment(btnPayment);
                   pay.setBtnCancel(btnCancel);
                   pay.setButtonHoldOrder(buttonHoldOrder);
                   pay.setVisible(true);
              }
         } else {
              System.err.println("System cannot open payment option");
         }
    }//GEN-LAST:event_btnPaymentMouseClicked

     //Action Button Return
    private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseClicked
         if (JavaConstant.token != null) {
              if (JavaConstant.checkOpenShift) {
                   ApprovalCode approval = new ApprovalCode(new JFrame(), true);
                   approval.setJdFormLogin(jdFormLogin);
                   approval.setTypeForm("return");
                   approval.setVisible(true);
              }
         }
    }//GEN-LAST:event_btnReturnMouseClicked

     //Action Button Cancel
    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked

         if (JavaConstant.token != null) {
              Component[] listCom = detailItem.getComponents();
              if (listCom.length != 0) {
                   CancelDialog cancel = new CancelDialog(new JFrame(), true);
                   cancel.setDetailItem(detailItem);
                   cancel.setTotalPanel(totalPanel);
                   cancel.setBtnPayment(btnPayment);
                   cancel.setBtnCancel(btnCancel);
                   cancel.setCode("cancel");
                   cancel.setButtonHoldOrder(buttonHoldOrder);
                   cancel.setListCom(listCom);
                   cancel.setLabelForTitle("Cancel");
                   cancel.setVisible(true);
              }
         }
    }//GEN-LAST:event_btnCancelMouseClicked

     //Action Button Cashier Report
    private void buttonCashierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCashierMouseClicked
         reportCashier();
    }//GEN-LAST:event_buttonCashierMouseClicked

     //Action Button Report Cashier
     public void reportCashier() {
          if (JavaConstant.token != null) {

               try {
                    CashierReport cashier = new CashierReport(new JFrame(), true);
                    Response response = JavaConnection.get(
                         JavaRoute.cashierReport + JavaConstant.userCode + "&userId=" + JavaConstant.cashierId + "&posId=" + JavaConstant.posId);
                    if (response.isSuccessful()) {
                         String myObject = response.body().string();
                         ObjectMapper objMap = new ObjectMapper();
                         DataSuccessModelReport d = objMap.readValue(myObject, DataSuccessModelReport.class
                         );
                         cashier.setDataSuccessReport(d);
                         cashier.setVisible(true);
                    }
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     public int countHold() {
          int countH = 0;
          Response responseGet = JavaConnection.get(JavaRoute.holdOrder);
          try {
               String dataJson = responseGet.body().string();
               JSONObject jSONObject = new JSONObject(dataJson);
               countH = jSONObject.getInt("count");
          } catch (Exception e) {
          }

          return countH;
     }

     //Action Button Holder
     private void buttonHoldOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHoldOrderMouseClicked

          if (JavaConstant.token != null) {
               Component[] listCom1 = detailItem.getComponents();
               if (listCom1.length != 0) {
                    btnPayment.setBackground(WindowColor.lightGray);
                    buttonHoldOrder.setBackground(WindowColor.lightGray);
                    btnCancel.setBackground(WindowColor.lightGray);

                    Component[] listHold = detailItem.getComponents();
                    ArrayList<HoldeModel> holdModel = new ArrayList<>();

                    int qty = 0;
                    for (int i = 0; i < listHold.length; i++) {
                         var box = ((BoxItem) listHold[i]);
                         qty += box.getQty();
                         HoldeModel h = new HoldeModel(box.getProductId(), box.getQty());
                         holdModel.add(h);
                    }

                    JSONObject json = new JSONObject();
                    json.put("note", "");
                    json.put("qtyHole", qty);
                    json.put("createBy", JavaConstant.cashierId);
                    json.put("listHoldDetail", holdModel);

//                    clicked++;
//                    NewHoldOrderModel hh = new NewHoldOrderModel(clicked, qty, listHold);
//                    JavaConstant.listHoldOrder.add(hh);
//
//                    int countRow = JavaConstant.listHoldOrder.size();
//                    countCircleShape.setCountTimes("" + countRow);
                    try {
                         Response response = JavaConnection.post(JavaRoute.holdOrder, json);

                         if (response.isSuccessful()) {

//                              Response responseGet = JavaConnection.get(JavaRoute.holdOrder);
//                              String dataJson = responseGet.body().string();
//                              JSONObject jSONObject = new JSONObject(dataJson);
//                              int count = jSONObject.getInt("count");
                              countCircleShape.setCountTimes("" + countHold());
                              clear();

                         } else {
                              UIManager UI = new UIManager();
                              UI.put("OptionPane.background", WindowColor.mediumGreen);
                              UI.put("Panel.background", WindowColor.mediumGreen);
                              UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
                              JOptionPane.showMessageDialog(null, "Caannot Add Hold Order!");

                         }

                    } catch (Exception e) {

                    }
               }
          }
     }//GEN-LAST:event_buttonHoldOrderMouseClicked

     void clear() {
          detailItem.removeAll();
          detailItem.revalidate();
          detailItem.repaint();
          totalPanel.setLabelSubTitleToZero();
     }
     private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered

     }//GEN-LAST:event_btnLoginMouseEntered

     //Action Discount
     private void buttonDiscountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDiscountMouseClicked

//        if (JavaConstant.token != null) {
//             if (JavaConstant.checkOpenShift) {
//                  DiscountType dis = new DiscountType(new JFrame(), true);
//                  dis.setSubtotalPanel(totalPanel);
//                  dis.setDetailItem(detailItem);
//                  dis.setPanelProduct(panelProduct);
//                  dis.setJdFormLogin(jdFormLogin);
//                  dis.setCategory(category);
//                  dis.setPanelPagination(panelPagination);
//                  dis.setVisible(true);
//             }
//        }

        if (JavaConstant.token != null) {
            if (JavaConstant.checkOpenShift) {       
                Component[] listCom1 = detailItem.getComponents();
                double sumDiscount = 0;
                if (listCom1.length != 0) {
                    
                    for (int i = 0; i < listCom1.length; i++) {
                        var obj = ((BoxItem) listCom1[i]);
                        sumDiscount += JavaConstant.getReplace(obj.getDiscountAmount());
                    }
                    
                    if(sumDiscount <= 0){
                        Discounting dis = new Discounting(new JFrame(), true);
                        dis.setTotalPanel(totalPanel);
                        dis.setDetailItem(detailItem);
                        dis.setVisible(true);
                    }else if(JavaConstant.discountAmount <= 0){
                        Discounting dis = new Discounting(new JFrame(), true);
                        dis.setTotalPanel(totalPanel);
                        dis.setDetailItem(detailItem);
                        dis.setVisible(true);
                    } else {
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage("Cannot process this function!");
                        j.setVisible(true);
                        return;
                    }
                }
            }
        }
     }//GEN-LAST:event_buttonDiscountMouseClicked

     //Action Button Customer
    private void buttonCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseClicked
         if (JavaConstant.token != null) {
              if (JavaConstant.checkOpenShift) {
//                   Customer cust = new Customer(new JFrame(), true);
//                   cust.setVisible(true);

                    JdailogCustomer cus = new JdailogCustomer(new JFrame(),true);
                    cus.setVisible(true);
              }
         }
    }//GEN-LAST:event_buttonCustomerMouseClicked

     //Action show hold order
    private void panelCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCartMouseClicked
         if (JavaConstant.token != null) {
             if (JavaConstant.checkOpenShift) {
                ListHoldOrder hold = new ListHoldOrder(new JFrame(), true);
                hold.setDetailItem(detailItem);
                hold.setSubtotalPanel(totalPanel);
                hold.setBtnPayment(btnPayment);
                hold.setButtonHoldOrder(buttonHoldOrder);
                hold.setCountCircleShape(countCircleShape);
                hold.setBtnCancel(btnCancel);
                hold.setVisible(true);
             }
         }
    }//GEN-LAST:event_panelCartMouseClicked

     //Function call Placeholder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          searchBox.initEvent(btnevent);
          textField.initEvent(btnevent);
     }

     public JPanel getDetailProduct() {
          return detailProduct;
     }

     public void setDetailProduct(JPanel detailProduct) {
          this.detailProduct = detailProduct;
     }

     public Color getActiveColor() {
          return activeColor;
     }

     public void setActiveColor(Color activeColor) {
          this.activeColor = activeColor;
     }

     /**
      * @param args the command line
      * arguments
      */
     public static void main(String args[]) {
          /* Set the Nimbus look and feel */
          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
          /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
           */
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;

                    }
               }
          } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(MainPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(MainPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(MainPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(MainPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the form */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    MainPage obj = new MainPage();
                    obj.setVisible(true);
               }
          });
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxOne;
    private javax.swing.JLabel boxUserName;
    private Components.LabelTitle breadcrumb;
    private ButtonPackage.ButtonCancel btnCancel;
    private Button.Button btnLogin;
    private Button.Button btnOpenShift;
    private Button.Button btnPayment;
    private Button.Button btnReprint;
    private Button.Button btnReturn;
    private Button.Button buttonCashier;
    private Button.Button buttonCustomer;
    private Button.Button buttonDiscount;
    private Button.Button buttonHoldOrder;
    private javax.swing.JPanel category;
    private Components.ComboBox cmboxBrand;
    private Components.countCircleShape countCircleShape;
    private javax.swing.JLabel currentDate;
    private javax.swing.JPanel day;
    private javax.swing.JPanel detailItem;
    private Components.LabelTitle homeMenu;
    private javax.swing.JLabel imageShopping;
    private javax.swing.JLabel imgUser;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneCategory;
    private javax.swing.JScrollPane jScrollPaneDetail;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbPOSId;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuBar;
    private Components.LabelFontGreen next;
    private javax.swing.JPanel panelCart;
    private javax.swing.JPanel panelCategory;
    private javax.swing.JPanel panelPagination;
    private javax.swing.JPanel panelProduct;
    private javax.swing.JPanel panelprocessing;
    private Components.LabelFontGreen previous;
    private Components.SearchField searchBox;
    private Components.TextField textField;
    private Components.SubtotalPanel totalPanel;
    // End of variables declaration//GEN-END:variables
}
