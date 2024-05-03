package Print;

 
import Color.WindowColor;
import Components.DialonInputName;
import Components.HistoryHoldOrder;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Model.Reprint.DataSuccessModel;
import Receipt.Receipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import Button.Button;
public class ReprintJdailog extends javax.swing.JDialog {

     private String title;
     private String textButtonLeft;
     private String textButtonRight;
     private String typeForm;
     private JPanel detailItem;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;

     public ReprintJdailog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelReprint.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelReprint = new javax.swing.JPanel();
          btnPrintByLast = new  Button();
          btnPrintByInvoice = new  Button();
          lbTitle = new Components.LabelPopUpTitle();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          panelReprint.setForeground(new java.awt.Color(0, 0, 0));
          panelReprint.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    panelReprintMouseClicked(evt);
               }
          });

          btnPrintByLast.setButtonName("Reprint by Last");
          btnPrintByLast.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnPrintByLastMouseClicked(evt);
               }
          });

          btnPrintByInvoice.setBackground(new java.awt.Color(47, 155, 70));
          btnPrintByInvoice.setButtonName("Reprint by Invoice №");
          btnPrintByInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnPrintByInvoiceMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnPrintByInvoiceMouseEntered(evt);
               }
          });

          lbTitle.setLabelTitle("Reprint Invoice");

          javax.swing.GroupLayout panelReprintLayout = new javax.swing.GroupLayout(panelReprint);
          panelReprint.setLayout(panelReprintLayout);
          panelReprintLayout.setHorizontalGroup(
               panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReprintLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnPrintByLast, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPrintByInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE))
               .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          panelReprintLayout.setVerticalGroup(
               panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelReprintLayout.createSequentialGroup()
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelReprintLayout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addComponent(btnPrintByLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(btnPrintByInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(30, 30, 30))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void btnPrintByLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintByLastMouseClicked

         if (JavaConstant.checkCloseShift == null) {
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Invoice is empty!");
              j.setVisible(true);
              return;
         }

         if (JavaConstant.checkCloseShift == 0) {
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Invoice is empty!");
              j.setVisible(true);
              return;
         }

         if (typeForm.equals("reprint")) {
              // for reprint function
              reprint(true);
         } else if (typeForm.equals("hold")) {
              // for hold function
              HistoryHoldOrder h = new HistoryHoldOrder(new JFrame(), true);
              h.setDetailItem(detailItem);
              h.setSubtotalPanel(subtotalPanel);
              h.setBtnPayment(btnPayment);
              h.setVisible(true);
              dispose();
         }
    }//GEN-LAST:event_btnPrintByLastMouseClicked

     public void reprint(boolean isVisible) {
          Receipt rec = new Receipt(new JFrame(), true);
          Response response = JavaConnection.get(JavaRoute.reprintByLast);
        
          this.dispose();
          if (response.isSuccessful()) {
               try {
                    String myObject = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    DataSuccessModel d = objMap.readValue(myObject, DataSuccessModel.class);
                    rec.setDataSuccess(d);
                    rec.setVisible(isVisible);
                     
               } catch (Exception e) {
                    System.err.println("err while loding = " + e);
               }
          } else {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("Invoice is empty!");
               j.setVisible(true);
               return;
          }
     }


    private void btnPrintByInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintByInvoiceMouseClicked

         if (typeForm.equals("reprint")) {
              this.dispose();
              ReprintByInvoicenumber rep = new ReprintByInvoicenumber(new JFrame(), true);
              rep.setVisible(true);
         } else if (typeForm.equals("hold")) {
              Component[] listCom1 = detailItem.getComponents();
              if (listCom1.length == 0) {
                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                   j.setMessage("Cannot add hold order!");
                   j.setVisible(true);
                   return;
              }

              this.dispose();
              DialonInputName d = new DialonInputName(new JFrame(), true);
              d.setDetailItem(detailItem);
              d.setSubtotalPanel(subtotalPanel);
              d.setBtnPayment(btnPayment);
              d.setVisible(true);
         }

    }//GEN-LAST:event_btnPrintByInvoiceMouseClicked

     private void panelReprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelReprintMouseClicked
          // TODO add your handling code here:
     }//GEN-LAST:event_panelReprintMouseClicked

     private void btnPrintByInvoiceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintByInvoiceMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_btnPrintByInvoiceMouseEntered

     public static void main(String args[]) {

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReprintJdailog dialog = new ReprintJdailog(new javax.swing.JFrame(), true);
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

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public String getTypeForm() {
          return typeForm;
     }

     public void setTypeForm(String typeForm) {
          this.typeForm = typeForm;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
          lbTitle.setLabelTitle(title);
     }

     public String getTextButtonLeft() {
          return textButtonLeft;
     }

     public void setTextButtonLeft(String textButtonLeft) {
          this.textButtonLeft = textButtonLeft;
          btnPrintByLast.setButtonName(textButtonLeft);
     }

     public String getTextButtonRight() {
          return textButtonRight;
     }

     public void setTextButtonRight(String textButtonRight) {
          this.textButtonRight = textButtonRight;
          btnPrintByInvoice.setButtonName(textButtonRight);
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Button btnPrintByInvoice;
     private Button btnPrintByLast;
     private Components.LabelPopUpTitle lbTitle;
     private javax.swing.JPanel panelReprint;
     // End of variables declaration//GEN-END:variables
}
