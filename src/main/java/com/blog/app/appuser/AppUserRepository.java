package com.blog.app.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findUserByEmail(String email);
}
