package com.burglak.linker.repository;

import com.burglak.linker.model.UserImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends CrudRepository<UserImage, Long> {
}
