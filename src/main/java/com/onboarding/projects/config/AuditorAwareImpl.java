package com.onboarding.projects.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

		@Override
		public Optional<Long> getCurrentAuditor() {
			
				return Optional.of(1L);
		}

}
