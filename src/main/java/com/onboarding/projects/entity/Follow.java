package com.onboarding.projects.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
		name = "FOLLOWS"
	    ,indexes = {
			   @Index(name = "idx_follower_id", columnList = "follower_id")
			  ,@Index(name = "idx_followee_id", columnList = "followee_id")
		}
	    ,uniqueConstraints = {
	    		@UniqueConstraint(columnNames = {"follower_id", "followee_id"})}
	    )
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Follow extends BaseEntity {
	
		@Id
		@Column(name = "FOLLOW_ID")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long followId;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "follower_id", nullable =  false)
		private User follower;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "followee_id", nullable =  false)
		private User followee;

		public Follow (User follower, User followee) {
				this.follower = follower;
				this.followee = followee;
		}
		
		public void updateFollow (User follower, User followee) {
			this.follower = follower;
			this.followee = followee;
		}
		
}
