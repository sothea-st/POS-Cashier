package LoginAndLogoutForm;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.LabelTitle;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.PackageProduct.CategoryModel;
import Model.PackageProduct.ProductModel;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import Button.Button;
import Components.BackgroundImage;
import Components.ComboBox;
import Components.JavaAlertMessage;
import Components.SearchField;
import Components.TextField;
import Controller.ActionProduct.ActionProduct;
import Controller.ActionRequestBrand.ActionRequestBrand;
import Model.Login.LoginModel;
import Model.OpenShift.OpenShiftDataModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import View.MainPage.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ButtonPackage.ButtonCancel;
import Components.BoxItem;
import Components.LabelFontGreen;
import Constant.JavaMessage;
import HoldOrder.HoldModelDir.DataListHold;
import HoldOrder.HoldModelDir.ListDetailHold;
import HoldOrder.HoldModelDir.ResultHoldSuccess;
import Products.ProductBox;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;
import password.ChangePasswordForm;

@Setter
@Getter
public class LoginFormJdailog extends javax.swing.JDialog {

     DecimalFormat df = new DecimalFormat("#,##0.00 kg");
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat bar = new DecimalFormat("########00000000");
     DecimalFormat kh = new DecimalFormat("#,##0");

     private Button btnLogin;
     private JPanel boxOne;
     private Button btnPayment;
     private Button buttonHoldOrder;
     private ButtonCancel btnCancel;
     private JPanel category;
     private JPanel detailItem;
     private JPanel panelPagination;
     private SubtotalPanel subtotalPanel;
     private Button btnReturn;
     private JLabel titleOrder;
     private Button btnReprint;
     private Button buttonDiscount;
     private Button buttonCustomer;
     private Button stock;
     private JLabel lbPOSId;
     private JPanel panelProduct;
     private JScrollPane jScrollPaneCategory;
     ActionProduct pro = new ActionProduct();
     private ComboBox cmboxBrand;
     private Button btnOpenShift;
     private int limit;
     private int catId;
     private int count;
     private int brandId = 0;
     private String catIdIndex0;
     private String catName;
     private SearchField searchBox;
     private TextField textField;
     private LabelTitle breadcrumb;
     private JFrame mainFrame;
     private Button buttonStaff;
     private LabelFontGreen next;
     private LabelFontGreen previous;
     private String titleCategory;
     private JLabel boxImg;
     private Button btnReporting;
     private Button btnSettings;

     public LoginFormJdailog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelLogin.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          txtUserId.setFocus();
          // Override the window close button functionality
          addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                    JavaConstant.restoreDefaultCursor(mainFrame);
                    dispose(); // Ensure the dialog is closed
               }
          });

     }

     //Function call Placeholder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtUserId.initEvent(btnevent);
     }

     public void assignProduct(ProductDataModel[] listData) {
          panelProduct.removeAll();
          panelProduct.revalidate();
          panelProduct.repaint();
          // setter of actionProduct
          pro.setBtnLogin(btnLogin);
          pro.setBoxOne(boxOne);
          pro.setBtnPayment(btnPayment);
          pro.setButtonHoldOrder(buttonHoldOrder);
          pro.setBtnCancel(btnCancel);
          pro.setCategory(category);
          pro.setDetailItem(detailItem);
          pro.setPanelPagination(panelPagination);
          pro.setSubtotalPanel(subtotalPanel);
          pro.setjScrollPaneCategory(jScrollPaneCategory);
          // pro.setBoxUserName(boxUserName);
          pro.setPanelProduct(panelProduct);
          pro.setBtnReturn(btnReturn);
          pro.setTitleOrder(titleOrder);
          pro.setNext(next);
          if (listData != null) {
               pro.assignProduct(listData, panelProduct);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        txtUserId = new Components.TextField();
        txtPassword = new Components.PasswordField();
        labelPopUpTitle2 = new Components.LabelPopUpTitle();
        lbUserId = new Components.Label();
        lbPassword = new Components.Label();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        buttonLogin1 = new ButtonPackage.ButtonLogin();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelLogin.setForeground(new java.awt.Color(0, 0, 0));

        txtUserId.setLabelTextField("User ID");

        txtPassword.setTextPassowrd("Password");

        labelPopUpTitle2.setLabelTitle("Login");

        lbUserId.setLabelName("User ID");

        lbPassword.setLabelName("Password");

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        buttonLogin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLogin1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLogin1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     public void scanbarCodeAddProduct(ProductModel proModel) {
          if (proModel.getQty() == 0) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage(JavaMessage.productOutStock);
               j.setVisible(true);
               return;
          }

          if (JavaConstant.returnByBarcode == null) {
               pro.eventBtnBuy(proModel, proModel.getQty(), new ProductBox());
          } else {
               pro.eventBtnBuy(proModel, 1, new ProductBox()); // this working when cashier use function return
          }
     }

     DataListHold[] listHoldData;
     ListDetailHold[] listHoldDetails;

     public void getHold() {
          try {
               Response response = JavaConnection.get(JavaRoute.holdOrder + "?userId=" + JavaConstant.cashierId);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ResultHoldSuccess data = objMap.readValue(responseData, ResultHoldSuccess.class);
                    listHoldData = data.getData();
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void scanbarCodeAddProduct(ProductModel proModel, String scanbarcode) {

          getHold();
          Component[] listPanelProduct = panelProduct.getComponents();
          Component[] listDetailItem = detailItem.getComponents();
          if (proModel.getQty() == 0) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage(JavaMessage.productOutStock);
               j.setVisible(true);
               return;
          }

          if (JavaConstant.returnByBarcode != null) {
               pro.eventBtnBuy(proModel, 1, new ProductBox());
               return;
          }

          int qtySale = 0;
          if (listDetailItem.length > 0) {
               for (Component c : listDetailItem) {
                    var data = ((BoxItem) c);
                    if (data.getLabelBarcode().equals(proModel.getBarcode())) {
                         qtySale = data.getQty();
                         qtySale++;
                    }
               }
          }

          for (Component cc : listPanelProduct) {
               var data = ((ProductBox) cc);

               if (data.getBarcode().equals(proModel.getBarcode())) {

                    if (data.getQty().equals("0")) {
                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                         j.setMessage(JavaMessage.productOutStock);
                         j.setVisible(true);
                         return;
                    }

                    int orgQty = data.getOrgQty();

                    orgQty = orgQty - qtySale;
                    if (orgQty < 0) {
                         orgQty = 0;
                    }

                    //    =============== update qty with hole ==================
                    if (listHoldData.length > 0) {
                         for (DataListHold cv : listHoldData) {
                              ListDetailHold[] l = cv.getListDetails();
                              for (ListDetailHold dd : l) {
                                   if (dd.getBarcode().equals(proModel.getBarcode())) {
                                        int holdQty = dd.getQty();
                                        orgQty = orgQty - holdQty;
                                        break;
                                   }
                              }
                         }
                    }

                    if (data.getQty().equals("0")) {
                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                         j.setMessage(JavaMessage.productOutStock);
                         j.setVisible(true);
                         return;
                    }

                    data.setQty("" + orgQty);
                    if (data.getQty().equals("0")) {
                         data.setProductStatus(JavaMessage.outStock);
                    }
               }
          }
          pro.eventBtnBuy(proModel, 1, new ProductBox());
     }

    private void buttonLogin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogin1MouseClicked
         //     String userId = txtUserId.getValueTextField();
         //     String password = txtPassword.getValuePassword();

         String userId = "0005";
         String password = "TT@126$kh#";
         JSONObject json = new JSONObject();
         String deviceName = JavaConstant.getDeviceName();
         String ipAddress;
         try {
              ipAddress = JavaConstant.getIpAddressPC();
              json.put("userCode", userId);
              json.put("password", password);
              json.put("deviceName", deviceName);
              json.put("ipAddress", ipAddress);
//              json.put("deviceName", "TT-MOB-APP-02");
//              json.put("ipAddress", "172.20.10.48");
         } catch (Exception ex) {
              System.err.println("erro getIpAddress : " + ex);
         }

         JavaConstant.setCircleLoadingCursor(mainFrame);

         try {

              if (userId == null || userId.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "User ID can not be empty!");
                   return;
              }

              if (password == null || password.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Password can not be empty!");
                   return;
              }

              Response response = JavaConnection.login(JavaRoute.login, json);

              if (response.isSuccessful()) {
                   String responseData = response.body().string();

                   ObjectMapper objMap = new ObjectMapper();
                   LoginModel model = objMap.readValue(responseData, LoginModel.class);

                   if (!model.getMag().equals("success")) {
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage(model.getMag());
                        j.setVisible(true);
                        return;
                   }

                   if (model.getRoleID() == null) {
                        dispose();
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage(model.getRoleName());
                        j.setVisible(true);
                        return;
                   }

                   btnOpenShift.setBackground(WindowColor.green);
                   JavaConstant.token = model.getToken();
                   JavaConstant.fullName = model.getUserName();
                   JavaConstant.userCode = model.getUserCode();
                   JavaConstant.posId = model.getPosID();
                   JavaConstant.cashierId = model.getID();
                   JavaConstant.empId = model.getEmpID();
                   JavaConstant.roleName = model.getRoleName();

                   Response responseOpenShift = JavaConnection.get(JavaRoute.openShift + "/" + JavaConstant.userCode);

                   if (model.getRoleName().equals("Admin")) {
                        stock.setVisible(true);
                        buttonStaff.setVisible(true);
                        btnReporting.setVisible(true);
                        btnSettings.setVisible(true);
                        stock.setBackground(WindowColor.green);
                        btnReporting.setBackground(WindowColor.green);
                        btnSettings.setBackground(WindowColor.green);
                        buttonStaff.setBackground(WindowColor.green);
                   }

                   if (responseOpenShift.isSuccessful()) {
                        btnOpenShift.setBackground(WindowColor.green);
                        String result = responseOpenShift.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        OpenShiftDataModel data = objectMapper.readValue(result, OpenShiftDataModel.class);
                        if (data.getData().getNumberOpenShift() == 1) { // == 1 user still open shift

                             if (model.getRoleName().equals("Admin")) {
                                  stock.setVisible(true);
                                  buttonStaff.setVisible(true);
                                  btnReporting.setVisible(true);
                                  btnSettings.setVisible(true);
                             }
                             JavaConstant.checkOpenShift = true;

                             searchBox.disabledTextField(true);
                             textField.disabledTextField(true);

                             btnReporting.setBackground(WindowColor.green);
                             btnSettings.setBackground(WindowColor.green);
                             btnReturn.setBackground(WindowColor.brown);
                             buttonCustomer.setBackground(WindowColor.green);
                             buttonDiscount.setBackground(WindowColor.green);
                             btnReprint.setBackground(WindowColor.green);
                             stock.setBackground(WindowColor.green);
                             buttonStaff.setBackground(WindowColor.green);
                             pro.setBtnReturn(btnReturn);

                             btnOpenShift.setButtonName(JavaConstant.closeShift);
                             JavaConstant.checkCloseShift = data.getData().getNumberOpenShift();
                             JavaConstant.numberOpenShift = Integer.valueOf("" + data.getData().getNumberOpenShift());
                        }
                   }

                   dispose();
                   getBtnLogin().setButtonName("Logout");

                   lbPOSId.setText(JavaConstant.fullName.toUpperCase() + " , " + " USER ID : " + JavaConstant.userCode + "            POS ID : " + JavaConstant.posId);

                   //         ==== event on profile image for change password ====
                   Icon icon = new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "UserIcon.png"));
                   JavaConstant.setPointer(boxImg);
                   boxImg.setIcon(icon);
                   boxImg.setVisible(true);
                   boxImg.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                             ChangePasswordForm obj = new ChangePasswordForm(new JFrame(), true);
                             obj.setVisible(true);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }

                   });

                   category();
                   jScrollPaneCategory.setVisible(true);
                   ActionRequestBrand.requestBrand(cmboxBrand);

                   eventSelectBrand();
                   txtUserId.setValueTextField(null);
                   txtPassword.setValuePassword(null);

                   if (JavaConstant.checkOpenShift) {
                        textField.setFocus();
                   }

                   JavaConstant.isOpenShift = null;

                   JavaConstant.restoreDefaultCursor(mainFrame);

              } else {
                   JavaConstant.restoreDefaultCursor(mainFrame);
                   JOptionPane.showMessageDialog(this, "Wrong email or password!");
              }

         } catch (Exception e) {

         }
    }//GEN-LAST:event_buttonLogin1MouseClicked

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
         JavaConstant.restoreDefaultCursor(mainFrame);
         this.dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

     public void eventSelectBrand() {
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelectBreadcrumb(String key, String value) {
                    if (key != null) {
                         JavaConstant.brandId = Integer.parseInt(key);
                         breadcrumb.setLabelTitle(value);
                    }
                    JavaConstant.limitPagination = 21;
                    JavaConstant.limit = 21;
                    JavaConstant.page = 0;
                    next.setBackground(WindowColor.white);
                    previous.setBackground(WindowColor.lightGray);
                    getProductByBrandID(key, limit);
               }
          };
          cmboxBrand.initEvent(events);
     }

     public void getProductByBrandID(String key, int limits) {

          Response response = JavaConnection.get(JavaRoute.getProductByBrandId + "?brandId=" + key + "&limit=" + JavaConstant.limitPagination + "&page=" + JavaConstant.page);

          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData model = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listProduct = model.getData();
                    if (listProduct.length > 0) {
                         assignProduct(listProduct);
                    } else {
                         panelProduct.removeAll();
                         panelProduct.add(new JLabel(JavaConstant.noResult));
                         panelProduct.revalidate();
                         panelProduct.repaint();
                    }

                    if (listProduct.length < JavaConstant.limitPagination) {
                         next.setBackground(WindowColor.lightGray);
                    }

                    setBrandId(Integer.parseInt(key));
                    setCount(model.getCount());
                    // each time select brand category will remove bg color 

                    if (getCatName() != null) {
                         category.getComponents()[Integer.parseInt(getCatName())].setBackground(WindowColor.darkGreen);
                    }
               }
          } catch (Exception e) {
               System.err.println("error get produt by brand = " + e);
          }
     }
     private String categoryName;

     private void category() {
          try {
               ArrayList<CategoryModel> listCategory = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.category);
               category.setLayout(new GridLayout());
               if (response.isSuccessful()) {
                    String strData = response.body().string(); // convert response to string 
                    JSONObject jsonObject = new JSONObject(strData); // conver string to jsonobject
                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                         var objData = data.getJSONObject(i);
                         CategoryModel c = new CategoryModel(
                              objData.getInt("id"),
                              objData.getString("catNameEn"),
                              objData.getInt("parentId"),
                              objData.getInt("parentId")
                         );
                         listCategory.add(c);
                    }

                    for (int i = 0; i < listCategory.size(); i++) {
                         int catId = listCategory.get(i).getId();

                         LabelTitle categoryTitle = new LabelTitle();
                         categoryTitle.setLbCatId("" + catId);
                         categoryTitle.textCenter();

                         String catNameData = listCategory.get(i).getCatNameEn();
                         if (!catNameData.equals("ALL")) {
                              category.add(categoryTitle);
                              categoryTitle.setLabelTitle(catNameData);
                         }

                         ButtonEvent event = new ButtonEvent() { // click on category
                              @Override
                              public void onMouseClick() {
                                   onClickCategory(catNameData, catId);
                              }

                              @Override
                              public void onMousePress() {
                                   categoryTitle.setBackground(WindowColor.gray);
                              }

                              @Override
                              public void onMouseRelease() {
                                   categoryTitle.setBackground(WindowColor.darkGreen);
                              }

                         };
                         categoryTitle.initEvent(event);
                    }

                    // setter of actionProduct
                    assignProduct(null);
                    runData();
                    if (MainPage.isFullScreen && btnOpenShift.getButtonName().equals("Close Shift")) {
                         callDataInFullScreen();
                    }
               } else {
                    System.err.println("fail load category");
               }
          } catch (Exception e) {
               System.err.println("error " + e);
          }

     }

     public void onClickCategory(String catNameData, int catId) {

          if (JavaConstant.checkOpenShift) {
               previous.setBackground(WindowColor.lightGray);
               next.setBackground(WindowColor.white);
               JavaConstant.resetValuePagination(); // for pagination

               setTitleCategory(catNameData);

               previous.setBackground(WindowColor.white);
               next.setBackground(WindowColor.white);

               setCatId(catId);

               getPanelPagination().setVisible(true);

               // click on category actice background color
               Component[] listCom = category.getComponents();
               for (int j = 0; j < listCom.length; j++) {
                    String title = ((LabelTitle) listCom[j]).getLabelTitle();
                    if (catNameData.equals(title)) {
                         listCom[j].setBackground(WindowColor.black);
                         setCatName("" + j); // setCatName is index for change back ground when user try to pick other category  and select brand
                         breadcrumb.setLabelTitle(title); // add breadcrumb
                    } else {
                         listCom[j].setBackground(WindowColor.darkGreen);
                    }
               }

               setBrandId(0); // each time user click on category brandId will be 0
               cmboxBrand.setToFirstItem(); // each time user click on category combobox brand will be set to first item
               searchBox.requestFocusInWindow(); // each time user click on category remove cursor from searchBox

               // in case when user maximize application to full window 
               if (MainPage.isFullScreen) {
                    callDataInFullScreen();
               }

               panelProduct.removeAll();
               if (catNameData.equals("ALL")) {
                    panelPagination.setVisible(false);
                    pro.getAllProduct(panelProduct);
                    listCom[0].setBackground(WindowColor.black);
                    setCatId(0);
               } else {
                    JavaConstant.page = 0;
                    String lowerCase = catNameData.toLowerCase();
                    pro.setNext(next);
                    switch (lowerCase) {
                         case "new items" -> //   NEW ITEMS
                              pro.newProduct(JavaConstant.limitPagination, panelProduct);
                         case "promotion" -> //  Promotion
                              pro.getPromotion(catId, JavaConstant.limitPagination, panelProduct);
                         default ->
                              pro.product(catId, JavaConstant.limitPagination, panelProduct);
                    }
               }
               pro.setBtnPayment(btnPayment);
               pro.setButtonHoldOrder(buttonHoldOrder);
               pro.setBtnCancel(btnCancel);
               pro.setBtnReturn(btnReturn);
               panelProduct.revalidate();
               panelProduct.repaint();
               setCount(pro.getCount()); // set all product count retrive from api for making pagination

               if (JavaConstant.checkOpenShift) {
                    textField.setFocus();
               }

          } else {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("You have to open shift first!");
               j.setVisible(true);
          }
     }

     public void runData() {
          if (btnOpenShift.getButtonName().equals("Close Shift")) {
               category.getComponents()[1].setBackground(WindowColor.black);
               Component[] _listCom = category.getComponents();
               for (int i = 0; i < _listCom.length; i++) {
                    var titleCategory = ((LabelTitle) _listCom[i]).getLabelTitle();
                    var _catId = ((LabelTitle) _listCom[i]).getLbCatId();

                    String _tCategory = titleCategory.toLowerCase();
                    if (_tCategory.equals("new items")) {

                         categoryName = titleCategory.toLowerCase();
                         catId = Integer.parseInt(_catId);
                         setCatId(catId);

                         setTitleCategory(titleCategory);
                         setCatName("" + i); // setCatName is index for change back ground when user try to pick other category and select brand
                         break;
                    }
               }

               previous.setBackground(WindowColor.lightGray);
               panelPagination.setVisible(true);
               setBrandId(0); // each time user click on category brandId will be 0
               cmboxBrand.setToFirstItem(); // each time user click on category combobox brand will be set to first item
               searchBox.requestFocusInWindow(); // each time user click on category remove cursor from searchBox
               panelProduct.removeAll();
               pro.newProduct(limit, panelProduct);
               pro.setBtnPayment(btnPayment);
               panelProduct.revalidate();
               panelProduct.repaint();
               setCount(pro.getCount());
          } else {
               //      == == == == == == == Add Background == == == == == == ==
               BackgroundImage bgimg = new BackgroundImage();
               panelProduct.removeAll();
               panelProduct.add(bgimg);
               panelProduct.revalidate();
               panelProduct.repaint();

          }
     }

     public void callDataInFullScreen() {
          pro.setNext(next);
          panelProduct.removeAll();
          pro.setBtnPayment(btnPayment);
          pro.setButtonHoldOrder(buttonHoldOrder);
          pro.setBtnCancel(btnCancel);
          pro.setBtnReturn(btnReturn);
          pro.setBtnReturn(btnReturn);
          panelProduct.revalidate();
          panelProduct.repaint();
          ActionProduct.marginRight = 15;
          JavaConstant.rowNum = 7;
          pro.newProduct(JavaConstant.limitPagination, panelProduct);
          setCount(pro.getCount());
     }

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    LoginFormJdailog dialog = new LoginFormJdailog(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                         @Override
                         public void windowClosing(java.awt.event.WindowEvent e) {
                              System.exit(0);
                         }
                    });
                    dialog.setVisible(true);
               }
          });
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel1;
    private ButtonPackage.ButtonLogin buttonLogin1;
    private Components.LabelPopUpTitle labelPopUpTitle2;
    private Components.Label lbPassword;
    private Components.Label lbUserId;
    private javax.swing.JPanel panelLogin;
    private Components.PasswordField txtPassword;
    private Components.TextField txtUserId;
    // End of variables declaration//GEN-END:variables

}
