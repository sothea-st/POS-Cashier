package Model.PackageProduct;

public class ProductModel {

     private int id;
     private int catId;
     private String flag;
     private String weight;
     private double cost;
     private String proImageName;
     private double price;
     private String barcode;
     private String productNameKh;
     private String productNameEn;
     private String productStatus;
     private int discount;
     private double discountTypeDouble;
     private int qty;

     public ProductModel() {
     }

     
     public ProductModel(
          int id,
          int catId,
          String flag,
          String weight,
          double cost,
          String proImageName,
          double price,
          String barcode,
          String productNameKh,
          String productNameEn,
          String productStatus,
          int discount,
          int qty
     ) {
          this.id = id;
          this.catId = catId;
          this.flag = flag;
          this.weight = weight;
          this.cost = cost;
          this.proImageName = proImageName;
          this.price = price;
          this.barcode = barcode;
          this.productNameKh = productNameKh;
          this.productNameEn = productNameEn;
          this.productStatus = productStatus;
          this.discount = discount;
          this.qty = qty;
     }
     
//      public ProductModel(
//          int id,
//          int catId,
//          String flag,
//          String weight,
//          double cost,
//          String proImageName,
//          double price,
//          String barcode,
//          String productNameKh,
//          String productNameEn,
//          String productStatus,
//          double discountTypeDouble,
//          int qty
//     ) {
//          this.id = id;
//          this.catId = catId;
//          this.flag = flag;
//          this.weight = weight;
//          this.cost = cost;
//          this.proImageName = proImageName;
//          this.price = price;
//          this.barcode = barcode;
//          this.productNameKh = productNameKh;
//          this.productNameEn = productNameEn;
//          this.productStatus = productStatus;
//          this.discountTypeDouble = discountTypeDouble;
//          this.qty = qty;
//     }

//     public double getDiscountTypeDouble() {
//          return discountTypeDouble;
//     }
//
//     public void setDiscountTypeDouble(double discountTypeDouble) {
//          this.discountTypeDouble = discountTypeDouble;
//     }
     
     
     public int getQty() {
          return qty;
     }

     
     public void setQty(int qty) {
          this.qty = qty;
     }

     
     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getCatId() {
          return catId;
     }

     public void setCatId(int catId) {
          this.catId = catId;
     }

     public String getFlag() {
          return flag;
     }

     public void setFlag(String flag) {
          this.flag = flag;
     }

     public String getWeight() {
          return weight;
     }

     public void setWeight(String weight) {
          this.weight = weight;
     }

     public double getCost() {
          return cost;
     }

     public void setCost(double cost) {
          this.cost = cost;
     }

     public String getProImageName() {
          return proImageName;
     }

     public void setProImageName(String proImageName) {
          this.proImageName = proImageName;
     }

     public double getPrice() {
          return price;
     }

     public void setPrice(double price) {
          this.price = price;
     }

     public String getBarcode() {
          return barcode;
     }

     public void setBarcode(String barcode) {
          this.barcode = barcode;
     }

     public String getProductNameKh() {
          return productNameKh;
     }

     public void setProductNameKh(String productNameKh) {
          this.productNameKh = productNameKh;
     }

     public String getProductNameEn() {
          return productNameEn;
     }

     public void setProductNameEn(String productNameEn) {
          this.productNameEn = productNameEn;
     }

     public String getProductStatus() {
          return productStatus;
     }

     public void setProductStatus(String productStatus) {
          this.productStatus = productStatus;
     }

     public int getDiscount() {
          return discount;
     }

     public void setDiscount(int discount) {
          this.discount = discount;
     }

}
