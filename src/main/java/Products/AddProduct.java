package Products;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.combobox.BrandModel;
import Model.combobox.CategoryModel;
import Model.combobox.TaxModel;

import java.awt.Cursor;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

@Setter
@Getter
public class AddProduct extends javax.swing.JDialog {

     private String brandId;
     private String categoryId;
     private String statusProduct;
     private String taxId;
     String path;
     String pathFlag;
     private JPanel panelProduct;
     ActionProduct pro = new ActionProduct();
     private LoginFormJdailog jdLogin;
     private JPanel panelCategory;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public AddProduct(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelAddProduct.setBackground(WindowColor.mediumGreen);
          event();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          // action get select brand
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    brandId = key;
               }
          };
          brand.initEvent(event);
          addComboBrand();

          // action get select category
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    categoryId = key;
               }
          };
          category.initEvent(events);
          addComboCategory();

          // action get select status
//          ButtonEvent eventtt = new ButtonEvent() {
//               @Override
//               public void onSelect(String key) {
//                    statusProduct = key;
//               }
//          };
//          status.initEvent(eventtt);
//          addComboStatus();
          // action get select tax
          ButtonEvent eventtss = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    taxId = key;
               }
          };
          tax.initEvent(eventtss);
          addComboTax();

          price.setComma("comma");
          cost.setComma("comma");
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          productName.initEvent(btnevent);
          productNameKh.initEvent(btnevent);
          barcode.initEvent(btnevent);
          price.initEvent(btnevent);
          cost.initEvent(btnevent);
          weight.initEvent(btnevent);
          qty.initEvent(btnevent);
          note.initEvent(btnevent);
          discount.initEvent(btnevent);
     }

     //Set Combo box brand
     private void addComboBrand() {
          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<BrandModel> brandModel = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.brand);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         BrandModel brand = new BrandModel(
                              obj.getInt("id"),
                              obj.getString("brandNameEn")
                         );
                         brandModel.add(brand);

                         int idBrand = brandModel.get(i).getBrandId();
                         String brandName = brandModel.get(i).getBrandName();

                         map.put(brandName, "" + idBrand);
                    }
                    brand.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     //Set Combo box category
     private void addComboCategory() {
          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<CategoryModel> categoryModel = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.category);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);

//                         if (_catName.equals("new items") || _catName.equals("promotion") || _catName.equals("all")) 
                         CategoryModel categ = new CategoryModel(
                              obj.getInt("id"),
                              obj.getString("catNameEn")
                         );
                         categoryModel.add(categ);

                         int idCategory = categoryModel.get(i).getCategoryId();
                         String categoryName = categoryModel.get(i).getCategoryName();
//                         String _catName = obj.getString("catNameEn").toLowerCase();

                         map.put(categoryName, "" + idCategory);

                    }
                    category.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     //Set Combo box status
//     private void addComboStatus() {
//          try {
//               HashMap<String, String> map = new HashMap<>();
//               map.put("In Stock", "In Stock");
//               map.put("Out Stock", "Out Stock");
//               status.setMap(map);
//
//          } catch (Exception e) {
//               System.err.println("error = " + e);
//          }
//     }
     //Set Combo box tax
     private void addComboTax() {
          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<TaxModel> taxModel = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.tax);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         TaxModel tax = new TaxModel(
                              obj.getInt("id"),
                              obj.getString("tax_name")
                         );
                         taxModel.add(tax);

                         int idTax = taxModel.get(i).getTaxId();
                         String taxName = taxModel.get(i).getTaxName();

                         map.put(taxName, "" + idTax);
                    }
                    tax.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelAddProduct = new javax.swing.JPanel();
          labelPopUpTitle1 = new Components.LabelPopUpTitle();
          label1 = new Components.Label();
          productNameKh = new Components.TextField();
          productName = new Components.TextField();
          label2 = new Components.Label();
          label3 = new Components.Label();
          barcode = new Components.TextField();
          label5 = new Components.Label();
          cost = new Components.TextField();
          label4 = new Components.Label();
          price = new Components.TextField();
          label6 = new Components.Label();
          weight = new Components.TextField();
          label14 = new Components.Label();
          discount = new Components.TextField();
          label13 = new Components.Label();
          tax = new Components.ComboBox();
          label9 = new Components.Label();
          brand = new Components.ComboBox();
          label10 = new Components.Label();
          category = new Components.ComboBox();
          note = new Components.TextField();
          label7 = new Components.Label();
          jLabel7 = new javax.swing.JLabel();
          jLabel10 = new javax.swing.JLabel();
          jLabel11 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel14 = new javax.swing.JLabel();
          label11 = new Components.Label();
          jLabel16 = new javax.swing.JLabel();
          jLabel17 = new javax.swing.JLabel();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          lbPicture = new javax.swing.JLabel();
          buttonUpload = new Button.Button();
          label12 = new Components.Label();
          qty = new Components.TextField();
          label8 = new Components.Label();
          lbFlag = new javax.swing.JLabel();
          button2 = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle1.setLabelTitle("Add Product");

          label1.setLabelName("Product Name ");

          productNameKh.setLabelTextField("Product Name Kh");

          productName.setLabelTextField("Product Name");

          label2.setLabelName("Product Name Kh");

          label3.setLabelName("Barcode");

          barcode.setLabelTextField("Barcode");

          label5.setLabelName("Cost");

          cost.setLabelTextField("$ 0.00");

          label4.setLabelName("Price");

          price.setLabelTextField("$ 0.00");

          label6.setLabelName("Weight");

          weight.setLabelTextField("Weight");

          label14.setLabelName("Discount");

          discount.setLabelTextField("0%");

          label13.setLabelName("Note");

          label9.setLabelName("Brand");

          label10.setLabelName("Category");

          note.setLabelTextField("Note");

          label7.setLabelName("Product Image");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(204, 0, 0));
          jLabel7.setText("*");

          jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(204, 0, 0));
          jLabel10.setText("*");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(204, 0, 0));
          jLabel11.setText("*");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(204, 0, 0));
          jLabel13.setText("*");

          jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(204, 0, 0));
          jLabel14.setText("*");

          label11.setLabelName("Tax");

          jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel16.setForeground(new java.awt.Color(204, 0, 0));
          jLabel16.setText("*");

          jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel17.setForeground(new java.awt.Color(204, 0, 0));
          jLabel17.setText("*");

          buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonCancelMouseClicked(evt);
               }
          });

          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          lbPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          buttonUpload.setBackground(new java.awt.Color(47, 152, 70));
          buttonUpload.setButtonName("Browse to Upload");
          buttonUpload.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonUploadMouseClicked(evt);
               }
          });

          label12.setLabelName("Quantity");

          qty.setLabelTextField("Quantity");

          label8.setLabelName("Country Image");

          lbFlag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbFlag.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          button2.setBackground(new java.awt.Color(47, 152, 70));
          button2.setButtonName("Browse to Upload");
          button2.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    button2MouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelAddProductLayout = new javax.swing.GroupLayout(panelAddProduct);
          panelAddProduct.setLayout(panelAddProductLayout);
          panelAddProductLayout.setHorizontalGroup(
               panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(panelAddProductLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelAddProductLayout.createSequentialGroup()
                              .addComponent(buttonUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(22, 22, 22))
                         .addGroup(panelAddProductLayout.createSequentialGroup()
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(productNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelAddProductLayout.createSequentialGroup()
                                                  .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelAddProductLayout.createSequentialGroup()
                                                  .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelAddProductLayout.createSequentialGroup()
                                                  .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(brand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 23, Short.MAX_VALUE))
                                   .addGroup(panelAddProductLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
          );
          panelAddProductLayout.setVerticalGroup(
               panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelAddProductLayout.createSequentialGroup()
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelAddProductLayout.createSequentialGroup()
                              .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(18, 18, 18)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(label9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(productNameKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(barcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(label11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(cost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(label13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(weight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(label14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGap(20, 20, 20)
                              .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelAddProductLayout.createSequentialGroup()
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addGroup(panelAddProductLayout.createSequentialGroup()
                              .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(buttonUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addContainerGap(18, Short.MAX_VALUE))))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String proName = productName.getValueTextField();
         String proNameKh = productNameKh.getValueTextField();
         String proBarcode = barcode.getValueTextField();
         String proPrice = price.getValueTextField();
         String proCost = cost.getValueTextField();
         String proWeight = weight.getValueTextField();
         String proNote = note.getValueTextField();
         String proDiscount = discount.getValueTextField();
         String proQty = qty.getValueTextField();

         if (proName == null || proName.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Product Name can not be empty!");
              return;
         }
         if (proBarcode == null || proBarcode.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Barcode can not be empty!");
              return;
         }
         if (proPrice == null || proPrice.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Price can not be empty!");
              return;
         }
         if (proCost == null || proCost.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Cost can not be empty!");
              return;
         }
         if (categoryId == null) {
              JOptionPane.showMessageDialog(this, "Please select a category!");
              return;
         }
         if (categoryId == null) {
              JOptionPane.showMessageDialog(this, "Please select a category!");
              return;
         }
         if (brandId == null) {
              JOptionPane.showMessageDialog(this, "Please select a brand!");
              return;
         }
         if (brandId == null) {
              JOptionPane.showMessageDialog(this, "Please select a brand!");
              return;
         }
         if (taxId == null) {
              JOptionPane.showMessageDialog(this, "Please select a tax!");
              return;
         }
//         if (statusProduct == null) {
//              JOptionPane.showMessageDialog(this, "Please select a product status!");
//              return;
//         }

         proPrice = proPrice.replace(",", "");
         proCost = proCost.replace(",", "");

         String url = new JavaBaseUrl().getBaseUrl() + JavaRoute.addProduct;
         OkHttpClient client = new OkHttpClient();
         // File to upload

         // Request body
         MultipartBody.Builder requestBody = new MultipartBody.Builder()
              .setType(MultipartBody.FORM)
              .addFormDataPart("catId", categoryId)
              .addFormDataPart("proNameEn", proName)
              .addFormDataPart("cost", proCost)
              .addFormDataPart("price", proPrice)
              .addFormDataPart("barcode", proBarcode)
              .addFormDataPart("brandId", brandId)
              .addFormDataPart("createBy", JavaConstant.cashierId + "")
              .addFormDataPart("taxId", taxId)
              .addFormDataPart("productStatus", "");

         if (path != null) {
              File fileToUpload = new File(path);
              requestBody.addFormDataPart("file", fileToUpload.getName(),
                   RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));
         }

         if (pathFlag != null) {
              File fileToUpload = new File(pathFlag);
              requestBody.addFormDataPart("flagFile", fileToUpload.getName(),
                   RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));
         }

         if (proWeight != null) {
              requestBody.addFormDataPart("weight", proWeight);
         }

         if (proDiscount != null) {
              requestBody.addFormDataPart("discount", proDiscount);
         }

         if (proNote != null) {
              requestBody.addFormDataPart("note", proNote);
         }

         if (proNameKh != null) {
              requestBody.addFormDataPart("proNameKh", proNameKh);
         }

         if (proQty != null) {
              requestBody.addFormDataPart("proQty", proQty);
         }

         // Request
         Request request = new Request.Builder()
              .url(url)
              .post(requestBody.build())
              .header("Authorization", "Bearer " + JavaConstant.token)
              .build();

//         this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

         try {
              Response response = client.newCall(request).execute();
              if (response.isSuccessful()) {
                   System.out.println("success data add");
                   productName.setValueTextField(null);
                   productNameKh.setValueTextField(null);
                   barcode.setValueTextField(null);
                   price.setValueTextField(null);
                   cost.setValueTextField(null);
                   weight.setValueTextField(null);
                   qty.setValueTextField(null);
                   note.setValueTextField(null);
                   discount.setValueTextField(null);
                   productName.setFocus();

                   //       ====== set placeholder ======
                   productNameKh.setLabelTextField("Product Name Kh");
                   barcode.setLabelTextField("Barcode");
                   price.setLabelTextField("$ 0.00");
                   cost.setLabelTextField("$ 0.00");
                   weight.setLabelTextField("Weight");
                   qty.setLabelTextField("Quantity");
                   note.setLabelTextField("Note");
                   discount.setLabelTextField("0%");
                   productNameKh.setLabelTextField("Product Name Kh");
                   productNameKh.setLabelTextField("Product Name Kh");

                   categoryId = null;
                   brandId = null;
                   taxId = null;
                   statusProduct = null;
                   path = null;
                   pathFlag = null;

                   brand.setToFirstItem();
                   category.setToFirstItem();
                   tax.setToFirstItem();
//                   status.setToFirstItem();

                   lbPicture.setIcon(null);
                   lbFlag.setIcon(null);
                   
                   
                   System.out.println("jjjjjjjjjjjjjjjjjjjjj = " + jdLogin.getCatId());
                   // for reload items
                   jdLogin.onClickCategory("new items", jdLogin.getCatId());
                   panelCategory.getComponents()[1].setBackground(WindowColor.black);

              }
              // Do something with the response.
         } catch (IOException e) {
              System.out.println("err = " + e);
         }

    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUploadMouseClicked
         try {
              path = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(path, lbPicture, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
         }

    }//GEN-LAST:event_buttonUploadMouseClicked

    private void button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseClicked
         try {
              pathFlag = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(pathFlag, lbFlag, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_button2MouseClicked

//    ========================== for file chooser JNA ===========================
//     public interface User32 extends Library {
//
//          User32 INSTANCE = Native.load("user32", User32.class);
//
//          int MessageBoxW(int hWnd, String lpText, String lpCaption, int uType);
//     }
//
//     void funChooseFile() throws IOException {
//          JFrame frame = new JFrame("File Chooser Example");
//          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          frame.setSize(750, 650);
//          FileDialog fileDialog = new FileDialog(frame, "Choose File", FileDialog.LOAD);
//          fileDialog.setVisible(true);
//
//          String selectedFile = fileDialog.getFile();
//          if (selectedFile != null) {
//               String directory = fileDialog.getDirectory();
//               String filePath = directory + selectedFile;
//               // Now you have the absolute path of the selected file, you can further process it if needed
//               File file = new File(filePath);
//               path = file.getAbsolutePath();
//               JavaConstant.coverImagePath(path, lbPicture, 124, 235);
//
//          } else {
//               System.out.println("No file selected.");
//          }
//     }
     //    ========================== end file chooser JNA ===========================
     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AddProduct dialog = new AddProduct(new javax.swing.JFrame(), true);
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
     private Components.TextField barcode;
     private Components.ComboBox brand;
     private Button.Button button2;
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Button.Button buttonUpload;
     private Components.ComboBox category;
     private Components.TextField cost;
     private Components.TextField discount;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel16;
     private javax.swing.JLabel jLabel17;
     private javax.swing.JLabel jLabel7;
     private Components.Label label1;
     private Components.Label label10;
     private Components.Label label11;
     private Components.Label label12;
     private Components.Label label13;
     private Components.Label label14;
     private Components.Label label2;
     private Components.Label label3;
     private Components.Label label4;
     private Components.Label label5;
     private Components.Label label6;
     private Components.Label label7;
     private Components.Label label8;
     private Components.Label label9;
     private Components.LabelPopUpTitle labelPopUpTitle1;
     private javax.swing.JLabel lbFlag;
     private javax.swing.JLabel lbPicture;
     private Components.TextField note;
     private javax.swing.JPanel panelAddProduct;
     private Components.TextField price;
     private Components.TextField productName;
     private Components.TextField productNameKh;
     private Components.TextField qty;
     private Components.ComboBox tax;
     private Components.TextField weight;
     // End of variables declaration//GEN-END:variables
}
