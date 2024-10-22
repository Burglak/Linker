package com.burglak.linker.repository;

import com.burglak.linker.model.entity.PostReaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepository extends CrudRepository<PostReaction, Long> {
}
