package com.onboarding.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onboarding.projects.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	
		List<Article> findAllByAuthor_UserId(Long authorId);
		
		//  pis 팔로우한 사용자가 작성한 글을 최신순으로 모두 조회
	    @Query("""
			        SELECT a
			        	FROM Article a
			        	  JOIN a.author u
			        	  JOIN Follow f ON u.userId = f.followee.userId
			        WHERE f.follower.userId = :authorId
			          ORDER BY a.createdBy DESC
	        """)
		List<Article> findAllByFollower_UserId(@Param("authorId") Long authorId);
		
}
