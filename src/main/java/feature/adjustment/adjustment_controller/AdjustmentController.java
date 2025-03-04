package feature.adjustment.adjustment_controller;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import feature.adjustment.AdjustmentForm;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import main.main_validation.main_alert_delete.MainDeleteAction;
import okhttp3.Response;
import org.json.JSONObject;

public class AdjustmentController extends MainDeleteAction {

     private AdjustmentForm adjustmentForm;

     public AdjustmentController(AdjustmentForm adjustmentForm) {
          this.adjustmentForm = adjustmentForm;
     }

     public void updateStatus(Integer adjustmentId, String statuValue) {

          if (statuValue.equals("Draft")) {
               return;
          }

          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to update status?",
               "Status", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               JSONObject json = new JSONObject();
               json.put("approvalBy", JavaConstant.cashierId);
               json.put("status", statuValue);

               Response response = JavaConnection.put(JavaRoute.adjustment + "/updateStatus/" + adjustmentId, json);

               if (response.isSuccessful()) {
                    adjustmentForm.getData(true); // reload
               }
          } else {
               adjustmentForm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          }

     }

     @Override
     public void yesOption(Integer id) {
          Response response = JavaConnection.delete(JavaRoute.adjustment + "/" + id);
          if (response.isSuccessful()) {
               adjustmentForm.getData(true);
          }
     }

}
