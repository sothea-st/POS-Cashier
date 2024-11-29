package Print;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.Reprint.DataSuccessModel;
import Receipt.Receipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author FRONT-END.06
 */
public class ReprintByInvoicenumber extends javax.swing.JDialog {

    public ReprintByInvoicenumber(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelReprint.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        txtInvoiceNumber.requestFocus();

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onKeyRelease() {
                String value = txtInvoiceNumber.getValueTextField();
                String barcodeValue = value.substring(2); // this working with device scanner 
                barcodeValue = barcodeValue.substring(0, barcodeValue.length() - 1);
                System.out.println("barcodeValue : " + barcodeValue);
                Response responseData = JavaConnection.get(JavaRoute.getInvoice + barcodeValue);
                try {
                    String _data = responseData.body().string();
                    JSONObject obj = new JSONObject(_data);
                    String invoice = obj.getString("data");
                    txtInvoiceNumber.setValueTextField(invoice);
                } catch (Exception e) {
                    System.err.println("error : " + e);
                }
                    
//                    String value = txtInvoiceNumber.getValueTextField();
//                    String barcodeValue = value.substring(2); // this working with device scanner 
//                    Response responseData = JavaConnection.get(JavaRoute.getInvoice + barcodeValue);
//
//                    try {
//                         String _data = responseData.body().string();
//                         JSONObject obj = new JSONObject(_data);
//                         String invoice = obj.getString("data");
//                         txtInvoiceNumber.setValueTextField(invoice);
//                    } catch (Exception e) {
//                         System.err.println("response data 333= " + e);
//                    }
             }
        };

        txtInvoiceNumber.initEvent(event);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReprint = new javax.swing.JPanel();
        btnBack = new Button.Button();
        btnPreview = new Button.Button();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        txtInvoiceNumber = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelReprint.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnBack.setButtonName("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnPreview.setBackground(new java.awt.Color(47, 155, 70));
        btnPreview.setButtonName("Review");
        btnPreview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviewMouseClicked(evt);
            }
        });

        labelPopUpTitle1.setLabelTitle("Reprint by Invoice №");

        txtInvoiceNumber.setLabelName("Invoice № *");
        txtInvoiceNumber.setPlaceHolder("Scan or input");

        javax.swing.GroupLayout panelReprintLayout = new javax.swing.GroupLayout(panelReprint);
        panelReprint.setLayout(panelReprintLayout);
        panelReprintLayout.setHorizontalGroup(
            panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelReprintLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtInvoiceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelReprintLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelReprintLayout.setVerticalGroup(
            panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReprintLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(txtInvoiceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReprintLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnPreviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewMouseClicked

        String paymentNo = txtInvoiceNumber.getValueTextField();
        
        boolean isCheck = JavaValidation.checkValidation(panelReprint);
            
            if (isCheck) {
                try {
                    printReceiptWithInvoic(paymentNo);
                } catch (Exception e) {
                    System.err.println("error = " + e);
                }
            }

    }//GEN-LAST:event_btnPreviewMouseClicked

    public void printReceiptWithInvoic(String paymentNo) throws IOException {

        Receipt rec = new Receipt(new JFrame(), true);
        Response response = JavaConnection.get(JavaRoute.reprintByLast + "/" + paymentNo);

        if (response.isSuccessful()) {
            this.dispose();
            String myObject = response.body().string();
            ObjectMapper objMap = new ObjectMapper();
            DataSuccessModel d = objMap.readValue(myObject, DataSuccessModel.class);
            rec.setDataSuccess(d);
            rec.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Wrong Invoice №!");
            return;
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReprintByInvoicenumber dialog = new ReprintByInvoicenumber(new javax.swing.JFrame(), true);
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
    private Button.Button btnBack;
    private Button.Button btnPreview;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private javax.swing.JPanel panelReprint;
    private FormComponent.JavaTextField txtInvoiceNumber;
    // End of variables declaration//GEN-END:variables
}
