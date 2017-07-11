package com.fp.gan.db.repository;

import com.fp.gan.db.entity.ActLog;
import org.springframework.data.repository.CrudRepository;

public interface ActLogRepository extends CrudRepository<ActLog, Long> {
	ActLog findById(Long id);
}