package Setting.Attribute;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Attribute.Attribute;
import Model.Attribute.DataAttributeModel;
import Model.Attribute.DetailAttributeModel;
import Model.Attribute.ListAttributeModel;
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

public class ListAttribute extends javax.swing.JDialog {

     String searchValue;
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private int dataCount = 0;
     private String pageType;

     public ListAttribute(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          header.setBackground(WindowColor.darkGreen);
          JavaConstant.addTitleAndLogo(this, "Attribute");

          getAttribute(listGetAttribute, true, pageNumber);
          eventSearchAttribute();
          eventPagination();

          // check permission
          // permissionId: 21 is primary key id from table pos_permission
          btnAdd.setVisible(JavaPermission.getPermissionDetail(21).getIsCreate());
     }

     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         getAttribute(listGetAttribute, true, pageNumber);
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

     public void getAttribute(JPanel jpanelData, boolean isCheck, String pageNumber) {
          try {

               Response response = null;
               if (isCheck) { // isCheck true get items
                    response = JavaConnection.get(JavaRoute.attribute + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
               } else { // isCheck false search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.searchAttribute + searchValue);
               }

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ListAttributeModel data = objMap.readValue(responseData, ListAttributeModel.class);
                    DataAttributeModel[] listData = data.getData();

                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage(data.getCount());
                    }

                    assignAttribute(listData, jpanelData);
               } else {
                    System.err.println("fail loading attribute");
               }
          } catch (Exception e) {
               System.err.println("error getting attribute " + e);
          }
     }

     public void assignAttribute(DataAttributeModel[] listData, JPanel listGetAttribute) {
          ArrayList<Attribute> attr = new ArrayList<>();

          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               Attribute getAttr = new Attribute(
                    obj.getId(),
                    obj.getAttrNameEn(),
                    obj.getAttrNameKh()
               );
               attr.add(getAttr);
          }

          appenAttribute(attr, listGetAttribute);
     }

     private void reloadPanel() {
          listGetAttribute.removeAll();
          listGetAttribute.revalidate();
          listGetAttribute.repaint();
     }

     void appenAttribute(ArrayList<Attribute> listAttribute, JPanel listGetAttribute) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetAttribute.setLayout(gridBagLayout);
          reloadPanel();

          int x = 0;
          int y = 0;

          if (listAttribute.size() > 0) {
               for (int i = 0; i < listAttribute.size(); i++) {
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

                    var listData = listAttribute.get(i);

                    GetCategory b = new GetCategory();
                    b.checkPermission("attribute");
                    ButtonEvent events = new ButtonEvent() {
                         @Override
                         public void onSelect(String Key) {  // event edit
                              
                              try {
                                   Response response = JavaConnection.get(JavaRoute.attribute + "/" + listData.getId());
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   DetailAttributeModel data = objMap.readValue(responseData, DetailAttributeModel.class);

                                   dispose();
                                   AddAttribute edit = new AddAttribute(new JFrame(), true);
                                   System.out.println("data : " + data);

                                   edit.setId(data.getId());
                                   edit.setListGetAttribute(listGetAttribute);
                                   edit.setPageNumber(pageNumber);

                                   edit.setValueEdit(
                                        data.getAttrNameEn(),
                                        data.getAttrNameKh()
                                   );

                                   edit.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting attribute " + e);
                              }
                         }

                         @Override
                         public void onRemove(String Key) {  // event delete attribute
                              try {
                                   UIManager UI = new UIManager();
                                   UI.put("OptionPane.background", WindowColor.mediumGreen);
                                   UI.put("Panel.background", WindowColor.mediumGreen);
                                   UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                                   int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this attribute?",
                                        "Delete Attribute?", JOptionPane.YES_NO_OPTION);

                                   if (resp == JOptionPane.YES_OPTION) {
                                        JSONObject json = new JSONObject();
                                        json.put("status", false);
                                        json.put("isDeleted", true);
                                        Response response = JavaConnection.delete(JavaRoute.attribute + "/" + listData.getId(), json);

                                        if (response.isSuccessful()) {

                                             dataCount = dataCount - 1;
                                             int totalP = pageSize * Integer.valueOf(pageNumber);
                                             if (dataCount == totalP) {
                                                  paginationPanel.resetPage(pageType, pageNumber);
                                                  int _value = Integer.parseInt(pageNumber) - 1; // value pageNumber star from 0 
                                                  pageNumber = String.valueOf(_value);
                                             }

                                             listGetAttribute.removeAll();
                                             listGetAttribute.revalidate();
                                             listGetAttribute.repaint();
                                             getAttribute(listGetAttribute, true, pageNumber);
                                             System.out.println("Successful deleted ");
                                        }
                                   } else {
                                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                   }

                              } catch (Exception e) {
                                   System.err.println("error getting attribute " + e);
                              }
                         }
                    };

                    b.initEvent(events);
                    b.setId(listData.getId());

                    b.setCategoryNameEn(listData.getAttributeNameEn());
                    b.setCategoryNameKh(listData.getAttributeNameKh());
                    paginationPanel.setVisible(true);
                    listGetAttribute.add(b, gbc);
               }
          } else {
               NoDataAvaibalePanel no = new NoDataAvaibalePanel();
               listGetAttribute.add(no);
               paginationPanel.setVisible(false);
          }

          listGetAttribute.revalidate();
          listGetAttribute.repaint();
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListAttribute = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetAttribute = new javax.swing.JPanel();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        btnAdd = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Attribute Name (KH)");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Attribute Name");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetAttribute.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetAttributeLayout = new javax.swing.GroupLayout(listGetAttribute);
        listGetAttribute.setLayout(listGetAttributeLayout);
        listGetAttributeLayout.setHorizontalGroup(
            listGetAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        listGetAttributeLayout.setVerticalGroup(
            listGetAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetAttribute);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Attribute");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListAttributeLayout = new javax.swing.GroupLayout(panelListAttribute);
        panelListAttribute.setLayout(panelListAttributeLayout);
        panelListAttributeLayout.setHorizontalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListAttributeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListAttributeLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListAttributeLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelListAttributeLayout.setVerticalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListAttributeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        AddAttribute add = new AddAttribute(new JFrame(), true);
        add.setPageNumber(pageNumber);
        add.setListGetAttribute(listGetAttribute);
        add.setObj(this);
        add.setVisible(true);
    }//GEN-LAST:event_btnAddMouseClicked

     //Action Search
     private void eventSearchAttribute() {

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
                                   getAttribute(listGetAttribute, true, pageNumber);
                                   return;
                              }
                              getAttribute(listGetAttribute, false, pageNumber);
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               }
          };
          searchField.initEvent(event);
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
               java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListAttribute dialog = new ListAttribute(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetAttribute;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListAttribute;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
