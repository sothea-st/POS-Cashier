/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Products;

 
import ButtonPackage.ButtonBrowse;
import Event.ButtonEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType.Enum.table;

/**
 *
 * @author MOBILE-APP.02
 */
public class ButtonEditor extends DefaultCellEditor {
     private ButtonEvent event;
     public ButtonEditor(ButtonEvent event) {
          super(new JComboBox());
          this.event = event;
     }

     @Override
     public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
          ButtonBrowse b = new ButtonBrowse();
          b.setForeground(Color.red);
          b.initEvent(event,row);
          System.err.println("row : " + row);
          return b;
     }

}
