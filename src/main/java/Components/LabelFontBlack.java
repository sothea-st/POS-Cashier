
package Components;

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
public class LabelFontBlack extends javax.swing.JPanel {

    private String labelName;
    private Color fontColor;
    
    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
        lbLabel.setForeground(fontColor);
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
        lbLabel.setText(labelName);
    }

    /**
     * Creates new form LabelFontBlack
    */
    public LabelFontBlack() {
        initComponents();
        setBackground(WindowColor.mediumGreen);
        lbLabel.setFont(WindowFonts.timeNewRomanBold14);
        lbLabel.setForeground(WindowColor.black);
         JavaConstant.setPointer(lbLabel);
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

        lbLabel = new javax.swing.JLabel();

        lbLabel.setForeground(new java.awt.Color(255, 255, 255));
        lbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbLabel;
    // End of variables declaration//GEN-END:variables
}
