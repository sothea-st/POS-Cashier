package feature.HoldOrder;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Components.Color.WindowColor;
import Components.SubtotalPanel;
import Components.countCircleShape;
import Constant.JavaConnection;
import Constant.JavaRoute;
import feature.DeleteAndCancel.CancelDialog;
import Components.Event.ButtonEvent;
import Model.HoldOrder.DataHoldOrder;
import Model.HoldOrder.HoldOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import okhttp3.Response;

public class ActionHoldOrder {

     private JPanel detailItem;
     private SubtotalPanel subtotalPanel;
     private Button btnPayment;
     private countCircleShape countCircleShape;
     private Button buttonHoldOrder;
     private ButtonCancel btnCancel;

     public ActionHoldOrder(JPanel panelHold) {
          getHoldItem(panelHold);
     }

     public void getHoldItem(JPanel panelHold) {
          try {
               Response response = JavaConnection.get(JavaRoute.holdOrder);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    HoldOrder data = objMap.readValue(responseData, HoldOrder.class);
                    DataHoldOrder[] listData = data.getData();
                    appendValue(listData, panelHold);
               } else {
                    System.err.println("fail loading product");
               }
          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     private void appendValue(DataHoldOrder[] listData, JPanel panelHold) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelHold.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (int i = 0; i < listData.length; i++) {

               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
               gbc.anchor = gbc.NORTH;
               x++;
               if (x == 1) {
                    x = 0;
                    y++;
               }

               var obj = listData[i];
               HoldItems h = new HoldItems();

               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String key) { // action process
                         detailItem.removeAll();
                         panelHold.revalidate();
                         panelHold.repaint();
                         System.out.println("listData.length :" + listData.length);

                         btnPayment.setBackground(WindowColor.lightBlue);
                         buttonHoldOrder.setBackground(WindowColor.yellow);
                         btnCancel.setBackground(WindowColor.darkred);
//                         dispose();
                    }

                    @Override
                    public void onRemove(String key) {
                         CancelDialog cancel = new CancelDialog(new JFrame(), true);
                         cancel.setCode("cancelHold");
                         ArrayList<HoldeModel> lstModel = new ArrayList<>();
                         lstModel.add(new HoldeModel(obj.getID()));
                         cancel.setHoldId(lstModel);
                         cancel.setPanelHold(panelHold);
                         cancel.setCountCircleShape(countCircleShape);
                         cancel.setVisible(true);
                    }
               };

               h.initEvent(events);
//               h.setCountNumber("" + obj.getID());
               int num = i + 1;
               h.setCountNumber("" + num);
               h.setQty(obj.getQtyHold());
               panelHold.revalidate();
               panelHold.repaint();
               panelHold.add(h, gbc);
          }

     }

//     public JPanel getPanelHold() {
//          return panelHold;
//     }
//
//     public void setPanelHold(JPanel panelHold) {
//          this.panelHold = panelHold;
//     }
//
//     void refreshPanel() {
//          panelHold.revalidate();
//          panelHold.repaint();
//     }
     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public countCircleShape getCountCircleShape() {
          return countCircleShape;
     }

     public void setCountCircleShape(countCircleShape countCircleShape) {
          this.countCircleShape = countCircleShape;
     }

     public Button getButtonHoldOrder() {
          return buttonHoldOrder;
     }

     public void setButtonHoldOrder(Button buttonHoldOrder) {
          this.buttonHoldOrder = buttonHoldOrder;
     }

     public ButtonCancel getBtnCancel() {
          return btnCancel;
     }

     public void setBtnCancel(ButtonCancel btnCancel) {
          this.btnCancel = btnCancel;
     }

}
