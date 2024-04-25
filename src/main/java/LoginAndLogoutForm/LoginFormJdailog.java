package LoginAndLogoutForm;

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
import Constant.JavaMessage;
import Products.ProductBox;



public class LoginFormJdailog extends javax.swing.JDialog {

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     DecimalFormat df = new DecimalFormat("#,##0.00 kg");
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat bar = new DecimalFormat("########00000000");
     DecimalFormat kh = new DecimalFormat("#,##0");

     private JLabel lbPOSId;
     private Button btnLogin;
     private JLabel boxUserName;
     private JPanel category;
     private JPanel panelProduct;
     private JScrollPane jScrollPaneCategory;
     private JPanel detailItem;
     private JPanel boxOne;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;
     private JPanel panelPagination;
     ActionProduct pro = new ActionProduct();
     private ComboBox cmboxBrand;
//  checkOpenShift = false;
     private Button btnOpenShift;
     private int limit;
     private int catId;
     private int count;
     private int brandId = 0;
     private String catIdIndex0;
     private String catName;
     private SearchField searchBox;
     private TextField textField;
     private Button buttonHoldOrder;
     private ButtonCancel btnCancel;
     private Button btnreturn;
     private Button btnReprint;
     private Button buttonDiscount;
     private Button buttonCustomer;
     private LabelTitle breadcrumb;

     public LoginFormJdailog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelLogin.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          txtUserId.setFocus();

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
          pro.setBoxUserName(boxUserName);
          pro.setPanelProduct(panelProduct);
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
               .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addGap(36, 36, 36))
          );
          panelLoginLayout.setVerticalGroup(
               panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLoginLayout.createSequentialGroup()
                    .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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
          pro.eventBtnBuy(proModel, proModel.getQty() , new ProductBox());
     }
     
     public void scanbarCodeAddProduct(ProductModel proModel,String scanbarcode) {
          if (proModel.getQty() == 0) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage(JavaMessage.productOutStock);
               j.setVisible(true);
               return;
          }
          pro.eventBtnBuy(proModel, 1 , new ProductBox());
     }

    private void buttonLogin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogin1MouseClicked
//         String userId = txtUserId.getValueTextField();
//         String password = txtPassword.getValuePassword();

         String userId = "0002";
         String password = "TT@126$kh#";
         String deviceName = JavaConstant.getDeviceName();

         JSONObject json = new JSONObject();
         json.put("userCode", userId);
         json.put("password", password);
         json.put("deviceName", deviceName);

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
                   Response responseOpenShift = JavaConnection.get(JavaRoute.openShift + "/" + JavaConstant.userCode);

                   if (responseOpenShift.isSuccessful()) {
                        btnOpenShift.setBackground(WindowColor.green);
                        String result = responseOpenShift.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        OpenShiftDataModel data = objectMapper.readValue(result, OpenShiftDataModel.class);
                        if (data.getData().getNumberOpenShift() == 1) { // == 1 user still open shift
                             
                             JavaConstant.checkOpenShift = true;

                             searchBox.disabledTextField(true);
                             textField.disabledTextField(true);

                             btnreturn.setBackground(WindowColor.brown);
                             buttonCustomer.setBackground(WindowColor.green);
                             buttonDiscount.setBackground(WindowColor.green);
                             btnReprint.setBackground(WindowColor.green);

                             btnOpenShift.setButtonName(JavaConstant.closeShift);
                             JavaConstant.checkCloseShift = data.getData().getNumberOpenShift();
                             JavaConstant.numberOpenShift = Integer.valueOf("" + data.getData().getNumberOpenShift());
                             
                        }
                   }

                   dispose();
                   getBtnLogin().setButtonName("Logout");
                   
//                  if (JavaConstant.checkOpenShift) {
//                      getBtnLogin().setBackground(WindowColor.lightGray);
//                  }
                   
                   getBoxUserName().setText(JavaConstant.fullName.toUpperCase() + " " + " USER ID : " + JavaConstant.userCode);
                   lbPOSId.setText("POS ID : " + JavaConstant.posId);
                   category();
                   getjScrollPaneCategory().setVisible(true);
                   ActionRequestBrand.requestBrand(cmboxBrand);

                   eventSelectBrand();
                   txtUserId.setValueTextField(null);
                   txtPassword.setValuePassword(null);

                   if (JavaConstant.checkOpenShift) {
                        textField.setFocus();
                   }
                   
                   JavaConstant.isOpenShift = null;

              } else {
                   JOptionPane.showMessageDialog(this, "Wrong email or password!");
              }

         } catch (Exception e) {

         }
    }//GEN-LAST:event_buttonLogin1MouseClicked

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

     public void eventSelectBrand() {
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    getProductByBrandID(key, limit);
               }
          };
          cmboxBrand.initEvent(events);
     }

     public void getProductByBrandID(String key, int limits) {
          Response response = JavaConnection.get(JavaRoute.getProductByBrandId + "?brandId=" + key + "&limit=" + limits + "");

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
                    setBrandId(Integer.parseInt(key));
                    setCount(model.getCount());
                    // each time select brand category will remove bg color 
                    if (getCatName() != null) {
                         Component[] listCom = category.getComponents();
                         int index = Integer.parseInt(getCatName());
                         listCom[index].setBackground(WindowColor.darkGreen);
                    }
               }
          } catch (Exception e) {
               System.err.println("error get produt by brand = " + e);
          }
     }

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
                         category.add(categoryTitle);
                         String catNameData = listCategory.get(i).getCatNameEn();

                         categoryTitle.setLabelTitle(catNameData);
                         int ind = i;

                         ButtonEvent event = new ButtonEvent() { // click on category
                              @Override
                              public void onMouseClick() {
                                   if (JavaConstant.checkOpenShift) {

                                        setCatId(catId);
                                        getPanelPagination().setVisible(true);
                                        // click on category actice background color
                                        Component[] listCom = category.getComponents();
                                        for (int j = 0; j < listCom.length; j++) {
                                             String title = ((LabelTitle) listCom[j]).getLabelTitle();
                                             if (catNameData.equals(title)) {
                                                  listCom[j].setBackground(WindowColor.black);
                                                  setCatName("" + j);
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
                                             pro.product(catId, limit, panelProduct);
                                        }
                                        pro.setBtnPayment(btnPayment);
                                        pro.setButtonHoldOrder(buttonHoldOrder);
                                        pro.setBtnCancel(btnCancel);
                                        panelProduct.revalidate();
                                        panelProduct.repaint();
                                        setCount(pro.getCount());

                                        if (JavaConstant.checkOpenShift) {
                                             textField.setFocus();
                                        }

                                   } else {
                                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                                        j.setMessage("You have to open shift first!");
                                        j.setVisible(true);
                                   }
                              }
                         };
                         categoryTitle.initEvent(event);

                    }

                    // setter of actionProduct
                    assignProduct(null);

                    if (btnOpenShift.getButtonName().equals("Close Shift")) {
                         category.getComponents()[0].setBackground(WindowColor.black);
                         setCatId(catId);
                         setCatName("" + 0);
                         setBrandId(0); // each time user click on category brandId will be 0
                         cmboxBrand.setToFirstItem(); // each time user click on category combobox brand will be set to first item
                         searchBox.requestFocusInWindow(); // each time user click on category remove cursor from searchBox
                         panelProduct.removeAll();
                         pro.getAllProduct(panelProduct);
                         pro.setBtnPayment(btnPayment);
                         panelProduct.revalidate();
                         panelProduct.repaint();
                         setCount(pro.getCount());
                    } else {
//                          == == == == == == == Add Background == == == == == == ==
                         BackgroundImage bgimg = new BackgroundImage();
                         panelProduct.removeAll();
                         panelProduct.add(bgimg);
                         panelProduct.revalidate();
                         panelProduct.repaint();
//                          == == == == == == == == == == == == == == == == == == == == == == =
                    }

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

     public void callDataInFullScreen() {
          panelProduct.removeAll();
          pro.setBtnPayment(btnPayment);
          pro.setButtonHoldOrder(buttonHoldOrder);
          pro.setBtnCancel(btnCancel);
          panelProduct.revalidate();
          panelProduct.repaint();
          ActionProduct.marginRight = 15;
          JavaConstant.rowNum = 7;
          pro.getAllProduct(panelProduct);
     }

     public TextField getTextField() {
          return textField;
     }

     public void setTextField(TextField textField) {
          this.textField = textField;
     }

     public SearchField getSearchBox() {
          return searchBox;
     }

     public void setSearchBox(SearchField searchBox) {
          this.searchBox = searchBox;
     }

     public String getCatName() {
          return catName;
     }

     public void setCatName(String catName) {
          this.catName = catName;
     }

     public String getCatIdIndex0() {
          return catIdIndex0;
     }

     public void setCatIdIndex0(String catIdIndex0) {
          this.catIdIndex0 = catIdIndex0;
     }

     public int getBrandId() {
          return brandId;
     }

     public void setBrandId(int brandId) {
          this.brandId = brandId;
     }

     public int getCount() {
          return count;
     }

     public void setCount(int count) {
          this.count = count;
     }

     public int getLimit() {
          return limit;
     }

     public void setLimit(int limit) {
          this.limit = limit;
     }

     public int getCatId() {
          return catId;
     }

     public void setCatId(int catId) {
          this.catId = catId;
     }

     public JLabel getLbPOSId() {
          return lbPOSId;
     }

     public void setLbPOSId(JLabel lbPOSId) {
          this.lbPOSId = lbPOSId;
     }

     public Button getBtnOpenShift() {
          return btnOpenShift;
     }

     public void setBtnOpenShift(Button btnOpenShift) {
          this.btnOpenShift = btnOpenShift;
     }

//     public boolean isCheckOpenShift() {
//          return checkOpenShift;
//     }
//
//     public void setCheckOpenShift(boolean checkOpenShift) {
//          this.checkOpenShift = checkOpenShift;
//     }
     public JLabel getBoxUserName() {
          return boxUserName;
     }

     public void setBoxUserName(JLabel boxUserName) {
          this.boxUserName = boxUserName;
     }

     public Button getBtnLogin() {
          return btnLogin;
     }

     public void setBtnLogin(Button btnLogin) {
          this.btnLogin = btnLogin;
     }

     public void setCategory(JPanel category) {
          this.category = category;
     }

     public JPanel getCategory() {
          return category;
     }

     public JScrollPane getjScrollPaneCategory() {
          return jScrollPaneCategory;
     }

     public void setjScrollPaneCategory(JScrollPane jScrollPaneCategory) {
          this.jScrollPaneCategory = jScrollPaneCategory;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public JPanel getBoxOne() {
          return boxOne;
     }

     public void setBoxOne(JPanel boxOne) {
          this.boxOne = boxOne;
     }

     public ComboBox getCmboxBrand() {
          return cmboxBrand;
     }

     public void setCmboxBrand(ComboBox cmboxBrand) {
          this.cmboxBrand = cmboxBrand;
     }

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

     public Button getBtnreturn() {
          return btnreturn;
     }

     public void setBtnreturn(Button btnreturn) {
          this.btnreturn = btnreturn;
     }

     public Button getBtnReprint() {
          return btnReprint;
     }

     public void setBtnReprint(Button btnReprint) {
          this.btnReprint = btnReprint;
     }

     public Button getButtonDiscount() {
          return buttonDiscount;
     }

     public void setButtonDiscount(Button buttonDiscount) {
          this.buttonDiscount = buttonDiscount;
     }

     public Button getButtonCustomer() {
          return buttonCustomer;
     }

     public void setButtonCustomer(Button buttonCustomer) {
          this.buttonCustomer = buttonCustomer;
     }

     public LabelTitle getBreadcrumb() {
          return breadcrumb;
     }

     public void setBreadcrumb(LabelTitle breadcrumb) {
          this.breadcrumb = breadcrumb;
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
