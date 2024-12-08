package feature.user_permission;

import Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import feature.user_permission.model.RoleHasPermission;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import okhttp3.Response;
import org.json.JSONObject;

public class UserPermissionForm extends javax.swing.JDialog {

    private String roleId = "-1";
    private boolean isStock = false;
    //========= product =============
    private boolean isProduct = false;
    private boolean isProductCreate = false;
    private boolean isProductUpdate = false;
    private boolean isProductView = false;
    private boolean isProductDelete = false;
    //========= end product =============

    //========= purchase order =============
    private boolean isPO = false;
    private boolean isPORequest = false;
    private boolean isPOCreate = false;
    private boolean isPOUpdate = false;
    private boolean isPOView = false;
    private boolean isPODelete = false;
    //========= end purchase order =============

    //========= purchase order =============
    private boolean isPOCheck = false;
    private boolean isPOCheckCreate = false;
    private boolean isPOCheckUpdate = false;
    private boolean isPOCheckView = false;
    private boolean isPOCheckDelete = false;
    //========= end purchase order =============

    //========= purchase approval =============
    private boolean isPOApproval = false;
    private boolean isPOApprovalCreate = false;
    private boolean isPOApprovalUpdate = false;
    private boolean isPOApprovalView = false;
    private boolean isPOApprovalDelete = false;
    //========= end purchase approval =============

    //========= purchase receive =============
    private boolean isPOReceive = false;
    private boolean isPOReceiveCreate = false;
    private boolean isPOReceiveUpdate = false;
    private boolean isPOReceiveView = false;
    private boolean isPOReceiveDelete = false;
    //========= end purchase receive =============

    //========= reportting =============
    private boolean isReport = false;
    private boolean isReportingSale = false;
    private boolean isReportPO = false;
    private boolean isReportingPurchaseReceive = false;
    private boolean isReportingInventory = false;
    //=========  end reportting =============

    private boolean isSfaff = false;
    //========= staff =============
    private boolean isSfaffInfo = false;
    private boolean isSfaffInfoCreate = false;
    private boolean isSfaffInfoView = false;
    private boolean isSfaffInfoUpdate = false;
    private boolean isSfaffInfoDelete = false;
    //========= end staff =============

    private boolean isUserLogin = false;
    private boolean isUserLoginUpdate = false;
    private boolean isUserPermission = false;

    private List<RoleHasPermission> listRoles = new ArrayList<>();

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
                
                updateIcon(lbStock,true);
            }
        };
        role.initEvent(event);
    }
    
     private void updateIcon(JLabel label, boolean state) {
        if (state) { // checked box
            label.setIcon(new ImageIcon(getClass().getResource("/icon/checked.png")));
        } else { // uncheck box
            label.setIcon(new ImageIcon(getClass().getResource("/icon/check.png")));
        }
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
        lbPO = new javax.swing.JLabel();
        permissionName2 = new javax.swing.JLabel();
        POReceive = new javax.swing.JLabel();
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
        lbPORequest = new javax.swing.JLabel();
        lbPOCheck = new javax.swing.JLabel();
        permissionName9 = new javax.swing.JLabel();
        poApproval = new javax.swing.JLabel();
        permissionName10 = new javax.swing.JLabel();
        lbPOCreate = new javax.swing.JLabel();
        permissionName11 = new javax.swing.JLabel();
        permissionName12 = new javax.swing.JLabel();
        lbPOUpdate = new javax.swing.JLabel();
        lbPOView = new javax.swing.JLabel();
        permissionName13 = new javax.swing.JLabel();
        lbPODelete = new javax.swing.JLabel();
        permissionName14 = new javax.swing.JLabel();
        lbPOCheckCreate = new javax.swing.JLabel();
        permissionName19 = new javax.swing.JLabel();
        lbPOCheckUpdate = new javax.swing.JLabel();
        permissionName20 = new javax.swing.JLabel();
        lbPOCheckView = new javax.swing.JLabel();
        permissionName21 = new javax.swing.JLabel();
        lbPOCheckDelete = new javax.swing.JLabel();
        permissionName22 = new javax.swing.JLabel();
        poApprovalCreate = new javax.swing.JLabel();
        permissionName23 = new javax.swing.JLabel();
        poApprovalUpdate = new javax.swing.JLabel();
        permissionName24 = new javax.swing.JLabel();
        poApprovalView = new javax.swing.JLabel();
        permissionName25 = new javax.swing.JLabel();
        poApprovalDelete = new javax.swing.JLabel();
        permissionName26 = new javax.swing.JLabel();
        POReceiveCreate = new javax.swing.JLabel();
        permissionName27 = new javax.swing.JLabel();
        POReceiveUpdate = new javax.swing.JLabel();
        permissionName28 = new javax.swing.JLabel();
        POReceiveView = new javax.swing.JLabel();
        permissionName29 = new javax.swing.JLabel();
        POReceiveDelete = new javax.swing.JLabel();
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
        lbReporting = new javax.swing.JLabel();
        permissionName102 = new javax.swing.JLabel();
        lbReportingSale = new javax.swing.JLabel();
        permissionName103 = new javax.swing.JLabel();
        lbReportingPO = new javax.swing.JLabel();
        permissionName108 = new javax.swing.JLabel();
        lbReportingPurchaseReceive = new javax.swing.JLabel();
        permissionName113 = new javax.swing.JLabel();
        lbReportingInventory = new javax.swing.JLabel();
        permissionName118 = new javax.swing.JLabel();
        lbStaff = new javax.swing.JLabel();
        permissionName123 = new javax.swing.JLabel();
        lbStaffInfo = new javax.swing.JLabel();
        permissionName124 = new javax.swing.JLabel();
        lbStaffCreate = new javax.swing.JLabel();
        permissionName125 = new javax.swing.JLabel();
        lbstaffUpdate = new javax.swing.JLabel();
        permissionName126 = new javax.swing.JLabel();
        lbstaffView = new javax.swing.JLabel();
        permissionName127 = new javax.swing.JLabel();
        lbstaffDelete = new javax.swing.JLabel();
        permissionName128 = new javax.swing.JLabel();
        btnCheck129 = new javax.swing.JLabel();
        permissionName129 = new javax.swing.JLabel();
        btnCheck131 = new javax.swing.JLabel();
        permissionName131 = new javax.swing.JLabel();
        permissionName134 = new javax.swing.JLabel();
        btnCheck136 = new javax.swing.JLabel();
        btnCancel = new Button.Button();
        btnCheck139 = new javax.swing.JLabel();
        permissionName139 = new javax.swing.JLabel();
        buttonSave = new ButtonPackage.ButtonSave();

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

        lbPO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOMouseClicked(evt);
            }
        });

        permissionName2.setText("Purchase Order");
        permissionName2.setPreferredSize(new java.awt.Dimension(37, 20));

        POReceive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        POReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POReceiveMouseClicked(evt);
            }
        });

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
        productDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productDeleteMouseClicked(evt);
            }
        });

        permissionName7.setText("Delete");
        permissionName7.setPreferredSize(new java.awt.Dimension(37, 20));

        permissionName8.setText("Purchase Request");
        permissionName8.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPORequest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPORequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPORequestMouseClicked(evt);
            }
        });

        lbPOCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCheckMouseClicked(evt);
            }
        });

        permissionName9.setText("Purchase Check");
        permissionName9.setPreferredSize(new java.awt.Dimension(37, 20));

        poApproval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        poApproval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poApprovalMouseClicked(evt);
            }
        });

        permissionName10.setText("Purchase Approval");
        permissionName10.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCreateMouseClicked(evt);
            }
        });

        permissionName11.setText("Create");
        permissionName11.setPreferredSize(new java.awt.Dimension(37, 20));

        permissionName12.setText("Update");
        permissionName12.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOUpdateMouseClicked(evt);
            }
        });

        lbPOView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOViewMouseClicked(evt);
            }
        });

        permissionName13.setText("View");
        permissionName13.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPODelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPODelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPODeleteMouseClicked(evt);
            }
        });

        permissionName14.setText("Delete");
        permissionName14.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOCheckCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCheckCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCheckCreateMouseClicked(evt);
            }
        });

        permissionName19.setText("Create");
        permissionName19.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOCheckUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCheckUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCheckUpdateMouseClicked(evt);
            }
        });

        permissionName20.setText("Update");
        permissionName20.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOCheckView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCheckView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCheckViewMouseClicked(evt);
            }
        });

        permissionName21.setText("View");
        permissionName21.setPreferredSize(new java.awt.Dimension(37, 20));

        lbPOCheckDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbPOCheckDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPOCheckDeleteMouseClicked(evt);
            }
        });

        permissionName22.setText("Delete");
        permissionName22.setPreferredSize(new java.awt.Dimension(37, 20));

        poApprovalCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        poApprovalCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poApprovalCreateMouseClicked(evt);
            }
        });

        permissionName23.setText("Create");
        permissionName23.setPreferredSize(new java.awt.Dimension(37, 20));

        poApprovalUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        poApprovalUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poApprovalUpdateMouseClicked(evt);
            }
        });

        permissionName24.setText("Update");
        permissionName24.setPreferredSize(new java.awt.Dimension(37, 20));

        poApprovalView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        poApprovalView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poApprovalViewMouseClicked(evt);
            }
        });

        permissionName25.setText("View");
        permissionName25.setPreferredSize(new java.awt.Dimension(37, 20));

        poApprovalDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        poApprovalDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poApprovalDeleteMouseClicked(evt);
            }
        });

        permissionName26.setText("Delete");
        permissionName26.setPreferredSize(new java.awt.Dimension(37, 20));

        POReceiveCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        POReceiveCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POReceiveCreateMouseClicked(evt);
            }
        });

        permissionName27.setText("Create");
        permissionName27.setPreferredSize(new java.awt.Dimension(37, 20));

        POReceiveUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        POReceiveUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POReceiveUpdateMouseClicked(evt);
            }
        });

        permissionName28.setText("Update");
        permissionName28.setPreferredSize(new java.awt.Dimension(37, 20));

        POReceiveView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        POReceiveView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POReceiveViewMouseClicked(evt);
            }
        });

        permissionName29.setText("View");
        permissionName29.setPreferredSize(new java.awt.Dimension(37, 20));

        POReceiveDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        POReceiveDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POReceiveDeleteMouseClicked(evt);
            }
        });

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

        lbReporting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbReporting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportingMouseClicked(evt);
            }
        });

        permissionName102.setText("Reporting");
        permissionName102.setPreferredSize(new java.awt.Dimension(37, 20));

        lbReportingSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbReportingSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportingSaleMouseClicked(evt);
            }
        });

        permissionName103.setText("Reporting Sale");
        permissionName103.setPreferredSize(new java.awt.Dimension(37, 20));

        lbReportingPO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbReportingPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportingPOMouseClicked(evt);
            }
        });

        permissionName108.setText("Reporting Purchase Order ");
        permissionName108.setPreferredSize(new java.awt.Dimension(37, 20));

        lbReportingPurchaseReceive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbReportingPurchaseReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportingPurchaseReceiveMouseClicked(evt);
            }
        });

        permissionName113.setText("Reporting Purchase Receive");
        permissionName113.setPreferredSize(new java.awt.Dimension(37, 20));

        lbReportingInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbReportingInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportingInventoryMouseClicked(evt);
            }
        });

        permissionName118.setText("Reporting Inventory");
        permissionName118.setPreferredSize(new java.awt.Dimension(37, 20));

        lbStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStaffMouseClicked(evt);
            }
        });

        permissionName123.setText("Staff");
        permissionName123.setPreferredSize(new java.awt.Dimension(37, 20));

        lbStaffInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbStaffInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStaffInfoMouseClicked(evt);
            }
        });

        permissionName124.setText("Staff Information");
        permissionName124.setPreferredSize(new java.awt.Dimension(37, 20));

        lbStaffCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbStaffCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStaffCreateMouseClicked(evt);
            }
        });

        permissionName125.setText("Create");
        permissionName125.setPreferredSize(new java.awt.Dimension(37, 20));

        lbstaffUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbstaffUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbstaffUpdateMouseClicked(evt);
            }
        });

        permissionName126.setText("Update");
        permissionName126.setPreferredSize(new java.awt.Dimension(37, 20));

        lbstaffView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbstaffView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbstaffViewMouseClicked(evt);
            }
        });

        permissionName127.setText("View");
        permissionName127.setPreferredSize(new java.awt.Dimension(37, 20));

        lbstaffDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        lbstaffDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbstaffDeleteMouseClicked(evt);
            }
        });

        permissionName128.setText("Delete");
        permissionName128.setPreferredSize(new java.awt.Dimension(37, 20));

        btnCheck129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        btnCheck129.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheck129MouseClicked(evt);
            }
        });

        permissionName129.setText("User Login");
        permissionName129.setPreferredSize(new java.awt.Dimension(37, 20));

        btnCheck131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        btnCheck131.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheck131MouseClicked(evt);
            }
        });

        permissionName131.setText("Update");
        permissionName131.setPreferredSize(new java.awt.Dimension(37, 20));

        permissionName134.setText("User Permission");
        permissionName134.setPreferredSize(new java.awt.Dimension(37, 20));

        btnCheck136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        btnCheck136.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheck136MouseClicked(evt);
            }
        });

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
                                        .addComponent(POReceive)
                                        .addGap(12, 12, 12)
                                        .addComponent(permissionName3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbProduct)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDataLayout.createSequentialGroup()
                                        .addComponent(lbPO)
                                        .addGap(12, 12, 12)
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelDataLayout.createSequentialGroup()
                                                .addComponent(lbPORequest)
                                                .addGap(12, 12, 12)
                                                .addComponent(permissionName8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(permissionName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panelDataLayout.createSequentialGroup()
                                                .addComponent(lbPOCheck)
                                                .addGap(12, 12, 12)
                                                .addComponent(permissionName9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(panelDataLayout.createSequentialGroup()
                                                .addComponent(poApproval)
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
                                        .addComponent(lbPOCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPOUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPOView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPODelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbPOCheckCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPOCheckUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPOCheckView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPOCheckDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName22, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(poApprovalCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(poApprovalUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(poApprovalView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(poApprovalDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(POReceiveCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(POReceiveUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(POReceiveView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(POReceiveDelete)
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
                        .addComponent(lbReporting)
                        .addGap(12, 12, 12)
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(lbReportingSale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName103, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(permissionName102, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(lbReportingPO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName108, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(lbReportingPurchaseReceive)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName113, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(lbReportingInventory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName118, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelDataLayout.createSequentialGroup()
                        .addComponent(lbStaff)
                        .addGap(12, 12, 12)
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(lbStaffInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName124, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbStaffCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName125, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbstaffUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName126, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbstaffView)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName127, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbstaffDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName128, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(permissionName123, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(btnCheck129)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName129, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(btnCheck131)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addComponent(btnCheck136)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(permissionName134, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(lbPO))
                        .addGap(12, 12, 12)
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelDataLayout.createSequentialGroup()
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(permissionName8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPORequest)
                                            .addComponent(permissionName11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPOCreate)
                                            .addComponent(permissionName12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPOUpdate)
                                            .addComponent(permissionName13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPOView)
                                            .addComponent(permissionName14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPODelete))
                                        .addGap(12, 12, 12)
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(permissionName9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbPOCheck)))
                                    .addComponent(permissionName19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPOCheckCreate)
                                    .addComponent(permissionName20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPOCheckUpdate)
                                    .addComponent(permissionName21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPOCheckView)
                                    .addComponent(permissionName22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPOCheckDelete))
                                .addGap(12, 12, 12)
                                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(permissionName10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(poApproval)))
                            .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poApprovalCreate)
                            .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poApprovalUpdate)
                            .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poApprovalView)
                            .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poApprovalDelete))
                        .addGap(12, 12, 12)
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(permissionName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(POReceive)))
                    .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(POReceiveCreate)
                    .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(POReceiveUpdate)
                    .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(POReceiveView)
                    .addComponent(permissionName30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(POReceiveDelete))
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
                    .addComponent(lbReporting))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReportingSale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReportingPO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReportingPurchaseReceive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReportingInventory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStaff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStaffInfo)
                    .addComponent(permissionName125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStaffCreate)
                    .addComponent(permissionName126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbstaffUpdate)
                    .addComponent(permissionName127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbstaffView)
                    .addComponent(permissionName128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbstaffDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck129)
                    .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck131))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck136))
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

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btnCheck139)
                        .addGap(12, 12, 12)
                        .addComponent(permissionName139, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(permissionName139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck139))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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
         setAction(label, "stock");
     }//GEN-LAST:event_lbStockMouseClicked

     private void lbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "product");
     }//GEN-LAST:event_lbProductMouseClicked

     private void productCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productCreateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "productCreate");
     }//GEN-LAST:event_productCreateMouseClicked

     private void productUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productUpdateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "productUpdate");
     }//GEN-LAST:event_productUpdateMouseClicked

     private void productViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productViewMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "productView");
     }//GEN-LAST:event_productViewMouseClicked

    private void productDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productDeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "productDelete");
    }//GEN-LAST:event_productDeleteMouseClicked

    private void lbPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "PO");
    }//GEN-LAST:event_lbPOMouseClicked

    private void lbPORequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPORequestMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "PORequest");
    }//GEN-LAST:event_lbPORequestMouseClicked

    private void lbPOCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCreateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCreate");
    }//GEN-LAST:event_lbPOCreateMouseClicked

    private void lbPOUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOUpdateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POUpdate");
    }//GEN-LAST:event_lbPOUpdateMouseClicked

    private void lbPOViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOViewMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POView");
    }//GEN-LAST:event_lbPOViewMouseClicked

    private void lbPODeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPODeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "PODelete");
    }//GEN-LAST:event_lbPODeleteMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
        for (RoleHasPermission data : listRoles) {
            System.err.println("RoleId : " + data.getRoleId() + " PermissionId : " + data.getPermissionId() + " isVisible : " + data.getIsVisible() + " "
                    + " isCreate : " + data.getIsCreate() + " isView : " + data.getIsView() + " isUpdata : " + data.getIsUpdate() + " isDelete : " + data.getIsDelete());
        }

        JSONObject json = new JSONObject();
        json.put("roleHasPermissionRequestList", listRoles);

        Response response = JavaConnection.post(JavaRoute.roleHasPermissions, json);

        try {

            if (response.isSuccessful()) {
                dispose();
            }

        } catch (Exception e) {
            System.err.println("error : " + e);
        }


    }//GEN-LAST:event_buttonSaveMouseClicked

    private void lbPOCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCheckMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCheck");
    }//GEN-LAST:event_lbPOCheckMouseClicked

    private void lbPOCheckCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCheckCreateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCheckCreate");
    }//GEN-LAST:event_lbPOCheckCreateMouseClicked

    private void lbPOCheckUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCheckUpdateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCheckUpdate");
    }//GEN-LAST:event_lbPOCheckUpdateMouseClicked

    private void lbPOCheckViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCheckViewMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCheckView");
    }//GEN-LAST:event_lbPOCheckViewMouseClicked

    private void lbPOCheckDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOCheckDeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POCheckDelete");
    }//GEN-LAST:event_lbPOCheckDeleteMouseClicked

    private void poApprovalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poApprovalMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POApproval");
    }//GEN-LAST:event_poApprovalMouseClicked

    private void poApprovalCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poApprovalCreateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POApprovalCreate");
    }//GEN-LAST:event_poApprovalCreateMouseClicked

    private void poApprovalUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poApprovalUpdateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POApprovalUpdate");
    }//GEN-LAST:event_poApprovalUpdateMouseClicked

    private void poApprovalViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poApprovalViewMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POApprovalView");
    }//GEN-LAST:event_poApprovalViewMouseClicked

    private void poApprovalDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poApprovalDeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POApprovalDelete");
    }//GEN-LAST:event_poApprovalDeleteMouseClicked

    private void POReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POReceiveMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POReceive");
    }//GEN-LAST:event_POReceiveMouseClicked

    private void POReceiveCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POReceiveCreateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POReceiveCreate");
    }//GEN-LAST:event_POReceiveCreateMouseClicked

    private void POReceiveUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POReceiveUpdateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POReceiveUpdate");
    }//GEN-LAST:event_POReceiveUpdateMouseClicked

    private void POReceiveViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POReceiveViewMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POReceiveView");
    }//GEN-LAST:event_POReceiveViewMouseClicked

    private void POReceiveDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POReceiveDeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "POReceiveDelete");
    }//GEN-LAST:event_POReceiveDeleteMouseClicked

    private void lbReportingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportingMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "reportting");
    }//GEN-LAST:event_lbReportingMouseClicked

    private void lbReportingSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportingSaleMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "reporttingSale");
    }//GEN-LAST:event_lbReportingSaleMouseClicked

    private void lbReportingPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportingPOMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "reporttingPO");
    }//GEN-LAST:event_lbReportingPOMouseClicked

    private void lbReportingPurchaseReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportingPurchaseReceiveMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "reporttingPurchaseReceive");
    }//GEN-LAST:event_lbReportingPurchaseReceiveMouseClicked

    private void lbReportingInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportingInventoryMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "reporttingInventory");
    }//GEN-LAST:event_lbReportingInventoryMouseClicked

    private void lbStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStaffMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staff");
    }//GEN-LAST:event_lbStaffMouseClicked

    private void lbStaffInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStaffInfoMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staffInfo");
    }//GEN-LAST:event_lbStaffInfoMouseClicked

    private void lbStaffCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStaffCreateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staffInfoCreate");
    }//GEN-LAST:event_lbStaffCreateMouseClicked

    private void lbstaffUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbstaffUpdateMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staffInfoUpdate");
    }//GEN-LAST:event_lbstaffUpdateMouseClicked

    private void lbstaffViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbstaffViewMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staffInfoView");
    }//GEN-LAST:event_lbstaffViewMouseClicked

    private void lbstaffDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbstaffDeleteMouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "staffInfoDelete");
    }//GEN-LAST:event_lbstaffDeleteMouseClicked

    private void btnCheck129MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheck129MouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "userLogin");
    }//GEN-LAST:event_btnCheck129MouseClicked

    private void btnCheck131MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheck131MouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "userLoginUpdate");
    }//GEN-LAST:event_btnCheck131MouseClicked

    private void btnCheck136MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheck136MouseClicked
        JLabel label = (JLabel) evt.getSource();
        setAction(label, "userPermission");
    }//GEN-LAST:event_btnCheck136MouseClicked

    private void setAction(JLabel label, String actionType) {

        if (roleId.equals("-1")) {
            JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
            j.setMessage("Please select Role first!");
            j.setVisible(true);
            return;
        }

        // Toggle the appropriate state based on actionType
        switch (actionType) {
            case "stock" -> {
                isStock = !isStock;
                updateIcon(label, isStock);
                // note : permissionId = 5 and parentId = 0 from database table pos_permission
                setRoleHasPermission(
                        isStock,
                        5,
                        0,
                        false,
                        false,
                        false,
                        false);
            }

            // =============== blog product ================
            case "product" -> {
                isProduct = !isProduct;
                updateIcon(label, isProduct);
                // note : permissionId = 9 and parentId = 5 from database table pos_permission
                setRoleHasPermission(
                        isProduct,
                        9,
                        5,
                        isProductCreate,
                        isProductView,
                        isProductUpdate,
                        isProductDelete);
            }
            case "productCreate" -> {
                isProductCreate = !isProductCreate;
                updateIcon(label, isProductCreate);
                // note : permissionId = 9 from database table pos_permission
                setCreate(9, isProductCreate);
            }
            case "productUpdate" -> {
                isProductUpdate = !isProductUpdate;
                updateIcon(label, isProductUpdate);
                // note : permissionId = 9 from database table pos_permission
                setUpdate(9, isProductUpdate);
            }
            case "productView" -> {
                isProductView = !isProductView;
                updateIcon(label, isProductView);
                // note : permissionId = 9 from database table pos_permission
                setView(9, isProductView);
            }
            case "productDelete" -> {
                isProductDelete = !isProductDelete;
                updateIcon(label, isProductDelete);
                // note : permissionId = 9 from database table pos_permission
                setDelete(9, isProductDelete);
            }
            // =============== end product ================

            case "PO" -> {
                isPO = !isPO;
                updateIcon(label, isPO);
                // note : permissionId = 10 and parentId = 5 from database table pos_permission
                setRoleHasPermission(
                        isPO,
                        10,
                        5,
                        isPOCreate,
                        isPOView,
                        isPOUpdate,
                        isPODelete);
            }
            // =============== blog Purchase Order ================
            case "PORequest" -> {
                isPORequest = !isPORequest;
                updateIcon(label, isPORequest);
                // note : permissionId = 12 and parentId = 10 from database table pos_permission
                setRoleHasPermission(
                        isPO,
                        12,
                        10,
                        isPOCreate,
                        isPOView,
                        isPOUpdate,
                        isPODelete);
            }
            case "POCreate" -> {
                isPOCreate = !isPOCreate;
                updateIcon(label, isPOCreate);
                // note : permissionId = 12 from database table pos_permission
                setCreate(12, isPOCreate);
            }
            case "POUpdate" -> {
                isPOUpdate = !isPOUpdate;
                updateIcon(label, isPOUpdate);
                // note : permissionId = 12 from database table pos_permission
                setUpdate(12, isPOUpdate);
            }
            case "POView" -> {
                isPOView = !isPOView;
                updateIcon(label, isPOView);
                // note : permissionId = 12 from database table pos_permission
                setView(12, isPOView);
            }
            case "PODelete" -> {
                isPODelete = !isPODelete;
                updateIcon(label, isPODelete);
                // note : permissionId = 12 from database table pos_permission
                setDelete(12, isPODelete);
            }
            // =============== end Purchase Order ================

            // =============== blog Purchase Check ================
            case "POCheck" -> {
                isPOCheck = !isPOCheck;
                updateIcon(label, isPOCheck);
                // note : permissionId = 13 and parentId = 11 from database table pos_permission
                setRoleHasPermission(
                        isPOCheck,
                        13,
                        10,
                        isPOCheckCreate,
                        isPOCheckView,
                        isPOCheckUpdate,
                        isPOCheckDelete);
            }
            case "POCheckCreate" -> {
                isPOCheckCreate = !isPOCheckCreate;
                updateIcon(label, isPOCheckCreate);
                // note : permissionId = 13 from database table pos_permission
                setCreate(13, isPOCheckCreate);
            }
            case "POCheckUpdate" -> {
                isPOCheckUpdate = !isPOCheckUpdate;
                updateIcon(label, isPOCheckUpdate);
                // note : permissionId = 13 from database table pos_permission
                setUpdate(13, isPOCheckUpdate);
            }
            case "POCheckView" -> {
                isPOCheckView = !isPOCheckView;
                updateIcon(label, isPOCheckView);
                // note : permissionId = 13 from database table pos_permission
                setView(13, isPOCheckView);
            }
            case "POCheckDelete" -> {
                isPOCheckDelete = !isPOCheckDelete;
                updateIcon(label, isPOCheckDelete);
                // note : permissionId = 13 from database table pos_permission
                setDelete(13, isPOCheckDelete);
            }
            // =============== end Purchase Check ================

            // =============== blog Purchase approval ================
            case "POApproval" -> {
                isPOApproval = !isPOApproval;
                updateIcon(label, isPOApproval);
                // note : permissionId = 14 and parentId = 11 from database table pos_permission
                setRoleHasPermission(
                        isPOApproval,
                        14,
                        10,
                        isPOApprovalCreate,
                        isPOApprovalView,
                        isPOApprovalUpdate,
                        isPOApprovalDelete);
            }
            case "POApprovalCreate" -> {
                isPOApprovalCreate = !isPOApprovalCreate;
                updateIcon(label, isPOApprovalCreate);
                // note : permissionId = 14 from database table pos_permission
                setCreate(14, isPOApprovalCreate);
            }
            case "POApprovalUpdate" -> {
                isPOApprovalUpdate = !isPOApprovalUpdate;
                updateIcon(label, isPOApprovalUpdate);
                // note : permissionId = 14 from database table pos_permission
                setUpdate(14, isPOApprovalUpdate);
            }
            case "POApprovalView" -> {
                isPOApprovalView = !isPOApprovalView;
                updateIcon(label, isPOApprovalView);
                // note : permissionId = 14 from database table pos_permission
                setView(14, isPOApprovalView);
            }
            case "POApprovalDelete" -> {
                isPOApprovalDelete = !isPOApprovalDelete;
                updateIcon(label, isPOApprovalDelete);
                // note : permissionId = 14 from database table pos_permission
                setDelete(14, isPOApprovalDelete);
            }
            // =============== end Purchase approval ================

            // =============== blog Purchase Receive ================
            case "POReceive" -> {
                isPOReceive = !isPOReceive;
                updateIcon(label, isPOReceive);
                // note : permissionId = 11 and parentId = 11 from database table pos_permission
                setRoleHasPermission(
                        isPOReceive,
                        11,
                        5,
                        isPOReceiveCreate,
                        isPOReceiveView,
                        isPOReceiveUpdate,
                        isPOReceiveDelete);
            }
            case "POReceiveCreate" -> {
                isPOReceiveCreate = !isPOReceiveCreate;
                updateIcon(label, isPOReceiveCreate);
                // note : permissionId = 11 from database table pos_permission
                setCreate(11, isPOReceiveCreate);
            }
            case "POReceiveUpdate" -> {
                isPOReceiveUpdate = !isPOReceiveUpdate;
                updateIcon(label, isPOReceiveUpdate);
                // note : permissionId = 11 from database table pos_permission
                setUpdate(11, isPOReceiveUpdate);
            }
            case "POReceiveView" -> {
                isPOReceiveView = !isPOReceiveView;
                updateIcon(label, isPOReceiveView);
                // note : permissionId = 11 from database table pos_permission
                setView(11, isPOReceiveView);
            }
            case "POReceiveDelete" -> {
                isPOReceiveDelete = !isPOReceiveDelete;
                updateIcon(label, isPOReceiveDelete);
                // note : permissionId = 11from database table pos_permission
                setDelete(11, isPOReceiveDelete);
            }
            // =============== end Purchase Receive ================

            // =============== blog reportting ================
            case "reportting" -> {
                isReport = !isReport;
                updateIcon(label, isReport);
                // note : permissionId = 7 and parentId = 0 from database table pos_permission
                setRoleHasPermission(
                        isReport,
                        7,
                        0,
                        false,
                        false,
                        false,
                        false);
            }
            case "reporttingSale" -> {
                isReportingSale = !isReportingSale;
                updateIcon(label, isReportingSale);
                // note : permissionId = 29 and parentId = 7 from database table pos_permission
                setRoleHasPermission(
                        isReportingSale,
                        29,
                        7,
                        false,
                        false,
                        false,
                        false);
            }
            case "reporttingPO" -> {
                isReportPO = !isReportPO;
                updateIcon(label, isReportPO);
                // note : permissionId = 30 and parentId = 7 from database table pos_permission
                setRoleHasPermission(
                        isReportPO,
                        30,
                        7,
                        false,
                        false,
                        false,
                        false);
            }
            case "reporttingPurchaseReceive" -> {
                isReportingPurchaseReceive = !isReportingPurchaseReceive;
                updateIcon(label, isReportingPurchaseReceive);
                // note : permissionId = 31 and parentId = 7 from database table pos_permission
                setRoleHasPermission(
                        isReportingPurchaseReceive,
                        31,
                        7,
                        false,
                        false,
                        false,
                        false);
            }
            case "reporttingInventory" -> {
                isReportingInventory = !isReportingInventory;
                updateIcon(label, isReportingInventory);
                // note : permissionId = 32 and parentId = 7 from database table pos_permission
                setRoleHasPermission(
                        isReportingInventory,
                        32,
                        7,
                        false,
                        false,
                        false,
                        false);
            }
            // =============== end Purchase Receive ================

            case "staff" -> {
                isSfaff = !isSfaff;
                updateIcon(label, isSfaff);
                // note : permissionId = 8 and parentId = 0 from database table pos_permission
                setRoleHasPermission(
                        isSfaff,
                        8,
                        0,
                        false,
                        false,
                        false,
                        false);
            }
            // =============== blog staffinfo ================
            case "staffInfo" -> {
                isSfaffInfo = !isSfaffInfo;

                updateIcon(label, isSfaffInfo);
                // note : permissionId = 33 and parentId = 8 from database table pos_permission
                setRoleHasPermission(
                        isSfaffInfo,
                        33,
                        8,
                        isSfaffInfoCreate,
                        isSfaffInfoView,
                        isSfaffInfoUpdate,
                        isSfaffInfoDelete);
            }
            case "staffInfoCreate" -> {
                isSfaffInfoCreate = !isSfaffInfoCreate;
                updateIcon(label, isSfaffInfoCreate);
                // note : permissionId = 11 from database table pos_permission
                setUpdate(33, isSfaffInfoCreate);
            }
            case "staffInfoView" -> {
                isSfaffInfoView = !isSfaffInfoView;
                updateIcon(label, isSfaffInfoView);
                // note : permissionId = 33 from database table pos_permission
                setView(33, isSfaffInfoView);
            }
            case "staffInfoUpdate" -> {
                isSfaffInfoUpdate = !isSfaffInfoUpdate;
                updateIcon(label, isSfaffInfoUpdate);
                // note : permissionId = 33 from database table pos_permission
                setDelete(33, isSfaffInfoUpdate);
            }
            case "staffInfoDelete" -> {
                isSfaffInfoDelete = !isSfaffInfoDelete;
                updateIcon(label, isSfaffInfoDelete);
                // note : permissionId = 33 from database table pos_permission
                setDelete(33, isSfaffInfoDelete);
            }
            // =============== end staff info ================

            // =============== blog user login ================
            case "userLogin" -> {
                isUserLogin = !isUserLogin;

                updateIcon(label, isUserLogin);
                // note : permissionId = 34 and parentId = 8 from database table pos_permission
                setRoleHasPermission(
                        isUserLogin,
                        34,
                        8,
                        false,
                        false,
                        false,
                        false);
            }
            case "userLoginUpdate" -> {
                isUserLoginUpdate = !isUserLoginUpdate;
                updateIcon(label, isUserLoginUpdate);
                // note : permissionId = 34 from database table pos_permission
                setDelete(34, isUserLoginUpdate);
            }
            // =============== end user login ================

            case "userPermission" -> {
                isUserPermission = !isUserPermission;

                updateIcon(label, isUserPermission);
                // note : permissionId = 34 and parentId = 8 from database table pos_permission
                setRoleHasPermission(
                        isUserPermission,
                        35,
                        8,
                        false,
                        false,
                        false,
                        false);
            }

        }
    }

//    POReceive
    private void setRoleHasPermission(
            boolean isVisible,
            int permissionId,
            int parentId,
            boolean isCreate,
            boolean isView,
            boolean isUpdate,
            boolean isDelete
    ) {

        if (isVisible) {   // Add to the list

            RoleHasPermission roleHasPermission = new RoleHasPermission();
            roleHasPermission.setRoleId(Integer.valueOf(roleId));
            roleHasPermission.setPermissionId(permissionId);
            roleHasPermission.setParentId(parentId);
            roleHasPermission.setIsVisible(isVisible);
            roleHasPermission.setIsCreate(isCreate);
            roleHasPermission.setIsView(isView);
            roleHasPermission.setIsUpdate(isUpdate);
            roleHasPermission.setIsDelete(isDelete);
            listRoles.add(roleHasPermission);

        } else { // Remove from the list
            listRoles.removeIf(rhp
                    -> rhp.getRoleId() == Integer.valueOf(roleId)
                    && rhp.getPermissionId() == permissionId
            );

        }
    }

   

    private void setCreate(
            int permissionId,
            boolean isCreate
    ) {
        for (RoleHasPermission rolePermission : listRoles) {
            if (rolePermission.getPermissionId() == permissionId) {
                rolePermission.setIsCreate(isCreate);
                break;
            }
        }
    }

    private void setUpdate(
            int permissionId,
            boolean isUpdate
    ) {
        for (RoleHasPermission rolePermission : listRoles) {
            if (rolePermission.getPermissionId() == permissionId) {
                rolePermission.setIsUpdate(isUpdate);
                break;
            }
        }
    }

    private void setView(
            int permissionId,
            boolean isView
    ) {
        for (RoleHasPermission rolePermission : listRoles) {
            if (rolePermission.getPermissionId() == permissionId) {
                rolePermission.setIsView(isView);
                break;
            }
        }
    }

    private void setDelete(
            int permissionId,
            boolean isDelete
    ) {
        for (RoleHasPermission rolePermission : listRoles) {
            if (rolePermission.getPermissionId() == permissionId) {
                rolePermission.setIsDelete(isDelete);
                break;
            }
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
    private javax.swing.JLabel POReceive;
    private javax.swing.JLabel POReceiveCreate;
    private javax.swing.JLabel POReceiveDelete;
    private javax.swing.JLabel POReceiveUpdate;
    private javax.swing.JLabel POReceiveView;
    private Button.Button btnCancel;
    private javax.swing.JLabel btnCheck100;
    private javax.swing.JLabel btnCheck101;
    private javax.swing.JLabel btnCheck129;
    private javax.swing.JLabel btnCheck131;
    private javax.swing.JLabel btnCheck136;
    private javax.swing.JLabel btnCheck139;
    private javax.swing.JLabel btnCheck15;
    private javax.swing.JLabel btnCheck16;
    private javax.swing.JLabel btnCheck17;
    private javax.swing.JLabel btnCheck18;
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
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbPO;
    private javax.swing.JLabel lbPOCheck;
    private javax.swing.JLabel lbPOCheckCreate;
    private javax.swing.JLabel lbPOCheckDelete;
    private javax.swing.JLabel lbPOCheckUpdate;
    private javax.swing.JLabel lbPOCheckView;
    private javax.swing.JLabel lbPOCreate;
    private javax.swing.JLabel lbPODelete;
    private javax.swing.JLabel lbPORequest;
    private javax.swing.JLabel lbPOUpdate;
    private javax.swing.JLabel lbPOView;
    private javax.swing.JLabel lbProduct;
    private javax.swing.JLabel lbReporting;
    private javax.swing.JLabel lbReportingInventory;
    private javax.swing.JLabel lbReportingPO;
    private javax.swing.JLabel lbReportingPurchaseReceive;
    private javax.swing.JLabel lbReportingSale;
    private javax.swing.JLabel lbStaff;
    private javax.swing.JLabel lbStaffCreate;
    private javax.swing.JLabel lbStaffInfo;
    private javax.swing.JLabel lbStock;
    private javax.swing.JLabel lbstaffDelete;
    private javax.swing.JLabel lbstaffUpdate;
    private javax.swing.JLabel lbstaffView;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelData;
    private javax.swing.JLabel permissionName;
    private javax.swing.JLabel permissionName1;
    private javax.swing.JLabel permissionName10;
    private javax.swing.JLabel permissionName100;
    private javax.swing.JLabel permissionName101;
    private javax.swing.JLabel permissionName102;
    private javax.swing.JLabel permissionName103;
    private javax.swing.JLabel permissionName108;
    private javax.swing.JLabel permissionName11;
    private javax.swing.JLabel permissionName113;
    private javax.swing.JLabel permissionName118;
    private javax.swing.JLabel permissionName12;
    private javax.swing.JLabel permissionName123;
    private javax.swing.JLabel permissionName124;
    private javax.swing.JLabel permissionName125;
    private javax.swing.JLabel permissionName126;
    private javax.swing.JLabel permissionName127;
    private javax.swing.JLabel permissionName128;
    private javax.swing.JLabel permissionName129;
    private javax.swing.JLabel permissionName13;
    private javax.swing.JLabel permissionName131;
    private javax.swing.JLabel permissionName134;
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
    private javax.swing.JLabel poApproval;
    private javax.swing.JLabel poApprovalCreate;
    private javax.swing.JLabel poApprovalDelete;
    private javax.swing.JLabel poApprovalUpdate;
    private javax.swing.JLabel poApprovalView;
    private javax.swing.JLabel productCreate;
    private javax.swing.JLabel productDelete;
    private javax.swing.JLabel productUpdate;
    private javax.swing.JLabel productView;
    private FormComponent.combobox.JavaCombobox role;
    // End of variables declaration//GEN-END:variables
}
