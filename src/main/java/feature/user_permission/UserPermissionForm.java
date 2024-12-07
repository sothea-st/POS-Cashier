package feature.user_permission;

import Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserPermissionForm extends javax.swing.JDialog {

     private String roleId = "-1";
     private boolean isCheck = false;

     public UserPermissionForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          //call init
          init();

          // call cmdRole()
          cmdRole();
     }

     public void init() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE); // not allow close dialog
          setResizable(false); // no allow resize dialog
          setTitle("User Permission");
          panel.setBackground(WindowColor.mediumGreen); // set background panel

          JavaConstant.setScroll(jScrollPane1);
     }

     // Action Select Role
     private void cmdRole() {
          JavaComboBoxSelection.addComboBox(role,
               JavaRoute.role,
               "role_name",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    roleId = id;
               }
          };
          role.initEvent(event);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          btnCheck15 = new javax.swing.JLabel();
          permissionName15 = new javax.swing.JLabel();
          permissionName16 = new javax.swing.JLabel();
          btnCheck16 = new javax.swing.JLabel();
          btnCheck17 = new javax.swing.JLabel();
          permissionName17 = new javax.swing.JLabel();
          btnCheck18 = new javax.swing.JLabel();
          permissionName18 = new javax.swing.JLabel();
          panel = new javax.swing.JPanel();
          role = new FormComponent.combobox.JavaCombobox();
          jScrollPane1 = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          lbStock = new javax.swing.JLabel();
          permissionName = new javax.swing.JLabel();
          lbProduct = new javax.swing.JLabel();
          permissionName1 = new javax.swing.JLabel();
          btnCheck2 = new javax.swing.JLabel();
          permissionName2 = new javax.swing.JLabel();
          btnCheck3 = new javax.swing.JLabel();
          permissionName3 = new javax.swing.JLabel();
          permissionName4 = new javax.swing.JLabel();
          productCreate = new javax.swing.JLabel();
          productUpdate = new javax.swing.JLabel();
          permissionName5 = new javax.swing.JLabel();
          productView = new javax.swing.JLabel();
          permissionName6 = new javax.swing.JLabel();
          productDelete = new javax.swing.JLabel();
          permissionName7 = new javax.swing.JLabel();
          permissionName8 = new javax.swing.JLabel();
          btnCheck8 = new javax.swing.JLabel();
          btnCheck9 = new javax.swing.JLabel();
          permissionName9 = new javax.swing.JLabel();
          btnCheck10 = new javax.swing.JLabel();
          permissionName10 = new javax.swing.JLabel();
          btnCheck11 = new javax.swing.JLabel();
          permissionName11 = new javax.swing.JLabel();
          permissionName12 = new javax.swing.JLabel();
          btnCheck12 = new javax.swing.JLabel();
          btnCheck13 = new javax.swing.JLabel();
          permissionName13 = new javax.swing.JLabel();
          btnCheck14 = new javax.swing.JLabel();
          permissionName14 = new javax.swing.JLabel();
          btnCheck19 = new javax.swing.JLabel();
          permissionName19 = new javax.swing.JLabel();
          btnCheck20 = new javax.swing.JLabel();
          permissionName20 = new javax.swing.JLabel();
          btnCheck21 = new javax.swing.JLabel();
          permissionName21 = new javax.swing.JLabel();
          btnCheck22 = new javax.swing.JLabel();
          permissionName22 = new javax.swing.JLabel();
          btnCheck23 = new javax.swing.JLabel();
          permissionName23 = new javax.swing.JLabel();
          btnCheck24 = new javax.swing.JLabel();
          permissionName24 = new javax.swing.JLabel();
          btnCheck25 = new javax.swing.JLabel();
          permissionName25 = new javax.swing.JLabel();
          btnCheck26 = new javax.swing.JLabel();
          permissionName26 = new javax.swing.JLabel();
          btnCheck27 = new javax.swing.JLabel();
          permissionName27 = new javax.swing.JLabel();
          btnCheck28 = new javax.swing.JLabel();
          permissionName28 = new javax.swing.JLabel();
          btnCheck29 = new javax.swing.JLabel();
          permissionName29 = new javax.swing.JLabel();
          btnCheck30 = new javax.swing.JLabel();
          permissionName30 = new javax.swing.JLabel();
          btnCheck31 = new javax.swing.JLabel();
          permissionName31 = new javax.swing.JLabel();
          btnCheck32 = new javax.swing.JLabel();
          permissionName32 = new javax.swing.JLabel();
          btnCheck33 = new javax.swing.JLabel();
          permissionName33 = new javax.swing.JLabel();
          btnCheck34 = new javax.swing.JLabel();
          permissionName34 = new javax.swing.JLabel();
          btnCheck35 = new javax.swing.JLabel();
          permissionName35 = new javax.swing.JLabel();
          btnCheck36 = new javax.swing.JLabel();
          permissionName36 = new javax.swing.JLabel();
          btnCheck37 = new javax.swing.JLabel();
          permissionName37 = new javax.swing.JLabel();
          btnCheck38 = new javax.swing.JLabel();
          permissionName38 = new javax.swing.JLabel();
          btnCheck39 = new javax.swing.JLabel();
          permissionName39 = new javax.swing.JLabel();
          btnCheck40 = new javax.swing.JLabel();
          permissionName40 = new javax.swing.JLabel();
          btnCheck41 = new javax.swing.JLabel();
          permissionName41 = new javax.swing.JLabel();
          btnCheck42 = new javax.swing.JLabel();
          permissionName42 = new javax.swing.JLabel();
          permissionName43 = new javax.swing.JLabel();
          btnCheck43 = new javax.swing.JLabel();
          btnCheck44 = new javax.swing.JLabel();
          btnCheck45 = new javax.swing.JLabel();
          permissionName44 = new javax.swing.JLabel();
          permissionName45 = new javax.swing.JLabel();
          permissionName46 = new javax.swing.JLabel();
          btnCheck46 = new javax.swing.JLabel();
          btnCheck47 = new javax.swing.JLabel();
          permissionName47 = new javax.swing.JLabel();
          permissionName48 = new javax.swing.JLabel();
          btnCheck48 = new javax.swing.JLabel();
          permissionName49 = new javax.swing.JLabel();
          btnCheck49 = new javax.swing.JLabel();
          permissionName50 = new javax.swing.JLabel();
          permissionName51 = new javax.swing.JLabel();
          btnCheck50 = new javax.swing.JLabel();
          btnCheck51 = new javax.swing.JLabel();
          permissionName52 = new javax.swing.JLabel();
          btnCheck52 = new javax.swing.JLabel();
          permissionName53 = new javax.swing.JLabel();
          permissionName54 = new javax.swing.JLabel();
          permissionName55 = new javax.swing.JLabel();
          btnCheck53 = new javax.swing.JLabel();
          permissionName56 = new javax.swing.JLabel();
          btnCheck54 = new javax.swing.JLabel();
          btnCheck55 = new javax.swing.JLabel();
          btnCheck56 = new javax.swing.JLabel();
          btnCheck57 = new javax.swing.JLabel();
          btnCheck58 = new javax.swing.JLabel();
          permissionName57 = new javax.swing.JLabel();
          btnCheck59 = new javax.swing.JLabel();
          permissionName58 = new javax.swing.JLabel();
          permissionName59 = new javax.swing.JLabel();
          btnCheck60 = new javax.swing.JLabel();
          permissionName60 = new javax.swing.JLabel();
          btnCheck61 = new javax.swing.JLabel();
          permissionName61 = new javax.swing.JLabel();
          btnCheck62 = new javax.swing.JLabel();
          permissionName62 = new javax.swing.JLabel();
          permissionName63 = new javax.swing.JLabel();
          btnCheck63 = new javax.swing.JLabel();
          btnCheck64 = new javax.swing.JLabel();
          btnCheck65 = new javax.swing.JLabel();
          permissionName64 = new javax.swing.JLabel();
          btnCheck66 = new javax.swing.JLabel();
          permissionName65 = new javax.swing.JLabel();
          permissionName66 = new javax.swing.JLabel();
          permissionName67 = new javax.swing.JLabel();
          permissionName68 = new javax.swing.JLabel();
          btnCheck67 = new javax.swing.JLabel();
          permissionName69 = new javax.swing.JLabel();
          permissionName70 = new javax.swing.JLabel();
          btnCheck68 = new javax.swing.JLabel();
          btnCheck69 = new javax.swing.JLabel();
          btnCheck70 = new javax.swing.JLabel();
          btnCheck71 = new javax.swing.JLabel();
          permissionName71 = new javax.swing.JLabel();
          btnCheck72 = new javax.swing.JLabel();
          permissionName72 = new javax.swing.JLabel();
          btnCheck73 = new javax.swing.JLabel();
          permissionName73 = new javax.swing.JLabel();
          btnCheck74 = new javax.swing.JLabel();
          permissionName74 = new javax.swing.JLabel();
          btnCheck75 = new javax.swing.JLabel();
          permissionName75 = new javax.swing.JLabel();
          btnCheck76 = new javax.swing.JLabel();
          permissionName76 = new javax.swing.JLabel();
          btnCheck77 = new javax.swing.JLabel();
          btnCheck78 = new javax.swing.JLabel();
          permissionName77 = new javax.swing.JLabel();
          btnCheck79 = new javax.swing.JLabel();
          permissionName78 = new javax.swing.JLabel();
          btnCheck80 = new javax.swing.JLabel();
          permissionName79 = new javax.swing.JLabel();
          permissionName80 = new javax.swing.JLabel();
          btnCheck81 = new javax.swing.JLabel();
          permissionName81 = new javax.swing.JLabel();
          btnCheck82 = new javax.swing.JLabel();
          permissionName82 = new javax.swing.JLabel();
          permissionName83 = new javax.swing.JLabel();
          btnCheck83 = new javax.swing.JLabel();
          btnCheck84 = new javax.swing.JLabel();
          permissionName84 = new javax.swing.JLabel();
          permissionName85 = new javax.swing.JLabel();
          btnCheck85 = new javax.swing.JLabel();
          permissionName86 = new javax.swing.JLabel();
          btnCheck86 = new javax.swing.JLabel();
          btnCheck87 = new javax.swing.JLabel();
          btnCheck88 = new javax.swing.JLabel();
          permissionName87 = new javax.swing.JLabel();
          permissionName88 = new javax.swing.JLabel();
          permissionName89 = new javax.swing.JLabel();
          btnCheck89 = new javax.swing.JLabel();
          permissionName90 = new javax.swing.JLabel();
          btnCheck90 = new javax.swing.JLabel();
          permissionName91 = new javax.swing.JLabel();
          btnCheck91 = new javax.swing.JLabel();
          btnCheck92 = new javax.swing.JLabel();
          permissionName92 = new javax.swing.JLabel();
          btnCheck93 = new javax.swing.JLabel();
          permissionName93 = new javax.swing.JLabel();
          btnCheck94 = new javax.swing.JLabel();
          permissionName94 = new javax.swing.JLabel();
          btnCheck95 = new javax.swing.JLabel();
          permissionName95 = new javax.swing.JLabel();
          btnCheck96 = new javax.swing.JLabel();
          permissionName96 = new javax.swing.JLabel();
          permissionName97 = new javax.swing.JLabel();
          btnCheck97 = new javax.swing.JLabel();
          btnCheck98 = new javax.swing.JLabel();
          permissionName98 = new javax.swing.JLabel();
          btnCheck99 = new javax.swing.JLabel();
          permissionName99 = new javax.swing.JLabel();
          btnCheck100 = new javax.swing.JLabel();
          permissionName100 = new javax.swing.JLabel();
          btnCheck101 = new javax.swing.JLabel();
          permissionName101 = new javax.swing.JLabel();
          btnCheck102 = new javax.swing.JLabel();
          permissionName102 = new javax.swing.JLabel();
          btnCheck103 = new javax.swing.JLabel();
          permissionName103 = new javax.swing.JLabel();
          btnCheck104 = new javax.swing.JLabel();
          permissionName104 = new javax.swing.JLabel();
          btnCheck105 = new javax.swing.JLabel();
          permissionName105 = new javax.swing.JLabel();
          btnCheck106 = new javax.swing.JLabel();
          permissionName106 = new javax.swing.JLabel();
          btnCheck107 = new javax.swing.JLabel();
          permissionName107 = new javax.swing.JLabel();
          btnCheck108 = new javax.swing.JLabel();
          permissionName108 = new javax.swing.JLabel();
          btnCheck109 = new javax.swing.JLabel();
          permissionName109 = new javax.swing.JLabel();
          btnCheck110 = new javax.swing.JLabel();
          permissionName110 = new javax.swing.JLabel();
          btnCheck111 = new javax.swing.JLabel();
          permissionName111 = new javax.swing.JLabel();
          btnCheck112 = new javax.swing.JLabel();
          permissionName112 = new javax.swing.JLabel();
          btnCheck113 = new javax.swing.JLabel();
          permissionName113 = new javax.swing.JLabel();
          btnCheck114 = new javax.swing.JLabel();
          permissionName114 = new javax.swing.JLabel();
          btnCheck115 = new javax.swing.JLabel();
          permissionName115 = new javax.swing.JLabel();
          btnCheck116 = new javax.swing.JLabel();
          permissionName116 = new javax.swing.JLabel();
          btnCheck117 = new javax.swing.JLabel();
          permissionName117 = new javax.swing.JLabel();
          btnCheck118 = new javax.swing.JLabel();
          permissionName118 = new javax.swing.JLabel();
          btnCheck119 = new javax.swing.JLabel();
          permissionName119 = new javax.swing.JLabel();
          btnCheck120 = new javax.swing.JLabel();
          permissionName120 = new javax.swing.JLabel();
          btnCheck121 = new javax.swing.JLabel();
          permissionName121 = new javax.swing.JLabel();
          btnCheck122 = new javax.swing.JLabel();
          permissionName122 = new javax.swing.JLabel();
          btnCheck123 = new javax.swing.JLabel();
          permissionName123 = new javax.swing.JLabel();
          btnCheck124 = new javax.swing.JLabel();
          permissionName124 = new javax.swing.JLabel();
          btnCheck125 = new javax.swing.JLabel();
          permissionName125 = new javax.swing.JLabel();
          btnCheck126 = new javax.swing.JLabel();
          permissionName126 = new javax.swing.JLabel();
          btnCheck127 = new javax.swing.JLabel();
          permissionName127 = new javax.swing.JLabel();
          btnCheck128 = new javax.swing.JLabel();
          permissionName128 = new javax.swing.JLabel();
          btnCheck129 = new javax.swing.JLabel();
          permissionName129 = new javax.swing.JLabel();
          btnCheck130 = new javax.swing.JLabel();
          permissionName130 = new javax.swing.JLabel();
          btnCheck131 = new javax.swing.JLabel();
          permissionName131 = new javax.swing.JLabel();
          btnCheck132 = new javax.swing.JLabel();
          permissionName132 = new javax.swing.JLabel();
          btnCheck133 = new javax.swing.JLabel();
          permissionName133 = new javax.swing.JLabel();
          btnCheck134 = new javax.swing.JLabel();
          btnCheck135 = new javax.swing.JLabel();
          permissionName134 = new javax.swing.JLabel();
          permissionName135 = new javax.swing.JLabel();
          btnCheck136 = new javax.swing.JLabel();
          permissionName136 = new javax.swing.JLabel();
          permissionName137 = new javax.swing.JLabel();
          permissionName138 = new javax.swing.JLabel();
          btnCheck137 = new javax.swing.JLabel();
          btnCheck138 = new javax.swing.JLabel();
          btnCancel = new Button.Button();
          btnCheck139 = new javax.swing.JLabel();
          permissionName139 = new javax.swing.JLabel();

          btnCheck15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName15.setText("Create");
          permissionName15.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName16.setText("Update");
          permissionName16.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName17.setText("View");
          permissionName17.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName18.setText("Delete");
          permissionName18.setPreferredSize(new java.awt.Dimension(37, 20));

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          role.setLabelName("Role *");
          role.setName(""); // NOI18N

          panelData.setPreferredSize(new java.awt.Dimension(1100, 992));

          lbStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStock.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStockMouseClicked(evt);
               }
          });

          permissionName.setText("Stock");
          permissionName.setPreferredSize(new java.awt.Dimension(37, 20));

          lbProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbProduct.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbProductMouseClicked(evt);
               }
          });

          permissionName1.setText("Product");
          permissionName1.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName2.setText("Purchase Order");
          permissionName2.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName3.setText("Purchase Receive");
          permissionName3.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName4.setText("Create");
          permissionName4.setPreferredSize(new java.awt.Dimension(37, 20));

          productCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          productCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    productCreateMouseClicked(evt);
               }
          });

          productUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          productUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    productUpdateMouseClicked(evt);
               }
          });

          permissionName5.setText("Update");
          permissionName5.setPreferredSize(new java.awt.Dimension(37, 20));

          productView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          productView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    productViewMouseClicked(evt);
               }
          });

          permissionName6.setText("View");
          permissionName6.setPreferredSize(new java.awt.Dimension(37, 20));

          productDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName7.setText("Delete");
          permissionName7.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName8.setText("Purchase Request");
          permissionName8.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName9.setText("Purchase Check");
          permissionName9.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName10.setText("Purchase Approval");
          permissionName10.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName11.setText("Create");
          permissionName11.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName12.setText("Update");
          permissionName12.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName13.setText("View");
          permissionName13.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName14.setText("Delete");
          permissionName14.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName19.setText("Create");
          permissionName19.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName20.setText("Update");
          permissionName20.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName21.setText("View");
          permissionName21.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName22.setText("Delete");
          permissionName22.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName23.setText("Create");
          permissionName23.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName24.setText("Update");
          permissionName24.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName25.setText("View");
          permissionName25.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName26.setText("Delete");
          permissionName26.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName27.setText("Create");
          permissionName27.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName28.setText("Update");
          permissionName28.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName29.setText("View");
          permissionName29.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName30.setText("Delete");
          permissionName30.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName31.setText("Settings");
          permissionName31.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName32.setText("Division");
          permissionName32.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName33.setText("Create");
          permissionName33.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName34.setText("Update");
          permissionName34.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName35.setText("View");
          permissionName35.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName36.setText("Delete");
          permissionName36.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName37.setText("Department");
          permissionName37.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName38.setText("Create");
          permissionName38.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName39.setText("Update");
          permissionName39.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName40.setText("View");
          permissionName40.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName41.setText("Delete");
          permissionName41.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName42.setText("Delete");
          permissionName42.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName43.setText("Category");
          permissionName43.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName44.setText("Update");
          permissionName44.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName45.setText("View");
          permissionName45.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName46.setText("Create");
          permissionName46.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName47.setText("Sub Category");
          permissionName47.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName48.setText("Update");
          permissionName48.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName49.setText("Delete");
          permissionName49.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName50.setText("Create");
          permissionName50.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName51.setText("View");
          permissionName51.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName52.setText("Delete");
          permissionName52.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName53.setText("Create");
          permissionName53.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName54.setText("View");
          permissionName54.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName55.setText("Brand");
          permissionName55.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName56.setText("Update");
          permissionName56.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName57.setText("Create");
          permissionName57.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName58.setText("View");
          permissionName58.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName59.setText("Update");
          permissionName59.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName60.setText("Delete");
          permissionName60.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName61.setText("Vendor");
          permissionName61.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName62.setText("View");
          permissionName62.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName63.setText("Update");
          permissionName63.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName64.setText("Attribute");
          permissionName64.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName65.setText("Delete");
          permissionName65.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName66.setText("Create");
          permissionName66.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName67.setText("Update");
          permissionName67.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName68.setText("Delete");
          permissionName68.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName69.setText("Create");
          permissionName69.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName70.setText("View");
          permissionName70.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName71.setText("Uom");
          permissionName71.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName72.setText("Country");
          permissionName72.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName73.setText("Create");
          permissionName73.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName74.setText("Update");
          permissionName74.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName75.setText("View");
          permissionName75.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName76.setText("Delete");
          permissionName76.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName77.setText("Delete");
          permissionName77.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName78.setText("Create");
          permissionName78.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName79.setText("Update");
          permissionName79.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName80.setText("View");
          permissionName80.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName81.setText("TAX");
          permissionName81.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName82.setText("Update");
          permissionName82.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName83.setText("Delete");
          permissionName83.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName84.setText("Status");
          permissionName84.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName85.setText("View");
          permissionName85.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName86.setText("Create");
          permissionName86.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName87.setText("Warehouse");
          permissionName87.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName88.setText("Create");
          permissionName88.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName89.setText("Update");
          permissionName89.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName90.setText("View");
          permissionName90.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName91.setText("Delete");
          permissionName91.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName92.setText("Range");
          permissionName92.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName93.setText("Create");
          permissionName93.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName94.setText("Update");
          permissionName94.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName95.setText("View");
          permissionName95.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName96.setText("Delete");
          permissionName96.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName97.setText("View");
          permissionName97.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName98.setText("Create");
          permissionName98.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName99.setText("Slot");
          permissionName99.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName100.setText("Update");
          permissionName100.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName101.setText("Delete");
          permissionName101.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName102.setText("Reporting");
          permissionName102.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName103.setText("Reporting Sale");
          permissionName103.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName104.setText("Create");
          permissionName104.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName105.setText("Update");
          permissionName105.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName106.setText("View");
          permissionName106.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName107.setText("Delete");
          permissionName107.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName108.setText("Reporting Purchase Order ");
          permissionName108.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName109.setText("Create");
          permissionName109.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName110.setText("Update");
          permissionName110.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName111.setText("View");
          permissionName111.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName112.setText("Delete");
          permissionName112.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName113.setText("Reporting Purchase Receive");
          permissionName113.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName114.setText("Create");
          permissionName114.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName115.setText("Update");
          permissionName115.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName116.setText("View");
          permissionName116.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName117.setText("Delete");
          permissionName117.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName118.setText("Reporting Inventory");
          permissionName118.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName119.setText("Create");
          permissionName119.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName120.setText("Update");
          permissionName120.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName121.setText("View");
          permissionName121.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName122.setText("Delete");
          permissionName122.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName123.setText("Staff");
          permissionName123.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName124.setText("Staff Information");
          permissionName124.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName125.setText("Create");
          permissionName125.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName126.setText("Update");
          permissionName126.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName127.setText("View");
          permissionName127.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName128.setText("Delete");
          permissionName128.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName129.setText("User Login");
          permissionName129.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName130.setText("Create");
          permissionName130.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName131.setText("Update");
          permissionName131.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck132.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName132.setText("View");
          permissionName132.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName133.setText("Delete");
          permissionName133.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck134.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName134.setText("User Permission");
          permissionName134.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName135.setText("Create");
          permissionName135.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName136.setText("Update");
          permissionName136.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName137.setText("View");
          permissionName137.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName138.setText("Delete");
          permissionName138.setPreferredSize(new java.awt.Dimension(37, 20));

          btnCheck137.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          btnCheck138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelDataLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addComponent(lbStock)
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(permissionName, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck3)
                                                  .addGap(12, 12, 12)
                                                  .addComponent(permissionName3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(lbProduct)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck2)
                                                  .addGap(12, 12, 12)
                                                  .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addGroup(panelDataLayout.createSequentialGroup()
                                                            .addComponent(btnCheck8)
                                                            .addGap(12, 12, 12)
                                                            .addComponent(permissionName8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                       .addComponent(permissionName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                       .addGroup(panelDataLayout.createSequentialGroup()
                                                            .addComponent(btnCheck9)
                                                            .addGap(12, 12, 12)
                                                            .addComponent(permissionName9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                       .addGroup(panelDataLayout.createSequentialGroup()
                                                            .addComponent(btnCheck10)
                                                            .addGap(12, 12, 12)
                                                            .addComponent(permissionName10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(productCreate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(productUpdate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(productView)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(productDelete)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck11)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck12)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck13)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck14)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck19)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck20)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck21)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck22)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName22, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck23)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck24)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck25)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck26)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(btnCheck27)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck28)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck29)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btnCheck30)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addComponent(btnCheck31)
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName32, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName33, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName34, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName36, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(permissionName31, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName37, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName38, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck40)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName40, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName41, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName43, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName46, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck43)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName44, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName45, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck44)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName42, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName47, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck50)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName50, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName51, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck51)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName49, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck54)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName55, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName53, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName56, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck52)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName54, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName52, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck59)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName61, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName57, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName59, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck61)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName58, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck60)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName60, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck66)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName64, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck62)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName66, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck64)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName63, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck65)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName62, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck63)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName65, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck68)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName71, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck69)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName69, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck67)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName67, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck71)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName70, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck70)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName68, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck72)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName72, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck73)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName73, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck74)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName74, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck75)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName75, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck76)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName76, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck78)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName81, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck81)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName78, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck80)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName79, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck79)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName80, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck77)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName77, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck86)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName84, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck83)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName86, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck84)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName82, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck85)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName85, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck82)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName83, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck88)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName87, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck89)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName88, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck91)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName89, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck87)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName90, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck90)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName91, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck92)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName92, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck93)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName93, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck94)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName94, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck95)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName95, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck96)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName96, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck98)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName99, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck100)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName98, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck99)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName100, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck97)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName97, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck101)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName101, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addComponent(btnCheck102)
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck103)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName103, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck104)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName104, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck105)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName105, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck106)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName106, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck107)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName107, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(permissionName102, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck108)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName108, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck109)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName109, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck110)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName110, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck111)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName111, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck112)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName112, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck113)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName113, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck114)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName114, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck115)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName115, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck116)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName116, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck117)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName117, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck118)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName118, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck119)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName119, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck120)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName120, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck121)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName121, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck122)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName122, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addComponent(btnCheck123)
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck124)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName124, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck125)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName125, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck126)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName126, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck127)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName127, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck128)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName128, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(permissionName123, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck129)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName129, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck130)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName130, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck131)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck132)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName132, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck133)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName133, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(btnCheck136)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName134, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck137)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName135, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck135)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName136, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck138)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName137, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCheck134)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName138, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(415, Short.MAX_VALUE))
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelDataLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStock))
                    .addGap(12, 12, 12)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbProduct)
                         .addComponent(permissionName4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(productCreate)
                         .addComponent(permissionName5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(productUpdate)
                         .addComponent(permissionName6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(productView)
                         .addComponent(permissionName7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(productDelete))
                    .addGap(12, 12, 12)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(permissionName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck2))
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                       .addComponent(permissionName8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck8)
                                                       .addComponent(permissionName11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck11)
                                                       .addComponent(permissionName12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck12)
                                                       .addComponent(permissionName13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck13)
                                                       .addComponent(permissionName14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck14))
                                                  .addGap(12, 12, 12)
                                                  .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                       .addComponent(permissionName9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(btnCheck9)))
                                             .addComponent(permissionName19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(btnCheck19)
                                             .addComponent(permissionName20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(btnCheck20)
                                             .addComponent(permissionName21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(btnCheck21)
                                             .addComponent(permissionName22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(btnCheck22))
                                        .addGap(12, 12, 12)
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addComponent(permissionName10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(btnCheck10)))
                                   .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck23)
                                   .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck24)
                                   .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck25)
                                   .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck26))
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(permissionName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(btnCheck3)))
                         .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck27)
                         .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck28)
                         .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck29)
                         .addComponent(permissionName30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck30))
                    .addGap(12, 12, 12)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck31))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck32)
                         .addComponent(permissionName33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck33)
                         .addComponent(permissionName34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck34)
                         .addComponent(permissionName35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck35)
                         .addComponent(permissionName36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck36))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck37)
                         .addComponent(permissionName38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck38)
                         .addComponent(permissionName39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck39)
                         .addComponent(permissionName40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck40)
                         .addComponent(permissionName41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck41))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck45)
                         .addComponent(permissionName46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck42)
                         .addComponent(permissionName44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck43)
                         .addComponent(permissionName45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck46)
                         .addComponent(permissionName42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck44))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck47)
                         .addComponent(permissionName50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck50)
                         .addComponent(permissionName48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck49)
                         .addComponent(permissionName51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck48)
                         .addComponent(permissionName49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck51))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck54)
                         .addComponent(permissionName53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck53)
                         .addComponent(permissionName56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck55)
                         .addComponent(permissionName54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck52)
                         .addComponent(permissionName52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck56))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck59)
                         .addComponent(permissionName57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck58)
                         .addComponent(permissionName59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck57)
                         .addComponent(permissionName58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck61)
                         .addComponent(permissionName60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck60))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck66)
                         .addComponent(permissionName66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck62)
                         .addComponent(permissionName63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck64)
                         .addComponent(permissionName62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck65)
                         .addComponent(permissionName65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck63))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck68)
                         .addComponent(permissionName69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck69)
                         .addComponent(permissionName67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck67)
                         .addComponent(permissionName70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck71)
                         .addComponent(permissionName68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck70))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck72)
                         .addComponent(permissionName73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck73)
                         .addComponent(permissionName74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck74)
                         .addComponent(permissionName75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck75)
                         .addComponent(permissionName76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck76))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck78)
                         .addComponent(permissionName78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck81)
                         .addComponent(permissionName79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck80)
                         .addComponent(permissionName80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck79)
                         .addComponent(permissionName77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck77))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck86)
                         .addComponent(permissionName86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck83)
                         .addComponent(permissionName82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck84)
                         .addComponent(permissionName85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck85)
                         .addComponent(permissionName83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck82))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck88)
                         .addComponent(permissionName88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck89)
                         .addComponent(permissionName89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck91)
                         .addComponent(permissionName90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck87)
                         .addComponent(permissionName91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck90))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck92)
                         .addComponent(permissionName93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck93)
                         .addComponent(permissionName94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck94)
                         .addComponent(permissionName95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck95)
                         .addComponent(permissionName96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck96))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck98)
                         .addComponent(permissionName98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck100)
                         .addComponent(permissionName100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck99)
                         .addComponent(permissionName97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck97)
                         .addComponent(permissionName101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck101))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck102))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck103)
                         .addComponent(permissionName104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck104)
                         .addComponent(permissionName105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck105)
                         .addComponent(permissionName106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck106)
                         .addComponent(permissionName107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck107))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck108)
                         .addComponent(permissionName109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck109)
                         .addComponent(permissionName110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck110)
                         .addComponent(permissionName111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck111)
                         .addComponent(permissionName112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck112))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck113)
                         .addComponent(permissionName114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck114)
                         .addComponent(permissionName115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck115)
                         .addComponent(permissionName116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck116)
                         .addComponent(permissionName117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck117))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck118)
                         .addComponent(permissionName119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck119)
                         .addComponent(permissionName120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck120)
                         .addComponent(permissionName121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck121)
                         .addComponent(permissionName122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck122))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck123))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck124)
                         .addComponent(permissionName125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck125)
                         .addComponent(permissionName126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck126)
                         .addComponent(permissionName127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck127)
                         .addComponent(permissionName128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck128))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck129)
                         .addComponent(permissionName130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck130)
                         .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck131)
                         .addComponent(permissionName132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck132)
                         .addComponent(permissionName133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck133))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck136)
                         .addComponent(permissionName135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck137)
                         .addComponent(permissionName136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck135)
                         .addComponent(permissionName137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck138)
                         .addComponent(permissionName138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck134))
                    .addContainerGap(24, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(panelData);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          btnCheck139.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          permissionName139.setText("All");
          permissionName139.setPreferredSize(new java.awt.Dimension(37, 20));

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(panelLayout.createSequentialGroup()
                                   .addComponent(btnCheck139)
                                   .addGap(12, 12, 12)
                                   .addComponent(permissionName139, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck139))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void lbStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStockMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label);
     }//GEN-LAST:event_lbStockMouseClicked

     private void lbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label);
     }//GEN-LAST:event_lbProductMouseClicked

     private void productCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label);
     }//GEN-LAST:event_productCreateMouseClicked

     private void productUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label);
     }//GEN-LAST:event_productUpdateMouseClicked

     private void productViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label);
     }//GEN-LAST:event_productViewMouseClicked

     private void setAction(JLabel label) {

          if (roleId.equals("-1")) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("Pls select Role first !");
               j.setVisible(true);
               return;
          }

          // Toggle the stockId variable
          isCheck = !isCheck;

          // Update the icon based on the state of stockId
          if (!isCheck) {
               label.setIcon(new ImageIcon(getClass().getResource("/icon/check.png")));
          } else {
               label.setIcon(new ImageIcon(getClass().getResource("/icon/checked.png")));
          }
     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    UserPermissionForm dialog = new UserPermissionForm(new javax.swing.JFrame(), true);
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
     private Button.Button btnCancel;
     private javax.swing.JLabel btnCheck10;
     private javax.swing.JLabel btnCheck100;
     private javax.swing.JLabel btnCheck101;
     private javax.swing.JLabel btnCheck102;
     private javax.swing.JLabel btnCheck103;
     private javax.swing.JLabel btnCheck104;
     private javax.swing.JLabel btnCheck105;
     private javax.swing.JLabel btnCheck106;
     private javax.swing.JLabel btnCheck107;
     private javax.swing.JLabel btnCheck108;
     private javax.swing.JLabel btnCheck109;
     private javax.swing.JLabel btnCheck11;
     private javax.swing.JLabel btnCheck110;
     private javax.swing.JLabel btnCheck111;
     private javax.swing.JLabel btnCheck112;
     private javax.swing.JLabel btnCheck113;
     private javax.swing.JLabel btnCheck114;
     private javax.swing.JLabel btnCheck115;
     private javax.swing.JLabel btnCheck116;
     private javax.swing.JLabel btnCheck117;
     private javax.swing.JLabel btnCheck118;
     private javax.swing.JLabel btnCheck119;
     private javax.swing.JLabel btnCheck12;
     private javax.swing.JLabel btnCheck120;
     private javax.swing.JLabel btnCheck121;
     private javax.swing.JLabel btnCheck122;
     private javax.swing.JLabel btnCheck123;
     private javax.swing.JLabel btnCheck124;
     private javax.swing.JLabel btnCheck125;
     private javax.swing.JLabel btnCheck126;
     private javax.swing.JLabel btnCheck127;
     private javax.swing.JLabel btnCheck128;
     private javax.swing.JLabel btnCheck129;
     private javax.swing.JLabel btnCheck13;
     private javax.swing.JLabel btnCheck130;
     private javax.swing.JLabel btnCheck131;
     private javax.swing.JLabel btnCheck132;
     private javax.swing.JLabel btnCheck133;
     private javax.swing.JLabel btnCheck134;
     private javax.swing.JLabel btnCheck135;
     private javax.swing.JLabel btnCheck136;
     private javax.swing.JLabel btnCheck137;
     private javax.swing.JLabel btnCheck138;
     private javax.swing.JLabel btnCheck139;
     private javax.swing.JLabel btnCheck14;
     private javax.swing.JLabel btnCheck15;
     private javax.swing.JLabel btnCheck16;
     private javax.swing.JLabel btnCheck17;
     private javax.swing.JLabel btnCheck18;
     private javax.swing.JLabel btnCheck19;
     private javax.swing.JLabel btnCheck2;
     private javax.swing.JLabel btnCheck20;
     private javax.swing.JLabel btnCheck21;
     private javax.swing.JLabel btnCheck22;
     private javax.swing.JLabel btnCheck23;
     private javax.swing.JLabel btnCheck24;
     private javax.swing.JLabel btnCheck25;
     private javax.swing.JLabel btnCheck26;
     private javax.swing.JLabel btnCheck27;
     private javax.swing.JLabel btnCheck28;
     private javax.swing.JLabel btnCheck29;
     private javax.swing.JLabel btnCheck3;
     private javax.swing.JLabel btnCheck30;
     private javax.swing.JLabel btnCheck31;
     private javax.swing.JLabel btnCheck32;
     private javax.swing.JLabel btnCheck33;
     private javax.swing.JLabel btnCheck34;
     private javax.swing.JLabel btnCheck35;
     private javax.swing.JLabel btnCheck36;
     private javax.swing.JLabel btnCheck37;
     private javax.swing.JLabel btnCheck38;
     private javax.swing.JLabel btnCheck39;
     private javax.swing.JLabel btnCheck40;
     private javax.swing.JLabel btnCheck41;
     private javax.swing.JLabel btnCheck42;
     private javax.swing.JLabel btnCheck43;
     private javax.swing.JLabel btnCheck44;
     private javax.swing.JLabel btnCheck45;
     private javax.swing.JLabel btnCheck46;
     private javax.swing.JLabel btnCheck47;
     private javax.swing.JLabel btnCheck48;
     private javax.swing.JLabel btnCheck49;
     private javax.swing.JLabel btnCheck50;
     private javax.swing.JLabel btnCheck51;
     private javax.swing.JLabel btnCheck52;
     private javax.swing.JLabel btnCheck53;
     private javax.swing.JLabel btnCheck54;
     private javax.swing.JLabel btnCheck55;
     private javax.swing.JLabel btnCheck56;
     private javax.swing.JLabel btnCheck57;
     private javax.swing.JLabel btnCheck58;
     private javax.swing.JLabel btnCheck59;
     private javax.swing.JLabel btnCheck60;
     private javax.swing.JLabel btnCheck61;
     private javax.swing.JLabel btnCheck62;
     private javax.swing.JLabel btnCheck63;
     private javax.swing.JLabel btnCheck64;
     private javax.swing.JLabel btnCheck65;
     private javax.swing.JLabel btnCheck66;
     private javax.swing.JLabel btnCheck67;
     private javax.swing.JLabel btnCheck68;
     private javax.swing.JLabel btnCheck69;
     private javax.swing.JLabel btnCheck70;
     private javax.swing.JLabel btnCheck71;
     private javax.swing.JLabel btnCheck72;
     private javax.swing.JLabel btnCheck73;
     private javax.swing.JLabel btnCheck74;
     private javax.swing.JLabel btnCheck75;
     private javax.swing.JLabel btnCheck76;
     private javax.swing.JLabel btnCheck77;
     private javax.swing.JLabel btnCheck78;
     private javax.swing.JLabel btnCheck79;
     private javax.swing.JLabel btnCheck8;
     private javax.swing.JLabel btnCheck80;
     private javax.swing.JLabel btnCheck81;
     private javax.swing.JLabel btnCheck82;
     private javax.swing.JLabel btnCheck83;
     private javax.swing.JLabel btnCheck84;
     private javax.swing.JLabel btnCheck85;
     private javax.swing.JLabel btnCheck86;
     private javax.swing.JLabel btnCheck87;
     private javax.swing.JLabel btnCheck88;
     private javax.swing.JLabel btnCheck89;
     private javax.swing.JLabel btnCheck9;
     private javax.swing.JLabel btnCheck90;
     private javax.swing.JLabel btnCheck91;
     private javax.swing.JLabel btnCheck92;
     private javax.swing.JLabel btnCheck93;
     private javax.swing.JLabel btnCheck94;
     private javax.swing.JLabel btnCheck95;
     private javax.swing.JLabel btnCheck96;
     private javax.swing.JLabel btnCheck97;
     private javax.swing.JLabel btnCheck98;
     private javax.swing.JLabel btnCheck99;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JLabel lbProduct;
     private javax.swing.JLabel lbStock;
     private javax.swing.JPanel panel;
     private javax.swing.JPanel panelData;
     private javax.swing.JLabel permissionName;
     private javax.swing.JLabel permissionName1;
     private javax.swing.JLabel permissionName10;
     private javax.swing.JLabel permissionName100;
     private javax.swing.JLabel permissionName101;
     private javax.swing.JLabel permissionName102;
     private javax.swing.JLabel permissionName103;
     private javax.swing.JLabel permissionName104;
     private javax.swing.JLabel permissionName105;
     private javax.swing.JLabel permissionName106;
     private javax.swing.JLabel permissionName107;
     private javax.swing.JLabel permissionName108;
     private javax.swing.JLabel permissionName109;
     private javax.swing.JLabel permissionName11;
     private javax.swing.JLabel permissionName110;
     private javax.swing.JLabel permissionName111;
     private javax.swing.JLabel permissionName112;
     private javax.swing.JLabel permissionName113;
     private javax.swing.JLabel permissionName114;
     private javax.swing.JLabel permissionName115;
     private javax.swing.JLabel permissionName116;
     private javax.swing.JLabel permissionName117;
     private javax.swing.JLabel permissionName118;
     private javax.swing.JLabel permissionName119;
     private javax.swing.JLabel permissionName12;
     private javax.swing.JLabel permissionName120;
     private javax.swing.JLabel permissionName121;
     private javax.swing.JLabel permissionName122;
     private javax.swing.JLabel permissionName123;
     private javax.swing.JLabel permissionName124;
     private javax.swing.JLabel permissionName125;
     private javax.swing.JLabel permissionName126;
     private javax.swing.JLabel permissionName127;
     private javax.swing.JLabel permissionName128;
     private javax.swing.JLabel permissionName129;
     private javax.swing.JLabel permissionName13;
     private javax.swing.JLabel permissionName130;
     private javax.swing.JLabel permissionName131;
     private javax.swing.JLabel permissionName132;
     private javax.swing.JLabel permissionName133;
     private javax.swing.JLabel permissionName134;
     private javax.swing.JLabel permissionName135;
     private javax.swing.JLabel permissionName136;
     private javax.swing.JLabel permissionName137;
     private javax.swing.JLabel permissionName138;
     private javax.swing.JLabel permissionName139;
     private javax.swing.JLabel permissionName14;
     private javax.swing.JLabel permissionName15;
     private javax.swing.JLabel permissionName16;
     private javax.swing.JLabel permissionName17;
     private javax.swing.JLabel permissionName18;
     private javax.swing.JLabel permissionName19;
     private javax.swing.JLabel permissionName2;
     private javax.swing.JLabel permissionName20;
     private javax.swing.JLabel permissionName21;
     private javax.swing.JLabel permissionName22;
     private javax.swing.JLabel permissionName23;
     private javax.swing.JLabel permissionName24;
     private javax.swing.JLabel permissionName25;
     private javax.swing.JLabel permissionName26;
     private javax.swing.JLabel permissionName27;
     private javax.swing.JLabel permissionName28;
     private javax.swing.JLabel permissionName29;
     private javax.swing.JLabel permissionName3;
     private javax.swing.JLabel permissionName30;
     private javax.swing.JLabel permissionName31;
     private javax.swing.JLabel permissionName32;
     private javax.swing.JLabel permissionName33;
     private javax.swing.JLabel permissionName34;
     private javax.swing.JLabel permissionName35;
     private javax.swing.JLabel permissionName36;
     private javax.swing.JLabel permissionName37;
     private javax.swing.JLabel permissionName38;
     private javax.swing.JLabel permissionName39;
     private javax.swing.JLabel permissionName4;
     private javax.swing.JLabel permissionName40;
     private javax.swing.JLabel permissionName41;
     private javax.swing.JLabel permissionName42;
     private javax.swing.JLabel permissionName43;
     private javax.swing.JLabel permissionName44;
     private javax.swing.JLabel permissionName45;
     private javax.swing.JLabel permissionName46;
     private javax.swing.JLabel permissionName47;
     private javax.swing.JLabel permissionName48;
     private javax.swing.JLabel permissionName49;
     private javax.swing.JLabel permissionName5;
     private javax.swing.JLabel permissionName50;
     private javax.swing.JLabel permissionName51;
     private javax.swing.JLabel permissionName52;
     private javax.swing.JLabel permissionName53;
     private javax.swing.JLabel permissionName54;
     private javax.swing.JLabel permissionName55;
     private javax.swing.JLabel permissionName56;
     private javax.swing.JLabel permissionName57;
     private javax.swing.JLabel permissionName58;
     private javax.swing.JLabel permissionName59;
     private javax.swing.JLabel permissionName6;
     private javax.swing.JLabel permissionName60;
     private javax.swing.JLabel permissionName61;
     private javax.swing.JLabel permissionName62;
     private javax.swing.JLabel permissionName63;
     private javax.swing.JLabel permissionName64;
     private javax.swing.JLabel permissionName65;
     private javax.swing.JLabel permissionName66;
     private javax.swing.JLabel permissionName67;
     private javax.swing.JLabel permissionName68;
     private javax.swing.JLabel permissionName69;
     private javax.swing.JLabel permissionName7;
     private javax.swing.JLabel permissionName70;
     private javax.swing.JLabel permissionName71;
     private javax.swing.JLabel permissionName72;
     private javax.swing.JLabel permissionName73;
     private javax.swing.JLabel permissionName74;
     private javax.swing.JLabel permissionName75;
     private javax.swing.JLabel permissionName76;
     private javax.swing.JLabel permissionName77;
     private javax.swing.JLabel permissionName78;
     private javax.swing.JLabel permissionName79;
     private javax.swing.JLabel permissionName8;
     private javax.swing.JLabel permissionName80;
     private javax.swing.JLabel permissionName81;
     private javax.swing.JLabel permissionName82;
     private javax.swing.JLabel permissionName83;
     private javax.swing.JLabel permissionName84;
     private javax.swing.JLabel permissionName85;
     private javax.swing.JLabel permissionName86;
     private javax.swing.JLabel permissionName87;
     private javax.swing.JLabel permissionName88;
     private javax.swing.JLabel permissionName89;
     private javax.swing.JLabel permissionName9;
     private javax.swing.JLabel permissionName90;
     private javax.swing.JLabel permissionName91;
     private javax.swing.JLabel permissionName92;
     private javax.swing.JLabel permissionName93;
     private javax.swing.JLabel permissionName94;
     private javax.swing.JLabel permissionName95;
     private javax.swing.JLabel permissionName96;
     private javax.swing.JLabel permissionName97;
     private javax.swing.JLabel permissionName98;
     private javax.swing.JLabel permissionName99;
     private javax.swing.JLabel productCreate;
     private javax.swing.JLabel productDelete;
     private javax.swing.JLabel productUpdate;
     private javax.swing.JLabel productView;
     private FormComponent.combobox.JavaCombobox role;
     // End of variables declaration//GEN-END:variables
}
