package feature.Return;


import Components.Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionScanBarcodeAddProduct.ActionScanBarcodeAddProduct;
import Components.Event.ButtonEvent;
import feature.LoginAndLogoutForm.LoginFormJdailog;
import okhttp3.Response;
import org.json.JSONObject;
import Button.Button;
import ButtonPackage.ButtonCancel;
import FormComponent.combobox.JavaComboBoxSelection;
import javax.swing.JPanel;
import main_validation.JavaValidation;


public class ReturnDialog extends javax.swing.JDialog {

    private String reasonId = "-1";
    private LoginFormJdailog jdFormLogin;
    private Button btnPayment;
    private ButtonCancel btnCancel;
    private Button btnHold;
    private Button btnReturn;
    private Button btnDiscount;

    private JPanel detailItem;
    private JPanel boxOne;

    private JPanel panelProduct;

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

    public JPanel getPanelProduct() {
        return panelProduct;
    }

    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    public ReturnDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelReturn.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        event();
        addComboReason();
        txtinvoice.setFocus();
        eventSelectReason();
        
        txtinvoice.requestFocus();

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onKeyRelease() {
                String value = txtinvoice.getValueTextField();
                String barcodeValue = value.substring(2); // this working with device scanner 
                barcodeValue = barcodeValue.substring(0, barcodeValue.length() - 1);
                System.out.println("barcodeValue : " + barcodeValue);
                Response responseData = JavaConnection.get(JavaRoute.getInvoice + barcodeValue);
                try {
                    String _data = responseData.body().string();
                    JSONObject obj = new JSONObject(_data);
                    String invoice = obj.getString("data");
                    txtinvoice.setValueTextField(invoice);
                } catch (Exception e) {
                    System.err.println("error : " + e);
                }

//                    if (value.length() >= 9) {
//                         String barcodeValue = value.substring(2); // this working with device scanner 
//                         System.out.println("barcodeValue : " + barcodeValue);
//
//                         Response responseData = JavaConnection.get(JavaRoute.getInvoice + barcodeValue);
//                         System.out.println("responseData onKeyRelease : " + responseData);
//                         try {
//                              if (responseData.isSuccessful()) {
//                                   String _data = responseData.body().string();
//                                   JSONObject obj = new JSONObject(_data);
//                                   String invoice = obj.getString("data");
//                                   System.out.println("invoice == onKeyRelease " + invoice);
//                                   txtinvoice.setValueTextField(invoice);
//                                   String _value = txtinvoice.getValueTextField();
//
//                                   _value = _value.substring(0, 17);
//                                   System.out.println("The truncated string is: " + _value);
//                              } else {
////                                   System.out.println("ffffffffffffffffffffff = " + barcodeValue);
////                                   barcodeValue = barcodeValue.substring(0, 17);
////                                   System.out.println("The truncated string is: " + barcodeValue);
////                                   txtinvoice.setValueTextField(barcodeValue);
//                              }
//                         } catch (Exception e) {
//                              System.err.println("response data 333= " + e);
//                         }
//                    }
            }
        };

        txtinvoice.initEvent(event);

    }

    void eventSelectReason() {
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                reasonId = key;
            }
        };
        comboBoxReason.initEvent(event);
    }

    //Action call function placeholder
    void event() {
        ButtonEvent btnevent = new ButtonEvent() {
            @Override
            public void onFocusGain() {

            }
        };
        txtinvoice.initEvent(btnevent);
        txtBarcode.initEvent(btnevent);
    }

    private void addComboReason() {
        
        JavaComboBoxSelection.addComboBox(comboBoxReason,
                JavaRoute.reason + "return",
                "reason",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                reasonId = id;
            }
        };
        comboBoxReason.initEvent(event);
        
    }

    public Button getBtnPayment() {
        return btnPayment;
    }

    public void setBtnPayment(Button btnPayment) {
        this.btnPayment = btnPayment;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReturn = new javax.swing.JPanel();
        labelPopUpTitle = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        txtinvoice = new FormComponent.JavaTextField();
        txtBarcode = new FormComponent.JavaTextField();
        comboBoxReason = new FormComponent.combobox.JavaCombobox();
        buttonSave = new ButtonPackage.ButtonSave();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle.setLabelTitle("Return");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        txtinvoice.setLabelName("Invoice № *");
        txtinvoice.setPlaceHolder("Scan or input");

        txtBarcode.setLabelName("Barcode");
        txtBarcode.setPlaceHolder("Scan or input");

        comboBoxReason.setLabelName("Reason *");

        buttonSave.setName(""); // NOI18N
        buttonSave.setTitleButton("Search");
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelReturnLayout = new javax.swing.GroupLayout(panelReturn);
        panelReturn.setLayout(panelReturnLayout);
        panelReturnLayout.setHorizontalGroup(
            panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelReturnLayout.createSequentialGroup()
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelReturnLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelReturnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panelReturnLayout.setVerticalGroup(
            panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReturnLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 40, Short.MAX_VALUE))
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

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked


    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

        String barcode = txtBarcode.getValueTextField();
        String invoiceNo = txtinvoice.getValueTextField();

        try {

            boolean isCheck = JavaValidation.checkValidation(panelReturn);

            if (isCheck) {
                ActionScanBarcodeAddProduct obj = new ActionScanBarcodeAddProduct();
                obj.setBtnPayment(btnPayment);
                obj.setBtnReturn(btnReturn);
                obj.setPanelProduct(panelProduct);
                obj.setDetailItem(detailItem);
                obj.setBoxOne(boxOne);

                if (barcode != null) { // return with barcode
                     obj.returnWithBarcode(barcode, jdFormLogin, invoiceNo);
                } else { // return withour barcode
                     obj.scanWithoutReturn(invoiceNo, jdFormLogin);
                }

                btnCancel.setBackground(WindowColor.lightGray);
                btnHold.setBackground(WindowColor.lightGray);

       //         btnDiscount.setBackground(WindowColor.lightGray);
                JavaConstant.reasonId = reasonId;
                JavaConstant.invoiceNo = invoiceNo;

                if (JavaConstant.tmpInvoice != null) {
                     dispose();
                }  
            }
        } catch (Exception e) {
            System.err.println("err  = " + e);
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    public void setResetReturn() {
        JavaConstant.isReturn = null;
        JavaConstant.reasonId = null;
        JavaConstant.invoiceNo = null;
        JavaConstant.returnerId = null;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReturnDialog dialog = new ReturnDialog(new javax.swing.JFrame(), true);
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

    public Button getBtnDiscount() {
        return btnDiscount;
    }

    public void setBtnDiscount(Button btnDiscount) {
        this.btnDiscount = btnDiscount;
    }

    public Button getBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(Button btnReturn) {
        this.btnReturn = btnReturn;
    }

    public LoginFormJdailog getJdFormLogin() {
        return jdFormLogin;
    }

    public void setJdFormLogin(LoginFormJdailog jdFormLogin) {
        this.jdFormLogin = jdFormLogin;
    }

    public ButtonCancel getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ButtonCancel btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getBtnHold() {
        return btnHold;
    }

    public void setBtnHold(Button btnHold) {
        this.btnHold = btnHold;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private FormComponent.combobox.JavaCombobox comboBoxReason;
    private Components.LabelPopUpTitle labelPopUpTitle;
    private javax.swing.JPanel panelReturn;
    private FormComponent.JavaTextField txtBarcode;
    private FormComponent.JavaTextField txtinvoice;
    // End of variables declaration//GEN-END:variables
}
