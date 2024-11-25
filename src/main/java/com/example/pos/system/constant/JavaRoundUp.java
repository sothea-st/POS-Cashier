package com.example.pos.system.constant;

import java.text.DecimalFormat;

public class JavaRoundUp {
       public static String roundUp3length(String[] listStr) {
          int lengthChar = listStr.length;
          int num = Integer.parseInt(listStr[lengthChar - 3]) + 1;
          String numStr = "" + num;
          listStr[lengthChar - 3] = numStr;
          listStr[lengthChar - 2] = "0";
          listStr[lengthChar - 1] = "0";

          String valueData = "";
          for (int j = 0; j < listStr.length; j++) {
               valueData += listStr[j];
          }
          return valueData;
     }

     public static String roundUpKhr4length(String[] listStr) {
          int lengthChar = listStr.length;
          int num = Integer.parseInt(listStr[lengthChar - 3]) + 1;
          String numStr = "" + num;
          if (num < 10) {
               listStr[lengthChar - 3] = "" + numStr;
          } else {
               int num0 = Integer.parseInt(listStr[0]) + 1;
               listStr[lengthChar - 4] = "" + num0;
               listStr[lengthChar - 3] = "0";
          }
          listStr[lengthChar - 2] = "0";
          listStr[lengthChar - 1] = "0";

          String valueData = "";
          for (int j = 0; j < listStr.length; j++) {
               valueData += listStr[j];
          }
          return valueData;
     }

     public static String roundUpKhr5length(String[] listStr) {
          int num = Integer.parseInt(listStr[2]) + 1;
          String numStr = "" + num;
          if (num < 10) {
               listStr[2] = numStr;
          } else {
               int num0 = Integer.parseInt(listStr[1]) + 1;

               if (num0 < 10) {
                    listStr[1] = "" + num0;
                    listStr[2] = "0";
               } else {
                    int num1 = Integer.parseInt(listStr[0]) + 1;
                    listStr[0] = "" + num1;
                    listStr[1] = "0";
                    listStr[2] = "0";
               }
          }

          listStr[3] = "0";
          listStr[4] = "0";

          String valueData = "";
          for (int j = 0; j < listStr.length; j++) {
               valueData += listStr[j];
          }
          return valueData;
     }

     public static String roundUpKhr6length(String[] listStr) {
          int num = Integer.parseInt(listStr[3]) + 1;
          String numStr = "" + num;
          if (num < 10) {
               listStr[3] = numStr;
          } else {
               int num0 = Integer.parseInt(listStr[2]) + 1;

               if (num0 < 10) {
                    listStr[2] = "" + num0;
                    listStr[3] = "0";
               } else {
                    int num1 = Integer.parseInt(listStr[1]) + 1;

                    if (num1 < 10) {
                         listStr[1] = "" + num1;
                         listStr[2] = "0";
                         listStr[3] = "0";
                    } else {
                         int num2 = Integer.parseInt(listStr[0]) + 1;
                         listStr[0] = "" + num2;
                         listStr[1] = "0";
                         listStr[2] = "0";
                         listStr[3] = "0";
                    }
               }
          }

          listStr[4] = "0";
          listStr[5] = "0";

          String valueData = "";
          for (int j = 0; j < listStr.length; j++) {
               valueData += listStr[j];
          }
          return valueData;
     }

     public static String roundUpKhr7length(String[] listStr) {
          int num = Integer.parseInt(listStr[4]) + 1;
          String numStr = "" + num;
          if (num < 10) {
               listStr[4] = numStr;
          } else {
               int num0 = Integer.parseInt(listStr[3]) + 1;

               if (num0 < 10) {
                    listStr[3] = "" + num0;
                    listStr[4] = "0";
               } else {
                    int num1 = Integer.parseInt(listStr[2]) + 1;

                    if (num1 < 10) {
                         listStr[2] = "" + num1;
                         listStr[3] = "0";
                         listStr[4] = "0";

                    } else {

                         int num2 = Integer.parseInt(listStr[1]) + 1;
                         if (num2 < 9) {
                              listStr[1] = "" + num2;
                              listStr[2] = "0";
                              listStr[3] = "0";
                              listStr[4] = "0";
                         } else {
                              int num3 = Integer.parseInt(listStr[0]) + 1;
                              listStr[0] = "" + num3;
                              listStr[1] = "0";
                              listStr[2] = "0";
                              listStr[3] = "0";
                              listStr[4] = "0";
                         }

                    }
               }
          }

          listStr[5] = "0";
          listStr[6] = "0";

          String valueData = "";
          for (int j = 0; j < listStr.length; j++) {
               valueData += listStr[j];
          }
          return valueData;
     }

     public static String roundDown(String value) {
          int le = value.length();
          int begin = le - 2;
          value = value.substring(0, begin);
          value = value + "00";
          return value;
     }

     public static String setRoundNumber(double change) {
          DecimalFormat dd = new DecimalFormat("#");
          String khValue = dd.format(change);
          String data = "";
          int l = khValue.length();
          
          if( l == 2 ) {
               int _value = Integer.parseInt(khValue);
               if( _value > 50 ) {
                    data = "100";
               } else {
                    data = "0";
               }
             return data;
          }
          
          if (l >= 3) {
               int begin = l - 2;
               String last2Number = khValue.substring(begin, l);
               if (last2Number.equals("00")) {
                    data = JavaConstant.insertComma(khValue);
               } else {
                    if (Integer.parseInt(last2Number) < 50) {
                         data = JavaConstant.insertComma(JavaRoundUp.roundDown(khValue));
                    } else {
                         String value = "";
                         String[] listStr = khValue.split("");
                         int lengthChar = listStr.length;

                         switch (lengthChar) {
                              case 3:
                                   value = JavaRoundUp.roundUp3length(listStr);
                                   break;
                              case 4:
                                   value = JavaRoundUp.roundUpKhr4length(listStr);
                                   break;
                              case 5:
                                   value = JavaRoundUp.roundUpKhr5length(listStr);
                                   break;
                              case 6:
                                   value = JavaRoundUp.roundUpKhr6length(listStr);
                                   break;
                              case 7:
                                   value = JavaRoundUp.roundUpKhr7length(listStr);
                                   break;
                         }
                         data = JavaConstant.insertComma(value);
                    }
               }
          }
          return data;
     }
}
