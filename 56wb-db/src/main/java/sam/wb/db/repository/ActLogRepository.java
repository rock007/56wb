package sam.wb.db.repository;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.ActLog;

public interface ActLogRepository extends CrudRepository<ActLog, Long> {
	ActLog findById(Long id);
}