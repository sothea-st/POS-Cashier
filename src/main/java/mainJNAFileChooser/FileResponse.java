 
package main.mainJNAFileChooser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FileResponse {
     private String fileName;
     private String originalName;
}
