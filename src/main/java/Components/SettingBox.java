package Components;

 
import Constant.JavaConstant;
import Constant.UtilShadow;
import Fonts.WindowFonts;
 
import java.awt.Graphics;
 
import java.io.IOException;
import javax.swing.Icon;


public class SettingBox extends javax.swing.JPanel {

    private String title;
    private Icon iconImage;

    public SettingBox() {
        initComponents();
        labelTitle.setFont(WindowFonts.timeNewRomanBold14);
    }

    //=================================================Create Shadow Box
    @Override
    protected void paintComponent(Graphics grphcs) {
        setOpaque(false);
        UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
        super.paintComponent(grphcs);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        labelTitle.setText(title);
    }

    public Icon getIconImage() {
        return iconImage;
    }

    public void setIconImage(Icon iconImage) {
        this.iconImage = iconImage;
        img.setIcon(iconImage);
    }
    
    public void setIconImage(String url) throws IOException {
          JavaConstant.coverImage(url, img, 120, 85);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Setting");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables
}
