package DeleteAndCancel;

import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Package.ReasonModel;
import Model.PackageProduct.ProductIDModel;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeleteDialog extends javax.swing.JDialog {

     // declar variable
     private JPanel detailItem;
     private Component[] listCom;
     private int productId;
     private SubtotalPanel subtotalPanel;
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");
     DecimalFormat kh = new DecimalFormat("#,##0");
     private String reasonId;
     private Button.Button btnPayment;

     public DeleteDialog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelDelete.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          addComboReason();

          // action get select reason type
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    reasonId = key;

               }
          };
          comboBoxReason.initEvent(event);
     }

     private void addComboReason() {
          HashMap<String, String> map = new HashMap<>();
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

     void deleteItem() {
          double sumSubTotalUsd = 0;
          double sumDiscount = 0;

          for (int i = 0; i < listCom.length; i++) {
               var d = (BoxItem) listCom[i];
               if (productId == d.getProductId()) {
                    detailItem.remove(i);
                    detailItem.revalidate();
                    detailItem.repaint();
               } else {
                    var data = (BoxItem) listCom[i];
                    String priceStr = data.getLabelPrice();
                    priceStr = priceStr.replace("$", "");
                    priceStr = priceStr.replace(",", "");
                    double price = Double.valueOf(priceStr);
                    int qty = data.getQty();
                    double amount = price * qty;
                    sumSubTotalUsd += amount;

                    String discount = data.getDiscountAmount();
                    discount = discount.replace("$", "");
                    discount = discount.replace(",", "");
                    double discountValue = JavaConstant.getReplace(d.getDiscountAmount());
                    sumDiscount += discountValue;
               }
          }

          Component[] l = detailItem.getComponents();

          if (l.length == 0) {
               btnPayment.setBackground(WindowColor.lightGray);
               subtotalPanel.setLabelSubTitleToZero();
               return;
          }

          subtotalPanel.setLabelSubtotalUsd(dm.format(sumSubTotalUsd));
          double _subTotalKh = JavaRoundDown.roundDown("" + sumSubTotalUsd * JavaConstant.exchangeRate);
          subtotalPanel.setLabelSubtotalKhr(kh.format(_subTotalKh));

          subtotalPanel.setLableDiscountUsd(dm.format(sumDiscount));
          double _disKh = JavaRoundDown.roundDown("" + sumDiscount * JavaConstant.exchangeRate);
          subtotalPanel.setLableDiscountKhr(kh.format(_disKh));

          // total
          double total = sumSubTotalUsd - sumDiscount;
          subtotalPanel.setLableTotalUsd(dm.format(total));
          double _total = JavaRoundDown.roundDown("" + total * JavaConstant.exchangeRate);
          subtotalPanel.setLableTotalKhr(kh.format(_total));

          this.dispose();
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDelete = new javax.swing.JPanel();
        labelPopUpTitle2 = new Components.LabelPopUpTitle();
        lbReason1 = new Components.Label();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        buttonSave1 = new ButtonPackage.ButtonSave();
        jLabel1 = new javax.swing.JLabel();
        comboBoxReason = new Components.ComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelDelete.setPreferredSize(new java.awt.Dimension(379, 155));

        labelPopUpTitle2.setLabelTitle("Delete");

        lbReason1.setLabelName("Reason");

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSave1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        javax.swing.GroupLayout panelDeleteLayout = new javax.swing.GroupLayout(panelDelete);
        panelDelete.setLayout(panelDeleteLayout);
        panelDeleteLayout.setHorizontalGroup(
            panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeleteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDeleteLayout.createSequentialGroup()
                        .addComponent(lbReason1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDeleteLayout.createSequentialGroup()
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        panelDeleteLayout.setVerticalGroup(
            panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeleteLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReason1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked

         if (reasonId == null) {
              JOptionPane.showMessageDialog(this, "Please select a reason!");
              return;
         }

         try {
              JSONObject json = new JSONObject();
              json.put("createBy", JavaConstant.cashierId);
              json.put("reasonId", 1);
              ArrayList<ProductIDModel> listCancelDetail = new ArrayList<>();
              listCancelDetail.add(new ProductIDModel(productId));
              json.put("listCancelDetail", listCancelDetail);

              Response response = JavaConnection.post(JavaRoute.cancelAndDelete + "delete", json);

              if (response.isSuccessful()) {
                   dispose();
                   deleteItem();
              } else {
                   JOptionPane.showMessageDialog(this, "Save Failed!");
                   return;
              }
         } catch (Exception e) {
         }

    }//GEN-LAST:event_buttonSave1MouseClicked

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    DeleteDialog dialog = new DeleteDialog(new javax.swing.JFrame(), true);
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

     public Button.Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button.Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel1;
    private ButtonPackage.ButtonSave buttonSave1;
    private Components.ComboBox comboBoxReason;
    private javax.swing.JLabel jLabel1;
    private Components.LabelPopUpTitle labelPopUpTitle2;
    private Components.Label lbReason1;
    private javax.swing.JPanel panelDelete;
    // End of variables declaration//GEN-END:variables
}
