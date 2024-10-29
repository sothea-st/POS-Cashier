

package Constant;

public class JavaBaseUrl {
     private String baseUrl = "http://localhost:8080/api";
//     public static String baseUrlImage = "http://103.101.80.108:8082//";
          public static String baseUrlImage = "http://10.2.3.2:2010//";

     public static String baseUrlBgImage = "http://localhost:8080/api/public/addImageForBackground/";
     public static String baseUrlDefaultImage = "http://localhost:8080/api/public/addImageForBackground/default.jpg";
     public static String baseUrlDefaultImageStaff = "http://localhost:8080/api/public/addImageForBackground/93ed3b9c-be82-4c69-8b7d-331ab82ef54a";
     public String getBaseUrl(){
          return baseUrl;
     }
     
}
