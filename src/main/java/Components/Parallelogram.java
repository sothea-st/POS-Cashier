package Components;

import Components.Color.WindowColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 *
 * @author FRONT-END.06
 */
public class Parallelogram extends javax.swing.JPanel {

    private Path2D.Double parallelogram;
    private String paralleLogramTitle;

    public Parallelogram() {
        initComponents();
        parallelogram = new Path2D.Double();
        parallelogram.moveTo(100,0);
        parallelogram.lineTo(200,0);
        parallelogram.lineTo(100,100);
        parallelogram.lineTo(0,100);
//        parallelogram.closePath();
        setBackground(WindowColor.slightGreen);
        jLabel1.setForeground(WindowColor.white);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(WindowColor.green);
        g2d.fill(parallelogram);
    }

    public String getParalleLogramTitle() {
        return paralleLogramTitle;
    }

    public void setParalleLogramTitle(String paralleLogramTitle) {
        this.paralleLogramTitle = paralleLogramTitle;
        jLabel1.setText(paralleLogramTitle);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
