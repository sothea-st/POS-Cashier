package Components;

import Constant.JavaConstant;
import Fonts.WindowFonts;

public class labelDis extends javax.swing.JPanel {

    private String labelDiscount;
    public labelDis() {
        initComponents();
        labelDis.setFont(WindowFonts.timeNewRomanBold16);
         JavaConstant.setPointer(labelDis);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDis = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        labelDis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDis.setText("labelDis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelDis, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelDis, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public String getLabelDiscount() {
        return labelDiscount;
    }

    public void setLabelDiscount(String labelDiscount) {
        this.labelDiscount = labelDiscount;
        labelDis.setText(labelDiscount);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDis;
    // End of variables declaration//GEN-END:variables
}
