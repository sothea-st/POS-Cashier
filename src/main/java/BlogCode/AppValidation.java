/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Components.TextFieldCenter;

public class AppValidation {

     public static String checkValidation(String discountValue, TextFieldCenter inputDis) {
          int l = discountValue.length();
          if (l == 1 && discountValue.equals(".")) {
               inputDis.setValueTextFieldCenter(null);
          }

          if (l == 1 && discountValue.equals("0")) {
               inputDis.setValueTextFieldCenter(null);
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
               inputDis.setValueTextFieldCenter(_value);
          }
          return  _value;
     }
}
