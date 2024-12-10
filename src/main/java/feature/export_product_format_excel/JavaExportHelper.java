package feature.export_product_format_excel;

import Constant.JavaConnection;
import Constant.JavaRoute;
import Model.Attribute.DataAttributeModel;
import Model.Attribute.ListAttributeModel;
import Model.Brand.BrandModel;
import Model.Brand.BrandSuccessModel;
import Model.Category.CategoryGetdataModel;
import Model.Category.CategorySuccessModel;
import Model.Country.DataCountryModel;
import Model.Country.ListCountryModel;
import Model.Status.GetStatusModel;
import Model.Status.ListStatusModel;
import Model.Tax.DataTaxModel;
import Model.Tax.ListTaxModel;
import Model.Uom.DataUomModel;
import Model.Uom.ListUomModel;
import Model.Vendor.DataVendorModel;
import Model.Vendor.ListVendorModel;
import Model.Warehouse.WarehouseModel;
import Model.Warehouse.WarehouseModel.WarehouseDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;

public class JavaExportHelper {

     public static List<DataVendorModel> getVendorNames() {
          List<DataVendorModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.vendor);

          try {

               String responseData = respsone.body().string();

               ObjectMapper object = new ObjectMapper();

               ListVendorModel model = object.readValue(responseData, ListVendorModel.class);

               for (DataVendorModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<BrandModel> getBrands() {
          List<BrandModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.brand);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               BrandSuccessModel model = object.readValue(responseData, BrandSuccessModel.class);

               for (BrandModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<CategoryGetdataModel> getSubCategories() {
          List<CategoryGetdataModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.subcategory);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               CategorySuccessModel model = object.readValue(responseData, CategorySuccessModel.class);

               for (CategoryGetdataModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<DataAttributeModel> getAttributes() {
          List<DataAttributeModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.attribute);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               ListAttributeModel model = object.readValue(responseData, ListAttributeModel.class);

               for (DataAttributeModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<DataUomModel> getUoms() {
          List<DataUomModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.uom);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               ListUomModel model = object.readValue(responseData, ListUomModel.class);

               for (DataUomModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<GetStatusModel> getStatus() {
          List<GetStatusModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.status);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               ListStatusModel model = object.readValue(responseData, ListStatusModel.class);

               for (GetStatusModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<DataCountryModel> getCountries() {
          List<DataCountryModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.country);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               ListCountryModel model = object.readValue(responseData, ListCountryModel.class);

               for (DataCountryModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<DataTaxModel> getTaxs() {
          List<DataTaxModel> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.tax);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               ListTaxModel model = object.readValue(responseData, ListTaxModel.class);

               for (DataTaxModel data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }

     public static List<WarehouseDetail> getWarehouses() {
          List<WarehouseDetail> list = new ArrayList<>();
          Response respsone = JavaConnection.get(JavaRoute.warehouse);

          try {
               String responseData = respsone.body().string();
               ObjectMapper object = new ObjectMapper();

               WarehouseModel model = object.readValue(responseData, WarehouseModel.class);

               for (WarehouseDetail data : model.getData()) {
                    list.add(data);
               }

          } catch (Exception e) {
               System.err.println("error : " + e);
          }

          return list;
     }
}
