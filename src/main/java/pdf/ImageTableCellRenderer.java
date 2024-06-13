/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MOBILE-APP.02
 */
public class ImageTableCellRenderer extends DefaultTableCellRenderer {

     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

          if (value instanceof BufferedImage) {
               BufferedImage image = (BufferedImage) value;
               if (image != null) {
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    setIcon(icon);
                    setText(""); // Clear text
               } else {
                    setIcon(null); // Clear icon if the value is null
                    setText(""); // Clear text
               }
          } else {
               setIcon(null); // Clear icon if the value is not an image
               setText(value != null ? value.toString() : ""); // Display text if value is not null
          }

          return component;
     }
}
