package Return;

import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Model.Reprint.DataSuccessModel;
import Model.ReturnModel.ReturnProductModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import okhttp3.Response;
import org.json.JSONObject;

public class JdialogConfirmReturn extends javax.swing.JDialog {

     private JPanel boxOne;
     private JPanel detailItem;
     private Button.Button btnPayment;
     private Button.Button buttonHoldOrder;
     private ButtonCancel btnCancel;
     private SubtotalPanel subtotalPanel;

     public JdialogConfirmReturn(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          initComponents();
     }
     
     
//     void data(){
//          Component[] _list = detailItem.getComponents();
//          for( int i = 0 ; i < _list.length ; i++ ) {
//               var obj = (BoxItem)_list[i];
//               System.err.println("ddddddddddddddd = " + obj.get);
//          }
//     }
     

     public JPanel getBoxOne() {
          return boxOne;
     }

     public void setBoxOne(JPanel boxOne) {
          this.boxOne = boxOne;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public Button.Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button.Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public Button.Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button.Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelReturn = new javax.swing.JPanel();
          labelPopUpTitle = new Components.LabelPopUpTitle();
          button1 = new Button.Button();
          buttonCancel = new ButtonPackage.ButtonCancel();
          jLabel1 = new javax.swing.JLabel();
          lbSubTotal = new Components.Label();
          txtSubTotalKhr = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          lbUsd = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle.setLabelTitle("Return");

          button1.setBackground(new java.awt.Color(47, 152, 70));
          button1.setButtonName("Return");
          button1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    button1MouseClicked(evt);
               }
          });

          buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonCancelMouseClicked(evt);
               }
          });

          jLabel1.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
          jLabel1.setText("៛");

          lbSubTotal.setLabelName("Return Amount :");

          txtSubTotalKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          txtSubTotalKhr.setText("0");

          jLabel4.setText(":");

          lbUsd.setText("jLabel2");

          javax.swing.GroupLayout panelReturnLayout = new javax.swing.GroupLayout(panelReturn);
          panelReturn.setLayout(panelReturnLayout);
          panelReturnLayout.setHorizontalGroup(
               panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
               .addGroup(panelReturnLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(lbSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(jLabel4)
                    .addGap(38, 38, 38)
                    .addComponent(txtSubTotalKhr)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReturnLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
          );
          panelReturnLayout.setVerticalGroup(
               panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelReturnLayout.createSequentialGroup()
                    .addComponent(labelPopUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)
                    .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(lbSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(txtSubTotalKhr)
                              .addComponent(jLabel4)
                              .addComponent(lbUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(16, 16, 16))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked

          try {
               Component[] listCom = detailItem.getComponents();
               returnProduct(listCom);
          } catch (IOException ex) {
               Logger.getLogger(JdialogConfirmReturn.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_button1MouseClicked

     private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_buttonCancelMouseClicked

     public void returnProduct(Component[] listCom) throws IOException {
          double totalReturn = 0;

          JSONObject jsonReturnData = new JSONObject();
          jsonReturnData.put("paymentNo", JavaConstant.invoiceNo);
          jsonReturnData.put("reasonId", Integer.valueOf(JavaConstant.reasonId));
          jsonReturnData.put("createBy", JavaConstant.returnerId);
          jsonReturnData.put("returnAmount", totalReturn);

          //get dataSale 
          ArrayList<ReturnProductModel> dataDetails = new ArrayList<>();
          for (int i = 0; i < listCom.length; i++) {
               var obj = ((BoxItem) listCom[i]);
               double price = JavaConstant.getReplace(obj.getLabelPrice());
               double amount = JavaConstant.getReplace(obj.getLabelAmountUsd());
               double discountDigit = obj.getDiscountDigit();

//               ReturnProductModel pro = new ReturnProductModel(
//                    obj.getProductId(),
//                    obj.getQty(),
//                    price,
//                    amount,
//                    discountDigit,
//                    obj.getLabelProductName(),
//                    obj.getLabelBarcode()
//               );
//               dataDetails.add(pro);
          }
          jsonReturnData.put("dataDetails", dataDetails);

          Response responseReturn = JavaConnection.post(JavaRoute.returnProduct, jsonReturnData);

          if (responseReturn.isSuccessful()) {
               String _data = responseReturn.body().string();
               dispose();
               detailItem.removeAll();
               detailItem.revalidate();
               detailItem.repaint();
               subtotalPanel.setLabelSubTitleToZero();
               btnPayment.setBackground(WindowColor.lightGray);
               btnCancel.setBackground(WindowColor.lightGray);
               buttonHoldOrder.setBackground(WindowColor.lightGray);

               PrinterReturn print = new PrinterReturn(new JFrame(), true);
               ObjectMapper objMap = new ObjectMapper();
               DataSuccessModel d = objMap.readValue(_data, DataSuccessModel.class);
               print.setDataSuccess(d);
               print.revalidate();
               print.repaint();
//               print.printReceipt();
               print.setVisible(true);

               // assign JavaConstant.isReturn , reasonId , inovoiceNo to null
               ReturnDialog r = new ReturnDialog(new JFrame(), true);
               r.setResetReturn();
//               JavaConstant.isReturn = null;

          } else {
               System.err.println("err = 4444");
          }

     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    JdialogConfirmReturn dialog = new JdialogConfirmReturn(new javax.swing.JFrame(), true);
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
     private Button.Button button1;
     private ButtonPackage.ButtonCancel buttonCancel;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel4;
     private Components.LabelPopUpTitle labelPopUpTitle;
     private Components.Label lbSubTotal;
     private javax.swing.JLabel lbUsd;
     private javax.swing.JPanel panelReturn;
     private javax.swing.JLabel txtSubTotalKhr;
     // End of variables declaration//GEN-END:variables
}
