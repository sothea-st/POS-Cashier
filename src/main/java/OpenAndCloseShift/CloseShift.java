package OpenAndCloseShift;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BackgroundImage;
import Components.JavaAlertMessage;
import Components.SearchField;
import Components.TextField;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Print.EpsonPrinter;
 
import View.MainPage.MainPage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

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

     public CloseShift(java.awt.Frame parent, boolean modal, Button btnOpenShift) {
          super(parent, modal);
          initComponents();
          panelCloseShift.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          this.btnOpenShift = btnOpenShift;
//          redexpress.setFocus();

          redexpress.setComma("comma");
          qrMnk.setComma("comma"); // when user type 4length it will insert , at 3 length 
          qrAba.setComma("comma"); // when user type 4length it will insert , at 3 length 
          abaCreditCard.setComma("comma"); // when user type 4length it will insert , at 3 length 
          cashUs.setComma("comma"); // when user type 4length it will insert , at 3 length 
          cashKh.setComma("comma"); // when user type 4length it will insert , at 3 length 
//          cashCount.setComma("comma"); // when user type 4length it will insert , at 3 length 

     }

     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }

          };
          redexpress.initEvent(btnevent);
          qrMnk.initEvent(btnevent);
          qrAba.initEvent(btnevent);
          abaCreditCard.initEvent(btnevent);
          cashUs.initEvent(btnevent);
          cashKh.initEvent(btnevent);
//          cashCount.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCloseShift = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbPosId = new Components.Label();
        IbUserId = new Components.Label();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        label1 = new Components.Label();
        label2 = new Components.Label();
        label3 = new Components.Label();
        label4 = new Components.Label();
        label5 = new Components.Label();
        label6 = new Components.Label();
        redexpress = new Components.TextField();
        qrMnk = new Components.TextField();
        qrAba = new Components.TextField();
        abaCreditCard = new Components.TextField();
        cashUs = new Components.TextField();
        cashKh = new Components.TextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelCloseShift.setForeground(new java.awt.Color(0, 0, 0));

        labelPopUpTitle1.setLabelTitle("Close Shift");

        lbPosId.setLabelName("Payment Type");

        IbUserId.setLabelName("Cash Amount");

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

        label1.setLabelName("RED ANT EXPRESS");

        label2.setLabelName("KHQR-MNK");

        label3.setLabelName("KHQR-ABA");

        label4.setLabelName("ABA-CREDIT CARD");

        label5.setLabelName("CASH (USD)");

        label6.setLabelName("CASH (KHR)");

        redexpress.setLabelTextField("$ 0.00");

        qrMnk.setLabelTextField("$ 0.00");

        qrAba.setLabelTextField("$ 0.00");

        abaCreditCard.setLabelTextField("$ 0.00");

        cashUs.setLabelTextField("$ 0.00");

        cashKh.setLabelTextField("0.00");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("*");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("*");

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
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createSequentialGroup()
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbPosId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(abaCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qrAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cashUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cashKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qrMnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(redexpress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCloseShiftLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(IbUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(panelCloseShiftLayout.createSequentialGroup()
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 326, Short.MAX_VALUE))))
        );
        panelCloseShiftLayout.setVerticalGroup(
            panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IbUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lbPosId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(redexpress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(qrMnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(qrAba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abaCreditCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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
//         String countCash = cashCount.getValueTextField();

         try {

              if (express == null || express.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "RED ANT EXPRESS can not be empty!");
                   return;
              }
              if (khqrMnk == null || khqrMnk.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "KHQR-NMK can not be empty!");
                   return;
              }
              if (khqrAba == null || khqrAba.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "KHQR-ABA can not be empty!");
                   return;
              }
              if (creditCard == null || creditCard.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "ABA-CREDIT CARD can not be empty!");
                   return;
              }
              if (cashUsd == null || cashUsd.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "CASH (USD) can not be empty!");
                   return;
              }
              if (cashKhr == null || cashKhr.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "CASH (KHR) can not be empty!");
                   return;
              }

//              if (countCash == null || countCash.isEmpty()) {
//                   JOptionPane.showMessageDialog(this, "CASHIER COUNT can not be empty!");
//                   return;
//              }

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
//              countCash = countCash.replace(",", "");

              JSONObject json = new JSONObject();
              json.put("express", express);
              json.put("khqrMnk", khqrMnk);
              json.put("khqrAba", khqrAba);
              json.put("creditCard", creditCard);
              json.put("cashKhr", cashKhr);
              json.put("cashUsd", cashUsd);
//              json.put("cashCount", countCash);
              json.put("userCode", JavaConstant.userCode);
              json.put("userId", JavaConstant.cashierId);
              json.put("posId", JavaConstant.posId);

           

              boolean isExpress = JavaConstant.onlyDigits(express);
              if (!isExpress) {
                   JOptionPane.showMessageDialog(this, "Invalid value Express !");
                   return;
              }

              boolean isKhqrMnk = JavaConstant.onlyDigits(khqrMnk);
              if (!isKhqrMnk) {
                   JOptionPane.showMessageDialog(this, "Invalid value KHQR-MNK !");
                   return;
              }

              boolean isKhqrAba = JavaConstant.onlyDigits(khqrAba);
              if (!isKhqrAba) {
                   JOptionPane.showMessageDialog(this, "Invalid value KHQR-ABA !");
                   return;
              }

              boolean isAbaCreditCart = JavaConstant.onlyDigits(creditCard);
              if (!isAbaCreditCart) {
                   JOptionPane.showMessageDialog(this, "Invalid value ABA Credit Cart !");
                   return;
              }

              boolean isCashUsd = JavaConstant.onlyDigits(cashUsd);
              if (!isCashUsd) {
                   JOptionPane.showMessageDialog(this, "Invalid value Cash(USD) !");
                   return;
              }

              boolean isCashKhr = JavaConstant.onlyDigits(cashKhr);
              if (!isCashKhr) {
                   JOptionPane.showMessageDialog(this, "Invalid value Cash(KHR) !");
                   return;
              }

//              boolean isCashCount = JavaConstant.onlyDigits(countCash);
//              if (!isCashCount) {
//                   JOptionPane.showMessageDialog(this, "Invalid value Cash Count !");
//                   return;
//              }

              Response response = JavaConnection.post(JavaRoute.closeShift, json);
              System.out.println("respoeng : " + response);
              if (response.isSuccessful()) {

                   searchBox.disabledTextField(false);
                   searchBox.setPlaceholder("Search by name or barcode");

                   textField.disabledTextField(false);
                   textField.setLabelTextField("Scan or input barcode");

                   panelProduct.removeAll();
                   panelProduct.revalidate();
                   panelProduct.repaint();
                   getPanelPagination().setVisible(false);
                   category.getComponents()[0].setBackground(WindowColor.darkGreen);

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
//                   btnLogin.setBackground(WindowColor.green);

                   JavaConstant.checkCloseShift = 0l;
                   JavaConstant.checkOpenShift = false;
                   JavaConstant.isOpenShift = "Can not openshift";
                   
                   
                    category.removeAll();
                    category.revalidate();
                    category.repaint();

                   //      == == == == == == == Add Background == == == == == == ==
//                   BackgroundImage bgimg = new BackgroundImage();
//                   panelProduct.removeAll();
//                   panelProduct.add(bgImage);
//                   panelProduct.revalidate();
//                   panelProduct.repaint();
//                          == == == == == == == == == == == == == == == == == == == == == == =
//                   EpsonPrinter.printReceipt(new JPanel()); // for open cash drawer
              } else {
                   JOptionPane.showMessageDialog(this, "Save Failed!");

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
    private Components.Label IbUserId;
    private Components.TextField abaCreditCard;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private Components.TextField cashKh;
    private Components.TextField cashUs;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private Components.Label label1;
    private Components.Label label2;
    private Components.Label label3;
    private Components.Label label4;
    private Components.Label label5;
    private Components.Label label6;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbPosId;
    private javax.swing.JPanel panelCloseShift;
    private Components.TextField qrAba;
    private Components.TextField qrMnk;
    private Components.TextField redexpress;
    // End of variables declaration//GEN-END:variables
}
