package Products;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.JavaAlertMessage;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.PackageProduct.ProductModel;
import Model.ProductModelV1.ProductResponseByIdV1;
import Model.ProductModelV1.ProductResponseDetailV1;
import Model.ProductModelV1.ProductResponseV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import okhttp3.Response;
import pdf.PrintListPDF;
import pdf.PrintToCSV;
import pdf.PrintToExcel;

public class ListProduct extends javax.swing.JDialog {

     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     private String searchValue;
     private int id;

     private JPanel panelProduct;
     private JPanel panelCategory;
     private LoginFormJdailog jdLogin;
     String status = "allProduct";
     ProductResponseDetailV1[] listData;
     private String pageNumber = "0";
     private long totalPage = 0;
     private int pageSize = 15;
     private int code = 0;
     private boolean isCheckSearch = true;

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
          getProduct(listGetProduct, true, 0);
          eventSearchProduct(this);
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
          JavaConstant.addTitleAndLogo(this, "Product");

          allProduct.setBorder(new UnderlineBorder());
          JavaConstant.setPointer(allProduct);
          JavaConstant.setPointer(active);
          JavaConstant.setPointer(inActive);

          eventPagination();

     }

     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         switch (code) {
                              case 0 -> {
                                   getProduct(listGetProduct, true, 0);
                                   break;
                              }
                              case 1 -> {
                                   getProduct(listGetProduct, true, 1);
                                   break;
                              }
                              case 2 -> {
                                   getProduct(listGetProduct, true, 2);
                                   break;
                              }
                         }

                    }
               }
          };
          paginationPanel.initEvent(event);
     }

     void setBackground() {
          header.setBackground(WindowColor.darkGreen);
     }

     public void getProduct(JPanel jpanelData, boolean isCheck, int code) {

          try {
               Response response = null;

               switch (code) {
                    case 0 -> {
                         if (isCheck) { // isCheck true get itmes
                              response = JavaConnection.get(JavaRoute.productV1 + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
                         } else { // isCheck false search
                              isCheckSearch = false;
                              response = JavaConnection.get(JavaRoute.productV1 + "/search/" + searchValue + "?pageNumber=0&pageSize=50");
                         }
                    }
                    case 1 -> {
                         if (isCheck) { // isCheck true get itmes
                              response = JavaConnection.get(JavaRoute.productV1 + "/status" + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&status=Active");
                         } else { // isCheck false search
                              isCheckSearch = false;
                              response = JavaConnection.get(JavaRoute.productV1 + "/search/" + searchValue + "?pageNumber=0&pageSize=50&status=Active");
                         }
                    }
                    case 2 -> {
                         if (isCheck) { // isCheck true get itmes
                              response = JavaConnection.get(JavaRoute.productV1 + "/status" + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&status=Inactive");
                              System.out.println("pageNumber : " + pageNumber);
                         } else { // isCheck false search
                              isCheckSearch = false;
                              response = JavaConnection.get(JavaRoute.productV1 + "/search/" + searchValue + "?pageNumber=0&pageSize=50&status=Inactive");
                         }
                    }

               }

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductResponseV1 data = objMap.readValue(responseData, ProductResponseV1.class);

                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
                    } else {
                         paginationPanel.resetPage();
                    }

                    listData = data.getData();
                    setProduct(listData);
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     public void setProduct(ProductResponseDetailV1[] listProductData) {

          listGetProduct.removeAll();
          listGetProduct.revalidate();
          listGetProduct.repaint();
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetProduct.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          if (listProductData.length == 0) {
               listGetProduct.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               listGetProduct.add(nofound, BorderLayout.CENTER);
               listGetProduct.add(nofound);
               listGetProduct.revalidate();
               listGetProduct.repaint();
          }

          for (ProductResponseDetailV1 p : listProductData) {
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

               Products.GetProduct prod = new Products.GetProduct();
               prod.setData(
                    p.getBarcode(),
                    p.getItemCode(),
                    p.getSubCatNameEn(),
                    p.getVendorCode(),
                    p.getVendorName(),
                    p.getProNameEn(),
                    p.getProNameKh(),
                    String.valueOf(p.getQty() == null ? 0 : p.getQty()),
                    "$" + String.valueOf(String.format("%.2f", p.getPrice())),
                    "$" + String.valueOf(String.format("%.2f", p.getCost())),
                    String.valueOf(p.getID())
               );
               prod.setProductId(p.getID());
               try {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              prod.setImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                              prod.setImageDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }

               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String id) {
                         eventEdit(id);
                    }

                    @Override
                    public void onRemove(String id) {
                         eventRemove(id);
                    }

               };
               prod.initEvent(events);

               if (status.equals("allProduct")) {
                    listGetProduct.add(prod, gbc);
               } else if (status.toLowerCase().equals(p.getStatusName().toLowerCase())) {
                    listGetProduct.add(prod, gbc);
               }
          }

     }

     private void eventRemove(String id) {
          try {
               UIManager UI = new UIManager();
               UI.put("OptionPane.background", WindowColor.mediumGreen);
               UI.put("Panel.background", WindowColor.mediumGreen);
               UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

               int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?",
                    "Delete Product?", JOptionPane.YES_NO_OPTION);

               if (resp == JOptionPane.YES_OPTION) {
                    Response response = JavaConnection.delete(JavaRoute.productV1 + "/" + id);
                    if (response.isSuccessful()) {

                         listGetProduct.removeAll();
                         listGetProduct.revalidate();
                         listGetProduct.repaint();
                         getProduct(listGetProduct, true, 0);

//                         jdLogin.onClickCategory("new items", jdLogin.getCatId());
//                         panelCategory.getComponents()[1].setBackground(WindowColor.black);
                         System.out.println("Successful deleted ");
                    }
               } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               }

          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     private void eventEdit(String id) {
          Response response = JavaConnection.get(JavaRoute.productV1 + "/" + id);
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objectMapper = new ObjectMapper();
                    ProductResponseByIdV1 productResponseByIdV1 = objectMapper.readValue(responseData, ProductResponseByIdV1.class);
                    ProductResponseByIdV1.Data data = productResponseByIdV1.getData();
                    InsertProduct insertProduct = new InsertProduct(new Frame(), true);
                    insertProduct.setId(data.getID());
                    insertProduct.setListGetProduct(listGetProduct);
                    insertProduct.setListProduct(this);
                    insertProduct.setEdit(
                         data.getBarcode(),
                         String.valueOf(data.getVendorID()),
                         String.valueOf(data.getBrandID()),
                         String.valueOf(data.getSubCatID()),
                         String.valueOf(data.getProNameEn()),
                         String.valueOf(data.getProNameKh() == null ? "" : data.getProNameKh()),
                         String.valueOf(data.getCost()),
                         String.valueOf(data.getPrice()),
                         String.valueOf(data.getMargin()),
                         String.valueOf(data.getAttributeID()),
                         String.valueOf(data.getChoices()),
                         String.valueOf(data.getUomID()),
                         String.valueOf(data.getProductActiveID()),
                         String.valueOf(data.getCountryID()),
                         String.valueOf(data.getTaxID()),
                         String.valueOf(data.getProImageName())
                    );
                    insertProduct.setJdLogin(jdLogin);
                    insertProduct.setPanelProduct(panelProduct);
                    insertProduct.setPanelCategory(panelCategory);
                    insertProduct.setVisible(true);
               }
          } catch (Exception e) {
               System.out.println("Error : " + e);
          }
     }

//     private void search() {
//          isCheckSearch = false;
//          Response response = JavaConnection.get(JavaRoute.productV1 + "/search/" + searchValue + "?pageNumber=0&pageSize=50");
//          if (response.isSuccessful()) {
//               String responseData;
//               try {
//                    responseData = response.body().string();
//                    ObjectMapper objMap = new ObjectMapper();
//                    ProductResponseV1 data = objMap.readValue(responseData, ProductResponseV1.class);
//                    paginationPanel.resetPage();
//                    listData = data.getData();
//                    setProduct(listData);
//               } catch (IOException ex) {
//                    Logger.getLogger(ListProduct.class.getName()).log(Level.SEVERE, null, ex);
//               }
//
//          }
//     }
     //Action Search
     private void eventSearchProduct(ListProduct listP) {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = searchField.getValueTextSearch();
                              paginationPanel.resetPage();
                              pageNumber = "0";

                              if (searchValue.isEmpty()) {
                                   isCheckSearch = true;
                                   pageNumber = "0";
                                   searchCode(true);
                                   return;
                              }
                              searchCode(false);
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               }
          };
          searchField.initEvent(event);
     }

     private void searchCode(boolean isC) {
          switch (code) {
               case 0 -> {
                    getProduct(listGetProduct, isC, 0);
                    break;
               }
               case 1 -> {
                    getProduct(listGetProduct, isC, 1);
                    break;
               }
               case 2 -> {
                    getProduct(listGetProduct, isC, 2);
                    break;
               }
          }
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelListProduct = new javax.swing.JPanel();
          header = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jLabel3 = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          jLabel7 = new javax.swing.JLabel();
          jLabel8 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          Division = new javax.swing.JLabel();
          Division1 = new javax.swing.JLabel();
          Division2 = new javax.swing.JLabel();
          jLabel10 = new javax.swing.JLabel();
          searchField = new Components.SearchField();
          jScrollPane1 = new javax.swing.JScrollPane();
          listGetProduct = new javax.swing.JPanel();
          button1 = new Button.Button();
          btnExcel = new Button.Button();
          btnPdf = new Button.Button();
          btnCsv = new Button.Button();
          btnCancel = new Button.Button();
          inActive = new javax.swing.JLabel();
          allProduct = new javax.swing.JLabel();
          active = new javax.swing.JLabel();
          paginationPanel = new pagination.PaginationPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          header.setBackground(new java.awt.Color(0, 0, 0));

          jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel1.setForeground(new java.awt.Color(255, 255, 255));
          jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel1.setText("Actions");

          jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(255, 255, 255));
          jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel3.setText("Product Price");

          jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(255, 255, 255));
          jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel4.setText("Barcode");

          jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel5.setForeground(new java.awt.Color(255, 255, 255));
          jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel5.setText("Cost");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(255, 255, 255));
          jLabel7.setText("Product Name");

          jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel8.setForeground(new java.awt.Color(255, 255, 255));
          jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel8.setText("Quantity");

          jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(255, 255, 255));
          jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel9.setText("Item Code");

          Division.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          Division.setForeground(new java.awt.Color(255, 255, 255));
          Division.setText("Divison");

          Division1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          Division1.setForeground(new java.awt.Color(255, 255, 255));
          Division1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          Division1.setText("Vendor Code");

          Division2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          Division2.setForeground(new java.awt.Color(255, 255, 255));
          Division2.setText("Vendor Name");

          jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(255, 255, 255));
          jLabel10.setText("Product Name Khmer");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Division, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Division1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Division2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1)
                         .addComponent(jLabel3)
                         .addComponent(jLabel5)
                         .addComponent(jLabel4)
                         .addComponent(jLabel7)
                         .addComponent(jLabel8)
                         .addComponent(jLabel9)
                         .addComponent(Division)
                         .addComponent(Division1)
                         .addComponent(Division2)
                         .addComponent(jLabel10))
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
               .addGap(0, 665, Short.MAX_VALUE)
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

          inActive.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          inActive.setForeground(new java.awt.Color(0, 0, 0));
          inActive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          inActive.setText("Inactive");
          inActive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    inActiveMouseClicked(evt);
               }
          });

          allProduct.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          allProduct.setForeground(new java.awt.Color(0, 0, 0));
          allProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          allProduct.setText("All Product");
          allProduct.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    allProductMouseClicked(evt);
               }
          });

          active.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          active.setForeground(new java.awt.Color(0, 0, 0));
          active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          active.setText("Active");
          active.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    activeMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelListProductLayout = new javax.swing.GroupLayout(panelListProduct);
          panelListProduct.setLayout(panelListProductLayout);
          panelListProductLayout.setHorizontalGroup(
               panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelListProductLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                         .addGroup(panelListProductLayout.createSequentialGroup()
                              .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListProductLayout.createSequentialGroup()
                              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(18, 18, 18)
                              .addComponent(allProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(25, 25, 25)
                              .addComponent(active, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(25, 25, 25)
                              .addComponent(inActive, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          panelListProductLayout.setVerticalGroup(
               panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelListProductLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                              .addComponent(inActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(active, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(allProduct, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGap(10, 10, 10)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelListProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     static class UnderlineBorder implements Border {

          private final MatteBorder matteBorder;

          public UnderlineBorder() {
               matteBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
          }

          @Override
          public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
               Insets insets = matteBorder.getBorderInsets(c);
               matteBorder.paintBorder(c, g, x, y + height - insets.bottom, width, insets.bottom);
          }

          @Override
          public Insets getBorderInsets(java.awt.Component c) {
               return matteBorder.getBorderInsets(c);
          }

          @Override
          public boolean isBorderOpaque() {
               return matteBorder.isBorderOpaque();
          }
     }
    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked

         InsertProduct add = new InsertProduct(new JFrame(), true);
         add.setJdLogin(jdLogin);
         add.setPanelCategory(panelCategory);
         add.setPanelProduct(panelProduct);
         add.setListProduct(this);
         add.setListGetProduct(listGetProduct);
         add.setVisible(true);
    }//GEN-LAST:event_button1MouseClicked

     private void btnCsvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCsvMouseClicked
          exportFunc("csv");
     }//GEN-LAST:event_btnCsvMouseClicked

     private void btnPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseClicked
          exportFunc("pdf");
     }//GEN-LAST:event_btnPdfMouseClicked

     private void btnExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseClicked
          exportFunc("excel");
     }//GEN-LAST:event_btnExcelMouseClicked

     private void exportFunc(String typeExport) {
          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          Response response = JavaConnection.get(JavaRoute.productV1);
          try {
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ProductResponseV1 data = objMap.readValue(responseData, ProductResponseV1.class);
                    setCursor(Cursor.getDefaultCursor());
                    switch (typeExport) {
                         case "excel" -> {
                              msgPrint(PrintToExcel.folderPath);
                              PrintToExcel.toExcel(data.getData());
                              break;
                         }
                         case "pdf" -> {
                              msgPrint(PrintListPDF.folderPath);
                              PrintListPDF.printListPdf(data.getData());
                              break;
                         }
                         case "csv" -> {
                              msgPrint(PrintToCSV.folderPath);
                              PrintToCSV.exportToCSV(data.getData());
                              break;
                         }
                    }

               }
          } catch (Exception e) {
               System.out.println("error export : " + e);
          }
     }

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void allProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allProductMouseClicked
          removeBorder(active);
          removeBorder(inActive);
          allProduct.setBorder(new UnderlineBorder());
          status = "allProduct";
          code = 0;
          pageNumber = "0";
          paginationPanel.resetPage();
          getProduct(listGetProduct, true, 0);
     }//GEN-LAST:event_allProductMouseClicked

     private void activeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activeMouseClicked
          removeBorder(allProduct);
          removeBorder(inActive);
          active.setBorder(new UnderlineBorder());
          status = "active";
          code = 1;
          pageNumber = "0";
          paginationPanel.resetPage();
          getProduct(listGetProduct, true, 1);

     }//GEN-LAST:event_activeMouseClicked

     private void inActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inActiveMouseClicked
          removeBorder(active);
          removeBorder(allProduct);
          inActive.setBorder(new UnderlineBorder());
          status = "inActive";
          code = 2;
          pageNumber = "0";
          paginationPanel.resetPage();
          getProduct(listGetProduct, true, 2);


     }//GEN-LAST:event_inActiveMouseClicked

     private void removeBorder(JLabel label) {
          label.setBorder(new EmptyBorder(0, 0, 0, 0));
     }

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
     private javax.swing.JLabel Division;
     private javax.swing.JLabel Division1;
     private javax.swing.JLabel Division2;
     private javax.swing.JLabel active;
     private javax.swing.JLabel allProduct;
     private Button.Button btnCancel;
     private Button.Button btnCsv;
     private Button.Button btnExcel;
     private Button.Button btnPdf;
     private Button.Button button1;
     private javax.swing.JPanel header;
     private javax.swing.JLabel inActive;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel listGetProduct;
     private pagination.PaginationPanel paginationPanel;
     private javax.swing.JPanel panelListProduct;
     private Components.SearchField searchField;
     // End of variables declaration//GEN-END:variables
}
