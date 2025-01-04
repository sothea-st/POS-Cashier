package Model.combobox;

import Components.ComboBox;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Components.Event.ButtonEvent;
import java.util.HashMap;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class ComboBoxSelection {

     public static void addComboBox(ComboBox comboBox, String route, String columnName) {
          try {
               HashMap<String, String> map = new HashMap<>();
               Response response = JavaConnection.get(route);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         String id = String.valueOf(obj.getInt("id"));
                         String name = obj.getString(columnName);
                         map.put(name, id);
                    }
                    comboBox.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

}
