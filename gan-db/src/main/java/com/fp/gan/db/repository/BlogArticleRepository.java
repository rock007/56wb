package com.fp.gan.db.repository;

import com.fp.gan.db.entity.blog.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogArticleRepository extends CrudRepository<Article, Long>,
												PagingAndSortingRepository<Article, Long> {
	
	Article findById(Long id);
	
	Page<Article> findByCreateUserOrderByIdDesc(String username, Pageable pageable);
	
}