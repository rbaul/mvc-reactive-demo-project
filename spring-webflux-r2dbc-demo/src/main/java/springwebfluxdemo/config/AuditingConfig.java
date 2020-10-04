package springwebfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcAuditing
public class AuditingConfig {

    public static final String ANONYMOUS_USER = "anonymousUser";

    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return AuditingConfig::getUser;
    }

    private static Mono<String> getUser() {
        return ReactiveSecurityContextHolder.getContext()//.switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName).switchIfEmpty(Mono.just(ANONYMOUS_USER));
    }


}