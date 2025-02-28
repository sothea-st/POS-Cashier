package feature.adjustment.adjustment_controller;

import Components.Event.ButtonEvent;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.adjustment.AdjustmentCreateForm;
import feature.adjustment.adjustmetn_detail.model.AdjustmentDetailResponse;
import feature.adjustment.component.ItemFormCreate;
import feature.adjustment.model.ProductAdjustment;
import feature.adjustment.model.ProductBarcode;
import feature.adjustment.model.ProductBarcode.ProductBarcodeDetail;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import okhttp3.Response;

@Setter
@Getter
public class CreateAdjustmentController {

     private AdjustmentCreateForm form;
     private List<ProductAdjustment> listTmp = new ArrayList<>();

     public CreateAdjustmentController(AdjustmentCreateForm form) {
          this.form = form;
     }

     public void eventBarcode() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    if (form.getObjBarcode().getValueTextField().length() == 13) {

                         Response response = JavaConnection.get(JavaRoute.productV1 + "/search/" + form.getObjBarcode().getValueTextField());

                         try {
                              String responseData = response.body().string();

                              ObjectMapper objMapper = new ObjectMapper();

                              ProductBarcode model = objMapper.readValue(responseData, ProductBarcode.class);

                              ProductBarcode.ProductBarcodeDetail[] listProduct = model.getData();

                              ProductBarcodeDetail product = listProduct[0];

                              appendData(product);

                         } catch (Exception e) {
                              System.err.println("error : " + e);
                         }

                    }
               }
          };

          form.getObjBarcode().initEvent(event);

     }

     public void appendData(ProductBarcodeDetail product) {

          if (product != null) { // product == null means remove
               ProductAdjustment productAdjustment = ProductAdjustment.builder()
                    .id(product.getId())
                    .itemCode(product.getItemCode())
                    .barcode(product.getBarcode())
                    .proNameEn(product.getProNameEn())
                    .proNameKh(product.getProNameKh())
                    .uom(product.getUomNameEn())
                    .onHandQty(product.getQty())
                    .adjustQty(1)
                    .cost(product.getCost())
                    .build();

               boolean found = false;

               for (ProductAdjustment data : listTmp) {
                    if (product.getBarcode().equals(data.getBarcode())) {
                         data.setAdjustQty(data.getAdjustQty() + 1);
                         found = true;
                         break;
                    }
               }

               if (!found) {
                    listTmp.add(productAdjustment);
               }
          }

          addData();
     }

     private void addData() {
          form.getPanelData().removeAll();

          int i = 1;
          for (ProductAdjustment data : listTmp) {
               ItemFormCreate item = new ItemFormCreate(data, i);
               item.setForm(form);
               item.setController(this);
               form.getPanelData().add(item, 0);
               i++;
          }
          form.getPanelData().revalidate();
          form.getPanelData().repaint();
          form.getObjBarcode().setText(null);

          // reload total qty and cost
          form.getBoxTotal().setAdjustmentCreateForm(form);
     }

     public void update(Integer adjustmentId) {

          Response response = JavaConnection.get(JavaRoute.adjustment + "/" + adjustmentId);

          try {

               String responseData = response.body().string();

               ObjectMapper objMapper = new ObjectMapper();

               AdjustmentDetailResponse model = objMapper.readValue(responseData, AdjustmentDetailResponse.class);

               AdjustmentDetailResponse.AdjustmentData data = model.getData();
               
               System.err.println("data.getTransactionDate() : " + data.getTransactionDate());
               
               form.getObjTransactionDate().setSelectedDate(JavaConstant.formateDateDDMMYYYY(data.getTransactionDate()));
               form.getObjReason().setSelectedItem(data.getReason().getId());
               form.getObjReference().setText(data.getReference());
               form.getObjComment().setText(data.getComment());

               for (AdjustmentDetailResponse.Detail detail : data.getDetails()) {
                    ProductAdjustment productAdjustment = ProductAdjustment.builder()
                         .id(detail.getProductId())
                         .itemCode(detail.getItemCode())
                         .barcode(detail.getBarcode())
                         .proNameEn(detail.getProductNameEn())
                         .proNameKh(detail.getProductNameKh())
                         .uom(detail.getOum())
                         .onHandQty(detail.getOnHandQty())
                         .adjustQty(detail.getAdjustQty())
                         .cost(detail.getCost())
                         .build();

                    listTmp.add(productAdjustment);
               }
               
               addData();

          } catch (Exception e) {
               System.err.println("error read adjustment detail : " + e);
          }
     }
 
}
