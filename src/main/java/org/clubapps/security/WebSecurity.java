package org.clubapps.security;

import static org.clubapps.security.SecurityConstants.GET_PHOTOS;
import static org.clubapps.security.SecurityConstants.GET_TEAM_DETAILS_URL;
import static org.clubapps.security.SecurityConstants.GET_TEAM_MEMBERS;
import static org.clubapps.security.SecurityConstants.PAYPAL_IPN;
import static org.clubapps.security.SecurityConstants.PUBLIC_ACCESS;
import static org.clubapps.security.SecurityConstants.CONFIRM_ACADEMY_REG_POST;
import static org.clubapps.security.SecurityConstants.ACADEMY_REG_POST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ArrayList<String> allowedHeaders;
    
    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, GET_TEAM_DETAILS_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_TEAM_MEMBERS ).permitAll()
                .antMatchers(HttpMethod.GET, GET_PHOTOS ).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_ACCESS ).permitAll()
                .antMatchers(HttpMethod.POST,PAYPAL_IPN).permitAll()
                .antMatchers(HttpMethod.POST,CONFIRM_ACADEMY_REG_POST).permitAll()
                .antMatchers(HttpMethod.POST,ACADEMY_REG_POST).permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("admin:read")//hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

	  
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
	List<String> methods = new ArrayList<String>();
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    methods.add("*");
    config.setAllowedMethods(methods);
    
    source.registerCorsConfiguration("/**", config);
	return source;
  }
}
