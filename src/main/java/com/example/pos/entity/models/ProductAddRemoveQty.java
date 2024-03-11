package com.example.pos.entity.models;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRemoveQty {
     @OneToMany(cascade = CascadeType.ALL)
     private List<ProIdModel>  listProId;
}
