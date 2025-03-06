package feature.company_profile.business.controller;

import Constant.ErrorDetail;
import Constant.ErrorResponse;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import FormComponent.JavaTextField;
import FormComponent.combobox.JavaCombobox;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.business.model.BusinessModel;
import feature.company_profile.business.view.BusinessCreate;
import feature.company_profile.business.view.BusinessView;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import main.main_province.MainProvince;
import main.main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class BusinessCreateController extends MainProvince {

     // variable
     private BusinessCreate businessCreate;
     private BusinessViewController businessController;
     private JPanel panel;
     private BusinessView businessView;
     private BusinessModel.BusinessModelDetail detail;

     // variable form
     private JavaTextField objCustomerName;
     private JavaTextField objCompanyName;
     private JavaTextField objPhoneNumber;
     private JavaTextField objEmail;
     private JavaTextField objVatNumber;
     private JavaTextField objHome;
     private JavaTextField objStreet;

     public BusinessCreateController(
          BusinessCreate businessCreate,
          JavaCombobox objProvince,
          JavaCombobox objDistrict,
          JavaCombobox objCommune,
          JavaCombobox objVillage
     ) {
          super(objProvince, objDistrict, objCommune, objVillage);

          this.businessCreate = businessCreate;
          this.panel = businessCreate.getPanel();
          this.objCustomerName = businessCreate.getObjCustomerName();
          this.objCompanyName = businessCreate.getObjCompany();
          this.objPhoneNumber = businessCreate.getObjPhoneNumber();
          this.objEmail = businessCreate.getObjEmail();
          this.objVatNumber = businessCreate.getObjVatNumber();
          this.objHome = businessCreate.getObjHome();
          this.objStreet = businessCreate.getObjStreet();
     }

     public void create() {

          boolean isCheck = JavaValidation.checkValidation(panel);

          if (isCheck) {
               JSONObject json = new JSONObject();
               json.put("customerName", objCustomerName.getValueTextField());
               json.put("companyName", objCompanyName.getValueTextField());
               json.put("phoneNumber", objPhoneNumber.getValueTextField().replace(" ", ""));
               json.put("email", objEmail.getValueTextField());
               json.put("vatNumber", objVatNumber.getValueTextField());
               json.put("home", objHome.getValueTextField());
               json.put("street", objStreet.getValueTextField());
               json.put("province", objProvince.getSelectedItem());
               json.put("district", objDistrict.getSelectedItem());
               json.put("commune", objCommune.getSelectedItem());
               json.put("village", objVillage.getSelectedItem());
               json.put("createdBy", JavaConstant.cashierId);

               Response response = null;

               if (detail == null) { // add new
                    response = JavaConnection.post(JavaRoute.companyProfile + "/business", json);
               } else { // update
                    response = JavaConnection.put(JavaRoute.companyProfile + "/business/" + detail.getId(), json);
               }

               System.err.println("log view json : " + json);
               System.err.println("log view response : " + response);

               try {
                    if (response.isSuccessful()) {

                         String respsneData = response.body().string();

                         JSONObject jsonObject = new JSONObject(respsneData);

                         if (jsonObject.has("error")) {

                              ObjectMapper objMapper = new ObjectMapper();

                              ErrorResponse errorResponse = objMapper.readValue(respsneData, ErrorResponse.class);

                              ErrorDetail error = errorResponse.getError();

                              String reason = error.getReason();

                              if (error.getCode() == 409) { // conflict

                                   String phoneNumber = objPhoneNumber.getValueTextField().replace(" ", "");

                                   String email = objEmail.getValueTextField();

                                   if (reason.contains(phoneNumber)) { // duplicate phone number

                                        objPhoneNumber.setFieldError("Phone number already exist !");

                                   }

                                   if (reason.contains(email)) {

                                        objEmail.setFieldError("Email already exist !");

                                   }
                              }

                         } else {
                              businessCreate.dispose();
                              businessView.getBusinessController().init(); // reload panel
                         }

                    }
               } catch (Exception e) {
                    System.err.println("error add business : " + e);
               }

          }

     }

}
