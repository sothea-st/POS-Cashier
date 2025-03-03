package main.mainJNAFileChooser.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MultipleFileUploadResponse {

     private int status;
     private String msg;
     private MultipleFileUploadDetail[] data;

     @Setter
     @Getter
     public static class MultipleFileUploadDetail {

          private String fileName;
          private String originalName;
          private String uri;
          private String contentType;
          private Long size;
     }
}
