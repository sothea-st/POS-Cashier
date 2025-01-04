package feature.Customer;

import Components.Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Components.Event.ButtonEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

public class Customer extends javax.swing.JDialog {

    private HashMap<String, String> map = new HashMap<>();
    private String cusTypeId;
    private String gender = "-1";
    private String nationality = "-1";

    public Customer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        txtCustomerName.requestFocus();
        txtPhone.setValidatePhoneNumber();
        cmdgender();
        cmdNational();
    }

    void setBackground() {
        customer.setBackground(WindowColor.mediumGreen);
    }


    // Action Select Gender
    private void cmdgender() {
      
        try {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("Male", "Male");
            map.put("Female", "Female");
            cmdGender.setMap(map);

            ButtonEvent event = new ButtonEvent() {
                @Override
                public void onSelected(String id) {
                    gender = id;
                }
            };
            cmdGender.initEvent(event);

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }
    
    // Action Select National
    private void cmdNational() {
      
        try {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("Cambodian", "Cambodian");
            map.put("Chinese", "Chinese");
            map.put("White", "White");
            map.put("Black", "Black");
            cmdNationality.setMap(map);

            ButtonEvent event = new ButtonEvent() {
                @Override
                public void onSelected(String id) {
                    nationality = id;
                }
            };
            cmdNationality.initEvent(event);

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customer = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        buttonSave1 = new ButtonPackage.ButtonSave();
        txtCustomerName = new FormComponent.JavaTextField();
        txtPhone = new FormComponent.JavaTextField();
        cmdGender = new FormComponent.combobox.JavaCombobox();
        cmdNationality = new FormComponent.combobox.JavaCombobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Customer");

        buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSave1MouseClicked(evt);
            }
        });

        txtCustomerName.setLabelName("Customer Name *");
        txtCustomerName.setPlaceHolder("Customer Name");

        txtPhone.setLabelName("Phone Number *");
        txtPhone.setPlaceHolder("000 000 0000");

        cmdGender.setLabelName("Gender *");

        cmdNationality.setLabelName("National *");

        javax.swing.GroupLayout customerLayout = new javax.swing.GroupLayout(customer);
        customer.setLayout(customerLayout);
        customerLayout.setHorizontalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(customerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(cmdGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );
        customerLayout.setVerticalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked
   
        String txtCus = txtCustomerName.getValueTextField();
        String txtPh = txtPhone.getValueTextField();

        if(txtPh!= null){
            txtPh = txtPh.replace(" ", "");
        }
          
        try {

            boolean isCheck = JavaValidation.checkValidation(customer);

            if (isCheck) {

                JSONObject json = new JSONObject();
                json.put("cusName", txtCus);
                json.put("contact", txtPh);
                json.put("gender", gender);
                json.put("nationality", nationality);
                json.put("createBy", JavaConstant.cashierId);

                Response response = JavaConnection.post(JavaRoute.customer, json);

                try {
                    if (response.isSuccessful()) {
                        dispose();
                    }
                } catch (Exception e) {
                    System.err.println("data err" + e);
                }
            }

        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }
     }//GEN-LAST:event_buttonSave1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Customer dialog = new Customer(new javax.swing.JFrame(), true);
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
    private ButtonPackage.ButtonSave buttonSave1;
    private FormComponent.combobox.JavaCombobox cmdGender;
    private FormComponent.combobox.JavaCombobox cmdNationality;
    private javax.swing.JPanel customer;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private FormComponent.JavaTextField txtCustomerName;
    private FormComponent.JavaTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
