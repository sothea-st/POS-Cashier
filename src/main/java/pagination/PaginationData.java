package pagination;

import java.util.List;

public interface PaginationData<T> {

     int getCount();  

     List<T> getData(); 
     
}
