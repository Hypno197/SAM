package com.generation.italy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.model.Role;
import com.generation.italy.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) {
        Role role = roleService.getRoleById(roleId);
        return ResponseEntity.ok().body(role);
    }

    @PostMapping
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody Role roleDetails) {
        Role role = roleService.getRoleById(roleId);
        Role updatedRole = roleService.updateRole(roleId, roleDetails);
        return ResponseEntity.ok().body(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(value = "id") Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok().<Void>build();
    }
}
