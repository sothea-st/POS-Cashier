package FormComponent.combobox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JavaItem {

     private String key;    // key (e.g., ID)
     private String value;  // value (e.g., name)

     // Override toString() to display the value in the JComboBox
     @Override
     public String toString() {
          return key;
     }
}
