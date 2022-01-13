package br.com.josias.users.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.josias.users.service.UserService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//Em banco de dados
		auth.userDetailsService(userService)
					.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/api/users/registration").permitAll()
				.antMatchers(HttpMethod.GET,"/api/users/admin/").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET,"/api/users/admin/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST,"/api/users/admin/").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/api/users/admin/**").hasRole("ADMIN")
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();
	}
	
	
	
}