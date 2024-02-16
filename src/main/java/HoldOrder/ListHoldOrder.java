
package HoldOrder;

import Button.Button;
import Color.WindowColor;
import Components.BoxItem;
import Components.HoldItem;
import Components.SubtotalPanel;
import Components.countCircleShape;
import Constant.JavaConstant;
import Controller.ActionProduct.ActionProduct;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.HoldOrder.HoldOrderModel;
import Model.HoldOrder.NewHoldOrderModel;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;


public class ListHoldOrder extends javax.swing.JDialog {

    private JPanel detailItem;
    private SubtotalPanel subtotalPanel;
    private Button btnPayment;
    private countCircleShape countCircleShape; 

    public ListHoldOrder(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();  
          header.setBackground(WindowColor.darkGreen);
          panelHold.setBackground(WindowColor.mediumGreen);
          remove.setFont(WindowFonts.timeNewRomanBold14);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          body.setBackground(WindowColor.mediumGreen);
          setResizable(false);
          buttonCancel.setButtonName("Close");
          
          callHistoryHold(JavaConstant.listHoldOrder);
          panelHold.setLayout(new BoxLayout(panelHold, BoxLayout.Y_AXIS));
          panelHold.setBorder(new EmptyBorder(0, 0, 0, 0));
    }
          
     void callHistoryHold( ArrayList<NewHoldOrderModel> listHoldOrder) {
         
         for (int i = 0; i < listHoldOrder.size(); i++) {
               int number = JavaConstant.listHoldOrder.get(i).getNumber();
               int qty = listHoldOrder.get(i).getQty();
               Component[] listCom = listHoldOrder.get(i).getListCom();
               HoldItems h = new HoldItems();
               int index = i;
               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String key) { // action process
                         detailItem.removeAll();
                         for (int j = 0; j < listCom.length; j++) {
                              var box = ((BoxItem) listCom[j]);
                              detailItem.add(box);
                         }
                         refreshPanel();
                         detailItem.setBorder(new BevelBorder(BevelBorder.RAISED));
                         detailItem.setLayout(new BoxLayout(detailItem, BoxLayout.PAGE_AXIS));
                         detailItem.setBackground(WindowColor.white);
                         subtotalPanel.total(0, listCom, 0, subtotalPanel);
                         JavaConstant.indexArrayListHold = index;

                         btnPayment.setBackground(WindowColor.lightBlue);
                         dispose();
                         
                         listHoldOrder.remove(index);
                         panelHold.remove(index);
                         panelHold.removeAll();
                         callHistoryHold(JavaConstant.listHoldOrder);
                         countCircleShape.setCountTimes(""+JavaConstant.listHoldOrder.size());
                         countCircleShape.revalidate();
                         countCircleShape.repaint();
                         refreshPanel();
                    }

                    @Override
                    public void onRemove(String key) {
                         listHoldOrder.remove(index);
                         panelHold.remove(index);
                         panelHold.removeAll();
                         callHistoryHold(JavaConstant.listHoldOrder);
                         countCircleShape.setCountTimes(""+JavaConstant.listHoldOrder.size());
                         countCircleShape.revalidate();
                         countCircleShape.repaint();
                         refreshPanel();
                    }
               };

               h.initEvent(events);
               h.setCountNumber(""+number);
               h.setQty(qty);
               panelHold.add(h);
               refreshPanel();
          }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();
        buttonCancel = new ButtonPackage.ButtonCancel();
        panelHold = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        remove = new ButtonPackage.ButtonCancel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonCancel.setButtonName("Close");
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        panelHold.setBackground(new java.awt.Color(255, 255, 255));
        panelHold.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelHoldLayout = new javax.swing.GroupLayout(panelHold);
        panelHold.setLayout(panelHoldLayout);
        panelHoldLayout.setHorizontalGroup(
            panelHoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelHoldLayout.setVerticalGroup(
            panelHoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("№ ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QTY");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Action");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        remove.setBackground(new java.awt.Color(153, 102, 0));
        remove.setButtonName("Clear All");
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bodyLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelHold, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(15, 15, 15))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

     void refreshPanel() {
          panelHold.revalidate();
          panelHold.repaint();
    }
     
    private void removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseClicked

        panelHold.removeAll();
        refreshPanel();
        JavaConstant.listHoldOrder.clear();
        countCircleShape.setCountTimes(""+JavaConstant.listHoldOrder.size());
        countCircleShape.revalidate();
        countCircleShape.repaint();
    }//GEN-LAST:event_removeMouseClicked

    public JPanel getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(JPanel detailItem) {
        this.detailItem = detailItem;
    }

    public SubtotalPanel getSubtotalPanel() {
        return subtotalPanel;
    }

    public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
        this.subtotalPanel = subtotalPanel;
    }

    public Button getBtnPayment() {
        return btnPayment;
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

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListHoldOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListHoldOrder dialog = new ListHoldOrder(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private ButtonPackage.ButtonCancel buttonCancel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelHold;
    private ButtonPackage.ButtonCancel remove;
    // End of variables declaration//GEN-END:variables
}
