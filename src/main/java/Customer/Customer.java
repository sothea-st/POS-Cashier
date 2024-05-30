package Customer;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.CustomerType.CustomerTypeModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class Customer extends javax.swing.JDialog {

     private HashMap<String, String> map = new HashMap<>();
     private String cusTypeId;
     private String gender;
     private String nationality;

     public Customer(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setBackground();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          txtCustomerName.requestFocus();
          event();
//          addComboCustomerType();
          nationalityGroupButton();
          genderGroupButton();
          buttonSave1.setBackground(WindowColor.lightGray);

          // action get select customer type
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    cusTypeId = key;
               }
          };
          customerFun();
          cmdEvent();
          cmdNationalityEvent();
     }

     void changeBackGroundBtn() {

          String ph = txtPhone.getValueTextField();
          String cus = txtCustomerName.getValueTextField();

          if (cus != null
               && ph != null
               && gender != null
               && nationality != null) {
               buttonSave1.setBackground(WindowColor.primary);
          }
          
          if (cus.isEmpty()
               || ph.isEmpty()
               || gender == null
               || nationality == null) {
               buttonSave1.setBackground(WindowColor.lightGray);
          }
     }

     void customerFun() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    changeBackGroundBtn();
               }
          };

          txtCustomerName.initEvent(event);
          txtPhone.initEvent(event);
     }

     void setBackground() {
          customer.setBackground(WindowColor.mediumGreen);
     }

     //================ Gender Option ===================
     private void genderGroupButton() {

          ButtonGroup group = new ButtonGroup();
//        group.add(male);
//        group.add(female);
     }

     void cmdEvent() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    gender = key;
                    changeBackGroundBtn();
               }
          };

          cmdGender.initEvent(event);

     }

     void cmdNationalityEvent() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    nationality = key;
                    changeBackGroundBtn();
               }
          };

          cmdNationality.initEvent(event);
     }

     //================ Nationality Option ===================
     private void nationalityGroupButton() {
          HashMap<String, String> mapGender = new HashMap<>();
          mapGender.put("Male", "Male");
          mapGender.put("Female", "Female");
          cmdGender.setMap(mapGender);

          HashMap<String, String> mapNationality = new HashMap<>();
          mapNationality.put("Cambodian", "Cambodian");
          mapNationality.put("Chinese", "Chinese");
          mapNationality.put("White", "White");
          mapNationality.put("Black", "Black");
          cmdNationality.setMap(mapNationality);

     }

     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtCustomerName.initEvent(btnevent);
          txtPhone.initEvent(btnevent);

     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customer = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbCustomerType = new Components.Label();
        lbCustomerName = new Components.Label();
        lbPhone = new Components.Label();
        cmdGender = new Components.ComboBox();
        txtCustomerName = new Components.TextField();
        txtPhone = new Components.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbCustomerType1 = new Components.Label();
        jLabel4 = new javax.swing.JLabel();
        cmdNationality = new Components.ComboBox();
        buttonSave1 = new ButtonPackage.ButtonSave();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Customer");

        lbCustomerType.setLabelName("Gender");

        lbCustomerName.setLabelName("Customer Name");

        lbPhone.setLabelName("Phone Number");

        txtCustomerName.setLabelTextField("Customer Name");

        txtPhone.setLabelTextField("000 000 0000");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        lbCustomerType1.setLabelName("Nationallity");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");

        buttonSave1.setBackground(new java.awt.Color(0, 153, 255));
        buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSave1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customerLayout = new javax.swing.GroupLayout(customer);
        customer.setLayout(customerLayout);
        customerLayout.setHorizontalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerLayout.createSequentialGroup()
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(customerLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmdGender, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customerLayout.createSequentialGroup()
                                .addComponent(lbCustomerType1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(customerLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281)))
                .addGap(17, 17, 17))
        );
        customerLayout.setVerticalGroup(
            customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCustomerType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCustomerType1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked

        
          
          String txtCus = txtCustomerName.getValueTextField();
          String txtPh = txtPhone.getValueTextField();
          if (txtCus.isEmpty()) {
               txtCus = null;
          }
          if (txtPh.isEmpty()) {
               txtPh = null;
          }
          
          if (txtPh == null) {
               JOptionPane.showMessageDialog(this, "The field phone number is required!");
               return;
          }
          if (!txtPh.isEmpty() && txtPh.length() < 9 || txtPh.length() > 10) {
               JOptionPane.showMessageDialog(this, "The field phone must be 9 or 10 charaters!");
               return;
          }

          if (txtCus != null && txtPh != null && gender != null && nationality != null) {
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
                    } else {
                         JOptionPane.showMessageDialog(this, "The phone number already uesd!");
                    }
               } catch (Exception e) {
                    System.err.println("data err" + e);
               }

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
    private Components.ComboBox cmdGender;
    private Components.ComboBox cmdNationality;
    private javax.swing.JPanel customer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbCustomerName;
    private Components.Label lbCustomerType;
    private Components.Label lbCustomerType1;
    private Components.Label lbPhone;
    private Components.TextField txtCustomerName;
    private Components.TextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
