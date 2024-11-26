package Setting.Uom;

import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JLabel;

public class GetUom extends javax.swing.JPanel {

    private Integer id;
    private String uomNameKh;
    private String uomNameEn;
    private String uomValue;
    private Icon iconEdit;
    private Icon iconDelete;
    
    public GetUom() {
        initComponents();
        lbId.setVisible(false);
    }

     public JLabel getLbId() {
          return lbId;
     }

     public void setLbId(JLabel lbId) {
          this.lbId = lbId;
     }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        lbId.setText(""+id);
    }

    public String getUomNameKh() {
        return uomNameKh;
    }

    public void setUomNameKh(String uomNameKh) {
        this.uomNameKh = uomNameKh;
        lbUomNameKh.setText(uomNameKh);
        lbUomNameKh.setFont(WindowFonts.khmerOsContent12);
        
    }

    public String getUomNameEn() {
        return uomNameEn;
    }

    public void setUomNameEn(String uomNameEn) {
        this.uomNameEn = uomNameEn;
        lbUomName.setText(uomNameEn);
    }

    public String getUomValue() {
        return uomValue;
    }

    public void setUomValue(String uomValue) {
        this.uomValue = uomValue;
        lbUomValue.setText(uomValue);
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
                    event.onRemove(""+id);
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
        lbUomNameKh = new javax.swing.JLabel();
        lbUomName = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        lbUomValue = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        lbUomNameKh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbUomNameKh.setForeground(new java.awt.Color(0, 0, 0));
        lbUomNameKh.setText("UOM Name Kh");

        lbUomName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbUomName.setForeground(new java.awt.Color(0, 0, 0));
        lbUomName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUomName.setText("UOM Name En");

        lbId.setText("jLabel1");

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N

        lbUomValue.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbUomValue.setForeground(new java.awt.Color(0, 0, 0));
        lbUomValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUomValue.setText("UOM Value");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbUomName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUomNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUomValue, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbUomValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbUomName)
                        .addComponent(lbId)
                        .addComponent(btnEdit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbUomNameKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbUomNameKh.getAccessibleContext().setAccessibleName("");
        lbUomName.getAccessibleContext().setAccessibleName("Uom Name En");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbUomName;
    private javax.swing.JLabel lbUomNameKh;
    private javax.swing.JLabel lbUomValue;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
