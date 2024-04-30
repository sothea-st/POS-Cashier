package View.MainPage;

import BlogCode.ActionCloseShift;
import static BlogCode.ActionCloseShift.j;
import BlogCode.JavaActionAddHold;
import BlogCode.JavaActionDiscount;
import BlogCode.JavaBlogImage;
import BlogCode.JavaEventNextPrevious;
import BlogCode.JavaExistScreen;
import BlogCode.JavaSearchByNameAndCode;
import BlogCode.ResponsiveSize;
import Color.WindowColor;
import Components.BackgroundImage;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Customer.JdailogCustomer;
import DefaultPrice.DataModelDefaultPrice;
import DeleteAndCancel.CancelDialog;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import HoldOrder.ListHoldOrder;
import LoginAndLogoutForm.LoginFormJdailog;
import LoginAndLogoutForm.LogoutDialog;
import Model.Report.DataSuccessCashierReport;
import NewCashierReport.CashierPreview;
import OpenAndCloseShift.OpenShiftJdailog;
import Payment.PaymentOption;
import Print.ReprintJdailog;
import Return.ApprovalCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import okhttp3.Response;
import org.json.JSONObject;

public class MainPage extends javax.swing.JFrame {

     private Color activeColor = new Color(56, 56, 56);
     private JPanel detailProduct;
     private int limit = 10;
     LoginFormJdailog jdFormLogin = new LoginFormJdailog(new JFrame(), true);

     public static boolean isFullScreen = false;
     BackgroundImage bgimg = new BackgroundImage();

     public MainPage() {
          initComponents();
          jScrollPaneDetail.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPaneCategory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

          panelProduct.removeAll();
          panelProduct.add(bgimg);
          panelProduct.revalidate();
          panelProduct.repaint();

          jScrollPaneCategory.setVisible(false);
          panelPagination.setVisible(false);
          searchBox.requestFocusInWindow();
          groupEvent();
          getImage();
          JavaExistScreen.existFun(this); // when user try to close applicatio dialog will ask " Are you sure ? "
          setTitle("King Mart");
//           setExtendedState(JFrame.MAXIMIZED_BOTH);
          currentDate.setFont(WindowFonts.timeNewRomanBold14);
          lbPOSId.setFont(WindowFonts.timeNewRomanBold14);
          boxUserName.setFont(WindowFonts.timeNewRomanBold14);

          searchBox.disabledTextField(false);
          textField.disabledTextField(false);
          // custome scrollbar ui
          jScrollPane2.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane2.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane2.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          // custome scrollbar ui
          jScrollPaneDetail.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane2.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBars = jScrollPaneDetail.getVerticalScrollBar();
          verticalScrollBars.setUnitIncrement(30);
          verticalScrollBars.setBlockIncrement(35);

          // for resize screen
          new ResponsiveSize(detailItem, panelProduct, totalPanel, btnPayment, btnCancel, buttonHoldOrder, jdFormLogin).resizeEvent(this);

          currentDateTime();
          boxUserName.setVisible(false);

     }

     // Set Timer
     private void currentDateTime() {
          Timer timer = new Timer();

          // Set the desired date and time for the first execution
          Date firstExecutionTime = new Date(System.currentTimeMillis());

          // Schedule the task to run at the desired time and repeat every 1 seconds
          timer.schedule(new TimerTask() {
               @Override
               public void run() {
                    SimpleDateFormat formatTime = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss aa");
                    currentDate.setText("" + formatTime.format(new Date()));

               }
          }, firstExecutionTime, 1000); // 1000 milliseconds (1 seconds) interval between executions
     }

     void getImage() {
          // get image from api
          setIconImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "King Mart Small Logo.png")).getImage());
          lbLogo.setIcon(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "logoKingMart.png")));
          imageShopping.setIcon(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "shopping-cart.png")));
          // set background color 
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

     private void groupEvent() {
          JavaEventNextPrevious.eventNext(next, limit, jdFormLogin);  // pagination next
          JavaEventNextPrevious.eventPrevious(previous, limit, jdFormLogin);  // pagination previous
          JavaSearchByNameAndCode.searchProduct(panelProduct, searchBox, panelPagination, jdFormLogin, category);  // search product by name or barcode
          
          JavaSearchByNameAndCode j = new JavaSearchByNameAndCode();
          j.setBtnCancel(btnCancel);
          j.setBtnPayment(btnPayment);
          j.setBtnReturn(btnReturn);
          JavaSearchByNameAndCode.scanProduct(textField, jdFormLogin, panelProduct, detailItem); // function scan barcode or input barcode

          // this event for place holder
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          searchBox.initEvent(btnevent);
          textField.initEvent(btnevent);
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
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    panelCartMouseEntered(evt);
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
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnPaymentMouseEntered(evt);
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
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    // btnOpenShiftMouseEntered(evt);
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
                    .addContainerGap(27, Short.MAX_VALUE))
          );

          searchBox.setPlaceholder("Search by name or barcode");

          currentDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          currentDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          currentDate.setText("Sarturday, 02 January 2024 03:50:00 PM");

          jScrollPane2.setBorder(null);

          panelProduct.setLayout(new java.awt.BorderLayout());
          jScrollPane2.setViewportView(panelProduct);

          lbPOSId.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          lbPOSId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

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
          boxUserName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

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
               .addComponent(menuBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(mainPanelLayout.createSequentialGroup()
                              .addGap(42, 42, 42)
                              .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(38, 38, 38)
                              .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                        .addComponent(boxUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lbPOSId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(currentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                         .addGroup(mainPanelLayout.createSequentialGroup()
                              .addGap(29, 29, 29)
                              .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(panelPagination, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(39, 39, 39))
                                   .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                              .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(panelprocessing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jScrollPaneDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
          );
          mainPanelLayout.setVerticalGroup(
               mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(mainPanelLayout.createSequentialGroup()
                              .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(1, 1, 1)
                              .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(currentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPOSId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(boxUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(menuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(mainPanelLayout.createSequentialGroup()
                              .addComponent(jScrollPaneDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                              .addGap(18, 18, 18)
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
              if (JavaConstant.checkOpenShift == false) {
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
              } else {
                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                   j.setMessage("Please Close Shift Before Log Out!");
                   j.setVisible(true);
                   return;
              }
         }
    }//GEN-LAST:event_btnLoginMouseClicked

     //Action Button Open And Close Shift
    private void btnOpenShiftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenShiftMouseClicked
         String buttonName = btnOpenShift.getButtonName().toLowerCase();
         if (JavaConstant.token != null) {
              if (buttonName.equals("open shift")) {
                   if (JavaConstant.isOpenShift != null) {
                        return;
                   }
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
                             jdOpenShift.setLoginFormJdailog(jdFormLogin);
                             jdOpenShift.setBtnCancel(btnCancel);
                             jdOpenShift.setBtnLogin(btnLogin);
                             jdOpenShift.setDataSuccess(d);
                        }
                   } catch (Exception e) {
                        System.err.println("error getting default price " + e);
                   }

                   jdOpenShift.setJdLoginForm(jdFormLogin);
                   jdOpenShift.setVisible(true);

              } else if (buttonName.equals("close shift")) {
                   ActionCloseShift.closeShift(
                        detailItem, panelProduct,
                        panelPagination, category,
                        searchBox, textField,
                        btnOpenShift, buttonCustomer,
                        buttonDiscount, btnReprint,
                        btnReturn, buttonCashier, btnCancel, buttonHoldOrder, bgimg, btnLogin
                   );
              }
         }
    }//GEN-LAST:event_btnOpenShiftMouseClicked

     //Action Button Reprint
    private void btnReprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReprintMouseClicked
         if (JavaConstant.token != null) {
              //========================= reprint with supervisor===========
//              if (JavaConstant.checkOpenShift) {
//                   ApprovalCode approval = new ApprovalCode(new JFrame(), true);
//                   approval.setJdFormLogin(jdFormLogin);
//                   approval.setTypeForm("reprint");
//                   approval.setVisible(true);
//              }
              //========================= reprint without supervisor=========== 
              if (JavaConstant.checkOpenShift) {
                   ReprintJdailog rep = new ReprintJdailog(new JFrame(), true);
                   rep.setTitle("Reprint Invoice");
                   rep.setTextButtonLeft("Reprint by Last");
                   rep.setTextButtonRight("Reprint by Invoice №");
                   rep.setTypeForm("reprint");
                   rep.setVisible(true);
              }
         }
    }//GEN-LAST:event_btnReprintMouseClicked

     //Action Button payment
    private void btnPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseClicked

         if (JavaConstant.token != null) {

//              if (JavaConstant.isReturn == null) { // there is not transaction retrun 
              Component[] listCom = detailItem.getComponents();
              if (listCom.length != 0) {
                   PaymentOption pay = new PaymentOption(new JFrame(), true);
                   pay.setTotalUsd(totalPanel.getLableTotalUsd());
                   pay.setListCom(listCom);
                   pay.setSubtotalPanel(totalPanel);
                   pay.setDetailItem(detailItem);
                   pay.setBoxOne(boxOne);
                   pay.setBtnPayment(btnPayment);
                   pay.setBtnReturn(btnReturn);
                   pay.setPanelProduct(panelProduct);
                   pay.setBtnDiscount(buttonDiscount);
                   pay.setBtnCancel(btnCancel);
                   pay.setButtonHoldOrder(buttonHoldOrder);
                   pay.setjScrollPaneDetail(jScrollPaneDetail);
                   pay.setVisible(true);
              }
//              } else { // there is transaction retrun 
//                   JdialogConfirmReturn j = new JdialogConfirmReturn(new JFrame(), true);
//                   j.setBoxOne(boxOne);
//                   j.setBtnPayment(btnPayment);
//                   j.setBtnCancel(btnCancel);
//                   j.setSubtotalPanel(totalPanel);
//                   j.setDetailItem(detailItem);
//                   j.setVisible(true);
//              }

         } else {
              System.err.println("System cannot open payment option");
         }


    }//GEN-LAST:event_btnPaymentMouseClicked

     //Action Button Return
    private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseClicked
         if (JavaConstant.token != null) {

              if (JavaConstant.checkOpenShift) {
                   if (detailItem.getComponentCount() > 0) {
                        return;
                   }

                   if (JavaConstant.isReturn != null) {
                        return;
                   }

                   ApprovalCode approval = new ApprovalCode(new JFrame(), true);
                   approval.setDetailItem(detailItem);
                   approval.setPanelProduct(panelProduct);
                   approval.setJdFormLogin(jdFormLogin);
                   approval.setTypeForm("return");
                   approval.setBtnHold(buttonHoldOrder);
                   approval.setBtnCancel(btnCancel);
                   approval.setBtnReturn(btnReturn);
                   approval.setBtnDiscount(buttonDiscount);
                   approval.setBtnPayment(btnPayment);
                   approval.setVisible(true);
              }
         }

//         if (JavaConstant.token != null) {
//              if (JavaConstant.checkOpenShift) {
//                   PrinterReturn approval = new PrinterReturn(new JFrame(), true);
//                   approval.setVisible(true);
//              }
//         }
    }//GEN-LAST:event_btnReturnMouseClicked

     //Action Button Cancel
    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked

         if (JavaConstant.token != null) {

              if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
                   return;
              }

              Component[] listCom = detailItem.getComponents();
              if (listCom.length != 0) {
                   CancelDialog cancel = new CancelDialog(new JFrame(), true);
                   cancel.setDetailItem(detailItem);
                   cancel.setTotalPanel(totalPanel);
                   cancel.setBtnPayment(btnPayment);
                   cancel.setBtnCancel(btnCancel);
                   cancel.setCode("cancel");
                   cancel.setButtonHoldOrder(buttonHoldOrder);
                   cancel.setPanelProduct(panelProduct);
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
//          if (JavaConstant.token != null) {
//
//               try {
//                    CashierReport cashier = new CashierReport(new JFrame(), true);
//                    Response response = JavaConnection.get(
//                         JavaRoute.cashierReport + JavaConstant.userCode + "&userId=" + JavaConstant.cashierId + "&posId=" + JavaConstant.posId);
//                    if (response.isSuccessful()) {
//                         String myObject = response.body().string();
//                         ObjectMapper objMap = new ObjectMapper();
//                         DataSuccessModelReport d = objMap.readValue(myObject, DataSuccessModelReport.class
//                         );
//                         cashier.setDataSuccessReport(d);
//                         cashier.setVisible(true);
//                    }
//               } catch (Exception e) {
//                    System.err.println("error = " + e);
//               }
//          }

          if (JavaConstant.token != null) {

               try {
                    CashierPreview cashier = new CashierPreview(new JFrame(), true);
//                    Response response = JavaConnection.get(JavaRoute.cashierReport + JavaConstant.userCode + "&userId=" + JavaConstant.cashierId + "&posId=" + JavaConstant.posId);
                    Response response = JavaConnection.get(JavaRoute.cashierReport + "0005&userId=5&posId=49");

                    if (response.isSuccessful()) {
                         String myObject = response.body().string();
                         ObjectMapper objMap = new ObjectMapper();
                         DataSuccessCashierReport d = objMap.readValue(myObject, DataSuccessCashierReport.class);
                         cashier.setGetData(d);
                         cashier.setVisible(true);
                    }
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     public int countHold() {
          int countH = 0;
          Response responseGet = JavaConnection.get(JavaRoute.holdOrder + "?userId=" + JavaConstant.cashierId);
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
               if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
                    return;
               }
               Component[] listCom1 = detailItem.getComponents();
               if (listCom1.length != 0) {
                    JavaActionAddHold.addHold(detailItem, btnPayment, buttonHoldOrder, btnCancel, totalPanel, countCircleShape);
               }
          }
     }//GEN-LAST:event_buttonHoldOrderMouseClicked

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
                    if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
                         return;
                    }
                    JavaActionDiscount.discount(detailItem, totalPanel);
               }
          }
     }//GEN-LAST:event_buttonDiscountMouseClicked

     //Action show hold order
    private void panelCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCartMouseClicked
         if (JavaConstant.token != null) {
              if (JavaConstant.checkOpenShift) {
                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                   if (JavaConstant.isReturn != null) {
                        j.setMessage(JavaAlertMessage.returnMsg);
                        j.setVisible(true);
                        return;
                   }

                   ListHoldOrder hold = new ListHoldOrder(new JFrame(), true);
                   hold.setDetailItem(detailItem);
                   hold.setSubtotalPanel(totalPanel);
                   hold.setPanelProduct(panelProduct);
                   hold.setBtnPayment(btnPayment);
                   hold.setPanelProduct(panelProduct);
                   hold.setButtonHoldOrder(buttonHoldOrder);
                   hold.setCountCircleShape(countCircleShape);
                   hold.setBtnCancel(btnCancel);
                   hold.setVisible(true);
              }
         }
    }//GEN-LAST:event_panelCartMouseClicked

     private void btnPaymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_btnPaymentMouseEntered

     private void panelCartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCartMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_panelCartMouseEntered

     //Action Button Customer
    private void buttonCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseClicked
         if (JavaConstant.token != null) {
              if (JavaConstant.checkOpenShift) {
                   JdailogCustomer cus = new JdailogCustomer(new JFrame(), true);
                   cus.setVisible(true);
              }
         }
    }//GEN-LAST:event_buttonCustomerMouseClicked

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

     public static void main(String args[]) {
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
