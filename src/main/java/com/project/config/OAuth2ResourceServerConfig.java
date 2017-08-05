package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by Noman
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String UFRIL_REST_RESOURCE_ID = "rest_api";



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(UFRIL_REST_RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
             http
                .requestMatchers().antMatchers("/oauth/v1/**")
                .and()
                .authorizeRequests().antMatchers("/oauth/v1/**").authenticated();
    }

}
