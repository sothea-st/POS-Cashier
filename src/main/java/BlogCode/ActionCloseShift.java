 
package BlogCode;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Components.BackgroundImage;
import Components.JavaAlertMessage;
import Components.SearchField;
import Components.TextField;
import Constant.JavaConstant;
import feature.LoginAndLogoutForm.LoginFormJdailog;
import feature.OpenAndCloseShift.CloseShift;
import View.MainPage.MainPage;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionCloseShift {

     public static JavaAlertMessage j;

     public static void closeShift(
          JPanel detailItem,
          JPanel panelProduct,
          JPanel panelPagination,
          JPanel category,
          SearchField searchBox,
          TextField textField,
          Button btnOpenShift,
          Button buttonCustomer,
          Button buttonDiscount,
          Button btnReprint,
          Button btnReturn,
          Button buttonCashier,
          ButtonCancel btnCancel,
          Button btnHold,
          BackgroundImage bgImage,
          Button btnLogin,
          Button stock,
          Button buttonStaff,
          LoginFormJdailog jdFormLogin,
          MainPage mainPage
     ) {

          Component[] listCom1 = detailItem.getComponents();
          j = new JavaAlertMessage(new JFrame(), true);

          JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
          if (JavaConstant.isReturn != null) {
               j.setMessage(JavaAlertMessage.returnMsg);
               j.setVisible(true);
               return;
          }

          if (listCom1.length != 0) {
               j.setMessage(Components.MessageAlert.Message.clearItemMsg);
               j.setVisible(true);
               return;
          }    
          

          if (JavaCountHold.countHold() > 0) {
               j.setMessage("There are any trancsactions not yet completed in Hold function!");
               j.setVisible(true);
               return;
          }


          
          CloseShift close = new CloseShift(new JFrame(), true, btnOpenShift);
          close.setMainPage(mainPage);
          close.setPanelProduct(panelProduct);
          close.setPanelPagination(panelPagination);
          close.setSearchBox(searchBox);
          close.setTextField(textField);
          close.setCategory(category);
          close.setBtnCancel(btnCancel);
          close.setBtnHold(btnHold);
          close.setButtonCustomer(buttonCustomer);
          close.setButtonDiscount(buttonDiscount);
          close.setBtnReprint(btnReprint);
          close.setBtnreturn(btnReturn);
          close.setBgImage(bgImage);
          close.setButtonCashier(buttonCashier);
          close.setBtnLogin(btnLogin);
          close.setStock(stock);
          close.setButtonStaff(buttonStaff);
          close.setVisible(true);

     }
}
