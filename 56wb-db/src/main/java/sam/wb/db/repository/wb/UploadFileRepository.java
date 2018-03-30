package sam.wb.db.repository.wb;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.wb.UploadFile;

public interface UploadFileRepository extends CrudRepository<UploadFile, Long> {
	
}