 
package feature.invoice_listing.controller;

import feature.invoice_listing.InvoiceListingView;

 
public class InvoiceController {
     
     private InvoiceListingView invoiceListingView;
     private String dateFrom;
     private String dateTo;
     
     public InvoiceController(InvoiceListingView invoiceListingView,String dateFrom ,String dateTo) {
          
          this.invoiceListingView = invoiceListingView;
          this.dateFrom = dateFrom;
          this.dateTo = dateTo;
          
          read();
          
     }
     
     public void read(){
          invoiceListingView.getInvoicePanel().setInvoiceListingView(invoiceListingView);
          invoiceListingView.getInvoicePanel().setDateFrom(dateFrom);
          invoiceListingView.getInvoicePanel().setDateTo(dateTo);
          invoiceListingView.getInvoicePanel().read(true);
     }
     
}
