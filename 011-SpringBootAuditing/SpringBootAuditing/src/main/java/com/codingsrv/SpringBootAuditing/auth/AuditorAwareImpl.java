package com.codingsrv.SpringBootAuditing.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // get security context
        // get authentication
        // get principle(user who have logged in)
        // get Username
        return Optional.of("saurav kumar"); // hardcoding username is not recommended as it should come from spring security.
    }
}
