package com.burglak.linker.repository;

import com.burglak.linker.model.entity.PostCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends CrudRepository<PostCategory, Long> {
}
