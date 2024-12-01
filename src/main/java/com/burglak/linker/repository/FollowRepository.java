package com.burglak.linker.repository;

import com.burglak.linker.model.Follow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Long> {
}
