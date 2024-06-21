package Button;

import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Fonts.WindowFonts;
import java.awt.Graphics;

public class Button extends javax.swing.JPanel {

     public String getButtonName() {
          return buttonName;
     }

     public void setButtonName(String buttonName) {
          this.buttonName = buttonName;
          button.setText(buttonName);
     }

     public Button() {
          initComponents();
          setBackground(WindowColor.darkred);
          button.setFont(WindowFonts.timeNewRomanBold14);
          JavaConstant.setPointer(button);
     }

     //=====================Create Shadow Box============================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JLabel();

        button.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        button.setForeground(new java.awt.Color(255, 255, 255));
        button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        button.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     private String buttonName;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel button;
    // End of variables declaration//GEN-END:variables
}
