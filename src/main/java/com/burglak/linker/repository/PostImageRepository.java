package com.burglak.linker.repository;

import com.burglak.linker.model.entity.PostImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends CrudRepository<PostImage, Long> {
}
