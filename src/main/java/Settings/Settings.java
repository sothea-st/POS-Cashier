package Settings;

import Constant.JavaBaseUrl;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import LoginAndLogoutForm.LoginFormJdailog;
import Products.ListProduct;
import Setting.Category.Category;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Settings extends javax.swing.JDialog {

    private JPanel panelProduct;
    
    private LoginFormJdailog jdLogin;
    
    public Settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getImageAndTitle();
    }
    
    private void getImageAndTitle(){
        division.setTitle("Division");
        department.setTitle("Department");
        category.setTitle("Category");
        subCategory.setTitle("Sub Category");
        brand.setTitle("Brand");
        item.setTitle("Item");
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    // Task to be executed
                    division.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "division.png");
                    subCategory.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "sub_category.png");
                    brand.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "brand.png");
                    category.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "Category.png");
                    department.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "department.png");
                    item.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "item.png");

                } catch (IOException ex) {
                    Logger.getLogger(ActionProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 500); // Delays task execution by 1 second
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        division = new Components.SettingBox();
        subCategory = new Components.SettingBox();
        department = new Components.SettingBox();
        category = new Components.SettingBox();
        item = new Components.SettingBox();
        brand = new Components.SettingBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                divisionMouseClicked(evt);
            }
        });

        subCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subCategoryMouseClicked(evt);
            }
        });

        department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                departmentMouseClicked(evt);
            }
        });

        category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryMouseClicked(evt);
            }
        });

        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_divisionMouseClicked
        Category cat = new Category(new JFrame(), true, "division");
        cat.setCode("division");
        cat.setVisible(true);
    }//GEN-LAST:event_divisionMouseClicked

    private void categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryMouseClicked
        Category cat = new Category(new JFrame(), true, "category");
        cat.setCode("category");
        cat.setVisible(true);
    }//GEN-LAST:event_categoryMouseClicked

    private void itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMouseClicked
        ListProduct list = new ListProduct(new JFrame(), true);
        list.setPanelProduct(panelProduct);
        list.setJdLogin(jdLogin);
        list.setPanelCategory(category);
        list.setVisible(true);
    }//GEN-LAST:event_itemMouseClicked

    private void departmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentMouseClicked
        Category cat = new Category(new JFrame(), true, "department");
        cat.setCode("department");
        cat.setVisible(true);
    }//GEN-LAST:event_departmentMouseClicked

    private void subCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subCategoryMouseClicked
        Category cat = new Category(new JFrame(), true, "subcategory");
        cat.setCode("subcategory");
        cat.setVisible(true);
    }//GEN-LAST:event_subCategoryMouseClicked

    public JPanel getPanelProduct() {
        return panelProduct;
    }

    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    public LoginFormJdailog getJdLogin() {
        return jdLogin;
    }

    public void setJdLogin(LoginFormJdailog jdLogin) {
        this.jdLogin = jdLogin;
    }

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Settings dialog = new Settings(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.SettingBox brand;
    private Components.SettingBox category;
    private Components.SettingBox department;
    private Components.SettingBox division;
    private Components.SettingBox item;
    private Components.SettingBox subCategory;
    // End of variables declaration//GEN-END:variables
}
