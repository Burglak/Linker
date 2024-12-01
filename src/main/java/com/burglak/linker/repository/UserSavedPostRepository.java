package com.burglak.linker.repository;

import com.burglak.linker.model.UserSavedPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSavedPostRepository extends CrudRepository<UserSavedPost, Long> {
}
