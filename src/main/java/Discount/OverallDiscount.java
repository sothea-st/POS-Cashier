
package Discount;

import Color.WindowColor;
import Components.SubtotalPanel;
import Constant.JavaConstant;
import Event.ButtonEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class OverallDiscount extends javax.swing.JDialog {

    private HashMap<String, String> map = new HashMap<>();
    private String keyValue;
    private SubtotalPanel totalPanel;
    DecimalFormat dm = new DecimalFormat("$ #,##0.00");
    DecimalFormat kh = new DecimalFormat("#,##0");
    
    //Constructor
    public OverallDiscount(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        event();
        disValue.requestFocus();
        disValue.setLabelTextField("0");
        panelDiscountType.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        addComboBox();
        ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    keyValue = key;
               }
          };
        disType.initEvent(events);
    }
    
    //placeholder
    void event() {
        ButtonEvent btnevent = new ButtonEvent() {
             @Override
             public void onFocusGain() {

             }
        };
        disValue.initEvent(btnevent);
    }
    
    //Combobox
    private void addComboBox() {

           map.put("Cash (USD)","cashUsd");
           map.put("Percent (%)","percent");
           disType.setMap(map);
     }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDiscountType = new javax.swing.JPanel();
        label1 = new Components.Label();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        disType = new Components.ComboBox();
        jLabel2 = new javax.swing.JLabel();
        label2 = new Components.Label();
        disValue = new Components.TextField();
        jLabel3 = new javax.swing.JLabel();
        buttonSave1 = new ButtonPackage.ButtonSave();
        buttonCancel1 = new ButtonPackage.ButtonCancel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label1.setLabelName("Discount Type");

        labelPopUpTitle1.setLabelTitle("Overall Discount");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");

        label2.setLabelName("Discount Value");

        disValue.setLabelTextField("0");
        disValue.setValueTextField("");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSave1MouseClicked(evt);
            }
        });

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDiscountTypeLayout = new javax.swing.GroupLayout(panelDiscountType);
        panelDiscountType.setLayout(panelDiscountTypeLayout);
        panelDiscountTypeLayout.setHorizontalGroup(
            panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                        .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2)))
                        .addGap(2, 2, 2)
                        .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(disType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(disValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelDiscountTypeLayout.setVerticalGroup(
            panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiscountTypeLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(disType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDiscountTypeLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(disValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    //save action
    private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked
        
        if (keyValue == null) {
            JOptionPane.showMessageDialog(this, "Please select discount type!");
            return;
        }
        
        if (disValue.getValueTextField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Discount value can not be empty!");
            return;
        }
 
        double discount = JavaConstant.getReplace(disValue.getValueTextField());
        double subTotal = JavaConstant.getReplace(totalPanel.getLabelSubtotalUsd());
        double totalPrice = 0;
        if(keyValue.equals("cashUsd")){
            
            totalPrice = subTotal-discount;
            totalPanel.setLableDiscountUsd(dm.format(discount));
            totalPanel.setLableDiscountKhr(kh.format( discount * JavaConstant.exchangeRate));
           
        }else if(keyValue.equals("percent")){
            double percentValue = (discount*subTotal)/100;
            totalPrice = subTotal-percentValue;
            totalPanel.setLableDiscountUsd(dm.format(percentValue));
            totalPanel.setLableDiscountKhr(kh.format( percentValue * JavaConstant.exchangeRate));
        }
        
        totalPanel.setLableTotalUsd(dm.format(totalPrice));
        totalPanel.setLableTotalKhr(kh.format( totalPrice * JavaConstant.exchangeRate));
        totalPanel.revalidate();
        totalPanel.repaint();
        this.dispose();
    }//GEN-LAST:event_buttonSave1MouseClicked

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
            java.util.logging.Logger.getLogger(OverallDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverallDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverallDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverallDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OverallDiscount dialog = new OverallDiscount(new javax.swing.JFrame(), true);
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
    
     public SubtotalPanel getTotalPanel() {
          return totalPanel;
     }

     public void setTotalPanel(SubtotalPanel totalPanel) {
          this.totalPanel = totalPanel;
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel1;
    private ButtonPackage.ButtonSave buttonSave1;
    private Components.ComboBox disType;
    private Components.TextField disValue;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private Components.Label label1;
    private Components.Label label2;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private javax.swing.JPanel panelDiscountType;
    // End of variables declaration//GEN-END:variables
}
