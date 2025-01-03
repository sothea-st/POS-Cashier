
package Button;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author FRONT-END.06
 */
public class ButtonDiscount extends javax.swing.JPanel {

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
        discount.setText(discountPrice);
    }

    /**
     * Creates new form ButtonDiscount
     */
    public ButtonDiscount() {
        initComponents();
        discount.setForeground(WindowColor.white);
        discount.setFont(WindowFonts.timeNewRomanBold8);
        setBackground(WindowColor.white);
    }
    
    private String discountPrice;

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        graphics.setColor(WindowColor.black);
        graphics.fillRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint background
        graphics.setColor(WindowColor.black);
        graphics.drawRoundRect(0, 0, width-2, height-2, arcs.width, arcs.height);//paint border
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        discount = new javax.swing.JLabel();

        discount.setBackground(new java.awt.Color(255, 255, 255));
        discount.setFont(new java.awt.Font("Times New Roman", 1, 8)); // NOI18N
        discount.setForeground(new java.awt.Color(16, 107, 67));
        discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discount.setText("Was $10.10 ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel discount;
    // End of variables declaration//GEN-END:variables
}
