/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Constant.JavaConnection;
import okhttp3.Response;

public class JavaBlogImage {

     public static byte[] getImage(String route){
       Response response = JavaConnection.getWithoutToken(route);
          byte[] bg = null;
          if (response.isSuccessful()) {
               try {
                    bg = response.body().bytes();
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
          return bg;
     }
}
