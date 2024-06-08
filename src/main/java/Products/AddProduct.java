package Products;

import Color.WindowColor;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.CustomerType.CustomerTypeModel;
import Model.combobox.BrandModel;
import Model.combobox.CategoryModel;
import Model.combobox.TaxModel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddProduct extends javax.swing.JDialog {

    private HashMap<String, String> map = new HashMap<>();
    private String brandId;
    private String categoryId;
    private String statusProduct;
    private String taxId;
    
    public AddProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelAddProduct.setBackground(WindowColor.mediumGreen);
        event();
        
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
        ButtonEvent eventtt = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                statusProduct = key;
            }
        };
        status.initEvent(eventtt);
        addComboStatus();
        
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
          note.initEvent(btnevent);
          discount.initEvent(btnevent);
     }
    
    
    //Set Combo box brand
    private void addComboBrand() {
          try {
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
    
    
    //Set Combo box status
    private void addComboStatus() {
          try {
              map.put("In Stock", "In Stock");
              map.put("Out Stock", "Out Stock");
              status.setMap(map);
              
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }
     
    
    
    //Set Combo box tax
    private void addComboTax() {
          try {
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
        lbCustomerId = new Components.Label();
        productNameKh = new Components.TextField();
        productName = new Components.TextField();
        lbCustomerId1 = new Components.Label();
        lbCustomerId2 = new Components.Label();
        barcode = new Components.TextField();
        lbCustomerId3 = new Components.Label();
        cost = new Components.TextField();
        lbCustomerId4 = new Components.Label();
        price = new Components.TextField();
        lbCustomerId5 = new Components.Label();
        weight = new Components.TextField();
        lbCustomerId6 = new Components.Label();
        discount = new Components.TextField();
        lbCustomerId7 = new Components.Label();
        tax = new Components.ComboBox();
        lbCustomerId8 = new Components.Label();
        brand = new Components.ComboBox();
        lbCustomerId9 = new Components.Label();
        category = new Components.ComboBox();
        lbCustomerId10 = new Components.Label();
        note = new Components.TextField();
        lbCustomerId12 = new Components.Label();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbCustomerId11 = new Components.Label();
        status = new Components.ComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        lbPicture = new javax.swing.JLabel();
        button1 = new Button.Button();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Add Product");

        lbCustomerId.setLabelName("Product Name ");

        productNameKh.setLabelTextField("Product Name Kh");

        productName.setLabelTextField("Product Name");

        lbCustomerId1.setLabelName("Product Name Kh");

        lbCustomerId2.setLabelName("Barcode");

        barcode.setLabelTextField("Barcode");

        lbCustomerId3.setLabelName("Cost");

        cost.setLabelTextField("$ 0.00");

        lbCustomerId4.setLabelName("Price");

        price.setLabelTextField("$ 0.00");

        lbCustomerId5.setLabelName("Weight");

        weight.setLabelTextField("Weight");

        lbCustomerId6.setLabelName("Discount");

        discount.setLabelTextField("0%");

        lbCustomerId7.setLabelName("Note");

        lbCustomerId8.setLabelName("Brand");

        lbCustomerId9.setLabelName("Category");

        lbCustomerId10.setLabelName("Status");

        note.setLabelTextField("Note");

        lbCustomerId12.setLabelName("Product Image");

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

        lbCustomerId11.setLabelName("Tax");

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

        lbPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        button1.setBackground(new java.awt.Color(47, 152, 70));
        button1.setButtonName("Browse to Upload");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");

        javax.swing.GroupLayout panelAddProductLayout = new javax.swing.GroupLayout(panelAddProduct);
        panelAddProduct.setLayout(panelAddProductLayout);
        panelAddProductLayout.setHorizontalGroup(
            panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAddProductLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddProductLayout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(panelAddProductLayout.createSequentialGroup()
                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbCustomerId5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbCustomerId1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerId12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(lbCustomerId8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddProductLayout.createSequentialGroup()
                                .addComponent(lbCustomerId10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbCustomerId7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerId6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(brand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        panelAddProductLayout.setVerticalGroup(
            panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddProductLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCustomerId8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCustomerId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productNameKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCustomerId9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCustomerId1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCustomerId2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCustomerId11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbCustomerId4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCustomerId10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCustomerId3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCustomerId7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCustomerId5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(weight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCustomerId6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCustomerId12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddProductLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(panelAddProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        
        try{
            
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
              if (statusProduct == null) {
                   JOptionPane.showMessageDialog(this, "Please select a product status!");
                   return;
              }
              
            String url = new JavaBaseUrl().getBaseUrl() + JavaRoute.addProduct;
            System.out.println("url ; " + url);
            proPrice = proPrice.replace(",", "");
            proCost = proCost.replace(",", "");
            
            OkHttpClient client = new OkHttpClient();

//            System.out.println("categoryId :" + categoryId);
//            System.out.println("proNameKh :" + proNameKh);
//            System.out.println("proName :" + proName);
//            System.out.println("proCost :" + proCost);
//            System.out.println("proPrice :" + proPrice);
//            System.out.println("proWeight :" + proWeight);
//            System.out.println("proBarcode :" + proBarcode);
//            System.out.println("discount :" + proDiscount);
//            System.out.println("statusProduct :" + statusProduct);
//            System.out.println("proNote :" + proNote);
//            System.out.println("brandId :" + brandId);
//            System.out.println("taxId :" + taxId);
//            System.out.println("JavaConstant.cashierId :" + JavaConstant.cashierId);
            
            RequestBody formBody = new FormBody.Builder()
                    .add("catId", categoryId)
                    .add("proNameKh", proNameKh)
                    .add("proNameEn", proName)
                    .add("cost", proCost)
                    .add("price", proPrice)
                    .add("weight", proWeight)
                    .add("barcode", proBarcode)
                    .add("discount", proDiscount)
                    .add("productStatus", statusProduct)
                    .add("note", proNote)
                    .add("brandId", brandId)
                    .add("createBy", ""+JavaConstant.cashierId)
                    .add("taxId", taxId)
                    .build();
            
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .header("Authorization", "Bearer " + JavaConstant.token)
                    .build();
            
            try {
                Response response = client.newCall(request).execute();
                
                System.out.println("response : " + response);
                if(response.isSuccessful()){
                    dispose();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File f = jf.getSelectedFile();
        String path = f.getAbsolutePath();
        
        try {
            BufferedImage bi  = ImageIO.read(new File(path) );
            Image img = bi.getScaledInstance(135, 135, Image.SCALE_SMOOTH);
            ImageIcon li = new ImageIcon(img);
            lbPicture.setIcon(li);
        } catch (IOException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button1MouseClicked

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
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
    private Button.Button button1;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbCustomerId;
    private Components.Label lbCustomerId1;
    private Components.Label lbCustomerId10;
    private Components.Label lbCustomerId11;
    private Components.Label lbCustomerId12;
    private Components.Label lbCustomerId2;
    private Components.Label lbCustomerId3;
    private Components.Label lbCustomerId4;
    private Components.Label lbCustomerId5;
    private Components.Label lbCustomerId6;
    private Components.Label lbCustomerId7;
    private Components.Label lbCustomerId8;
    private Components.Label lbCustomerId9;
    private javax.swing.JLabel lbPicture;
    private Components.TextField note;
    private javax.swing.JPanel panelAddProduct;
    private Components.TextField price;
    private Components.TextField productName;
    private Components.TextField productNameKh;
    private Components.ComboBox status;
    private Components.ComboBox tax;
    private Components.TextField weight;
    // End of variables declaration//GEN-END:variables
}
