package springmvcdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableMongoAuditing
public class AuditingConfig {

    public static final String ANONYMOUS_USER = "anonymousUser";

    @Bean
    public AuditorAware<String> auditorAware() {
        return AuditingConfig::getUser;
    }

    private static Optional<String> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(ANONYMOUS_USER);
        }
        return Optional.ofNullable(authentication.getName());
    }

    public static String getUserName() {
        return getUser().orElse(ANONYMOUS_USER);
    }
}