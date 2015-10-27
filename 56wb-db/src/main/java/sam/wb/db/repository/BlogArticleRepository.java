package sam.wb.db.repository;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.Account;
import sam.wb.db.entity.blog.Article;

public interface BlogArticleRepository extends CrudRepository<Article, Long> {
	
	Article findById(Long id);
	
	Article findByCreatUser(String username);
	
}