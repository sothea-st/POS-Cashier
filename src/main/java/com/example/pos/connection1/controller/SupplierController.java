package com.example.pos.connection1.controller;


// @RestController
// @RequestMapping("/api/supplier")
// @Validated
public class SupplierController {
    // @Autowired
    // private SupplierService service;

    // @PostMapping()
    // public ResponseEntity<?> addSupplier(@Valid @ModelAttribute Supplier supplier) {
    //     HashMap<String,String> err = new HashMap<>();
    //     String key="contact";
    //     String contact = JavaValidation.checkPhone(supplier.getContact());

    //     if( !contact.isEmpty() ) {
    //         err.put(key, contact);
    //         return ResponseEntity.status(500).body(err);
    //     }
      
    //     Supplier s = service.addSupplier(supplier);
    //     return JavaResponse.success(s);
    // }

    // @GetMapping()
    // public ResponseEntity<?> getSupplier() {
    //     ArrayList<Supplier> data = service.getSupplier();
    //     return JavaResponse.success(data);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> getSupplierById(@PathVariable("id")int id){
    //     Supplier data = service.getSupplierById(id);
    //     return JavaResponse.success(data);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> updateSupplier(@PathVariable("id") int id ,@Valid @RequestBody Supplier s){
    //     HashMap<String,String> err = new HashMap<>();
    //     String key="contact";
    //     String contact = JavaValidation.checkPhone(s.getContact());

    //     if( !contact.isEmpty() ) {
    //         err.put(key, contact);
    //         return ResponseEntity.status(500).body(err);
    //     }
    //     Supplier data = service.updateSupplier(id,s);
    //     return JavaResponse.success(data);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteSupplier(@PathVariable("id")int id , @RequestBody Supplier s){
    //     service.deleteSupplier(id,s);
    //     return JavaResponse.success("delete success");
    // }

    
}
