package com.thesis.medicalapplication.config;

import com.thesis.medicalapplication.handler.UrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/index/**", "/resetPassword").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/user/**").authenticated().anyRequest().permitAll()
                .and().authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/doctor/**").authenticated().anyRequest().hasAnyRole("ADMIN", "DOCTOR")
                .and().formLogin().loginPage("/index/login")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/index/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());

    }


}
