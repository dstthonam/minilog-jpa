package com.onboarding.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequestDto {
	
		@NonNull
		private Long followerId;
		
		@NonNull
		private Long followeeId;
		
}
