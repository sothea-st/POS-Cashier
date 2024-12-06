package com.example.pos.system.layer.service.RoleAndPermissionService;

import com.example.pos.system.domain.User;
import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.roleProjection.RoleProjection;
import com.example.pos.system.domain.sourceData.AssignRole;
import com.example.pos.system.layer.repository.UserRepository;
import com.example.pos.system.layer.repository.roleAndPermissionRepository.RoleRepository;
import com.example.pos.system.constant.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
     @Autowired
     private RoleRepository repo;

     @Autowired
     private UserRepository userRepo;

     public Role add(Role role) {

          if( repo.existsByRoleName(role.getRoleName()) ) {
               throw new ResponseStatusException(HttpStatus.CONFLICT,"Role name already exists.");
          }

          Role r = new Role();
          r.setRoleName(role.getRoleName());
          r.setCreateBy(role.getCreateBy());
          repo.save(r);
          return r;
     }

     public List<RoleProjection> getRole() {
          return repo.getRole();
     }

     public Role getRoleById(int id) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          return data;
     }

     public void deleteRoleById(int id, Role role) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          data.setStatus(role.isStatus());
          data.setDeleted(role.isDeleted());
          repo.save(data);
     }

     public Role updateRoleById(int id, Role role) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          data.setRoleName(role.getRoleName());
          repo.save(data);
          return data;
     }

     public void assignRole(AssignRole a) {
          Optional<User> users = userRepo.findById(a.getAccountId());
          User u = users.get();
          u.setRole(a.getRoleId());
          userRepo.save(u);
     }

}
