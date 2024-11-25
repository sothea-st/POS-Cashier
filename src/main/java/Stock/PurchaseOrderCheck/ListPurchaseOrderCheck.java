package Stock.PurchaseOrderCheck;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Model.PurchaseOrder.DataPurchaseModel;
import Model.PurchaseOrder.ListPurchaseOrderModel;
import Stock.PurchaseOrderRequest.PurchaseNoData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

public class ListPurchaseOrderCheck extends javax.swing.JDialog {

     private String pageNumber = "0";
     private int pageSize = 10;
     private String typeForm;
     private String searchValue;
     private boolean isCheckSearch = true;

     public ListPurchaseOrderCheck(java.awt.Frame parent, boolean modal) {
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
          JavaConstant.addTitleAndLogo(this, "Purchase Order Check");
          eventSearchPuchaseOrder(this);
          
          eventPagination(this,true);
     }
     
     
     
       //Pagination
     private void eventPagination(ListPurchaseOrderCheck listPurchaseOrderCheck , boolean  isCheck) {
          ButtonEvent paginationEvent = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value) - 1; // value pageNumber star from 1 
                         pageNumber = String.valueOf(_value);
                         getData(listPurchaseOrderCheck,isCheck);
                    }
               }
          };
          paginationPanel.initEvent(paginationEvent);
     }


     private void eventSearchPuchaseOrder(ListPurchaseOrderCheck obj) {
          ButtonEvent events = new ButtonEvent() {
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
                                   getData(obj, true);
                                   return;
                              }
                              getData(obj, false);
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);
               }
          };
          searchField.initEvent(events);
     }

     public String getTypeForm() {
          return typeForm;
     }

     public void setTypeForm(String typeForm) {
          this.typeForm = typeForm;
          getData(this, true);
     }

     public void getData(ListPurchaseOrderCheck obj, boolean isCheck) {
          Response response = null;
          String remark = typeForm.equals("checked") ? "requested" : "checked";

          if (isCheck) {
               response = JavaConnection.get(JavaRoute.imports + "/getListByRemark?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&remark=" + remark);
              
          } else {
               isCheckSearch = false;
               response = JavaConnection.get(JavaRoute.imports + "/filter/" + searchValue + "?remark=" + remark);
          }
          
   
          try {
               String responseData = response.body().string();
               ObjectMapper objMap = new ObjectMapper();
               ListPurchaseOrderModel data = objMap.readValue(responseData, ListPurchaseOrderModel.class);
               DataPurchaseModel[] listData = data.getData();
               
               
               if (isCheckSearch) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
               } else {
                    paginationPanel.resetPage(data.getCount());
               }
               reloadPanel();
               appendPurchaeOrder(listData, obj);
          } catch (Exception e) {
               System.out.println("error : " + e);
          }

     }

     void reloadPanel() {
          listGetOrder.removeAll();
          listGetOrder.revalidate();
          listGetOrder.repaint();
     }

     void appendPurchaeOrder(DataPurchaseModel[] listData, ListPurchaseOrderCheck obj) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetOrder.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          if (listData.length != 0) {
               for (int i = 0; i < listData.length; i++) {
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
                    var data = listData[i];
                    GetPurchaseOrderCheck b = new GetPurchaseOrderCheck();
                    b.setVendorName(data.getVendorName());
                    b.setTransactionNo(data.getTransactionNo());
                    b.setReferenceNo(data.getReferenceNo());
                    b.setTransactionDate(data.getTransactionDate());
                    b.setTotalQty(String.valueOf(data.getTotalQty()));
                    b.setTotalCost("$ " + String.format("%.2f", data.getTotalCost()));
                    b.setRemark(StringUtils.capitalize(data.getRemark()));

                    ButtonEvent events = new ButtonEvent() {
                         @Override
                         public void onSelectDetail(String Key) {  // event checked
                              DetailPurchaseOrderCheck detail = new DetailPurchaseOrderCheck(new JFrame(), true);
                              
                              try {
                                   Response response = JavaConnection.get(JavaRoute.imports + "/" + data.getId());
                                
                                   String responseData = response.body().string();
                                   ObjectMapper objectMapper = new ObjectMapper();
                                   PurchaseOrderCheckModel model = objectMapper.readValue(responseData, PurchaseOrderCheckModel.class);
                                   POCheckDetailsModel detailData = model.getData();
                                   detail.setpOCheckDetailsModel(detailData, typeForm, data.getId());
                                   detail.setObj(obj);
                                   detail.setVisible(true);
                              } catch (Exception e) {
                                   System.err.println("error getting purchase order " + e);
                              }
                         }
                    };

                    b.initEvent(events);

//                    try {
//
//                         TimerTask task = new TimerTask() {
//                              @Override
//                              public void run() {
//                                   // Task to be executed
//                                   b.setIconDetail(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "3141f98b-6212-4fa2-8a16-91a9cbc2af62")));
//                              }
//                         };
//
//                         Timer timer = new Timer();
//                         timer.schedule(task, 500); // Delays task execution by 1 second
//
//                    } catch (Exception e) {
//                         System.err.println("error read image = " + e);
//                    }

                    if (typeForm.toLowerCase().equals("checked")) {
                         if (data.getRemark().toLowerCase().equals("requested")) {
                              listGetOrder.add(b, gbc);
                         }
                    } else if (typeForm.toLowerCase().equals("approved")) {
                         if (data.getRemark().toLowerCase().equals("checked")) {
                              listGetOrder.add(b, gbc);
                         }
                    }
                    paginationPanel.setVisible(true);

               }
          } else {
               PurchaseNoData no = new PurchaseNoData();
               listGetOrder.add(no);
               paginationPanel.setVisible(false);
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
        jLabel8 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetOrder = new javax.swing.JPanel();
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

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Status");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
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
            .addGap(0, 431, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetOrder);

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
                .addGap(20, 20, 20)
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListProductLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 20, Short.MAX_VALUE))
        );
        panelListProductLayout.setVerticalGroup(
            panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListProductLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

     /**
      * @param args the command line
      * arguments
      */
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
               java.util.logging.Logger.getLogger(ListPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListPurchaseOrderCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListPurchaseOrderCheck dialog = new ListPurchaseOrderCheck(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listGetOrder;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListProduct;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
