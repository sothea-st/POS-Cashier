package feature.adjustment.adjustment_controller;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import Constant.JavaConnection;
import Constant.JavaRoute;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import okhttp3.Response;

public abstract class Controller {
     
     public abstract void setData(Integer adjustmentId);

     public void data(Integer adjustmentId) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to update status?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               setData(adjustmentId);
          } else {
//               adjustmentForm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          }
     }

}
