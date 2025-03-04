package main.main_validation.main_alert_delete;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public abstract class MainDeleteAction {
     

     public void alertOption(Integer adjustmentId) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to update status?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               yesOption(adjustmentId);
          }
     }
     
    

     public abstract void yesOption(Integer id);

}
