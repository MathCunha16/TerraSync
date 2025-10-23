package com.terrasync.terrasyncBackend.repository;

import com.terrasync.terrasyncBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
