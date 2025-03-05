package main.main_province;

import Components.Event.ButtonEvent;
import Constant.JavaRoute;
import FormComponent.combobox.JavaCombobox;
import feature.company_profile.individual.component.JavaComboBoxSelectionV1;

public class MainProvince {

     protected JavaCombobox objProvince;
     protected JavaCombobox objDistrict;
     protected JavaCombobox objCommune;
     protected JavaCombobox objVillage;

     public MainProvince(
          JavaCombobox objProvince,
          JavaCombobox objDistrict,
          JavaCombobox objCommune,
          JavaCombobox objVillage
     ) {
          this.objProvince = objProvince;
          this.objDistrict = objDistrict;
          this.objCommune = objCommune;
          this.objVillage = objVillage;
          
          // call cmdProvince
          cmdProvince();
     }

     

     protected void cmdProvince() {
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
