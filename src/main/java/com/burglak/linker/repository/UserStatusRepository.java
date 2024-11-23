package com.burglak.linker.repository;

import com.burglak.linker.model.entity.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends CrudRepository<UserStatus, Long> {
}
