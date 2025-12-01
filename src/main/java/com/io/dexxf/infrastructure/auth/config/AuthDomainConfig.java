package com.io.dexxf.infrastructure.auth.config;

import com.io.dexxf.common.util.IDGenerator;
import com.io.dexxf.domain.auth.factory.AuthFactory;
import com.io.dexxf.domain.auth.factory.DefaultAuthFactory;
import com.io.dexxf.domain.auth.repository.AuthDomainRepository;
import com.io.dexxf.domain.auth.service.AuthIDGenerator;
import com.io.dexxf.domain.auth.service.ValidateAuthRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;

@Configuration
public class AuthDomainConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ValidateAuthRegistration validateAuthRegistration(AuthFactory factory, AuthDomainRepository repository) {
        return new ValidateAuthRegistration(factory,repository);
    }

    @Bean
    public DefaultAuthFactory authFactory(IDGenerator idGenerator, Clock clock) {
        return new DefaultAuthFactory(idGenerator,clock);
    }

    @Bean
    public AuthIDGenerator idGenerator(AuthDomainRepository repository) {
        return new AuthIDGenerator(repository);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
