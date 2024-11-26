package Setting.Warehouse;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Warehouse.Warehouse;
import Model.Warehouse.WarehouseDetailModel;
import Model.Warehouse.WarehouseModel;
import Model.Warehouse.WarehouseModel.WarehouseDetail;
import Setting.Category.GetCategory;
import Setting.Category.NoDataAvaibalePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

public class ListWarehouse extends javax.swing.JDialog {
     
     String searchValue;
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private int dataCount = 0;
     private String pageType;
     
     public ListWarehouse(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          header1.setBackground(WindowColor.darkGreen);
          jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          getWarehouse(listGetWarehouse, true,pageNumber);
          
          JavaConstant.addTitleAndLogo(this, "Warehouse");
          
          eventSearchBrand();
          eventPagination();
     }
     
     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         getWarehouse(listGetWarehouse, true, pageNumber);
                    }
               }
               
               // for pagination
               @Override
               public void onMouseClick(String value, String pType) {
                    pageType = pType;
               }
          };
          paginationPanel.initEvent(event);
     }
     
     public void getWarehouse(JPanel jpanelData, boolean isCheck,String pageNumber) {
          try {
               
               Response response = null;
               if (isCheck) { // isCheck true get items
                    response = JavaConnection.get(JavaRoute.warehouse + "?pageNumber=" + pageNumber + "&pageSize=10");
               } else { // isCheck false search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.warehouse + "/search?" + searchValue);
               }
               
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    WarehouseModel data = objMap.readValue(responseData, WarehouseModel.class);
                    WarehouseDetail[] listData = data.getData();
                    
                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage();
                    }
                    
                    assignWarehouse(listData, jpanelData);
               } else {
                    System.err.println("fail loading warehouse");
               }
          } catch (Exception e) {
               System.err.println("error getting warehouse " + e);
          }
     }
     
     public void assignWarehouse(WarehouseDetail[] listData, JPanel listGetBrand) {
          ArrayList<Warehouse> warehouse = new ArrayList<>();
          
          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               
               Warehouse getWarehouse = new Warehouse(
                    obj.getId(),
                    obj.getWarehouseNameEn(),
                    obj.getWarehouseNameKh()
               );
               warehouse.add(getWarehouse);
          }
          
          appendWarehouse(warehouse, listGetWarehouse);
     }
     
     private void reloadPanel() {
          listGetWarehouse.removeAll();
          listGetWarehouse.revalidate();
          listGetWarehouse.repaint();
     }
     
     void appendWarehouse(ArrayList<Warehouse> listWarehouse, JPanel listGetWarehouse) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          
          listGetWarehouse.setLayout(gridBagLayout);
          reloadPanel();
          
          int x = 0;
          int y = 0;
          if (!listWarehouse.isEmpty()) {
               for (int i = 0; i < listWarehouse.size(); i++) {
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
                    
                    var listData = listWarehouse.get(i);
                    GetCategory b = new GetCategory();
                    
                    ButtonEvent events = new ButtonEvent() {
                         @Override
                         public void onSelect(String Key) {  // event edit
                              InsertWarehouse edit = new InsertWarehouse(new JFrame(), true);
                              
                              try {
                                   Response response = JavaConnection.get(JavaRoute.warehouse + "/" + listData.getId());
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   WarehouseDetailModel listData = objMap.readValue(responseData, WarehouseDetailModel.class);
                               
                                   edit.setId(listData.getData().getId());
                                   edit.setPageNumber(pageNumber);
                             
                                   edit.setListGetWarehouse(listGetWarehouse);
                                   edit.setObj(ListWarehouse.this);
                                   
                                   edit.setValueEdit(
                                        listData.getData().getWarehouseNameEn(),
                                        listData.getData().getWarehouseNameKh()
                                   );
                                   
                                   edit.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting warehouse " + e);
                              }
                              
                         }
                         
                         @Override
                         public void onRemove(String Key) {  // event delete brand
                              try {
                                   UIManager UI = new UIManager();
                                   UI.put("OptionPane.background", WindowColor.mediumGreen);
                                   UI.put("Panel.background", WindowColor.mediumGreen);
                                   UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
                                   
                                   int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this warehouse?",
                                        "Delete Warehouse?", JOptionPane.YES_NO_OPTION);
                                   
                                   if (resp == JOptionPane.YES_OPTION) {
                                        JSONObject json = new JSONObject();
                                        json.put("status", false);
                                        json.put("isDeleted", true);
                                        Response response = JavaConnection.delete(JavaRoute.warehouse + "/" + listData.getId(), json);
                                        
                                        if (response.isSuccessful()) {
                                            
                                             dataCount = dataCount - 1;
                                             int totalP = pageSize * Integer.valueOf(pageNumber);
                                             if (dataCount == totalP) {
                                                  paginationPanel.resetPage(pageType, pageNumber);
                                                  int _value = Integer.parseInt(pageNumber) - 1; // value pageNumber star from 0 
                                                  pageNumber = String.valueOf(_value);
                                             }
                                             
                                             listGetWarehouse.removeAll();
                                             listGetWarehouse.revalidate();
                                             listGetWarehouse.repaint();
                                             getWarehouse(listGetWarehouse, true,pageNumber);
                                             System.out.println("Successful deleted ");
                                        }
                                   } else {
                                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                   }
                                   
                              } catch (Exception e) {
                                   System.err.println("error getting brand " + e);
                              }
                         }
                    };
                    
                    b.initEvent(events);
                    b.setId(listData.getId());
                    b.setCategoryNameEn(listData.getWarehouseNameEn());
                    b.setCategoryNameKh(listData.getWarehouseNameKh());
                    
                    paginationPanel.setVisible(true);
                    listGetWarehouse.add(b, gbc);
               }               
          } else {
               NoDataAvaibalePanel no = new NoDataAvaibalePanel();
               listGetWarehouse.add(no);
               paginationPanel.setVisible(false);
          }
          
          listGetWarehouse.revalidate();
          listGetWarehouse.repaint();
     }

     //Action Search
     private void eventSearchBrand() {
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
                                 getWarehouse(listGetWarehouse, true,pageNumber);
                                return;
                           }
                           getWarehouse(listGetWarehouse, false,pageNumber);
                      }
                 };

                 Timer timer = new Timer();
                 timer.schedule(task, 500);

            }
        };
        searchField.initEvent(event);
     }
     
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListCategory = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetWarehouse = new javax.swing.JPanel();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        btnAdd = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Warehouse Name Kh");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Warehouse Name");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel8.getAccessibleContext().setAccessibleName("Warehouse Name Kh");

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetWarehouse.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetWarehouseLayout = new javax.swing.GroupLayout(listGetWarehouse);
        listGetWarehouse.setLayout(listGetWarehouseLayout);
        listGetWarehouseLayout.setHorizontalGroup(
            listGetWarehouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
        );
        listGetWarehouseLayout.setVerticalGroup(
            listGetWarehouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetWarehouse);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Warehouse");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListCategoryLayout = new javax.swing.GroupLayout(panelListCategory);
        panelListCategory.setLayout(panelListCategoryLayout);
        panelListCategoryLayout.setHorizontalGroup(
            panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListCategoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListCategoryLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListCategoryLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addComponent(header1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelListCategoryLayout.setVerticalGroup(
            panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
         dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
         InsertWarehouse insert = new InsertWarehouse(new JFrame(), true);
         insert.setListGetWarehouse(listGetWarehouse);
         insert.setPaginationPanel(paginationPanel);
         insert.setPageNumber(pageNumber);
         insert.setObj(this);
         insert.setVisible(true);
    }//GEN-LAST:event_btnAddMouseClicked
     
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
               java.util.logging.Logger.getLogger(ListWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListWarehouse dialog = new ListWarehouse(new javax.swing.JFrame(), true);
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
    private Button.Button btnAdd;
    private ButtonPackage.ButtonCancel buttonCancel1;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetWarehouse;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListCategory;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
