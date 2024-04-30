package Button;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author FRONT-END.06
 */
public class ButtonAddProduct extends javax.swing.JPanel {

     /**
      * @return the quantity
      */
     public int getQuantity() {
          return quantity;
     }

     /**
      * @param quantity the quantity to
      * set
      */
     public void setQuantity(int quantity) {
          this.quantity = quantity;
          lbQty.setText("" + quantity);
     }

     public ButtonAddProduct() {
          initComponents();
          setBackground(WindowColor.white);
          JavaConstant.setPointer(btnPlus);
          JavaConstant.setPointer(btnMinus);

     }

     public JLabel getLbQty() {
          return lbQty;
     }

     public void setLbQty(JLabel lbQty) {
          this.lbQty = lbQty;
     }

     
     
     protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Dimension arcs = new Dimension(15, 15); //Border corners arcs {width,height}, change this to whatever you want
          int width = getWidth();
          int height = getHeight();
          Graphics2D graphics = (Graphics2D) g;
          graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

          //Draws the rounded panel with borders.
          graphics.setColor(WindowColor.white);
          graphics.fillRoundRect(0, 0, width - 2, height - 2, arcs.width, arcs.height);//paint background
          graphics.setColor(WindowColor.darkGreen);
          graphics.drawRoundRect(0, 0, width - 2, height - 2, arcs.width, arcs.height);//paint border
     }

     private int quantity;

     //=================================================Create Shadow Box
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
//            x = shadowSize;
//            y = size;
//        } else if (shadowType == ShadowType.BOT) {
//            x = shadowSize;
//            y = 0;
//        } else if (shadowType == ShadowType.TOP_LEFT) {
//            x = size;
//            y = size;
//        } else if (shadowType == ShadowType.TOP_RIGHT) {
//            x = 0;
//            y = size;
//        } else if (shadowType == ShadowType.BOT_LEFT) {
//            x = size;
//            y = 0;
//        } else if (shadowType == ShadowType.BOT_RIGHT) {
//            x = 0;
//            y = 0;
//        } else {
//            //  Center
//            x = shadowSize;
//            y = shadowSize;
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
     // new 18-01-2024 (hello world)
     public void initEvent(ButtonEvent event) {
          btnPlus.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.btnPlus();
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });
          btnMinus.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.btnMinus();
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMinus = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        btnPlus = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(100, 32));

        btnMinus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnMinus.setForeground(new java.awt.Color(16, 107, 67));
        btnMinus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinus.setText("-");

        lbQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbQty.setForeground(new java.awt.Color(16, 107, 67));
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("1");

        btnPlus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(16, 107, 67));
        btnPlus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPlus.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinus, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(lbQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnMinus;
    private javax.swing.JLabel btnPlus;
    private javax.swing.JLabel lbQty;
    // End of variables declaration//GEN-END:variables
}
