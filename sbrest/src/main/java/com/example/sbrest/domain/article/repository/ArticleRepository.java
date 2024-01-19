package com.example.sbrest.domain.article.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbrest.domain.article.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Page<Article> findAll(Pageable pageable);

	Optional<Article> findById(Long id);
}
