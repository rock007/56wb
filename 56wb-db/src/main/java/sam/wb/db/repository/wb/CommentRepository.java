package sam.wb.db.repository.wb;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.wb.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
}