package Setting.Country;

import Constant.JavaConstant;
import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GetCountry extends javax.swing.JPanel {

     private Integer id;
     private String country;
     private Icon flag;
     private Icon iconEdit;
     private Icon iconDelete;

     public GetCountry() {
          initComponents();
          lbId.setVisible(false);
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
          lbId.setText("" + id);
     }

     public String getCountry() {
          return country;
     }

     public void setCountry(String country) {
          this.country = country;
          lbCountry.setText(country);
     }

     public Icon getFlag() {
          return flag;
     }

     public void setFlag(Icon flag) {
          this.flag = flag;
          imgFlag.setIcon(flag);
     }

     public void setFlag(String url) throws IOException {
          JavaConstant.coverImage(url, imgFlag, 30, 15);
     }

     public void setDefaultImage() throws IOException {
          ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("productImage/flag.png"));
          imgFlag.setIcon(icon);
     }

     public Icon getIconEdit() {
          return iconEdit;
     }

     public void setIconEdit(Icon iconEdit) {
          this.iconEdit = iconEdit;
          btnEdit.setIcon(iconEdit);
     }

     public Icon getIconDelete() {
          return iconDelete;
     }

     public void setIconDelete(Icon iconDelete) {
          this.iconDelete = iconDelete;
          btnDelete.setIcon(iconDelete);
     }

     public void initEvent(ButtonEvent event) {
          btnEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelect("" + id);
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

          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onRemove("" + id);
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

        panel = new javax.swing.JPanel();
        imgFlag = new javax.swing.JLabel();
        lbCountry = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        imgFlag.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        imgFlag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbCountry.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCountry.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCountry.setText("Country");

        lbId.setText("jLabel1");

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lbCountry, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbId)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(imgFlag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCountry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel imgFlag;
    private javax.swing.JLabel lbCountry;
    private javax.swing.JLabel lbId;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
