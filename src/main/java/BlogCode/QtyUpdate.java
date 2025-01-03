package BlogCode;

import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaMessage;
import Constant.JavaRoute;
import feature.Stock.Products.ProductBox;
import BlogCode.UpdateQty.UpdateQtyModel;
import java.util.ArrayList;
import javax.swing.JFrame;
import okhttp3.Response;
import org.json.JSONObject;

public class QtyUpdate {
    public static void updateQty(int proId, String sign,ProductBox product, int qty) {
          JSONObject json = new JSONObject();
          ArrayList<UpdateQtyModel> model = new ArrayList<>();
          UpdateQtyModel _updateModel = new UpdateQtyModel(proId, qty, sign);
          model.add(_updateModel);
          json.put("listProId", model);
          
          Response _responseData = JavaConnection.post(JavaRoute.updateQty, json);
          int _qtyUpdate = 0;
          
          try {
               String _data = _responseData.body().string();
               JSONObject _json = new JSONObject(_data);
               _qtyUpdate = _json.getInt("qtyUpdate");

               if (_qtyUpdate < 0) {
                    JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                    j.setMessage(JavaMessage.productOutStock);
                    j.setVisible(true);
                    return;
               } else if (_qtyUpdate == 0) {
                    product.setProductStatus("Out Stock");
               }else{
                    product.setProductStatus("In Stock");
               }
               product.setQty("" + _qtyUpdate);
          } catch (Exception e) {
               System.err.println(e);
          }
         
     }
}
