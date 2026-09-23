package com.onboarding.projects.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

		@CreatedBy
	    @Column(name = "INS_USRID", updatable = false)
		private Long createdBy;
				
		@CreatedDate
	    @Column(name = "INS_DATE", updatable = false)
		private LocalDateTime createdDate;

		@LastModifiedBy
	    @Column(name = "UPD_USRID", updatable = true)
		private Long modifiedBy;
		
		@LastModifiedDate
	    @Column(name = "UPD_DATE", updatable = true)
		private LocalDateTime modifiedDate;
		
	    @PrePersist
	    protected void prePersist() {
		        this.createdBy = 1L;
		        this.createdDate = LocalDateTime.now();
	    }
		
}
