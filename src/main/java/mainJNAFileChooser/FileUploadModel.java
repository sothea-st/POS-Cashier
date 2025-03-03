package main.mainJNAFileChooser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileUploadModel {

     private int status;
     private String msg;
     private FileUploadDetail data;

     @Setter
     @Getter
     public static class FileUploadDetail {
          private String originalName;
          private String fileName;
          private String uri;
          private String contentType;
          private long size;
     }
}
