package com.somnus.firebasedemoproject.users;

import com.somnus.firebasedemoproject.users.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RolesHandler {

    @Autowired
    private RoleRepository roleRepository;

    public RolesHandler(){}

    public List<RoleEntity> getRoles(String role) {
        return Collections.singletonList(getRole(role));
    }

    /**
     * Get or create role
     * @param authority
     * @return
     */
    private RoleEntity getRole(String authority) {
        RoleEntity role = roleRepository.findByAuthority(authority);
        if (role == null) {
            return new RoleEntity(authority);
        } else {
            return role;
        }
    }
}
