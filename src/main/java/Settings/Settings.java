package Settings;

import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import CustomeUI.CustomScrollBarUI;
import LoginAndLogoutForm.LoginFormJdailog;
import Products.ListProduct;
import Setting.Attribute.ListAttribute;
import Setting.Brand.ListBrand;
import Setting.Category.Category;
import Setting.Country.ListCountry;
import Setting.Tax.ListTax;
import Setting.Uom.listUom;
import Setting.Vendor.ListVendor;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Settings extends javax.swing.JDialog {

    private JPanel panelProduct;
    
    private LoginFormJdailog jdLogin;
    
    public Settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getImageAndTitle();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        
        JavaConstant.addTitleAndLogo(this, "Settings");
    }
    
    private void getImageAndTitle(){
        division.setTitle("Division");
        department.setTitle("Department");
        category.setTitle("Category");
        subCategory.setTitle("Sub Category");
        brand.setTitle("Brand");
        product.setTitle("Product");
        vendor.setTitle("Vendor");
        attribute.setTitle("Attribute");
        uom.setTitle("UOM");
        country.setTitle("Country");
        tax.setTitle("Tax");
        
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
                    product.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "item.png");
                    vendor.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "vendor.png");
                    attribute.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "attribute.png");
                    uom.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "measure.png");
                    country.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "countries.png");
                    tax.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "taxes.png");

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        division = new Components.SettingBox();
        department = new Components.SettingBox();
        category = new Components.SettingBox();
        subCategory = new Components.SettingBox();
        product = new Components.SettingBox();
        brand = new Components.SettingBox();
        vendor = new Components.SettingBox();
        attribute = new Components.SettingBox();
        uom = new Components.SettingBox();
        country = new Components.SettingBox();
        tax = new Components.SettingBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                divisionMouseClicked(evt);
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

        subCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subCategoryMouseClicked(evt);
            }
        });

        product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productMouseClicked(evt);
            }
        });

        brand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brandMouseClicked(evt);
            }
        });

        vendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorMouseClicked(evt);
            }
        });

        attribute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attributeMouseClicked(evt);
            }
        });

        uom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uomMouseClicked(evt);
            }
        });

        country.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                countryMouseClicked(evt);
            }
        });

        tax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
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

    private void productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productMouseClicked
        dispose();
        ListProduct list = new ListProduct(new JFrame(), true);
        list.setPanelProduct(panelProduct);
        list.setJdLogin(jdLogin);
        list.setPanelCategory(category);
        list.setVisible(true);
        
    }//GEN-LAST:event_productMouseClicked

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

    private void brandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandMouseClicked
        ListBrand brand = new ListBrand(new JFrame(), true);
        brand.setVisible(true);
    }//GEN-LAST:event_brandMouseClicked

    private void vendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorMouseClicked
        ListVendor vendor = new ListVendor(new JFrame(), true);
        vendor.setVisible(true);
    }//GEN-LAST:event_vendorMouseClicked

    private void attributeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attributeMouseClicked
        ListAttribute attribute = new ListAttribute(new JFrame(), true);
        attribute.setVisible(true);
        
    }//GEN-LAST:event_attributeMouseClicked

    private void uomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uomMouseClicked
        listUom uom = new listUom(new JFrame(), true);
        uom.setVisible(true);
    }//GEN-LAST:event_uomMouseClicked

    private void countryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_countryMouseClicked
        ListCountry list = new ListCountry(new JFrame(), true);
        list.setVisible(true);
    }//GEN-LAST:event_countryMouseClicked

    private void taxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxMouseClicked
        ListTax list = new ListTax(new JFrame(), true);
        list.setVisible(true);
    }//GEN-LAST:event_taxMouseClicked

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
    private Components.SettingBox attribute;
    private Components.SettingBox brand;
    private Components.SettingBox category;
    private Components.SettingBox country;
    private Components.SettingBox department;
    private Components.SettingBox division;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.SettingBox product;
    private Components.SettingBox subCategory;
    private Components.SettingBox tax;
    private Components.SettingBox uom;
    private Components.SettingBox vendor;
    // End of variables declaration//GEN-END:variables
}
