package pagination;

import java.util.List;

public interface PaginationData {

     int getCount();  // to get the count

     List<?> getData();  // to get the data list
}
