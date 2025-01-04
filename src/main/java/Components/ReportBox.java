package Components;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import java.math.BigDecimal;

/**
 *
 * @author FRONT-END.06
 */
public class ReportBox extends javax.swing.JPanel {

    private String title;
    private String quantity;
    private String totalpice;
    
    public ReportBox() {
        initComponents();
        name.setForeground(WindowColor.black);
        name.setFont(WindowFonts.timeNewRoman10);
        qty.setForeground(WindowColor.black);
        qty.setFont(WindowFonts.timeNewRoman10);
        total.setForeground(WindowColor.black);
        total.setFont(WindowFonts.timeNewRoman10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        qty = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        name = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        qty.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        qty.setForeground(new java.awt.Color(0, 0, 0));
        qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qty.setText("75");

        total.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        total.setForeground(new java.awt.Color(0, 0, 0));
        total.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        total.setText("$ 680.74");

        name.setBackground(new java.awt.Color(255, 0, 0));
        name.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qty, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(qty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        name.setText("<html>"+title+"</html>");
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        qty.setText(quantity);
    }

    public String getTotalpice() {
        return totalpice;
    }

    public void setTotalpice(String totalpice) {
        this.totalpice = totalpice;
        total.setText(totalpice);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel name;
    private javax.swing.JLabel qty;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
