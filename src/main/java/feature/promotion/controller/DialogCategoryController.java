package feature.promotion.controller;

import Components.Event.ButtonEvent;
import Constant.JavaConnection;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.promotion.model.CategoryId;
import feature.promotion.model.CategoryResponse;
import feature.promotion.model.ProductPromotionResponse;
import feature.promotion.view.component.dialog_category.BoxCategoryWithText;
import feature.promotion.view.component.dialog_category.DialogCategory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import okhttp3.Response;
import org.json.JSONObject;

public class DialogCategoryController {

     private DialogCategory controller;

     private List<CategoryId> listCategoryId = new ArrayList<>();

     public DialogCategoryController(DialogCategory controller) {

          this.controller = controller;

          // call read
          read();

     }

     public void read() {

          Response response = JavaConnection.get("/category/code/category");

          try {
               String responseData = response.body().string();

               ObjectMapper objMapper = new ObjectMapper();

               CategoryResponse model = objMapper.readValue(responseData, CategoryResponse.class);

               appendData(model);

          } catch (Exception e) {
               System.err.println("error get category : " + e);
          }
     }

     private void appendData(CategoryResponse response) {

          JPanel panelData = controller.getPanelData();

          panelData.removeAll();

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 1}; // 1 align item to top
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 1}; // 1 align item to left 

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (CategoryResponse.CategoryResponseDetail detail : response.getData()) {

               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
               gbc.anchor = gbc.NORTH;

               gbc.insets = new Insets(10, 0, 10, 0);
               x++;
               if (x == 3) {
                    x = 0;
                    y++;
               }

               Integer categoryId = detail.getId();

               BoxCategoryWithText rowData = new BoxCategoryWithText(detail);

               rowData.setCheckBox(controller.getIsSelectAll());

               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onMouseClick() {
                         if (rowData.getIsCheck()) { // uncheck box
                              rowData.setCheckBox(false);
                              listCategoryId.removeIf(item -> item.getCategoryId().equals(categoryId));
                         } else { // check box 
                              rowData.setCheckBox(true);
                              listCategoryId.add(CategoryId.builder()
                                   .categoryId(categoryId)
                                   .build());
                         }
                    }
               };

               rowData.initEvent(event);

               panelData.add(rowData, gbc);
          }

          panelData.revalidate();
          panelData.repaint();
     }

     public void getProductByCategory() {

          JSONObject json = new JSONObject();

          json.put("listCategoryId", listCategoryId);

          Response response = JavaConnection.post(JavaRoute.promotion + "/readByCategoryId", json);

          System.err.println("log view response : " + response);
          System.err.println("log view json : " + json);

          try {

               String responseData = response.body().string();

               ObjectMapper objMapper = new ObjectMapper();

               ProductPromotionResponse model = objMapper.readValue(responseData, ProductPromotionResponse.class);

               for (ProductPromotionResponse.ProductPromotionResponseDetail detail : model.getData()) {
                    System.err.println("productName : " + detail.getEnglishDescription());
               }

          } catch (Exception e) {
               System.err.println("error get product by category : " + e);
          }

     }

}
