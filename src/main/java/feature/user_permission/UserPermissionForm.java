package feature.user_permission;

import Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import LoginAndLogoutForm.model.RoleHasPermissionModel;
import Staff.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.user_permission.model.RoleHasPermission;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import okhttp3.Response;
import org.json.JSONObject;

public class UserPermissionForm extends javax.swing.JDialog {

     private String roleId = "-1";
   

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
          JavaComboBoxSelection.addComboBox(objRole,
               JavaRoute.role,
               "role_name",
               JavaComboBoxSelection.DESC);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    roleId = id;
                    // get permission by role
                    getPermissionByRole(roleId);

               }
          };
          objRole.initEvent(event);
     }

     private void unCheckBox() {
          Component[] listCom = panelData.getComponents();
          for (Component com : listCom) {
               if (com instanceof JLabel) {
                    String title = ((JLabel) com).getText();
                    if (title.isEmpty()) {
                         ((JLabel) com).setIcon(new ImageIcon(getClass().getResource("/icon/check.png")));
                    }
               }
          }

     }

     private void getPermissionByRole(String _roleId) {
          try {

               unCheckBox();
               listRoles.clear(); // clear 

               Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "/readByRole?roleId=" + _roleId);

               String responseData = response.body().string();

               ObjectMapper object = new ObjectMapper();

               RoleHasPermissionModel data = object.readValue(responseData, RoleHasPermissionModel.class);

               // add data to listRoles
               for (RoleHasPermissionModel.RoleHasPermissionDetail result : data.getData()) {
                    listRoles.add(RoleHasPermission.builder()
                         .roleId(Integer.valueOf(roleId))
                         .permissionId(result.getPermissionId())
                         .parentId(result.getParentId())
                         .isVisible(result.getIsVisible())
                         .isCreate(result.getIsCreate())
                         .isView(result.getIsView())
                         .isUpdate(result.getIsUpdate())
                         .isDelete(result.getIsDelete())
                         .build());

                    String permissionName = result.getPermissionName().toLowerCase();
                    boolean isVisible = result.getIsVisible();
                    boolean isCreate = result.getIsCreate();
                    boolean isView = result.getIsView();
                    boolean isUpdate = result.getIsUpdate();
                    boolean isDelete = result.getIsDelete();

                    // not permissionName convert to lowercase
                    switch (permissionName) {
                         case "stock" -> {
                              updateIcon(lbStock, isVisible);
                              isStock = isVisible;
                         }
                         case "product" -> {
                              updateIcon(lbProduct, isVisible);
                              isProduct = isVisible;
                              updateIcon(lbProductCreate, isCreate);
                              isProductCreate = isCreate;
                              updateIcon(lbProductView, isView);
                              isProductView = isView;
                              updateIcon(lbProductUpdate, isUpdate);
                              isProductUpdate = isUpdate;
                              updateIcon(lbProductDelete, isDelete);
                              isProductDelete = isDelete;
                         }
                         case "purchase order" -> {
                              updateIcon(lbPO, isVisible);
                              isPO = isVisible;
                         }
                         case "purchase request" -> {
                              updateIcon(lbPORequest, isVisible);
                              isPORequest = isVisible;
                              updateIcon(lbPOCreate, isCreate);
                              isPOCreate = isCreate;
                              updateIcon(lbPOView, isView);
                              isPOView = isView;
                              updateIcon(lbPOUpdate, isUpdate);
                              isPOUpdate = isUpdate;
                              updateIcon(lbPODelete, isDelete);
                              isPODelete = isDelete;
                         }
                         case "purchase check" -> {
                              updateIcon(lbPOCheck, isVisible);
                              isPOCheck = isVisible;
                              updateIcon(lbPOCheckCreate, isCreate);
                              isPOCheckCreate = isCreate;
                              updateIcon(lbPOCheckView, isView);
                              isPOCheckView = isView;
                              updateIcon(lbPOCheckUpdate, isUpdate);
                              isPOCheckUpdate = isUpdate;
                              updateIcon(lbPOCheckDelete, isDelete);
                              isPOCheckDelete = isDelete;
                         }
                         case "purchase approval" -> {
                              updateIcon(lbPOApproval, isVisible);
                              isPOApproval = isVisible;
                              updateIcon(lbPOApprovalCreate, isCreate);
                              isPOApprovalCreate = isCreate;
                              updateIcon(lbPOApprovalView, isView);
                              isPOApprovalView = isView;
                              updateIcon(lbPOApprovalUpdate, isUpdate);
                              isPOApprovalUpdate = isUpdate;
                              updateIcon(lbPOApprovalDelete, isDelete);
                              isPOApprovalDelete = isDelete;
                         }
                         case "purchase receive" -> {
                              updateIcon(lbPOReceive, isVisible);
                              isPOReceive = isVisible;
                              updateIcon(lbPOReceiveCreate, isCreate);
                              isPOReceiveCreate = isCreate;
                              updateIcon(lbPOReceiveView, isView);
                              isPOReceiveView = isView;
                              updateIcon(lbPOReceiveUpdate, isUpdate);
                              isPOReceiveUpdate = isUpdate;
                              updateIcon(lbPOReceiveDelete, isDelete);
                              isPOReceiveDelete = isDelete;
                         }
                         case "settings" -> {
                              updateIcon(lbSettings, isVisible);
                              isSettings = isVisible;
                         }
                         case "division" -> {
                              updateIcon(lbDivision, isVisible);
                              isDivision = isVisible;
                              updateIcon(lbDivisionCreate, isCreate);
                              isDivisionCreate = isCreate;
                              updateIcon(lbDivisionView, isView);
                              isDivisionView = isView;
                              updateIcon(lbDivisionUpdate, isUpdate);
                              isDivisionUpdate = isUpdate;
                              updateIcon(lbDivisionDelete, isDelete);
                              isDivisionoDelete = isDelete;
                         }
                         case "department" -> {
                              updateIcon(lbDepartment, isVisible);
                              isDepartment = isVisible;
                              updateIcon(lbDepartmentCreate, isCreate);
                              isDepartmentCreate = isCreate;
                              updateIcon(lbDepartmentView, isView);
                              isDepartmentView = isView;
                              updateIcon(lbDepartmentUpdate, isUpdate);
                              isDepartmentUpdate = isUpdate;
                              updateIcon(lbDepartmentDelete, isDelete);
                              isDepartmentDelete = isDelete;
                         }
                         case "category" -> {
                              updateIcon(lbCategory, isVisible);
                              isCategory = isVisible;
                              updateIcon(lbCategoryCreate, isCreate);
                              isCategoryCreate = isCreate;
                              updateIcon(lbCategoryView, isView);
                              isCategoryView = isView;
                              updateIcon(lbCategoryUpdate, isUpdate);
                              isCategoryUpdate = isUpdate;
                              updateIcon(lbCategoryDelete, isDelete);
                              isCategoryDelete = isDelete;
                         }

                         case "sub category" -> {
                              updateIcon(lbSubCategory, isVisible);
                              isSubCategory = isVisible;
                              updateIcon(lbSubCategoryCreate, isCreate);
                              isSubCategoryCreate = isCreate;
                              updateIcon(lbSubCategoryView, isView);
                              isSubCategoryView = isView;
                              updateIcon(lbSubCategoryUpdate, isUpdate);
                              isSubCategoryUpdate = isUpdate;
                              updateIcon(lbSubCategoryDelete, isDelete);
                              isSubCategoryDelete = isDelete;
                         }

                         case "brand" -> {
                              updateIcon(lbBrand, isVisible);
                              isBrand = isVisible;
                              updateIcon(lbBrandCreate, isCreate);
                              isBrandCreate = isCreate;
                              updateIcon(lbBrandView, isView);
                              isBrandView = isView;
                              updateIcon(lbBrandUpdate, isUpdate);
                              isBrandUpdate = isUpdate;
                              updateIcon(lbBrandDelete, isDelete);
                              isBrandDelete = isDelete;
                         }

                         case "vendor" -> {
                              updateIcon(lbVendor, isVisible);
                              isVendor = isVisible;
                              updateIcon(lbVendorCreate, isCreate);
                              isVendorCreate = isCreate;
                              updateIcon(lbVendorView, isView);
                              isVendorView = isView;
                              updateIcon(lbVendorUpdate, isUpdate);
                              isVendorUpdate = isUpdate;
                              updateIcon(lbVendorDelete, isDelete);
                              isVendorDelete = isDelete;
                         }

                         case "attribute" -> {
                              updateIcon(lbAttribute, isVisible);
                              isAttribute = isVisible;
                              updateIcon(lbAttributeCreate, isCreate);
                              isAttributeCreate = isCreate;
                              updateIcon(lbAttributeView, isView);
                              isAttributeView = isView;
                              updateIcon(lbAttributeUpdate, isUpdate);
                              isAttributeUpdate = isUpdate;
                              updateIcon(lbAttributeDelete, isDelete);
                              isAttributeDelete = isDelete;
                         }

                         case "uom" -> {
                              updateIcon(lbUom, isVisible);
                              isUom = isVisible;
                              updateIcon(lbUomCreate, isCreate);
                              isUomCreate = isCreate;
                              updateIcon(lbUomView, isView);
                              isUomView = isView;
                              updateIcon(lbUomUpdate, isUpdate);
                              isUomUpdate = isUpdate;
                              updateIcon(lbUomDelete, isDelete);
                              isUomDelete = isDelete;
                         }

                         case "country" -> {
                              updateIcon(lbCountry, isVisible);
                              isCountry = isVisible;
                              updateIcon(lbCountryCreate, isCreate);
                              isCountryCreate = isCreate;
                              updateIcon(lbCountryView, isView);
                              isCountryView = isView;
                              updateIcon(lbCountryUpdate, isUpdate);
                              isCountryUpdate = isUpdate;
                              updateIcon(lbCountryDelete, isDelete);
                              isCountryDelete = isDelete;
                         }

                         case "tax" -> {
                              updateIcon(lbTax, isVisible);
                              isTax = isVisible;
                              updateIcon(lbTaxCreate, isCreate);
                              isTaxCreate = isCreate;
                              updateIcon(lbTaxView, isView);
                              isTaxView = isView;
                              updateIcon(lbTaxUpdate, isUpdate);
                              isTaxUpdate = isUpdate;
                              updateIcon(lbTaxDelete, isDelete);
                              isTaxDelete = isDelete;
                         }

                         case "status" -> {
                              updateIcon(lbStatus, isVisible);
                              isStatus = isVisible;
                              updateIcon(lbStatusCreate, isCreate);
                              isStatusCreate = isCreate;
                              updateIcon(lbStatusView, isView);
                              isStatusView = isView;
                              updateIcon(lbStatusUpdate, isUpdate);
                              isStatusUpdate = isUpdate;
                              updateIcon(lbStatusDelete, isDelete);
                              isStatusDelete = isDelete;
                         }

                         case "warehouse" -> {
                              updateIcon(lbWarehouse, isVisible);
                              isWarehouse = isVisible;
                              updateIcon(lbWarehouseCreate, isCreate);
                              isWarehouseCreate = isCreate;
                              updateIcon(lbWarehouseView, isView);
                              isWarehouseView = isView;
                              updateIcon(lbWarehouseUpdate, isUpdate);
                              isWarehouseUpdate = isUpdate;
                              updateIcon(lbWarehouseDelete, isDelete);
                              isWarehouseDelete = isDelete;
                         }

                         case "range" -> {
                              updateIcon(lbRange, isVisible);
                              isRange = isVisible;
                              updateIcon(lbRangeCreate, isCreate);
                              isRangeCreate = isCreate;
                              updateIcon(lbRangeView, isView);
                              isRangeView = isView;
                              updateIcon(lbRangeUpdate, isUpdate);
                              isRangeUpdate = isUpdate;
                              updateIcon(lbRangeDelete, isDelete);
                              isRangeDelete = isDelete;
                         }

                         case "slot" -> {
                              updateIcon(lbSlot, isVisible);
                              isSlot = isVisible;
                              updateIcon(lbSlotCreate, isCreate);
                              isSlotCreate = isCreate;
                              updateIcon(lbSlotView, isView);
                              isSlotView = isView;
                              updateIcon(lbSlotUpdate, isUpdate);
                              isSlotUpdate = isUpdate;
                              updateIcon(lbSlotDelete, isDelete);
                              isSlotDelete = isDelete;
                         }

                         case "reporting" -> {
                              updateIcon(lbReporting, isVisible);
                              isReport = isVisible;
                         }

                         case "reporting sale" -> {
                              updateIcon(lbReportingSale, isVisible);
                              isReportingSale = isVisible;
                         }
                         case "reporting purchase order" -> {
                              updateIcon(lbReportingPO, isVisible);
                              isReportPO = isVisible;
                         }
                         case "reporting purchase receive" -> {
                              updateIcon(lbReportingPurchaseReceive, isVisible);
                              isReportingPurchaseReceive = isVisible;
                         }
                         case "reporting inventory" -> {
                              updateIcon(lbReportingInventory, isVisible);
                              isReportingInventory = isVisible;
                         }

                         case "staff" -> {
                              updateIcon(lbStaff, isVisible);
                              isSfaff = isVisible;
                         }
                         case "staff information" -> {
                              updateIcon(lbStaffInfo, isVisible);
                              isSfaffInfo = isVisible;
                              updateIcon(lbStaffCreate, isCreate);
                              isSfaffInfoCreate = isCreate;
                              updateIcon(lbstaffView, isView);
                              isSfaffInfoView = isView;
                              updateIcon(lbstaffUpdate, isUpdate);
                              isSfaffInfoUpdate = isUpdate;
                              updateIcon(lbstaffDelete, isDelete);
                              isSfaffInfoDelete = isDelete;
                         }

                         case "user login" -> {
                              updateIcon(lbUserLogin, isVisible);
                              isUserLogin = isVisible;
                              updateIcon(lbUserLoginUpdate, isUpdate);
                              isUserLoginUpdate = isUpdate;
                         }
                         case "user permission" -> {
                              updateIcon(lbUserPermission, isVisible);
                              isUserPermission = isVisible;
                         }
                    }
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }
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
          jScrollPane1 = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          lbStock = new javax.swing.JLabel();
          permissionName = new javax.swing.JLabel();
          lbProduct = new javax.swing.JLabel();
          permissionName1 = new javax.swing.JLabel();
          lbPO = new javax.swing.JLabel();
          permissionName2 = new javax.swing.JLabel();
          lbPOReceive = new javax.swing.JLabel();
          permissionName3 = new javax.swing.JLabel();
          permissionName4 = new javax.swing.JLabel();
          lbProductCreate = new javax.swing.JLabel();
          lbProductUpdate = new javax.swing.JLabel();
          permissionName5 = new javax.swing.JLabel();
          lbProductView = new javax.swing.JLabel();
          permissionName6 = new javax.swing.JLabel();
          lbProductDelete = new javax.swing.JLabel();
          permissionName7 = new javax.swing.JLabel();
          permissionName8 = new javax.swing.JLabel();
          lbPORequest = new javax.swing.JLabel();
          lbPOCheck = new javax.swing.JLabel();
          permissionName9 = new javax.swing.JLabel();
          lbPOApproval = new javax.swing.JLabel();
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
          lbPOApprovalCreate = new javax.swing.JLabel();
          permissionName23 = new javax.swing.JLabel();
          lbPOApprovalUpdate = new javax.swing.JLabel();
          permissionName24 = new javax.swing.JLabel();
          lbPOApprovalView = new javax.swing.JLabel();
          permissionName25 = new javax.swing.JLabel();
          lbPOApprovalDelete = new javax.swing.JLabel();
          permissionName26 = new javax.swing.JLabel();
          lbPOReceiveCreate = new javax.swing.JLabel();
          permissionName27 = new javax.swing.JLabel();
          lbPOReceiveUpdate = new javax.swing.JLabel();
          permissionName28 = new javax.swing.JLabel();
          lbPOReceiveView = new javax.swing.JLabel();
          permissionName29 = new javax.swing.JLabel();
          lbPOReceiveDelete = new javax.swing.JLabel();
          permissionName30 = new javax.swing.JLabel();
          lbSettings = new javax.swing.JLabel();
          permissionName31 = new javax.swing.JLabel();
          lbDivision = new javax.swing.JLabel();
          permissionName32 = new javax.swing.JLabel();
          lbDivisionCreate = new javax.swing.JLabel();
          permissionName33 = new javax.swing.JLabel();
          lbDivisionUpdate = new javax.swing.JLabel();
          permissionName34 = new javax.swing.JLabel();
          lbDivisionView = new javax.swing.JLabel();
          permissionName35 = new javax.swing.JLabel();
          lbDivisionDelete = new javax.swing.JLabel();
          permissionName36 = new javax.swing.JLabel();
          lbDepartment = new javax.swing.JLabel();
          permissionName37 = new javax.swing.JLabel();
          lbDepartmentCreate = new javax.swing.JLabel();
          permissionName38 = new javax.swing.JLabel();
          lbDepartmentUpdate = new javax.swing.JLabel();
          permissionName39 = new javax.swing.JLabel();
          lbDepartmentView = new javax.swing.JLabel();
          permissionName40 = new javax.swing.JLabel();
          lbDepartmentDelete = new javax.swing.JLabel();
          permissionName41 = new javax.swing.JLabel();
          lbCategoryCreate = new javax.swing.JLabel();
          permissionName42 = new javax.swing.JLabel();
          permissionName43 = new javax.swing.JLabel();
          lbCategoryUpdate = new javax.swing.JLabel();
          lbCategoryDelete = new javax.swing.JLabel();
          lbCategory = new javax.swing.JLabel();
          permissionName44 = new javax.swing.JLabel();
          permissionName45 = new javax.swing.JLabel();
          permissionName46 = new javax.swing.JLabel();
          lbCategoryView = new javax.swing.JLabel();
          lbSubCategory = new javax.swing.JLabel();
          permissionName47 = new javax.swing.JLabel();
          permissionName48 = new javax.swing.JLabel();
          lbSubCategoryView = new javax.swing.JLabel();
          permissionName49 = new javax.swing.JLabel();
          lbSubCategoryUpdate = new javax.swing.JLabel();
          permissionName50 = new javax.swing.JLabel();
          permissionName51 = new javax.swing.JLabel();
          lbSubCategoryCreate = new javax.swing.JLabel();
          lbSubCategoryDelete = new javax.swing.JLabel();
          permissionName52 = new javax.swing.JLabel();
          lbBrandView = new javax.swing.JLabel();
          permissionName53 = new javax.swing.JLabel();
          permissionName54 = new javax.swing.JLabel();
          permissionName55 = new javax.swing.JLabel();
          lbBrandCreate = new javax.swing.JLabel();
          permissionName56 = new javax.swing.JLabel();
          lbBrand = new javax.swing.JLabel();
          lbBrandUpdate = new javax.swing.JLabel();
          lbBrandDelete = new javax.swing.JLabel();
          lbVendorUpdate = new javax.swing.JLabel();
          lbVendorCreate = new javax.swing.JLabel();
          permissionName57 = new javax.swing.JLabel();
          lbVendor = new javax.swing.JLabel();
          permissionName58 = new javax.swing.JLabel();
          permissionName59 = new javax.swing.JLabel();
          lbVendorDelete = new javax.swing.JLabel();
          permissionName60 = new javax.swing.JLabel();
          lbVendorView = new javax.swing.JLabel();
          permissionName61 = new javax.swing.JLabel();
          lbAttributeCreate = new javax.swing.JLabel();
          permissionName62 = new javax.swing.JLabel();
          permissionName63 = new javax.swing.JLabel();
          lbAttributeDelete = new javax.swing.JLabel();
          lbAttributeUpdate = new javax.swing.JLabel();
          lbAttributeView = new javax.swing.JLabel();
          permissionName64 = new javax.swing.JLabel();
          lbAttribute = new javax.swing.JLabel();
          permissionName65 = new javax.swing.JLabel();
          permissionName66 = new javax.swing.JLabel();
          permissionName67 = new javax.swing.JLabel();
          permissionName68 = new javax.swing.JLabel();
          lbUomUpdate = new javax.swing.JLabel();
          permissionName69 = new javax.swing.JLabel();
          permissionName70 = new javax.swing.JLabel();
          lbUom = new javax.swing.JLabel();
          lbUomCreate = new javax.swing.JLabel();
          lbUomDelete = new javax.swing.JLabel();
          lbUomView = new javax.swing.JLabel();
          permissionName71 = new javax.swing.JLabel();
          lbCountry = new javax.swing.JLabel();
          permissionName72 = new javax.swing.JLabel();
          lbCountryCreate = new javax.swing.JLabel();
          permissionName73 = new javax.swing.JLabel();
          lbCountryUpdate = new javax.swing.JLabel();
          permissionName74 = new javax.swing.JLabel();
          lbCountryView = new javax.swing.JLabel();
          permissionName75 = new javax.swing.JLabel();
          lbCountryDelete = new javax.swing.JLabel();
          permissionName76 = new javax.swing.JLabel();
          lbTaxDelete = new javax.swing.JLabel();
          lbTax = new javax.swing.JLabel();
          permissionName77 = new javax.swing.JLabel();
          lbTaxView = new javax.swing.JLabel();
          permissionName78 = new javax.swing.JLabel();
          lbTaxUpdate = new javax.swing.JLabel();
          permissionName79 = new javax.swing.JLabel();
          permissionName80 = new javax.swing.JLabel();
          lbTaxCreate = new javax.swing.JLabel();
          permissionName81 = new javax.swing.JLabel();
          lbStatusDelete = new javax.swing.JLabel();
          permissionName82 = new javax.swing.JLabel();
          permissionName83 = new javax.swing.JLabel();
          lbStatusCreate = new javax.swing.JLabel();
          lbStatusUpdate = new javax.swing.JLabel();
          permissionName84 = new javax.swing.JLabel();
          permissionName85 = new javax.swing.JLabel();
          lbStatusView = new javax.swing.JLabel();
          permissionName86 = new javax.swing.JLabel();
          lbStatus = new javax.swing.JLabel();
          lbWarehouseView = new javax.swing.JLabel();
          lbWarehouse = new javax.swing.JLabel();
          permissionName87 = new javax.swing.JLabel();
          permissionName88 = new javax.swing.JLabel();
          permissionName89 = new javax.swing.JLabel();
          lbWarehouseCreate = new javax.swing.JLabel();
          permissionName90 = new javax.swing.JLabel();
          lbWarehouseDelete = new javax.swing.JLabel();
          permissionName91 = new javax.swing.JLabel();
          lbWarehouseUpdate = new javax.swing.JLabel();
          lbRange = new javax.swing.JLabel();
          permissionName92 = new javax.swing.JLabel();
          lbRangeCreate = new javax.swing.JLabel();
          permissionName93 = new javax.swing.JLabel();
          lbRangeUpdate = new javax.swing.JLabel();
          permissionName94 = new javax.swing.JLabel();
          lbRangeView = new javax.swing.JLabel();
          permissionName95 = new javax.swing.JLabel();
          lbRangeDelete = new javax.swing.JLabel();
          permissionName96 = new javax.swing.JLabel();
          permissionName97 = new javax.swing.JLabel();
          lbSlotView = new javax.swing.JLabel();
          lbSlot = new javax.swing.JLabel();
          permissionName98 = new javax.swing.JLabel();
          lbSlotUpdate = new javax.swing.JLabel();
          permissionName99 = new javax.swing.JLabel();
          lbSlotCreate = new javax.swing.JLabel();
          permissionName100 = new javax.swing.JLabel();
          lbSlotDelete = new javax.swing.JLabel();
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
          lbUserLogin = new javax.swing.JLabel();
          permissionName129 = new javax.swing.JLabel();
          lbUserLoginUpdate = new javax.swing.JLabel();
          permissionName131 = new javax.swing.JLabel();
          permissionName134 = new javax.swing.JLabel();
          lbUserPermission = new javax.swing.JLabel();
          btnCancel = new Button.Button();
          lbCheckAll = new javax.swing.JLabel();
          permissionName139 = new javax.swing.JLabel();
          btnSave = new ButtonPackage.ButtonSave();
          objRole = new FormComponent.combobox.JavaCombobox();

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

          lbPOReceive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOReceive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOReceiveMouseClicked(evt);
               }
          });

          permissionName3.setText("Purchase Receive");
          permissionName3.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName4.setText("Create");
          permissionName4.setPreferredSize(new java.awt.Dimension(37, 20));

          lbProductCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbProductCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbProductCreateMouseClicked(evt);
               }
          });

          lbProductUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbProductUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbProductUpdateMouseClicked(evt);
               }
          });

          permissionName5.setText("Update");
          permissionName5.setPreferredSize(new java.awt.Dimension(37, 20));

          lbProductView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbProductView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbProductViewMouseClicked(evt);
               }
          });

          permissionName6.setText("View");
          permissionName6.setPreferredSize(new java.awt.Dimension(37, 20));

          lbProductDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbProductDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbProductDeleteMouseClicked(evt);
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

          lbPOApproval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOApproval.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOApprovalMouseClicked(evt);
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

          lbPOApprovalCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOApprovalCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOApprovalCreateMouseClicked(evt);
               }
          });

          permissionName23.setText("Create");
          permissionName23.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOApprovalUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOApprovalUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOApprovalUpdateMouseClicked(evt);
               }
          });

          permissionName24.setText("Update");
          permissionName24.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOApprovalView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOApprovalView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOApprovalViewMouseClicked(evt);
               }
          });

          permissionName25.setText("View");
          permissionName25.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOApprovalDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOApprovalDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOApprovalDeleteMouseClicked(evt);
               }
          });

          permissionName26.setText("Delete");
          permissionName26.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOReceiveCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOReceiveCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOReceiveCreateMouseClicked(evt);
               }
          });

          permissionName27.setText("Create");
          permissionName27.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOReceiveUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOReceiveUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOReceiveUpdateMouseClicked(evt);
               }
          });

          permissionName28.setText("Update");
          permissionName28.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOReceiveView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOReceiveView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOReceiveViewMouseClicked(evt);
               }
          });

          permissionName29.setText("View");
          permissionName29.setPreferredSize(new java.awt.Dimension(37, 20));

          lbPOReceiveDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbPOReceiveDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbPOReceiveDeleteMouseClicked(evt);
               }
          });

          permissionName30.setText("Delete");
          permissionName30.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSettings.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSettingsMouseClicked(evt);
               }
          });

          permissionName31.setText("Settings");
          permissionName31.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDivision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDivision.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDivisionMouseClicked(evt);
               }
          });

          permissionName32.setText("Division");
          permissionName32.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDivisionCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDivisionCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDivisionCreateMouseClicked(evt);
               }
          });

          permissionName33.setText("Create");
          permissionName33.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDivisionUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDivisionUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDivisionUpdateMouseClicked(evt);
               }
          });

          permissionName34.setText("Update");
          permissionName34.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDivisionView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDivisionView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDivisionViewMouseClicked(evt);
               }
          });

          permissionName35.setText("View");
          permissionName35.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDivisionDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDivisionDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDivisionDeleteMouseClicked(evt);
               }
          });

          permissionName36.setText("Delete");
          permissionName36.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDepartment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDepartmentMouseClicked(evt);
               }
          });

          permissionName37.setText("Department");
          permissionName37.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDepartmentCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDepartmentCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDepartmentCreateMouseClicked(evt);
               }
          });

          permissionName38.setText("Create");
          permissionName38.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDepartmentUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDepartmentUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDepartmentUpdateMouseClicked(evt);
               }
          });

          permissionName39.setText("Update");
          permissionName39.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDepartmentView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDepartmentView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDepartmentViewMouseClicked(evt);
               }
          });

          permissionName40.setText("View");
          permissionName40.setPreferredSize(new java.awt.Dimension(37, 20));

          lbDepartmentDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbDepartmentDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbDepartmentDeleteMouseClicked(evt);
               }
          });

          permissionName41.setText("Delete");
          permissionName41.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCategoryCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCategoryCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCategoryCreateMouseClicked(evt);
               }
          });

          permissionName42.setText("Delete");
          permissionName42.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName43.setText("Category");
          permissionName43.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCategoryUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCategoryUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCategoryUpdateMouseClicked(evt);
               }
          });

          lbCategoryDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCategoryDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCategoryDeleteMouseClicked(evt);
               }
          });

          lbCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCategory.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCategoryMouseClicked(evt);
               }
          });

          permissionName44.setText("Update");
          permissionName44.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName45.setText("View");
          permissionName45.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName46.setText("Create");
          permissionName46.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCategoryView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCategoryView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCategoryViewMouseClicked(evt);
               }
          });

          lbSubCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSubCategory.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSubCategoryMouseClicked(evt);
               }
          });

          permissionName47.setText("Sub Category");
          permissionName47.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName48.setText("Update");
          permissionName48.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSubCategoryView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSubCategoryView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSubCategoryViewMouseClicked(evt);
               }
          });

          permissionName49.setText("Delete");
          permissionName49.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSubCategoryUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSubCategoryUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSubCategoryUpdateMouseClicked(evt);
               }
          });

          permissionName50.setText("Create");
          permissionName50.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName51.setText("View");
          permissionName51.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSubCategoryCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSubCategoryCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSubCategoryCreateMouseClicked(evt);
               }
          });

          lbSubCategoryDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSubCategoryDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSubCategoryDeleteMouseClicked(evt);
               }
          });

          permissionName52.setText("Delete");
          permissionName52.setPreferredSize(new java.awt.Dimension(37, 20));

          lbBrandView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbBrandView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbBrandViewMouseClicked(evt);
               }
          });

          permissionName53.setText("Create");
          permissionName53.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName54.setText("View");
          permissionName54.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName55.setText("Brand");
          permissionName55.setPreferredSize(new java.awt.Dimension(37, 20));

          lbBrandCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbBrandCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbBrandCreateMouseClicked(evt);
               }
          });

          permissionName56.setText("Update");
          permissionName56.setPreferredSize(new java.awt.Dimension(37, 20));

          lbBrand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbBrand.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbBrandMouseClicked(evt);
               }
          });

          lbBrandUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbBrandUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbBrandUpdateMouseClicked(evt);
               }
          });

          lbBrandDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbBrandDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbBrandDeleteMouseClicked(evt);
               }
          });

          lbVendorUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbVendorUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbVendorUpdateMouseClicked(evt);
               }
          });

          lbVendorCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbVendorCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbVendorCreateMouseClicked(evt);
               }
          });

          permissionName57.setText("Create");
          permissionName57.setPreferredSize(new java.awt.Dimension(37, 20));

          lbVendor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbVendor.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbVendorMouseClicked(evt);
               }
          });

          permissionName58.setText("View");
          permissionName58.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName59.setText("Update");
          permissionName59.setPreferredSize(new java.awt.Dimension(37, 20));

          lbVendorDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbVendorDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbVendorDeleteMouseClicked(evt);
               }
          });

          permissionName60.setText("Delete");
          permissionName60.setPreferredSize(new java.awt.Dimension(37, 20));

          lbVendorView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbVendorView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbVendorViewMouseClicked(evt);
               }
          });

          permissionName61.setText("Vendor");
          permissionName61.setPreferredSize(new java.awt.Dimension(37, 20));

          lbAttributeCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbAttributeCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbAttributeCreateMouseClicked(evt);
               }
          });

          permissionName62.setText("View");
          permissionName62.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName63.setText("Update");
          permissionName63.setPreferredSize(new java.awt.Dimension(37, 20));

          lbAttributeDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbAttributeDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbAttributeDeleteMouseClicked(evt);
               }
          });

          lbAttributeUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbAttributeUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbAttributeUpdateMouseClicked(evt);
               }
          });

          lbAttributeView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbAttributeView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbAttributeViewMouseClicked(evt);
               }
          });

          permissionName64.setText("Attribute");
          permissionName64.setPreferredSize(new java.awt.Dimension(37, 20));

          lbAttribute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbAttribute.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbAttributeMouseClicked(evt);
               }
          });

          permissionName65.setText("Delete");
          permissionName65.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName66.setText("Create");
          permissionName66.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName67.setText("Update");
          permissionName67.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName68.setText("Delete");
          permissionName68.setPreferredSize(new java.awt.Dimension(37, 20));

          lbUomUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUomUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUomUpdateMouseClicked(evt);
               }
          });

          permissionName69.setText("Create");
          permissionName69.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName70.setText("View");
          permissionName70.setPreferredSize(new java.awt.Dimension(37, 20));

          lbUom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUom.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUomMouseClicked(evt);
               }
          });

          lbUomCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUomCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUomCreateMouseClicked(evt);
               }
          });

          lbUomDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUomDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUomDeleteMouseClicked(evt);
               }
          });

          lbUomView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUomView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUomViewMouseClicked(evt);
               }
          });

          permissionName71.setText("Uom");
          permissionName71.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCountry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCountry.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCountryMouseClicked(evt);
               }
          });

          permissionName72.setText("Country");
          permissionName72.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCountryCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCountryCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCountryCreateMouseClicked(evt);
               }
          });

          permissionName73.setText("Create");
          permissionName73.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCountryUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCountryUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCountryUpdateMouseClicked(evt);
               }
          });

          permissionName74.setText("Update");
          permissionName74.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCountryView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCountryView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCountryViewMouseClicked(evt);
               }
          });

          permissionName75.setText("View");
          permissionName75.setPreferredSize(new java.awt.Dimension(37, 20));

          lbCountryDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCountryDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCountryDeleteMouseClicked(evt);
               }
          });

          permissionName76.setText("Delete");
          permissionName76.setPreferredSize(new java.awt.Dimension(37, 20));

          lbTaxDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbTaxDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTaxDeleteMouseClicked(evt);
               }
          });

          lbTax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbTax.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTaxMouseClicked(evt);
               }
          });

          permissionName77.setText("Delete");
          permissionName77.setPreferredSize(new java.awt.Dimension(37, 20));

          lbTaxView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbTaxView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTaxViewMouseClicked(evt);
               }
          });

          permissionName78.setText("Create");
          permissionName78.setPreferredSize(new java.awt.Dimension(37, 20));

          lbTaxUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbTaxUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTaxUpdateMouseClicked(evt);
               }
          });

          permissionName79.setText("Update");
          permissionName79.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName80.setText("View");
          permissionName80.setPreferredSize(new java.awt.Dimension(37, 20));

          lbTaxCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbTaxCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTaxCreateMouseClicked(evt);
               }
          });

          permissionName81.setText("TAX");
          permissionName81.setPreferredSize(new java.awt.Dimension(37, 20));

          lbStatusDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStatusDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStatusDeleteMouseClicked(evt);
               }
          });

          permissionName82.setText("Update");
          permissionName82.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName83.setText("Delete");
          permissionName83.setPreferredSize(new java.awt.Dimension(37, 20));

          lbStatusCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStatusCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStatusCreateMouseClicked(evt);
               }
          });

          lbStatusUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStatusUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStatusUpdateMouseClicked(evt);
               }
          });

          permissionName84.setText("Status");
          permissionName84.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName85.setText("View");
          permissionName85.setPreferredSize(new java.awt.Dimension(37, 20));

          lbStatusView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStatusView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStatusViewMouseClicked(evt);
               }
          });

          permissionName86.setText("Create");
          permissionName86.setPreferredSize(new java.awt.Dimension(37, 20));

          lbStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbStatusMouseClicked(evt);
               }
          });

          lbWarehouseView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbWarehouseView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbWarehouseViewMouseClicked(evt);
               }
          });

          lbWarehouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbWarehouse.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbWarehouseMouseClicked(evt);
               }
          });

          permissionName87.setText("Warehouse");
          permissionName87.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName88.setText("Create");
          permissionName88.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName89.setText("Update");
          permissionName89.setPreferredSize(new java.awt.Dimension(37, 20));

          lbWarehouseCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbWarehouseCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbWarehouseCreateMouseClicked(evt);
               }
          });

          permissionName90.setText("View");
          permissionName90.setPreferredSize(new java.awt.Dimension(37, 20));

          lbWarehouseDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbWarehouseDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbWarehouseDeleteMouseClicked(evt);
               }
          });

          permissionName91.setText("Delete");
          permissionName91.setPreferredSize(new java.awt.Dimension(37, 20));

          lbWarehouseUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbWarehouseUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbWarehouseUpdateMouseClicked(evt);
               }
          });

          lbRange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbRange.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbRangeMouseClicked(evt);
               }
          });

          permissionName92.setText("Range");
          permissionName92.setPreferredSize(new java.awt.Dimension(37, 20));

          lbRangeCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbRangeCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbRangeCreateMouseClicked(evt);
               }
          });

          permissionName93.setText("Create");
          permissionName93.setPreferredSize(new java.awt.Dimension(37, 20));

          lbRangeUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbRangeUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbRangeUpdateMouseClicked(evt);
               }
          });

          permissionName94.setText("Update");
          permissionName94.setPreferredSize(new java.awt.Dimension(37, 20));

          lbRangeView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbRangeView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbRangeViewMouseClicked(evt);
               }
          });

          permissionName95.setText("View");
          permissionName95.setPreferredSize(new java.awt.Dimension(37, 20));

          lbRangeDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbRangeDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbRangeDeleteMouseClicked(evt);
               }
          });

          permissionName96.setText("Delete");
          permissionName96.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName97.setText("View");
          permissionName97.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSlotView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSlotView.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSlotViewMouseClicked(evt);
               }
          });

          lbSlot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSlot.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSlotMouseClicked(evt);
               }
          });

          permissionName98.setText("Create");
          permissionName98.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSlotUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSlotUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSlotUpdateMouseClicked(evt);
               }
          });

          permissionName99.setText("Slot");
          permissionName99.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSlotCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSlotCreate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSlotCreateMouseClicked(evt);
               }
          });

          permissionName100.setText("Update");
          permissionName100.setPreferredSize(new java.awt.Dimension(37, 20));

          lbSlotDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbSlotDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbSlotDeleteMouseClicked(evt);
               }
          });

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

          lbUserLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUserLogin.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUserLoginMouseClicked(evt);
               }
          });

          permissionName129.setText("User Login");
          permissionName129.setPreferredSize(new java.awt.Dimension(37, 20));

          lbUserLoginUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUserLoginUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUserLoginUpdateMouseClicked(evt);
               }
          });

          permissionName131.setText("Update");
          permissionName131.setPreferredSize(new java.awt.Dimension(37, 20));

          permissionName134.setText("User Permission");
          permissionName134.setPreferredSize(new java.awt.Dimension(37, 20));

          lbUserPermission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbUserPermission.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbUserPermissionMouseClicked(evt);
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
                                                  .addComponent(lbPOReceive)
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
                                                            .addComponent(lbPOApproval)
                                                            .addGap(12, 12, 12)
                                                            .addComponent(permissionName10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(lbProductCreate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbProductUpdate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbProductView)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbProductDelete)
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
                                                  .addComponent(lbPOApprovalCreate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOApprovalUpdate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOApprovalView)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOApprovalDelete)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelDataLayout.createSequentialGroup()
                                                  .addComponent(lbPOReceiveCreate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOReceiveUpdate)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOReceiveView)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbPOReceiveDelete)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(permissionName30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                         .addGroup(panelDataLayout.createSequentialGroup()
                              .addComponent(lbSettings)
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbDivision)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName32, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDivisionCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName33, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDivisionUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName34, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDivisionView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDivisionDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName36, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(permissionName31, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbDepartment)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName37, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDepartmentCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName38, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDepartmentUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDepartmentView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName40, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbDepartmentDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName41, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbCategory)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName43, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCategoryCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName46, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCategoryUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName44, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCategoryView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName45, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCategoryDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName42, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbSubCategory)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName47, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSubCategoryCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName50, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSubCategoryUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSubCategoryView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName51, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSubCategoryDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName49, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbBrand)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName55, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbBrandCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName53, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbBrandUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName56, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbBrandView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName54, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbBrandDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName52, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbVendor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName61, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbVendorCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName57, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbVendorUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName59, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbVendorView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName58, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbVendorDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName60, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbAttribute)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName64, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbAttributeCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName66, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbAttributeUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName63, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbAttributeView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName62, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbAttributeDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName65, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbUom)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName71, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbUomCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName69, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbUomUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName67, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbUomView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName70, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbUomDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName68, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbCountry)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName72, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCountryCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName73, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCountryUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName74, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCountryView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName75, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCountryDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName76, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbTax)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName81, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbTaxCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName78, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbTaxUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName79, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbTaxView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName80, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbTaxDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName77, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName84, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStatusCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName86, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStatusUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName82, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStatusView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName85, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStatusDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName83, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbWarehouse)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName87, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbWarehouseCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName88, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbWarehouseUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName89, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbWarehouseView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName90, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbWarehouseDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName91, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbRange)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName92, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbRangeCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName93, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbRangeUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName94, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbRangeView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName95, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbRangeDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName96, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbSlot)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName99, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSlotCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName98, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSlotUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName100, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSlotView)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName97, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSlotDelete)
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
                                        .addComponent(lbUserLogin)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName129, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95)
                                        .addComponent(lbUserLoginUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelDataLayout.createSequentialGroup()
                                        .addComponent(lbUserPermission)
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
                         .addComponent(lbProductCreate)
                         .addComponent(permissionName5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbProductUpdate)
                         .addComponent(permissionName6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbProductView)
                         .addComponent(permissionName7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbProductDelete))
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
                                             .addComponent(lbPOApproval)))
                                   .addComponent(permissionName23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPOApprovalCreate)
                                   .addComponent(permissionName24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPOApprovalUpdate)
                                   .addComponent(permissionName25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPOApprovalView)
                                   .addComponent(permissionName26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPOApprovalDelete))
                              .addGap(12, 12, 12)
                              .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(permissionName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbPOReceive)))
                         .addComponent(permissionName27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPOReceiveCreate)
                         .addComponent(permissionName28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPOReceiveUpdate)
                         .addComponent(permissionName29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPOReceiveView)
                         .addComponent(permissionName30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPOReceiveDelete))
                    .addGap(12, 12, 12)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSettings))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDivision)
                         .addComponent(permissionName33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDivisionCreate)
                         .addComponent(permissionName34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDivisionUpdate)
                         .addComponent(permissionName35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDivisionView)
                         .addComponent(permissionName36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDivisionDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDepartment)
                         .addComponent(permissionName38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDepartmentCreate)
                         .addComponent(permissionName39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDepartmentUpdate)
                         .addComponent(permissionName40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDepartmentView)
                         .addComponent(permissionName41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDepartmentDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCategory)
                         .addComponent(permissionName46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCategoryCreate)
                         .addComponent(permissionName44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCategoryUpdate)
                         .addComponent(permissionName45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCategoryView)
                         .addComponent(permissionName42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCategoryDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSubCategory)
                         .addComponent(permissionName50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSubCategoryCreate)
                         .addComponent(permissionName48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSubCategoryUpdate)
                         .addComponent(permissionName51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSubCategoryView)
                         .addComponent(permissionName49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSubCategoryDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbBrand)
                         .addComponent(permissionName53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbBrandCreate)
                         .addComponent(permissionName56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbBrandUpdate)
                         .addComponent(permissionName54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbBrandView)
                         .addComponent(permissionName52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbBrandDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbVendor)
                         .addComponent(permissionName57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbVendorCreate)
                         .addComponent(permissionName59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbVendorUpdate)
                         .addComponent(permissionName58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbVendorView)
                         .addComponent(permissionName60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbVendorDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbAttribute)
                         .addComponent(permissionName66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbAttributeCreate)
                         .addComponent(permissionName63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbAttributeUpdate)
                         .addComponent(permissionName62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbAttributeView)
                         .addComponent(permissionName65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbAttributeDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUom)
                         .addComponent(permissionName69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUomCreate)
                         .addComponent(permissionName67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUomUpdate)
                         .addComponent(permissionName70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUomView)
                         .addComponent(permissionName68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUomDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCountry)
                         .addComponent(permissionName73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCountryCreate)
                         .addComponent(permissionName74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCountryUpdate)
                         .addComponent(permissionName75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCountryView)
                         .addComponent(permissionName76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCountryDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTax)
                         .addComponent(permissionName78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTaxCreate)
                         .addComponent(permissionName79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTaxUpdate)
                         .addComponent(permissionName80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTaxView)
                         .addComponent(permissionName77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTaxDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStatus)
                         .addComponent(permissionName86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStatusCreate)
                         .addComponent(permissionName82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStatusUpdate)
                         .addComponent(permissionName85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStatusView)
                         .addComponent(permissionName83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbStatusDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWarehouse)
                         .addComponent(permissionName88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWarehouseCreate)
                         .addComponent(permissionName89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWarehouseUpdate)
                         .addComponent(permissionName90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWarehouseView)
                         .addComponent(permissionName91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWarehouseDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbRange)
                         .addComponent(permissionName93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbRangeCreate)
                         .addComponent(permissionName94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbRangeUpdate)
                         .addComponent(permissionName95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbRangeView)
                         .addComponent(permissionName96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbRangeDelete))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSlot)
                         .addComponent(permissionName98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSlotCreate)
                         .addComponent(permissionName100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSlotUpdate)
                         .addComponent(permissionName97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSlotView)
                         .addComponent(permissionName101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbSlotDelete))
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
                         .addComponent(lbUserLogin)
                         .addComponent(permissionName131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUserLoginUpdate))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbUserPermission))
                    .addContainerGap(24, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(panelData);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          lbCheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          lbCheckAll.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbCheckAllMouseClicked(evt);
               }
          });

          permissionName139.setText("All");
          permissionName139.setPreferredSize(new java.awt.Dimension(37, 20));

          btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnSaveMouseClicked(evt);
               }
          });

          objRole.setLabelName("Role *");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addComponent(lbCheckAll)
                              .addGap(12, 12, 12)
                              .addComponent(permissionName139, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addGroup(panelLayout.createSequentialGroup()
                                   .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(12, 12, 12)
                                   .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(objRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(permissionName139, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCheckAll, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
          Staff staff = new Staff(new JFrame(), true);
          staff.setVisible(true);
     }//GEN-LAST:event_btnCancelMouseClicked

     private void lbStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStockMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "stock");
     }//GEN-LAST:event_lbStockMouseClicked

     private void lbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "product");
     }//GEN-LAST:event_lbProductMouseClicked

     private void lbProductCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "productCreate");
     }//GEN-LAST:event_lbProductCreateMouseClicked

     private void lbProductUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "productUpdate");
     }//GEN-LAST:event_lbProductUpdateMouseClicked

     private void lbProductViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "productView");
     }//GEN-LAST:event_lbProductViewMouseClicked

    private void lbProductDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductDeleteMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "productDelete");
    }//GEN-LAST:event_lbProductDeleteMouseClicked

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

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
//         for (RoleHasPermission data : listRoles) {
//              System.err.println("RoleId : " + data.getRoleId() + " PermissionId : " + data.getPermissionId() + " isVisible : " + data.getIsVisible() + " "
//                   + " isCreate : " + data.getIsCreate() + " isView : " + data.getIsView() + " isUpdata : " + data.getIsUpdate() + " isDelete : " + data.getIsDelete());
//         }

         if (roleId.equals("-1")) {
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Please select Role first!");
              j.setVisible(true);
              return;
         }
         // Set cursor to loading
         setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
         btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

         JSONObject json = new JSONObject();
         json.put("roleHasPermissionRequestList", listRoles);

         System.err.println("respnse : " + json);

         new Thread(() -> {
              Response response = JavaConnection.post(JavaRoute.roleHasPermissions, json);
              System.err.println("respnse : " + response);
              try {

                   if (response.isSuccessful()) {
                        dispose();
                   }

              } catch (Exception e) {
                   System.err.println("error : " + e);
              }

         }).start();

    }//GEN-LAST:event_btnSaveMouseClicked

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

    private void lbPOApprovalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOApprovalMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POApproval");
    }//GEN-LAST:event_lbPOApprovalMouseClicked

    private void lbPOApprovalCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOApprovalCreateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POApprovalCreate");
    }//GEN-LAST:event_lbPOApprovalCreateMouseClicked

    private void lbPOApprovalUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOApprovalUpdateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POApprovalUpdate");
    }//GEN-LAST:event_lbPOApprovalUpdateMouseClicked

    private void lbPOApprovalViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOApprovalViewMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POApprovalView");
    }//GEN-LAST:event_lbPOApprovalViewMouseClicked

    private void lbPOApprovalDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOApprovalDeleteMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POApprovalDelete");
    }//GEN-LAST:event_lbPOApprovalDeleteMouseClicked

    private void lbPOReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOReceiveMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POReceive");
    }//GEN-LAST:event_lbPOReceiveMouseClicked

    private void lbPOReceiveCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOReceiveCreateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POReceiveCreate");
    }//GEN-LAST:event_lbPOReceiveCreateMouseClicked

    private void lbPOReceiveUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOReceiveUpdateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POReceiveUpdate");
    }//GEN-LAST:event_lbPOReceiveUpdateMouseClicked

    private void lbPOReceiveViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOReceiveViewMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POReceiveView");
    }//GEN-LAST:event_lbPOReceiveViewMouseClicked

    private void lbPOReceiveDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPOReceiveDeleteMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "POReceiveDelete");
    }//GEN-LAST:event_lbPOReceiveDeleteMouseClicked

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

    private void lbUserLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUserLoginMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "userLogin");
    }//GEN-LAST:event_lbUserLoginMouseClicked

    private void lbUserLoginUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUserLoginUpdateMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "userLoginUpdate");
    }//GEN-LAST:event_lbUserLoginUpdateMouseClicked

    private void lbUserPermissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUserPermissionMouseClicked
         JLabel label = (JLabel) evt.getSource();
         setAction(label, "userPermission");
    }//GEN-LAST:event_lbUserPermissionMouseClicked

     private void lbSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSettingsMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "settings");
     }//GEN-LAST:event_lbSettingsMouseClicked

     private void lbDivisionCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDivisionCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "divisionCreate");
     }//GEN-LAST:event_lbDivisionCreateMouseClicked

     private void lbDivisionUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDivisionUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "divisionUpdate");
     }//GEN-LAST:event_lbDivisionUpdateMouseClicked

     private void lbDivisionViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDivisionViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "divisionView");
     }//GEN-LAST:event_lbDivisionViewMouseClicked

     private void lbDivisionDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDivisionDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "divisionDelete");
     }//GEN-LAST:event_lbDivisionDeleteMouseClicked

     private void lbDivisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDivisionMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "division");
     }//GEN-LAST:event_lbDivisionMouseClicked

     private void lbDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDepartmentMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "department");
     }//GEN-LAST:event_lbDepartmentMouseClicked

     private void lbDepartmentCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDepartmentCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "departmentCreate");
     }//GEN-LAST:event_lbDepartmentCreateMouseClicked

     private void lbDepartmentUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDepartmentUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "departmentUpdate");
     }//GEN-LAST:event_lbDepartmentUpdateMouseClicked

     private void lbDepartmentViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDepartmentViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "departmentView");
     }//GEN-LAST:event_lbDepartmentViewMouseClicked

     private void lbDepartmentDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDepartmentDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "departmentDelete");
     }//GEN-LAST:event_lbDepartmentDeleteMouseClicked

     private void lbCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCategoryMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "category");
     }//GEN-LAST:event_lbCategoryMouseClicked

     private void lbCategoryCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCategoryCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "categoryCreate");
     }//GEN-LAST:event_lbCategoryCreateMouseClicked

     private void lbCategoryUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCategoryUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "categoryUpdate");
     }//GEN-LAST:event_lbCategoryUpdateMouseClicked

     private void lbCategoryViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCategoryViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "categoryView");
     }//GEN-LAST:event_lbCategoryViewMouseClicked

     private void lbCategoryDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCategoryDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "categoryDelete");
     }//GEN-LAST:event_lbCategoryDeleteMouseClicked

     private void lbSubCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubCategoryMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "subCategory");
     }//GEN-LAST:event_lbSubCategoryMouseClicked

     private void lbSubCategoryCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubCategoryCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "subCategoryCreate");
     }//GEN-LAST:event_lbSubCategoryCreateMouseClicked

     private void lbSubCategoryUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubCategoryUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "subCategoryUpdate");
     }//GEN-LAST:event_lbSubCategoryUpdateMouseClicked

     private void lbSubCategoryViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubCategoryViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "subCategoryView");
     }//GEN-LAST:event_lbSubCategoryViewMouseClicked

     private void lbSubCategoryDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubCategoryDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "subCategoryDelete");
     }//GEN-LAST:event_lbSubCategoryDeleteMouseClicked

     private void lbBrandCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBrandCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "brandCreate");
     }//GEN-LAST:event_lbBrandCreateMouseClicked

     private void lbBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBrandMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "brand");
     }//GEN-LAST:event_lbBrandMouseClicked

     private void lbBrandUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBrandUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "brandUpdate");
     }//GEN-LAST:event_lbBrandUpdateMouseClicked

     private void lbBrandViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBrandViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "brandView");
     }//GEN-LAST:event_lbBrandViewMouseClicked

     private void lbBrandDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBrandDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "brandDelete");
     }//GEN-LAST:event_lbBrandDeleteMouseClicked

     private void lbVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendorMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "vendor");
     }//GEN-LAST:event_lbVendorMouseClicked

     private void lbVendorCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendorCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "vendorCreate");
     }//GEN-LAST:event_lbVendorCreateMouseClicked

     private void lbVendorUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendorUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "vendorUpdate");
     }//GEN-LAST:event_lbVendorUpdateMouseClicked

     private void lbVendorViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendorViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "vendorView");
     }//GEN-LAST:event_lbVendorViewMouseClicked

     private void lbVendorDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendorDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "vendorDelete");
     }//GEN-LAST:event_lbVendorDeleteMouseClicked

     private void lbAttributeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAttributeMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "attribute");
     }//GEN-LAST:event_lbAttributeMouseClicked

     private void lbAttributeCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAttributeCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "attributeCreate");
     }//GEN-LAST:event_lbAttributeCreateMouseClicked

     private void lbAttributeUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAttributeUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "attributeUpdate");
     }//GEN-LAST:event_lbAttributeUpdateMouseClicked

     private void lbAttributeViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAttributeViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "attributeView");
     }//GEN-LAST:event_lbAttributeViewMouseClicked

     private void lbAttributeDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAttributeDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "attributeDelete");
     }//GEN-LAST:event_lbAttributeDeleteMouseClicked

     private void lbUomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUomMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "uom");
     }//GEN-LAST:event_lbUomMouseClicked

     private void lbUomCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUomCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "uomCreate");
     }//GEN-LAST:event_lbUomCreateMouseClicked

     private void lbUomUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUomUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "uomUpdate");
     }//GEN-LAST:event_lbUomUpdateMouseClicked

     private void lbUomViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUomViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "uomView");
     }//GEN-LAST:event_lbUomViewMouseClicked

     private void lbUomDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUomDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "uomDelete");
     }//GEN-LAST:event_lbUomDeleteMouseClicked

     private void lbCountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCountryMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "country");
     }//GEN-LAST:event_lbCountryMouseClicked

     private void lbCountryCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCountryCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "countryCreate");
     }//GEN-LAST:event_lbCountryCreateMouseClicked

     private void lbCountryUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCountryUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "countryUpdate");
     }//GEN-LAST:event_lbCountryUpdateMouseClicked

     private void lbCountryViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCountryViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "countryView");
     }//GEN-LAST:event_lbCountryViewMouseClicked

     private void lbCountryDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCountryDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "countryDelete");
     }//GEN-LAST:event_lbCountryDeleteMouseClicked

     private void lbTaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaxMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "tax");
     }//GEN-LAST:event_lbTaxMouseClicked

     private void lbTaxCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaxCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "taxCreate");
     }//GEN-LAST:event_lbTaxCreateMouseClicked

     private void lbTaxUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaxUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "taxUpdate");
     }//GEN-LAST:event_lbTaxUpdateMouseClicked

     private void lbTaxViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaxViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "taxView");
     }//GEN-LAST:event_lbTaxViewMouseClicked

     private void lbTaxDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaxDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "taxDelete");
     }//GEN-LAST:event_lbTaxDeleteMouseClicked

     private void lbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "status");
     }//GEN-LAST:event_lbStatusMouseClicked

     private void lbStatusCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "statusCreate");
     }//GEN-LAST:event_lbStatusCreateMouseClicked

     private void lbStatusUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "statusUpdate");
     }//GEN-LAST:event_lbStatusUpdateMouseClicked

     private void lbStatusViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "statusView");
     }//GEN-LAST:event_lbStatusViewMouseClicked

     private void lbStatusDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "statusDelete");
     }//GEN-LAST:event_lbStatusDeleteMouseClicked

     private void lbWarehouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbWarehouseMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "warehouse");
     }//GEN-LAST:event_lbWarehouseMouseClicked

     private void lbWarehouseCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbWarehouseCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "warehouseCreate");
     }//GEN-LAST:event_lbWarehouseCreateMouseClicked

     private void lbWarehouseUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbWarehouseUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "warehouseUpdate");
     }//GEN-LAST:event_lbWarehouseUpdateMouseClicked

     private void lbWarehouseViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbWarehouseViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "warehouseView");
     }//GEN-LAST:event_lbWarehouseViewMouseClicked

     private void lbWarehouseDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbWarehouseDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "warehouseDelete");
     }//GEN-LAST:event_lbWarehouseDeleteMouseClicked

     private void lbRangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRangeMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "range");
     }//GEN-LAST:event_lbRangeMouseClicked

     private void lbRangeCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRangeCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "rangeCreate");
     }//GEN-LAST:event_lbRangeCreateMouseClicked

     private void lbRangeUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRangeUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "rangeUpdate");
     }//GEN-LAST:event_lbRangeUpdateMouseClicked

     private void lbRangeViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRangeViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "rangeView");
     }//GEN-LAST:event_lbRangeViewMouseClicked

     private void lbRangeDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRangeDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "rangeDelete");
     }//GEN-LAST:event_lbRangeDeleteMouseClicked

     private void lbSlotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSlotMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "slot");
     }//GEN-LAST:event_lbSlotMouseClicked

     private void lbSlotCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSlotCreateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "slotCreate");
     }//GEN-LAST:event_lbSlotCreateMouseClicked

     private void lbSlotUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSlotUpdateMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "slotUpdate");
     }//GEN-LAST:event_lbSlotUpdateMouseClicked

     private void lbSlotViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSlotViewMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "slotView");
     }//GEN-LAST:event_lbSlotViewMouseClicked

     private void lbSlotDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSlotDeleteMouseClicked
          JLabel label = (JLabel) evt.getSource();
          setAction(label, "slotDelete");
     }//GEN-LAST:event_lbSlotDeleteMouseClicked

     private void lbCheckAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCheckAllMouseClicked

          if (roleId.equals("-1")) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("Please select Role first!");
               j.setVisible(true);
               return;
          }

          JLabel label = (JLabel) evt.getSource();
          isAll = !isAll;
          listRoles.clear(); // clear list
          if (isAll) { // checked box
               label.setIcon(new ImageIcon(getClass().getResource("/icon/checked.png")));
               getPermissionByRole("1"); // get all role has permission

          } else { // uncheck box
               label.setIcon(new ImageIcon(getClass().getResource("/icon/check.png")));
               unCheckBox(); // clear all box
               listRoles.clear(); // clear list
          }
     }//GEN-LAST:event_lbCheckAllMouseClicked

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
                    // note : permissionId = 11 and parentId = 5 from database table pos_permission
                    setRoleHasPermission(
                         isPO,
                         11,
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
                    // note : permissionId = 12 and parentId = 11 from database table pos_permission
                    setRoleHasPermission(
                         isPORequest,
                         12,
                         11,
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
                         11,
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
                         11,
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
                    // note : permissionId = 10 and parentId = 11 from database table pos_permission
                    setRoleHasPermission(
                         isPOReceive,
                         10,
                         5,
                         isPOReceiveCreate,
                         isPOReceiveView,
                         isPOReceiveUpdate,
                         isPOReceiveDelete);
               }
               case "POReceiveCreate" -> {
                    isPOReceiveCreate = !isPOReceiveCreate;
                    updateIcon(label, isPOReceiveCreate);
                    // note : permissionId = 10 from database table pos_permission
                    setCreate(10, isPOReceiveCreate);
               }
               case "POReceiveUpdate" -> {
                    isPOReceiveUpdate = !isPOReceiveUpdate;
                    updateIcon(label, isPOReceiveUpdate);
                    // note : permissionId = 10 from database table pos_permission
                    setUpdate(10, isPOReceiveUpdate);
               }
               case "POReceiveView" -> {
                    isPOReceiveView = !isPOReceiveView;
                    updateIcon(label, isPOReceiveView);
                    // note : permissionId = 10 from database table pos_permission
                    setView(10, isPOReceiveView);
               }
               case "POReceiveDelete" -> {
                    isPOReceiveDelete = !isPOReceiveDelete;
                    updateIcon(label, isPOReceiveDelete);
                    // note : permissionId = 10 from database table pos_permission
                    setDelete(10, isPOReceiveDelete);
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
                    setCreate(33, isSfaffInfoCreate);
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
                    setUpdate(33, isSfaffInfoUpdate);
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
                    setUpdate(34, isUserLoginUpdate);
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

               case "settings" -> {
                    isSettings = !isSettings;
                    updateIcon(label, isSettings);
                    // note : permissionId = 6 and parentId = 0 from database table pos_permission
                    setRoleHasPermission(
                         isSettings,
                         6,
                         0,
                         false,
                         false,
                         false,
                         false);
               }
               // =============== blog division ================
               case "division" -> {
                    isDivision = !isDivision;

                    updateIcon(label, isDivision);
                    // note : permissionId = 15 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isDivision,
                         15,
                         6,
                         isDivisionCreate,
                         isDivisionView,
                         isDivisionUpdate,
                         isDivisionoDelete);
               }
               case "divisionCreate" -> {
                    isDivisionCreate = !isDivisionCreate;
                    updateIcon(label, isDivisionCreate);
                    // note : permissionId = 15 from database table pos_permission
                    setCreate(15, isDivisionCreate);
               }
               case "divisionView" -> {
                    isDivisionView = !isDivisionView;
                    updateIcon(label, isDivisionView);
                    // note : permissionId = 15 from database table pos_permission
                    setView(15, isDivisionView);
               }
               case "divisionUpdate" -> {
                    isDivisionUpdate = !isDivisionUpdate;
                    updateIcon(label, isDivisionUpdate);
                    // note : permissionId = 15 from database table pos_permission
                    setUpdate(15, isDivisionUpdate);
               }
               case "divisionDelete" -> {
                    isDivisionoDelete = !isDivisionoDelete;
                    updateIcon(label, isDivisionoDelete);
                    // note : permissionId = 15 from database table pos_permission
                    setDelete(15, isDivisionoDelete);
               }
               // =============== end division ================

               // =============== blog department ================
               case "department" -> {
                    isDepartment = !isDepartment;

                    updateIcon(label, isDepartment);
                    // note : permissionId = 16 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isDepartment,
                         16,
                         6,
                         isDepartmentCreate,
                         isDepartmentView,
                         isDepartmentUpdate,
                         isDepartmentDelete);
               }
               case "departmentCreate" -> {
                    isDepartmentCreate = !isDepartmentCreate;
                    updateIcon(label, isDepartmentCreate);
                    // note : permissionId = 16 from database table pos_permission
                    setCreate(16, isDepartmentCreate);
               }
               case "departmentView" -> {
                    isDepartmentView = !isDepartmentView;
                    updateIcon(label, isDepartmentView);
                    // note : permissionId = 16 from database table pos_permission
                    setView(16, isDepartmentView);
               }
               case "departmentUpdate" -> {
                    isDepartmentUpdate = !isDepartmentUpdate;
                    updateIcon(label, isDepartmentUpdate);
                    // note : permissionId = 16 from database table pos_permission
                    setUpdate(16, isDepartmentUpdate);
               }
               case "departmentDelete" -> {
                    isDepartmentDelete = !isDepartmentDelete;
                    updateIcon(label, isDepartmentDelete);
                    // note : permissionId = 16 from database table pos_permission
                    setDelete(16, isDepartmentDelete);
               }
               // =============== end department ================

               // =============== blog category ================
               case "category" -> {
                    isCategory = !isCategory;

                    updateIcon(label, isCategory);
                    // note : permissionId = 17 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isCategory,
                         17,
                         6,
                         isCategoryCreate,
                         isCategoryView,
                         isCategoryUpdate,
                         isCategoryDelete);
               }
               case "categoryCreate" -> {
                    isCategoryCreate = !isCategoryCreate;
                    updateIcon(label, isCategoryCreate);
                    // note : permissionId = 17 from database table pos_permission
                    setCreate(17, isCategoryCreate);
               }
               case "categoryView" -> {
                    isCategoryView = !isCategoryView;
                    updateIcon(label, isCategoryView);
                    // note : permissionId = 16 from database table pos_permission
                    setView(17, isCategoryView);
               }
               case "categoryUpdate" -> {
                    isCategoryUpdate = !isCategoryUpdate;
                    updateIcon(label, isCategoryUpdate);
                    // note : permissionId = 17 from database table pos_permission
                    setUpdate(17, isCategoryUpdate);
               }
               case "categoryDelete" -> {
                    isCategoryDelete = !isCategoryDelete;
                    updateIcon(label, isCategoryDelete);
                    // note : permissionId = 16 from database table pos_permission
                    setDelete(17, isCategoryDelete);
               }
               // =============== end category ================

               // =============== blog SubCategory ================
               case "subCategory" -> {
                    isSubCategory = !isSubCategory;

                    updateIcon(label, isSubCategory);
                    // note : permissionId = 18 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isSubCategory,
                         18,
                         6,
                         isSubCategoryCreate,
                         isSubCategoryView,
                         isSubCategoryUpdate,
                         isSubCategoryDelete);
               }
               case "subCategoryCreate" -> {
                    isSubCategoryCreate = !isSubCategoryCreate;
                    updateIcon(label, isSubCategoryCreate);
                    // note : permissionId = 18 from database table pos_permission
                    setCreate(18, isSubCategoryCreate);
               }
               case "subCategoryView" -> {
                    isSubCategoryView = !isSubCategoryView;
                    updateIcon(label, isSubCategoryView);
                    // note : permissionId = 18 from database table pos_permission
                    setView(18, isSubCategoryView);
               }
               case "subCategoryUpdate" -> {
                    isSubCategoryUpdate = !isSubCategoryUpdate;
                    updateIcon(label, isSubCategoryUpdate);
                    // note : permissionId = 18 from database table pos_permission
                    setUpdate(18, isSubCategoryUpdate);
               }
               case "subCategoryDelete" -> {
                    isSubCategoryDelete = !isSubCategoryDelete;
                    updateIcon(label, isSubCategoryDelete);
                    // note : permissionId = 18 from database table pos_permission
                    setDelete(18, isSubCategoryDelete);
               }
               // =============== end SubCategory ================

               // =============== blog brand ================
               case "brand" -> {
                    isBrand = !isBrand;

                    updateIcon(label, isBrand);
                    // note : permissionId = 19 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isBrand,
                         19,
                         6,
                         isBrandCreate,
                         isBrandView,
                         isBrandUpdate,
                         isBrandDelete);
               }
               case "brandCreate" -> {
                    isBrandCreate = !isBrandCreate;
                    updateIcon(label, isBrandCreate);
                    // note : permissionId = 19 from database table pos_permission
                    setCreate(19, isBrandCreate);
               }
               case "brandView" -> {
                    isBrandView = !isBrandView;
                    updateIcon(label, isBrandView);
                    // note : permissionId = 19 from database table pos_permission
                    setView(19, isBrandView);
               }
               case "brandUpdate" -> {
                    isBrandUpdate = !isBrandUpdate;
                    updateIcon(label, isBrandUpdate);
                    // note : permissionId = 19 from database table pos_permission
                    setUpdate(19, isBrandUpdate);
               }
               case "brandDelete" -> {
                    isBrandDelete = !isBrandDelete;
                    updateIcon(label, isBrandDelete);
                    // note : permissionId = 19 from database table pos_permission
                    setDelete(19, isBrandDelete);
               }
               // =============== end brand ================

               // =============== blog vendor ================
               case "vendor" -> {
                    isVendor = !isVendor;

                    updateIcon(label, isVendor);
                    // note : permissionId = 20 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isVendor,
                         20,
                         6,
                         isVendorCreate,
                         isVendorView,
                         isVendorUpdate,
                         isVendorDelete);
               }
               case "vendorCreate" -> {
                    isVendorCreate = !isVendorCreate;
                    updateIcon(label, isVendorCreate);
                    // note : permissionId = 19 from database table pos_permission
                    setCreate(20, isVendorCreate);
               }
               case "vendorView" -> {
                    isVendorView = !isVendorView;
                    updateIcon(label, isVendorView);
                    // note : permissionId = 20 from database table pos_permission
                    setView(20, isVendorView);
               }
               case "vendorUpdate" -> {
                    isVendorUpdate = !isVendorUpdate;
                    updateIcon(label, isVendorUpdate);
                    // note : permissionId = 20 from database table pos_permission
                    setUpdate(20, isVendorUpdate);
               }
               case "vendorDelete" -> {
                    isVendorDelete = !isVendorDelete;
                    updateIcon(label, isVendorDelete);
                    // note : permissionId = 20 from database table pos_permission
                    setDelete(20, isVendorDelete);
               }
               // =============== end vendor ================

               // =============== blog attribute ================
               case "attribute" -> {
                    isAttribute = !isAttribute;

                    updateIcon(label, isAttribute);
                    // note : permissionId = 21 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isAttribute,
                         21,
                         6,
                         isAttributeCreate,
                         isAttributeView,
                         isAttributeUpdate,
                         isAttributeDelete);
               }
               case "attributeCreate" -> {
                    isAttributeCreate = !isAttributeCreate;
                    updateIcon(label, isAttributeCreate);
                    // note : permissionId = 21 from database table pos_permission
                    setCreate(21, isAttributeCreate);
               }
               case "attributeView" -> {
                    isAttributeView = !isAttributeView;
                    updateIcon(label, isAttributeView);
                    // note : permissionId = 21 from database table pos_permission
                    setView(21, isAttributeView);
               }
               case "attributeUpdate" -> {
                    isAttributeUpdate = !isAttributeUpdate;
                    updateIcon(label, isAttributeUpdate);
                    // note : permissionId = 21 from database table pos_permission
                    setUpdate(21, isAttributeUpdate);
               }
               case "attributeDelete" -> {
                    isAttributeDelete = !isAttributeDelete;
                    updateIcon(label, isAttributeDelete);
                    // note : permissionId = 21 from database table pos_permission
                    setDelete(21, isAttributeDelete);
               }
               // =============== end attribute ================

               // =============== blog uom ================
               case "uom" -> {
                    isUom = !isUom;

                    updateIcon(label, isUom);
                    // note : permissionId = 22 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isUom,
                         22,
                         6,
                         isUomCreate,
                         isUomView,
                         isUomUpdate,
                         isUomDelete);
               }
               case "uomCreate" -> {
                    isUomCreate = !isUomCreate;
                    updateIcon(label, isUomCreate);
                    // note : permissionId = 22 from database table pos_permission
                    setCreate(22, isUomCreate);
               }
               case "uomView" -> {
                    isUomView = !isUomView;
                    updateIcon(label, isUomView);
                    // note : permissionId = 22 from database table pos_permission
                    setView(22, isUomView);
               }
               case "uomUpdate" -> {
                    isUomUpdate = !isUomUpdate;
                    updateIcon(label, isUomUpdate);
                    // note : permissionId = 22 from database table pos_permission
                    setUpdate(22, isUomUpdate);
               }
               case "uomDelete" -> {
                    isUomDelete = !isUomDelete;
                    updateIcon(label, isUomDelete);
                    // note : permissionId = 22 from database table pos_permission
                    setDelete(22, isUomDelete);
               }
               // =============== end uom ================

               // =============== blog country ================
               case "country" -> {
                    isCountry = !isCountry;

                    updateIcon(label, isCountry);
                    // note : permissionId = 23 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isCountry,
                         23,
                         6,
                         isCountryCreate,
                         isCountryView,
                         isCountryUpdate,
                         isCountryDelete);
               }
               case "countryCreate" -> {
                    isCountryCreate = !isCountryCreate;
                    updateIcon(label, isCountryCreate);
                    // note : permissionId = 23 from database table pos_permission
                    setCreate(23, isCountryCreate);
               }
               case "countryView" -> {
                    isCountryView = !isCountryView;
                    updateIcon(label, isCountryView);
                    // note : permissionId = 23 from database table pos_permission
                    setView(23, isCountryView);
               }
               case "countryUpdate" -> {
                    isCountryUpdate = !isCountryUpdate;
                    updateIcon(label, isCountryUpdate);
                    // note : permissionId = 23 from database table pos_permission
                    setUpdate(23, isCountryUpdate);
               }
               case "countryDelete" -> {
                    isCountryDelete = !isCountryDelete;
                    updateIcon(label, isCountryDelete);
                    // note : permissionId = 23 from database table pos_permission
                    setDelete(23, isCountryDelete);
               }
               // =============== end country ================

               // =============== blog tax ================
               case "tax" -> {
                    isTax = !isTax;

                    updateIcon(label, isTax);
                    // note : permissionId = 24 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isTax,
                         24,
                         6,
                         isTaxCreate,
                         isTaxView,
                         isTaxUpdate,
                         isTaxDelete);
               }
               case "taxCreate" -> {
                    isTaxCreate = !isTaxCreate;
                    updateIcon(label, isTaxCreate);
                    // note : permissionId = 24 from database table pos_permission
                    setCreate(24, isTaxCreate);
               }
               case "taxView" -> {
                    isTaxView = !isTaxView;
                    updateIcon(label, isTaxView);
                    // note : permissionId = 24 from database table pos_permission
                    setView(24, isTaxView);
               }
               case "taxUpdate" -> {
                    isTaxUpdate = !isTaxUpdate;
                    updateIcon(label, isTaxUpdate);
                    // note : permissionId = 24 from database table pos_permission
                    setUpdate(24, isTaxUpdate);
               }
               case "taxDelete" -> {
                    isTaxDelete = !isTaxDelete;
                    updateIcon(label, isTaxDelete);
                    // note : permissionId = 24 from database table pos_permission
                    setDelete(24, isTaxDelete);
               }
               // =============== end tax ================

               // =============== blog status ================
               case "status" -> {
                    isStatus = !isStatus;

                    updateIcon(label, isStatus);
                    // note : permissionId = 25 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isStatus,
                         25,
                         6,
                         isStatusCreate,
                         isStatusView,
                         isStatusUpdate,
                         isStatusDelete);
               }
               case "statusCreate" -> {
                    isStatusCreate = !isStatusCreate;
                    updateIcon(label, isStatusCreate);
                    // note : permissionId = 25 from database table pos_permission
                    setCreate(25, isStatusCreate);
               }
               case "statusView" -> {
                    isStatusView = !isStatusView;
                    updateIcon(label, isStatusView);
                    // note : permissionId = 25 from database table pos_permission
                    setView(25, isStatusView);
               }
               case "statusUpdate" -> {
                    isStatusUpdate = !isStatusUpdate;
                    updateIcon(label, isStatusUpdate);
                    // note : permissionId = 25 from database table pos_permission
                    setUpdate(25, isStatusUpdate);
               }
               case "statusDelete" -> {
                    isStatusDelete = !isStatusDelete;
                    updateIcon(label, isStatusDelete);
                    // note : permissionId = 25 from database table pos_permission
                    setDelete(25, isStatusDelete);
               }
               // =============== end status ================

               // =============== blog warehouse ================
               case "warehouse" -> {
                    isWarehouse = !isWarehouse;

                    updateIcon(label, isWarehouse);
                    // note : permissionId = 26 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isWarehouse,
                         26,
                         6,
                         isWarehouseCreate,
                         isWarehouseView,
                         isWarehouseUpdate,
                         isWarehouseDelete);
               }
               case "warehouseCreate" -> {
                    isWarehouseCreate = !isWarehouseCreate;
                    updateIcon(label, isWarehouseCreate);
                    // note : permissionId = 26 from database table pos_permission
                    setCreate(26, isWarehouseCreate);
               }
               case "warehouseView" -> {
                    isWarehouseView = !isWarehouseView;
                    updateIcon(label, isWarehouseView);
                    // note : permissionId = 26 from database table pos_permission
                    setView(26, isWarehouseView);
               }
               case "warehouseUpdate" -> {
                    isWarehouseUpdate = !isWarehouseUpdate;
                    updateIcon(label, isWarehouseUpdate);
                    // note : permissionId = 26 from database table pos_permission
                    setUpdate(26, isWarehouseUpdate);
               }
               case "warehouseDelete" -> {
                    isWarehouseDelete = !isWarehouseDelete;
                    updateIcon(label, isWarehouseDelete);
                    // note : permissionId = 26 from database table pos_permission
                    setDelete(26, isWarehouseDelete);
               }
               // =============== end warehouse ================

               // =============== blog range ================
               case "range" -> {
                    isRange = !isRange;

                    updateIcon(label, isRange);
                    // note : permissionId = 27 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isRange,
                         27,
                         6,
                         isRangeCreate,
                         isRangeView,
                         isRangeUpdate,
                         isRangeDelete);
               }
               case "rangeCreate" -> {
                    isRangeCreate = !isRangeCreate;
                    updateIcon(label, isRangeCreate);
                    // note : permissionId = 27 from database table pos_permission
                    setCreate(27, isRangeCreate);
               }
               case "rangeView" -> {
                    isRangeView = !isRangeView;
                    updateIcon(label, isRangeView);
                    // note : permissionId = 27 from database table pos_permission
                    setView(27, isRangeView);
               }
               case "rangeUpdate" -> {
                    isRangeUpdate = !isRangeUpdate;
                    updateIcon(label, isRangeUpdate);
                    // note : permissionId = 27 from database table pos_permission
                    setUpdate(27, isRangeUpdate);
               }
               case "rangeDelete" -> {
                    isRangeDelete = !isRangeDelete;
                    updateIcon(label, isRangeDelete);
                    // note : permissionId = 27 from database table pos_permission
                    setDelete(27, isRangeDelete);
               }
               // =============== end range ================

               // =============== blog slot ================
               case "slot" -> {
                    isSlot = !isSlot;

                    updateIcon(label, isSlot);
                    // note : permissionId = 28 and parentId = 6 from database table pos_permission
                    setRoleHasPermission(
                         isSlot,
                         28,
                         6,
                         isSlotCreate,
                         isSlotView,
                         isSlotUpdate,
                         isSlotDelete);
               }
               case "slotCreate" -> {
                    isSlotCreate = !isSlotCreate;
                    updateIcon(label, isSlotCreate);
                    // note : permissionId = 28 from database table pos_permission
                    setCreate(28, isSlotCreate);
               }
               case "slotView" -> {
                    isSlotView = !isSlotView;
                    updateIcon(label, isSlotView);
                    // note : permissionId = 28 from database table pos_permission
                    setView(28, isSlotView);
               }
               case "slotUpdate" -> {
                    isSlotUpdate = !isSlotUpdate;
                    updateIcon(label, isSlotUpdate);
                    // note : permissionId = 28 from database table pos_permission
                    setUpdate(28, isSlotUpdate);
               }
               case "slotDelete" -> {
                    isSlotDelete = !isSlotDelete;
                    updateIcon(label, isSlotDelete);
                    // note : permissionId = 28 from database table pos_permission
                    setDelete(28, isSlotDelete);
               }
               // =============== end slot ================

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

     
       private boolean isStock = false;
     private boolean isAll = false;
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

     // ========= user permission ========
     private boolean isUserLogin = false;
     private boolean isUserLoginUpdate = false;
     private boolean isUserPermission = false;
     // ========= end user permission =======

     private boolean isSettings = false;
     //========= division =============
     private boolean isDivision = false;
     private boolean isDivisionCreate = false;
     private boolean isDivisionView = false;
     private boolean isDivisionUpdate = false;
     private boolean isDivisionoDelete = false;
     //========= end division =============

     //========= department =============
     private boolean isDepartment = false;
     private boolean isDepartmentCreate = false;
     private boolean isDepartmentView = false;
     private boolean isDepartmentUpdate = false;
     private boolean isDepartmentDelete = false;
     //========= end department =============

     //========= category =============
     private boolean isCategory = false;
     private boolean isCategoryCreate = false;
     private boolean isCategoryView = false;
     private boolean isCategoryUpdate = false;
     private boolean isCategoryDelete = false;
     //========= end category =============

     //========= subCategory =============
     private boolean isSubCategory = false;
     private boolean isSubCategoryCreate = false;
     private boolean isSubCategoryView = false;
     private boolean isSubCategoryUpdate = false;
     private boolean isSubCategoryDelete = false;
     //========= end subCategory =============

     //========= brand =============
     private boolean isBrand = false;
     private boolean isBrandCreate = false;
     private boolean isBrandView = false;
     private boolean isBrandUpdate = false;
     private boolean isBrandDelete = false;
     //========= end brand =============

     //========= vendor =============
     private boolean isVendor = false;
     private boolean isVendorCreate = false;
     private boolean isVendorView = false;
     private boolean isVendorUpdate = false;
     private boolean isVendorDelete = false;
     //========= end vendor =============

     //========= attribute =============
     private boolean isAttribute = false;
     private boolean isAttributeCreate = false;
     private boolean isAttributeView = false;
     private boolean isAttributeUpdate = false;
     private boolean isAttributeDelete = false;
     //========= end attribute =============

     //========= uom =============
     private boolean isUom = false;
     private boolean isUomCreate = false;
     private boolean isUomView = false;
     private boolean isUomUpdate = false;
     private boolean isUomDelete = false;
     //========= end uom =============

     //========= country =============
     private boolean isCountry = false;
     private boolean isCountryCreate = false;
     private boolean isCountryView = false;
     private boolean isCountryUpdate = false;
     private boolean isCountryDelete = false;
     //========= end country =============

     //========= tax =============
     private boolean isTax = false;
     private boolean isTaxCreate = false;
     private boolean isTaxView = false;
     private boolean isTaxUpdate = false;
     private boolean isTaxDelete = false;
     //========= end tax =============

     //========= status =============
     private boolean isStatus = false;
     private boolean isStatusCreate = false;
     private boolean isStatusView = false;
     private boolean isStatusUpdate = false;
     private boolean isStatusDelete = false;
     //========= end status =============

     //========= warehouse =============
     private boolean isWarehouse = false;
     private boolean isWarehouseCreate = false;
     private boolean isWarehouseView = false;
     private boolean isWarehouseUpdate = false;
     private boolean isWarehouseDelete = false;
     //========= end warehouse =============

     //========= range =============
     private boolean isRange = false;
     private boolean isRangeCreate = false;
     private boolean isRangeView = false;
     private boolean isRangeUpdate = false;
     private boolean isRangeDelete = false;
     //========= end range =============

     //========= slot =============
     private boolean isSlot = false;
     private boolean isSlotCreate = false;
     private boolean isSlotView = false;
     private boolean isSlotUpdate = false;
     private boolean isSlotDelete = false;
     //========= end slot =============
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Button.Button btnCancel;
     private javax.swing.JLabel btnCheck15;
     private javax.swing.JLabel btnCheck16;
     private javax.swing.JLabel btnCheck17;
     private javax.swing.JLabel btnCheck18;
     private ButtonPackage.ButtonSave btnSave;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JLabel lbAttribute;
     private javax.swing.JLabel lbAttributeCreate;
     private javax.swing.JLabel lbAttributeDelete;
     private javax.swing.JLabel lbAttributeUpdate;
     private javax.swing.JLabel lbAttributeView;
     private javax.swing.JLabel lbBrand;
     private javax.swing.JLabel lbBrandCreate;
     private javax.swing.JLabel lbBrandDelete;
     private javax.swing.JLabel lbBrandUpdate;
     private javax.swing.JLabel lbBrandView;
     private javax.swing.JLabel lbCategory;
     private javax.swing.JLabel lbCategoryCreate;
     private javax.swing.JLabel lbCategoryDelete;
     private javax.swing.JLabel lbCategoryUpdate;
     private javax.swing.JLabel lbCategoryView;
     private javax.swing.JLabel lbCheckAll;
     private javax.swing.JLabel lbCountry;
     private javax.swing.JLabel lbCountryCreate;
     private javax.swing.JLabel lbCountryDelete;
     private javax.swing.JLabel lbCountryUpdate;
     private javax.swing.JLabel lbCountryView;
     private javax.swing.JLabel lbDepartment;
     private javax.swing.JLabel lbDepartmentCreate;
     private javax.swing.JLabel lbDepartmentDelete;
     private javax.swing.JLabel lbDepartmentUpdate;
     private javax.swing.JLabel lbDepartmentView;
     private javax.swing.JLabel lbDivision;
     private javax.swing.JLabel lbDivisionCreate;
     private javax.swing.JLabel lbDivisionDelete;
     private javax.swing.JLabel lbDivisionUpdate;
     private javax.swing.JLabel lbDivisionView;
     private javax.swing.JLabel lbPO;
     private javax.swing.JLabel lbPOApproval;
     private javax.swing.JLabel lbPOApprovalCreate;
     private javax.swing.JLabel lbPOApprovalDelete;
     private javax.swing.JLabel lbPOApprovalUpdate;
     private javax.swing.JLabel lbPOApprovalView;
     private javax.swing.JLabel lbPOCheck;
     private javax.swing.JLabel lbPOCheckCreate;
     private javax.swing.JLabel lbPOCheckDelete;
     private javax.swing.JLabel lbPOCheckUpdate;
     private javax.swing.JLabel lbPOCheckView;
     private javax.swing.JLabel lbPOCreate;
     private javax.swing.JLabel lbPODelete;
     private javax.swing.JLabel lbPOReceive;
     private javax.swing.JLabel lbPOReceiveCreate;
     private javax.swing.JLabel lbPOReceiveDelete;
     private javax.swing.JLabel lbPOReceiveUpdate;
     private javax.swing.JLabel lbPOReceiveView;
     private javax.swing.JLabel lbPORequest;
     private javax.swing.JLabel lbPOUpdate;
     private javax.swing.JLabel lbPOView;
     private javax.swing.JLabel lbProduct;
     private javax.swing.JLabel lbProductCreate;
     private javax.swing.JLabel lbProductDelete;
     private javax.swing.JLabel lbProductUpdate;
     private javax.swing.JLabel lbProductView;
     private javax.swing.JLabel lbRange;
     private javax.swing.JLabel lbRangeCreate;
     private javax.swing.JLabel lbRangeDelete;
     private javax.swing.JLabel lbRangeUpdate;
     private javax.swing.JLabel lbRangeView;
     private javax.swing.JLabel lbReporting;
     private javax.swing.JLabel lbReportingInventory;
     private javax.swing.JLabel lbReportingPO;
     private javax.swing.JLabel lbReportingPurchaseReceive;
     private javax.swing.JLabel lbReportingSale;
     private javax.swing.JLabel lbSettings;
     private javax.swing.JLabel lbSlot;
     private javax.swing.JLabel lbSlotCreate;
     private javax.swing.JLabel lbSlotDelete;
     private javax.swing.JLabel lbSlotUpdate;
     private javax.swing.JLabel lbSlotView;
     private javax.swing.JLabel lbStaff;
     private javax.swing.JLabel lbStaffCreate;
     private javax.swing.JLabel lbStaffInfo;
     private javax.swing.JLabel lbStatus;
     private javax.swing.JLabel lbStatusCreate;
     private javax.swing.JLabel lbStatusDelete;
     private javax.swing.JLabel lbStatusUpdate;
     private javax.swing.JLabel lbStatusView;
     private javax.swing.JLabel lbStock;
     private javax.swing.JLabel lbSubCategory;
     private javax.swing.JLabel lbSubCategoryCreate;
     private javax.swing.JLabel lbSubCategoryDelete;
     private javax.swing.JLabel lbSubCategoryUpdate;
     private javax.swing.JLabel lbSubCategoryView;
     private javax.swing.JLabel lbTax;
     private javax.swing.JLabel lbTaxCreate;
     private javax.swing.JLabel lbTaxDelete;
     private javax.swing.JLabel lbTaxUpdate;
     private javax.swing.JLabel lbTaxView;
     private javax.swing.JLabel lbUom;
     private javax.swing.JLabel lbUomCreate;
     private javax.swing.JLabel lbUomDelete;
     private javax.swing.JLabel lbUomUpdate;
     private javax.swing.JLabel lbUomView;
     private javax.swing.JLabel lbUserLogin;
     private javax.swing.JLabel lbUserLoginUpdate;
     private javax.swing.JLabel lbUserPermission;
     private javax.swing.JLabel lbVendor;
     private javax.swing.JLabel lbVendorCreate;
     private javax.swing.JLabel lbVendorDelete;
     private javax.swing.JLabel lbVendorUpdate;
     private javax.swing.JLabel lbVendorView;
     private javax.swing.JLabel lbWarehouse;
     private javax.swing.JLabel lbWarehouseCreate;
     private javax.swing.JLabel lbWarehouseDelete;
     private javax.swing.JLabel lbWarehouseUpdate;
     private javax.swing.JLabel lbWarehouseView;
     private javax.swing.JLabel lbstaffDelete;
     private javax.swing.JLabel lbstaffUpdate;
     private javax.swing.JLabel lbstaffView;
     private FormComponent.combobox.JavaCombobox objRole;
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
     // End of variables declaration//GEN-END:variables
}
