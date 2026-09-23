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
public class UserRequestDto {
	
		@NonNull
		private String userName;
		
		@NonNull
		private String userPassword;
	
		@NonNull
		private String deleteFlag;
}
