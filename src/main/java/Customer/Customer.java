package Customer;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.CustomerType.CustomerTypeModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author FRONT-END.06
 */
public class Customer extends javax.swing.JDialog {

    private HashMap<String, String> map = new HashMap<>();
    private String cusTypeId;
    
    public Customer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        txtCustomerName.requestFocus();
        event();
        addComboCustomerType();
        nationalityGroupButton();
        genderGroupButton();
        
         // action get select customer type
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                cusTypeId = key;
            }
        };
        comboCustType.initEvent(event);
        
//        khmer.setSelected(true);
//        male.setSelected(true);
    }
    
    void setBackground(){
        customer.setBackground(WindowColor.mediumGreen);
    }
    
    //================ Gender Option ===================
    private void genderGroupButton() {

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
    }

    //================ Nationality Option ===================
    private void nationalityGroupButton() {
        ButtonGroup group = new ButtonGroup();
        group.add(khmer);
        group.add(asian);
        group.add(chinese);
        group.add(white);
        group.add(black);
    }
    
    void event() {
        ButtonEvent btnevent = new ButtonEvent() {
             @Override
             public void onFocusGain() {

             }
        };
        txtCustomerName.initEvent(btnevent);
        txtPhone.initEvent(btnevent);
        txtEarning.initEvent(btnevent);
        txtEmail.initEvent(btnevent);
    }
    
    private void addComboCustomerType() {
        try {
            ArrayList<CustomerTypeModel> typeCustomer = new ArrayList<>();
            Response response = JavaConnection.get(JavaRoute.customerType);
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                JSONObject jsonObject = new JSONObject(responseData);
                JSONArray data = jsonObject.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    CustomerTypeModel customer = new CustomerTypeModel(
                            obj.getInt("id"),
                            obj.getString("name")
                    );
                    typeCustomer.add(customer);
                    int idType = typeCustomer.get(i).getCustomerTypeId();
                    String type = typeCustomer.get(i).getCustomerTypeName();
                    map.put(type, "" + idType);
                }
                comboCustType.setMap(map);
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

        customer = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbCustomerType = new Components.Label();
        lbCustomerName = new Components.Label();
        lbPhone = new Components.Label();
        lbGender = new Components.Label();
        lbNationality = new Components.Label();
        comboCustType = new Components.ComboBox();
        txtCustomerName = new Components.TextField();
        txtPhone = new Components.TextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        khmer = new javax.swing.JRadioButton();
        asian = new javax.swing.JRadioButton();
        chinese = new javax.swing.JRadioButton();
        white = new javax.swing.JRadioButton();
        black = new javax.swing.JRadioButton();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        lbEarning = new Components.Label();
        txtEarning = new Components.TextField();
        lbCoupon = new Components.Label();
        comboCoupon = new Components.ComboBox();
        lbEmail = new Components.Label();
        txtEmail = new Components.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Customer");

        lbCustomerType.setLabelName("Customer Type");

        lbCustomerName.setLabelName("Customer Name");

        lbPhone.setLabelName("Phone Number");

        lbGender.setLabelName("Gender");

        lbNationality.setLabelName("Nationality");

        txtCustomerName.setLabelTextField("Customer Name");

        txtPhone.setLabelTextField("000 000 0000");

        male.setBackground(new java.awt.Color(176, 215, 181));
        male.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        male.setForeground(new java.awt.Color(56, 56, 56));
        male.setText("Male");

        female.setBackground(new java.awt.Color(176, 215, 181));
        female.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        female.setForeground(new java.awt.Color(56, 56, 56));
        female.setText("Female");

        khmer.setBackground(new java.awt.Color(176, 215, 181));
        khmer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        khmer.setForeground(new java.awt.Color(56, 56, 56));
        khmer.setText("Khmer");

        asian.setBackground(new java.awt.Color(176, 215, 181));
        asian.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        asian.setForeground(new java.awt.Color(56, 56, 56));
        asian.setText("Asian");

        chinese.setBackground(new java.awt.Color(176, 215, 181));
        chinese.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        chinese.setForeground(new java.awt.Color(56, 56, 56));
        chinese.setText("Chinese");

        white.setBackground(new java.awt.Color(176, 215, 181));
        white.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        white.setForeground(new java.awt.Color(56, 56, 56));
        white.setText("White");

        black.setBackground(new java.awt.Color(176, 215, 181));
        black.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        black.setForeground(new java.awt.Color(56, 56, 56));
        black.setText("Black");

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

        lbEarning.setLabelName("Earning ($)");

        txtEarning.setLabelTextField("$ 0.00");

        lbCoupon.setLabelName("Coupon ($)");

        lbEmail.setLabelName("Email");

        txtEmail.setLabelTextField("example@gmail.com");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        javax.swing.GroupLayout customerLayout = new javax.swing.GroupLayout(customer);
        customer.setLayout(customerLayout);
        customerLayout.setHorizontalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(customerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(lbEarning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtEarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(lbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboCustType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(comboCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(khmer)
                                    .addGroup(customerLayout.createSequentialGroup()
                                        .addComponent(chinese)
                                        .addGap(54, 54, 54)
                                        .addComponent(white))
                                    .addComponent(black)))
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(male)
                                .addGap(72, 72, 72)
                                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(asian)
                                    .addComponent(female)))
                            .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerLayout.createSequentialGroup()
                                    .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(customerLayout.createSequentialGroup()
                                    .addGap(153, 153, 153)
                                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(customerLayout.createSequentialGroup()
                                    .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        customerLayout.setVerticalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(comboCustType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboCoupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCoupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(female, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(khmer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(asian, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chinese, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(white, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addComponent(black, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String customerName = txtCustomerName.getValueTextField();
         String phoneNumber = txtPhone.getValueTextField();
         String earning = txtEarning.getValueTextField();
         String email = txtEmail.getValueTextField();
         
         JSONObject jsonData = new JSONObject();
         jsonData.put("cusName", customerName);
         jsonData.put("contact", phoneNumber);
         jsonData.put("earning", earning);
         jsonData.put("email", email);
         jsonData.put("customerTypeId", cusTypeId);
         jsonData.put("coupon", "");
         jsonData.put("createBy", JavaConstant.cashierId);
         
         //Action choosing option Gender
         if(male.isSelected()){
             jsonData.put("gender", male.getText());
         }else if(female.isSelected()){
             jsonData.put("gender", female.getText());
         }
         
         //Action choosing option Nationality
         if(khmer.isSelected()){
             jsonData.put("nationality", khmer.getText());
         }else if(asian.isSelected()){
             jsonData.put("nationality", asian.getText());
         }else if(chinese.isSelected()){
             jsonData.put("nationality", chinese.getText());
         }else if(white.isSelected()){
             jsonData.put("nationality", white.getText());
         }else if(black.isSelected()){
             jsonData.put("nationality", black.getText());
         }
         
         if (cusTypeId == null) {
            JOptionPane.showMessageDialog(this, "Please select Customer Type!");
            return;
         }
         
         if (customerName == null || customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer Name can not be empty!");
            return;
         }
         
         if (phoneNumber == null || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone Number can not be empty!");
            return;
         }
         
        Response response = JavaConnection.post(JavaRoute.customer, jsonData);
        
        if (response.isSuccessful()) {
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Save Failed!");
            return;
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

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
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
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
    private javax.swing.JRadioButton asian;
    private javax.swing.JRadioButton black;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JRadioButton chinese;
    private Components.ComboBox comboCoupon;
    private Components.ComboBox comboCustType;
    private javax.swing.JPanel customer;
    private javax.swing.JRadioButton female;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton khmer;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbCoupon;
    private Components.Label lbCustomerName;
    private Components.Label lbCustomerType;
    private Components.Label lbEarning;
    private Components.Label lbEmail;
    private Components.Label lbGender;
    private Components.Label lbNationality;
    private Components.Label lbPhone;
    private javax.swing.JRadioButton male;
    private Components.TextField txtCustomerName;
    private Components.TextField txtEarning;
    private Components.TextField txtEmail;
    private Components.TextField txtPhone;
    private javax.swing.JRadioButton white;
    // End of variables declaration//GEN-END:variables
}
