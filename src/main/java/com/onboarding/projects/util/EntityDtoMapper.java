package com.onboarding.projects.util;

import com.onboarding.projects.dto.ArticleResponseDto;
import com.onboarding.projects.dto.FollowResponseDto;
import com.onboarding.projects.dto.UserResponseDto;
import com.onboarding.projects.entity.Article;
import com.onboarding.projects.entity.Follow;
import com.onboarding.projects.entity.User;

public class EntityDtoMapper {

		public static ArticleResponseDto toDto(Article article) {
			
			return ArticleResponseDto.builder()
							.articleId(article.getArticleId())
							.articleContent(article.getArticleContent())
							.authorId(article.getAuthor().getUserId())
							.authorName(article.getAuthor().getUserName())
							.createdDate(article.getCreatedDate())
							.build();
		}
		
		public static FollowResponseDto toDto(Follow follow) {
			
			return FollowResponseDto.builder()
							.followerId(follow.getFollower().getUserId())
							.followeeId(follow.getFollowee().getUserId())
							.build();
		}
		
		public static UserResponseDto toDto(User user) {
			
			return UserResponseDto.builder()
							.userId(user.getUserId())
							.userName(user.getUserName())
							.deleteFlag(user.getDeleteFlag())
							.build();
		}
		
		public static Follow toEntity(Long followerId, Long followeeId) {
			
			return Follow.builder()
							.follower(User.builder().userId(followerId).build())
							.followee(User.builder().userId(followeeId).build())
							.build();
		}
}
