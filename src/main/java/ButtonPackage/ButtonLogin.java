package ButtonPackage;

import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Fonts.WindowFonts;
import java.awt.Graphics;
 

 
public class ButtonLogin extends javax.swing.JPanel {

 
    public ButtonLogin() {
        initComponents();
        setBackground(WindowColor.green);
        btnLogin.setFont(WindowFonts.timeNewRomanBold14);
         JavaConstant.setPointer(btnLogin);
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

        btnLogin = new javax.swing.JLabel();

        btnLogin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogin.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnLogin;
    // End of variables declaration//GEN-END:variables
}
