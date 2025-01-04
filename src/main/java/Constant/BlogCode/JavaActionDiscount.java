
package Constant.BlogCode;

import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConstant;
import NewDiscounts.Discounting;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JavaActionDiscount {

     public static void discount(JPanel detailItem,SubtotalPanel totalPanel ) {
          Component[] listCom1 = detailItem.getComponents();
          double sumDiscount = 0;
          if (listCom1.length != 0) {
               for (Component listCom11 : listCom1) {
                    Components.BoxItem obj = (BoxItem) listCom11;
//                  sumDiscount += JavaConstant.getReplace(""+obj.getOldDiscount());
                    
                    if(obj.getProductId() == JavaConstant.productId){
                        if(obj.getOldDiscount() <= 0){
                            Discounting dis = new Discounting(new JFrame(), true);
                            dis.setTotalPanel(totalPanel);
                            dis.setDetailItem(detailItem);
                            dis.setVisible(true);
                        }else{
                            JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                            j.setMessage("Cannot add any discount!");
                            j.setVisible(true);
                        }
                    } 
               }
               
               //condition if have one discount cant add overall dis but can update some
//               if(JavaConstant.productId == 0){
//                    if(sumDiscount <= 0) {
//                        Discounting dis = new Discounting(new JFrame(), true);
//                        dis.setTotalPanel(totalPanel);
//                        dis.setDetailItem(detailItem);
//                        dis.setVisible(true);
//                   }else{
//                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
//                        j.setMessage("Cannot add any discount!");
//                        j.setVisible(true);
//                    }

//                    Discounting dis = new Discounting(new JFrame(), true);
//                    dis.setTotalPanel(totalPanel);
//                    dis.setDetailItem(detailItem);
//                    dis.setVisible(true);
//               }
                

                 //condition if have one discount cant add overall dis and cant update too
//               if (sumDiscount <= 0) {
//                    Discounting dis = new Discounting(new JFrame(), true);
//                    dis.setTotalPanel(totalPanel);
//                    dis.setDetailItem(detailItem);
//                    dis.setVisible(true);
//               } else if (JavaConstant.discountAmount <= 0) {
//                    Discounting dis = new Discounting(new JFrame(), true);
//                    dis.setTotalPanel(totalPanel);
//                    dis.setDetailItem(detailItem);
//                    dis.setVisible(true);
//               } else {
//                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
//                    j.setMessage("Cannot process this function!");
//                    j.setVisible(true);
//               }
               
          }else{
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Cannot add any discount!");
              j.setVisible(true);
          }
     }
     
     
     public static void overallDiscount(JPanel detailItem,SubtotalPanel totalPanel ){
        
          Component[] listCom1 = detailItem.getComponents();
          if (listCom1.length != 0) {  
              Discounting dis = new Discounting(new JFrame(), true);
              dis.setTotalPanel(totalPanel);
              dis.setDetailItem(detailItem);
              JavaConstant.productId = 0;
              dis.setVisible(true);
          }else{
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Cannot add any discount!");
              j.setVisible(true);
          }
         
     }
}
