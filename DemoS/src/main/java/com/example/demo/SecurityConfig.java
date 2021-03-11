package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.JwtFilter;
import com.example.demo.repo.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {
	
//	@Autowired
//	private final UserRepository  userRepository;
//	@Autowired
    private UserService accountService;

    @Autowired
    private JwtFilter jwtFilter;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Override
//    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
//    
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder authentication) throws Exception {
//        authentication.userDetailsService(userDetailsServiceBean());
//        //authentication.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new UserService();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/getname","/api/account/authenticate","/api/account/byToken"
                        )
                .permitAll()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .cors()
            .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}