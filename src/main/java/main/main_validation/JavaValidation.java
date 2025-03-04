package main.main_validation;

import FormComponent.JavaTextField;
import FormComponent.JavaTextFieldPassword;
import FormComponent.combobox.JavaCombobox;
import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class JavaValidation {

     private String id;

     public static boolean checkValidation(JPanel panel) {
          boolean isCheck = true;
          for (Component com : panel.getComponents()) {
               if (com instanceof JavaTextField) {

                    String text = ((JavaTextField) com).getValueTextField();
                    String lanelName = ((JavaTextField) com).getLabelName();
                    String typeTextField = ((JavaTextField) com).getTypeTextField();
                    String fieldEmail = ((JavaTextField) com).getEmail();
                    String fieldPhoneNumber = ((JavaTextField) com).getPhoneNumber();
                    String fieldAmount = ((JavaTextField) com).getAmount();
                    

                    // Ensure text is not null
                    if (text == null) {
                         text = ""; // Default to an empty string
                    }

                    // for general textField 
                    if (lanelName.contains("*") && text.isEmpty()) {
                         ((JavaTextField) com).setErrorBorder();
                         isCheck = false;
                         ((JavaTextField) com).setFocus();
                    }

                    // for email textField
                    if (typeTextField != null && typeTextField.equals(fieldEmail) && !text.contains("@")) {
                         ((JavaTextField) com).setErrorBorder();
                         isCheck = false;
                         ((JavaTextField) com).setFocus();
                    }

                    // for phone number textField
                    if (typeTextField != null && typeTextField.equals(fieldPhoneNumber)) {
                         text = text.replace(" ", "");
                         if (text.length() != 9 && text.length() != 10) {
                              ((JavaTextField) com).setErrorBorder();
                              isCheck = false;
                              ((JavaTextField) com).setFocus();
                         }
                    }

                    // for amount textField
                    if (typeTextField != null && typeTextField.equals(fieldAmount) && text.equals("0.")) {
                         ((JavaTextField) com).setErrorBorder();
                         isCheck = false;
                         ((JavaTextField) com).setFieldError(JavaTextField.invalidAmount);
                         ((JavaTextField) com).setFocus();
                    }
               } else if (com instanceof JavaCombobox) {
                    var lanelName = ((JavaCombobox) com).getLabelName();

                    String valueCombo = ((JavaCombobox) com).getSelectedItem();
                    if (lanelName.contains("*") && valueCombo.equals("0")) {
                         ((JavaCombobox) com).setErrorBorder();
                         isCheck = false;
                    }
               }else if(com instanceof JavaTextFieldPassword){
                    String text = ((JavaTextFieldPassword) com).getValueTextField();
                    String lanelName = ((JavaTextFieldPassword) com).getLabelName();
                    
                    if (text == null) {
                         text = ""; // Default to an empty string
                    }

                    // for general textField 
                    if (lanelName.contains("*") && text.isEmpty()) {
                         ((JavaTextFieldPassword) com).setErrorBorder();
                         isCheck = false;
                    }
               }
                    
//               }else if(com instanceof JavaDatePicker){
//                    String text = ((JavaDatePicker) com).getSelectedDate();
//                    String labelName = ((JavaDatePicker) com).getLabelName();
//                    
//                    // for general textField 
//                    if (labelName.contains("*") && text == null) {
//                         ((JavaDatePicker) com).setErrorBorder();
//                         isCheck = false;
//                    }
//               }
          }

          return isCheck;
     }

     public static void setError(String errorReason, String key, String msg, JavaTextField textField) {
          if (errorReason.contains(key)) {
               textField.setFieldError(msg);
          } else {
               textField.setFieldError(false);
          }
     }
     
     public static void setPasswordError(String errorReason, String key, String msg, JavaTextFieldPassword textField) {
          if (errorReason.contains(key)) {
               textField.setFieldError(msg);
          } else {
               textField.setFieldError(false);
          }
     }
     
     
     public static boolean checkPassword(Response response, List<JavaPasswordConflicValidation> fields) {
          boolean isExist = true;
          try {
                String responseData = response.body().string();
                JSONObject errorJson = new JSONObject(responseData);
                
                System.out.println("errorJson : " + errorJson);
                
                
                if (errorJson.has("newPassword")) {
                     String reason = errorJson.getString("newPassword");

                     isExist = false;
                     for (JavaPasswordConflicValidation field : fields) {
                          setPasswordError(
                               reason,
                               field.getKey(),
                               field.getMsg(),
                               field.getField());
                     }

                }else if(errorJson.has("msg")){
                     String reason = errorJson.getString("msg");
                     
                     if(reason.equals("success")){
                        isExist = true;
                     }else{
                        isExist = false;
                        for (JavaPasswordConflicValidation field : fields) {
                             setPasswordError(
                                  reason,
                                  field.getKey(),
                                  field.getMsg(),
                                  field.getField());
                        }
                     }
                }
               
          } catch (Exception e) {
               System.err.println("error : " + e);
          }
          return isExist;
     }
     

     public static boolean checkNameExist(Response response, List<JavaConflicValidation> fields) {
          boolean isExist = true;
          try {
               String responseData = response.body().string();
               JSONObject errorJson = new JSONObject(responseData);
               if (errorJson.has("error")) {
                    JSONObject err = errorJson.getJSONObject("error");
                    int statusCode = err.getInt("code");
                    String reason = err.getString("reason");
                    if (statusCode == 409) { // conflic
                         isExist = false;
                         for (JavaConflicValidation field : fields) {
                              setError(
                                   reason,
                                   field.getKey(),
                                   field.getMsg(),
                                   field.getField());
                         }

                    }
               }
          } catch (Exception e) {
               System.err.println("error : " + e);
          }
          return isExist;
     }
     
     // For old API
     public static boolean checkNameExistSecondFunction(Response response, List<JavaConflicValidation> fields) {
          boolean isExist = true;
          try {
               String responseData = response.body().string();
               
               JSONObject errorJson = new JSONObject(responseData);
               
               int statusCode = errorJson.getInt("statusCode");
               String message = errorJson.getString("message");
               
               if (statusCode == 500) { // conflic
                    isExist = false;
                    for (JavaConflicValidation field : fields) {
                         setError(
                              message,
                              field.getKey(),
                              field.getMsg(),
                              field.getField());
                    }

               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }
          return isExist;
     }

//    public void cmdCat(JavaCombobox objCmd) {
//        // name is field from response 
//        JavaComboBoxSelection.addComboBox(
//                objCmd,
//                JavaRoute.category,
//                "categoryNameEn",
//                JavaComboBoxSelection.DESC);
//
//        // event select company
//        ButtonEvent event = new ButtonEvent() {
//            @Override
//            public void onSelected(String id) {
//
//                setId(id);
//            }
//        };
//        objCmd.initEvent(event);
//    }
}
