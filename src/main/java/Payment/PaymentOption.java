package Payment;

import Button.Button;
import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.RadioButton;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.CustomerType.CustomerTypeModel;
import Model.CustomerType.SourceModel;
import Model.ReturnModel.ReturnProductModel;
import Model.Sale.ProductSaleModel;
import Return.ReturnDialog;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class PaymentOption extends javax.swing.JDialog {

     private HashMap<String, String> map = new HashMap<>();
     private String totalUsd;
     DecimalFormat dm = new DecimalFormat("#,##0");
     DecimalFormat df = new DecimalFormat("$ #,##0.00");
     private String sign;
     private Component[] listCom;
     private SubtotalPanel subtotalPanel;
     private String cusTypeId;
     private String sourceId;
     private String paymentType = JavaConstant.typeCash;
     private JPanel detailItem;
     private JPanel boxOne;
     private Button btnPayment;

     public PaymentOption(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          addComboCustomerType();
          addComboSource();
          event();

          // action get select customer type
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    cusTypeId = key;
               }
          };
          cmbCustomerType.initEvent(event);

          // action get select source
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    sourceId = key;
               }
          };
          cmbSource.initEvent(events);

          genderGroupButton();
          nationalityGroupButton();
          setColor();

          paymentType = "cash";
          txtCustomerId.requestFocus();
          radioButtonKhmer.setSelected(true);
          radioButtonMale.setSelected(true);
          getCusomerId();
     }

     private void getCusomerId() {
          Response response = JavaConnection.get(JavaRoute.getCustomerId);
          System.err.println("data response = " + response);

          try {
               if (response.isSuccessful()) {
                    String data = response.body().string();
                    System.err.println("data   = " + data);
                    JSONObject obj = new JSONObject(data);
                    String customerID = obj.getString("data");
                    txtCustomerId.setValueTextFieldCenter(customerID);
               }
          } catch (Exception e) {
          }
     }

     //=================================================
     private void setColor() {
          panelPayment.setBackground(WindowColor.mediumGreen);
          panelTotal.setBackground(WindowColor.mediumGreen);
          lbCashPayment.setFontColor(WindowColor.white);
          lbCash.setFontColor(WindowColor.white);
          lbCreditCard.setFontColor(WindowColor.white);
          labelFontBlack2.setFontColor(WindowColor.white);
          labelFontBlack5.setFontColor(WindowColor.white);
          labelFontBlack9.setFontColor(WindowColor.white);
          lbCreditCard.setBackground(WindowColor.lightBlue);
          lbCash.setBackground(WindowColor.green);
     }

     //================ Gender Option ===================
     private void genderGroupButton() {
          ButtonGroup group = new ButtonGroup();
          group.add(radioButtonMale);
          group.add(radioButtonFemale);
     }

     //================ Nationality Option ===================
     private void nationalityGroupButton() {
          ButtonGroup group = new ButtonGroup();
          group.add(radioButtonKhmer);
          group.add(radioButtonAsian);
          group.add(radioButtonChinese);
          group.add(radioButtonWhite);
          group.add(radioButtonBlack);
     }

     //Action call function placeholder
     private void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtCustomerId.initEvent(btnevent);
          txtEarning.initEvent(btnevent);
          txtCustomerName.initEvent(btnevent);
          txtCustomerPhone.initEvent(btnevent);
          txtCustomerEmail.initEvent(btnevent);
     }

     //Set Combo box Customer Type
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
                         if (i == 0) {
                              cusTypeId = "" + idType;
                         }
                    }
                    cmbCustomerType.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     //Set Combo box Source
     private void addComboSource() {
          HashMap<String, String> source = new HashMap<>();
          try {
               ArrayList<SourceModel> modelSource = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.source);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         SourceModel sourceCombo = new SourceModel(
                              obj.getInt("id"),
                              obj.getString("name")
                         );
                         modelSource.add(sourceCombo);
                         int idSource = modelSource.get(i).getSourceId();
                         String sourceName = modelSource.get(i).getSourceName();
                         source.put(sourceName, "" + idSource);
                         if (i == 0) {
                              sourceId = "" + idSource;
                         }
                    }
                    cmbSource.setMap(source);

               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     private void inputAmount(String value) {
          String receviUsd = txtReceiveUsd.getText();
          String receviKhr = txtReceiveKhr.getText();

          if (sign == "usd") {
               receviUsd += value;
               txtReceiveUsd.setText(receviUsd);

               String strTotalUsd = getTotalUsd().replace("$", "");
               strTotalUsd = strTotalUsd.replace(",", "");
               double doubleTotalUsd = Double.valueOf(strTotalUsd);

               if (!receviUsd.isEmpty()) {
                    String stringReceiveUsd = receviUsd.replace(",", "");
                    double doubleReceviceUsd = Double.valueOf(stringReceiveUsd);
                    double result = doubleReceviceUsd - doubleTotalUsd;
                    if (result < 0) {
                         setValueLabelUsd(result, 0);
                    } else if (result > 0) {
                         setValueLabelUsd(0, result);
                    } else if (result == 0) {
                         setValueLabelUsd(0, 0);
                    }
               }
          } else if (sign == "khr") {
               receviKhr += value;
               txtReceiveKhr.setText(receviKhr);
               String strTotalKhr = lbTotalKhr.getLabelName();
               strTotalKhr = strTotalKhr.replace(",", "");
               double doubleTotalKhr = Double.valueOf(strTotalKhr);

               if (!receviKhr.isEmpty()) {
                    String stringReceiveKhr = receviKhr.replace(",", "");
                    double doubleReceviceKhr = Double.valueOf(stringReceiveKhr);

                    double result = doubleReceviceKhr - doubleTotalKhr;
                    if (result < 0) {
                         setValueLabelKhr(result, 0);
                    } else if (result > 0) {
                         setValueLabelKhr(0, result);

                    } else if (result == 0) {
                         setValueLabelKhr(0, 0);
                    }
               }
          }

     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPayment = new javax.swing.JPanel();
        cmbCoupon = new Components.ComboBox();
        cmbCustomerType = new Components.ComboBox();
        cmbSource = new Components.ComboBox();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbCustomerId = new Components.Label();
        lbCustomerType = new Components.Label();
        lbSource = new Components.Label();
        lbEarning = new Components.Label();
        label1 = new Components.Label();
        lbCustomerPhone = new Components.Label();
        lbCustomerEmail = new Components.Label();
        lbGift = new Components.Label();
        lbGender = new Components.Label();
        lbNationality = new Components.Label();
        lbCash = new Components.LabelFontBlack();
        lbCreditCard = new Components.LabelFontBlack();
        lbCashPayment = new Components.LabelFontBlack();
        panelTotal = new javax.swing.JPanel();
        label5 = new Components.Label();
        label6 = new Components.Label();
        label7 = new Components.Label();
        label8 = new Components.Label();
        label2 = new Components.Label();
        label3 = new Components.Label();
        label4 = new Components.Label();
        label9 = new Components.Label();
        lbTotalUsd = new Components.Label();
        lbRemainingUsd = new Components.Label();
        lbChangeUsd = new Components.Label();
        label14 = new Components.Label();
        label15 = new Components.Label();
        label16 = new Components.Label();
        label17 = new Components.Label();
        lbTotalKhr = new Components.Label();
        lbRemainingKhr = new Components.Label();
        lbChangeKhr = new Components.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtReceiveUsd = new javax.swing.JTextField();
        txtReceiveKhr = new javax.swing.JTextField();
        lbOne = new Components.LabelFontBlack();
        lbTwo = new Components.LabelFontBlack();
        lbThree = new Components.LabelFontBlack();
        lbDelete = new Components.LabelFontBlack();
        lbFour = new Components.LabelFontBlack();
        lbFive = new Components.LabelFontBlack();
        lbSix = new Components.LabelFontBlack();
        lbDot = new Components.LabelFontBlack();
        lbSeven = new Components.LabelFontBlack();
        lbEight = new Components.LabelFontBlack();
        lbNine = new Components.LabelFontBlack();
        lbZero = new Components.LabelFontBlack();
        labelFontBlack2 = new Components.LabelFontBlack();
        labelFontBlack5 = new Components.LabelFontBlack();
        labelFontBlack9 = new Components.LabelFontBlack();
        txtCustomerId = new Components.TextFieldCenter();
        txtEarning = new Components.TextFieldCenter();
        txtCustomerName = new Components.TextFieldCenter();
        txtCustomerPhone = new Components.TextFieldCenter();
        txtCustomerEmail = new Components.TextFieldCenter();
        radioButtonKhmer = new javax.swing.JRadioButton();
        radioButtonAsian = new javax.swing.JRadioButton();
        radioButtonChinese = new javax.swing.JRadioButton();
        radioButtonWhite = new javax.swing.JRadioButton();
        radioButtonBlack = new javax.swing.JRadioButton();
        radioButtonMale = new javax.swing.JRadioButton();
        radioButtonFemale = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Payment Option");

        lbCustomerId.setLabelName("Customer ID");

        lbCustomerType.setLabelName("Customer Type");

        lbSource.setLabelName("Source");

        lbEarning.setLabelName("Earning ($)");

        label1.setLabelName("Customer Name");

        lbCustomerPhone.setLabelName("Customer Phone");

        lbCustomerEmail.setLabelName("Customer Email");

        lbGift.setLabelName("Gift Coupon ($)");

        lbGender.setLabelName(" Gender");

        lbNationality.setLabelName(" Nationality");

        lbCash.setBackground(new java.awt.Color(51, 153, 255));
        lbCash.setFontColor(java.awt.Color.white);
        lbCash.setLabelName("Cash");
        lbCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCashMouseClicked(evt);
            }
        });

        lbCreditCard.setBackground(new java.awt.Color(51, 153, 255));
        lbCreditCard.setFontColor(java.awt.Color.white);
        lbCreditCard.setLabelName("Credit Card");
        lbCreditCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCreditCardMouseClicked(evt);
            }
        });

        lbCashPayment.setBackground(new java.awt.Color(51, 153, 255));
        lbCashPayment.setFontColor(java.awt.Color.white);
        lbCashPayment.setLabelName("Cash Payment");

        label5.setLabelName("Total Pay");

        label6.setLabelName("Receive");

        label7.setLabelName("Remaining");

        label8.setLabelName("Change");

        label2.setLabelName(":");

        label3.setLabelName(":");

        label4.setLabelName(":");

        label9.setLabelName(":");

        lbTotalUsd.setLabelName("$ 0.00");

        lbRemainingUsd.setLabelName("$ 0.00");

        lbChangeUsd.setLabelName("$ 0.00");

        label14.setLabelName(":");

        label15.setLabelName(":");

        label16.setLabelName(":");

        label17.setLabelName(":");

        lbTotalKhr.setLabelName("0");

        lbRemainingKhr.setLabelName("0");

        lbChangeKhr.setLabelName("0");

        jLabel1.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel1.setText("៛");

        jLabel2.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel2.setText("៛");

        jLabel3.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel3.setText("៛");

        jLabel4.setFont(new java.awt.Font("Khmer OS Content", 0, 12)); // NOI18N
        jLabel4.setText("៛");

        txtReceiveUsd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtReceiveUsd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtReceiveUsdMouseClicked(evt);
            }
        });
        txtReceiveUsd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReceiveUsdKeyReleased(evt);
            }
        });

        txtReceiveKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtReceiveKhr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtReceiveKhrMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtReceiveKhrMouseEntered(evt);
            }
        });
        txtReceiveKhr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReceiveKhrKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelTotalLayout = new javax.swing.GroupLayout(panelTotal);
        panelTotal.setLayout(panelTotalLayout);
        panelTotalLayout.setHorizontalGroup(
            panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTotalLayout.createSequentialGroup()
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbChangeUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panelTotalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTotalLayout.createSequentialGroup()
                        .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTotalLayout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRemainingUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTotalLayout.createSequentialGroup()
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtReceiveUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbChangeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(txtReceiveKhr, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(lbRemainingKhr, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbTotalKhr, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        panelTotalLayout.setVerticalGroup(
            panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotalUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalKhr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTotalLayout.createSequentialGroup()
                        .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addComponent(txtReceiveKhr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtReceiveUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTotalLayout.createSequentialGroup()
                        .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRemainingKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRemainingUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbChangeUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTotalLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(lbChangeKhr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbOne.setBackground(new java.awt.Color(255, 255, 255));
        lbOne.setForeground(new java.awt.Color(255, 255, 255));
        lbOne.setFontColor(new java.awt.Color(56, 56, 56));
        lbOne.setLabelName("1");
        lbOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOneMouseClicked(evt);
            }
        });

        lbTwo.setBackground(new java.awt.Color(255, 255, 255));
        lbTwo.setFontColor(new java.awt.Color(56, 56, 56));
        lbTwo.setLabelName("2");
        lbTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTwoMouseClicked(evt);
            }
        });

        lbThree.setBackground(new java.awt.Color(255, 255, 255));
        lbThree.setFontColor(new java.awt.Color(56, 56, 56));
        lbThree.setLabelName("3");
        lbThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThreeMouseClicked(evt);
            }
        });

        lbDelete.setBackground(new java.awt.Color(255, 255, 255));
        lbDelete.setFontColor(new java.awt.Color(56, 56, 56));
        lbDelete.setLabelName("Del");
        lbDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDeleteMouseClicked(evt);
            }
        });

        lbFour.setBackground(new java.awt.Color(255, 255, 255));
        lbFour.setFontColor(new java.awt.Color(56, 56, 56));
        lbFour.setLabelName("4");
        lbFour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFourMouseClicked(evt);
            }
        });

        lbFive.setBackground(new java.awt.Color(255, 255, 255));
        lbFive.setFontColor(new java.awt.Color(56, 56, 56));
        lbFive.setLabelName("5");
        lbFive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFiveMouseClicked(evt);
            }
        });

        lbSix.setBackground(new java.awt.Color(255, 255, 255));
        lbSix.setFontColor(new java.awt.Color(56, 56, 56));
        lbSix.setLabelName("6");
        lbSix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSixMouseClicked(evt);
            }
        });

        lbDot.setBackground(new java.awt.Color(255, 255, 255));
        lbDot.setFontColor(new java.awt.Color(56, 56, 56));
        lbDot.setLabelName(".");
        lbDot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDotMouseClicked(evt);
            }
        });

        lbSeven.setBackground(new java.awt.Color(255, 255, 255));
        lbSeven.setFontColor(new java.awt.Color(56, 56, 56));
        lbSeven.setLabelName("7");
        lbSeven.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSevenMouseClicked(evt);
            }
        });

        lbEight.setBackground(new java.awt.Color(255, 255, 255));
        lbEight.setFontColor(new java.awt.Color(56, 56, 56));
        lbEight.setLabelName("8");
        lbEight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEightMouseClicked(evt);
            }
        });

        lbNine.setBackground(new java.awt.Color(255, 255, 255));
        lbNine.setFontColor(new java.awt.Color(56, 56, 56));
        lbNine.setLabelName("9");
        lbNine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNineMouseClicked(evt);
            }
        });

        lbZero.setBackground(new java.awt.Color(255, 255, 255));
        lbZero.setFontColor(new java.awt.Color(56, 56, 56));
        lbZero.setLabelName("0");
        lbZero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbZeroMouseClicked(evt);
            }
        });

        labelFontBlack2.setBackground(new java.awt.Color(204, 0, 0));
        labelFontBlack2.setFontColor(java.awt.Color.white);
        labelFontBlack2.setLabelName("Back");
        labelFontBlack2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFontBlack2MouseClicked(evt);
            }
        });

        labelFontBlack5.setBackground(new java.awt.Color(47, 152, 70));
        labelFontBlack5.setFontColor(java.awt.Color.white);
        labelFontBlack5.setLabelName("Charge&eInvoice");

        labelFontBlack9.setBackground(new java.awt.Color(47, 152, 70));
        labelFontBlack9.setFontColor(java.awt.Color.white);
        labelFontBlack9.setLabelName("Charge&Print");
        labelFontBlack9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFontBlack9MouseClicked(evt);
            }
        });

        txtCustomerId.setLabelTextCenter("Scan or input");

        txtEarning.setLabelTextCenter("$ 0.00");

        txtCustomerName.setLabelTextCenter("No found");

        txtCustomerPhone.setLabelTextCenter("No found");

        txtCustomerEmail.setLabelTextCenter("No found");

        radioButtonKhmer.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonKhmer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonKhmer.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonKhmer.setText("Khmer");

        radioButtonAsian.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonAsian.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonAsian.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonAsian.setText("Asian");

        radioButtonChinese.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonChinese.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonChinese.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonChinese.setText("Chinese");

        radioButtonWhite.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonWhite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonWhite.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonWhite.setText("White");

        radioButtonBlack.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonBlack.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonBlack.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonBlack.setText("Black");

        radioButtonMale.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonMale.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonMale.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonMale.setText("Male");

        radioButtonFemale.setBackground(new java.awt.Color(176, 215, 181));
        radioButtonFemale.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        radioButtonFemale.setForeground(new java.awt.Color(56, 56, 56));
        radioButtonFemale.setText("Female");

        javax.swing.GroupLayout panelPaymentLayout = new javax.swing.GroupLayout(panelPayment);
        panelPayment.setLayout(panelPaymentLayout);
        panelPaymentLayout.setHorizontalGroup(
            panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPaymentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCustomerId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbSource, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbEarning, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbSource, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCustomerType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCustomerId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGift, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(radioButtonMale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(radioButtonFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNationality, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(radioButtonKhmer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioButtonAsian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioButtonChinese, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioButtonWhite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioButtonBlack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(90, 90, 90)))
                .addGap(12, 12, 12)
                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbCoupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCashPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelFontBlack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelFontBlack5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                                .addComponent(lbNine, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbZero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(labelFontBlack9, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                                    .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                                .addComponent(lbOne, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                                .addComponent(lbFour, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbFive, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbEight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbThree, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbSix, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                            .addComponent(lbDot, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPaymentLayout.setVerticalGroup(
            panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPaymentLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCustomerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCustomerType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCustomerPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addComponent(txtCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(7, 7, 7)))
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPaymentLayout.createSequentialGroup()
                                .addComponent(cmbCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelPaymentLayout.createSequentialGroup()
                                    .addComponent(lbEarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(49, 49, 49))
                                .addGroup(panelPaymentLayout.createSequentialGroup()
                                    .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbGift, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEarning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addComponent(lbCashPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbThree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTwo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbOne, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCash, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSix, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCreditCard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSeven, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbZero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFontBlack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFontBlack5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFontBlack9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPaymentLayout.createSequentialGroup()
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonKhmer)
                            .addComponent(radioButtonMale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonFemale)
                            .addComponent(radioButtonAsian))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonChinese)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonWhite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonBlack)))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labelFontBlack2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFontBlack2MouseClicked
         this.dispose();
    }//GEN-LAST:event_labelFontBlack2MouseClicked

    private void lbOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOneMouseClicked
         String number = lbOne.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbOneMouseClicked

    private void lbTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTwoMouseClicked
         String number = lbTwo.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbTwoMouseClicked

    private void lbThreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThreeMouseClicked
         String number = lbThree.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbThreeMouseClicked

    private void lbFourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFourMouseClicked
         String number = lbFour.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbFourMouseClicked

    private void lbFiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiveMouseClicked
         String number = lbFive.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbFiveMouseClicked

    private void lbSixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSixMouseClicked
         String number = lbSix.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbSixMouseClicked

    private void lbDotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDotMouseClicked
         String number = lbDot.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbDotMouseClicked

    private void lbSevenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSevenMouseClicked
         String number = lbSeven.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbSevenMouseClicked

    private void lbEightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEightMouseClicked
         String number = lbEight.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbEightMouseClicked

    private void lbNineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNineMouseClicked
         String number = lbNine.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbNineMouseClicked

    private void lbZeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbZeroMouseClicked
         String number = lbZero.getLabelName();
         inputAmount(number);
    }//GEN-LAST:event_lbZeroMouseClicked

    private void lbDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDeleteMouseClicked
         if (!txtReceiveUsd.getText().isEmpty()) {
              String valueReceive = txtReceiveUsd.getText();
              valueReceive = valueReceive.substring(0, valueReceive.length() - 1);
              txtReceiveUsd.setText("");
              if (valueReceive.isEmpty()) {
                   setValueLabelUsd(0, 0);
              }
              inputAmount(valueReceive);
         }

         if (!txtReceiveKhr.getText().isEmpty()) {
              String valueReceive = txtReceiveKhr.getText();
              valueReceive = valueReceive.substring(0, valueReceive.length() - 1);
              txtReceiveKhr.setText("");
              if (valueReceive.isEmpty()) {
                   setValueLabelKhr(0, 0);
              }
              inputAmount(valueReceive);
         }
    }//GEN-LAST:event_lbDeleteMouseClicked

    private void txtReceiveUsdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiveUsdMouseClicked
         sign = "usd";
    }//GEN-LAST:event_txtReceiveUsdMouseClicked

    private void txtReceiveKhrMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiveKhrMouseEntered

    }//GEN-LAST:event_txtReceiveKhrMouseEntered

    private void txtReceiveKhrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiveKhrMouseClicked
         sign = "khr";
    }//GEN-LAST:event_txtReceiveKhrMouseClicked

    private void txtReceiveUsdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveUsdKeyReleased
         if (txtReceiveUsd.getText().length() > 0) {
              double totalUsdValue = JavaConstant.getReplace(totalUsd);
              double rUsdValue = JavaConstant.getReplace(txtReceiveUsd.getText());
              double resultValueUsd = rUsdValue - totalUsdValue;
              if (resultValueUsd == 0) {
                   setValueLabelUsd(resultValueUsd, resultValueUsd);
              } else if (resultValueUsd > 0) {
                   setValueLabelUsd(0, resultValueUsd);
              } else if (resultValueUsd < 0) {
                   setValueLabelUsd(resultValueUsd, 0);
              }
         } else {
              setValueLabelUsd(0, 0);
         }
    }//GEN-LAST:event_txtReceiveUsdKeyReleased

    private void txtReceiveKhrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveKhrKeyReleased
         if (txtReceiveKhr.getText().length() > 0) {
              double totalKhrValue = JavaConstant.getReplace(lbTotalKhr.getLabelName());
              double rKhrValue = JavaConstant.getReplace(txtReceiveKhr.getText());
              double resultValueKhr = rKhrValue - totalKhrValue;

              if (resultValueKhr == 0) {
                   setValueLabelKhr(resultValueKhr, resultValueKhr);
              } else if (resultValueKhr > 0) {

                   setValueLabelKhr(0, resultValueKhr);
              } else if (resultValueKhr < 0) {
                   setValueLabelKhr(resultValueKhr, 0);
              }
         } else {
              setValueLabelKhr(0, 0);
         }
    }//GEN-LAST:event_txtReceiveKhrKeyReleased

    private void labelFontBlack9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFontBlack9MouseClicked

         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
         if (txtReceiveKhr.getText().isEmpty() && txtReceiveUsd.getText().isEmpty()) {
              j.setMessage("Box Receive must be have one value!");
              j.setVisible(true);
              return;
         }

         if (lbRemainingUsd.getLabelName().contains("-")) {
              j.setMessage("remainningUsd : " + lbRemainingUsd.getLabelName());
              j.setVisible(true);
              return;
         }

         if (lbRemainingKhr.getLabelName().contains("-")) {
              j.setMessage("remainningKhr : " + lbRemainingKhr.getLabelName());
              j.setVisible(true);
              return;
         }

         // data is return 
         if (JavaConstant.isReturn != null) {
              returnProduct();
              return;
         }

         double discount = JavaConstant.getReplace(subtotalPanel.getLableDiscountUsd());
         // double deliveryFee = JavaConstant.getReplace(subtotalPanel.getLableDeliveryUsd());
         double subTotal = JavaConstant.getReplace(subtotalPanel.getLabelSubtotalUsd());
         double total = JavaConstant.getReplace(subtotalPanel.getLableTotalUsd());
         double remainningUsd = JavaConstant.getReplace(lbRemainingUsd.getLabelName());
         double remainningKhr = JavaConstant.getReplace(lbRemainingKhr.getLabelName());
         double changeUsd = JavaConstant.getReplace(lbChangeUsd.getLabelName());
         double changeKhr = JavaConstant.getReplace(lbChangeKhr.getLabelName());

         JSONObject jsonData = new JSONObject();
         jsonData.put("userId", JavaConstant.cashierId);
         jsonData.put("userCode", JavaConstant.userCode);
         jsonData.put("saleDate", JavaConstant.currentDate);
         jsonData.put("discount", discount);
         jsonData.put("subTotal", subTotal);
         jsonData.put("deliveryFee", "0");
         jsonData.put("total", total);

         //get dataPay
         HashMap<String, Object> dataPay = new HashMap<>();
         dataPay.put("sourceId", sourceId);
         dataPay.put("customerTypeId", cusTypeId);
         dataPay.put("paymentType", paymentType);
         dataPay.put("receiveKhr", txtReceiveKhr.getText());
         dataPay.put("receiveUsd", txtReceiveUsd.getText());
         dataPay.put("remainingUsd", remainningUsd);
         dataPay.put("remainingKhr", remainningKhr);
         dataPay.put("changeUsd", changeUsd);
         dataPay.put("changeKhr", changeKhr);
         jsonData.put("dataPay", dataPay);

         //get customer 
         HashMap<String, Object> customer = new HashMap<>();
         customer.put("cusName", txtCustomerName.getValueTextFieldCenter());
         customer.put("contact", txtCustomerPhone.getValueTextFieldCenter());
         customer.put("email", txtCustomerEmail.getValueTextFieldCenter());
         customer.put("earning", txtEarning.getValueTextFieldCenter());
         if (radioButtonKhmer.isSelected()) {
              customer.put("nationality", radioButtonKhmer.getText());
         } else if (radioButtonAsian.isSelected()) {
              customer.put("nationality", radioButtonAsian.getText());
         } else if (radioButtonChinese.isSelected()) {
              customer.put("nationality", radioButtonChinese.getText());
         } else if (radioButtonWhite.isSelected()) {
              customer.put("nationality", radioButtonWhite.getText());
         } else if (radioButtonBlack.isSelected()) {
              customer.put("nationality", radioButtonBlack.getText());
         }
         if (radioButtonMale.isSelected()) {
              customer.put("gender", radioButtonMale.getText());
         } else if (radioButtonFemale.isSelected()) {
              customer.put("gender", radioButtonFemale.getText());
         }

         if (txtCustomerName.getValueTextFieldCenter() != null) {
              jsonData.put("customer", customer);
         }

         //get dataSale 
         ArrayList<ProductSaleModel> dataSale = new ArrayList<>();
         for (int i = 0; i < listCom.length; i++) {
              var obj = ((BoxItem) listCom[i]);
              double price = JavaConstant.getReplace(obj.getLabelPrice());

              int discountDigit = obj.getDiscountDigit();
              double unitPrice = price - (price * discountDigit) / 100;
              double p = JavaConstant.getReplace(df.format(unitPrice));

              double amount = obj.getQty() * p;
              double a = JavaConstant.getReplace(df.format(amount));
              ProductSaleModel pro = new ProductSaleModel(
                   obj.getProductId(),
                   obj.getQty(),
                   p,
                   a,
                   discountDigit
              );
              dataSale.add(pro);
         }
         jsonData.put("dataSale", dataSale);

         Response response = JavaConnection.post(JavaRoute.sale, jsonData);

         if (response.isSuccessful()) {
              dispose();
              detailItem.removeAll();
              detailItem.revalidate();
              detailItem.repaint();
              subtotalPanel.setLabelSubTitleToZero();
              btnPayment.setBackground(WindowColor.lightGray);

              // remove hole order
              if (!JavaConstant.listHoldData.isEmpty()) {
                   int index = JavaConstant.indexArrayListHold;
                   JavaConstant.listHoldData.remove(index);
                   JavaConstant.indexArrayListHold = 0;
              }
         }
         else{
            JOptionPane.showMessageDialog(this, "Charge Failed!");
            return;
         }


    }//GEN-LAST:event_labelFontBlack9MouseClicked

     void returnProduct() {
          double totalReturn = 0;
          if (!txtReceiveUsd.getText().isEmpty()) {
               totalReturn = Double.valueOf(txtReceiveUsd.getText());
          }

          if (!txtReceiveKhr.getText().isEmpty()) {
               double returnAmountKhr = Double.valueOf(txtReceiveKhr.getText());
               totalReturn = returnAmountKhr / JavaConstant.exchangeRate;
          }
          System.err.println("JavaConstant.returnerId = " + JavaConstant.returnerId);
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
               int discountDigit = obj.getDiscountDigit();

               ReturnProductModel pro = new ReturnProductModel(
                    obj.getProductId(),
                    obj.getQty(),
                    price,
                    amount,
                    discountDigit
               );
               dataDetails.add(pro);
          }
          jsonReturnData.put("dataDetails", dataDetails);

          Response responseReturn = JavaConnection.post(JavaRoute.returnProduct, jsonReturnData);

          if (responseReturn.isSuccessful()) {
               dispose();
               detailItem.removeAll();
               detailItem.revalidate();
               detailItem.repaint();
               subtotalPanel.setLabelSubTitleToZero();
               btnPayment.setBackground(WindowColor.lightGray);
               // assign JavaConstant.isReturn , reasonId , inovoiceNo to null
               ReturnDialog r = new ReturnDialog(new JFrame(), true);
               r.setResetReturn();
          } else {
               System.err.println("err = 4444");
          }

     }


     private void lbCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCashMouseClicked
          paymentType = "cash";
          lbCreditCard.setBackground(WindowColor.lightBlue);
          lbCash.setBackground(WindowColor.green);
     }//GEN-LAST:event_lbCashMouseClicked

     private void lbCreditCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreditCardMouseClicked
          paymentType = JavaConstant.typeCredit;
          lbCreditCard.setBackground(WindowColor.green);
          lbCash.setBackground(WindowColor.lightBlue);
     }//GEN-LAST:event_lbCreditCardMouseClicked

     private void setValueLabelUsd(double remaining, double change) {
          lbRemainingUsd.setLabelName(df.format(remaining));
          lbChangeUsd.setLabelName(df.format(change));
     }

     private void setValueLabelKhr(double remaining, double change) {

          lbRemainingKhr.setLabelName(dm.format(remaining));

          lbChangeKhr.setLabelName(dm.format(change));
     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PaymentOption dialog = new PaymentOption(new javax.swing.JFrame(), true);
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

     public String getTotalUsd() {
          return totalUsd;
     }

     public void setTotalUsd(String totalUsd) {
          this.totalUsd = totalUsd;
          lbTotalUsd.setLabelName(totalUsd);
          String moneyUsd = totalUsd.replace("$", "");
          moneyUsd = moneyUsd.replace(",", "");
          double totalKhr = Double.valueOf(moneyUsd);
          double _totalKh = JavaRoundDown.roundDown("" + totalKhr * JavaConstant.exchangeRate);
          lbTotalKhr.setLabelName(dm.format(_totalKh));
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public JPanel getBoxOne() {
          return boxOne;
     }

     public void setBoxOne(JPanel boxOne) {
          this.boxOne = boxOne;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.ComboBox cmbCoupon;
    private Components.ComboBox cmbCustomerType;
    private Components.ComboBox cmbSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private Components.Label label1;
    private Components.Label label14;
    private Components.Label label15;
    private Components.Label label16;
    private Components.Label label17;
    private Components.Label label2;
    private Components.Label label3;
    private Components.Label label4;
    private Components.Label label5;
    private Components.Label label6;
    private Components.Label label7;
    private Components.Label label8;
    private Components.Label label9;
    private Components.LabelFontBlack labelFontBlack2;
    private Components.LabelFontBlack labelFontBlack5;
    private Components.LabelFontBlack labelFontBlack9;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.LabelFontBlack lbCash;
    private Components.LabelFontBlack lbCashPayment;
    private Components.Label lbChangeKhr;
    private Components.Label lbChangeUsd;
    private Components.LabelFontBlack lbCreditCard;
    private Components.Label lbCustomerEmail;
    private Components.Label lbCustomerId;
    private Components.Label lbCustomerPhone;
    private Components.Label lbCustomerType;
    private Components.LabelFontBlack lbDelete;
    private Components.LabelFontBlack lbDot;
    private Components.Label lbEarning;
    private Components.LabelFontBlack lbEight;
    private Components.LabelFontBlack lbFive;
    private Components.LabelFontBlack lbFour;
    private Components.Label lbGender;
    private Components.Label lbGift;
    private Components.Label lbNationality;
    private Components.LabelFontBlack lbNine;
    private Components.LabelFontBlack lbOne;
    private Components.Label lbRemainingKhr;
    private Components.Label lbRemainingUsd;
    private Components.LabelFontBlack lbSeven;
    private Components.LabelFontBlack lbSix;
    private Components.Label lbSource;
    private Components.LabelFontBlack lbThree;
    private Components.Label lbTotalKhr;
    private Components.Label lbTotalUsd;
    private Components.LabelFontBlack lbTwo;
    private Components.LabelFontBlack lbZero;
    private javax.swing.JPanel panelPayment;
    private javax.swing.JPanel panelTotal;
    private javax.swing.JRadioButton radioButtonAsian;
    private javax.swing.JRadioButton radioButtonBlack;
    private javax.swing.JRadioButton radioButtonChinese;
    private javax.swing.JRadioButton radioButtonFemale;
    private javax.swing.JRadioButton radioButtonKhmer;
    private javax.swing.JRadioButton radioButtonMale;
    private javax.swing.JRadioButton radioButtonWhite;
    private Components.TextFieldCenter txtCustomerEmail;
    private Components.TextFieldCenter txtCustomerId;
    private Components.TextFieldCenter txtCustomerName;
    private Components.TextFieldCenter txtCustomerPhone;
    private Components.TextFieldCenter txtEarning;
    private javax.swing.JTextField txtReceiveKhr;
    private javax.swing.JTextField txtReceiveUsd;
    // End of variables declaration//GEN-END:variables
}
