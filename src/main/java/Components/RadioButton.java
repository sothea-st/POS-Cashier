
package Components;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;

/**
 *
 * @author FRONT-END.06
 */
public class RadioButton extends javax.swing.JPanel {

    /**
     * @return the radioButton
     */
    public String getRadioButton() {
        return radioButton;
    }

    /**
     * @param radioButton the radioButton to set
     */
    public void setRadioButton(String radioButton) {
        this.radioButton = radioButton;
        jRadioButton.setText(radioButton);
    }

    /**
     * Creates new form RadioButton
     */
    public RadioButton() {
        initComponents();
        setBackground(WindowColor.mediumGreen);
        jRadioButton.setBackground(WindowColor.mediumGreen);
        jRadioButton.setFont(WindowFonts.timeNewRomanBold14);
        jRadioButton.setForeground(WindowColor.black);
      
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton = new javax.swing.JRadioButton();

        jRadioButton.setText("jRadioButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private String radioButton;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton jRadioButton;
    // End of variables declaration//GEN-END:variables
}
