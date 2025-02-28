/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.company_profile.individual.component;

import Components.Event.ButtonEvent;
import Constant.JavaConnection;
import FormComponent.combobox.JavaCombobox;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MOBILE-APP.02
 */
public class JavaComboBoxSelectionV1 {
      public static int ASC = 0;
     public static int DESC = 1;

     public static void addComboBox(JavaCombobox comboBox, String route, String columnName, int sortBy) {
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
                         map.put(id, name);
                    }

                    // add data order by asc
                    if (sortBy == ASC) {
                         LinkedHashMap<String, String> sortedMap = map.entrySet()
                              .stream()
                              .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Integer::parseInt)))
                              .collect(
                                   LinkedHashMap::new,
                                   (m, e) -> m.put(e.getKey(), e.getValue()),
                                   LinkedHashMap::putAll
                              );

                         // Set the sorted map to the combo box
                         comboBox.setMap(sortedMap);
                    } else if (sortBy == DESC) { // add data order by desc
                         LinkedHashMap<String, String> sortedMap = map.entrySet()
                              .stream()
                              .sorted((e1, e2) -> Integer.compare(Integer.parseInt(e2.getKey()), Integer.parseInt(e1.getKey())))
                              .collect(
                                   LinkedHashMap::new,
                                   (m, e) -> m.put(e.getKey(), e.getValue()),
                                   LinkedHashMap::putAll
                              );

                         // Set the sorted map to the combo box
                         comboBox.setMap(sortedMap);
                    }

                    // event select assetClass
                    // to remove border error from combo
                    ButtonEvent event = new ButtonEvent() {
                         @Override
                         public void onSelected(String id) {
                              
                         }
                    };
                    comboBox.initEvent(event);

               } else {
                    System.err.println("Failed to load data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }
}
