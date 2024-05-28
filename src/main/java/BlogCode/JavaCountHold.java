/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import okhttp3.Response;
import org.json.JSONObject;

public class JavaCountHold {

     public static int countHold() {
          int countH = 0;
          Response responseGet = JavaConnection.get(JavaRoute.holdOrder + "?userId=" + JavaConstant.cashierId);
          try {
               String dataJson = responseGet.body().string();
               JSONObject jSONObject = new JSONObject(dataJson);
               countH = jSONObject.getInt("count");

          } catch (Exception e) {
          }

          return countH;
     }
}
