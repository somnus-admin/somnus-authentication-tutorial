package com.somnus.firebasedemoproject.users.repositories;

import com.somnus.firebasedemoproject.users.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByAuthority(String authority);
}
