package BlogCode;

import Components.TextFieldCenter;
import javax.swing.JTextField;

public class AppValidation {

     public static void checkValidation(String discountValue, TextFieldCenter inputDis) {
          int l = discountValue.length();
          if (l == 1 && discountValue.equals(".")) {
               inputDis.setValueTextFieldCenter(null);
               return;
          }

          String _value = "";
          if (l >= 2) {

               int count = 0;
               char[] arr = discountValue.toCharArray();
               if (arr[0] == '0' && arr[1] == '.') {
                    for (int i = 0; i < arr.length; i++) {
                         if (arr[i] == '.') {
                              count++;
                         }
                         if (count == 2) {
                              break;
                         }
                         _value += arr[i];
                    }
                    inputDis.setValueTextFieldCenter(_value);
               } else {
                    for (int i = 0; i < arr.length; i++) {
                         if (arr[i] == '.') {
                              count++;
                         }
                         if (count == 2) {
                              break;
                         }
                         if (arr[0] == '0') {
                              arr[0] = ' ';
                         }
                         _value += arr[i];
                    }
                    _value = _value.trim();
                    inputDis.setValueTextFieldCenter(_value);

               }

          }

     }

     public static boolean checkValidation(JTextField text) {
          String _data = text.getText();
          int l = _data.length();

          char[] arr = text.getText().toCharArray();
//          String _reValue = "";
//          if (!_data.contains(".")) {
//               for (int i = 0; i < l; i++) {
//                    _reValue += arr[i];
//                    if (_data.charAt(i) < '0' || _data.charAt(i) > '9') {
//                         int len = _reValue.length() - 1;
//                         String _d = _reValue.substring(0, len);
//                         text.setText(_d);
//                         return true;
//                    }
//               }
//          }

          if (l == 1 && _data.equals(".")) {
               text.setText(null);
               return true;
          }
          String _value = "";
          if (l >= 2) {
               int count = 0;

               if (arr[0] == '0' && arr[1] == '.') {
                    for (int i = 0; i < arr.length; i++) {
                         if (arr[i] == '.') {
                              count++;
                         }
                         if (count == 2) {
                              break;
                         }
                         _value += arr[i];
                    }
                    text.setText(_value);
               } else {
                    for (int i = 0; i < arr.length; i++) {
                         if (arr[i] == '.') {
                              count++;
                         }
                         if (count == 2) {
                              if (arr[arr.length - 1] == '.') {
                                   arr[arr.length - 1] = ' ';
                              }
                              _value += arr[i];
                              _value = _value.trim();
                              text.setText(_value);
                              return true;
                         }
                         if (arr[0] == '0') {
                              arr[0] = ' ';
                         }
                         _value += arr[i];
                    }
                    _value = _value.trim();
                    text.setText(_value);
               }
          }
          return false;
     }
     
     
     public static String checkValidationJTextField(String discountValue, JTextField text) {
         
         System.out.println("Helllooooooooooooooooooo");
         
          int l = discountValue.length();
          if (l == 1 && discountValue.equals(".")) {
               text.setText(null);
          }

          if (l == 1 && discountValue.equals("0")) {
               text.setText(null);
          }
          String _value = "";
          if (l > 2) {
               int count = 0;
               char[] arr = discountValue.toCharArray();

               for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == '.') {
                         count++;
                    }
                    if (count == 2) {
                         break;
                    }
                    _value += arr[i];
               }
               text.setText(_value);
          }
          return  _value;
     }
}
