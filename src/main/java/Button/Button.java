package Button;

import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author FRONT-END.06
 */
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
    
    
    //=================================================Create Shadow Box
    private ShadowType shadowType;
    private int shadowSize = 1;
    private float shadowOpacity = 0.1f;
    private Color shadowColor = Color.GRAY;
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        setOpaque(false);
        createShadow(grphcs);
        super.paintComponent(grphcs);
    }
    
    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        int width = getWidth() - size;
        int height = getHeight() - size;
        if (shadowType == ShadowType.TOP) {
             x = shadowSize;
             y = size;
        } else if (shadowType == ShadowType.BOT) {
             x = shadowSize;
             y = 0;
        } else if (shadowType == ShadowType.TOP_LEFT) {
             x = size;
             y = size;
        } else if (shadowType == ShadowType.TOP_RIGHT) {
             x = 0;
             y = size;
        } else if (shadowType == ShadowType.BOT_LEFT) {
             x = size;
             y = 0;
        } else if (shadowType == ShadowType.BOT_RIGHT) {
             x = 0;
             y = 0;
        } else {
             //  Center
             x = shadowSize;
             y = shadowSize;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width, height, 10, 10);

        //  Create Shadow
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
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
