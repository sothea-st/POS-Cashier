package Button;

import Components.Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import Components.Fonts.WindowFonts;
import java.awt.Dimension;

/**
 *
 * @author FRONT-END.06
 */
public class ButtonBuy extends javax.swing.JPanel {

    /**
     * @return the buttonName
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * @param buttonName the buttonName to set
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
        button.setText(buttonName);
    }

    /**
     * Creates new form ButtonBuy
     */
    public ButtonBuy() {
        initComponents();
        button.setForeground(WindowColor.darkGreen);
        button.setFont(WindowFonts.timeNewRomanBold10);
        setBackground(WindowColor.white);
        JavaConstant.setPointer(button);
    }
    
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        graphics.setColor(WindowColor.white);
        graphics.fillRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint background
        graphics.setColor(WindowColor.darkGreen);
        graphics.drawRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint border
    }

//   //=================================================Create Shadow Box
//    private ShadowType shadowType;
//    private int shadowSize = 3;
//    private float shadowOpacity = 0.8f;
//    private Color shadowColor = WindowColor.darkGreen;
//    
//    @Override
//    protected void paintComponent(Graphics grphcs) {
//        setOpaque(false);
//        createShadow(grphcs);
//        super.paintComponent(grphcs);
//    }
//    
//    private void createShadow(Graphics grphcs) {
//        Graphics2D g2 = (Graphics2D) grphcs;
//        int size = shadowSize * 2;
//        int x = 0;
//        int y = 0;
//        int width = getWidth() - size;
//        int height = getHeight() - size;
//        if (shadowType == ShadowType.TOP) {
//             x = shadowSize;
//             y = size;
//        } else if (shadowType == ShadowType.BOT) {
//             x = shadowSize;
//             y = 0;
//        } else if (shadowType == ShadowType.TOP_LEFT) {
//             x = size;
//             y = size;
//        } else if (shadowType == ShadowType.TOP_RIGHT) {
//             x = 0;
//             y = size;
//        } else if (shadowType == ShadowType.BOT_LEFT) {
//             x = size;
//             y = 0;
//        } else if (shadowType == ShadowType.BOT_RIGHT) {
//             x = 0;
//             y = 0;
//        } else {
//             //  Center
//             x = shadowSize;
//             y = shadowSize;
//        }
//        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = img.createGraphics();
//        g.setColor(getBackground());
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.fillRoundRect(0, 0, width, height, 40, 40);
//
//        //  Create Shadow
//        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
//        g2.drawImage(render.createShadow(img), 0, 0, null);
//        g2.drawImage(img, x, y, null);
//    }
    
    private String buttonName;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JLabel();

        button.setBackground(new java.awt.Color(255, 255, 255));
        button.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        button.setForeground(new java.awt.Color(16, 107, 67));
        button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        button.setText("Buy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel button;
    // End of variables declaration//GEN-END:variables
}
