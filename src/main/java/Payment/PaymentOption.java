package Payment;

import BlogCode.AppValidation;
import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Components.TextField;
import static Components.TextField.onlyDigits;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoundDown;
import Constant.JavaRoundUpKhr;
import Constant.JavaRoute;
import Event.ButtonEvent;
import HoldOrder.HoldeModel;
import Model.CustomerType.CustomerTypeModel;
import Model.CustomerType.SourceModel;
import Model.Reprint.DataSuccessModel;
import Model.ReturnModel.ModelReturnData;
import Model.ReturnModel.ReturnProductModel;
import Model.Sale.ProductSaleModel;
import Receipt.Receipt;
import Return.PrinterReturn;
import Return.ReturnDialog;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class PaymentOption extends javax.swing.JDialog {

     private HashMap<String, String> map = new HashMap<>();
     private String totalUsd;
     DecimalFormat dm = new DecimalFormat("#,##0");
     DecimalFormat df = new DecimalFormat("$ #,##0.00");
     DecimalFormat dd = new DecimalFormat("#,##0.00");

     private String sign = "khr";
     private Component[] listCom;

     private String cusTypeId;
     private String sourceId;
     private String paymentType = JavaConstant.typeCash;
     private JPanel detailItem;
     private JPanel boxOne;
     private JPanel panelProduct;
     private Button btnPayment;
     private Button buttonHoldOrder;
     private ButtonCancel btnCancel;
     private SubtotalPanel subtotalPanel;
     private Button btnReturn;
     private Button btnDiscount;
     private JLabel titleOrder;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     private JScrollPane jScrollPaneDetail;

     public JScrollPane getjScrollPaneDetail() {
          return jScrollPaneDetail;
     }

     public void setjScrollPaneDetail(JScrollPane jScrollPaneDetail) {
          this.jScrollPaneDetail = jScrollPaneDetail;
     }

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
//          getCusomerId();
          txtReceiveKhr.requestFocus();

          txtReceiveUsd.setBorder(null);
          txtReceiveKhr.setBorder(null);

          evenGroup();
          customerFun();

          setRoundRadius(2, 3, 2);
     }

     void setRoundRadius(int radius, int padding, int left) {
          lbOne.setRoundRadious(radius);
          lbTwo.setRoundRadious(radius);
          lbThree.setRoundRadious(radius);
          lbFour.setRoundRadious(radius);
          lbFive.setRoundRadious(radius);
          lbSix.setRoundRadious(radius);
          lbSeven.setRoundRadious(radius);
          lbEight.setRoundRadious(radius);
          lbNine.setRoundRadious(radius);
          lbZero.setRoundRadious(radius);
          lbDelete.setRoundRadious(radius);
          lbDot.setRoundRadious(radius);

          lbOne.setBorder(new EmptyBorder(padding, left, padding, left));
          lbTwo.setBorder(new EmptyBorder(padding, left, padding, left));
          lbThree.setBorder(new EmptyBorder(padding, left, padding, left));
          lbFour.setBorder(new EmptyBorder(padding, left, padding, left));
          lbFive.setBorder(new EmptyBorder(padding, left, padding, left));
          lbSix.setBorder(new EmptyBorder(padding, left, padding, left));
          lbSeven.setBorder(new EmptyBorder(padding, left, padding, left));
          lbEight.setBorder(new EmptyBorder(padding, left, padding, left));
          lbNine.setBorder(new EmptyBorder(padding, left, padding, left));
          lbZero.setBorder(new EmptyBorder(padding, left, padding, left));
          lbDelete.setBorder(new EmptyBorder(padding, left, padding, left));
          lbDot.setBorder(new EmptyBorder(padding, left, padding, left));
     }

     void customerFun() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    changeBackGroundBtn();
               }
          };

          txtCustomerId.initEvent(event);
          txtCustomerName.initEvent(event);
          txtCustomerPhone.initEvent(event);
     }

     void changeBackGroundBtn() {

          String custId = txtCustomerId.getValueTextFieldCenter();
          String cusName = txtCustomerName.getValueTextFieldCenter();
          String phone = txtCustomerPhone.getValueTextFieldCenter();

          if (custId != null
               && cusName != null
               && phone != null) {
               btnEinvoice.setBackground(WindowColor.green);
          }

          if (custId.isEmpty()
               || cusName.isEmpty()
               || phone.isEmpty()) {
               btnEinvoice.setBackground(WindowColor.lightGray);
          }
     }

     void evenGroup() {
          txtReceiveUsd.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {

               }

               @Override
               public void keyPressed(KeyEvent e) {

               }

               @Override
               public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                         keyDelete("keyboard");
                    } else {
                         String _val = txtReceiveUsd.getText().replace(",", "");
                         addCommaUsd(_val);
                    }
               }

          });

          txtReceiveKhr.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {

               }

               @Override
               public void keyPressed(KeyEvent e) {

               }

               @Override
               public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                         keyDelete("keyboard");

                    } else {
                         String _val = txtReceiveKhr.getText().replace(",", "");
                         addCommaKhr(_val);
                    }
               }

          });

     }

     private void getCusomerId() {
          Response response = JavaConnection.get(JavaRoute.getCustomerId);
          try {
               if (response.isSuccessful()) {
                    String data = response.body().string();

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
          btnEinvoice.setFontColor(WindowColor.white);
          buttonChargeAndPrint.setFontColor(WindowColor.white);
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
                              cusTypeId = "" + 1;
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
                              sourceId = "" + 2;
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

          if (JavaConstant.isReturn != null) {
               return;
          }

          String receviUsd = txtReceiveUsd.getText();
          String receviKhr = txtReceiveKhr.getText();
          receviKhr = receviKhr.replace(",", "");
          receviUsd = receviUsd.replace(",", "");

          if ("usd".equals(sign)) {

               receviUsd += value;
               txtReceiveUsd.setText(receviUsd);

               double doubleTotalUsd = JavaConstant.getReplace(getTotalUsd());

               //    ======= check some validattion =======
               boolean isCheck = AppValidation.checkValidation(txtReceiveUsd);
               if (isCheck) {
                    return;
               }
               //  =======  end =======

               if (!receviUsd.isEmpty()) {

                    double doubleReceviceUsd = JavaConstant.getReplace(receviUsd);
                    double result = doubleReceviceUsd - doubleTotalUsd;

                    if (result < 0) {
                         setValueLabelUsd(result, 0);
                    } else if (result > 0) {
                         if (result > 5) {
                              setValueLabelUsd(0, result);
                         } else {
                              setValueLabelUsd(0, result);
                         }
                    } else if (result == 0) {
                         setValueLabelUsd(0, 0);
                    }
               } else {

                    receviUsd = "0";

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

                    if (txtReceiveUsd.getText().isEmpty() && txtReceiveKhr.getText().isEmpty()) {
                         lbRemainingKhr.setLabelName(dm.format(0));
                         lbRemainingUsd.setLabelName(df.format(0));
                         lbChangeKhr.setLabelName(dm.format(0));
                         lbChangeUsd.setLabelName(df.format(0));
                    }

                    if (!receviKhr.isEmpty()) {
                         funKhr(receviKhr);
                    }
                    return;

               }

          } else if ("khr".equals(sign)) {
               receviKhr += value;
               txtReceiveKhr.setText(receviKhr);
               String strTotalKhr = lbTotalKhr.getLabelName();
               strTotalKhr = strTotalKhr.replace(",", "");
               double doubleTotalKhr = Double.parseDouble(strTotalKhr);
               // =============== check validataion =============
               boolean isCheck = AppValidation.checkValidation(txtReceiveKhr);
               if (isCheck) {
                    return;
               }
               // ============ end =============
               if (!receviKhr.isEmpty()) {
                    String stringReceiveKhr = receviKhr.replace(",", "");
                    double doubleReceviceKhr = Double.parseDouble(stringReceiveKhr);

                    double result = doubleReceviceKhr - doubleTotalKhr;
                    if (result < 0) {
                         setValueLabelKhr(result, 0);
                    } else if (result > 0) {
                         setValueLabelKhr(0, result);
                    } else if (result == 0) {
                         setValueLabelKhr(0, 0);
                    }
               } else {
                    receviKhr = "0";

                    String stringReceiveKhr = receviKhr.replace(",", "");
                    double doubleReceviceKhr = Double.parseDouble(stringReceiveKhr);

                    double result = doubleReceviceKhr - doubleTotalKhr;
                    if (result < 0) {
                         setValueLabelKhr(result, 0);
                    } else if (result > 0) {
                         setValueLabelKhr(0, result);
                    } else if (result == 0) {
                         setValueLabelKhr(0, 0);
                    }

                    if (txtReceiveUsd.getText().isEmpty() && txtReceiveKhr.getText().isEmpty()) {
                         lbRemainingKhr.setLabelName(dm.format(0));
                         lbRemainingUsd.setLabelName(df.format(0));
                         lbChangeKhr.setLabelName(dm.format(0));
                         lbChangeUsd.setLabelName(df.format(0));
                    }

                    if (!receviUsd.isEmpty()) {
                         funUsd(receviUsd);
                    }

               }

          }

          checkBothValueTextField(receviKhr, receviUsd);
          //         =============== validation ==========
          addCommaKhr(receviKhr);
          addCommaUsd(receviUsd);
     }

     void checkBothValueTextField(String receviKhr, String receviUsd) {
          if (!receviKhr.isEmpty() && !receviUsd.isEmpty()) {

               if (txtReceiveKhr.getText().isEmpty() && txtReceiveUsd.getText().isEmpty()) {
                    lbRemainingKhr.setLabelName(dm.format(0));
                    lbRemainingUsd.setLabelName(df.format(0));
                    lbChangeKhr.setLabelName(dm.format(0));
                    lbChangeUsd.setLabelName(df.format(0));
               }

               double _totalUsd = JavaConstant.getReplace(getTotalUsd());
               double _convertToUsd = Double.parseDouble(receviKhr) / JavaConstant.exchangeRate;
               double _usd = Double.parseDouble(receviUsd) + _convertToUsd;
               double result = _usd - _totalUsd;
               if (Double.parseDouble(receviUsd) > _convertToUsd) { // usd > khr
                    paidBothValue(result, "usd");
               } else {
                    paidBothValue(result, "khr"); // khr > usd
               }
          }
     }

     void addCommaKhr(String receviKhr) {

          // ================ 3 length insert comma =========
          if (receviKhr.length() > 3) {
               StringBuilder builder = new StringBuilder(receviKhr.replaceAll(",", ""));
               for (int i = builder.length() - 3; i > 0; i -= 3) {
                    builder.insert(i, ",");
               }
               setValueTextField(builder.toString());
          }
     }

     void addCommaUsd(String receviUsd) {

          //          boolean isCheck = onlyDigits(receviKhr);
          // ================ 3 length insert comma =========
          if (receviUsd.length() > 3 && !receviUsd.contains(".")) {
               StringBuilder builder = new StringBuilder(receviUsd.replaceAll(",", ""));

               for (int i = builder.length() - 3; i > 0; i -= 3) {
                    builder.insert(i, ",");
               }
               txtReceiveUsd.setText(builder.toString());
               txtReceiveUsd.setForeground(Color.BLACK);
          }

     }

     private void paidBothValue(double value, String types) {
          String strValue = "" + value;
          strValue = strValue.replace("-", "");
          double _d = Double.parseDouble(strValue);
          double _remainingUsd = _d * JavaConstant.exchangeRate;
          String receviUsd = txtReceiveUsd.getText();
          String receviKhr = txtReceiveKhr.getText();

          double lastPoint = 0;
          if (value < 0) { // remaining
               lbRemainingUsd.setLabelName(df.format(_d));
               String _khr = JavaRoundUpKhr.setRoundNumber(_remainingUsd);
               lbRemainingKhr.setLabelName(_khr);

               lbChangeKhr.setLabelName(dm.format(0));
               lbChangeUsd.setLabelName(df.format(0));

          } else {
               lbRemainingKhr.setLabelName(dm.format(0));
               lbRemainingUsd.setLabelName(df.format(0));

               if (types.equals("usd")) {
                    lastPoint = _d * JavaConstant.exchangeRate4050;
               } else {
                    lastPoint = _d * JavaConstant.exchangeRate;
               }

               int cashChange = 0;
               int[] arrInt = {100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5};

               for (int i = 0; i < arrInt.length; i++) {
                    if (_d >= arrInt[i]) {

                         if (types.equals("usd")) {
                              lastPoint = (_d - arrInt[i]) * JavaConstant.exchangeRate4050;
                         } else {
                              lastPoint = (_d - arrInt[i]) * JavaConstant.exchangeRate;
                         }
                         cashChange = arrInt[i];
                         break;
                    }
               }

               lbChangeKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(lastPoint));

               lbChangeUsd.setLabelName(df.format(cashChange));

          }

          if (types.equals("khr") && txtReceiveUsd.getText().isEmpty()) {

               lbChangeUsd.setLabelName(df.format(0));
               lbChangeKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(_remainingUsd));
          }

     }

     private void funKhr(String receviKhr) {
          String strTotalKhr = lbTotalKhr.getLabelName();
          strTotalKhr = strTotalKhr.replace(",", "");
          double doubleTotalKhr = Double.parseDouble(strTotalKhr);
          String stringReceiveKhr = receviKhr.replace(",", "");
          double doubleReceviceKhr = Double.parseDouble(stringReceiveKhr);

          double result = doubleReceviceKhr - doubleTotalKhr;

          if (result < 0) {

               setValueLabelKhr(result, 0);
          } else if (result > 0) {
               setValueLabelKhr(0, result);
          } else if (result == 0) {
               setValueLabelKhr(0, 0);
          }

          lbChangeUsd.setLabelName(df.format(0));
     }

     private void funUsd(String receviUsd) {
          String stringReceiveUsd = receviUsd.replace(",", "");
          double doubleReceviceUsd = Double.valueOf(stringReceiveUsd);
          double doubleTotalUsd = JavaConstant.getReplace(getTotalUsd());
          double result = doubleReceviceUsd - doubleTotalUsd;

          if (result < 0) {
               setValueLabelUsd(result, 0);
          } else if (result > 0) {
               setValueLabelUsd(0, result);
          } else if (result == 0) {
               setValueLabelUsd(0, 0);
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
          btnEinvoice = new Components.LabelFontBlack();
          buttonChargeAndPrint = new Components.LabelFontBlack();
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
               public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtReceiveUsdKeyPressed(evt);
               }
               public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtReceiveUsdKeyReleased(evt);
               }
               public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtReceiveUsdKeyTyped(evt);
               }
          });

          txtReceiveKhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          txtReceiveKhr.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtReceiveKhrMouseClicked(evt);
               }
          });
          txtReceiveKhr.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtReceiveKhrActionPerformed(evt);
               }
          });
          txtReceiveKhr.addKeyListener(new java.awt.event.KeyAdapter() {
               public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtReceiveKhrKeyPressed(evt);
               }
               public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtReceiveKhrKeyReleased(evt);
               }
               public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtReceiveKhrKeyTyped(evt);
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
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbOneMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbOneMouseExited(evt);
               }
          });

          lbTwo.setBackground(new java.awt.Color(255, 255, 255));
          lbTwo.setFontColor(new java.awt.Color(56, 56, 56));
          lbTwo.setLabelName("2");
          lbTwo.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTwoMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbTwoMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbTwoMouseExited(evt);
               }
          });

          lbThree.setBackground(new java.awt.Color(255, 255, 255));
          lbThree.setFontColor(new java.awt.Color(56, 56, 56));
          lbThree.setLabelName("3");
          lbThree.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbThreeMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbThreeMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbThreeMouseExited(evt);
               }
          });

          lbDelete.setBackground(new java.awt.Color(255, 255, 255));
          lbDelete.setFontColor(new java.awt.Color(56, 56, 56));
          lbDelete.setLabelName("Del");
          lbDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDeleteMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbDeleteMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbDeleteMouseExited(evt);
               }
          });

          lbFour.setBackground(new java.awt.Color(255, 255, 255));
          lbFour.setFontColor(new java.awt.Color(56, 56, 56));
          lbFour.setLabelName("4");
          lbFour.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbFourMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbFourMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbFourMouseExited(evt);
               }
          });

          lbFive.setBackground(new java.awt.Color(255, 255, 255));
          lbFive.setFontColor(new java.awt.Color(56, 56, 56));
          lbFive.setLabelName("5");
          lbFive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbFiveMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbFiveMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbFiveMouseExited(evt);
               }
          });

          lbSix.setBackground(new java.awt.Color(255, 255, 255));
          lbSix.setFontColor(new java.awt.Color(56, 56, 56));
          lbSix.setLabelName("6");
          lbSix.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSixMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbSixMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbSixMouseExited(evt);
               }
          });

          lbDot.setBackground(new java.awt.Color(255, 255, 255));
          lbDot.setFontColor(new java.awt.Color(56, 56, 56));
          lbDot.setLabelName(".");
          lbDot.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDotMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbDotMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbDotMouseExited(evt);
               }
          });

          lbSeven.setBackground(new java.awt.Color(255, 255, 255));
          lbSeven.setFontColor(new java.awt.Color(56, 56, 56));
          lbSeven.setLabelName("7");
          lbSeven.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSevenMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbSevenMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbSevenMouseExited(evt);
               }
          });

          lbEight.setBackground(new java.awt.Color(255, 255, 255));
          lbEight.setFontColor(new java.awt.Color(56, 56, 56));
          lbEight.setLabelName("8");
          lbEight.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbEightMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbEightMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbEightMouseExited(evt);
               }
          });

          lbNine.setBackground(new java.awt.Color(255, 255, 255));
          lbNine.setFontColor(new java.awt.Color(56, 56, 56));
          lbNine.setLabelName("9");
          lbNine.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbNineMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbNineMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbNineMouseExited(evt);
               }
          });

          lbZero.setBackground(new java.awt.Color(255, 255, 255));
          lbZero.setFontColor(new java.awt.Color(56, 56, 56));
          lbZero.setLabelName("0");
          lbZero.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbZeroMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    lbZeroMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    lbZeroMouseExited(evt);
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

          btnEinvoice.setBackground(new java.awt.Color(204, 204, 204));
          btnEinvoice.setFontColor(java.awt.Color.white);
          btnEinvoice.setLabelName("Charge&eInvoice");
          btnEinvoice.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnEinvoiceMouseClicked(evt);
               }
          });

          buttonChargeAndPrint.setBackground(new java.awt.Color(47, 152, 70));
          buttonChargeAndPrint.setFontColor(java.awt.Color.white);
          buttonChargeAndPrint.setLabelName("Charge&Print");
          buttonChargeAndPrint.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonChargeAndPrintMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    buttonChargeAndPrintMouseEntered(evt);
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
                    .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelPaymentLayout.createSequentialGroup()
                              .addGap(19, 19, 19)
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
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelPaymentLayout.createSequentialGroup()
                                                  .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addComponent(lbSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnEinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addGap(12, 12, 12)
                                                  .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                       .addGroup(panelPaymentLayout.createSequentialGroup()
                                                            .addComponent(lbNine, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lbZero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addComponent(buttonChargeAndPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
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
                                                       .addComponent(lbDot, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                       .addComponent(lbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))))
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
                                        .addGap(0, 9, Short.MAX_VALUE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(lbCustomerType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbCustomerPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(cmbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 10, Short.MAX_VALUE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(cmbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(lbCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(lbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addComponent(txtCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                        .addGap(7, 7, 7)))
                              .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelPaymentLayout.createSequentialGroup()
                                        .addComponent(cmbCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46))
                                   .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPaymentLayout.createSequentialGroup()
                                             .addComponent(lbEarning, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
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
                                   .addComponent(lbDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                              .addGap(15, 15, 15)
                              .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(labelFontBlack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnEinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonChargeAndPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGap(16, 16, 16))
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
                    .addComponent(panelPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
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
         if (JavaConstant.isReturn != null) {
              return;
         }
         keyDelete("");
    }//GEN-LAST:event_lbDeleteMouseClicked

     void keyDelete(String valueCheck) {
          if (!txtReceiveUsd.getText().isEmpty() && !txtReceiveKhr.getText().isEmpty()) {
               if (sign.equals("khr")) {
                    String _khr = txtReceiveKhr.getText().replace(",", "");
                    if (valueCheck.isEmpty()) {
                         khr(_khr);
                    } else {
                         khrKey(_khr);
                    }

               } else if (sign.equals("usd")) {
                    String _usd = txtReceiveUsd.getText().replace(",", "");
                    if (valueCheck.isEmpty()) {
                         usd(_usd);
                    } else {
                         usdKey(_usd);
                    }
               }
          } else {
               if (sign.equals("usd")) {
                    if (!txtReceiveUsd.getText().isEmpty()) {
                         if (valueCheck.isEmpty()) {
                              usd(txtReceiveUsd.getText());
                         } else {
                              usdKey(txtReceiveUsd.getText());
                         }
                    } else {
                         usd("0");
                    }
               } else if (sign.equals("khr")) {
                    if (!txtReceiveKhr.getText().isEmpty()) {
                         if (valueCheck.isEmpty()) {
                              khr(txtReceiveKhr.getText());
                         } else {
                              khrKey(txtReceiveKhr.getText());
                         }
                    } else {
                         khr("0");
                    }
               }
          }
     }

     void khr(String value) {

          value = value.substring(0, value.length() - 1);

          txtReceiveKhr.setText("");
          if (value.isEmpty()) {
               setValueLabelKhr(0, 0);
          }
          inputAmount(value);
     }

     void khrKey(String value) {
//          value = value.substring(0, value.length() - 1);

          txtReceiveKhr.setText("");
          if (value.isEmpty()) {
               setValueLabelKhr(0, 0);
          }
          inputAmount(value);
     }

     void usd(String value) {

          value = value.substring(0, value.length() - 1);

          txtReceiveUsd.setText("");
          if (value.isEmpty()) {
               setValueLabelUsd(0, 0);
          }
          inputAmount(value);

     }

     void usdKey(String value) {

//          value = value.substring(0, value.length() - 1);
          txtReceiveUsd.setText("");
          if (value.isEmpty()) {
               setValueLabelUsd(0, 0);
          }
          inputAmount(value);

     }

    private void txtReceiveUsdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiveUsdMouseClicked
         sign = "usd";
    }//GEN-LAST:event_txtReceiveUsdMouseClicked

    private void txtReceiveKhrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiveKhrMouseClicked
         sign = "khr";
    }//GEN-LAST:event_txtReceiveKhrMouseClicked

     void _checkUsd(String usd) {
          for (int i = 0; i < usd.length(); i++) {
               if (usd.charAt(i) == '.' || usd.charAt(i) == ',') {
                    continue;
               }
               if (usd.charAt(i) < '0' || usd.charAt(i) > '9') {
                    String newValue = usd.substring(0, usd.length() - 1) + "";
                    txtReceiveUsd.setText(newValue);
                    return;
               }
          }
     }
    private void txtReceiveUsdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveUsdKeyReleased

         String khr = txtReceiveKhr.getText();
         String usd = txtReceiveUsd.getText();
         _checkUsd(usd);
         khr = khr.replace(",", "");
         usd = usd.replace(",", "");
         if (usd.length() > 0 && khr.length() > 0) {
              checkBothValueTextField(khr, usd);
              return;
         }

         if (khr.length() > 0 || usd.isEmpty()) {
              return;
         }

         if (txtReceiveUsd.getText().length() > 0) {
              catculateUsd();
         } else {
              setValueLabelUsd(0, 0);
         }
    }//GEN-LAST:event_txtReceiveUsdKeyReleased

     void twoReceiveBox(String khr, String usd) {
          double k = Double.parseDouble(khr) / JavaConstant.exchangeRate;
          double u = Double.parseDouble(usd);

          double _usd = JavaConstant.getReplace(lbTotalUsd.getLabelName());
          double _khr = JavaConstant.getReplace(lbTotalKhr.getLabelName());

          double _t = k + u;

          _t = Double.parseDouble(dd.format(_t));

          double _r = _t - _usd;

          if (_t == _usd) {
               setValueLabelKhr(0, 0);
          } else if (_t < _usd) {
               setValueLabelKhr(_r, 0);
               setValueLabelUsd(_r, 0);
          } else if (_t > _usd) {
               setValueLabelKhr(0, _r);
               setValueLabelUsd(0, _r);
          }
     }

     void catculateUsd() {
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
     }

     void _checkKhr(String khr) {
          for (int i = 0; i < khr.length(); i++) {
               if (khr.charAt(i) == '.' || khr.charAt(i) == ',') {
                    continue;
               }
               if (khr.charAt(i) < '0' || khr.charAt(i) > '9') {
                    String newValue = khr.substring(0, khr.length() - 1) + "";
                    txtReceiveKhr.setText(newValue);
                    return;
               }
          }
     }
    private void txtReceiveKhrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveKhrKeyReleased

         String khr = txtReceiveKhr.getText();
         _checkKhr(khr);
         String usd = txtReceiveUsd.getText();
         khr = khr.replace(",", "");
         usd = usd.replace(",", "");
         if (usd.length() > 0 && khr.length() > 0) {
//              twoReceiveBox(khr, usd);
              checkBothValueTextField(khr, usd);
              return;
         }

         if (usd.length() > 0 || khr.isEmpty()) {
              return;
         }

         if (txtReceiveKhr.getText().length() > 0) {
              calculateKhr(lbTotalKhr.getLabelName(), txtReceiveKhr.getText());
         } else {
              setValueLabelKhr(0, 0);
         }

    }//GEN-LAST:event_txtReceiveKhrKeyReleased

     public void setValueTextField(String valueTextField) {
          txtReceiveKhr.setText(valueTextField);
          txtReceiveKhr.setForeground(Color.BLACK);
     }

     void calculateKhr(String totalStr, String receiveStr) {
          double totalKhrValue = JavaConstant.getReplace(totalStr);
          double rKhrValue = JavaConstant.getReplace(receiveStr);
          double resultValueKhr = rKhrValue - totalKhrValue;

          if (resultValueKhr == 0) {
               setValueLabelKhr(resultValueKhr, resultValueKhr);
          } else if (resultValueKhr > 0) {

               setValueLabelKhr(0, resultValueKhr);
          } else if (resultValueKhr < 0) {
               setValueLabelKhr(resultValueKhr, 0);
          }
     }


    private void buttonChargeAndPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChargeAndPrintMouseClicked
         charge();
    }//GEN-LAST:event_buttonChargeAndPrintMouseClicked

     private void charge() {
          JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);

          if (JavaConstant.isReturn == null) {

               boolean isCheckKhr = TextField.onlyDigits(txtReceiveKhr.getText());

               if (!isCheckKhr) {
                    j.setMessage("Invaid receive khr !");
                    j.setVisible(true);
                    return;
               }

               boolean isCheckUsd = TextField.onlyDigits(txtReceiveUsd.getText());

               if (!isCheckUsd) {
                    j.setMessage("Invaid receive usd !");
                    j.setVisible(true);
                    return;
               }

               if (txtReceiveKhr.getText().isEmpty() && txtReceiveUsd.getText().isEmpty()) {
                    j.setMessage("Please pay to charge !");
                    j.setVisible(true);
                    return;
               }

               double valueRemainingUsd = JavaConstant.getReplace(lbRemainingUsd.getLabelName());
               double valueRemainingKhr = JavaConstant.getReplace(lbRemainingKhr.getLabelName());
               if (valueRemainingUsd > 0 || valueRemainingKhr > 0) {
                    j.setMessage(" remainning Usd : " + lbRemainingUsd.getLabelName() + " <br><br> remainning KHR : " + lbRemainingKhr.getLabelName());
                    j.setVisible(true);
                    return;
               }

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
          jsonData.put("posId", JavaConstant.posId);
          jsonData.put("total", total);

          String _khr = txtReceiveKhr.getText().replace(",", "");
          String _usd = txtReceiveUsd.getText().replace(",", "");

          //get dataPay
          HashMap<String, Object> dataPay = new HashMap<>();
          dataPay.put("sourceId", sourceId);
          dataPay.put("customerTypeId", cusTypeId);
          dataPay.put("paymentType", paymentType);
          dataPay.put("receiveKhr", _khr);
          dataPay.put("receiveUsd", _usd);
          dataPay.put("remainingUsd", remainningUsd);
          dataPay.put("remainingKhr", remainningKhr);
          dataPay.put("changeUsd", changeUsd);
          dataPay.put("changeKhr", changeKhr);

          if ((txtCustomerName.getValueTextFieldCenter() == null || txtCustomerName.getValueTextFieldCenter().isEmpty())
               && (txtCustomerId.getValueTextFieldCenter() == null || txtCustomerId.getValueTextFieldCenter().isEmpty())
               && (txtCustomerPhone.getValueTextFieldCenter() == null || txtCustomerPhone.getValueTextFieldCenter().isEmpty())) {

          } else {
               //get customer 
               HashMap<String, Object> customer = new HashMap<>();
               customer.put("cusName", txtCustomerName.getValueTextFieldCenter());
               customer.put("customerId", txtCustomerId.getValueTextFieldCenter());
               customer.put("contact", txtCustomerPhone.getValueTextFieldCenter());
               customer.put("email", txtCustomerEmail.getValueTextFieldCenter());
               customer.put("earning", txtEarning.getValueTextFieldCenter());
//          customer.put("sourceId", sourceId);
//          customer.put("customerTypeId", cusTypeId);

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
               jsonData.put("customer", customer);
          }

//          if(txtCustomerName.getValueTextFieldCenter().isEmpty() && 
//              txtCustomerId.getValueTextFieldCenter().isEmpty() && 
//              txtCustomerPhone.getValueTextFieldCenter().isEmpty()){
//              jsonData.put("customer", "");
//              System.out.println("Helllloooo Hiiiii" +txtCustomerName.getValueTextFieldCenter());
//          }else{
//              jsonData.put("customer", "");
//          }
          String discountType = "";
          //get dataSale 
          ArrayList<ProductSaleModel> dataSale = new ArrayList<>();
          for (int i = 0; i < listCom.length; i++) {
               var obj = ((BoxItem) listCom[i]);
               double price = JavaConstant.getReplace(obj.getLabelPrice());

               double discountDigit = obj.getDiscountDigit();
               double unitPrice = price - (price * discountDigit) / 100;
               double p = JavaConstant.getReplace(df.format(unitPrice));

               double discountVale = obj.getDiscountValue();
               double amount = obj.getQty() * p;
               double a = JavaConstant.getReplace(df.format(amount));

               if (obj.getOldDiscount() > 0) {
                    discountType = "promotion";
               } else {
                    discountType = obj.getDiscountType();
               }

               ProductSaleModel pro = new ProductSaleModel(
                    obj.getProductId(),
                    obj.getQty(),
                    price,
                    a,
                    discountVale,
                    discountType
               );
               dataSale.add(pro);
          }
          jsonData.put("dataSale", dataSale);
          dataPay.put("discountType", discountType);
          dataPay.put("discountValue", discount);
          jsonData.put("dataPay", dataPay);
          jsonData.put("discountCase", discountType);

          Response response = JavaConnection.post(JavaRoute.sale, jsonData);

          try {
               if (response.isSuccessful()) {
                    detailItem.removeAll();
                    detailItem.revalidate();
                    detailItem.repaint();
                    dispose();
                    btnCancel.setBackground(WindowColor.lightGray);
                    buttonHoldOrder.setBackground(WindowColor.lightGray);
                    detailItem.setBackground(WindowColor.slightGreen);
                    subtotalPanel.setLabelSubTitleToZero();
                    btnReturn.setBackground(WindowColor.brown);
                    btnPayment.setBackground(WindowColor.lightGray);
                    detailItem.setBackground(WindowColor.slightGreen);
                    titleOrder.setVisible(false);
                    detailItem.setBorder(null);

                    JavaConstant.productId = 0;

                    // remove hole order
                    if (JavaConstant.holdId != 0) {
                         ArrayList<HoldeModel> holdId = new ArrayList<>();
                         holdId.add(new HoldeModel(JavaConstant.holdId));
                         JSONObject json = new JSONObject();
                         json.put("reasonId", 0); // 0 meaning product was paid
                         json.put("listHoldDetail", holdId);

                         Response responseHold = JavaConnection.delete(JavaRoute.holdOrder, json);

                         if (responseHold.isSuccessful()) {
                              JavaConstant.holdId = 0;
                         }
                    }

                    // ===== print receipt
                    Response responsePrint = JavaConnection.get(JavaRoute.reprintByLast);
                    if (response.isSuccessful()) {
                         try {
                              String myObject = responsePrint.body().string();
                              ObjectMapper objMap = new ObjectMapper();
                              DataSuccessModel d = objMap.readValue(myObject, DataSuccessModel.class);
                              Receipt re = new Receipt(new JFrame(), true);
                              re.setDataSuccess(d);
                              re.revalidate();
                              re.repaint();
//                              re.printReceipt(); // for print with device
                              re.setVisible(true);

//                             FrameReceiptForPrint te = new FrameReceiptForPrint();
//                             te.setDataSuccess(d);
//                             te.revalidate();
//                             te.repaint();
//                             te.printPanel(d);
                         } catch (Exception e) {
                              System.err.println("err while loding = " + e);
                         }
                    }

               } else {
                    JOptionPane.showMessageDialog(this, "Charge Failed!");
               }
          } catch (Exception e) {
               System.err.println("errir = " + e);
          }
     }

     public void returnProduct() throws IOException {
          DecimalFormat df = new DecimalFormat("#.##");
          double totalReturn = 0.0;
          if (!txtReceiveUsd.getText().isEmpty()) {
               totalReturn = Double.valueOf(txtReceiveUsd.getText());
          }

          if (!txtReceiveKhr.getText().isEmpty()) {
               double returnAmountKhr = Double.valueOf(txtReceiveKhr.getText());
               totalReturn = returnAmountKhr / JavaConstant.exchangeRate;
          }

          JSONObject jsonReturnData = new JSONObject();
          jsonReturnData.put("paymentNo", JavaConstant.invoiceNo);
          jsonReturnData.put("reasonId", Integer.valueOf(JavaConstant.reasonId));
          jsonReturnData.put("createBy", JavaConstant.returnerId);
          jsonReturnData.put("returnAmount", df.format(totalReturn));
          jsonReturnData.put("saleId", JavaConstant.saleId);

          //get dataSale 
          ArrayList<ReturnProductModel> dataDetails = new ArrayList<>();
          for (int i = 0; i < listCom.length; i++) {
               var obj = ((BoxItem) listCom[i]);
               double price = JavaConstant.getReplace(obj.getLabelPrice());
               double amount = JavaConstant.getReplace(obj.getLabelAmountUsd());
               double discountDigit = obj.getDiscountDigit();
               double discountValue = obj.getDiscountValue();

               double disAmt = JavaConstant.getReplace(obj.getDiscountAmount());

               ReturnProductModel pro = new ReturnProductModel(
                    obj.getProductId(),
                    obj.getQty(),
                    price,
                    amount,
                    discountValue,
                    obj.getLabelProductName(),
                    obj.getLabelBarcode(),
                    disAmt
               );
               dataDetails.add(pro);
          }
          jsonReturnData.put("dataDetails", dataDetails);

          Response responseReturn = JavaConnection.post(JavaRoute.returnProduct, jsonReturnData);

          if (responseReturn.isSuccessful()) {

//               ============ after return reset value ==================
               JavaConstant.setBackQty(detailItem, panelProduct);
               JavaConstant.resetValueReturn();

               String _data = responseReturn.body().string();
               dispose();
               detailItem.removeAll();
               detailItem.revalidate();
               detailItem.repaint();
               subtotalPanel.setLabelSubTitleToZero();
               btnPayment.setBackground(WindowColor.lightGray);
               btnCancel.setBackground(WindowColor.lightGray);
               buttonHoldOrder.setBackground(WindowColor.lightGray);
               btnReturn.setBackground(WindowColor.brown);
               btnDiscount.setBackground(WindowColor.green);
               detailItem.setBackground(WindowColor.slightGreen);
               detailItem.setBorder(null);
               btnPayment.setButtonName("Payment");
               titleOrder.setVisible(false);

               PrinterReturn print = new PrinterReturn(new JFrame(), true);
               ObjectMapper objMap = new ObjectMapper();
               DataSuccessModel d = objMap.readValue(_data, DataSuccessModel.class);
               print.setDataSuccess(d);
               print.revalidate();
               print.repaint();
//               print.printReceipt(); // print paper with device
               print.setVisible(true);

               // assign JavaConstant.isReturn , reasonId , inovoiceNo to null
               ReturnDialog r = new ReturnDialog(new JFrame(), true);
               r.setResetReturn();

//               ModelReturnData.setReceiveToNull(); // assign value null to receive_usd and receive_khr 
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

     private void txtReceiveKhrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReceiveKhrActionPerformed
          // TODO add your handling code here:
     }//GEN-LAST:event_txtReceiveKhrActionPerformed

     private void buttonChargeAndPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChargeAndPrintMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_buttonChargeAndPrintMouseEntered

    private void lbOneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOneMouseEntered
         lbOne.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbOneMouseEntered

    private void lbOneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOneMouseExited
         lbOne.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbOneMouseExited

    private void lbTwoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTwoMouseEntered
         lbTwo.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbTwoMouseEntered

    private void lbTwoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTwoMouseExited
         lbTwo.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbTwoMouseExited

    private void lbThreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThreeMouseEntered
         lbThree.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbThreeMouseEntered

    private void lbThreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThreeMouseExited
         lbThree.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbThreeMouseExited

    private void lbFourMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFourMouseEntered
         lbFour.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbFourMouseEntered

    private void lbFourMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFourMouseExited
         lbFour.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbFourMouseExited

    private void lbFiveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiveMouseEntered
         lbFive.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbFiveMouseEntered

    private void lbFiveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiveMouseExited
         lbFive.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbFiveMouseExited

    private void lbSixMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSixMouseEntered
         lbSix.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbSixMouseEntered

    private void lbSixMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSixMouseExited
         lbSix.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbSixMouseExited

    private void lbSevenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSevenMouseEntered
         lbSeven.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbSevenMouseEntered

    private void lbSevenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSevenMouseExited
         lbSeven.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbSevenMouseExited

    private void lbEightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEightMouseEntered
         lbEight.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbEightMouseEntered

    private void lbEightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEightMouseExited
         lbEight.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbEightMouseExited

    private void lbNineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNineMouseEntered
         lbNine.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbNineMouseEntered

    private void lbNineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNineMouseExited
         lbNine.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbNineMouseExited

    private void lbDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDeleteMouseEntered
         lbDelete.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbDeleteMouseEntered

    private void lbDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDeleteMouseExited
         lbDelete.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbDeleteMouseExited

    private void lbDotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDotMouseEntered
         lbDot.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbDotMouseEntered

    private void lbDotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDotMouseExited
         lbDot.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbDotMouseExited

    private void lbZeroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbZeroMouseEntered
         lbZero.setBackground(WindowColor.slighWhite);
    }//GEN-LAST:event_lbZeroMouseEntered

    private void lbZeroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbZeroMouseExited
         lbZero.setBackground(WindowColor.white);
    }//GEN-LAST:event_lbZeroMouseExited

    private void btnEinvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEinvoiceMouseClicked

         String custId = txtCustomerId.getValueTextFieldCenter();
         String cusName = txtCustomerName.getValueTextFieldCenter();
         String phone = txtCustomerPhone.getValueTextFieldCenter();

         if ((custId == null || custId.isEmpty())
              && (cusName == null || cusName.isEmpty())
              && (phone == null || phone.isEmpty())) {
              return;
         } else {
              charge();
         }
    }//GEN-LAST:event_btnEinvoiceMouseClicked

     private void txtReceiveUsdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveUsdKeyPressed
          _checkUsd(txtReceiveUsd.getText());
     }//GEN-LAST:event_txtReceiveUsdKeyPressed

     private void txtReceiveUsdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveUsdKeyTyped
          _checkUsd(txtReceiveUsd.getText());
     }//GEN-LAST:event_txtReceiveUsdKeyTyped

     private void txtReceiveKhrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveKhrKeyPressed
          _checkKhr(txtReceiveKhr.getText());
     }//GEN-LAST:event_txtReceiveKhrKeyPressed

     private void txtReceiveKhrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiveKhrKeyTyped
          _checkKhr(txtReceiveKhr.getText());
     }//GEN-LAST:event_txtReceiveKhrKeyTyped

     DecimalFormat kh = new DecimalFormat("#");

     private void setValueLabelUsd(double remaining, double change) {
          String receviUsd = txtReceiveUsd.getText();
          String receviKhr = txtReceiveKhr.getText();

          if (remaining == 0 && change == 0) {
               lbRemainingUsd.setLabelName(df.format(0));
               lbRemainingKhr.setLabelName(dm.format(0));
               lbChangeKhr.setLabelName(dm.format(change));
               lbChangeUsd.setLabelName(df.format(change));
               return;
          }

          if (remaining < 0) {
               String convertDoubleToStr = "" + remaining;
               convertDoubleToStr = convertDoubleToStr.replace("-", "");
               double stringToDouble = Double.parseDouble(convertDoubleToStr);
               lbRemainingUsd.setLabelName(df.format(Double.valueOf(stringToDouble)));
               double _re = stringToDouble * JavaConstant.exchangeRate;

               lbRemainingKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(_re));
               lbChangeKhr.setLabelName(dm.format(change));
               lbChangeUsd.setLabelName(df.format(change));

          } else {

               if (change < 5) {
                    change = change * JavaConstant.exchangeRate4050;

                    lbChangeKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(change));

                    lbRemainingKhr.setLabelName(dm.format(0));
                    lbRemainingUsd.setLabelName(df.format(0));
                    lbChangeUsd.setLabelName(df.format(0));
               } else {

                    double lastPoint = 0;
                    int cashChange = 0;
                    int[] arrInt = {100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5};

                    for (int i = 0; i < arrInt.length; i++) {
                         if (change >= arrInt[i]) {
                              lastPoint = change - arrInt[i];
                              cashChange = arrInt[i];
                              break;
                         }
                    }
                    lbChangeUsd.setLabelName(df.format(cashChange));
                    lastPoint = lastPoint * JavaConstant.exchangeRate4050;
                    if (lastPoint == 0) {
                         lbChangeKhr.setLabelName(dm.format(lastPoint));
                    } else {
                         lbChangeKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(lastPoint));
                    }

                    lbRemainingKhr.setLabelName(dm.format(0));
                    lbRemainingUsd.setLabelName(df.format(0));
               }
          }

     }

     private void setValueLabelKhr(double remaining, double change) {
          String receviUsd = txtReceiveUsd.getText();
          String receviKhr = txtReceiveKhr.getText();
          String convertDoubleToStr = "" + remaining;
          convertDoubleToStr = convertDoubleToStr.replace("-", "");
          double _d = Double.parseDouble(convertDoubleToStr);
          double _remainingUsd = _d / JavaConstant.exchangeRate;

          if (remaining == 0 && change == 0) {
               lbRemainingKhr.setLabelName(dm.format(0));
               lbRemainingUsd.setLabelName(df.format(0));
               lbChangeKhr.setLabelName(dm.format(0));
               lbChangeUsd.setLabelName(df.format(0));
               return;
          }

          if (remaining < 0) {

               convertDoubleToStr = JavaRoundUpKhr.setRoundNumber(_d);
               if (txtReceiveKhr.getText().isEmpty()) {
                    lbRemainingKhr.setLabelName(lbTotalKhr.getLabelName());
               } else {
                    lbRemainingKhr.setLabelName(convertDoubleToStr);
               }
               lbRemainingUsd.setLabelName(df.format(_remainingUsd));
               lbChangeKhr.setLabelName(dm.format(0));
               lbChangeUsd.setLabelName(df.format(0));

          } else {

               if (sign.equals("khr")) {
                    lbRemainingUsd.setLabelName(df.format(0));
               } else if (sign.equals("usd")) {
                    lbRemainingUsd.setLabelName(df.format(_d));
               }
               lbRemainingKhr.setLabelName(dm.format(0));
               String khrValue = JavaRoundUpKhr.setRoundNumber(change);
               lbChangeKhr.setLabelName(khrValue);
               lbChangeUsd.setLabelName(df.format(0));
          }

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
          lbTotalKhr.setLabelName(JavaRoundUpKhr.setRoundNumber(_totalKh));

          if (JavaConstant.isReturn != null || JavaConstant.returnByBarcode != null) {
               txtReceiveKhr.setText("" + ModelReturnData.receive_khr);
               txtReceiveUsd.setText("" + ModelReturnData.receive_usd);
               lbChangeKhr.setLabelName(dm.format(Double.parseDouble(ModelReturnData.change_khr)));
               lbChangeUsd.setLabelName(df.format(ModelReturnData.change_usd));

               txtReceiveKhr.setFocusable(false);
               txtReceiveUsd.setFocusable(false);
               txtCustomerId.requestFocusInWindow();
          }

     }

     public Button getBtnDiscount() {
          return btnDiscount;
     }

     public void setBtnDiscount(Button btnDiscount) {
          this.btnDiscount = btnDiscount;
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

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

     public Button getBtnReturn() {
          return btnReturn;
     }

     public void setBtnReturn(Button btnReturn) {
          this.btnReturn = btnReturn;
     }

     public JLabel getTitleOrder() {
          return titleOrder;
     }

     public void setTitleOrder(JLabel titleOrder) {
          this.titleOrder = titleOrder;
     }


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Components.LabelFontBlack btnEinvoice;
     private Components.LabelFontBlack buttonChargeAndPrint;
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
