package Products;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import LoginAndLogoutForm.LoginFormJdailog;
import feature.export_product_format_excel.ExportProductFormatEXCEL;
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
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class InsertProduct extends javax.swing.JDialog {

     private Integer id;
     private String status;

     private String brandId;
     private String taxId;
     private String uomId;
     private String attributeId;
     private String subCatId;
     private String vendorId;
     private String countryId;
     private String statusId;
     private String warehouseId = "-1";
     private String rangeId = "-1";
     private String slotId = "-1";
     private String proImageName;
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
          txtBarcode.requestFocus();
          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));

          Font khmerFont = new Font("Khmer OS", Font.PLAIN, 12);
          txtProductNameKh.setFont(khmerFont);
          groupEventCmd();
          groupCalculation();
          txtCost.setValidateAmount();
          txtPrice.setValidateAmount();
          JavaConstant.setPointer(browse);
          txtMargin.setDisable();

//          try {
//               JavaConstant.coverImage(JavaBaseUrl.baseUrlDefaultImage, lbPicture, 150, 135);
//          } catch (IOException ex) {
//               Logger.getLogger(InsertStaff.class.getName()).log(Level.SEVERE, null, ex);
//          }
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
          String _proImageName,
          String _warehouseId,
          String _rangeId,
          String _slotId
     ) {

          System.out.println("_warehouseId : " + _warehouseId);

          txtBarcode.setText(_barcode);
          cmbVendorName.setSelectedItem(_vendorId);
          cmbBrand.setSelectedItem(_brandId);
          cmbSubCategory.setSelectedItem(_subCatId);
          txtProductName.setText(_proName);
          System.err.println("_proNameKh : " + _proNameKh);
          if (_proNameKh != null && !_proNameKh.isEmpty()) {
               System.err.println("_proNameKhdddddddddddddddddddd : " + _proNameKh);
               txtProductNameKh.setText(_proNameKh);
          }

          txtCost.setText(_cost);
          txtPrice.setText(_price);
          txtMargin.setText(_margin);
          txtMargin.setDisable();
          cmbAttribute.setSelectedItem(_attributeId);

          if (_choice != null && !_choice.isEmpty()) {
               txtChoiceValue.setText(_choice);
          }

          cmbUom.setSelectedItem(_uomId);
          cmbStatus.setSelectedItem(_statusId);
          cmbCountry.setSelectedItem(_countryId);
          cmbTax.setSelectedItem(_taxId);

//        cmdWharehouse.setSelectedItem(_warehouseId);
//        cmdRange.setSelectedItem(_rangeId);
//        cmdSlot.setSelectedItem(_slotId);
          if (!_warehouseId.equals("-1")) {
               cmdWharehouse.setSelectedItem(_warehouseId);
          }
          if (!_rangeId.equals("-1")) {
               cmdRange.setSelectedItem(_rangeId);
          }
          if (!_slotId.equals("-1")) {
               cmdSlot.setSelectedItem(_slotId);
          }

          proImageName = _proImageName;

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
                    cal();
               }
          };
          txtCost.initEvent(eventCost);

          ButtonEvent eventPrice = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    cal();
               }
          };
          txtPrice.initEvent(eventPrice);

     }

     private void cal() {

          String _price = txtPrice.getValueTextField();
          String _cost = txtCost.getValueTextField();

          //System.out.println("_price : " + _price);
          //System.out.println("_cost : " + _cost);
          if ( _price != null && _price.equals("0") && _cost != null && _cost.equals("0")) {
               txtMargin.setText("0");
               return;
          }

          if (_cost != null
               && _price != null
               && !_price.isEmpty()
               && !_cost.isEmpty()) {

               double _pPrice = JavaConstant.getReplace(_price);
               double _cCost = JavaConstant.getReplace(_cost);

               double result = _pPrice - _cCost;
               if (result < 0) {
                    txtMargin.setText(String.valueOf(0));
               } else {
                    result = (result * 100) / _pPrice;
                    String lastValue = String.format("%.2f", result) + "%";
                    txtMargin.setText(String.valueOf(lastValue));
               }
          } else {
               txtMargin.setText(String.valueOf(0));
          }
     }

     private void groupEventCmd() {
          cmdVendor();
          cmdBrand();
          cmdSubCategory();
          cmdAttribute();
          cmdUom();
          cmdStatus();
          cmdCountry();
          cmdTax();
          cmdWarehouse();
     }

     private void cmdVendor() {
          JavaComboBoxSelection.addComboBox(cmbVendorName,
               JavaRoute.vendor,
               "vendorName",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    vendorId = id;
               }
          };
          cmbVendorName.initEvent(event);
     }

     private void cmdBrand() {
          JavaComboBoxSelection.addComboBox(cmbBrand,
               JavaRoute.brand,
               "brandNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    brandId = id;
               }
          };
          cmbBrand.initEvent(event);
     }

     private void cmdSubCategory() {
          JavaComboBoxSelection.addComboBox(cmbSubCategory,
               JavaRoute.subcategory,
               "catNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    subCatId = id;
               }
          };
          cmbSubCategory.initEvent(event);
     }

     private void cmdAttribute() {
          JavaComboBoxSelection.addComboBox(cmbAttribute,
               JavaRoute.attribute,
               "attrNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    attributeId = id;
               }
          };
          cmbAttribute.initEvent(event);
     }

     private void cmdUom() {
          JavaComboBoxSelection.addComboBox(cmbUom,
               JavaRoute.uom,
               "uomNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    uomId = id;
               }
          };
          cmbUom.initEvent(event);
     }

     private void cmdStatus() {
          JavaComboBoxSelection.addComboBox(cmbStatus,
               JavaRoute.status,
               "statusName",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    statusId = id;
               }
          };
          cmbStatus.initEvent(event);
     }

     private void cmdCountry() {
          JavaComboBoxSelection.addComboBox(cmbCountry,
               JavaRoute.country,
               "countryName",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    countryId = id;
               }
          };
          cmbCountry.initEvent(event);
     }

     private void cmdTax() {
          JavaComboBoxSelection.addComboBox(cmbTax,
               JavaRoute.tax,
               "tax_name",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    taxId = id;
               }
          };
          cmbTax.initEvent(event);
     }

     private void cmdWarehouse() {
          JavaComboBoxSelection.addComboBox(cmdWharehouse,
               JavaRoute.warehouse,
               "warehouseNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    warehouseId = id;
                    cmdRange(id);
               }
          };
          cmdWharehouse.initEvent(event);
     }

     private void cmdRange(String warehouseId) {
          JavaComboBoxSelection.addComboBox(cmdRange,
               JavaRoute.ranges + "/readByWarehouseId/" + warehouseId,
               "rangeNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    rangeId = id;
                    cmdSlot(id);
               }
          };
          cmdRange.initEvent(event);
     }

     private void cmdSlot(String rangeId) {
          JavaComboBoxSelection.addComboBox(cmdSlot,
               JavaRoute.slots + "/readByRangeId/" + rangeId,
               "slotNameEn",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    slotId = id;
               }
          };
          cmdSlot.initEvent(event);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          labelPopUpTitle1 = new Components.LabelPopUpTitle();
          label7 = new Components.Label();
          lbPicture = new javax.swing.JLabel();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          btnExport = new Button.Button();
          browse = new javax.swing.JLabel();
          txtBarcode = new FormComponent.JavaTextField();
          cmbSubCategory = new FormComponent.combobox.JavaCombobox();
          cmbVendorName = new FormComponent.combobox.JavaCombobox();
          cmbBrand = new FormComponent.combobox.JavaCombobox();
          txtProductName = new FormComponent.JavaTextField();
          txtProductNameKh = new FormComponent.JavaTextField();
          txtCost = new FormComponent.JavaTextField();
          cmbAttribute = new FormComponent.combobox.JavaCombobox();
          cmbCountry = new FormComponent.combobox.JavaCombobox();
          cmdWharehouse = new FormComponent.combobox.JavaCombobox();
          txtPrice = new FormComponent.JavaTextField();
          txtMargin = new FormComponent.JavaTextField();
          txtChoiceValue = new FormComponent.JavaTextField();
          cmbStatus = new FormComponent.combobox.JavaCombobox();
          cmdRange = new FormComponent.combobox.JavaCombobox();
          cmbUom = new FormComponent.combobox.JavaCombobox();
          cmbTax = new FormComponent.combobox.JavaCombobox();
          cmdSlot = new FormComponent.combobox.JavaCombobox();
          btnImport = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle1.setLabelTitle("Add Product");

          label7.setLabelName("Image");

          lbPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productImage/default.jpg"))); // NOI18N
          lbPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          buttonCancel.setButtonName("Close");
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

          btnExport.setBackground(new java.awt.Color(47, 152, 70));
          btnExport.setButtonName("Export");
          btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnExportMouseClicked(evt);
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

          txtBarcode.setLabelName("Barcode *");
          txtBarcode.setPlaceHolder("Barcode");

          cmbSubCategory.setLabelName("Sub Category *");

          cmbVendorName.setLabelName("Vendor Name *");

          cmbBrand.setLabelName("Brand *");
          cmbBrand.setName(""); // NOI18N

          txtProductName.setLabelName("Product Name *");
          txtProductName.setPlaceHolder("Product Name");

          txtProductNameKh.setLabelName("Product Name (KH)");
          txtProductNameKh.setPlaceHolder("Product Name (KH)");

          txtCost.setLabelName("Cost *");
          txtCost.setPlaceHolder("$ 0.00");

          cmbAttribute.setLabelName("Attribute *");

          cmbCountry.setLabelName("Country *");
          cmbCountry.setName(""); // NOI18N

          cmdWharehouse.setLabelName("Warehouse ");

          txtPrice.setLabelName("Sale Price *");
          txtPrice.setPlaceHolder("$ 0.00");

          txtMargin.setLabelName("Margin");
          txtMargin.setPlaceHolder("Margin");

          txtChoiceValue.setLabelName("Choice Value *");
          txtChoiceValue.setPlaceHolder("Choice Value");

          cmbStatus.setLabelName("Status *");

          cmdRange.setLabelName("Range");

          cmbUom.setLabelName("UoM *");

          cmbTax.setLabelName("Tax *");

          cmdSlot.setLabelName("Slot");

          btnImport.setBackground(new java.awt.Color(47, 152, 70));
          btnImport.setButtonName("Import");
          btnImport.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnImportMouseClicked(evt);
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
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 597, Short.MAX_VALUE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(20, 20, 20))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(cmbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addContainerGap(20, Short.MAX_VALUE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProductNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtChoiceValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbUom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(cmdWharehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(0, 0, Short.MAX_VALUE))))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(cmbSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(txtProductNameKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(txtCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(txtMargin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(cmbUom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(cmbAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(txtChoiceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmbTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(cmdWharehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmdRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmdSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(20, 20, 20))))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
         reloadList();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         barcode = txtBarcode.getValueTextField();
         productName = txtProductName.getValueTextField();
         productNameKh = txtProductNameKh.getValueTextField();
         cost = txtCost.getValueTextField();
         price = txtPrice.getValueTextField();
         margin = txtMargin.getValueTextField();
         choiceValue = txtChoiceValue.getValueTextField();

         saveFunction(); // save
    }//GEN-LAST:event_buttonSaveMouseClicked

     private void saveFunction() {
          try {

               boolean isCheck = JavaValidation.checkValidation(jPanel1);

               if (isCheck) {
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

                    json.put("warehouseId", warehouseId.equals("-1") ? JSONObject.NULL : warehouseId);
                    json.put("rangeId", rangeId.equals("-1") ? JSONObject.NULL : rangeId);
                    json.put("slotId", slotId.equals("-1") ? JSONObject.NULL : slotId);

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
                                   } else {
                                        JOptionPane.showMessageDialog(this, "Save Failed!");
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
                                   } else {
                                        JOptionPane.showMessageDialog(this, "Save Failed!");
                                   }
                              } catch (Exception e) {
                                   System.out.println("erro : " + e);
                              }
                         } else {
                              if (proImageName != null) {
                                   json.put("proImageName", proImageName);
                              }
                              responseUpdateProduct(json);
                         }
                    }
               }

          } catch (Exception e) {
               System.err.println("errr -- " + e);
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
                         dispose();
                         reloadList();
                         proImageName = null;
                    }
               }
          } catch (Exception e) {
               System.out.println("erro : " + e);
          }
     }

     private void responseAddProduct(JSONObject json) {
          Response response = JavaConnection.post(JavaRoute.productV1, json);

          //System.out.println("response : " + response);
          //System.out.println("json : " + json);
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jSONObject = new JSONObject(responseData);
                    if (jSONObject.has("error")) {
                         JSONObject error = jSONObject.getJSONObject("error");
                         String reason = error.getString("reason");
                         JOptionPane.showMessageDialog(this, reason);
                    } else {
                         reloadList();
                         afterSuccess();
                    }
               }
          } catch (Exception e) {
               System.out.println("erro : " + e);
          }
     }

     private void afterSuccess() {
          dispose();

          JavaConstant.restoreDefaultCursor(this);

          txtBarcode.setValueTextField(null);
          txtProductName.setValueTextField(null);
          txtProductNameKh.setValueTextField(null);
          txtCost.setValueTextField(null);
          txtPrice.setValueTextField(null);
          txtMargin.setValueTextField(null);
          txtChoiceValue.setValueTextField(null);

          cmbVendorName.setToFirstItem();
          cmbBrand.setToFirstItem();
          cmbSubCategory.setToFirstItem();
          cmbAttribute.setToFirstItem();
          cmbUom.setToFirstItem();
          cmbStatus.setToFirstItem();
          cmbCountry.setToFirstItem();
          cmbTax.setToFirstItem();

          if (warehouseId != null) {
               cmdWharehouse.setToFirstItem();
          }
          if (rangeId != null) {
               cmdRange.setToFirstItem();
          }
          if (slotId != null) {
               cmdSlot.setToFirstItem();
          }

          lbPicture.setIcon(null);

          warehouseId = null;
          rangeId = null;
          slotId = null;
          brandId = null;
          taxId = null;
          uomId = null;
          attributeId = null;
          subCatId = null;
          vendorId = null;
          countryId = null;

          txtPrice.setValueTextField("Price");
          txtCost.setValueTextField("Cost");
          txtProductNameKh.setValueTextField("Product Name Kh");
          txtChoiceValue.setValueTextField("Choice Value");
          txtProductName.setValueTextField("Product Name");

          txtBarcode.setFocus();
          reloadList();

//          System.out.println(" add category id : " + jdLogin.getCatId());
//          jdLogin.onClickCategory("new items", jdLogin.getCatId());
//          panelCategory.getComponents()[1].setBackground(WindowColor.black);
//          panelCategory.revalidate();
//          panelCategory.repaint();
     }

     public void reloadList() {

          if (status.equals("allProduct")) {
               listProduct.getProduct(listGetProduct, true, 0);
               listProduct.setVisible(true);
          } else if (status.equals("active")) {
               listProduct.getProduct(listGetProduct, true, 1);
               listProduct.setVisible(true);
          } else if (status.equals("inActive")) {
               listProduct.getProduct(listGetProduct, true, 2);
               listProduct.setVisible(true);
          }

          // refresh list product
          listGetProduct.removeAll();
          listGetProduct.repaint();
          listGetProduct.revalidate();

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
          labelPopUpTitle1.setLabelTitle("Edit Product");
     }


    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
         ExportProductFormatEXCEL.exportProductFormatEXCEL(); // export to excel
         ListProduct.msgPrint(ExportProductFormatEXCEL.folderPaths);
    }//GEN-LAST:event_btnExportMouseClicked

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

     private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
          dispose();
          ImportFile importF = new ImportFile(new JFrame(), true);
          importF.setInsertProduct(this);
          importF.setVisible(true);
     }//GEN-LAST:event_btnImportMouseClicked

     public static void main(String args[]) {
        
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
     private Button.Button btnExport;
     private Button.Button btnImport;
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private FormComponent.combobox.JavaCombobox cmbAttribute;
     private FormComponent.combobox.JavaCombobox cmbBrand;
     private FormComponent.combobox.JavaCombobox cmbCountry;
     private FormComponent.combobox.JavaCombobox cmbStatus;
     private FormComponent.combobox.JavaCombobox cmbSubCategory;
     private FormComponent.combobox.JavaCombobox cmbTax;
     private FormComponent.combobox.JavaCombobox cmbUom;
     private FormComponent.combobox.JavaCombobox cmbVendorName;
     private FormComponent.combobox.JavaCombobox cmdRange;
     private FormComponent.combobox.JavaCombobox cmdSlot;
     private FormComponent.combobox.JavaCombobox cmdWharehouse;
     private javax.swing.JPanel jPanel1;
     private Components.Label label7;
     private Components.LabelPopUpTitle labelPopUpTitle1;
     private javax.swing.JLabel lbPicture;
     private FormComponent.JavaTextField txtBarcode;
     private FormComponent.JavaTextField txtChoiceValue;
     private FormComponent.JavaTextField txtCost;
     private FormComponent.JavaTextField txtMargin;
     private FormComponent.JavaTextField txtPrice;
     private FormComponent.JavaTextField txtProductName;
     private FormComponent.JavaTextField txtProductNameKh;
     // End of variables declaration//GEN-END:variables
}
