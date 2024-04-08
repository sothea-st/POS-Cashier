package com.example.pos.connection1.controller.openShiftController;

// @RestController
// @RequestMapping("/api/closeShiftTime")
public class CloseShiftController {
    // @Autowired
    // private CloseShiftService service;

    // @Autowired
    // private HttpSession session;

    // @Autowired
    // private CloseShiftRepository repoClose;

    // @Autowired
    // private OpenShiftRepository repoOpen;


    // @PostMapping
    // public ResponseEntity<?> closeShift(@RequestBody CloseShift c) {
    //     HashMap<String,Object> map = new HashMap<>();
    
    //     OpenShift countOpenShift = repoOpen.countOpenShift(c.getUserCode(), JavaConstant.currentDate);
    //     // protect when user try to processing sale but user does not open shift first
    //     if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
    //         map.put(JavaConstant.message, JavaConstant.closeOpenShfitFirst);
    //         return JavaResponse.error(map);
    //     }
        
    //     CloseShift data = service.closeShift(c);
    //     return JavaResponse.success(data);
    // }

    // @GetMapping("/cashierReport")
    // public ResponseEntity<?> getCashierReport(){
    //     return JavaResponse.success(service.cashierReport());
    // }


}
