package Stock.PurchaseOrder;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.PurchaseOrder.DataPurchaseModel;
import Model.PurchaseOrder.DetailPurchaseModelFirst;
import Model.PurchaseOrder.DetailPurchaseModelSecond;
import Model.PurchaseOrder.ListPurchaseOrderModel;
import Model.PurchaseOrder.PurchaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONObject;

public class PurchaseOrder extends javax.swing.JDialog {

     String searchValue;
     private String pageNumber = "0";
     private int pageSize = 10;
     private boolean isCheckSearch = true;

     public PurchaseOrder(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          header.setBackground(WindowColor.darkGreen);
          JavaConstant.addTitleAndLogo(this, "Purchase Order");
          getListPurchase(listGetOrder, true);
          eventSearchPuchaseOrder();

          eventPagination();
     }

     private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         getListPurchase(listGetOrder, true);
                    }
               }
          };
          paginationPanel.initEvent(event);
     }

     public void getListPurchase(JPanel jpanelData, boolean isCheck) {
          try {
               Response response = null;
               if (isCheck) { // isCheck true get items
                    response = JavaConnection.get(JavaRoute.imports + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
               } else { // isCheck false search
                    isCheckSearch = false;
                    response = JavaConnection.get(JavaRoute.searchPurchase + searchValue + "?pageNumber=" + pageNumber + "&pageSize=50");
               }
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ListPurchaseOrderModel data = objMap.readValue(responseData, ListPurchaseOrderModel.class);
                    DataPurchaseModel[] listData = data.getData();
                    if (isCheck) {
                         paginationPanel.setTotalPage(data.getCount(), pageSize);
                    } else {
                         paginationPanel.resetPage();
                    }
                    assignPurchase(listData, jpanelData);
               } else {
                    System.err.println("fail loading purchase");
               }
          } catch (Exception e) {
               System.err.println("error getting purchase " + e);
          }
     }

     public void assignPurchase(DataPurchaseModel[] listData, JPanel assignPurchase) {
          ArrayList<PurchaseModel> purchase = new ArrayList<>();

          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               PurchaseModel getPurchase = new PurchaseModel(
                    obj.getId(),
                    obj.getTransactionNo(),
                    obj.getVendorName(),
                    obj.getReferenceNo(),
                    obj.getTransactionDate(),
                    obj.getTotalQty(),
                    obj.getTotalCost()
               );
               purchase.add(getPurchase);
          }

          appendPurchaseOrder(purchase, listGetOrder);
     }

     private void reloadPanel() {
          listGetOrder.removeAll();
          listGetOrder.revalidate();
          listGetOrder.repaint();
     }

     void appendPurchaseOrder(ArrayList<PurchaseModel> listPurchase, JPanel listGetOrder) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetOrder.setLayout(gridBagLayout);
          reloadPanel();
          int x = 0;
          int y = 0;
          if (listPurchase.size() > 0) {
               for (int i = 0; i < listPurchase.size(); i++) {
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
                    var listData = listPurchase.get(i);

                    GetPurchaseOrder b = new GetPurchaseOrder();

                    ButtonEvent events = new ButtonEvent() {
                         @Override
                         public void onSelectDetail(String Key) {  // event edit
                              DetailPurchaseOrder detail = new DetailPurchaseOrder(new JFrame(), true, listData.getId());
                              try {
                                   Response response = JavaConnection.get(JavaRoute.imports + "/" + listData.getId());
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   DetailPurchaseModelFirst data = objMap.readValue(responseData, DetailPurchaseModelFirst.class);
                                   DetailPurchaseModelSecond listDataOne = data.getData();

                                   detail.setVendorName(listDataOne.getVendorName());
                                   detail.setTransacionNo("" + listDataOne.getTransactionNo());
                                   detail.setPurchaseOrderNo(listDataOne.getPurchaseOrderNo());
                                   detail.setReferenceNo(listDataOne.getReferenceNo());
                                   detail.setTransactionDate(listDataOne.getTransactionDate());
                                   detail.setOrderDate(listDataOne.getOrderDate());
                                   detail.setTotalQty("" + listDataOne.getTotalQty());
                                   detail.setTotalCost("" + listDataOne.getTotalCost());
                                   detail.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting purchase order " + e);
                              }
                         }

                         @Override
                         public void onSelect(String Key) {  // event edit
                              EditPurchaseOrder edit = new EditPurchaseOrder(new JFrame(), true, listData.getId());
                              try {
                                   Response response = JavaConnection.get(JavaRoute.imports + "/" + listData.getId());
                                   String responseData = response.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   DetailPurchaseModelFirst data = objMap.readValue(responseData, DetailPurchaseModelFirst.class);
                                   DetailPurchaseModelSecond listDataOne = data.getData();
                                   edit.setListGetOrder(listGetOrder);
                                   edit.setValue(
                                        String.valueOf(listDataOne.getVendorName()),
                                        String.valueOf(listDataOne.getReferenceNo()),
                                        String.valueOf(listDataOne.getTransactionNo()),
                                        String.valueOf(listDataOne.getPurchaseOrderNo()),
                                        String.valueOf(listDataOne.getTotalQty()),
                                        String.valueOf(listDataOne.getTotalCost()),
                                        String.valueOf(listDataOne.getVendorId()),
                                        String.valueOf(listDataOne.getOrderDate()),
                                        String.valueOf(listDataOne.getTransactionDate())
                                   );

                                   edit.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting purchase order " + e);
                              }
                         }

                         @Override
                         public void onRemove(String Key) {  // event delete 
                              try {
                                   UIManager UI = new UIManager();
                                   UI.put("OptionPane.background", WindowColor.mediumGreen);
                                   UI.put("Panel.background", WindowColor.mediumGreen);
                                   UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                                   int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this purchase order?",
                                        "Delete Purchase Order?", JOptionPane.YES_NO_OPTION);

                                   if (resp == JOptionPane.YES_OPTION) {
                                        JSONObject json = new JSONObject();
                                        Response response = JavaConnection.delete(JavaRoute.imports + "/" + listData.getId(), json);

                                        if (response.isSuccessful()) {
                                             listGetOrder.removeAll();
                                             listGetOrder.revalidate();
                                             listGetOrder.repaint();
                                             getListPurchase(listGetOrder,true);
                                             System.out.println("Successful deleted ");
                                        }
                                   } else {
                                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                   }

                              } catch (Exception e) {
                                   System.err.println("error getting purchase order " + e);
                              }
                         }
                    };

                    b.initEvent(events);

                    b.setId(listData.getId());
                    b.setVendorName(listData.getVendorName());
                    b.setReferenceNo(listData.getReferenceNo());
                    b.setTransactionDate(listData.getTransactionDate());
                    b.setTotalQty("" + listData.getTotalQty());
                    b.setTotalCost("$ " + listData.getTotalCost());
                    b.setTransactionNo(listData.getTransactionNo());

                    try {

                         TimerTask task = new TimerTask() {
                              @Override
                              public void run() {
                                   // Task to be executed
                                   b.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                                   b.setIconDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                                   b.setIconDetail(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "3141f98b-6212-4fa2-8a16-91a9cbc2af62")));
                              }
                         };

                         Timer timer = new Timer();
                         timer.schedule(task, 500); // Delays task execution by 1 second

                    } catch (Exception e) {
                         System.err.println("error read image = " + e);
                    }

                    listGetOrder.add(b, gbc);
               }
          } else {
               PurchaseNoData no = new PurchaseNoData();
               listGetOrder.add(no);
          }

          listGetOrder.revalidate();
          listGetOrder.repaint();
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
        jLabel7 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetOrder = new javax.swing.JPanel();
        button1 = new Button.Button();
        btnCancel = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actions");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reference №");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Transaction Date");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Vendor Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Qty");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Cost");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Transaction №");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane1.setBorder(null);

        listGetOrder.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetOrderLayout = new javax.swing.GroupLayout(listGetOrder);
        listGetOrder.setLayout(listGetOrderLayout);
        listGetOrderLayout.setHorizontalGroup(
            listGetOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listGetOrderLayout.setVerticalGroup(
            listGetOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetOrder);

        button1.setBackground(new java.awt.Color(47, 155, 70));
        button1.setButtonName("+ Add Purchase Order");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
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
                .addGap(15, 15, 15)
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListProductLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListProductLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
         AddPurchaseOrder add = new AddPurchaseOrder(new JFrame(), true);
         add.setVisible(true);
    }//GEN-LAST:event_button1MouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

     private void eventSearchPuchaseOrder() {
          // this event was called when user type on searchTextField 
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    searchValue = searchField.getValueTextSearch();
                    if (searchValue.isEmpty()) {
                         isCheckSearch = true;
                         pageNumber = "0";
                         getListPurchase(listGetOrder, true);
                         return;
                    }
                    getListPurchase(listGetOrder, false);
//                    if (searchValue.isEmpty()) {
//                         listGetOrder.removeAll();
//                         listGetOrder.revalidate();
//                         listGetOrder.repaint();
//                         getListPurchase(listGetOrder);
//                    } else {
//
//                         Response response = JavaConnection.get(JavaRoute.searchPurchase + searchValue + "?pageNumber=0&pageSize=100");
//
//                         if (response.isSuccessful()) {
//                              try {
//                                   listGetOrder.removeAll();
//                                   listGetOrder.revalidate();
//                                   listGetOrder.repaint();
//                                   String responseData = response.body().string();
//                                   ObjectMapper objMap = new ObjectMapper();
//                                   ListPurchaseOrderModel data = objMap.readValue(responseData, ListPurchaseOrderModel.class);
//                                   DataPurchaseModel[] listData = data.getData();
//
//                                   if (listData.length > 0) {
//                                        assignPurchase(listData, listGetOrder);
//                                   } else {
//                                        listGetOrder.removeAll();
//                                        PurchaseNoData notfound = new PurchaseNoData();
//                                        notfound.setLabelName("Not Found!");
//                                        listGetOrder.add(notfound);
//                                        listGetOrder.revalidate();
//                                        listGetOrder.repaint();
//                                   }
//
//                              } catch (Exception e) {
//                                   System.out.println("err from search purchase = " + e);
//                              }
//                         }
//                    }
               }
          };
          searchField.initEvent(events);
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
               java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PurchaseOrder dialog = new PurchaseOrder(new javax.swing.JFrame(), true);
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
    private Button.Button button1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listGetOrder;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListProduct;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
