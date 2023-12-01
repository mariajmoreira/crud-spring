package com.crud.crudspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.crud.crudspring.repository.UsersRepository;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
// @EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private UsersRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

   @Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .build();
}
    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());
        System.out.println(authProvider.toString());
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
       // CorsConfigurationSource corsConfigurationSource = corsConfigurationSource();
        http.csrf(csrf -> csrf.disable());
       http.cors(withDefaults());
        http.httpBasic(withDefaults());
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
               // .rememberMe(me -> me.userDetailsService(userDetailsService())); // important

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/users", "api/users/login");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() throws IOException{
	final CorsConfiguration configuration = new CorsConfiguration();

	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:8080"));
	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers",  "Origin","Accept", "X-Requested-With", "Content-Type","Access-Control-Request-Method", "Access-Control-Request-Headers","Authorization"));

	final org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:8080","http://localhost:4200");
			}
		};
	}
    /*
     * @Bean
     * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     * http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
     * 
     * .authorizeHttpRequests((authorize) -> authorize
     * .anyRequest().authenticated()
     * )
     * .httpBasic(Customizer.withDefaults())
     * .formLogin(Customizer.withDefaults());
     * 
     * return http.build();
     * }
     */

}