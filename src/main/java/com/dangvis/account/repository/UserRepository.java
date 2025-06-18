package com.dangvis.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dangvis.account.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByName(String name);
    boolean existsByUsername(String username);
}
