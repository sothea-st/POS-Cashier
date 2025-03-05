package pagination;


import Reporting.GroupButtonExport;
import javax.swing.JPanel;

public class MainPagination  {

     protected String pageNumber = "1";
     protected int pageSize = 10;
     protected boolean isCheckSearch = true;
     protected String searchValue;
     protected int dataCount = 0;
     protected String pageType;

     protected PaginationPanel paginationPanel;
     protected JPanel panelData;
     protected GroupButtonExport groupButtonExport;
  
}
