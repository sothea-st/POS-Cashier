package FormComponent.combobox;

import Color.WindowColor;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import lombok.Getter;
 

@Getter
public class JavaCombobox extends javax.swing.JPanel {

    private LinkedHashMap<String, String> linkMap; // Map where key is id and value is name
    private String labelName;
    private String placeHolder = "---Select---";

    public JavaCombobox() {
        initComponents();

        // label error
        lbError.setVisible(false);
        lbError.setForeground(WindowColor.red);
        lbError.setFont(WindowFonts.timeNewRomanBold14);
        setBackground(WindowColor.bgDefault);
        cmd.setFont(WindowFonts.timeNewRoman14);
        label.setFont(WindowFonts.timeNewRomanBold14);

        // Enable search in JComboBox
        search();
        setPlaceholder(placeHolder);

        putClientProperty(FlatClientProperties.STYLE, "background:$Table.background;");

    }

    private void setPlaceholder(String placeholder) {
        JTextField textField = (JTextField) cmd.getEditor().getEditorComponent();
        textField.putClientProperty("JTextField.placeholderText", placeholder);

        // Set initial placeholder text
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        // Add action listener to clear placeholder when an item is selected
        cmd.addActionListener(e -> {
            if (cmd.getSelectedItem() != null && !cmd.getSelectedItem().toString().equals(placeholder)) {
                textField.setForeground(Color.BLACK);
            }
        });

        // Add focus listener to reset placeholder when losing focus
        cmd.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void search() {
        cmd.setEditable(true); // Make JComboBox editable for search
        JTextField textField = (JTextField) cmd.getEditor().getEditorComponent();

        // Add key listener for filtering items
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = textField.getText().toLowerCase();
                List<JavaItem> filteredItems = new ArrayList<>();

                // Filter the items in linkMap based on the input
                for (Map.Entry<String, String> entry : linkMap.entrySet()) {
                    if (entry.getValue().toLowerCase().contains(input)) {
                        filteredItems.add(new JavaItem(entry.getValue(), entry.getKey()));
                    }
                }

                // Update JComboBox with filtered items
                cmd.removeAllItems();
                for (JavaItem item : filteredItems) {
                    cmd.addItem(item);
                }

                textField.setText(input); // Keep user input
                cmd.showPopup(); // Keep dropdown open
            }
        });

    }

//    public String getSelectedItem() {
//        JavaItem item = (JavaItem) cmd.getSelectedItem();
//        System.err.println("irem :: " + item);
//        if (item == null) {
//            return "0";
//        }
//        return item.getValue();
//    }
    public String getSelectedItem() {
        Object selected = cmd.getSelectedItem();
        if (selected instanceof JavaItem) {
            JavaItem item = (JavaItem) selected;
            //System.err.println("Item :: " + item);
            return item.getValue();
        } else {
            //System.err.println("Selected item is not a JavaItem: " + selected);
            return "0";
        }
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

    private void customComboBox() {
        // Set background and foreground colors
        cmd.setBackground(WindowColor.bgDefault);  // Set background color
        cmd.setForeground(WindowColor.black);       // Set text color
    }

    private void roundedBorder() {
        label.setFont(WindowFonts.timeNewRomanBold12);
        // cmd.setFont(WindowFonts.timeNewRoman14);
        //cmd.setBorder(new RoundedBorder(0, WindowColor.gray));
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

    public void initEvent(ButtonEvent event) {

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedObject = cmd.getSelectedItem();

                // Check if selectedObject is an instance of JavaItem
                if (selectedObject instanceof JavaItem) {
                    resetError();
                    JavaItem selectedItem = (JavaItem) selectedObject;

                    if (selectedItem.getValue().equals("0")) {
                        SwingUtilities.invokeLater(() -> cmd.setSelectedIndex(-1)); // Reset selection
                    } else {
                        event.onSelected(selectedItem.getValue());
                        //System.err.println("selectedItem " + selectedItem.getValue());
                    }

                } else {
                    System.err.println("Unexpected item type: " + selectedObject);
                }

            }
        });
    }

    // set combobox item value
    public void setMap(LinkedHashMap<String, String> linkMap) {
        this.linkMap = linkMap;
        cmd.removeAllItems(); // Clear existing items

        // Add the placeholder item as a JavaItem
        JavaItem placeholderItem = new JavaItem(placeHolder, "0"); // -1 as ID for the placeholder
        cmd.addItem(placeholderItem);

        // Add the actual data from the linkMap
        for (Map.Entry<String, String> entry : linkMap.entrySet()) {
            JavaItem item = new JavaItem(entry.getValue(), entry.getKey()); // value is name, key is id
            cmd.addItem(item); // Add the JavaItem object directly to the combo box
        }

        // Add an item listener to disable the first item
//        cmd.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) {
//                JavaItem selectedItem = (JavaItem) cmd.getSelectedItem();
//                if (selectedItem != null && "0".equals(selectedItem.getValue())) {
//                    // If the placeholder is selected, deselect it
//                    SwingUtilities.invokeLater(() -> cmd.setSelectedIndex(-1)); // Reset selection
////                    JOptionPane.showMessageDialog(null, "Please select a valid option.");
//                }
//            }
//        });
        // Set up the placeholder
        setPlaceholder(placeHolder);
    }

    public void setData(String text, int id) {
        // Add the placeholder item as a JavaItem
        cmd.removeAllItems(); // Clear existing items
        JavaItem placeholderItem = new JavaItem(text, String.valueOf(id));
        cmd.addItem(placeholderItem);
    }

    // set combobox item value
    public void setMapWithNoPlaceHolder(LinkedHashMap<String, String> linkMap) {
        this.linkMap = linkMap;
        cmd.removeAllItems(); // Clear existing items

        // Add the actual data from the linkMap
        for (Map.Entry<String, String> entry : linkMap.entrySet()) {
            JavaItem item = new JavaItem(entry.getValue(), entry.getKey()); // value is name, key is id
            cmd.addItem(item); // Add the JavaItem object directly to the combo box
        }
    }

    // reset value to first item
    public void setToFirstItem() {
        cmd.removeAllItems(); // Clear existing items
        // Add the placeholder item as a JavaItem
        JavaItem placeholderItem = new JavaItem("--- Select Item ---", "-1"); // -1 as ID for the placeholder
        // Set the default selection to the placeholder item
        cmd.setSelectedItem(placeholderItem);
    }

    public void setSelectedItem(Integer id) {
        // Loop through existing items in the JComboBox
        for (int i = 0; i < cmd.getItemCount(); i++) {
            JavaItem item = cmd.getItemAt(i);
            // Compare both name and id to find a match
            if (item.getValue().equals(String.valueOf(id))) {
                cmd.setSelectedItem(item);  // Select the matching item
                return;
            }
        }
    }

    public void setSelectedItem(String value) {
        // Loop through existing items in the JComboBox
        for (int i = 0; i < cmd.getItemCount(); i++) {
            JavaItem item = cmd.getItemAt(i);
            // Compare both name and id to find a match
            if (item.getValue().equals(String.valueOf(value))) {
                cmd.setSelectedItem(item);  // Select the matching item
                return;
            }
        }
    }

    public void setDiable() {
        cmd.setEnabled(false);
        cmd.setForeground(Color.BLACK); // Change the text color when disabled

    }

    public void setEnable() {
        cmd.setEnabled(true);
    }

    // Method to set the red border for JTextField
    public void setErrorBorder() {
        cmd.putClientProperty(FlatClientProperties.STYLE, "borderColor:#FF0000;");
    }

    // Method to reset the border to the default color for JTextField
    public void resetError() {
        cmd.putClientProperty(FlatClientProperties.STYLE, "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd = new javax.swing.JComboBox<>();
        lbError = new javax.swing.JLabel();
        label = new javax.swing.JLabel();

        cmd.setPreferredSize(new java.awt.Dimension(300, 35));

        lbError.setText("The field email is required.");

        label.setText("Label Name");
        label.setPreferredSize(new java.awt.Dimension(63, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<JavaItem> cmd;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lbError;
    // End of variables declaration//GEN-END:variables
}
