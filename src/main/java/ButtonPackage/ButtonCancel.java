package ButtonPackage;

import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Fonts.WindowFonts;
import java.awt.Graphics;
 
 
public class ButtonCancel extends javax.swing.JPanel {

     private String buttonName;

     public ButtonCancel() {
          initComponents();
          setBackground(WindowColor.darkred);
          btnCancel.setFont(WindowFonts.timeNewRomanBold14);
          JavaConstant.setPointer(btnCancel);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JLabel();

        btnCancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     //=====================Create Shadow Box============================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
     }

 
     public String getButtonName() {
          return buttonName;
     }

     public void setButtonName(String buttonName) {
          this.buttonName = buttonName;
          btnCancel.setText(buttonName);
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancel;
    // End of variables declaration//GEN-END:variables
}
