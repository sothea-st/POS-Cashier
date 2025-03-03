package feature.company_profile.individual.controller;

import Components.Event.ButtonEvent;
import Constant.ErrorDetail;
import Constant.ErrorResponse;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import FormComponent.JavaTextField;
import FormComponent.combobox.JavaCombobox;
import FormComponent.datepicker.JavaDatePicker;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.individual.IndividualCreate;
import feature.company_profile.individual.IndividualView;
import feature.company_profile.individual.component.JavaComboBoxSelectionV1;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class IndividualCreateController {

     private IndividualCreate individualCreate;
     private IndividualView individualView;

     // variable combobox
     private JavaCombobox objProvince;
     private JavaCombobox objDistrict;
     private JavaCombobox objCommune;
     private JavaCombobox objVillage;
     private JavaCombobox objGender;
     private JavaCombobox objNationality;
     // variable form
     private JavaTextField objFirstName;
     private JavaTextField objLastName;
     private JavaDatePicker objDob;
     private JavaTextField objEmail;
     private JavaTextField objPhoneNumber;
     private JavaTextField objHome;
     private JavaTextField objLat;
     private JavaTextField objLng;
     private JavaTextField objStreet;

     public IndividualCreateController(IndividualCreate individualCreate) {

          this.individualCreate = individualCreate;
          // variable combobox
          this.objProvince = individualCreate.getObjProvince();
          this.objDistrict = individualCreate.getObjDistrict();
          this.objCommune = individualCreate.getObjCommune();
          this.objVillage = individualCreate.getObjVillage();
          this.objGender = individualCreate.getObjGender();
          this.objNationality = individualCreate.getObjNationality();

          // variable form
          this.objFirstName = individualCreate.getObjFirstName();
          this.objLastName = individualCreate.getObjLastName();
          this.objDob = individualCreate.getObjDate();
          this.objEmail = individualCreate.getObjEmail();
          this.objPhoneNumber = individualCreate.getObjPhoneNumber();
          this.objHome = individualCreate.getObjHome();
          this.objLat = individualCreate.getObjLat();
          this.objLng = individualCreate.getObjLng();
          this.objStreet = individualCreate.getObjStreet();

     }

     public void init() {
          cmdProvince();
          cmdMap();
     }

     public void create() {

          boolean isCheck = JavaValidation.checkValidation(individualCreate.getPanel());

          if (isCheck) {

               JSONObject json = new JSONObject();
               json.put("firstName", objFirstName.getValueTextField());
               json.put("lastName", objLastName.getValueTextField());
               json.put("gender", individualCreate.getObjGender().getSelectedItem());
               json.put("nationality", objNationality.getSelectedItem());
               json.put("phoneNumber", objPhoneNumber.getValueTextField().replace(" ", ""));
               json.put("email", objEmail.getValueTextField());
               json.put("dob", objDob.getSelectedDate());
               json.put("home", objHome.getValueTextField());
               json.put("lat", objLat.getValueTextField());
               json.put("lng", objLng.getValueTextField());
               json.put("street", objStreet.getValueTextField());
               json.put("province", objProvince.getSelectedItem());
               json.put("district", objDistrict.getSelectedItem());
               json.put("commune", objCommune.getSelectedItem());
               json.put("village", objVillage.getSelectedItem());
               json.put("profileName", individualCreate.getPathImg());  // Since profileName is null in your logs
               json.put("createdBy", JavaConstant.cashierId);

               Response response = JavaConnection.post(JavaRoute.companyProfile + "/individual", json);

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
                              } else if (error.getCode() == 400) {

                                   String dob = objDob.getSelectedDate();

                                   if (reason.contains(dob)) {
                                        objDob.setFieldError("Date of birth must be at least 18 years old!");
                                   }

                              }

                         } else {
                              individualCreate.dispose();
                              individualView.getPanelData().removeAll();
                              individualView.getIndividualController().read(true); // reload
                         }

                    }

               } catch (Exception e) {
                    System.err.println("error post company profile individual : " + e);
               }

          }

     }

     private void cmdMap() {
          LinkedHashMap<String, String> mapGender = new LinkedHashMap<>();
          mapGender.put("Male", "Male");
          mapGender.put("Female", "Female");
          objGender.setMap(mapGender);
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {

               }
          };
          objGender.initEvent(event);

          LinkedHashMap<String, String> mapNationality = new LinkedHashMap<>();
          mapNationality.put("Khmer", "Khmer");
          mapNationality.put("Forienger", "Forienger");
          objNationality.setMap(mapNationality);
          ButtonEvent event1 = new ButtonEvent() {
               @Override
               public void onSelected(String id) {

               }
          };
          objNationality.initEvent(event1);

     }

     private void cmdProvince() {
          // name is field from response 
          JavaComboBoxSelectionV1.addComboBox(
               objProvince,
               JavaRoute.province,
               "nameKh",
               JavaComboBoxSelectionV1.DESC);

          // event select company
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objProvince.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objDistrict,
                              JavaRoute.district + "/" + objProvince.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);

                         cmdCommune();
                    } else {
                         objDistrict.setToFirstItem();
                    }
               }
          };
          objProvince.initEvent(event);
     }

     private void cmdCommune() {

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objDistrict.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objCommune,
                              JavaRoute.commune + "/" + objDistrict.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);

                         cmdVillage();
                    } else {
                         objVillage.setToFirstItem();
                    }
               }
          };
          objDistrict.initEvent(event);

     }

     private void cmdVillage() {

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objCommune.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objVillage,
                              JavaRoute.village + "/" + objCommune.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);
                    } else {
                         objVillage.setToFirstItem();
                    }
               }
          };
          objCommune.initEvent(event);

     }
}
