package main.mainJNAFileChooser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {

     private byte[] byteData;
     private String fileName;
     private String originalName;
     private String contentType;

}
