package com.sparta.spartanbcampscheduleplanpp2.repository;

import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
