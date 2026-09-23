package com.onboarding.projects.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
		
		@Id
		@Column(name = "USER_ID")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long userId;

		@Column(name = "USER_NAME", nullable = false, unique  = true)
		private String userName;
		
		@Column(name = "USER_PASSWORD", nullable = false)
		private String userPassword;
		
		@Column(name = "DEL_FLAG")
		@Builder.Default
		private String deleteFlag = "N";
		
		@OneToMany(
				mappedBy = "author"
	 			//,cascade = CascadeType.ALL
				//,orphanRemoval = true
				,fetch = FetchType.LAZY
		)
		private List<Article> articles;

		public User (String userName, String userPassword, String deleteFlag) {
				this.userName = userName;
				this.userPassword = userPassword;
				this.deleteFlag = deleteFlag;
		}
		
		public void updateUser(String userName, String userPassword, String deleteFlag) {
				this.userName = userName;
				this.userPassword = userPassword;
				this.deleteFlag = deleteFlag;
		}
		
}
