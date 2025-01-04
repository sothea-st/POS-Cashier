 
package feature.Stock.Products;

 
import ButtonPackage.ButtonBrowse;
import Components.Event.ButtonEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
 
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
          return b;
     }

}
