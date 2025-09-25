package com.terrasync.terrasync_backend.repository;

import com.terrasync.terrasync_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
