package com.onboarding.projects.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ARTICLES")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {

		@Id
		@Column(name = "ARTICLE_ID")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long articleId;
	
		@Column(name = "ARTICLE_CONTENT")
		private String articleContent;

		@Column(name = "DEL_FLAG")
		@Builder.Default
		private String deleteFlag = "N";
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "author_id", nullable = false)
		private User author;

		public Article (String articleContent,User author) {
				this.articleContent = articleContent;
				this.author = author;
				this.deleteFlag = "N";
		}
		
		public void updateArticle(String articleContent, String deleteFlag) {
				this.articleContent = articleContent;
				this.deleteFlag = deleteFlag;
		}
		
}
