package com.burglak.linker.repository;

import com.burglak.linker.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = false")
    void updateAllUsersToInactive();

}
