/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomeUI;

import Color.WindowColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author MOBILE-APP.02
 */
public class CustomScrollBarUI extends BasicScrollBarUI {
     private final Dimension dim = new Dimension();


     @Override
     protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
          Graphics2D g2 = (Graphics2D) g.create();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

          Color color = WindowColor.gray;
//          if (isThumbRollover()) {
//               color = WindowColor.darkGreen;
//          }

          g2.setColor(color);
          g2.fillRoundRect(thumbBounds.x, thumbBounds.y, 10, thumbBounds.height, 0, 0);
          g2.dispose();
     }

     @Override
     protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
          g.setColor(WindowColor.slightGreen);
          g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
     }

     @Override
     protected JButton createDecreaseButton(int orientation) {
          return createZeroButton();
     }

     @Override
     protected JButton createIncreaseButton(int orientation) {
          return createZeroButton();
     }

     private JButton createZeroButton() {
          JButton button = new JButton();
          button.setPreferredSize(dim);
          button.setMinimumSize(dim);
          button.setMaximumSize(dim);
          return button;
     }
}
