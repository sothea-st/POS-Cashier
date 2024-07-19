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
import Model.combobox.ComboBoxSelection;
import java.awt.Font;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class InsertProduct extends javax.swing.JDialog {

     private Integer id;

     private String brandId;
     private String taxId;
     private String uomId;
     private String attributeId;
     private String subCatId;
     private String vendorId;
     private String countryId;
     private String statusId;

     private String barcode;
     private String productName;
     private String productNameKh;
     private String cost;
     private String price;
     private String margin;
     private String choiceValue;
     private String path;
     private ListProduct listProduct;
     private JPanel listGetProduct;
     private JPanel panelProduct;
     private JPanel panelCategory;
     private LoginFormJdailog jdLogin;

     public InsertProduct(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          txtBarcode.requestFocus();
          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));

          Font khmerFont = new Font("Khmer OS", Font.PLAIN, 12);
          txtProductNameKh.setFont(khmerFont);
          groupEventCmd();
          groupCalculation();
          txtCost.setComma("comma");
          txtPrice.setComma("comma");
          JavaConstant.setPointer(browse);
     }
     double costVal = 0;
     double priceVal = 0;

     public void setEdit(
          String _barcode,
          String _vendorId,
          String _brandId,
          String _subCatId,
          String _proName,
          String _proNameKh,
          String _cost,
          String _price,
          String _margin,
          String _attributeId,
          String _choice,
          String _uomId,
          String _statusId,
          String _countryId,
          String _taxId,
          String _proImageName
     ) {
          txtBarcode.setValueTextField(_barcode);
          cmbVendorName.setToLastItem(_vendorId);
          cmbBrand.setToLastItem(_brandId);
          cmbSubCategory.setToLastItem(_subCatId);
          txtProductName.setValueTextField(_proName);
          txtProductNameKh.setValueTextField(_proNameKh);
          txtCost.setValueTextField(_cost);
          txtPrice.setValueTextField(_price);
          txtMargin.setLabelTextField(_margin);
          cmbAttribute.setToLastItem(_attributeId);
          txtChoiceValue.setValueTextField(_choice);
          cmbUom.setToLastItem(_uomId);
          cmbStatus.setToLastItem(_statusId);
          cmbCountry.setToLastItem(_countryId);
          cmbTax.setToLastItem(_taxId);

          try {
               if (_proImageName != null) {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              try {
                                   // Task to be executed
                                   if (_proImageName.contains("media/file/crm/uploadfile/")) {
                                        JavaConstant.coverImage(JavaConstant.urlImage + _proImageName, lbPicture, 125, 135);
                                   } else {
                                        JavaConstant.coverImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + _proImageName, lbPicture, 125, 135);
                                        lbPicture.repaint();
                                        lbPicture.revalidate();
                                   }
                              } catch (IOException ex) {
                                   Logger.getLogger(ActionProduct.class.getName()).log(Level.SEVERE, null, ex);
                              }
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second
               }

          } catch (Exception e) {
               System.err.println("error read image = " + e);
          }
     }

     private void groupCalculation() {

          ButtonEvent eventCost = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    String _value = txtCost.getValueTextField();
                    if (!_value.isEmpty()) {
                         costVal = Double.parseDouble(_value);
                         cal();
                    } else {
                         txtMargin.setLabelTextField(String.valueOf(0));
                    }

               }
          };
          txtCost.initEvent(eventCost);

          ButtonEvent eventPrice = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    String _value = txtPrice.getValueTextField();
                    if (!_value.isEmpty()) {
                         priceVal = Double.parseDouble(_value);
                         cal();
                    } else {
                         txtMargin.setLabelTextField(String.valueOf(0));
                    }
               }
          };
          txtPrice.initEvent(eventPrice);

     }

     private void cal() {
          double result = priceVal - costVal;
          if (result < 0) {
               txtMargin.setLabelTextField(String.valueOf(0));
          } else {
               result = (result * 100) / priceVal;
               String lastValue = String.format("%.2f", result) + "%";
               txtMargin.setLabelTextField(String.valueOf(lastValue));
          }

     }

     private void groupEventCmd() {
          //  ============== combobox brand ================
          ButtonEvent brandEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    brandId = key;
               }
          };
          cmbBrand.initEvent(brandEvent);
          // brandNameEn is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbBrand, JavaRoute.brand, "brandNameEn");
          //  ============== end ================

          //  ============== combobox tax ================
          ButtonEvent taxEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    taxId = key;
               }
          };
          cmbTax.initEvent(taxEvent);
          // tax_name is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbTax, JavaRoute.tax, "tax_name");
          //  ============== end ================

          //  ============== combobox cmbUom ================
          ButtonEvent uomEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    uomId = key;
               }
          };
          cmbUom.initEvent(uomEvent);
          // nameEn is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbUom, JavaRoute.uom, "nameEn");
          //  ============== end ================

          //  ============== combobox cmbAttribute ================
          ButtonEvent attributeEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    attributeId = key;
               }
          };
          cmbAttribute.initEvent(attributeEvent);
          // attrNameEn is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbAttribute, JavaRoute.attribute, "attrNameEn");
          //  ============== end ================

          //  ============== combobox cmbSubCategory ================
          ButtonEvent subCatEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    subCatId = key;
               }
          };
          cmbSubCategory.initEvent(subCatEvent);
          // catNameEn is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbSubCategory, JavaRoute.subcategory, "catNameEn");
          //  ============== end ================

          //  ============== combobox cmbVendorName ================
          ButtonEvent vendorEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    vendorId = key;
               }
          };
          cmbVendorName.initEvent(vendorEvent);
          // vendorName is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbVendorName, JavaRoute.vendor, "vendorName");
          //  ============== end ================

          //  ============== combobox cmbCountry ================
          ButtonEvent countryEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    countryId = key;
               }
          };
          cmbCountry.initEvent(countryEvent);
          // countryName is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbCountry, JavaRoute.country, "countryName");
          //  ============== end ================

          //  ============== combobox status ================
          ButtonEvent statusEvent = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    statusId = key;
               }
          };
          cmbStatus.initEvent(statusEvent);
          // countryName is field from response that we want data add in combo
          ComboBoxSelection.addComboBox(cmbStatus, JavaRoute.status, "statusName");
          //  ============== end ================
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtBarcode.initEvent(btnevent);
          txtProductName.initEvent(btnevent);
          txtProductNameKh.initEvent(btnevent);
          txtCost.initEvent(btnevent);
          txtPrice.initEvent(btnevent);
          txtChoiceValue.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          labelPopUpTitle1 = new Components.LabelPopUpTitle();
          label1 = new Components.Label();
          txtBarcode = new Components.TextField();
          label2 = new Components.Label();
          txtProductNameKh = new Components.TextField();
          label3 = new Components.Label();
          txtProductName = new Components.TextField();
          label9 = new Components.Label();
          cmbSubCategory = new Components.ComboBox();
          label10 = new Components.Label();
          cmbStatus = new Components.ComboBox();
          label11 = new Components.Label();
          cmbCountry = new Components.ComboBox();
          cmbVendorName = new Components.ComboBox();
          cmbBrand = new Components.ComboBox();
          label12 = new Components.Label();
          txtCost = new Components.TextField();
          label13 = new Components.Label();
          txtPrice = new Components.TextField();
          label14 = new Components.Label();
          txtMargin = new Components.UnEditableTextField();
          label15 = new Components.Label();
          label16 = new Components.Label();
          label17 = new Components.Label();
          cmbTax = new Components.ComboBox();
          label18 = new Components.Label();
          cmbAttribute = new Components.ComboBox();
          label19 = new Components.Label();
          cmbUom = new Components.ComboBox();
          txtChoiceValue = new Components.TextField();
          label7 = new Components.Label();
          lbPicture = new javax.swing.JLabel();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel14 = new javax.swing.JLabel();
          jLabel15 = new javax.swing.JLabel();
          jLabel16 = new javax.swing.JLabel();
          jLabel17 = new javax.swing.JLabel();
          jLabel18 = new javax.swing.JLabel();
          jLabel19 = new javax.swing.JLabel();
          jLabel20 = new javax.swing.JLabel();
          jLabel21 = new javax.swing.JLabel();
          jLabel22 = new javax.swing.JLabel();
          button1 = new Button.Button();
          browse = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle1.setLabelTitle("Add Product");

          label1.setLabelName("Barcode");

          txtBarcode.setLabelTextField("Barcode");

          label2.setLabelName("Vendor Name");

          txtProductNameKh.setLabelTextField("Product Name Kh");

          label3.setLabelName("Brand");

          txtProductName.setLabelTextField("Product Name");

          label9.setLabelName("Sub Category");

          label10.setLabelName("Product Name ");

          label11.setLabelName("Product Name Kh");

          label12.setLabelName("Cost");

          txtCost.setLabelTextField("Cost");

          label13.setLabelName("Sale Price");

          txtPrice.setLabelTextField("Sale Price");

          label14.setLabelName("Margin");

          label15.setLabelName("Status");

          label16.setLabelName("Country");

          label17.setLabelName("Tax");

          label18.setLabelName("Attribute");

          label19.setLabelName("UOM");

          txtChoiceValue.setLabelTextField("Choice Values");

          label7.setLabelName("Image");

          lbPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(204, 0, 0));
          jLabel11.setText("*");

          jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(204, 0, 0));
          jLabel12.setText("*");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(204, 0, 0));
          jLabel13.setText("*");

          jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(204, 0, 0));
          jLabel14.setText("*");

          jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel15.setForeground(new java.awt.Color(204, 0, 0));
          jLabel15.setText("*");

          jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel16.setForeground(new java.awt.Color(204, 0, 0));
          jLabel16.setText("*");

          jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel17.setForeground(new java.awt.Color(204, 0, 0));
          jLabel17.setText("*");

          jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel18.setForeground(new java.awt.Color(204, 0, 0));
          jLabel18.setText("*");

          jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel19.setForeground(new java.awt.Color(204, 0, 0));
          jLabel19.setText("*");

          jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel20.setForeground(new java.awt.Color(204, 0, 0));
          jLabel20.setText("*");

          jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel21.setForeground(new java.awt.Color(204, 0, 0));
          jLabel21.setText("*");

          jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel22.setForeground(new java.awt.Color(204, 0, 0));
          jLabel22.setText("*");

          button1.setBackground(new java.awt.Color(47, 152, 70));
          button1.setButtonName("Import");
          button1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    button1MouseClicked(evt);
               }
          });

          browse.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          browse.setForeground(new java.awt.Color(0, 51, 102));
          browse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          browse.setText("Browse here to Upload");
          browse.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    browseMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    browseMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    browseMouseExited(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(2, 2, 2)
                              .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(2, 2, 2)
                              .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(2, 2, 2)
                              .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(2, 2, 2)
                              .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(2, 2, 2)
                              .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(browse)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(cmbAttribute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(cmbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                       .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE))
                                                  .addGap(2, 2, 2)
                                                  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(cmbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(txtChoiceValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(35, 35, 35)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(label11, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                   .addComponent(label14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(txtProductNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(txtMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(cmbTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(cmbUom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(cmbVendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(cmbBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbSubCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addComponent(txtProductNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(txtMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                  .addComponent(label18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                                  .addComponent(cmbAttribute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                  .addComponent(label19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(txtChoiceValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addComponent(cmbUom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                  .addComponent(label17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                                            .addComponent(cmbTax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(label15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                       .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addComponent(label16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                   .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(21, 21, 21)
                              .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addContainerGap(63, Short.MAX_VALUE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(18, 18, 18))))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         barcode = txtBarcode.getValueTextField();
         productName = txtProductName.getValueTextField();
         productNameKh = txtProductNameKh.getValueTextField();
         cost = txtCost.getValueTextField();
         price = txtPrice.getValueTextField();
         margin = txtMargin.getLabelTextField();
         choiceValue = txtChoiceValue.getValueTextField();
         saveFunction(); // save
    }//GEN-LAST:event_buttonSaveMouseClicked

     private void saveFunction() {
          if (barcode == null || barcode.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Barcode can not be empty!");
               return;
          }

          if (barcode.length() != 13) {
               JOptionPane.showMessageDialog(this, "barcode must be 13 length!");
               return;
          }

          if (productName == null || productName.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Product Name can not be empty!");
               return;
          }

          if (cost == null || cost.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Cost can not be empty!");
               return;
          }

          if (price == null || price.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Price can not be empty!");
               return;
          }

          if (choiceValue == null || choiceValue.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Choice Value can not be empty!");
               return;
          }

          if (brandId == null || brandId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Brand!");
               return;
          }

          if (taxId == null || taxId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Tax!");
               return;
          }

          if (uomId == null || uomId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select UOM!");
               return;
          }

          if (attributeId == null || attributeId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Attribute!");
               return;
          }

          if (subCatId == null || subCatId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Sub Category!");
               return;
          }

          if (vendorId == null || vendorId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Vendor Name!");
               return;
          }

          if (countryId == null || countryId.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Select Country!");
               return;
          }

          JSONObject json = new JSONObject();
          json.put("subCatId", subCatId);
          json.put("proNameKh", productNameKh);
          json.put("proNameEn", productName);
          json.put("cost", cost);
          json.put("price", price);
          json.put("margin", margin);
          json.put("brandId", brandId);
          json.put("barcode", barcode);
          json.put("createBy", JavaConstant.cashierId);
          json.put("taxId", taxId);
          json.put("vendorId", vendorId);
          json.put("uomId", uomId);
          json.put("attributeId", attributeId);
          json.put("productActiveId", statusId);
          json.put("countryId", countryId);
          json.put("choices", choiceValue);

          if (id == null) { // add new
               if (path != null) {
                    Response responseImg = JavaConnection.postFile(path);
                    try {
                         if (responseImg.isSuccessful()) {
                              String fileName = responseImg.body().string();
                              JSONObject obj = new JSONObject(fileName);
                              fileName = obj.getString("fileName");
                              json.put("proImageName", fileName);
                              responseAddProduct(json);
                         }
                    } catch (Exception e) {
                         System.out.println("erro : " + e);
                    }
               } else {
                    responseAddProduct(json);
               }
          } else { // update
               if (path != null) {
                    Response responseImg = JavaConnection.postFile(path);
                    try {
                         if (responseImg.isSuccessful()) {
                              String fileName = responseImg.body().string();
                              JSONObject obj = new JSONObject(fileName);
                              fileName = obj.getString("fileName");
                              json.put("proImageName", fileName);
                              responseUpdateProduct(json);
                         }
                    } catch (Exception e) {
                         System.out.println("erro : " + e);
                    }
               } else {
                    responseUpdateProduct(json);
               }
          }

     }

     private void responseUpdateProduct(JSONObject json) {
          Response response = JavaConnection.put(JavaRoute.productV1 + "/" + id, json);
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jSONObject = new JSONObject(responseData);
                    if (jSONObject.has("error")) {
                         JSONObject error = jSONObject.getJSONObject("error");
                         String reason = error.getString("reason");
                         JOptionPane.showMessageDialog(this, reason);
                    } else {
//                         jdLogin.onClickCategory("new items", jdLogin.getCatId());
//                         panelCategory.getComponents()[1].setBackground(WindowColor.black);
                         dispose();
                         listProduct.getProduct(listGetProduct,true);
                    }
               }
          } catch (Exception e) {
               System.out.println("erro : " + e);
          }
     }

     private void responseAddProduct(JSONObject json) {
          Response response = JavaConnection.post(JavaRoute.productV1, json);
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jSONObject = new JSONObject(responseData);
                    if (jSONObject.has("error")) {
                         JSONObject error = jSONObject.getJSONObject("error");
                         String reason = error.getString("reason");
                         JOptionPane.showMessageDialog(this, reason);
                    } else {
                         afterSuccess();
                    }
               }
          } catch (Exception e) {
               System.out.println("erro : " + e);
          }
     }

     private void afterSuccess() {
          JavaConstant.restoreDefaultCursor(this);

          txtBarcode.setValueTextField(null);
          txtProductName.setValueTextField(null);
          txtProductNameKh.setValueTextField(null);
          txtCost.setValueTextField(null);
          txtPrice.setValueTextField(null);
          txtMargin.setLabelTextField(null);
          txtChoiceValue.setValueTextField(null);

          cmbVendorName.setToFirstItem();
          cmbBrand.setToFirstItem();
          cmbSubCategory.setToFirstItem();
          cmbAttribute.setToFirstItem();
          cmbUom.setToFirstItem();
          cmbStatus.setToFirstItem();
          cmbCountry.setToFirstItem();
          cmbTax.setToFirstItem();

          lbPicture.setIcon(null);

          brandId = null;
          taxId = null;
          uomId = null;
          attributeId = null;
          subCatId = null;
          vendorId = null;
          countryId = null;

          txtPrice.setLabelTextField("Price");
          txtCost.setLabelTextField("Cost");
          txtProductNameKh.setLabelTextField("Product Name Kh");
          txtChoiceValue.setLabelTextField("Choice Value");
          txtProductName.setLabelTextField("Product Name");

          txtBarcode.setFocus();
          reloadList();
//          System.out.println(" add category id : " + jdLogin.getCatId());
//          jdLogin.onClickCategory("new items", jdLogin.getCatId());
//          panelCategory.getComponents()[1].setBackground(WindowColor.black);
//          panelCategory.revalidate();
//          panelCategory.repaint();
     }

     public void reloadList() {
          // refresh list product
          listGetProduct.removeAll();
          listGetProduct.repaint();
          listGetProduct.revalidate();
          listProduct.getProduct(listGetProduct,true);
     }

     public ListProduct getListProduct() {
          return listProduct;
     }

     public void setListProduct(ListProduct listProduct) {
          this.listProduct = listProduct;
     }

     public JPanel getListGetProduct() {
          return listGetProduct;
     }

     public void setListGetProduct(JPanel listGetProduct) {
          this.listGetProduct = listGetProduct;
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }


    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
         dispose();
         ImportFile importF = new ImportFile(new JFrame(), true);
         importF.setInsertProduct(this);
         importF.setVisible(true);

    }//GEN-LAST:event_button1MouseClicked

    private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
         try {
              path = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(path, lbPicture, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(AddProduct.class
                   .getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_browseMouseClicked

    private void browseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseEntered
         browse.setForeground(WindowColor.light_Blue);
         browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.light_Blue));
    }//GEN-LAST:event_browseMouseEntered

    private void browseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseExited
         browse.setForeground(WindowColor.darkBlue);
         browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
    }//GEN-LAST:event_browseMouseExited

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
               java.util.logging.Logger.getLogger(InsertProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(InsertProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(InsertProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(InsertProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    InsertProduct dialog = new InsertProduct(new javax.swing.JFrame(), true);
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
     private javax.swing.JLabel browse;
     private Button.Button button1;
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Components.ComboBox cmbAttribute;
     private Components.ComboBox cmbBrand;
     private Components.ComboBox cmbCountry;
     private Components.ComboBox cmbStatus;
     private Components.ComboBox cmbSubCategory;
     private Components.ComboBox cmbTax;
     private Components.ComboBox cmbUom;
     private Components.ComboBox cmbVendorName;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel15;
     private javax.swing.JLabel jLabel16;
     private javax.swing.JLabel jLabel17;
     private javax.swing.JLabel jLabel18;
     private javax.swing.JLabel jLabel19;
     private javax.swing.JLabel jLabel20;
     private javax.swing.JLabel jLabel21;
     private javax.swing.JLabel jLabel22;
     private javax.swing.JPanel jPanel1;
     private Components.Label label1;
     private Components.Label label10;
     private Components.Label label11;
     private Components.Label label12;
     private Components.Label label13;
     private Components.Label label14;
     private Components.Label label15;
     private Components.Label label16;
     private Components.Label label17;
     private Components.Label label18;
     private Components.Label label19;
     private Components.Label label2;
     private Components.Label label3;
     private Components.Label label7;
     private Components.Label label9;
     private Components.LabelPopUpTitle labelPopUpTitle1;
     private javax.swing.JLabel lbPicture;
     private Components.TextField txtBarcode;
     private Components.TextField txtChoiceValue;
     private Components.TextField txtCost;
     private Components.UnEditableTextField txtMargin;
     private Components.TextField txtPrice;
     private Components.TextField txtProductName;
     private Components.TextField txtProductNameKh;
     // End of variables declaration//GEN-END:variables
}
