 
package main.main_validation;

import FormComponent.JavaTextFieldPassword;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JavaPasswordConflicValidation {
   private String key;
   private String msg;
   private JavaTextFieldPassword field;
}
