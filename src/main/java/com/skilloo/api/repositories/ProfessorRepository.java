package com.skilloo.api.repositories;

import com.skilloo.api.entities.User;
import com.skilloo.api.entities.enuns.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    Page<User> findAllByRole(Role role, Pageable pageable);
}
