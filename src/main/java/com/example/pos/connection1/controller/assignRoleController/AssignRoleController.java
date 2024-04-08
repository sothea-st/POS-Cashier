package com.example.pos.connection1.controller.assignRoleController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignRole")
public class AssignRoleController {
    // @Autowired
    // private RoleService service;

    // @Autowired  private RoleRepository repoRole;

    // @Autowired private UserRepository repoUser;

    // @PostMapping
    // public ResponseEntity<?> assignRole(@RequestParam("assignerId")int assignerId , @RequestBody AssignRole a) {

    //     int roleId = repoUser.findById(assignerId).get().getRole();
    
    //     String roleName = repoRole.findById(roleId).get().getRoleName();
    
    //     System.out.println("a.getAssignerId() - " + roleName);
    //     if( !roleName.equals(JavaConstant.admin) ) {
    //         return JavaResponse.success("This account have no permission assign role!");
    //     }

    //     service.assignRole(a);
    //     return JavaResponse.success("Assign role success");
    // }

}
