package Setting.Brand;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Brand.Brand;
import Model.Brand.BrandModel;
import Model.Brand.BrandSuccessModel;
import Model.Brand.DetailBrandModel;
import Setting.Category.GetCategory;
import Setting.Category.NoDataAvaibalePanel;
import Settings.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.user_permission.JavaPermission;
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

public class ListBrand extends javax.swing.JDialog {
     
     String searchValue;
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private int dataCount = 0;
     private String pageType;
     
     public ListBrand(java.awt.Frame parent, boolean modal) {
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
          getBrand(listGetBrand, true,pageNumber);
          
          JavaConstant.addTitleAndLogo(this, "Brand");
          
          
          eventSearchBrand();
          eventPagination();
          
          
          // check permission
          // permissionId: 19 is primary key id from table pos_permission
          btnAdd.setVisible(JavaPermission.getPermissionDetail(19).getIsCreate());
          
     }
     
     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         getBrand(listGetBrand, true, pageNumber);
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
     
     public void getBrand(JPanel jpanelData, boolean isCheck,String pageNumber) {
          try {
               
               Response response = null;
               if (isCheck) { // isCheck true get items
                    response = JavaConnection.get(JavaRoute.brand + "?pageNumber=" + pageNumber + "&pageSize=10");
               } else { // isCheck false search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.searchBrand + searchValue);
               }
               
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    BrandSuccessModel data = objMap.readValue(responseData, BrandSuccessModel.class);
                    BrandModel[] listData = data.getData();
                    
                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage(data.getCount());
                    }
                    
                    assignBrand(listData, jpanelData);
               } else {
                    System.err.println("fail loading brand");
               }
          } catch (Exception e) {
               System.err.println("error getting brand " + e);
          }
     }
     
     public void assignBrand(BrandModel[] listData, JPanel listGetBrand) {
          ArrayList<Brand> brand = new ArrayList<>();
          
          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               Brand getBrand = new Brand(
                    obj.getId(),
                    obj.getBrandNameEn(),
                    obj.getBrandNameKh()
               );
               brand.add(getBrand);
          }
          
          appendBrand(brand, listGetBrand);
     }
     
     private void reloadPanel() {
          listGetBrand.removeAll();
          listGetBrand.revalidate();
          listGetBrand.repaint();
     }
     
     void appendBrand(ArrayList<Brand> listBrand, JPanel listGetBrand) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          
          listGetBrand.setLayout(gridBagLayout);
          reloadPanel();
          
          int x = 0;
          int y = 0;
          if (!listBrand.isEmpty()) {
               for (int i = 0; i < listBrand.size(); i++) {
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
                    
                    var listData = listBrand.get(i);
                    GetCategory b = new GetCategory();
                    b.checkPermission("brand");
                    ButtonEvent events = new ButtonEvent() {
                         @Override
                         public void onSelect(String Key) {  // event edit
                              try {
                                   Response response = JavaConnection.get(JavaRoute.brand + "/" + listData.getId());
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   DetailBrandModel listData = objMap.readValue(responseData, DetailBrandModel.class);
                               
                                   dispose(); 
                                   InsertBrand edit = new InsertBrand(new JFrame(), true);
                                   edit.setId(listData.getId());
                                   edit.setPageNumber(pageNumber);
                             
                                   edit.setListGetBrand(listGetBrand);
                                   
                                   edit.setValueEdit(
                                        listData.getBrandNameEn(),
                                        listData.getBrandNameKh()
                                   );
                                   
                                   edit.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting brand " + e);
                              }
                              
                         }
                         
                         @Override
                         public void onRemove(String Key) {  // event delete brand 
                             try {
                                   UIManager UI = new UIManager();
                                   UI.put("OptionPane.background", WindowColor.mediumGreen);
                                   UI.put("Panel.background", WindowColor.mediumGreen);
                                   UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
                                   
                                   int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this brand?",
                                        "Delete Brand?", JOptionPane.YES_NO_OPTION);
                                   
                                   if (resp == JOptionPane.YES_OPTION) {
                                        JSONObject json = new JSONObject();
                                        json.put("status", false);
                                        json.put("isDeleted", true);
                                        Response response = JavaConnection.delete(JavaRoute.brand + "/" + listData.getId(), json);
                                        
                                        if (response.isSuccessful()) {
                                            
                                             dataCount = dataCount - 1;
                                             int totalP = pageSize * Integer.valueOf(pageNumber);
                                             if (dataCount == totalP) {
                                                  paginationPanel.resetPage(pageType, pageNumber);
                                                  int _value = Integer.parseInt(pageNumber) - 1; // value pageNumber star from 0 
                                                  pageNumber = String.valueOf(_value);
                                             }
                                             
                                             listGetBrand.removeAll();
                                             listGetBrand.revalidate();
                                             listGetBrand.repaint();
                                             getBrand(listGetBrand, true,pageNumber);
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
                    b.setCategoryNameEn(listData.getBrandNameEn());
                    b.setCategoryNameKh(listData.getBrandNameKh());
                   
                    paginationPanel.setVisible(true);
                    listGetBrand.add(b, gbc);
               }               
          } else {
               NoDataAvaibalePanel no = new NoDataAvaibalePanel();
               listGetBrand.add(no);
               paginationPanel.setVisible(false);
          }
          
          listGetBrand.revalidate();
          listGetBrand.repaint();
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
                                getBrand(listGetBrand, true,pageNumber);
                                return;
                           }
                           getBrand(listGetBrand, false,pageNumber);
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
        listGetBrand = new javax.swing.JPanel();
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
        jLabel8.setText("Brand Name (KH)");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Brand Name");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetBrand.setBackground(new java.awt.Color(176, 215, 181));
        listGetBrand.setPreferredSize(new java.awt.Dimension(664, 372));

        javax.swing.GroupLayout listGetBrandLayout = new javax.swing.GroupLayout(listGetBrand);
        listGetBrand.setLayout(listGetBrandLayout);
        listGetBrandLayout.setHorizontalGroup(
            listGetBrandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        listGetBrandLayout.setVerticalGroup(
            listGetBrandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetBrand);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Brand");
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
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane)
                    .addComponent(header1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelListCategoryLayout.setVerticalGroup(
            panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListCategoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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
         Settings settings = new Settings(new JFrame(), true);
         settings.setVisible(true);
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        
         dispose();
         InsertBrand insert = new InsertBrand(new JFrame(), true);
         insert.setListGetBrand(listGetBrand);
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
               java.util.logging.Logger.getLogger(ListBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListBrand dialog = new ListBrand(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel listGetBrand;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListCategory;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
