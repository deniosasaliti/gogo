package com.den.config;

import com.den.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;


import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Configuration
@EnableWebSecurity
@ComponentScan("com.den.security")
@EnableGlobalMethodSecurity
        (securedEnabled = true,
        prePostEnabled = true,
        proxyTargetClass = true)




public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().mvcMatchers("/").requiresSecure()
                .and()

                .authorizeRequests()
                .antMatchers("/sing_up","/login").anonymous()
                .antMatchers("/users").authenticated()
                .and().rememberMe()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .and().logout();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring()
                .antMatchers("/CSS/**")
                 .antMatchers("/static/**")
                .antMatchers("/upload/**");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }




}
