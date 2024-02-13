package Constant;

import Model.HoldOrder.HoldOrderModel;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import okhttp3.MediaType;

public class JavaConstant {

     public static String invoiceNo;
     public static String reasonId;
     public static Integer returnerId;
     public static String token;
     public static String fullName;
     public static String userCode;
     public static String posId;
     public static Integer cashierId;
     public static Long checkCloseShift;
     public static int numberOpenShift = 0;
//     public static Component[] listHoldData;
     public static String roleName;
     public static String isReturn;

     public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
     public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

     public static String typeCash = "cash";
     public static String typeCredit = "credit";
     public static String typeQRaba = "aba";
     public static String typeQRmnk = "mnk";
     public static String typeExpress = "express";
     public static String titleClose = "Close";
     public static String success = "success";
     public static String closeShift = "Close Shift";
     public static String supervisor = "Supervisor";
     public static String admin = "Admin";

     public static double exchangeRate = 4150;

     public static double getReplace(String value) {
          String data = value.replace("$", "");
          data = data.replace(",", "");
          double doubleValue = Double.valueOf(data);
          return doubleValue;
     }

     public static ArrayList<HoldOrderModel> listHoldData = new ArrayList<>();
     public static int indexArrayListHold = 0;
     public static boolean checkOpenShift = false;
     public static String openShiftFirst = "You have to open shift first!";

     public static double get4Length(String value) {
          if (value.length() > 4) {
               String data = value.substring(0, 4);
               double d = Double.parseDouble(data);
               return d;
          }
          return Double.parseDouble(value);
     }
     
     public static String byBrandId = "Select By BrandID";
     public static String byCatID = "Select By CatID";
     public static String noResult = "No Result";
     
}
