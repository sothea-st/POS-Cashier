 
package BlogCode;
 
import Components.JavaAlertMessage;
import Constant.JavaConstant;
import javax.swing.JFrame;

public class ActionReturnProduct {
     public static String isReturn(){
          if( JavaConstant.isReturn != null ) {
               JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
               j.setMessage("You are in processing return !");
               j.setVisible(true);
               return "";
          }
          return "";
     }
}
