package com.onboarding.projects.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboarding.projects.dto.ArticleRequestDto;
import com.onboarding.projects.dto.ArticleResponseDto;
import com.onboarding.projects.entity.Article;
import com.onboarding.projects.entity.User;
import com.onboarding.projects.exception.ArticleNotFoundException;
import com.onboarding.projects.exception.UserNotFoundException;
import com.onboarding.projects.repository.ArticleRepository;
import com.onboarding.projects.repository.UserRepository;
import com.onboarding.projects.util.EntityDtoMapper;

@Service
@Transactional
public class ArticleService {
	
		private final ArticleRepository articleRepository;
		private final UserRepository userRepository;
		
		@Autowired
		public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
				this.articleRepository = articleRepository;
				this.userRepository = userRepository;
		}

		// 팔로우한 사용자의 글을 모두 조회
		@Transactional(readOnly = true)
		public List<ArticleResponseDto> getFeedListByFollowerId(Long userId) {

				userRepository.findById(userId)
												.orElseThrow(() -> new UserNotFoundException(
														String.format("해당 아이디(%d)을/를 가진 사용자를 찾을 수 없습니다.", userId)));
				
				List<Article> feedList = articleRepository.findAllByFollower_UserId(userId);
				
				return feedList.stream().map(EntityDtoMapper::toDto).toList();
		}

		// 해당 사용자의 글을 모두 조회
		@Transactional(readOnly = true)
		public List<ArticleResponseDto> getArticleListByUserId(Long userId) {

				userRepository.findById(userId)
												.orElseThrow(() -> new UserNotFoundException(
														String.format("해당 아이디(%d)을/를 가진 사용자를 찾을 수 없습니다.", userId)));
				
				List<Article> articleList = articleRepository.findAllByAuthor_UserId(userId);
				
				return articleList.stream().map(EntityDtoMapper::toDto).toList();
		}

		@Transactional(readOnly = true)
		public ArticleResponseDto getArticleById(Long articleId) {

				Article article = articleRepository.findById(articleId)
																						.orElseThrow(() -> new ArticleNotFoundException(
																								String.format("해당 게시글(%d)을 찾을 수 없습니다.", articleId)));

				return EntityDtoMapper.toDto(article);
		}

		public ArticleResponseDto createArticle(ArticleRequestDto articleRequestDto) {

				User user = userRepository.findById(articleRequestDto.getAuthorId())
																		.orElseThrow(() -> new UserNotFoundException(
																				String.format("해당 아이디(%d)을/를 가진 사용자를 찾을 수 없습니다.", articleRequestDto.getAuthorId())));
				
				Article savedArticle = articleRepository.save(
																	Article.builder()
																					.articleContent(articleRequestDto.getArticleContent())
																					.author(user)
																					.build());
				
				return EntityDtoMapper.toDto(savedArticle);
		}
		
		public ArticleResponseDto updateArticle(Long articleId, ArticleRequestDto article) {
				
				Article updateArticle = articleRepository.findById(articleId)
																									.orElseThrow(() -> new ArticleNotFoundException(
																											String.format("해당 게시글(%d)을 찾을 수 없습니다.", articleId)));

				updateArticle.updateArticle(article.getArticleContent(), article.getDeleteFlag());
				
				return EntityDtoMapper.toDto(updateArticle);
		}

		public void deleteArticle(Long articleId) {

				Article deleteArticle = articleRepository.findById(articleId)
																									.orElseThrow(() -> new ArticleNotFoundException(
																											String.format("해당 게시글(%d)을 찾을 수 없습니다.", articleId)));

				articleRepository.delete(deleteArticle);
		}
		
}
