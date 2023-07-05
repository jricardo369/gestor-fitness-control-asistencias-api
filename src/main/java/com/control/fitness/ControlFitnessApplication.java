package com.control.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ControlFitnessApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ControlFitnessApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/swagger-ui.html**").permitAll()
					.antMatchers("/webjars/**").permitAll()
					.antMatchers("/swagger-resources/**").permitAll()
					.antMatchers("/csrf/**").permitAll()
					.antMatchers("/v2/**").permitAll()
					.antMatchers(HttpMethod.POST, "/autenticaciones").permitAll()
					.antMatchers(HttpMethod.POST, "/autenticaciones/inicio-sesion").permitAll()
					.and().authorizeRequests()
					.antMatchers("/usuarios/**").permitAll()
					.antMatchers("/**").permitAll()
					
					.anyRequest().authenticated();
		}
		
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE","POST","OPTIONS","GET","PATCH").allowedHeaders("*",
                        "Access-Control-Request-Headers").exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
			}
		};
	}


	
	
	
}
