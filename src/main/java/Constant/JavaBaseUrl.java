package Constant;

import java.io.InputStream;
import java.util.Properties;

//     private String baseUrl = "http://localhost:8080/api";
////     public static String baseUrlImage = "http://103.101.80.108:8082//";
//          public static String baseUrlImage = "http://10.2.3.2:2010//";
//
//     public static String baseUrlBgImage = "http://localhost:8080/api/public/addImageForBackground/";
//     public static String baseUrlDefaultImage = "http://localhost:8080/api/public/addImageForBackground/default.jpg";
//     public static String baseUrlDefaultImageStaff = "http://localhost:8080/api/public/addImageForBackground/93ed3b9c-be82-4c69-8b7d-331ab82ef54a";
//     public String getBaseUrl(){
//          return baseUrl;
//     }
//     
//}
public class JavaBaseUrl {

     private String baseUrl;
     public static String routePDF;
     public static String baseUrlImage = "http://10.2.3.2:2010//";
     public static String baseUrlBgImage = "http://localhost:8080/api/public/addImageForBackground/";
     public static String baseUrlDefaultImage = "http://localhost:8080/api/public/addImageForBackground/default.jpg";
     public static String urlReadImage ="http://localhost:8080/image/";

     public JavaBaseUrl() {

          loadProperties("config-dev.properties"); // Change to config-dev.properties for developer
//        loadProperties("config-sit.properties"); // Change to config-sit.properties for sit
//        loadProperties("config-prop.properties"); // Change to config-prop.properties for production
     }

     private void loadProperties(String configFileName) {
          // path from src/main/resources/configEnv
          try (InputStream input = getClass().getClassLoader().getResourceAsStream("configEnv/" + configFileName)) {
               if (input == null) {
                    System.out.println("Sorry, unable to find " + configFileName);
                    return;
               }

               Properties prop = new Properties();
               prop.load(input);

               // Fetch HOST, PORT, and paths
               String host = prop.getProperty("HOST");
               String port = prop.getProperty("PORT");
               String basePath = prop.getProperty("basePath");
               String routePath = prop.getProperty("routePath");

               // Dynamically construct URLs
               baseUrl = "http://" + host + ":" + port + basePath; //it will outpunt   http://localhost:8080/api
               routePDF = "http://" + host + ":" + port + routePath; //it will outpunt   http://localhost:8080/api/file-uploads/pdf/

               // Debug: Check if URLs were constructed correctly
//            System.out.println("Base URL: " + baseUrl);
//            System.out.println("Route PDF URL: " + routePDF);
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public String getBaseUrl() {
          return baseUrl;
     }

     public static String getRoutePDF() {
          return routePDF;
     }

}
