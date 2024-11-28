package FormComponent;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import lombok.Getter;
import lombok.Setter;
 

@Setter
@Getter
public class JavaTextField extends javax.swing.JPanel {

    private String labelName;
    private String placeHolder;
    private String valueTextField = null;
    private String typeTextField;
    private String email = "Email";
    private String phoneNumber = "phoneNumber";
    private String amount = "Amount";
    public static String invalidAmount = "Invalid amount!";

    public JavaTextField() {
        initComponents();
        init();
        initEvent(); // remember must be call method initEvent without paramater to do action typing and get text 
    }

    private void init() {
        label.setFont(WindowFonts.timeNewRomanBold14); // set font
 
        // label error
        lbError.setVisible(false);
        lbError.setForeground(WindowColor.red);
        lbError.setFont(WindowFonts.timeNewRomanBold14);
        txt.putClientProperty(FlatClientProperties.STYLE, "arc:10;");
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

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
        // set placehoder to txt
        txt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolder);
        txt.setFont(WindowFonts.timeNewRoman14);
    }

    // Method to set the red border for JTextField
    public void setErrorBorder() {
        txt.putClientProperty(FlatClientProperties.STYLE, "borderColor:#FF0000;");
    }

    // Method to reset the border to the default color for JTextField
    public void resetError() {
        txt.putClientProperty(FlatClientProperties.STYLE, "");
    }

    public void setText(String value) {
        if (JavaConstant.containsKhmer(value)) {
            txt.setFont(WindowFonts.khmerOsContent12);
        } else {
            txt.setFont(WindowFonts.timeNewRoman14);
        }

        txt.setText(value);
        setValueTextField(value);
    }

//    public void resetTextValue() {
//        setValueTextField("");
//        txt.setText(null);
//    }

    public void setFocus() {
        // Ensure the focus is requested after the UI is shown
//        SwingUtilities.invokeLater(() -> {
//            txt.requestFocusInWindow();  // This ensures the first text field gets focus
//        });
    }

    // Validate Email Method
    public void setValidateEmail() {

        typeTextField = email; // for check validation 
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (txt.getText().isEmpty()) {
                    lbError.setVisible(false); // Hide error if valid
                    return;
                }

                // Check if email contains '@'
                if (txt.getText().contains("@")) {
                    lbError.setVisible(false); // Hide error if valid
                    resetError();
                } else {
                    setErrorBorder();
                    lbError.setText("Email address must contain @.");
                    lbError.setVisible(true); // Show error message
                }
            }
        });
    }

    // Validate phone number Method
    public void setValidatePhoneNumber() {
        typeTextField = phoneNumber; // for check validation 
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String currentText = txt.getText().replaceAll("\\s", ""); // Remove spaces for length checking
                int length = currentText.length();

                // Allow only digits, backspace, and delete
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Ignore non-digit characters
                }

                // Restrict the length to 12 digits (excluding spaces)
                if (length >= 10 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Stop input if length exceeds 12 digits
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // No specific action needed for keyPressed
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txt.getText().isEmpty()) {
                    lbError.setVisible(false); // Hide error if valid
                    return;
                }

                String text = txt.getText().replaceAll("\\s", ""); // Remove existing spaces for reformatting
                int length = text.length();

                // Format the text to add a space after every 3 digits
                StringBuilder formattedText = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    if (i > 0 && i % 3 == 0) {
                        formattedText.append(" ");
                    }
                    formattedText.append(text.charAt(i));
                }

                // Update the text field with formatted text
                txt.setText(formattedText.toString());

                // Check the length for validation
                if (length != 9 && length != 10) {
                    lbError.setText("Phone Number must be exactly 9 or 10 digits.");
                    lbError.setVisible(true); // Show error message
                    setErrorBorder();
                } else {
                    lbError.setVisible(false); // Hide error message if valid
                    resetError();
                }

            }
        });
    }

    // method for amount allow only digits with auto add comma in every three length ex: 1,230.23
    public void setValidateAmount() {
        typeTextField = amount;
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String currentText = txt.getText().replaceAll(",", ""); // Remove commas for length checking

                // Allow only digits, backspace, delete, and one dot (.)
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '.') {
                    e.consume(); // Ignore non-digit characters
                }

                // Ensure only one decimal point is allowed, and it can't be the first character
                if (c == '.' && (currentText.isEmpty() || currentText.contains("."))) {
                    e.consume(); // Disallow if no digits before decimal or if already a decimal point
                }

                // Restrict the length to 12 digits before the decimal
                if (currentText.contains(".")) {
                    String[] parts = currentText.split("\\.");
                    if (parts[0].length() >= 12 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                        e.consume(); // Stop input if length exceeds 12 digits before the decimal
                    }
                } else if (currentText.length() >= 12 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Stop input if length exceeds 12 digits without a decimal
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // No specific action needed for keyPressed
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txt.getText().isEmpty()) {
                    lbError.setVisible(false); // Hide error if valid
                    return;
                }
                lbError.setVisible(false); // Hide error if valid
                // Remove commas for proper formatting
                String text = txt.getText().replaceAll(",", "");
                try {
                    // Check if the input contains a decimal point
                    if (text.contains(".")) {
                        // Split integer part and decimal part
                        String[] parts = text.split("\\.");
                        String integerPart = parts[0];
                        String decimalPart = parts.length > 1 ? parts[1] : "";

                        // Format integer part with commas every 3 digits
                        String formattedIntegerPart = formatWithCommas(integerPart);

                        // Ensure the decimal part has at most two digits
                        if (decimalPart.length() > 2) {
                            decimalPart = decimalPart.substring(0, 2);
                        }

                        // Combine integer and decimal parts
                        txt.setText(formattedIntegerPart + "." + decimalPart);
                    } else {
                        // No decimal point, just format the integer part
                        String formattedIntegerPart = formatWithCommas(text);
                        txt.setText(formattedIntegerPart);
                    }
                } catch (NumberFormatException ex) {
                    lbError.setVisible(true); // Show error if the input is not a valid number
                }
            }

            // Helper method to format the integer part with commas every 3 digits
            private String formatWithCommas(String number) {
                try {
                    BigDecimal value = new BigDecimal(number);
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    return formatter.format(value);
                } catch (NumberFormatException e) {
                    return number; // Return original if formatting fails
                }
            }
        });
    }

    // method initEvent with paramate for do specific action 
    public void initEvent(ButtonEvent event) {
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                valueTextField = txt.getText();
                resetError();
                //Check if khmer font
                if (JavaConstant.containsKhmer(valueTextField)) {
                    txt.setFont(WindowFonts.khmerOsContent12);
                } else {
                    txt.setFont(WindowFonts.timeNewRoman14);
                }
            }
        });
    }

    // method initEvent for get get value from text when user typing text
    public void initEvent() {
        setFocus();
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                valueTextField = txt.getText();
              
                resetError();
                //Check if khmer font
                if (JavaConstant.containsKhmer(valueTextField)) {
                    txt.setFont(WindowFonts.khmerOsContent12);
                } else {
                    txt.setFont(WindowFonts.timeNewRoman14);
                }
            }
        });
    }

    public void setFieldError(boolean value) {
        lbError.setVisible(value);
    }

    public void setFieldError(String text) {
        lbError.setText(text);
        lbError.setVisible(true);
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void setDisable() {
        txt.setEnabled(false);
        txt.setDisabledTextColor(WindowColor.gray); // Change the text color when disabled
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        txt = new javax.swing.JTextField();
        lbError = new javax.swing.JLabel();

        label.setText("Label Name");
        label.setPreferredSize(new java.awt.Dimension(63, 20));

        txt.setPreferredSize(new java.awt.Dimension(300, 35));

        lbError.setText("The field is required.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JLabel lbError;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
