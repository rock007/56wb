package sam.wb.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import sam.wb.db.entity.blog.Article;

public interface BlogArticleRepository extends CrudRepository<Article, Long>,
												PagingAndSortingRepository<Article, Long> {
	
	Article findById(Long id);
	
	Page<Article> findByCreatUserOrderByIdDesc(String username, Pageable pageable);
	
}