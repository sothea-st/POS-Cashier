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
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author MOBILE-APP.02
 */
public class CustomScrollBarUI extends BasicScrollBarUI {

     private final Dimension dim = new Dimension();
     private final int SCROLL_BAR_SIZE = 12; // Change the size of the scroll bar here
     private final int TRACK_HEIGHT = 4; // Change the height of the track here
     private final int TRACK_ROUNDING = 2; // Change the rounding of the track corners here

     @Override
     protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
          Graphics2D g2 = (Graphics2D) g.create();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

          // Calculate the height of the thumb based on the visible portion of the content
          int visibleAmount = scrollbar.getVisibleAmount();
          int thumbHeight = Math.max((int) (((double) visibleAmount / (double) scrollbar.getMaximum()) * thumbBounds.height), SCROLL_BAR_SIZE);

          g2.setColor(Color.GRAY); // Change the color of the thumb here
          g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5); // Change the shape of the thumb here

          g2.dispose();
     }

     @Override
     protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
//          Graphics2D g2 = (Graphics2D) g.create();
//          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//          g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//
//          g2.setColor(Color.LIGHT_GRAY); // Change the color of the track here
//          int trackY = trackBounds.y + (trackBounds.height - TRACK_HEIGHT) / 2;
//          g2.fillRoundRect(trackBounds.x, trackY, trackBounds.width, TRACK_HEIGHT, TRACK_ROUNDING, TRACK_ROUNDING); // Adjust the width of the track here
//
//          g2.dispose();
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

//     private final Dimension dim = new Dimension();
//
//     @Override
//     protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
//          Graphics2D g2 = (Graphics2D) g.create();
//          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//          g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//
//          Color color = WindowColor.gray;
////          if (isThumbRollover()) {
////               color = WindowColor.darkGreen;
////          }
//
//          g2.setColor(color);
//          g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 0, 0);
//          g2.dispose();
//     }
//
//     @Override
//     protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
////          Graphics2D g2 = (Graphics2D) g;
////          g2.setColor(WindowColor.gray);
////          g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 0, 0);
////          g2.dispose();
//     }
//
//     @Override
//     protected JButton createDecreaseButton(int orientation) {
//          return createZeroButton();
//     }
//
//     @Override
//     protected JButton createIncreaseButton(int orientation) {
//          return createZeroButton();
//     }
//
//     private JButton createZeroButton() {
//          JButton button = new JButton();
//          button.setPreferredSize(dim);
//          button.setMinimumSize(dim);
//          button.setMaximumSize(dim);
//          return button;
//     }
}
