package com.oma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("userPass")).roles("USER")
            .and()
            .withUser("manager").password(passwordEncoder().encode("managerPass")).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/anonymous*").anonymous()
            .antMatchers("/login*").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/homepage.html", true)
            .failureUrl("/login.html?error=true")
            .and()
            .logout()
            .logoutUrl("/perform_logout")
            .deleteCookies("JSESSIONID");
        http.formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/homepage.html",true)
            .failureUrl("/login.html?error=true");
     }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
