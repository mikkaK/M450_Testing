package com.example.demo.core.security;

import com.example.demo.core.security.helpers.JwtProperties;
import com.example.demo.domain.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtProperties jwtProperties;

  @Autowired
  public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder, JwtProperties jwtProperties) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtProperties = jwtProperties;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests(
        requests -> requests.antMatchers(HttpMethod.POST, "/user/login", "/user/register").permitAll() //permits all for given URLS
                            .antMatchers(HttpMethod.GET, "/userprofile").hasAnyAuthority("USER_MODIFY", "USER_DELETE") //Access for Authorized users with authority USER_MODIFY/USER_DELETE
                            .antMatchers(HttpMethod.GET, "/v3/api-docs","/v3/api-docs/swagger-config","/swagger-ui/*","/myapi/*/*","/myapi/*").permitAll() //permits all for given URLS
                            .anyRequest().authenticated())//Any other requests are required to be authorized
               .addFilterAfter(new JWTAuthenticationFilter(new AntPathRequestMatcher("/user/login", "POST"),
                   authenticationManager(), jwtProperties), UsernamePasswordAuthenticationFilter.class)
               .addFilterAfter(new JWTAuthorizationFilter(userService, jwtProperties),
                   UsernamePasswordAuthenticationFilter.class)
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .cors().configurationSource(corsConfigurationSource())//cross origin resource sharing configuration
               .and()
               .csrf().disable() //cross site request forgery disabled
               .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
    configuration.setExposedHeaders(List.of("Authorization"));

    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("/**", configuration);

    return configurationSource;
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);
    return new ProviderManager(provider);
  }

}
