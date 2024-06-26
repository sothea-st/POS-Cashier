package Setting.Category;

import Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class GetCategory extends javax.swing.JPanel {

    private Integer id;
    private String CategoryNameKh;
    private String CategoryNameEn;
    private Icon iconEdit;
    private Icon iconDelete;
    
    public GetCategory() {
        initComponents();
        lbId.setVisible(false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        lbId.setText(""+id);
    }

    public String getCategoryNameKh() {
        return CategoryNameKh;
    }

    public void setCategoryNameKh(String CategoryNameKh) {
        this.CategoryNameKh = CategoryNameKh;
        lbCategoryKh.setText(CategoryNameKh);
        
    }

    public String getCategoryNameEn() {
        return CategoryNameEn;
    }

    public void setCategoryNameEn(String CategoryNameEn) {
        this.CategoryNameEn = CategoryNameEn;
        lbCategoryEn.setText(CategoryNameEn);
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
        lbCategoryKh = new javax.swing.JLabel();
        lbCategoryEn = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        lbCategoryKh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCategoryKh.setForeground(new java.awt.Color(0, 0, 0));
        lbCategoryKh.setText("Category Name Kh");

        lbCategoryEn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCategoryEn.setForeground(new java.awt.Color(0, 0, 0));
        lbCategoryEn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCategoryEn.setText("Category Name En");

        lbId.setText("jLabel1");

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon("D:\\POSCASHIERMASTER\\tt_pos_window\\src\\main\\resources\\image\\Edit.png")); // NOI18N

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon("D:\\POSCASHIERMASTER\\tt_pos_window\\src\\main\\resources\\image\\DeleteIcon.png")); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCategoryEn, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCategoryKh, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCategoryKh)
                        .addComponent(lbCategoryEn)
                        .addComponent(lbId)
                        .addComponent(btnEdit)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
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
    private javax.swing.JLabel lbCategoryEn;
    private javax.swing.JLabel lbCategoryKh;
    private javax.swing.JLabel lbId;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
