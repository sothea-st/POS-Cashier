package Products;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Model.PackageProduct.ProductResponse;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImportDetail extends javax.swing.JDialog {

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
//          setResizable(false);
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

     }

     public void setCustomTable(List<ProductResponse> list) {
          Object[][] rows = new Object[list.size()][19];

          // Loop through the list and populate the 2D array
          int count = 0;

          for (int i = 0; i < list.size(); i++) {
               ProductResponse p = list.get(i);
               if (p.getBarcode() != null
                    && p.getVendorId() != null
                    && p.getCatId() != null
                    && p.getProductName() != null) {

                    rows[i][0] = String.valueOf(i + 1); // Index
                    rows[i][1] = p.getLink(); // File column
                    rows[i][2] = p.getPhoto(); // Placeholder for now, adjust as needed
                    rows[i][3] = p.getBarcode(); // Barcode
                    rows[i][4] = p.getVendorId(); // Vendor Name
                    rows[i][5] = p.getBrandId(); // Brand
                    rows[i][6] = p.getCatId(); // Sub Category
                    rows[i][7] = p.getProductName(); // Product Name
                    rows[i][8] = p.getProductNameKh(); // Product Name Kh
                    rows[i][9] = p.getCost(); // Cost
                    rows[i][10] = p.getPrice(); // Price
                    rows[i][11] = p.getMargin(); // Margin
                    rows[i][12] = p.getAttributeId(); // Attribute
                    rows[i][13] = p.getChoiceValue(); // Choice Value
                    rows[i][14] = p.getUomId(); // UOM
                    rows[i][15] = p.getStatus(); // Status
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
                         && p.getCatId() != null
                         && p.getProductName() != null) {

                         rowsData[i][0] = String.valueOf(i + 1); // Index
                         rowsData[i][1] = p.getLink(); // File column
                         rowsData[i][2] = p.getPhoto(); // Placeholder for now, adjust as needed
                         rowsData[i][3] = p.getBarcode(); // Barcode
                         rowsData[i][4] = p.getVendorId(); // Vendor Name
                         rowsData[i][5] = p.getBrandId(); // Brand
                         rowsData[i][6] = p.getCatId(); // Sub Category
                         rowsData[i][7] = p.getProductName(); // Product Name
                         rowsData[i][8] = p.getProductNameKh(); // Product Name Kh
                         rowsData[i][9] = p.getCost(); // Cost
                         rowsData[i][10] = p.getPrice(); // Price
                         rowsData[i][11] = p.getMargin(); // Margin
                         rowsData[i][12] = p.getAttributeId(); // Attribute
                         rowsData[i][13] = p.getChoiceValue(); // Choice Value
                         rowsData[i][14] = p.getUomId(); // UOM
                         rowsData[i][15] = p.getStatus(); // Status
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
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelImp = new javax.swing.JPanel();
          jScrollPane1 = new javax.swing.JScrollPane();
          table = new javax.swing.JTable();
          jLabel1 = new javax.swing.JLabel();
          jButton1 = new javax.swing.JButton();

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

          jButton1.setText("Save");
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout panelImpLayout = new javax.swing.GroupLayout(panelImp);
          panelImp.setLayout(panelImpLayout);
          panelImpLayout.setHorizontalGroup(
               panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1707, Short.MAX_VALUE)
                         .addGroup(panelImpLayout.createSequentialGroup()
                              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE))))
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1))
          );
          panelImpLayout.setVerticalGroup(
               panelImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelImpLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addContainerGap())
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          String url = new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/addMultiple";
          MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
               .setType(MultipartBody.FORM);

          for (int i = 0; i < table.getRowCount(); i++) {
               String pathImg = null;
               if (table.getValueAt(i, 18) == null) {
                    pathImg = "src/main/resources/image/item.png";
               } else {
                    pathImg = table.getValueAt(i, 18).toString();
               }

               System.out.println("pathImg : " + pathImg);

               File fileToUpload = new File(pathImg);
               if (fileToUpload.exists()) {
                    requestBodyBuilder.addFormDataPart("files", fileToUpload.getName(),
                         RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));
               } else {
                    System.err.println("File not found: " + pathImg);
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
                    System.err.println("Success add multiple files ");
                    dispose();
               }
               // Do something with the response.
          } catch (IOException e) {
               System.out.println("err = " + e);
          }

     }//GEN-LAST:event_jButton1ActionPerformed

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
     private javax.swing.JButton jButton1;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel panelImp;
     private javax.swing.JTable table;
     // End of variables declaration//GEN-END:variables
}
