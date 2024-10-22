package com.burglak.linker.repository;

import com.burglak.linker.model.entity.UserSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepository extends CrudRepository<UserSettings, Long> {
}
