package Button;

import Color.WindowColor;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class ButtonDel extends javax.swing.JPanel {

     private Color bgColor = WindowColor.darkred;
     
     private String buttonName;

     public String getButtonName() {
          return buttonName;
     }

     public void setButtonName(String buttonName) {
          this.buttonName = buttonName;
          button.setText(buttonName);
     }

     public Color getBgColor() {
          return bgColor;
     }

     public void setBgColor(Color bgColor) {
          this.bgColor = bgColor;
     }
     

    public ButtonDel() {
        initComponents();
        button.setForeground(WindowColor.white);
        button.setFont(WindowFonts.timeNewRomanBold10);
        setBackground(WindowColor.white);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        graphics.setColor(bgColor);
        graphics.fillRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint background
        graphics.setColor(WindowColor.darkred);
        graphics.drawRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint border
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JLabel();

        button.setBackground(new java.awt.Color(255, 255, 255));
        button.setFont(new java.awt.Font("Times New Roman", 1, 8)); // NOI18N
        button.setForeground(new java.awt.Color(16, 107, 67));
        button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        button.setText("Del");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel button;
    // End of variables declaration//GEN-END:variables
}
