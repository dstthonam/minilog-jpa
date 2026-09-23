package com.onboarding.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestDto {
	
		@NonNull
		private Long followerId;
		
		@NonNull
		private Long followeeId;
		
}
