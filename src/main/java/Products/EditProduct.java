package Products;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.combobox.BrandModel;
import Model.combobox.CategoryModel;
import Model.combobox.TaxModel;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class EditProduct extends javax.swing.JDialog {

     private int productId;
     private String productNameEn;
     private String productNameKh;
     private String productBarcode;
     private String productPrice;
     private String productCost;
     private String productWeight;
     private String productNote;
     private String productDiscount;
     private int brandId;
     private int categoryId;
     private int taxId;
     private String productStatus;
     private String idBrand;
     private int proQty;
     private Icon productImage;
     private Icon flagImage;
     String path;
     String pathFlag;
     private JPanel listGetProduct;

     private JPanel plProduct;
     private LoginFormJdailog jdLogin;
     private JPanel pCategory;

     public EditProduct(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelEditProduct.setBackground(WindowColor.mediumGreen);
          event();

          // action get select brand
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
//                    idBrand = key;
                    brandId = Integer.parseInt(key);
               }
          };
          brand.initEvent(event);
          addComboBrand();

          // action get select tax
          ButtonEvent eventtss = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    taxId = Integer.parseInt(key);
               }
          };
          tax.initEvent(eventtss);
          addComboTax();

          // action get select category
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    categoryId = Integer.parseInt(key);
               }
          };
          category.initEvent(events);
          addComboCategory();

//           // action get select status
//          ButtonEvent eventtt = new ButtonEvent() {
//               @Override
//               public void onSelect(String key) {
//                    productStatus = key;
//               }
//          };
//          status.initEvent(eventtt);
//          addComboStatus();
     }

     public JPanel getPlProduct() {
          return plProduct;
     }

     public void setPlProduct(JPanel plProduct) {
          this.plProduct = plProduct;
     }

     public LoginFormJdailog getJdLogin() {
          return jdLogin;
     }

     public void setJdLogin(LoginFormJdailog jdLogin) {
          this.jdLogin = jdLogin;
     }

     public JPanel getpCategory() {
          return pCategory;
     }

     public void setpCategory(JPanel pCategory) {
          this.pCategory = pCategory;
     }

     public Icon getFlagImage() {
          return flagImage;
     }

     public void setFlagImage(Icon flagImage) {
          this.flagImage = flagImage;
          lbFlag.setIcon(flagImage);
     }

     public void setFlagImage(String url) throws MalformedURLException, IOException {
          JavaConstant.coverImage(url, lbFlag, 130, 130);
     }

     public Icon getProductImage() {
          return productImage;
     }

     public void setProductImage(Icon productImage) {
          this.productImage = productImage;
          lbPicture.setIcon(productImage);
     }

     public void setProductImage(String url) throws MalformedURLException, IOException {
          JavaConstant.coverImage(url, lbPicture, 150, 135);
     }

     public JPanel getListGetProduct() {
          return listGetProduct;
     }

     public void setListGetProduct(JPanel listGetProduct) {
          this.listGetProduct = listGetProduct;
     }

     public int getProQty() {
          return proQty;
     }

     public void setProQty(int proQty) {
          this.proQty = proQty;
          quantity.setValueTextField("" + proQty);
     }

     public String getProductNameEn() {
          return productNameEn;
     }

     public void setProductNameEn(String productNameEn) {
          this.productNameEn = productNameEn;
          nameEn.setValueTextField(productNameEn);
     }

     public String getProductNameKh() {
          return productNameKh;
     }

     public void setProductNameKh(String productNameKh) {
          this.productNameKh = productNameKh;
     }

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
     }

     public String getProductBarcode() {
          return productBarcode;
     }

     public void setProductBarcode(String productBarcode) {
          this.productBarcode = productBarcode;
          barcode.setValueTextField(productBarcode);
     }

     public String getProductPrice() {
          return productPrice;
     }

     public void setProductPrice(String productPrice) {
          this.productPrice = productPrice;
          price.setValueTextField(productPrice);
     }

     public String getProductCost() {
          return productCost;
     }

     public void setProductCost(String productCost) {
          this.productCost = productCost;
          cost.setValueTextField(productCost);
     }

     public String getProductWeight() {
          return productWeight;
     }

     public void setProductWeight(String productWeight) {
          this.productWeight = productWeight;
          weight.setValueTextField(productWeight);
     }

     public String getProductNote() {
          return productNote;
     }

     public void setProductNote(String productNote) {
          this.productNote = productNote;
          note.setValueTextField(productNote);
     }

     public String getProductDiscount() {
          return productDiscount;
     }

     public void setProductDiscount(String productDiscount) {
          this.productDiscount = productDiscount;
          discount.setValueTextField(productDiscount);
     }

     public int getBrandId() {
          return brandId;
     }

     public void setBrandId(int brandId) {
          this.brandId = brandId;
//        brand.setToLastItem();
     }

     public int getCategoryId() {
          return categoryId;
     }

     public void setCategoryId(int categoryId) {
          this.categoryId = categoryId;
     }

     public int getTaxId() {
          return taxId;
     }

     public void setTaxId(int taxId) {
          this.taxId = taxId;
     }

     public String getProductStatus() {
          return productStatus;
     }

     public void setProductStatus(String productStatus) {
          this.productStatus = productStatus;
     }

     public void setIndexToBrand(int id) {
          brand.setToLastItem(id);
     }

     public void setIndexToCategory(int id) {
          category.setToLastItem(id);
     }

     public void setIndexToTax(int id) {
          tax.setToLastItem(id);
     }

//     public void setIndexToStatus(String id) {
//          status.setToLastItem(id);
//     }
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
                         CategoryModel categ = new CategoryModel(
                              obj.getInt("id"),
                              obj.getString("catNameEn")
                         );
                         categoryModel.add(categ);

                         int idCategory = categoryModel.get(i).getCategoryId();
                         String categoryName = categoryModel.get(i).getCategoryName();

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

//     //Set Combo box status
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
     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          nameEn.initEvent(btnevent);
          nameKh.initEvent(btnevent);
          barcode.initEvent(btnevent);
          price.initEvent(btnevent);
          cost.initEvent(btnevent);
          weight.initEvent(btnevent);
          note.initEvent(btnevent);
          discount.initEvent(btnevent);
          quantity.initEvent(btnevent);
     }

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

     //Set Combo box brand
     private void addComboBrand() {
          try {
               HashMap<String, String> mapBrand = new HashMap<>();
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

                         mapBrand.put(brandName, "" + idBrand);

                    }
                    brand.setMap(mapBrand);
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

        panelEditProduct = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        label1 = new Components.Label();
        nameKh = new Components.TextField();
        nameEn = new Components.TextField();
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
        label12 = new Components.Label();
        quantity = new Components.TextField();
        lbPicture = new javax.swing.JLabel();
        label7 = new Components.Label();
        button1 = new Button.Button();
        label8 = new Components.Label();
        lbFlag = new javax.swing.JLabel();
        button2 = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Edit Product");

        label1.setLabelName("Product Name ");

        nameKh.setLabelTextField("Product Name Kh");

        nameEn.setLabelTextField("Product Name");

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

        label12.setLabelName("Import Quantity");

        quantity.setLabelTextField("Import Quantity");

        lbPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        label7.setLabelName("Product Image");

        button1.setBackground(new java.awt.Color(47, 152, 70));
        button1.setButtonName("Browse to Upload");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout panelEditProductLayout = new javax.swing.GroupLayout(panelEditProduct);
        panelEditProduct.setLayout(panelEditProductLayout);
        panelEditProductLayout.setHorizontalGroup(
            panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelEditProductLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditProductLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nameEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEditProductLayout.createSequentialGroup()
                                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelEditProductLayout.createSequentialGroup()
                                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelEditProductLayout.createSequentialGroup()
                                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(brand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 23, Short.MAX_VALUE))
                            .addGroup(panelEditProductLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelEditProductLayout.createSequentialGroup()
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        panelEditProductLayout.setVerticalGroup(
            panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditProductLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameEn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(weight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEditProductLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEditProductLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(panelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

//         System.out.println("qty : " + quantity.getValueTextField());
//         System.out.println("proNameEn : " + nameEn.getValueTextField());
//         System.out.println("nameKh : " + nameKh.getValueTextField());
//         System.out.println("barcode : " + barcode.getValueTextField());
//         System.out.println("price : " + price.getValueTextField());
//         System.out.println("cost : " + cost.getValueTextField());
//         System.out.println("weight : " + weight.getValueTextField());
//         System.out.println("brandId : " + brandId);
//         System.out.println("categoryId : " + categoryId);
//         System.out.println("taxId : " + taxId);
//         System.out.println("product Status : " + productStatus);
//         System.out.println("Note : " + note.getValueTextField());
//         System.out.println("Discount : " + discount.getValueTextField());
         String proName = nameEn.getValueTextField();
         String proNameKh = nameKh.getValueTextField();
         String proBarcode = barcode.getValueTextField();
         String proPrice = price.getValueTextField();
         String proCost = cost.getValueTextField();
         String proWeight = weight.getValueTextField();
         String proNote = note.getValueTextField();
         String proDiscount = discount.getValueTextField();
         String proQty = quantity.getValueTextField();

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
         if ("" + categoryId == null) {
              JOptionPane.showMessageDialog(this, "Please select a category!");
              return;
         }
         if ("" + categoryId == null) {
              JOptionPane.showMessageDialog(this, "Please select a category!");
              return;
         }
         if ("" + brandId == null) {
              JOptionPane.showMessageDialog(this, "Please select a brand!");
              return;
         }
         if ("" + taxId == null) {
              JOptionPane.showMessageDialog(this, "Please select a tax!");
              return;
         }
//         if (productStatus == null) {
//              JOptionPane.showMessageDialog(this, "Please select a product status!");
//              return;
//         }

         String url = new JavaBaseUrl().getBaseUrl() + JavaRoute.addProduct + "/" + productId;

         proPrice = proPrice.replace(",", "");
         proCost = proCost.replace(",", "");

         OkHttpClient client = new OkHttpClient();
         // File to upload

         // Request body
         MultipartBody.Builder requestBody = new MultipartBody.Builder()
              .setType(MultipartBody.FORM)
              .addFormDataPart("catId", "" + categoryId)
              .addFormDataPart("proNameEn", proName)
              .addFormDataPart("cost", proCost)
              .addFormDataPart("price", proPrice)
              .addFormDataPart("barcode", proBarcode)
              .addFormDataPart("brandId", "" + brandId)
              .addFormDataPart("createBy", JavaConstant.cashierId + "")
              .addFormDataPart("taxId", "" + taxId)
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

         try {
              Response response = client.newCall(request).execute();
              if (response.isSuccessful()) {

                   jdLogin.onClickCategory("new items", jdLogin.getCatId());
                   pCategory.getComponents()[1].setBackground(WindowColor.black);

                   ListProduct list = new ListProduct(new JFrame(), true);
                   listGetProduct.removeAll();
                   listGetProduct.revalidate();
                   listGetProduct.repaint();
                   list.getProduct(listGetProduct);

                   dispose();
              }
              // Do something with the response.
         } catch (IOException e) {
              System.out.println("err = " + e);
         }


    }//GEN-LAST:event_buttonSaveMouseClicked

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
         try {
              path = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(path, lbPicture, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_button1MouseClicked

    private void button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseClicked
         try {
              pathFlag = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(pathFlag, lbFlag, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_button2MouseClicked

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
               java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    EditProduct dialog = new EditProduct(new javax.swing.JFrame(), true);
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
    private Button.Button button1;
    private Button.Button button2;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
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
    private Components.TextField nameEn;
    private Components.TextField nameKh;
    private Components.TextField note;
    private javax.swing.JPanel panelEditProduct;
    private Components.TextField price;
    private Components.TextField quantity;
    private Components.ComboBox tax;
    private Components.TextField weight;
    // End of variables declaration//GEN-END:variables
}
