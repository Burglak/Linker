package com.burglak.linker.repository;

import com.burglak.linker.model.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, Long> {
}
