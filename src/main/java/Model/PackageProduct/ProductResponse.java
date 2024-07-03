/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.PackageProduct;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponse {
     
     private String barcode;
     private Integer vendorId;
     private Integer brandId;
     private Integer catId;
     private String productName;
     private String productNameKh;
     private BigDecimal cost;
     private BigDecimal price;
     private BigDecimal margin;
     private Integer attributeId;
     private String choiceValue;
     private Integer uomId;
     private String status;
     private Integer countryId;
     private Integer taxId;
     private String link="Browse";
     private String photo="";
     private String number;
     
}
