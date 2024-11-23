package com.burglak.linker.repository;

import com.burglak.linker.model.entity.PostReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends CrudRepository<PostReport, Long> {
}
