package com.onboarding.projects.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class FollowResponseDto {
	
		@NonNull
		private Long followerId;
		
		@NonNull
		private Long followeeId;

		private Long createdBy;

		private LocalDateTime createdDate;

		private Long modifiedBy;
		
		private LocalDateTime modifiedDate;
}
