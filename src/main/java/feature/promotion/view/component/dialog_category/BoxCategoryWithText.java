package feature.promotion.view.component.dialog_category;

import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.promotion.model.CategoryResponse.CategoryResponseDetail;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoxCategoryWithText extends javax.swing.JPanel {

     private Boolean isCheck;
     private CategoryResponseDetail detail;
     private Integer categoryId;

     public BoxCategoryWithText(CategoryResponseDetail detail) {

          this.detail = detail;
          
          initComponents();
          
          lbName.setText(detail.getCatNameEn());
          categoryId = detail.getId();

          custom();
     }

     public void setCheckBox(Boolean isCheck) {
          this.isCheck = isCheck;
          
          ImageIcon icon = null;
          if (isCheck) {
               icon = new ImageIcon(getClass().getResource("/icon/checked.png"));
          } else {
               icon = new ImageIcon(getClass().getResource("/icon/check.png"));
          }
          checkBox.setIcon(icon);
     }

     private void custom() {
          lbName.setFont(WindowFonts.timeNewRomanBold14);
          JavaConstant.setPointer(this);
     }

     public void initEvent(ButtonEvent event) {
          addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onMouseClick();
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

          jPanel1 = new javax.swing.JPanel();
          checkBox = new javax.swing.JLabel();
          lbName = new javax.swing.JLabel();

          checkBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          lbName.setText("Select Category");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel checkBox;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JLabel lbName;
     // End of variables declaration//GEN-END:variables
}
