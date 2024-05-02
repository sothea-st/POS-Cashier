package DeleteAndCancel;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.LabelPopUpTitle;
import Components.SubtotalPanel;
import Components.countCircleShape;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Event.ButtonEvent;
import HoldOrder.HoldDetail;
import HoldOrder.HoldItems;
import HoldOrder.HoldModelDir.DataListHold;
import HoldOrder.HoldModelDir.ListDetailHold;
import HoldOrder.HoldModelDir.ResultHoldSuccess;
import HoldOrder.HoldSuccess;
import HoldOrder.HoldeModel;
import Model.Package.ReasonModel;
import Model.PackageProduct.ProductIDModel;
import Model.PackageProduct.ProductModel;
import Products.ProductBox;
import UpdateQty.UpdateQtyModel;
import View.MainPage.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author FRONT-END.06
 */
public class CancelDialog extends javax.swing.JDialog {

     private JPanel detailItem;
     private SubtotalPanel totalPanel;
     private Button btnPayment;
     private HashMap<String, String> map = new HashMap<>();
     private String reasonId;
     private Component[] listCom;
     private ButtonCancel btnCancel;
     private Button buttonHoldOrder;
     private String code;
     private ArrayList<HoldeModel> holdId;
     private JPanel panelHold;
     private countCircleShape countCircleShape;
     private SubtotalPanel subtotalPanel;
     private String labelForTitle;
     private JPanel panelProduct;
     private int idHold;
     private Button btnReturn;
     private JLabel titleOrder;

     private String barcode;

     public String getBarcode() {
          return barcode;
     }

     public void setBarcode(String barcode) {
          this.barcode = barcode;
     }

     public int getIdHold() {
          return idHold;
     }

     public void setIdHold(int idHold) {
          this.idHold = idHold;
     }

    public Button getBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(Button btnReturn) {
        this.btnReturn = btnReturn;
    }

    public JLabel getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(JLabel titleOrder) {
        this.titleOrder = titleOrder;
    }
     
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");

     /**
      * Creates new form DeleteDialog
      */
     public CancelDialog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelCancel.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          addComboReason();
          // action get select customer type
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    reasonId = key;
               }
          };
          comboBoxReason.initEvent(events);

     }

     private void addComboReason() {

          try {
               ArrayList<ReasonModel> reason = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.reason + "cancel");
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         ReasonModel modelReason = new ReasonModel(
                              obj.getInt("id"),
                              obj.getString("reason")
                         );
                         reason.add(modelReason);
                         int idReason = reason.get(i).getIdReason();
                         String reasonName = reason.get(i).getReason();
                         map.put(reasonName, "" + idReason);

                    }
                    comboBoxReason.setMap(map);

               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCancel = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        lbReason = new Components.Label();
        comboBoxReason = new Components.ComboBox();
        cancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Cancel");

        lbReason.setLabelName("Reason");

        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        javax.swing.GroupLayout panelCancelLayout = new javax.swing.GroupLayout(panelCancel);
        panelCancel.setLayout(panelCancelLayout);
        panelCancelLayout.setHorizontalGroup(
            panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCancelLayout.createSequentialGroup()
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCancelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                    .addGroup(panelCancelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        panelCancelLayout.setVerticalGroup(
            panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCancelLayout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
         System.out.println("code :" + code);
         this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         if (code.equals("cancel")) {
              JSONObject jsonData = new JSONObject();
              ArrayList<ProductIDModel> listCancelDetail = new ArrayList<>();
              for (int i = 0; i < listCom.length; i++) {
                   var obj = ((BoxItem) listCom[i]);
                   ProductIDModel pro = new ProductIDModel(
                        obj.getProductId()
                   );
                   listCancelDetail.add(pro);
              }

              jsonData.put("listCancelDetail", listCancelDetail);
              jsonData.put("reasonId", reasonId);
              jsonData.put("createBy", JavaConstant.cashierId);

              try {

                   if (reasonId == null) {
                        JOptionPane.showMessageDialog(this, "Please select a reason!");
                        return;
                   }

                   Response response = JavaConnection.post(JavaRoute.cancelAndDelete + "cancel", jsonData);

                   if (response.isSuccessful()) {
                        JavaConstant.setBackQty(detailItem, panelProduct);
//                        updateCancelQty();
                        this.dispose();
                        detailItem.removeAll();
                        detailItem.revalidate();
                        detailItem.repaint();
                        clearTotal();
                        changeColorButtonPayment();
                        JavaConstant.productId = 0;
                        JavaConstant.productQTyLeft = 0;
                        JavaConstant.discountAmount = 1;
                        deleteHoldById();

                        detailItem.setBackground(WindowColor.slightGreen);
                        detailItem.setBorder(null);

                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");

                   }

              } catch (Exception e) {

              }
         } else if (code.equals("cancelHold")) {

              deleteHold();
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     // Add product to stock after cancel order
     private void updateCancelQty() {

          JSONObject json = new JSONObject();
          ArrayList<UpdateQtyModel> model = new ArrayList<>();
          for (int i = 0; i < listCom.length; i++) {
               var obj = ((BoxItem) listCom[i]);
               UpdateQtyModel _updateModel = new UpdateQtyModel(obj.getProductId(), obj.getQty(), "add");
               model.add(_updateModel);
          }
          json.put("listProId", model);

          Response _responseData = JavaConnection.post(JavaRoute.updateQty, json);
          try {
               String dataString = _responseData.body().string();
          } catch (Exception e) {
          }

          panelProduct.revalidate();
          panelProduct.repaint();

     }

     public countCircleShape getCountCircleShape() {
          return countCircleShape;
     }

     public void deleteHoldById() {
          if (JavaConstant.holdId != 0) {
               ArrayList<HoldeModel> holdId = new ArrayList<>();
               holdId.add(new HoldeModel(JavaConstant.holdId));
               JSONObject json = new JSONObject();
               json.put("reasonId", 0); // 0 meaning product was paid
               json.put("listHoldDetail", holdId);

               Response responseHold = JavaConnection.delete(JavaRoute.holdOrder, json);

               if (responseHold.isSuccessful()) {
                    JavaConstant.holdId = 0;
               }
          }

     }

     public void setCountCircleShape(countCircleShape countCircleShape) {
          this.countCircleShape = countCircleShape;
     }

     void deleteHold() {

          getHold();

          JSONObject json = new JSONObject();
          json.put("reasonId", reasonId);
          json.put("listHoldDetail", holdId);

          if (reasonId == null) {
               JOptionPane.showMessageDialog(this, "Please select a reason!");
               return;
          }

          Response response = JavaConnection.delete(JavaRoute.holdOrder, json);

          try {
               if (response.isSuccessful()) {

                    //          =========== update product status ================
                    Component[] listProductPanel = panelProduct.getComponents();

                    for (Component c : listProductPanel) {
                         var _data = ((ProductBox) c);
                         if (_data.getBarcode().equals(barcode)) {
                              _data.setProductStatus(JavaMessage.inStock);
                         }
                    }

                    dispose();
                    panelHold.removeAll();
                    panelHold.revalidate();
                    panelHold.repaint();
//                    getHoldItem(panelHold);
                    int count = new MainPage().countHold();
                    countCircleShape.setCountTimes("" + count);
                    detailItem.removeAll();
                    detailItem.revalidate();
                    detailItem.repaint();

                    detailItem.setBackground(WindowColor.slightGreen);
                    detailItem.setBorder(null);
               }
          } catch (Exception e) {
               System.err.println("errr delete + " + e);
          }

     }

     public void getHold() {
          DataListHold[] listHoldData;
          ListDetailHold[] listHoldDetails;
          try {
               Response response = JavaConnection.get(JavaRoute.holdOrder + "?userId=" + JavaConstant.cashierId + "&id=" + idHold);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    ResultHoldSuccess data = objMap.readValue(responseData, ResultHoldSuccess.class);
                    listHoldData = data.getData();

                    Component[] listCome1 = panelProduct.getComponents();

                    //    =============== update qty with hole ==================
                    if (listHoldData.length > 0) {

                         for (DataListHold cv : listHoldData) {
                              ListDetailHold[] l = cv.getListDetails();
                              for (ListDetailHold dd : l) {
                                   String barcode = dd.getBarcode();

                                   int holdQty = dd.getQty();
                                   for (Component bb : listCome1) {
                                        var datas = ((ProductBox) bb);
                                        int qtyShow = Integer.parseInt(datas.getQty());
                                        if (barcode.equals(datas.getBarcode())) {
                                             int qty = holdQty + qtyShow;
                                             datas.setQty("" + qty);
                                             break;
                                        }
                                   }
                              }
                         }
                    }
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

//     public void getHoldItem(JPanel panelHold) {
//          try {
//               Response response = JavaConnection.get(JavaRoute.holdOrder);
//               System.err.println("fffffffffff = " + response);
//               if (response.isSuccessful()) {
//                    String responseData = response.body().string();
//                    ObjectMapper objMap = new ObjectMapper();
//                    ResultHoldSuccess data = objMap.readValue(responseData, ResultHoldSuccess.class);
//                    DataListHold[] listData = data.getData();
//                    appendValue(listData, panelHold);
//               } else {
//                    System.err.println("fail loading product");
//               }
//          } catch (Exception e) {
//               System.err.println("error getting product " + e);
//          }
//     }
     private void appendValue(DataListHold[] listData, JPanel panelHold) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelHold.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

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
               HoldItems h = new HoldItems();

               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String key) { // action process
                         detailItem.removeAll();
                         panelHold.revalidate();
                         panelHold.repaint();
                         Response responseData = JavaConnection.get(JavaRoute.holdOrder + "/" + obj.getID());
                         int count = new MainPage().countHold();
                         count--;
                         countCircleShape.setCountTimes("" + count);
                         JavaConstant.holdId = obj.getID();

                         try {
                              if (responseData.isSuccessful()) {
                                   String data = responseData.body().string();
                                   ObjectMapper objMap = new ObjectMapper();
                                   HoldSuccess model = objMap.readValue(data, HoldSuccess.class);
                                   HoldDetail[] listDatas = model.getData().getDetails();

                                   for (int i = 0; i < listDatas.length; i++) {
                                        var obj = listDatas[i];
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
                              }
                         } catch (Exception e) {
                              System.out.println("error get hold = " + e);
                         }

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
                         cancel.setLabelForTitle("Delete");
                         cancel.setVisible(true);
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
                         deleteHoldById();
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

               box.setDiscountAmount(dm.format(discount));
               box.setDiscountAmt(dm.format(discount));

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

               subtotalPanel.total(_p, listCom, discount, subtotalPanel);

               // add list has one box to BoxItem (note: must be add)
               Component[] listCom1 = detailItem.getComponents();
               box.setDetailItem(detailItem);
               box.setSubtotalPanel(subtotalPanel);
               box.setListCom(listCom1);

          } catch (Exception e) {
               System.out.println("err get product image " + e);
          }
     }

     public String getLabelForTitle() {
          return labelForTitle;
     }

     public void setLabelForTitle(String labelForTitle) {
          this.labelForTitle = labelForTitle;
          titlePopUp.setLabelTitle(labelForTitle);
     }

     public String getCode() {
          return code;
     }

     public void setCode(String code) {
          this.code = code;
     }

     public ArrayList<HoldeModel> getHoldId() {
          return holdId;
     }

     public void setHoldId(ArrayList<HoldeModel> holdId) {
          this.holdId = holdId;
     }

     public JPanel getPanelHold() {
          return panelHold;
     }

     public void setPanelHold(JPanel panelHold) {
          this.panelHold = panelHold;
     }

     void changeColorButtonPayment() {
          Component[] listCom1 = detailItem.getComponents();
          if (listCom1.length == 0) {
               btnPayment.setBackground(WindowColor.lightGray);
               btnCancel.setBackground(WindowColor.lightGray);
               buttonHoldOrder.setBackground(WindowColor.lightGray);
               btnReturn.setBackground(WindowColor.brown);
               titleOrder.setVisible(false);
               getDetailItem().setBorder(null);
          }
     }

     void clearTotal() {
          totalPanel.setLabelSubtotalKhr("0");
          totalPanel.setLabelSubtotalUsd("$ 0.00");
          totalPanel.setLableDiscountKhr("0");
          totalPanel.setLableDiscountUsd("$ 0.00");
          totalPanel.setLableDeliveryUsd("$ 0.00");
          totalPanel.setLableTotalKhr("0");
          totalPanel.setLableTotalUsd("$ 0.00");
          totalPanel.setLableDeliveryKhr("0");
          totalPanel.setLableDeliveryUsd("$ 0.00");
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
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    CancelDialog dialog = new CancelDialog(new javax.swing.JFrame(), true);
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

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public SubtotalPanel getTotalPanel() {
          return totalPanel;
     }

     public void setTotalPanel(SubtotalPanel totalPanel) {
          this.totalPanel = totalPanel;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonSave buttonSave;
    private ButtonPackage.ButtonCancel cancel;
    private Components.ComboBox comboBoxReason;
    private javax.swing.JLabel jLabel1;
    private Components.Label lbReason;
    private javax.swing.JPanel panelCancel;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
