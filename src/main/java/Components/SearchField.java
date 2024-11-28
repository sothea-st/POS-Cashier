package Components;

import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 
public class SearchField extends javax.swing.JPanel {

     /**
      * @return the placeholder
      */
     public String getPlaceholder() {
          return placeholder;
     }

     /**
      * @param placeholder the
      * placeholder to set
      */
     public void setPlaceholder(String placeholder) {
          this.placeholder = placeholder;
          textSearch.setText(placeholder);
          textSearch.setForeground(Color.LIGHT_GRAY);

     }

     public void initEvent(ButtonEvent event) {
          textSearch.addFocusListener(new FocusListener() {
               @Override
               public void focusGained(FocusEvent e) {
                    if (textSearch.getText().trim().equals(placeholder)) {
                         textSearch.setText("");
                    }
                    textSearch.setForeground(Color.BLACK);
               }

               @Override
               public void focusLost(FocusEvent e) {
                    if (textSearch.getText().trim().equals("")) {
                         textSearch.setText(placeholder);
                         textSearch.setForeground(Color.LIGHT_GRAY);
                    }

                    if (textSearch.getText().trim().equals(placeholder)) {
                         textSearch.setForeground(Color.LIGHT_GRAY);
                    }
               }
          });

          textSearch.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
               }

               @Override
               public void keyPressed(KeyEvent e) {
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String valueText = textSearch.getText();
                    setValueTextSearch(valueText);
                    event.onKeyType();
                    event.onKeyRelease();
               }
          });
     }

     /**
      * Creates new form SearchField
      */
     public SearchField() {
          initComponents();
          setBackground(WindowColor.white);
          textSearch.setFont(WindowFonts.timeNewRoman14);
          JavaConstant.setPointer(textSearch);
     }

     public void disabledTextField(boolean value) {
          textSearch.setEnabled(value);
     }

     public void setFocus() {
          textSearch.requestFocus();
     }

     //======================Create Shadow Box===========================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadowSearch(grphcs,getWidth(),getHeight(),getBackground());
          super.paintComponent(grphcs);
     }

     

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search Icon.png"))); // NOI18N

        textSearch.setBorder(null);
        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

     private void textSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyReleased

     }//GEN-LAST:event_textSearchKeyReleased

     private String placeholder;
     private String valueTextSearch;

     public String getValueTextSearch() {
          return valueTextSearch;
     }

     public void setValueTextSearch(String valueTextSearch) {
          this.valueTextSearch = valueTextSearch;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
