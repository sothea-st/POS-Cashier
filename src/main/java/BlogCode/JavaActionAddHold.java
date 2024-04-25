/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Button.Button;
import Color.WindowColor;
import Components.BoxItem;
import Components.SubtotalPanel;
import Components.countCircleShape;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Fonts.WindowFonts;
import HoldOrder.HoldeModel;
import View.MainPage.MainPage;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONObject;

public class JavaActionAddHold {

     public static void addHold(
          JPanel detailItem,
          Button btnPayment,
          Button buttonHoldOrder,
          ButtonPackage.ButtonCancel btnCancel,
          SubtotalPanel totalPanel,
          countCircleShape countCircleShape) {
          btnPayment.setBackground(WindowColor.lightGray);
          buttonHoldOrder.setBackground(WindowColor.lightGray);
          btnCancel.setBackground(WindowColor.lightGray);
          Component[] listHold = detailItem.getComponents();
          ArrayList<HoldeModel> holdModel = new ArrayList<>();

          int qty = 0;
          HoldeModel h = new HoldeModel();
          for (int i = 0; i < listHold.length; i++) {
               var box = ((BoxItem) listHold[i]);
               qty += box.getQty();

               String _discountType = box.getDiscountType();
               double _discountValue = box.getDiscountDigit();
             

               if (_discountType != null) {
                    if (_discountType.equals("dollar")) {
                         _discountValue = JavaConstant.getReplace("" + box.getDiscountValue());
                    }

                    if (_discountType.equals("percent")) {
                         _discountValue = box.getDiscountDigit();
                    }
               }

               h = new HoldeModel(
                    box.getProductId(),
                    box.getQty(),
                    box.getDiscountType(),
                    _discountValue);
               holdModel.add(h);
          }

          JSONObject json = new JSONObject();
          json.put("note", "");
          json.put("qtyHole", qty);
          json.put("createBy", JavaConstant.cashierId);
          json.put("listHoldDetail", holdModel);

          try {
               Response response = JavaConnection.post(JavaRoute.holdOrder, json);

               if (response.isSuccessful()) {

                    countCircleShape.setCountTimes("" + new MainPage().countHold());
                    detailItem.removeAll();
                    detailItem.revalidate();
                    detailItem.repaint();
                    totalPanel.setLabelSubTitleToZero();
                    detailItem.setBackground(WindowColor.slightGreen);
                    detailItem.setBorder(null);

                    //==========Remove ID product when after selecting and store in holdorder
                    JavaConstant.productId = 0;
                    JavaConstant.discountAmount = 1;

               } else {
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", WindowColor.mediumGreen);
                    UI.put("Panel.background", WindowColor.mediumGreen);
                    UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
                    JOptionPane.showMessageDialog(null, "Caannot Add Hold Order!");

               }

          } catch (Exception e) {

          }
     }

}
