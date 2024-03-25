package HoldOrder;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.NoData;
import Components.NotFound;
import Components.SubtotalPanel;
import Components.countCircleShape;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import DeleteAndCancel.CancelDialog;
import Event.ButtonEvent;
import HoldOrder.HoldModelDir.DataListHold;
import HoldOrder.HoldModelDir.ListDetailHold;
import HoldOrder.HoldModelDir.ResultHoldSuccess;
import Model.PackageProduct.ProductModel;
import View.MainPage.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;
import okhttp3.Response;
import org.json.JSONObject;

public class ListHoldOrder extends javax.swing.JDialog {

     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private JPanel detailItem;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;
     private countCircleShape countCircleShape;
     private Button buttonHoldOrder;
     private ButtonCancel btnCancel;

     public ListHoldOrder(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          header.setBackground(WindowColor.darkGreen);
          panelHold.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          body.setBackground(WindowColor.mediumGreen);
          setResizable(false);
          cancel.setButtonName("Close");
          getHoldItem(panelHold);
     }

     DataListHold[] listData;
     
     public void getHoldItem(JPanel panelHold) {
          try {
               Response response = JavaConnection.get(JavaRoute.holdOrder+"?userId="+JavaConstant.cashierId);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ResultHoldSuccess data = objMap.readValue(responseData, ResultHoldSuccess.class);
                    listData = data.getData();
                  
                    appendValue(listData, panelHold);
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     private void appendValue(DataListHold[] listData, JPanel panelHold) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelHold.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          if(listData.length != 0){
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

                     var obj = listData[i];
                     ListDetailHold[] listDetails = listData[i].getListDetails();
                     HoldItems h = new HoldItems();

                     ButtonEvent events = new ButtonEvent() {
                          @Override
                          public void onSelect(String key) { // action process
                               detailItem.removeAll();
                               panelHold.revalidate();
                               panelHold.repaint();

                               for (int j = 0; j < listDetails.length; j++) {
                                    var obj = listDetails[j];
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
                                         obj.getQty()
                                    );
                                    addItemToCart(product);

                               }

      //                         Response responseData = JavaConnection.get(JavaRoute.holdOrder + "/" + obj.getID());
      //                         int count = new MainPage().countHold();
      //                         count--;
      //                         countCircleShape.setCountTimes(""+count);
      //                         JavaConstant.holdId = obj.getID();
      //                         try {
      //                              if (responseData.isSuccessful()) {
      //                                   String data = responseData.body().string();
      //                                   ObjectMapper objMap = new ObjectMapper();
      //                                   HoldSuccess model = objMap.readValue(data, HoldSuccess.class);
      //                                   HoldDetail[] listDatas = model.getData().getDetails();
      //                                   for (int i = 0; i < listDatas.length; i++) {
      //                                        var obj = listDatas[i];
      //                                        ProductModel product = new ProductModel(
      //                                             obj.getID(),
      //                                             obj.getCatID(),
      //                                             obj.getFlag(),
      //                                             obj.getWeight(),
      //                                             obj.getCost(),
      //                                             obj.getProImageName(),
      //                                             obj.getPrice(),
      //                                             obj.getBarcode(),
      //                                             obj.getProNameKh(),
      //                                             obj.getProNameEn(),
      //                                             obj.getProductStatus(),
      //                                             obj.getDiscount(),
      //                                             obj.getQty()
      //                                        );
      //                                        addItemToCart(product);
      //                                   }
      //                              }
      //                         } catch (Exception e) {
      //                              System.out.println("error get hold = " + e);
      //                         }
                               btnPayment.setBackground(WindowColor.lightBlue);
                               buttonHoldOrder.setBackground(WindowColor.yellow);
                               btnCancel.setBackground(WindowColor.darkred);

                               //Remove when Process
                               ArrayList<HoldeModel> lstModel = new ArrayList<>();
                               lstModel.add(new HoldeModel(obj.getID()));
                               JSONObject json = new JSONObject();
                               json.put("reasonId", 0);
                               json.put("listHoldDetail", lstModel);

                               Response response = JavaConnection.delete(JavaRoute.holdOrder, json);

                               try {
                                  if (response.isSuccessful()) {
                                     dispose();
                                     int count = new MainPage().countHold();
                                     countCircleShape.setCountTimes("" + count);
                                  }
                               } catch (Exception e) {
                                  System.err.println("errr delete + " + e);
                               }
                               dispose();
                          }

                          @Override
                          public void onRemove(String key) {
                               CancelDialog cancel = new CancelDialog(new JFrame(), true);
                               cancel.setCode("cancelHold");
                               ArrayList<HoldeModel> lstModel = new ArrayList<>();
                               lstModel.add(new HoldeModel(obj.getID()));
                               cancel.setHoldId(lstModel);
                               cancel.setPanelHold(panelHold);
                               cancel.setCountCircleShape(countCircleShape);
                               cancel.setDetailItem(detailItem);
                               cancel.setSubtotalPanel(subtotalPanel);
                               cancel.setLabelForTitle("Delete");
                               cancel.setVisible(true);
                               dispose();
                          }
                     };

                     h.initEvent(events);
                     int num = i + 1;
                     h.setCountNumber("" + num);
                     h.setQty(obj.getQtyHold());
                     panelHold.revalidate();
                     panelHold.repaint();
                     panelHold.add(h, gbc);
                }
          }
          else{
               NoData nodata = new NoData();
               panelHold.add(nodata);
          }

     }

     public void addItemToCart(ProductModel listData) {

          double price = listData.getPrice();
          double discount = (listData.getDiscount() * price) / 100;
          discount = JavaConstant.get4Length("" + discount); // get 2 precision

          try {
               BoxItem box = new BoxItem();
               box.setWasPrice("" + price);
               box.setBtnPayment(btnPayment);
               box.setButtonHoldOrder(buttonHoldOrder);
               box.setBtnCancel(btnCancel);
               Component[] listCom = detailItem.getComponents();
               if (listCom.length != 0) {
                    for (int i = 0; i < listCom.length; i++) {
                         var obj = ((BoxItem) listCom[i]);
                         int proId = obj.getProductId();
                         int qty = obj.getQty();
                         if (proId == listData.getId()) {
                              qty++;
                              obj.setQty(qty);
                              double newAmountUsd = qty * price;
                              if (listData.getDiscount() > 0) {
                                   newAmountUsd = price * qty;
                              }
                              obj.setLabelAmountUsd(dm.format(newAmountUsd));

                              double valueRoundDown1 = JavaRoundDown.roundDown("" + newAmountUsd * JavaConstant.exchangeRate);

                              obj.setLabelAmountKh(kh.format(valueRoundDown1));
                              box.setSubtotalPanel(subtotalPanel);
                              obj.setDiscountAmount(dm.format(qty * discount));

                              box.setListCom(listCom);
                              box.setDetailItem(detailItem);
                              subtotalPanel.total(0, listCom, 0, subtotalPanel);
                              return;
                         }
                    }
               }

               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onMouseClick() {
                         CancelDialog cancels = new CancelDialog(new JFrame(), true);
                         cancels.deleteHoldById();
                    }
               };
               box.initEvent(events);

               box.setDiscountDigit(listData.getDiscount());
               box.setLabelProductName(listData.getProductNameEn());
               box.setLabelWeight(listData.getWeight());
               box.setLabelBarcode(listData.getBarcode());

               box.setLabelPrice(dm.format(price));
               double _p = price * listData.getQty();
               box.setLabelAmountUsd(dm.format(_p));

               double valueRoundDown = JavaRoundDown.roundDown("" + _p * JavaConstant.exchangeRate);
               box.setLabelAmountKh(kh.format(valueRoundDown));

               
               box.setDiscountAmount(dm.format(discount*listData.getQty()));
               box.setDiscountAmt(dm.format(discount*listData.getQty()));

               box.setQty(listData.getQty());
               Response responseProductImage = JavaConnection.get(JavaRoute.readImage + listData.getProImageName());
               byte[] images = responseProductImage.body().bytes();

               box.setIconImage(new ImageIcon(images));
               box.setProductId(listData.getId());

               detailItem.add(box);
               // detailItem.add(Box.createRigidArea(new Dimension(2, 2)));
               detailItem.revalidate();
               detailItem.repaint();
               detailItem.setBorder(new BevelBorder(BevelBorder.RAISED));
               detailItem.setLayout(new BoxLayout(detailItem, BoxLayout.PAGE_AXIS));
               detailItem.setBackground(WindowColor.white);

               subtotalPanel.total(_p, listCom, discount*listData.getQty(), subtotalPanel);

               // add list has one box to BoxItem (note: must be add)
               Component[] listCom1 = detailItem.getComponents();
               box.setDetailItem(detailItem);
               box.setSubtotalPanel(subtotalPanel);
               box.setListCom(listCom1);

               btnPayment.setBackground(WindowColor.lightBlue);
               buttonHoldOrder.setBackground(WindowColor.yellow);
               btnCancel.setBackground(WindowColor.darkred);

          } catch (Exception e) {
               System.out.println("err get product image " + e);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();
        cancel = new ButtonPackage.ButtonCancel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelHold = new javax.swing.JPanel();
        removeAll = new ButtonPackage.ButtonCancel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cancel.setButtonName("Close");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("№ ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QTY");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Action");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        panelHold.setBackground(new java.awt.Color(255, 255, 255));
        panelHold.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelHoldLayout = new javax.swing.GroupLayout(panelHold);
        panelHold.setLayout(panelHoldLayout);
        panelHoldLayout.setHorizontalGroup(
            panelHoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelHoldLayout.setVerticalGroup(
            panelHoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelHold);

        removeAll.setButtonName("Remove All");
        removeAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeAllMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bodyLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeAll, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    // ACtion Remove All
    private void removeAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeAllMouseClicked
        listHolddata(listData);
    }//GEN-LAST:event_removeAllMouseClicked

    void listHolddata(DataListHold[] listData){
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            ArrayList<HoldeModel> lstModel = new ArrayList<>();
            lstModel.add(new HoldeModel(obj.getID()));
            JSONObject json = new JSONObject();
            json.put("reasonId", 0);
            json.put("listHoldDetail", lstModel);

            Response response = JavaConnection.delete(JavaRoute.holdOrder, json);

            try {
               if (response.isSuccessful()) {
                  dispose();
                  int count = new MainPage().countHold();
                  countCircleShape.setCountTimes("" + count);
                  btnCancel.setBackground(WindowColor.lightGray);
               }
            } catch (Exception e) {
               System.err.println("errr delete + " + e);
            }
            dispose();
        }
    }
    
     void refreshPanel() {
          panelHold.revalidate();
          panelHold.repaint();
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public countCircleShape getCountCircleShape() {
          return countCircleShape;
     }

     public void setCountCircleShape(countCircleShape countCircleShape) {
          this.countCircleShape = countCircleShape;
     }

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

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
               java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ListHoldOrder dialog = new ListHoldOrder(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel body;
    private ButtonPackage.ButtonCancel cancel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelHold;
    private ButtonPackage.ButtonCancel removeAll;
    // End of variables declaration//GEN-END:variables
}
