package Components;

import Combobox.ComboItem;
import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
          combo.setSelectedIndex(index);
          
     }
     
     public void removeAllItem() {
          int length = countItem();
          for (int i = 0; i < length; i++) {
               if( i == 0  ) continue;
               combo.removeItemAt(i);
          }
     }
     
      public void removeAllItemAndSetOption(String selectOption) {
          int length = countItem();
          for (int i = 0; i < length; i++) {
               combo.removeItemAt(i);
          }
          combo.addItem(selectOption);
     }
     
     public int countItem() {
          return combo.getItemCount();
     }

     //=====================Create Shadow Box============================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
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
