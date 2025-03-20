package feature.order_online.controller;

import Components.Event.ButtonEvent;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import FormComponent.combobox.JavaCombobox;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.order_online.component.OrderOnlineRowData;
import feature.order_online.export.ExportOrderOnlineExcel;
import feature.order_online.export.ExportOrderOnlinePDF;
import feature.order_online.model.OrderOnlineCalculateResponse;
import feature.order_online.model.OrderOnlineModelResponse;
import feature.order_online.model.OrderOnlineModelResponse.OrderOnlineData;
import feature.order_online.view.OrderOnlineDetail;
import feature.order_online.view.OrderOnlineView;
import java.awt.GridBagConstraints;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import okhttp3.Response;
import pagination.MainPaginationWithData;

public class OrderOnlineViewController extends MainPaginationWithData<OrderOnlineModelResponse> {
     
     private OrderOnlineView view;
     private String dateFrom;
     private String dateTo;
     private JavaCombobox objOrderStatus;
     private JavaCombobox objPaymentStatus;
     
     
     private OrderOnlineViewController controller;
     private String titleEn = "Order Online";
     private String titleKh = "បញ្ជាទិញតាមអ៊ីនធឺណិត";
     private String[] columnHeader = {
          "#",
          "Order Date",
          "Order Number",
          "Order Status",
          "Customer ID",
          "Customer Name",
          "Phone Number",
          "Delivery Information",
          "Total Amount",
          "Payment Method",
          "Payment Status",
          "Delivery Address",
          "Customer Note"
     };

     // constructor
     public OrderOnlineViewController(OrderOnlineView view) {
          super(
               OrderOnlineModelResponse.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               view.getGroupButtonExport()
          );
          
          this.view = view;
          this.objOrderStatus = view.getObjOrderStatus();
          this.objPaymentStatus = view.getObjPaymentStatus();

          controller = this;
          
          // call initializeDate
          initializeDate();

          // call setCmdValue
          setCmdValue();

          // call calculateOrder
          calculateOrder();
     }
     
     public void calculateOrder() {
          
          Response res = JavaConnection.get(JavaRoute.orderOnline + "/calculate");
          
          try {
               
               String responseData = res.body().string();
               
               ObjectMapper objMapper = new ObjectMapper();
               
               OrderOnlineCalculateResponse data = objMapper.readValue(responseData, OrderOnlineCalculateResponse.class);
               
               String newOrderCount = String.valueOf(data.getData().getNewOrders().getCount());
               BigDecimal newOrderTotalAmount = data.getData().getNewOrders().getTotal();
               view.getObjNewOrders().setTotalCount(newOrderCount);
               view.getObjNewOrders().setTotalAmount(JavaConstant.setAmount(newOrderTotalAmount));
               
               String cancelledCount = String.valueOf(data.getData().getCancelled().getCount());
               BigDecimal cancelledTotalAmount = data.getData().getCancelled().getTotal();
               view.getObjCancelled().setTotalCount(cancelledCount);
               view.getObjCancelled().setTotalAmount(JavaConstant.setAmount(cancelledTotalAmount));
               
               String completedCount = String.valueOf(data.getData().getCompleted().getCount());
               BigDecimal completedTotalAmount = data.getData().getCompleted().getTotal();
               view.getObjCompleted().setTotalCount(completedCount);
               view.getObjCompleted().setTotalAmount(JavaConstant.setAmount(completedTotalAmount));
               
          } catch (Exception e) {
               
               System.err.println("error get calculate : " + e);
          }
          
     }
     
     @Override
     protected String routeName(boolean isCheck) {
          String route;
          
          if (isCheck) { // get data
               route = JavaRoute.orderOnline + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
          } else { // search
               route = JavaRoute.orderOnline + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
          }
          
          StringBuilder str = new StringBuilder();
          if (!objOrderStatus.getSelectedItem().equals("0")) {
               str.append("&orderStatus=").append(objOrderStatus.getSelectedItem());
          }
          if (!objPaymentStatus.getSelectedItem().equals("0")) {
               str.append("&paymentStatus=").append(objPaymentStatus.getSelectedItem());
          }
          if (!isCheck) {
               str.append("&search=");
          }
          route += str.toString();
          return route;
     }
     
     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {
          OrderOnlineData data = (OrderOnlineData) listData.get(i);
          OrderOnlineRowData rowData = new OrderOnlineRowData(data);
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onClick() {
                    OrderOnlineDetail detail = new OrderOnlineDetail(new JFrame(), true);
                    detail.setData(data,view);
                    detail.setController(controller);
                    detail.setVisible(true);
               }
               
          };
          rowData.initEvent(event);
          panelData.add(rowData, gbc);
     }
     
     public void find() {
          initializeDate();
          init();
     }
     
     private void initializeDate() {
          this.dateFrom = view.getObjDateFrom().getSelectedDate();
          this.dateTo = view.getObjDateTo().getSelectedDate();
     }
     
     private void setCmdValue() {
          
          LinkedHashMap<String, String> orderStatus = new LinkedHashMap<>();
          orderStatus.put("All", "All");
          orderStatus.put("New", "New");
          orderStatus.put("Accepted", "Accepted");
          orderStatus.put("Picked & Packed", "Picked & Packed");
          orderStatus.put("Delivered", "Delivered");
          orderStatus.put("Completed", "Completed");
          orderStatus.put("Cancelled", "Cancelled");
          view.getObjOrderStatus().setMap(orderStatus);
          
          LinkedHashMap<String, String> paymentStatus = new LinkedHashMap<>();
          paymentStatus.put("All", "All");
          paymentStatus.put("Paid", "Paid");
          paymentStatus.put("Unpaid", "Unpaid");
          view.getObjPaymentStatus().setMap(paymentStatus);
          
     }
     
     @Override
     protected void exportExcel() {
          new ExportOrderOnlineExcel(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }
     
     @Override
     protected void exportPDF() {
          new ExportOrderOnlinePDF(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }
     
}
