package Components;

import Combobox.ComboItem;
import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBox extends javax.swing.JPanel {

     private HashMap<String, String> map;

     public int getItemCount() {
          return combo.getItemCount();
     }

     public HashMap<String, String> getMap() {
          return map;
     }

     public void setMap(HashMap<String, String> map) {
          this.map = map;

          for (String key : map.keySet()) {
               ComboItem cm = new ComboItem(key, map.get(key));
               combo.addItem(cm.getKey());
               combo.setFont(WindowFonts.timeNewRoman14);
          }

     }

     public ComboBox() {
          initComponents();
          setBackground(WindowColor.white);

          //Delete Border Combobox
          combo.setUI(new BasicComboBoxUI());
          JavaConstant.setPointer(combo);
     }

     public void initEvent(ButtonEvent events) {
          combo.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    String itemName = combo.getSelectedItem().toString();
                    events.onSelect(map.get(itemName));
                    events.onSelectBreadcrumb(map.get(itemName), itemName);
               }
          });
     }

     public void setToFirstItem() {
          combo.setSelectedIndex(0);
     }

     public void setToLastItem(int id) {
          int index = 0;
          if (!map.isEmpty()) {
               for (String key : map.keySet()) {
                    if (map.get(key).equals(String.valueOf(id))) {
                         index++;
                         break;
                    }
                    index++;
                   
               }
          }
          combo.setSelectedIndex(index);

     }

     public void setToLastItem(String id) {
          int index = 0;
          for (String key : map.keySet()) {
               if (map.get(key).equals(String.valueOf(id))) {
                    index++;
                    break;
               }
               index++;
            
          }

//          System.out.println("count = " + count);
          combo.setSelectedIndex(index);

     }

     public void removeAllItem() {

     }

     public int countItem() {
          return combo.getItemCount();
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

          combo = new javax.swing.JComboBox<>();

          combo.setBackground(new java.awt.Color(255, 255, 255));
          combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
          combo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
          combo.setFocusable(false);
          combo.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    comboMouseClicked(evt);
               }
          });
          combo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    comboActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(combo, 0, 202, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                    .addContainerGap())
          );
     }// </editor-fold>//GEN-END:initComponents

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed

    }//GEN-LAST:event_comboActionPerformed

     private void comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMouseClicked

     }//GEN-LAST:event_comboMouseClicked


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JComboBox<String> combo;
     // End of variables declaration//GEN-END:variables
}
