package com.burglak.linker.repository;

import com.burglak.linker.model.UserActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {
    List<UserActivity> findAllByUser_Id(Long userId);
}
