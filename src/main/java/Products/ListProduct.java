package Products;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionSearchProductController.ActionSearchProd;
import CustomeUI.CustomScrollBarUI;

import Event.ButtonEvent;
import Fonts.WindowFonts;
import LoginAndLogoutForm.LoginFormJdailog;

import Model.PackageProduct.ProductModel;
import Model.ProductModel.ProductDataModel;
import Model.ProductModel.ProductSuccessData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;
import pdf.PrintListPDF;
import pdf.PrintPanelToPDF;
import pdf.PrintToCSV;
import pdf.PrintToExcel;

public class ListProduct extends javax.swing.JDialog {

     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     private String searchValue;
     private int id;

     private JPanel panelProduct;
     private JPanel panelCategory;
     private LoginFormJdailog jdLogin;

     ArrayList<ProductModel> listProduct = new ArrayList<>();

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public LoginFormJdailog getJdLogin() {
          return jdLogin;
     }

     public void setJdLogin(LoginFormJdailog jdLogin) {
          this.jdLogin = jdLogin;
     }

     public JPanel getPanelCategory() {
          return panelCategory;
     }

     public void setPanelCategory(JPanel panelCategory) {
          this.panelCategory = panelCategory;
     }

     public ListProduct(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setBackground();
          panelListProduct.setBackground(WindowColor.mediumGreen);
          header.setBackground(WindowColor.darkGreen);
          getProduct(listGetProduct);
          eventSearchProduct();
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          searchField.setFocus();
          // custome scrollbar ui
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
//        setTitle("Stock");  

          JavaConstant.addTitleAndLogo(this, "Product");
               
     }

     void setBackground() {
          header.setBackground(WindowColor.darkGreen);
     }

     public void getProduct(JPanel jpanelData) {
          try {
               Response response = JavaConnection.get(JavaRoute.product + "?limit=0&perPage=200&page=0");
              
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductSuccessData data = objMap.readValue(responseData, ProductSuccessData.class);
                    ProductDataModel[] listData = data.getData();
                    assignProduct(listData, jpanelData);
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void assignProduct(ProductDataModel[] listData, JPanel listGetProduct) {
          listProduct = new ArrayList<>();

          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               ProductModel product = new ProductModel(
                    obj.getID(),
                    obj.getCatID(),
                    obj.getFlag(),
                    obj.getWeight(),
                    obj.getCost(),
                    obj.getProImageName(),
                    obj.getPrice(),
                    obj.getBarcode(),
                    obj.getProNameKh(),
                    obj.getProNameEn(),
                    obj.getProductStatus(),
                    obj.getDiscount(),
                    obj.getQty(),
                    obj.getDiscountType()
               );
               listProduct.add(product);
          }
          appendProduct(listProduct, listGetProduct);
     }

     //Append Product into list
     void appendProduct(ArrayList<ProductModel> listProduct, JPanel listGetProduct) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetProduct.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (int i = 0; i < listProduct.size(); i++) {
               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
               gbc.anchor = gbc.NORTH;
               x++;
               if (x == 1) {
                    x = 0;
                    y++;
               }

               var listData = listProduct.get(i);
               Products.GetProduct prod = new Products.GetProduct();

               ButtonEvent events = new ButtonEvent() {

                    @Override
                    public void onSelect(String Key) {  // event edit
                         EditProduct edit = new EditProduct(new JFrame(), true);
                         edit.setIconImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "bgwhite.jpg")).getImage());

                         edit.setPlProduct(panelProduct);
                         edit.setpCategory(panelCategory);
                         edit.setJdLogin(jdLogin);

                         try {
                              Response response = JavaConnection.get(JavaRoute.product + "/" + listData.getId());
                              String responseData = response.body().string();
                              ObjectMapper objMap = new ObjectMapper();
                              DataSuccessDetail data = objMap.readValue(responseData, DataSuccessDetail.class);
                              ListDetailProduct listproduct = data.getData();

                              edit.setProductId(listproduct.getID());
                              edit.setProductNameEn(listproduct.getProNameEn());
                              edit.setProductNameKh(listproduct.getProNameKh());
                              edit.setProductBarcode(listproduct.getBarcode());
                              edit.setProductPrice("" + listproduct.getPrice());
                              edit.setProductCost("" + listproduct.getCost());
                              edit.setProductDiscount("" + listproduct.getDiscount());
                              if (listproduct.getWeight() != null) {
                                   edit.setProductWeight(listproduct.getWeight());
                              }
                              if (listproduct.getNote() != null) {
                                   edit.setProductNote(listproduct.getNote());
                              }
                              edit.setIndexToBrand(listproduct.getBrandID());
                              edit.setIndexToCategory(listproduct.getCatID());
                              edit.setIndexToTax(listproduct.getTaxID());
//                              edit.setIndexToStatus(listproduct.getProductStatus());
                              edit.setProductStatus(listproduct.getProductStatus());
                              edit.setBrandId(listproduct.getBrandID());
                              edit.setTaxId(listproduct.getTaxID());
                              edit.setCategoryId(listproduct.getCatID());
//                              edit.setProQty(listData.getQty());
                              edit.setListGetProduct(listGetProduct);

                              if (listData.getProImageName().contains("media/file/crm/uploadfile/")) {
                                   edit.setProductImage(JavaConstant.urlImage + listData.getProImageName());
                              } else {
                                   edit.setProductImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + listData.getProImageName());
                              }

                              if (listData.getFlag() != null) {
                                   edit.setFlagImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + listData.getFlag());
                              }

                              edit.setVisible(true);

                         } catch (Exception e) {
                              System.err.println("error getting product " + e);
                         }
                    }

                    @Override
                    public void onRemove(String Key) {  // event delete prooduct
                         try {
                              UIManager UI = new UIManager();
                              UI.put("OptionPane.background", WindowColor.mediumGreen);
                              UI.put("Panel.background", WindowColor.mediumGreen);
                              UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                              int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?",
                                   "Delete Product?", JOptionPane.YES_NO_OPTION);

                              if (resp == JOptionPane.YES_OPTION) {
                                   JSONObject json = new JSONObject();
                                   json.put("status", false);
                                   json.put("isDeleted", true);
                                   Response response = JavaConnection.delete(JavaRoute.product + "/" + listData.getId(), json);

                                   if (response.isSuccessful()) {
                                        ListProduct list = new ListProduct(new JFrame(), true);
                                        listGetProduct.removeAll();
                                        listGetProduct.revalidate();
                                        listGetProduct.repaint();
                                        list.getProduct(listGetProduct);

                                        jdLogin.onClickCategory("new items", jdLogin.getCatId());
                                        panelCategory.getComponents()[1].setBackground(WindowColor.black);
                                        dispose();
                                        System.out.println("Successful deleted ");
                                   }
                              } else {
                                   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                              }

                         } catch (Exception e) {
                              System.err.println("error getting product " + e);
                         }
                    }
               };

               prod.initEvent(events);
               prod.setProductName(listData.getProductNameEn());
               prod.setProductBarcode(listData.getBarcode());
               prod.setProductPrice(dm.format(listData.getPrice()));
               prod.setQty(listData.getQty());
               prod.setProductId(listData.getId());
               prod.setListGetProduct(listGetProduct);
               if (listData.getProductStatus() == null) {
                    prod.setProductStatus("Out Stock");
               } else {
                    if (listData.getProductStatus().isEmpty()) {
                         prod.setProductStatus("Out Stock");
                    } else {
                         prod.setProductStatus(listData.getProductStatus());
                    }
               }

               try {

                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              // Task to be executed
                              prod.setImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                              prod.setImageDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }

               listGetProduct.add(prod, gbc);

          }
     }

     //Action Search
     private void eventSearchProduct() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    searchValue = searchField.getValueTextSearch();

                    if (searchValue.isEmpty()) {
                         listGetProduct.removeAll();
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                         getProduct(listGetProduct);
                    } else {
                         listGetProduct.removeAll();
                         ActionSearchProd a = new ActionSearchProd();
                         a.setCategory(panelCategory);
                         a.setPanelProduct(panelProduct);
                         a.setJdLogin(jdLogin);
                         a.searchProducts(searchValue, listGetProduct);
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                    }
               }

               @Override
               public void onKeyRelease() {

               }

          };
          searchField.initEvent(event);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelListProduct = new javax.swing.JPanel();
          header = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jLabel2 = new javax.swing.JLabel();
          jLabel3 = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          jLabel6 = new javax.swing.JLabel();
          searchField = new Components.SearchField();
          jScrollPane1 = new javax.swing.JScrollPane();
          listGetProduct = new javax.swing.JPanel();
          button1 = new Button.Button();
          btnExcel = new Button.Button();
          btnPdf = new Button.Button();
          btnCsv = new Button.Button();
          btnCancel = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          header.setBackground(new java.awt.Color(0, 0, 0));

          jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel1.setForeground(new java.awt.Color(255, 255, 255));
          jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel1.setText("Actions");

          jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel2.setForeground(new java.awt.Color(255, 255, 255));
          jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel2.setText("Product Barcode");

          jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(255, 255, 255));
          jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel3.setText("Product Price");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setText("Product Name");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Quantity");

          jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel6.setForeground(new java.awt.Color(255, 255, 255));
          jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel6.setText("Status");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 6, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1)
                         .addComponent(jLabel2)
                         .addComponent(jLabel3)
                         .addComponent(jLabel5)
                         .addComponent(jLabel4)
                         .addComponent(jLabel6))
                    .addContainerGap(12, Short.MAX_VALUE))
          );

          searchField.setPlaceholder("Search by name or barcode");
          searchField.setValueTextSearch("");

          jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
          jScrollPane1.setBorder(null);

          listGetProduct.setBackground(new java.awt.Color(176, 215, 181));

          javax.swing.GroupLayout listGetProductLayout = new javax.swing.GroupLayout(listGetProduct);
          listGetProduct.setLayout(listGetProductLayout);
          listGetProductLayout.setHorizontalGroup(
               listGetProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 0, Short.MAX_VALUE)
          );
          listGetProductLayout.setVerticalGroup(
               listGetProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 495, Short.MAX_VALUE)
          );

          jScrollPane1.setViewportView(listGetProduct);

          button1.setBackground(new java.awt.Color(47, 155, 70));
          button1.setButtonName("+ Add Product");
          button1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    button1MouseClicked(evt);
               }
          });

          btnExcel.setBackground(new java.awt.Color(47, 155, 70));
          btnExcel.setButtonName("Excel");
          btnExcel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnExcelMouseClicked(evt);
               }
          });

          btnPdf.setBackground(new java.awt.Color(47, 155, 70));
          btnPdf.setButtonName("PDF");
          btnPdf.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnPdfMouseClicked(evt);
               }
          });

          btnCsv.setBackground(new java.awt.Color(47, 155, 70));
          btnCsv.setButtonName("CSV");
          btnCsv.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCsvMouseClicked(evt);
               }
          });

          btnCancel.setButtonName("Cancel");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelListProductLayout = new javax.swing.GroupLayout(panelListProduct);
          panelListProduct.setLayout(panelListProductLayout);
          panelListProductLayout.setHorizontalGroup(
               panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListProductLayout.createSequentialGroup()
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelListProductLayout.createSequentialGroup()
                              .addGap(15, 15, 15)
                              .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelListProductLayout.createSequentialGroup()
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(jScrollPane1)
                                   .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                         .addGroup(panelListProductLayout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18))
          );
          panelListProductLayout.setVerticalGroup(
               panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelListProductLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelListProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
         dispose();
         AddProduct add = new AddProduct(new JFrame(), true);
         add.setJdLogin(jdLogin);
         add.setPanelCategory(panelCategory);
         add.setPanelProduct(panelProduct);
         add.setVisible(true);
    }//GEN-LAST:event_button1MouseClicked

     private void btnCsvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCsvMouseClicked
          msgPrint(PrintToCSV.folderPath);
          PrintToCSV.exportToCSV(listProduct);
     }//GEN-LAST:event_btnCsvMouseClicked

     private void btnPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseClicked
          try {
               msgPrint(PrintListPDF.folderPath);
               PrintListPDF.printListPdf(listProduct);
          } catch (IOException ex) {
               Logger.getLogger(ListProduct.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_btnPdfMouseClicked

     private void btnExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseClicked
          msgPrint(PrintToExcel.folderPath);
          PrintToExcel.toExcel(listProduct);
     }//GEN-LAST:event_btnExcelMouseClicked

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     public static void msgPrint(String path) {
          JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
          j.setIsShow(true);
          j.setMessage("File was saved to path " + path);
          j.setPathOpen(path);
          j.setVisible(true);
     }

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
               java.util.logging.Logger.getLogger(ListProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListProduct dialog = new ListProduct(new javax.swing.JFrame(), true);
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
     private Button.Button btnCancel;
     private Button.Button btnCsv;
     private Button.Button btnExcel;
     private Button.Button btnPdf;
     private Button.Button button1;
     private javax.swing.JPanel header;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel listGetProduct;
     private javax.swing.JPanel panelListProduct;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
