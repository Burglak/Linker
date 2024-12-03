package com.burglak.linker.activity;

import com.burglak.linker.repository.UserRepository;
import com.burglak.linker.service.JwtService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private UserRepository userRepository;

    private JwtService jwtService;

    public FilterConfig(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Bean
    public FilterRegistrationBean<ActivityTrackingFilter> loggingFilter() {
        FilterRegistrationBean<ActivityTrackingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ActivityTrackingFilter(userRepository, jwtService));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
