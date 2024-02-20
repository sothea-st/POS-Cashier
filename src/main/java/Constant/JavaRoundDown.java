package Constant;

public class JavaRoundDown {

     public static double roundDown(String value) {
//====================== for round down 
//          if (value.equals("0.0")) {
//               return Double.valueOf(value);
//          }
//
//          value = value.replace(".", ",");
//          String[] array = value.split(",");
//          value = array[0];
//          int le = value.length();
//          int begin = le - 2;
//          value = value.substring(0, begin);
//          value = value + "00";
          return Double.parseDouble(value);
     }

     public static String exchangeKh(double value) {
          double data = value * JavaConstant.exchangeRate;
          return String.valueOf(data);
     }

}
