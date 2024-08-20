package com.example.pos.connection1.feature.vendor.dto;
import com.example.pos.connection1.constant.JavaMessage;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record VendorUpdateRequest(
     @NotBlank(message = JavaMessage.required)
     String vendorName,

     @NotBlank(message = JavaMessage.required)
     String address,

     @NotBlank(message = JavaMessage.required)
     @Size(min = 9,max = 12,message = "Phone number must be between 9 and 12 digits.")
     @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
     String contact,

     @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail\\.com$", message = "Email must be contain @gmail.com")
     String email,

     String website
) {
     
}
