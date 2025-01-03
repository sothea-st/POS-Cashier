package FormComponent.datepicker;

import Components.Color.WindowColor;
import Components.Fonts.WindowFonts;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;

import javax.swing.UIManager;
import raven.datetime.component.date.DatePicker;

public class JavaDatePicker extends javax.swing.JPanel {

     private String titleLabel;
     private String labelName;
     private String valueTextField = "";
     private DatePicker datePicker;

     public JavaDatePicker() {
          initComponents();

          // Set rounded corners for FlatLaf text components 1 px
          UIManager.put("TextComponent.arc", 5);

          // label error
          lbError.setVisible(false);
          lbError.setFont(WindowFonts.timeNewRomanBold12);

          label.setFont(WindowFonts.timeNewRomanBold14);

          txtDate.putClientProperty(FlatClientProperties.STYLE, ""
               + "arc:10;");

          datePicker = new raven.datetime.component.date.DatePicker();
          datePicker.setCloseAfterSelected(false);
          datePicker.setEditor(txtDate);
          datePicker.setDateFormat("dd-MM-yyyy");
          datePicker.now();

          for (Component component : datePicker.getComponents()) {
               if (component instanceof JButton && "Clear".equals(((JButton) component).getToolTipText())) {
                    component.setVisible(false);
               }
          }

          setBackground(WindowColor.mediumGreen);

     }

     public String getLabelName() {
          return labelName;
     }

     public void setLabelName(String labelName) {
          this.labelName = labelName;
          if (labelName.contains("*")) {
               labelName = labelName.replace("*", "");
               label.setText("<html>" + labelName + " <span style='color:red;font-size:16;'>*</span></html>");
          } else {
               label.setText(labelName);
          }
     }

     public void setFieldError(boolean value) {
          lbError.setVisible(value);
     }

     public void setFieldError(String text) {
          lbError.setText(text);
          lbError.setVisible(true);
     }

     // Set the date value programmatically when you already have a date string (e.g., "dd-MM-yyyy")
     public void setSelectedDate(String dateValue) {
          try {
               // Define the date format
               SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

               // Parse the string into a Date object
               Date parsedDate = formatter.parse(dateValue);

               // Convert java.util.Date to java.time.LocalDate
               LocalDate localDate = parsedDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

               // Set the LocalDate in the DatePicker
               datePicker.setSelectedDate(localDate);

          } catch (Exception e) {
               // Handle potential parsing errors
               e.printStackTrace();
          }
     }

     public String getSelectedDate() {
          return datePicker.getSelectedDate().toString();
     }

     // Method to set the red border for JTextField
     public void setErrorBorder() {
          txtDate.putClientProperty(FlatClientProperties.STYLE, "borderColor:#FF0000;");
     }

     // Method to reset the border to the default color for JTextField
     public void resetError() {
          txtDate.putClientProperty(FlatClientProperties.STYLE, "");
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbError = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        txtDate = new javax.swing.JFormattedTextField();

        lbError.setText("The field email is required.");
        lbError.setPreferredSize(new java.awt.Dimension(63, 20));

        label.setText("label Name");

        txtDate.setPreferredSize(new java.awt.Dimension(64, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JLabel lbError;
    private javax.swing.JFormattedTextField txtDate;
    // End of variables declaration//GEN-END:variables
}
