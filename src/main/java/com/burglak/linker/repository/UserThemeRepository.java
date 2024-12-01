package com.burglak.linker.repository;

import com.burglak.linker.model.UserTheme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThemeRepository extends CrudRepository<UserTheme, Long> {
}
