package com.example.pos.connection1.controller;

// @RestController
// @RequestMapping("/api/sale")
// @Validated
public class SaleController {
    // @Autowired
    // private SaleService service;
    // @Autowired
    // private HttpSession session;
    // @Autowired
    // private OpenShiftRepository repoOpen;

    // @PostMapping 
    // public ResponseEntity<?> saleProduct(@Valid @RequestBody Sale s) throws Exception {
    //     var userCode = session.getAttribute(JavaConstant.userCode);
     
    //     OpenShift countOpenShift = repoOpen.countOpenShift(s.getUserCode(), JavaConstant.currentDate);
    //     HashMap<String, Object> map = new HashMap<>();
    //     // protect when user try to processing sale but user does not open shift first
    //     if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
    //         map.put(JavaConstant.message, JavaConstant.openShift);
    //         return JavaResponse.error(map);
    //     }
    //     var data = service.saleProduct(s);
    //     return JavaResponse.success(data);
    // }
}
