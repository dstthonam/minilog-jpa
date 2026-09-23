package com.onboarding.projects.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NonNull
		@Column(nullable = false, name = "Todo_Title")
		private String title;
		
		@Column(name = "Todo_Desc")
		private String descriptionString;
		
		@Column(nullable = false, name = "Todo_Completed")
		private boolean completed; 
		
}
