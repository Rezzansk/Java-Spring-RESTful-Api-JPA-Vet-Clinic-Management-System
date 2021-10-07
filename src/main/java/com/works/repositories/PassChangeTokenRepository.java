package com.works.repositories;

import com.works.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassChangeTokenRepository extends JpaRepository<PasswordResetToken,Integer> {
    PasswordResetToken findByTokenEquals(String token);
}
