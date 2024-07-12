package Products;

import Color.WindowColor;
import Constant.ErrorResponse;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import MessageAlert.JavaMessageDialog;
import Model.PackageProduct.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class ImportDetail extends javax.swing.JDialog {

     private List<ProductResponse> listProductResponse;
     private String[] columnName = {
          "#",
          "File",
          "Photo",
          "Barcode",
          "Vendor Name",
          "Brand",
          "Sub Category",
          "Product Name",
          "Product Name Kh",
          "Cost",
          "Price",
          "Margin",
          "Attribute",
          "Choice Value",
          "UOM",
          "Status",
          "Country",
          "Tax",
          "Path"
     };

     private String path;

     public ImportDetail(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
//          setCustomTable();
          getContentPane().setBackground(WindowColor.white);
          panelImp.setBackground(WindowColor.white);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          // custome scrollbar ui
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBars = jScrollPane1.getVerticalScrollBar();
          verticalScrollBars.setUnitIncrement(30);
          verticalScrollBars.setBlockIncrement(35);

          btnSave.setContentAreaFilled(false);

     }

     public List<ProductResponse> getListProductResponse() {
          return listProductResponse;
     }

     public void setListProductResponse(List<ProductResponse> listProductResponse) {
          this.listProductResponse = listProductResponse;
     }

     public void setCustomTable(List<ProductResponse> list) {
          Object[][] rows = new Object[list.size()][19];

          // Loop through the list and populate the 2D array
          int count = 0;

          for (int i = 0; i < list.size(); i++) {
               ProductResponse p = list.get(i);
               if (p.getBarcode() != null
                    && p.getVendorId() != null
                    && p.getSubCatId() != null
                    && p.getProductName() != null) {

                    rows[i][0] = String.valueOf(i + 1); // Index
                    rows[i][1] = p.getLink(); // File column
                    rows[i][2] = p.getPhoto(); // Placeholder for now, adjust as needed
                    rows[i][3] = p.getBarcode(); // Barcode
                    rows[i][4] = p.getVendorId(); // Vendor Name
                    rows[i][5] = p.getBrandId(); // Brand
                    rows[i][6] = p.getSubCatId(); // Sub Category
                    rows[i][7] = p.getProductName(); // Product Name
                    rows[i][8] = p.getProductNameKh(); // Product Name Kh
                    rows[i][9] = p.getCost(); // Cost
                    rows[i][10] = p.getPrice(); // Price
                    rows[i][11] = p.getMargin(); // Margin
                    rows[i][12] = p.getAttributeId(); // Attribute
                    rows[i][13] = p.getChoiceValue(); // Choice Value
                    rows[i][14] = p.getUomId(); // UOM
                    rows[i][15] = p.getStatusId(); // Status
                    rows[i][16] = p.getCountryId(); // Country
                    rows[i][17] = p.getTaxId(); // Tax
                    rows[i][18] = ""; // Tax

               } else {
                    count++;
               }
          }
          appendTable(rows);

          if (count > 0) {
               int l = rows.length - count;
               Object[][] rowsData = new Object[l][19];
               for (int i = 0; i < l; i++) {
                    ProductResponse p = list.get(i);
                    if (p.getBarcode() != null
                         && p.getVendorId() != null
                         && p.getSubCatId() != null
                         && p.getProductName() != null) {

                         rowsData[i][0] = String.valueOf(i + 1); // Index
                         rowsData[i][1] = p.getLink(); // File column
                         rowsData[i][2] = p.getPhoto(); // Placeholder for now, adjust as needed
                         rowsData[i][3] = p.getBarcode(); // Barcode
                         rowsData[i][4] = p.getVendorId(); // Vendor Name
                         rowsData[i][5] = p.getBrandId(); // Brand
                         rowsData[i][6] = p.getSubCatId(); // Sub Category
                         rowsData[i][7] = p.getProductName(); // Product Name
                         rowsData[i][8] = p.getProductNameKh(); // Product Name Kh
                         rowsData[i][9] = p.getCost(); // Cost
                         rowsData[i][10] = p.getPrice(); // Price
                         rowsData[i][11] = p.getMargin(); // Margin
                         rowsData[i][12] = p.getAttributeId(); // Attribute
                         rowsData[i][13] = p.getChoiceValue(); // Choice Value
                         rowsData[i][14] = p.getUomId(); // UOM
                         rowsData[i][15] = p.getStatusId(); // Status
                         rowsData[i][16] = p.getCountryId(); // Country
                         rowsData[i][17] = p.getTaxId(); // Tax
                         rows[i][18] = ""; // Tax
                    }
               }
               appendTable(rowsData);
          }

     }

     private void appendTable(Object[][] rowsData) {
          DefaultTableModel model = new DefaultTableModel(rowsData, columnName) {
               @Override
               public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 2) {
                         return ImageIcon.class;
                    }
                    // Return appropriate class for each column to render buttons correctly
                    return columnIndex == 1 ? JButton.class : Object.class;
               }

               @Override
               public boolean isCellEditable(int row, int column) {
                    // Make cells in the button column non-editable
                    if (column == 1) {
                         return true;
                    }
                    return false;
               }
          };
          table.setModel(model);

          // Set column widths (adjust as needed)
          table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          TableColumnModel columnModel = table.getColumnModel();
          columnModel.getColumn(0).setPreferredWidth(30);
          columnModel.getColumn(1).setPreferredWidth(80);
          columnModel.getColumn(2).setPreferredWidth(100);
          columnModel.getColumn(3).setPreferredWidth(130);
          columnModel.getColumn(4).setPreferredWidth(100);
          columnModel.getColumn(5).setPreferredWidth(80);
          columnModel.getColumn(6).setPreferredWidth(150);
          columnModel.getColumn(7).setPreferredWidth(300);
          columnModel.getColumn(8).setPreferredWidth(300);
          columnModel.getColumn(9).setPreferredWidth(70);
          columnModel.getColumn(10).setPreferredWidth(70);
          columnModel.getColumn(11).setPreferredWidth(70);
          columnModel.getColumn(12).setPreferredWidth(80);
          columnModel.getColumn(13).setPreferredWidth(120);
          columnModel.getColumn(14).setPreferredWidth(60);
          columnModel.getColumn(15).setPreferredWidth(70);
          columnModel.getColumn(16).setPreferredWidth(70);
          columnModel.getColumn(17).setPreferredWidth(70);

          // Customize table header
          JTableHeader header = table.getTableHeader();
          header.setBackground(WindowColor.darkGreen);

          header.setFont(new Font("Arial", Font.PLAIN, 12)); // Font size and style
          header.setForeground(Color.WHITE); // Header text color
          header.setPreferredSize(new Dimension(header.getWidth(), 30)); // Set header height

          // Center-align header content
          DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
          headerRenderer.setHorizontalAlignment(JLabel.CENTER);

          // Center-align cell content and set font size
          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
               @Override
               public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font size for cell content
                    setHorizontalAlignment(JLabel.CENTER); // Center-align cell content
                    return c;
               }
          };
          table.setDefaultRenderer(Object.class, cellRenderer);

          // Create button renderer and editor for the "File" column
          table.getColumn("File").setCellRenderer(new ButtonRenderer());

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onClick(int row) {
                    try {
                         path = JNAFileChooser.funChooseFile();
                         File file = new File(path);
                         Image image = ImageIO.read(new File(path));
                         if (image != null) {

                              int labelWidth = 30;
                              int labelHeight = 30;

                              // Calculate the scale factor
                              double scaleX = (double) labelWidth / image.getWidth(null);
                              double scaleY = (double) labelHeight / image.getHeight(null);
                              double scale = Math.min(scaleX, scaleY);

                              // Scale the image
                              int scaledWidth = (int) (image.getWidth(null) * scale);
                              int scaledHeight = (int) (image.getHeight(null) * scale);
                              Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                              ImageIcon icon = new ImageIcon(scaledImage);
                              table.setValueAt(icon, row, 2);
                              table.setValueAt(path, row, 18);
                         }

                         // Update the model with the new ImageIcon
                    } catch (IOException ex) {
                         Logger.getLogger(ImportFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
          };

          table.getColumn("File").setCellEditor(new ButtonEditor(event));

          // Set row height
          table.setRowHeight(30);
          table.setBackground(Color.WHITE);
          table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
          table.getTableHeader().setReorderingAllowed(false);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelImp = new javax.swing.JPanel();
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new javax.swing.JTable();
          jLabel1 = new javax.swing.JLabel();
          btnSave = new javax.swing.JButton();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
          jScrollPane1.setBorder(null);

          table.setBackground(new java.awt.Color(255, 255, 255));
          table.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
               },
               new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
               }
          ));
          jScrollPane1.setViewportView(table);

          jLabel1.setText("Import");

          btnSave.setText("Save");
          btnSave.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSaveActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout panelImpLayout = new javax.swing.GroupLayout(panelImp);
          panelImp.setLayout(panelImpLayout);
          panelImpLayout.setHorizontalGroup(
               panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1707, Short.MAX_VALUE)
                         .addGroup(panelImpLayout.createSequentialGroup()
                              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE))))
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave))
          );
          panelImpLayout.setVerticalGroup(
               panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave)
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

//     private void showLoadingDialog(String msg) {
//          JDialog loadingDialog = new JDialog(new JFrame(), "Conflict", true); // true for modal
//          JLabel label = new JLabel(msg);
//          loadingDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//          loadingDialog.setResizable(false);
//          label.setHorizontalAlignment(SwingConstants.CENTER);
//          loadingDialog.add(label);
//          loadingDialog.setSize(500, 200);
//          loadingDialog.setLocationRelativeTo(this); // Center dialog on the JFrame
//          loadingDialog.getContentPane().setBackground(Color.WHITE);
//          loadingDialog.setVisible(true);
//     }
     private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

          String url = new JavaBaseUrl().getBaseUrl() + JavaRoute.addMultipleDataFromExcel; // this one for insert image 
          MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
               .setType(MultipartBody.FORM);

          for (int i = 0; i < table.getRowCount(); i++) {
               String pathImg = null;
               if (table.getValueAt(i, 18) == null) {
                    pathImg = "productImage/default.jpg";
                    // Load the resource using ClassLoader
                    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathImg);
                    if (inputStream != null) {
                         try {
                              // Convert InputStream to FileBody or RequestBody as per your API upload mechanism
                              RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), inputStream.readAllBytes());
                              requestBodyBuilder.addFormDataPart("files", "default.jpg", fileBody);
                              inputStream.close();
                         } catch (IOException e) {
                              e.printStackTrace();
                         }
                    } else {
                         System.err.println("File not found in resources: " + pathImg);
                    }
               } else {
                    pathImg = table.getValueAt(i, 18).toString();

                    File fileToUpload = new File(pathImg);
                    if (fileToUpload.exists()) {
                         requestBodyBuilder.addFormDataPart("files", fileToUpload.getName(),
                              RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));
                    } else {
                         System.err.println("File not found: " + pathImg);
                    }
               }

          }

          // Build the request body
          RequestBody requestBody = requestBodyBuilder.build();

          // Create OkHttpClient instance
          OkHttpClient client = new OkHttpClient();

          // Create the request
          Request request = new Request.Builder()
               .url(url)
               .post(requestBody)
               .build();

          try {
               Response response = client.newCall(request).execute();

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ImageDataSuccess data = objMap.readValue(responseData, ImageDataSuccess.class);
                    ImageResponse[] listData = data.getData();
                    int count = 0;
                    for (int i = 0; i < listProductResponse.size(); i++) {
                         var p = listProductResponse.get(i);
                         if (p.getBarcode() != null
                              && p.getVendorId() != null
                              && p.getSubCatId() != null
                              && p.getProductName() != null) {
                              p.setPhoto(listData[i].getUUID());
                              p.setCreateBy(JavaConstant.cashierId);
                         } else {
                              count++;
                         }
                    }

                    System.out.println("before legth   : " + listProductResponse.size());
//                    List<ProductResponse> productResponses = new ArrayList<>();
//                    if (count > 0) {
//                         int l = listProductResponse.size() - count;
//                         for (int i = 0; i < l; i++) {
//                              var datas = listProductResponse.get(i);
//                              productResponses.add(ProductResponse.builder()
//                                   .barcode(datas.getBarcode())
//                                   .vendorId(datas.getVendorId())
//                                   .brandId(datas.getBrandId())
//                                   .catId(datas.getCatId())
//                                   .productName(datas.getProductName())
//                                   .productNameKh(datas.getProductNameKh())
//                                   .cost(datas.getCost())
//                                   .price(datas.getPrice())
//                                   .margin(datas.getMargin())
//                                   .attributeId(datas.getAttributeId())
//                                   .choiceValue(datas.getChoiceValue())
//                                   .uomId(datas.getUomId())
//                                   .status(datas.getStatus())
//                                   .countryId(datas.getCountryId())
//                                   .link(datas.getLink())
//                                   .photo(datas.getPhoto())
//                                   .number(datas.getNumber())
//                                   .build()
//                              );
//                         }
//                    }

                    try {
                         JSONObject json = new JSONObject();
                         json.put("lists", listProductResponse);
                         System.out.println("json : " + json);
                         Response responseImp = JavaConnection.post(JavaRoute.productExcel, json);
                         JavaConstant.setCircleLoadingCursor(this);
                         if (responseImp.isSuccessful()) {

                              String responseImgData = responseImp.body().string();
                              JSONObject jsonObject = new JSONObject(responseImgData);

                              if (jsonObject.has("error")) {
                                   // Retrieve values
                                   JSONObject errorObject = jsonObject.getJSONObject("error");
                                   int errorCode = errorObject.getInt("code");
                                   String reason = errorObject.getString("reason");

//                                   int code = jsonObject.getInt("code");
                                   JavaMessageDialog j = new JavaMessageDialog(new JFrame(), true);
                                   j.setTitleLabel(reason);
                                   j.setTitle("Message");
                                   j.setVisible(true);
                                   JavaConstant.restoreDefaultCursor(this);
                              } else {
                                   dispose();
                                   JavaConstant.restoreDefaultCursor(this);
                              }

                         }

                    } catch (Exception e) {
                         System.out.println("err = " + e);
                    }

//                    dispose();
               }
               // Do something with the response.
          } catch (IOException e) {
               System.out.println("err = " + e);
          }

     }//GEN-LAST:event_btnSaveActionPerformed

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ImportDetail dialog = new ImportDetail(new javax.swing.JFrame(), true);
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
     private javax.swing.JButton btnSave;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel panelImp;
     private javax.swing.JTable table;
     // End of variables declaration//GEN-END:variables
}
