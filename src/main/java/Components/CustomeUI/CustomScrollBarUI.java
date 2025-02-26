/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components.CustomeUI;

import Components.Color.WindowColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBarUI extends BasicScrollBarUI {

     // Set the preferred size of the scrollbar
     @Override
     public Dimension getPreferredSize(JComponent c) {
          if (((JScrollBar) c).getOrientation() == JScrollBar.HORIZONTAL) {
               return new Dimension(10, 10); // Height of horizontal scrollbar
          } else {
               return new Dimension(8, 20); // Width of vertical scrollbar
          }
     }

     // Paint the track (the area behind the thumb)
     @Override
     protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
          g.setColor(WindowColor.slightGreen); // Set track color
          g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height); // Fill track
     }

     // Paint the thumb (the draggable part of the scrollbar)
     @Override
     protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
          g.setColor(Color.GRAY); // Set thumb color
          g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 0, 0); // Round thumb
     }

     // Paint the buttons (arrows at the ends of the scrollbar)
     protected void paintButton(Graphics g, JComponent c, Rectangle buttonBounds, int direction) {
          // Override this method if you want to customize the scrollbar buttons
     }

     // Create decrease button
     @Override
     protected JButton createDecreaseButton(int orientation) {
          return createZeroButton();
     }

     // Create increase button
     @Override
     protected JButton createIncreaseButton(int orientation) {
          return createZeroButton();
     }

     // Helper method to create zero-sized button
     private JButton createZeroButton() {
          JButton button = new JButton();
          Dimension zeroDim = new Dimension(0, 0);
          button.setPreferredSize(zeroDim);
          button.setMinimumSize(zeroDim);
          button.setMaximumSize(zeroDim);
          return button;
     }

     // Apply custom scrollbar UI to JScrollPane or JScrollBar
     public static void applyCustomScrollBarUI(JScrollPane scrollPane) {
          scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
     }
}
