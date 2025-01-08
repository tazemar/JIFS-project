package com.jifs.server.repository;

import com.jifs.server.entity.Account;
import com.jifs.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(UUID id);

    Optional<Account> findAccountByEmail(String email);

    boolean existsByEmail(String email);

    List<Account> findByRole(Role userRole);
}
