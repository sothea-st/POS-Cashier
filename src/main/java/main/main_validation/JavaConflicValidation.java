 
package main.main_validation;

import FormComponent.JavaTextField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JavaConflicValidation {
   private String key;
   private String msg;
   private JavaTextField field;
}
