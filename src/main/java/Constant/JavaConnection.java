package Constant;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class JavaConnection {

     private static void closeConnection(OkHttpClient client) {
          // Close the client when you're done
          client.dispatcher().cancelAll();
          client.dispatcher().executorService().shutdown();
          client.connectionPool().evictAll();
     }

     public static Response get(String route) {
          Response response = null;
          OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
               .url(new JavaBaseUrl().getBaseUrl() + route)
               .header("Authorization", "Bearer " + JavaConstant.token)
               .build();
          try {
               response = client.newCall(request).execute();
          } catch (Exception e) {
               System.err.println("getting error during call request " + e);
          }
          closeConnection(client);
          return response;

     }

     public static Response getWithoutToken(String route) {
          Response response = null;
          OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
               .url(new JavaBaseUrl().getBaseUrl() + route)
               .build();
          try {
               response = client.newCall(request).execute();
          } catch (Exception e) {
               System.err.println("getting error during call request " + e);
          }
          closeConnection(client);
          return response;
     }

     public static Response login(String route, JSONObject json) {
          Response response = null;
          OkHttpClient client = new OkHttpClient();
          RequestBody body = RequestBody.create(
               JavaConstant.JSON,
               json.toString());
          Request request = new Request.Builder()
               .url(new JavaBaseUrl().getBaseUrl() + JavaRoute.login)
               .post(body).build();
          try {
               response = client.newCall(request).execute();

          } catch (Exception e) {
               System.err.println("getting error during call request " + e);
          }
          closeConnection(client);
          return response;
     }

     public static Response post(String route, JSONObject json) {
          Response response = null;
          String url = new JavaBaseUrl().getBaseUrl() + route;
          OkHttpClient client = new OkHttpClient();
          RequestBody body = RequestBody.create(
               JavaConstant.JSON,
               json.toString());
          Request request = new Request.Builder()
               .url(url)
               .header("Authorization", "Bearer " + JavaConstant.token)
               .post(body).build();
          try {
               response = client.newCall(request).execute();

          } catch (Exception e) {
               System.err.println("getting error during call request " + e);
          }
          closeConnection(client);
          return response;
     }

     public static Response delete(String route, JSONObject json) {
          Response response = null;
          String url = new JavaBaseUrl().getBaseUrl() + route;
          OkHttpClient client = new OkHttpClient();
          RequestBody body = RequestBody.create(
               JavaConstant.JSON,
               json.toString());

          Request deleteRequest = new Request.Builder()
               .url(url)
               .delete(body)
               .addHeader("Authorization", "Bearer " + JavaConstant.token)
               .build();
          try {
               response = client.newCall(deleteRequest).execute();
          } catch (Exception e) {
               System.err.println("getting error during call request " + e);
          }
          closeConnection(client);
          return response;
     }

     public static void getImage(JLabel lableName, String imageName) {
          Response response = JavaConnection.getWithoutToken(JavaRoute.bgImage + imageName);
          if (response.isSuccessful()) {
               try {
                    byte[] bg = response.body().bytes();
                    lableName.setIcon(new ImageIcon(bg));
               } catch (Exception e) {
                    System.err.println("error = " + e);
               }
          }
     }

     public static byte[] getBytes() {
          Response response = JavaConnection.getWithoutToken(JavaRoute.bgImage + "King Mart Small Logo.png");
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
