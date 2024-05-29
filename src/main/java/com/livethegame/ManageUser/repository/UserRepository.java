package com.livethegame.ManageUser.repository;

import com.livethegame.ManageUser.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
