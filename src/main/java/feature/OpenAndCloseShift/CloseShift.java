package feature.OpenAndCloseShift;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Components.Color.WindowColor;
import Components.BackgroundImage;
import Components.JavaAlertMessage;
import Components.SearchField;
import Components.TextField;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import feature.Print.EpsonPrinter;

import View.MainPage.MainPage;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class CloseShift extends javax.swing.JDialog {

     private Button btnOpenShift;
     private JPanel panelProduct;
     private JPanel panelPagination;
     private JPanel category;
     private SearchField searchBox;
     private TextField textField;
     private Button btnreturn;
     private Button btnReprint;
     private Button buttonDiscount;
     private Button buttonCustomer;
     private Button buttonCashier;
     private Button btnHold;
     private Button btnLogin;
     private BackgroundImage bgImage;
     private ButtonCancel btnCancel;
     private Button stock;
     private Button buttonStaff;
     private MainPage mainPage;

     public CloseShift(java.awt.Frame parent, boolean modal, Button btnOpenShift) {
          super(parent, modal);
          initComponents();
          panelCloseShift.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          this.btnOpenShift = btnOpenShift;

          redexpress.setValidateAmount();
          qrMnk.setValidateAmount();
          qrAba.setValidateAmount();
          abaCreditCard.setValidateAmount();
          cashUs.setValidateAmount();
          cashKh.setValidateAmount();
          redexpress.requestFocus();
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCloseShift = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        redexpress = new FormComponent.JavaTextField();
        qrMnk = new FormComponent.JavaTextField();
        qrAba = new FormComponent.JavaTextField();
        abaCreditCard = new FormComponent.JavaTextField();
        cashUs = new FormComponent.JavaTextField();
        cashKh = new FormComponent.JavaTextField();
        lbPosId1 = new Components.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Close Shift");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        redexpress.setLabelName("RED ANT EXPRESS *");
        redexpress.setPlaceHolder("$ 0.00");

        qrMnk.setLabelName("KHQR-MNK *");
        qrMnk.setPlaceHolder("$ 0.00");

        qrAba.setLabelName("KHQR-ABA *");
        qrAba.setName(""); // NOI18N
        qrAba.setPlaceHolder("$ 0.00");

        abaCreditCard.setLabelName("ABA-CREDIT CARD *");
        abaCreditCard.setPlaceHolder("$ 0.00");

        cashUs.setLabelName("CASH (USD) *");
        cashUs.setPlaceHolder("$ 0.00");

        cashKh.setLabelName("CASH (KHR) *");
        cashKh.setPlaceHolder("0");

        lbPosId1.setLabelName("Payment Type & Cash Amount");

        javax.swing.GroupLayout panelCloseShiftLayout = new javax.swing.GroupLayout(panelCloseShift);
        panelCloseShift.setLayout(panelCloseShiftLayout);
        panelCloseShiftLayout.setHorizontalGroup(
            panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCloseShiftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cashKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cashUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abaCreditCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qrAba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qrMnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(redexpress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPosId1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panelCloseShiftLayout.setVerticalGroup(
            panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbPosId1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redexpress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qrMnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qrAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abaCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCloseShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCloseShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         String express = redexpress.getValueTextField();
         String khqrMnk = qrMnk.getValueTextField();
         String khqrAba = qrAba.getValueTextField();
         String creditCard = abaCreditCard.getValueTextField();
         String cashKhr = cashKh.getValueTextField();
         String cashUsd = cashUs.getValueTextField();

         try {

              boolean isCheck = JavaValidation.checkValidation(panelCloseShift);

              if (isCheck) {

                   int count = new MainPage().countHold();
                   if (count > 0) {
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage("There are some transactions not complete yet in Hold function!");
                        j.setVisible(true);
                        return;
                   }

                   express = express.replace(",", "");
                   khqrMnk = khqrMnk.replace(",", "");
                   khqrAba = khqrAba.replace(",", "");
                   creditCard = creditCard.replace(",", "");
                   cashKhr = cashKhr.replace(",", "");
                   cashUsd = cashUsd.replace(",", "");

                   JSONObject json = new JSONObject();
                   json.put("express", express);
                   json.put("khqrMnk", khqrMnk);
                   json.put("khqrAba", khqrAba);
                   json.put("creditCard", creditCard);
                   json.put("cashKhr", cashKhr);
                   json.put("cashUsd", cashUsd);
                   json.put("userCode", JavaConstant.userCode);
                   json.put("userId", JavaConstant.cashierId);
                   json.put("posId", JavaConstant.posId);

                   Response response = JavaConnection.post(JavaRoute.closeShift, json);

                   System.err.println("json : " + json);
                   System.err.println("response : " + response);

                   if (response.isSuccessful()) {

                        searchBox.disabledTextField(false);
                        searchBox.setPlaceholder("Search by name or barcode");

                        textField.disabledTextField(false);
                        textField.setLabelTextField("Scan or input barcode");

                        panelProduct.removeAll();
                        panelProduct.revalidate();
                        panelProduct.repaint();
                        getPanelPagination().setVisible(false);
                        if (category.getComponentCount() > 0) {
                             category.getComponents()[0].setBackground(WindowColor.darkGreen);
                        }

                        dispose();

                        btnreturn.setBackground(WindowColor.lightGray);
                        buttonCustomer.setBackground(WindowColor.lightGray);
                        buttonDiscount.setBackground(WindowColor.lightGray);
                        btnReprint.setBackground(WindowColor.lightGray);
                        btnHold.setBackground(WindowColor.lightGray);
                        buttonCashier.setBackground(WindowColor.green);
                        stock.setBackground(WindowColor.lightGray);
                        buttonStaff.setBackground(WindowColor.lightGray);
                        dispose();
                        btnOpenShift.setButtonName("Open Shift");
                        btnOpenShift.setBackground(WindowColor.lightGray);
                        // btnLogin.setBackground(WindowColor.green);

                        JavaConstant.checkCloseShift = 0l;
                        JavaConstant.checkOpenShift = false;
                        JavaConstant.isOpenShift = "Can not openshift";

                        category.removeAll();
                        category.revalidate();
                        category.repaint();

                        if (JavaConstant.roleName.toLowerCase().equals(JavaConstant.admin.toLowerCase())) {
                             mainPage.setBackgroundButton();
                        }

                        //==============Add Background===============
                        BackgroundImage bgimg = new BackgroundImage();
                        // Create a JLabel
                        JLabel bg = new JLabel();
                        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("company/logoTT.png"));
                        bg.setIcon(icon);
                        panelProduct.setLayout(new GridBagLayout());
                        panelProduct.removeAll();
                        panelProduct.add(bg);
                        panelProduct.revalidate();
                        panelProduct.repaint();
                        // Set the label to be centered within the panel
                        GridBagConstraints constraints = new GridBagConstraints();
                        constraints.gridx = 0;
                        constraints.gridy = 0;
                        constraints.weightx = 1.0;
                        constraints.weighty = 1.0;
                        constraints.anchor = GridBagConstraints.CENTER;
                        panelProduct.add(bgimg, constraints);
                        //===========================================

                        EpsonPrinter.printReceipt(new JPanel()); // for open cash drawer
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");

                   }
              }

         } catch (Exception e) {
              System.err.println("errr -- " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     public BackgroundImage getBgImage() {
          return bgImage;
     }

     public void setBgImage(BackgroundImage bgImage) {
          this.bgImage = bgImage;
     }

     public Button getBtnHold() {
          return btnHold;
     }

     public void setBtnHold(Button btnHold) {
          this.btnHold = btnHold;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     public JPanel getCategory() {
          return category;
     }

     public void setCategory(JPanel category) {
          this.category = category;
     }

     public SearchField getSearchBox() {
          return searchBox;
     }

     public void setSearchBox(SearchField searchBox) {
          this.searchBox = searchBox;
     }

     public TextField getTextField() {
          return textField;
     }

     public void setTextField(TextField textField) {
          this.textField = textField;
     }

     public Button getBtnreturn() {
          return btnreturn;
     }

     public void setBtnreturn(Button btnreturn) {
          this.btnreturn = btnreturn;
     }

     public Button getBtnReprint() {
          return btnReprint;
     }

     public void setBtnReprint(Button btnReprint) {
          this.btnReprint = btnReprint;
     }

     public Button getButtonDiscount() {
          return buttonDiscount;
     }

     public void setButtonDiscount(Button buttonDiscount) {
          this.buttonDiscount = buttonDiscount;
     }

     public Button getButtonCustomer() {
          return buttonCustomer;
     }

     public void setButtonCustomer(Button buttonCustomer) {
          this.buttonCustomer = buttonCustomer;
     }

     public Button getButtonCashier() {
          return buttonCashier;
     }

     public void setButtonCashier(Button buttonCashier) {
          this.buttonCashier = buttonCashier;
     }

     public Button getBtnLogin() {
          return btnLogin;
     }

     public void setBtnLogin(Button btnLogin) {
          this.btnLogin = btnLogin;
     }

     public Button getStock() {
          return stock;
     }

     public void setStock(Button stock) {
          this.stock = stock;
     }

     public Button getButtonStaff() {
          return buttonStaff;
     }

     public void setButtonStaff(Button buttonStaff) {
          this.buttonStaff = buttonStaff;
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
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    CloseShift dialog = new CloseShift(new javax.swing.JFrame(), true, null);
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
    private FormComponent.JavaTextField abaCreditCard;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private FormComponent.JavaTextField cashKh;
    private FormComponent.JavaTextField cashUs;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbPosId1;
    private javax.swing.JPanel panelCloseShift;
    private FormComponent.JavaTextField qrAba;
    private FormComponent.JavaTextField qrMnk;
    private FormComponent.JavaTextField redexpress;
    // End of variables declaration//GEN-END:variables
}
